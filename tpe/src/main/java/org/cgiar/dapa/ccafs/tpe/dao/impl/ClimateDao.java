/*****************************************************************
 * This file is part of CCAFS Target Population Environments Identification Platform.
 * CCAFS TPE is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * at your option) any later version.
 * CCAFS TPE Identification Platform is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with CCAFS TPE Identification Platform. If not, see <http://www.gnu.org/licenses/>.
 *****************************************************************/
package org.cgiar.dapa.ccafs.tpe.dao.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
//import java.util.function.Function;

import java.util.regex.Pattern;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.cgiar.dapa.ccafs.tpe.chart.ClimatePlot;
import org.cgiar.dapa.ccafs.tpe.dao.IClimateDao;
import org.cgiar.dapa.ccafs.tpe.entity.Climate;
import org.cgiar.dapa.ccafs.tpe.entity.Coordinate;
import org.cgiar.dapa.ccafs.tpe.entity.Region;
import org.cgiar.dapa.ccafs.tpe.entity.Station;
import org.cgiar.dapa.ccafs.tpe.geojson.FeaturePoint;
import org.cgiar.dapa.ccafs.tpe.geojson.GeometryPoint;
import org.cgiar.dapa.ccafs.tpe.jqgrid.ClimateGrid;
import org.cgiar.dapa.ccafs.tpe.util.FeatureType;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

//import com.google.common.base.Function;
//import com.google.common.collect.Lists;

/**
 * This class implements the methods defined in the Climate DAO interface
 * 
 * @author NMATOVU
 *
 */
@SuppressWarnings({ "unchecked", "unused" })
public class ClimateDao extends GenericDao<Climate, Long> implements
		IClimateDao {
	private Logger LOG = Logger.getLogger(this.getClass());
	private static final String TMIN = "minT";
	private static final String TMAX = "maxT";
	private static final String RADIATION = "radiation";
	private static final String PRECIPITATION = "precipitation";
	private static final String PLOT_DATA = "plotData";
	private static final String ENABLED = "enabled";
	private static final String TYPE = "type";
	private static final Object TYPE_COLUMN = "column";
	private static final String NAME = "name";
	private static final String COLOR = "color";
	private static final String DATA = "data";
	// private static final String DATA = "data";
	private static final String CATEGORIES = "categories";
	private static final String SERIES = "series";
	private static final String MARKER = "marker";
	private static final Object TYPE_SPLINE = "spline";
	private static final String LINE_WIDTH = "lineWidth";
	private static final String LINE_COLOR = "lineColor";
	private static final String FILL_COLOR = "fillColor";
	private static final Object COLOR_GREEN = "#009900";
	private static final String TOOL_TIP = "tooltip";
	private static final String AXIS_Y = "yAxis";
	private static final Object VALUE_SUFFIX_MM = " mm";
	private static final String VALUE_SUFFIX = "valueSuffix";
	private static final Object VALUE_SUFFIX_C = " Â°C";
	private static final Object VALUE_SUFFIX_R = " MJ/m2.day";
	private static final Object VALUE_SUFFIX_T = " °C";

	public ClimateDao() {
		super(Climate.class);
		// TODO Auto-generated constructor stub
	}

	final Function<String, Double> fn = new Function<String, Double>() {
		@Override
		public Double apply(final String input) {
			return Double.parseDouble(input);
		}
	};

	@Override
	public Map<String, Object> getClimateGeoJSON(Integer countryId,
			List<Integer> indicators, Boolean continent) {

		// TODO Ignore the indicators
		// Initialize the climateGeoJSON with a LinkedHashMap();
		Map<String, Object> climateGeoJSON = new LinkedHashMap<String, Object>();
		List<FeaturePoint> climateFeatures = new LinkedList<FeaturePoint>();

		Query query = null;
		List<Object[]> results = new LinkedList<Object[]>();
		StringBuffer q = null;
		if (continent) {
			q = new StringBuffer(
					"select distinct r.region.name,r.region.parent.name,r.region.parent.parent.name,group_concat(r.tmin,r.month),group_concat(r.tmax,r.month),group_concat(r.precipitation,r.month),r.longitude, r.latitude,r.region.parent.parent.parent.name,r.monthPlantingDate,group_concat(r.radiation,r.month) from "
							+ entityClass.getName())
					.append(" r where r.region.parent.parent.parent.parent.id =:id")
					.append(" and r.continent =:continent")
					.append(" group by r.region.id, r.longitude, r.latitude")
					.append(" order by r.region.id, r.longitude, r.latitude, r.month asc");

			// Query query =
			// entityManager.createNativeQuery("SET SESSION group_concat_max_len = 10000000;");

			// Query query =
			// em.createNativeQuery("SET  group_concat_max_len = 10000000;");
			// query.executeUpdate();

			// results = query.getResultList();

		} else {

			q = new StringBuffer(
					"select distinct r.station.name,r.station.region.name,r.station.region.parent.name,group_concat(r.tmin,r.month),group_concat(r.tmax,r.month),group_concat(r.precipitation,r.month),r.station.longitude, r.station.latitude,r.longitude,r.latitude,group_concat(r.radiation,r.month),r.station.region.parent.parent.name,r.region.id from "
							+ entityClass.getName())
					.append(" r where r.station.region.parent.id =:id")
					.append(" and r.continent =:continent")
					.append(" or r.station.region.parent.parent.id =:id")
					.append(" group by r.station.id, r.longitude, r.latitude")
					.append(" order by r.station.id, r.longitude, r.latitude, r.month asc");

		}

		query = entityManager.createQuery(q.toString());
		query.setParameter("id", countryId);
		query.setParameter("continent", continent);
		results = query.getResultList();

		// log.info(results.size());
		// results = results != null ? results : new ArrayList<Object[]>();

		if ((results == null) || (results.size() == 0)) {
			LOG.warn("No results returned.");
			return new LinkedHashMap<String, Object>();
		}

		LOG.info(results.size());

		climateFeatures.addAll(createFeatures(results, continent));
		// }

		climateGeoJSON.put(GEOJSON_KEY_TYPE, GEOJSON_VALUE_FEATURE_COLLECTION);
		// Add the feature to the feature collection
		climateGeoJSON.put(GEOJSON_KEY_FEATURES, climateFeatures);
		return climateGeoJSON;
	}

	private List<FeaturePoint> createFeatures(List<Object[]> results,
			Boolean continent) {
		// List of features to return
		List<FeaturePoint> features = new LinkedList<FeaturePoint>();

		GeometryPoint climateGeometry = null;
		// FeatureProperty climateProperty;
		Map<String, Object> properties = new LinkedHashMap<String, Object>();
		// Get the first record, all the records have the same region or station
		// and coordinates
		// but they will have different values for tmin, tmax and prec
		// Climate climate = results.get(0);
		// Create a decimal format
		DecimalFormat df = new DecimalFormat("#.##");
		// for (Iterator<Climate> iterator = results.iterator(); iterator
		// .hasNext();) {
		// climate = iterator.next();
		// If there are more than 12 records,

		for (Object[] row : results) {
			properties = new LinkedHashMap<String, Object>();
			String municipio, state, countr;
			Double lon, lat;
			if (continent) {
				// Municipality/State/Country/Continent
				// properties.put(REGION_NAME, row[9]);
				properties.put(PLANTING_DATE, row[9]);
				properties.put(FEATURE_ICON, true);
				// For 3 Admin Levels
				// Municipality/State/Country
				countr = row[2].toString();
				state = removeUnderscores(row[1].toString());
				municipio = removeUnderscores(row[0].toString());
				lon = Double.valueOf(row[6] != null ? row[6].toString()
						: String.valueOf(0));
				lat = Double.valueOf(row[7] != null ? row[7].toString()
						: String.valueOf(0));
			} else {
				properties.put(FEATURE_ICON, false);
				if (row[12] != null) {
					// For 3 Admin Levels
					// Municipality/State/Country
					countr = row[11].toString();
					state = removeUnderscores(row[2].toString());
					municipio = removeUnderscores(row[1].toString());
				} else {
					// For2 admin levels
					// State/Country
					countr = removeUnderscores(row[2].toString());
					state = removeUnderscores(row[1].toString());
					municipio = "";
				}

				lon = Double.valueOf(row[8] != null ? row[8].toString()
						: (row[6] != null ? row[6].toString() : String
								.valueOf(0)));
				lat = Double.valueOf(row[9] != null ? row[9].toString()
						: (row[7] != null ? row[7].toString() : String
								.valueOf(0)));
			}

			// if(){
			//
			// }

			climateGeometry = new GeometryPoint(new LinkedList<Double>(

			Arrays.asList(lon, lat
			// Double.parseDouble(
			// row[6] != null ? row[6].toString()
			// : (row[8] != null ? row[8].toString() : String.valueOf(0)))
			// ,
			// Double.parseDouble(row[7] != null ? row[7].toString()
			// : (row[9] != null ? row[9].toString() :
			// String.valueOf(0))
			// )
			)));
			properties.put(COUNTRY_NAME, countr// row[2]
					);// Country
			properties.put("country", countr
			// row[2]
					);
			// Municipality/State
			properties.put(STATE_NAME, state
			// row[1]
					);
			// properties.put(STATION_NUMBER,
			// climate.getStation().getNumber());
			properties.put(STATION_NAME, row[0]);
			// Municipality
			properties.put(MUNICIPALITY_NAME, municipio
			// row[0]
					);
			properties.put(FEATURE_NAME,
					removeUnderscores(row[0] != null ? row[0].toString()
							: row[1].toString()));// Municipality or station

			if (continent)
				// Municipality/State/Country/Continent
				properties.put(REGION_NAME, row[8]);
			// TODO Refactor the feature ID
			properties.put(STATION_ID, (row[6] != null ? row[6].toString()
					: (row[8] != null ? row[8].toString() : String.valueOf(0)))

			+ "_" + (row[7] != null ? row[7].toString()

			: (row[9] != null ? row[9].toString() : String.valueOf(0))));

			properties.put(FEATURE_ID, (row[6] != null ? row[6].toString()

			: (row[8] != null ? row[8].toString() : String.valueOf(0)))

			+ "_" + (row[7] != null ? row[7].toString()

			:

			(row[9] != null ? row[9].toString() : String.valueOf(0))));

			Map<String, List<Object>> infoSeries = new LinkedHashMap<String, List<Object>>();
			// Create a dummy 12 points for a null series

			// List<Object> dummy12 = new LinkedList<Object>(Arrays.asList("",
			// "",
			// "", "", "", "", "", "", "", "", "", ""));
			// Create series for tmin, tmax and precipitation
			// The results are ordered by month 1-12 (Jan-Dec)
			infoSeries.put(
					"tmin",
					new LinkedList<Object>(Arrays.asList(row[3].toString()
							.split(","))));
			infoSeries.put(
					"tmax",
					new LinkedList<Object>(Arrays.asList(row[4].toString()
							.split(","))));
			infoSeries.put(
					"prec",
					new LinkedList<Object>(Arrays.asList(row[5].toString()
							.split(","))));

			if (row[10] != null)
				infoSeries.put(
						"radi",
						new LinkedList<Object>(Arrays.asList(row[10].toString()
								.split(","))));
			else
				infoSeries.put("radi", null);
			// infoSeries.put(
			// "radi",
			// new LinkedList<Object>(Arrays.asList("", "", "", "",
			// "", "", "", "", "", "", "", "")));

			// infoSeries = getInfoSeries(climate, continent);
			properties.put(INFO_SERIES, infoSeries);
			properties.put(FEATURE_COLOR, STATION_COLOR_RED);
			properties.put(FEATURE_TYPE, FeatureType.CLIMATE.toString());

			// Add data series for creating the graphics plot
			properties
					.put("dataSeries",
							createDataSeries(
									Lists.transform(
											new LinkedList<String>(Arrays
													.asList(row[3].toString()
															.split(","))), fn),
									Lists.transform(
											new LinkedList<String>(Arrays
													.asList(row[4].toString()
															.split(","))), fn),
									Lists.transform(
											new LinkedList<String>(Arrays
													.asList(row[5].toString()
															.split(","))), fn),

									row[10] != null ? Lists.transform(
											new LinkedList<String>(Arrays
													.asList(row[10].toString()
															.split(","))), fn)
											: null,
									(row[6] != null ? row[6].toString() : 0)
											+ "_"
											+ (row[7] != null ? row[7]
													.toString() : 0)));

			features.add(new FeaturePoint(FEATURES_TYPE, climateGeometry,
					properties));
		}

		// properties.put(TMIN, df.format(climate.getTmin()));
		// properties.put(TMAX, df.format(climate.getTmax()));
		// properties.put(RADIATION, df.format(climate.getRadiation()));
		// properties.put(PRECIPITATION,
		// df.format(climate.getPrecipitation()));
		// properties.put(PROPERTY_AUTHOR, climate.getAuthor());
		// properties.put(PROPERTY_SOURCE, climate.getSource());

		// Add chart data
		// TODO Complete
		// List<Object> dat = new LinkedList<Object>();
		// List<List<Object>> data = new LinkedList<List<Object>>();
		// // Max temp
		// dat.add(TMAX);
		// dat.add(climate.getTmax());
		// data.add(dat);
		// dat = new LinkedList<Object>();
		// // Min Temp
		// dat.add(TMIN);
		// dat.add(climate.getTmin());
		// data.add(dat);
		// dat = new LinkedList<Object>();
		// // Precipitation
		// dat.add(PRECIPITATION);
		// dat.add(climate.getPrecipitation());
		// data.add(dat);
		// dat = new LinkedList<Object>();
		// properties.put(
		// PLOT_DATA,
		// new LinkedList<ClimatePlot>(Arrays.asList(new ClimatePlot(
		// "Environment Sensibility", data))));

		// Re initialize the properties map
		// properties = new LinkedHashMap<String, Object>();
		// }
		return features;
	}

	private String removeUnderscores(String name) {
		if (name != null)
//			return name.replaceAll(Pattern.quote("_"), " ").replaceAll("\\d+", "");
			return name.replaceAll("[^a-zA-Z]", " ");
		return null;
	}

	private Map<String, Object> createDataSeries(List<Double> tmin,
			List<Double> tmax, List<Double> prec, List<Double> radi,
			String pointId) {
		Map<String, Object> seriesData = new LinkedHashMap<String, Object>();

		Map<String, Object> stationsSeriesMap = new LinkedHashMap<String, Object>();
		List<Map<String, Object>> seriesMapList = new LinkedList<Map<String, Object>>();
		Map<String, Object> seriesMap = new LinkedHashMap<String, Object>();
		// Map<String, Object> seriesMap2 = new LinkedHashMap<String, Object>();
		Map<String, Object> toolTipMap = new LinkedHashMap<String, Object>();
		// Add the months catgories[1-12]
		// List<Integer> categories = new LinkedList<Integer>(Arrays.asList(1,
		// 2,3, 4, 5, 6, 7, 8, 9, 10, 11, 12));
		List<Integer> categories = MONTHS;
		seriesData.put(CATEGORIES, categories);
		// Limit the values to 2 decimal places
		DecimalFormat df = new DecimalFormat("#.##");

		// The marker options. Only for the lines. Bars (rainfall will not have
		// these options)
		Map<String, Object> markerMap = new LinkedHashMap<String, Object>();

		// Add rainfall column series data
		seriesMap = new LinkedHashMap<String, Object>();
		toolTipMap = new LinkedHashMap<String, Object>();
		seriesMap.put(TYPE, TYPE_COLUMN);
		seriesMap.put(NAME, "Rainfall");
		seriesMap.put(COLOR, "#4572A7");
		seriesMap.put(AXIS_Y, 0);
		seriesMap.put(DATA, prec);
		// Tool Tip
		toolTipMap.put(VALUE_SUFFIX, VALUE_SUFFIX_MM);
		seriesMap.put(TOOL_TIP, toolTipMap);
		seriesMapList.add(seriesMap);

		// Add the min temperature series
		seriesMap = new LinkedHashMap<String, Object>();
		seriesMap.put(TYPE, TYPE_SPLINE);
		seriesMap.put(NAME, "Tmin");
		seriesMap.put(AXIS_Y, 1);
		seriesMap.put(COLOR, "#FF4500");
		seriesMap.put(DATA, tmin);
		toolTipMap = new LinkedHashMap<String, Object>();
		toolTipMap.put(VALUE_SUFFIX, VALUE_SUFFIX_T);
		seriesMap.put(TOOL_TIP, toolTipMap);

		// Add marker options
		markerMap = new LinkedHashMap<String, Object>();
		markerMap.put(LINE_WIDTH, 2);
		// markerMap.put(LINE_COLOR, clusterColor);
		// markerMap.put(FILL_COLOR, clusterColor);
		markerMap.put(ENABLED, false);
		seriesMap.put(MARKER, markerMap);
		seriesMapList.add(seriesMap);

		// Add the max temperature series
		seriesMap = new LinkedHashMap<String, Object>();
		seriesMap.put(TYPE, TYPE_SPLINE);
		seriesMap.put(NAME, "Tmax");
		seriesMap.put(AXIS_Y, 1);
		// seriesMap.put(AXIS_Y, 2);
		seriesMap.put(COLOR, "#800000");
		seriesMap.put(DATA, tmax);
		toolTipMap = new LinkedHashMap<String, Object>();
		toolTipMap.put(VALUE_SUFFIX, VALUE_SUFFIX_T);
		seriesMap.put(TOOL_TIP, toolTipMap);

		// Add marker options
		markerMap = new LinkedHashMap<String, Object>();
		markerMap.put(LINE_WIDTH, 2);
		// markerMap.put(LINE_COLOR, clusterColor);
		// markerMap.put(FILL_COLOR, clusterColor);
		markerMap.put(ENABLED, false);
		seriesMap.put(MARKER, markerMap);
		seriesMapList.add(seriesMap);

		// Add radiation series
		if (radi != null) {
			seriesMap = new LinkedHashMap<String, Object>();
			seriesMap.put(TYPE, TYPE_SPLINE);
			seriesMap.put(NAME, "Radiation");
			seriesMap.put(AXIS_Y, 2);
			seriesMap.put(COLOR, "#89A54E");
			seriesMap.put(DATA, radi);
			toolTipMap = new LinkedHashMap<String, Object>();
			toolTipMap.put(VALUE_SUFFIX, VALUE_SUFFIX_R);
			seriesMap.put(TOOL_TIP, toolTipMap);
			// Add marker options
			// Add marker options
			markerMap = new LinkedHashMap<String, Object>();
			markerMap.put(LINE_WIDTH, 2);
			// markerMap.put(LINE_COLOR, clusterColor);
			// markerMap.put(FILL_COLOR, clusterColor);
			markerMap.put(ENABLED, false);
			seriesMap.put(MARKER, markerMap);

			seriesMapList.add(seriesMap);

		}

		// Add the station id and its series data
		stationsSeriesMap.put(pointId, seriesMapList);

		// seriesData.put(SERIES, stationsSeriesMap);

		seriesData.put(SERIES, seriesMapList);

		return seriesData;
	}

	private Map<String, List<Double>> getInfoSeries(Climate climate,
			Boolean continent) {
		Map<String, List<Double>> infoSeries = new LinkedHashMap<String, List<Double>>();
		List<Double> series = new LinkedList<Double>();
		List<String> variables = new LinkedList<String>(Arrays.asList("tmin",
				"tmax", "precipitation"));

		for (String variable : variables) {
			StringBuffer stringQuery = new StringBuffer("select r." + variable
					+ " from ").append(entityClass.getName()).append(
					" r where r.month in (:months)");
			if (continent) {
				stringQuery.append(" and r.region.id =:region")
						.append(" and r.longitude =:longitude")
						.append(" and r.latitude =:latitude")
						.append(" and r.level.id in(:levels)");
			} else
				stringQuery
						.append(" and r.station.region.parent.id =:region")
						.append(" or r.station.region.parent.parent.id =:region");
			// Order the results by months, 1-12(Jan-Dec).
			stringQuery.append(" order by r.month asc");

			Query query = entityManager.createQuery(stringQuery.toString());
			query.setParameter("region", climate.getRegion().getId());
			query.setParameter("levels",
					new ArrayList<Integer>(Arrays.asList(1, 2, 3)));
			query.setParameter("months", MONTHS);
			if (continent) {
				query.setParameter("longitude", climate.getLongitude());
				query.setParameter("latitude", climate.getLatitude());
			}

			series = query.getResultList();
			infoSeries.put(variable, series);
		}

		return infoSeries;
	}

	@Override
	public List<String> getClimateYears(Integer countryId) {
		StringBuffer q = new StringBuffer("select distinct r.year from "
				+ entityClass.getName()).append(
				" r where r.station.region.parent.id =:country").append(
				" or r.station.region.id =:country");

		Query query = entityManager.createQuery(q.toString());
		query.setParameter("country", countryId);

		return query.getResultList();

	}

	private List<Station> getStations(Integer countryId) {

		StringBuffer q = new StringBuffer("from " + Station.class.getName())
				.append(" r where r.region.parent.id =:region").append(
						" or r.region.parent.parent.id =:region");
		Query query = entityManager.createQuery(q.toString());
		query = entityManager.createQuery(q.toString());
		query.setParameter("region", countryId);

		return query.getResultList();
	}

	@Override
	public Map<String, Object> getClimateSeries(Integer country,
			boolean continent) {
		Map<String, Object> seriesData = new LinkedHashMap<String, Object>();
		Map<Integer, Object> stationsSeriesMap = new LinkedHashMap<Integer, Object>();
		List<Map<String, Object>> seriesMapList = new LinkedList<Map<String, Object>>();
		Map<String, Object> seriesMap = new LinkedHashMap<String, Object>();
		// Map<String, Object> seriesMap2 = new LinkedHashMap<String, Object>();
		Map<String, Object> toolTipMap = new LinkedHashMap<String, Object>();

		// Get the stations
		// List<Station> stations = new LinkedList<Station>();
		// List<Region> regions = new LinkedList<Region>();

		List<Double> rainfall = new LinkedList<Double>();
		List<Double> radiation = new LinkedList<Double>();
		List<Double> tmin = new LinkedList<Double>();
		List<Double> tmax = new LinkedList<Double>();
		// Add the months catgories[1-12]
		// List<Integer> categories = new LinkedList<Integer>(Arrays.asList(1,
		// 2,3, 4, 5, 6, 7, 8, 9, 10, 11, 12));
		List<Integer> categories = MONTHS;
		seriesData.put(CATEGORIES, categories);
		// Limit the values to 2 decimal places
		DecimalFormat df = new DecimalFormat("#.##");

		// The marker options. Only for the lines. Bars (rainfall will not have
		// these options)
		Map<String, Object> markerMap = new LinkedHashMap<String, Object>();

		List<? extends Coordinate> stationsOrRegions = new LinkedList<Coordinate>();
		String queryString = "";
		if (continent) {
			// If Country is Latin America, then regions/municipalities will be
			// loaded.
			// regions = getRegions(country, continent);
			stationsOrRegions = getMunicipalities(country, continent);
			queryString = " r where r.region.id =:station";
		}

		else {
			// Any country
			// stations = getStations(country);
			stationsOrRegions = getStations(country);
			queryString = " r where r.station.id =:station";
		}

		if ((stationsOrRegions == null) || (stationsOrRegions.isEmpty()))
			return new LinkedHashMap<String, Object>();

		for (Coordinate station : stationsOrRegions) {
			seriesMapList = new LinkedList<Map<String, Object>>();
			StringBuffer q = new StringBuffer("from " + entityClass.getName())
					.append(queryString).append(" order by r.month asc");
			Query query = entityManager.createQuery(q.toString());
			query.setParameter("station", station.getId());
			// query.setParameter("indicators", indicators);
			List<Climate> results = query.getResultList();
			rainfall = new LinkedList<Double>();
			radiation = new LinkedList<Double>();
			tmin = new LinkedList<Double>();
			tmax = new LinkedList<Double>();

			// Create the series data
			for (Climate climate : results) {
				rainfall.add(Double.parseDouble(df.format(climate
						.getPrecipitation())));
				// TODO Handle null values
				/*
				 * radiation .add(Double.parseDouble(df.format((climate
				 * .getRadiation() == null) ? climate .getRadiation() : 0)));
				 */
				tmin.add(Double.parseDouble(df.format(climate.getTmin())));
				tmax.add(Double.parseDouble(df.format(climate.getTmax())));
			}

			// Add radiation series
			seriesMap = new LinkedHashMap<String, Object>();
			seriesMap.put(TYPE, TYPE_SPLINE);
			seriesMap.put(NAME, "Radiation");
			seriesMap.put(AXIS_Y, 0);
			seriesMap.put(COLOR, "#89A54E");
			seriesMap.put(DATA, radiation);
			toolTipMap = new LinkedHashMap<String, Object>();
			toolTipMap.put(VALUE_SUFFIX, VALUE_SUFFIX_R);
			seriesMap.put(TOOL_TIP, toolTipMap);
			// Add marker options
			// Add marker options
			markerMap = new LinkedHashMap<String, Object>();
			markerMap.put(LINE_WIDTH, 2);
			// markerMap.put(LINE_COLOR, clusterColor);
			// markerMap.put(FILL_COLOR, clusterColor);
			markerMap.put(ENABLED, false);
			seriesMap.put(MARKER, markerMap);

			seriesMapList.add(seriesMap);

			// Add rainfall column series data
			seriesMap = new LinkedHashMap<String, Object>();
			toolTipMap = new LinkedHashMap<String, Object>();
			seriesMap.put(TYPE, TYPE_COLUMN);
			seriesMap.put(NAME, "Rainfall");
			seriesMap.put(COLOR, "#4572A7");
			seriesMap.put(AXIS_Y, 1);
			seriesMap.put(DATA, rainfall);
			// Tool Tip
			toolTipMap.put(VALUE_SUFFIX, VALUE_SUFFIX_MM);
			seriesMap.put(TOOL_TIP, toolTipMap);
			seriesMapList.add(seriesMap);

			// Add the min temperature series
			seriesMap = new LinkedHashMap<String, Object>();
			seriesMap.put(TYPE, TYPE_SPLINE);
			seriesMap.put(NAME, "Tmin");
			seriesMap.put(AXIS_Y, 2);
			seriesMap.put(COLOR, "#FF4500");
			seriesMap.put(DATA, tmin);
			toolTipMap = new LinkedHashMap<String, Object>();
			toolTipMap.put(VALUE_SUFFIX, VALUE_SUFFIX_T);
			seriesMap.put(TOOL_TIP, toolTipMap);

			// Add marker options
			markerMap = new LinkedHashMap<String, Object>();
			markerMap.put(LINE_WIDTH, 2);
			// markerMap.put(LINE_COLOR, clusterColor);
			// markerMap.put(FILL_COLOR, clusterColor);
			markerMap.put(ENABLED, false);
			seriesMap.put(MARKER, markerMap);
			seriesMapList.add(seriesMap);

			// Add the max temperature series
			seriesMap = new LinkedHashMap<String, Object>();
			seriesMap.put(TYPE, TYPE_SPLINE);
			seriesMap.put(NAME, "Tmax");
			seriesMap.put(AXIS_Y, 3);
			seriesMap.put(COLOR, "#800000");
			seriesMap.put(DATA, tmax);
			toolTipMap = new LinkedHashMap<String, Object>();
			toolTipMap.put(VALUE_SUFFIX, VALUE_SUFFIX_T);
			seriesMap.put(TOOL_TIP, toolTipMap);

			// Add marker options
			markerMap = new LinkedHashMap<String, Object>();
			markerMap.put(LINE_WIDTH, 2);
			// markerMap.put(LINE_COLOR, clusterColor);
			// markerMap.put(FILL_COLOR, clusterColor);
			markerMap.put(ENABLED, false);
			seriesMap.put(MARKER, markerMap);
			seriesMapList.add(seriesMap);

			// Add the station id and its series data
			stationsSeriesMap.put(station.getId(), seriesMapList);
		}
		seriesData.put(SERIES, stationsSeriesMap);

		return seriesData;
	}

	private List<Region> getMunicipalities(Integer country, boolean continent) {

		StringBuffer q = new StringBuffer("from "
				+ Region.class.getSimpleName()).append(
				" r where r.level.id in(:levels)").append(" order by r.id asc");
		Query query = entityManager.createQuery(q.toString());
		query.setParameter("levels",
				new ArrayList<Integer>(Arrays.asList(1, 2, 3)));

		return query.getResultList();
	}

	@Override
	public List<Climate> getClimateByStations(List<Integer> stationIds) {
		StringBuffer q = new StringBuffer("from " + entityClass.getName())
				.append(" r where r.station.id in (:stations)");
		// .append(" and r.year =:year");

		Query query = entityManager.createQuery(q.toString());
		query.setParameter("stations", stationIds);

		return query.getResultList();
	}

	@Override
	public List<Climate> getClimateByRegions(List<Integer> regionIds) {
		StringBuffer q = new StringBuffer("from " + entityClass.getName())
				.append(" r where r.station.region.id in (:regions)");
		// .append(" and r.year =:year");

		Query query = entityManager.createQuery(q.toString());
		query.setParameter("regions", regionIds);
		return query.getResultList();
	}

	@Override
	public List<Object[]> getClimateData(Integer countryId) {

		List<Object[]> results = new LinkedList<Object[]>();

		StringBuffer q = new StringBuffer(
				"select distinct r.region.name,r.region.id,r.region.parent.name,r.region.parent.parent.name,group_concat(r.tmin,r.month),group_concat(r.tmax,r.month),group_concat(r.precipitation,r.month),r.longitude, r.latitude from "
						+ entityClass.getName())
				.append(" r where r.region.parent.parent.parent.parent.id =:id")
				.append(" group by r.region.id, r.longitude, r.latitude")
				.append(" order by r.region.id, r.longitude, r.latitude, r.month asc");

		// Query query =
		// entityManager.createNativeQuery("SET SESSION group_concat_max_len = 10000000;");

		Query query;
		// Query query =
		// em.createNativeQuery("SET  group_concat_max_len = 10000000;");
		// query.executeUpdate();

		query = entityManager.createQuery(q.toString());
		query.setParameter("id", countryId);

		results = query.getResultList();

		return results != null ? results : new ArrayList<Object[]>();

		// List<Object[]> results = new LinkedList<Object[]>();
		//
		// StringBuffer q = new StringBuffer(
		// "select r.region.id, r.month from "
		// + entityClass.getName())
		// .append(" r where r.region.parent.parent.parent.parent.id =:id").append(
		// " order by r.region, r.longitude, r.latitude, r.month asc");
		//
		// Query query = entityManager.createQuery(q.toString());
		// query.setParameter("id", countryId);
		//
		// results = query.getResultList();
		//
		// return results != null ? results : new ArrayList<Object[]>();
	}

	@Override
	public List<ClimateGrid> listClimate(Integer country, Boolean level,
			String param, int start, int rows) {
		List<ClimateGrid> climate = new LinkedList<ClimateGrid>();
		Query query = null;
		// List<Climate> results = new LinkedList<Climate>();
		List<Object[]> results = new LinkedList<Object[]>();
		StringBuffer q = null;
		if (level) {
			// q = new StringBuffer("from " + entityClass.getName())
			// .append(" r where r.region.parent.parent.parent.parent.id =:id")
			// .append(" order by r.region.id, r.longitude, r.latitude, r.month asc");
			// param should be tmin, tmax, precipitation or radiation
			q = new StringBuffer(
			// "select distinct r.region.name,r.region.parent.name,r.region.parent.parent.name,group_concat(r.tmin,r.month),r.month,r.longitude, r.latitude,r.region.parent.parent.parent.name from "
					"select distinct r.region.name,r.region.parent.name,r.region.parent.parent.name,group_concat(r."
							+ param
							+ ",r.month),r.month,r.longitude, r.latitude,r.region.parent.parent.parent.name,r.region.id from "
							+ entityClass.getName())
					.append(" r where r.region.parent.parent.parent.parent.id =:id")
					.append(" and r.continent =:continent")
					.append(" group by r.region.id, r.longitude, r.latitude")
					.append(" order by r.region.id, r.longitude, r.latitude, r.month asc");
		} else {

			// q = new StringBuffer("from " + entityClass.getName())
			// .append(" r where r.station.region.parent.id =:id")
			// .append(" or r.station.region.parent.parent.id =:id")
			// .append(" order by r.station.id, r.longitude, r.latitude, r.month asc");
			// param should be tmin, tmax, precipitation or radiation
			q = new StringBuffer(
			// "select distinct r.station.name,r.station.region.name,r.station.region.parent.name,group_concat(r.tmin,r.month),r.month,r.station.longitude, r.station.latitude,r.longitude,r.latitude from "
					"select distinct r.station.name,r.station.region.name,r.station.region.parent.name,group_concat(r."
							+ param
							+ ",r.month),r.month,r.station.longitude, r.station.latitude,r.longitude,r.latitude,r.station.region.parent.parent.name,r.region.id from "
							+ entityClass.getName())
					.append(" r where r.station.region.parent.id =:id")
					.append(" or r.station.region.parent.parent.id =:id")
					.append(" and r.continent =:continent")
					.append(" group by r.station.id, r.longitude, r.latitude")
					.append(" order by r.station.id, r.longitude, r.latitude, r.month asc");

		}

		query = entityManager.createQuery(q.toString());
		query.setParameter("id", country);
		query.setParameter("continent", level);
		query.setMaxResults(rows);
		query.setFirstResult(start);
		results = query.getResultList();

		// log.info(results.size());
		// results = results != null ? results : new ArrayList<Object[]>();

		if ((results == null) || (results.size() == 0)) {
			LOG.warn("No results retrieved");
			return new ArrayList<ClimateGrid>();
		}
		LOG.info(results.size());
		for (Object[] row : results) {
			List<Object> res = new LinkedList<Object>(Arrays.asList(row[3]
					.toString().split(",")));
			String municipio, state, countr;
			Double lon, lat;
			if (level) {
				// For 3 Admin Levels
				// Municipality/State/Country
				countr = row[2].toString();
				state = row[1].toString();
				municipio = row[0].toString();
				lon = Double.valueOf(row[5].toString());
				lat = Double.valueOf(row[6].toString());
			} else {

				if (row[10] != null) {
					// For 3 Admin Levels
					// Municipality/State/Country
					countr = row[9].toString();
					state = row[2].toString();
					municipio = row[1].toString();
				} else {
					// For2 admin levels
					// State/Country
					countr = row[2].toString();
					state = row[1].toString();
					municipio = "";
				}

				lon = Double.valueOf(row[7] != null ? row[7].toString()
						: row[5].toString());
				lat = Double.valueOf(row[8] != null ? row[8].toString()
						: row[6].toString());
			}
			climate.add(new ClimateGrid(countr, state, municipio, row[0]
					.toString(), Float.valueOf(res.get(0).toString()), Float
					.valueOf(res.get(1).toString()), Float.valueOf(res.get(2)
					.toString()), Float.valueOf(res.get(3).toString()), Float
					.valueOf(res.get(4).toString()), Float.valueOf(res.get(5)
					.toString()), Float.valueOf(res.get(6).toString()), Float
					.valueOf(res.get(7).toString()), Float.valueOf(res.get(8)
					.toString()), Float.valueOf(res.get(9).toString()), Float
					.valueOf(res.get(10).toString()), Float.valueOf(res.get(11)
					.toString()), lon, lat));
		}
		return climate;
	}
}
