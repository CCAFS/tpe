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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;
import javax.sound.sampled.DataLine;

import org.apache.log4j.Logger;
import org.cgiar.dapa.ccafs.tpe.chart.BoxPlot;
import org.cgiar.dapa.ccafs.tpe.chart.Chart;
import org.cgiar.dapa.ccafs.tpe.convexhull.ConvexHull;
import org.cgiar.dapa.ccafs.tpe.convexhull.HullPoint;
import org.cgiar.dapa.ccafs.tpe.convexhull.ITPEConvexHull;
import org.cgiar.dapa.ccafs.tpe.dao.IPhenologyGrowthDao;
import org.cgiar.dapa.ccafs.tpe.entity.Cultivar;
import org.cgiar.dapa.ccafs.tpe.entity.Environment;
import org.cgiar.dapa.ccafs.tpe.entity.PhenologyGrowth;
import org.cgiar.dapa.ccafs.tpe.entity.Region;
import org.cgiar.dapa.ccafs.tpe.entity.Series;
import org.cgiar.dapa.ccafs.tpe.entity.Soil;
import org.cgiar.dapa.ccafs.tpe.entity.Type;
import org.cgiar.dapa.ccafs.tpe.geojson.FeaturePolygon;
import org.cgiar.dapa.ccafs.tpe.geojson.FeatureProperty;
import org.cgiar.dapa.ccafs.tpe.geojson.GeometryPolygon;
import org.cgiar.dapa.ccafs.tpe.util.Cluster;
import org.cgiar.dapa.ccafs.tpe.util.ClusterColor;
import org.cgiar.dapa.ccafs.tpe.util.StatisticsTPE;
import org.cgiar.dapa.ccafs.tpe.util.Utils;

/**
 * This class provides the implementation of the methods defined in the
 * IPhenologyGrowth interface and it extends the Generic Dao class.
 * 
 * @author NMATOVU
 *
 */
@SuppressWarnings("unchecked")
public class PhenologyGrowthDao extends GenericDao<PhenologyGrowth, Long>
		implements IPhenologyGrowthDao {

	private static final String TYPE_LAI = "lai";
	private static final String TYPE_PCEW = "pcew";
	private static final String TYPE_RAIN_S = "rainsum";
	private static final String TYPE_DVS = "dvs";
	private static final String TYPE_TMAX = "tmax";
	private static final String TYPE_TMIN = "tmin";
	private static final String TYPE_TRC = "trc";
	private static final String TYPE_TRW = "trw";
	private static final String TYPE_DTR = "dtr";
	private static final String TYPE_RAIN_CUM = "raincum";
	private static final String TYPE_WAGT = "wagt";
	private static final String TYPE_COLUMN = "column";
	private static final String TYPE_SPLINE = "spline";
	private static final String TYPE = "type";
	private static final String NAME = "name";
	private static final String COLOR = "color";
	private static final String ENABLED = "enabled";
	private static final String MARKER = "marker";
	private static final String LINE_WIDTH = "lineWidth";
	private static final String LINE_COLOR = "lineColor";
	private static final String FILL_COLOR = "fillColor";
	private static final String DATA = "data";
	private static final String CATEGORIES = "categories";
	private static final String SERIES = "series";
	private static final String ENVIRONMENT = "environment";
	private static final String TITLE = "title";
	private static final String TITLE_Y = "yaxis";
	private static final String TITLE_X = "xaxis";
	private static final String PLOT_BANDS = "plotBands";
	private static final String LEGEND_TITLE = "legendTitle";
	private static final String BAND_VEGETATIVE = "Vegetative";
	private static final String BAND_REPRODUCTIVE = "Reproductive";
	private static final String BAND_FILLING_GRAIN = "Filling Grain";
	private static final String FROM = "from";
	private static final String TO = "to";
	private static final String BAND_TEXT = "text";
	private static final String LABEL = "label";

	private Logger log = Logger.getLogger(this.getClass());

	public PhenologyGrowthDao() {
		super(PhenologyGrowth.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Map<String, Map<Double, Double>> getTPERegions(Integer cultivarId,
			Integer regionId, Integer swindowId, String year) {
		// regions map
		Map<String, Map<Double, Double>> regionsMap = new LinkedHashMap<String, Map<Double, Double>>();
		// Point map
		Map<Double, Double> geoMap = new LinkedHashMap<Double, Double>();

		StringBuffer q = new StringBuffer("select r.region from "
				+ entityClass.getName())
				.append(" r where r.region.parent.id =:country")
				.append(" and r.cultivar.id =:cultivar")
				.append(" and r.window.id =:swindow")
				.append(" and r.year =:year");

		Query query = entityManager.createQuery(q.toString());

		query.setParameter("swindow", swindowId);
		query.setParameter("country", regionId);
		query.setParameter("cultivar", cultivarId);
		query.setParameter("year", year);

		List<Region> results = query.getResultList();

		for (Region region : results) {
			geoMap.put(region.getLatitude(), region.getLongitude());
			regionsMap.put(region.getAlphaISO(), geoMap);
		}

		return regionsMap;
	}

	@Override
	public Map<String, Map<Double, Double>> getTPERegions(Integer cultivarId,
			Integer regionId, Integer swindowId, String year, String scenario) {
		// regions map
		Map<String, Map<Double, Double>> regionsMap = new LinkedHashMap<String, Map<Double, Double>>();
		// Point map
		Map<Double, Double> geoMap = new LinkedHashMap<Double, Double>();

		StringBuffer q = new StringBuffer("select r.region from "
				+ entityClass.getName())
				.append(" r where r.region.parent.id =:country")
				.append(" and r.cultivar.id =:cultivar")
				.append(" and r.window.id =:swindow")
				.append(" and r.year =:year")
				.append(" and r.scenario =:scenario");

		Query query = entityManager.createQuery(q.toString());

		query.setParameter("swindow", swindowId);
		query.setParameter("country", regionId);
		query.setParameter("cultivar", cultivarId);
		query.setParameter("year", year);
		query.setParameter("scenario", scenario);

		List<Region> results = query.getResultList();

		for (Region region : results) {
			geoMap.put(region.getLatitude(), region.getLongitude());
			regionsMap.put(region.getAlphaISO(), geoMap);
		}

		return regionsMap;
	}

	@Override
	public Map<String, Map<String, Double>> getTPESoil(Integer cultivarId,
			Integer regionId, Integer swindowId, String year) {
		// soil map Map<SoilCode,Map<regionISO,yield>>
		Map<String, Map<String, Double>> soilMap = new LinkedHashMap<String, Map<String, Double>>();
		// region-yield map (Map<regionISO,yield>)
		Map<String, Double> regionYieldMap = new LinkedHashMap<String, Double>();
		// TODO To retrieve r.soil.code, r.region.lat, etc
		StringBuffer q = new StringBuffer(
				"select r.soil, r.region, r.wrr14 from "
						+ entityClass.getName())
				.append(" r where r.region.parent.id =:country")
				.append(" and r.cultivar.id =:cultivar")
				.append(" and r.window.id =:swindow")
				.append(" and r.year =:year");

		Query query = entityManager.createQuery(q.toString());

		query.setParameter("swindow", swindowId);
		query.setParameter("country", regionId);
		query.setParameter("cultivar", cultivarId);
		query.setParameter("year", year);

		List<Object[]> results = query.getResultList();

		for (Object[] res : results) {
			regionYieldMap.put(String.valueOf(res[1]),
					Double.valueOf((String) res[2]));
			soilMap.put(((Soil) res[0]).getCode(), regionYieldMap);
		}

		return soilMap;
	}

	@Override
	public Map<String, Map<String, Double>> getTPESoil(Integer cultivarId,
			Integer regionId, Integer swindowId, String year, String scenario) {
		// soil map Map<SoilCode,Map<regionISO,yield>>
		Map<String, Map<String, Double>> soilMap = new LinkedHashMap<String, Map<String, Double>>();
		// region-yield map (Map<regionISO,yield>)
		Map<String, Double> regionYieldMap = new LinkedHashMap<String, Double>();
		// TODO To retrieve r.soil.code, r.region.lat, etc
		StringBuffer q = new StringBuffer(
				"select r.soil, r.region, r.wrr14 from "
						+ entityClass.getName())
				.append(" r where r.region.parent.id =:country")
				.append(" and r.cultivar.id =:cultivar")
				.append(" and r.window.id =:swindow")
				.append(" and r.year =:year")
				.append(" and r.scenario =:scenario");

		Query query = entityManager.createQuery(q.toString());

		query.setParameter("swindow", swindowId);
		query.setParameter("country", regionId);
		query.setParameter("cultivar", cultivarId);
		query.setParameter("year", year);
		query.setParameter("scenario", scenario);

		List<Object[]> results = query.getResultList();

		for (Object[] res : results) {
			regionYieldMap.put(String.valueOf(res[1]),
					Double.valueOf((String) res[2]));
			soilMap.put(((Soil) res[0]).getCode(), regionYieldMap);
		}

		return soilMap;
	}

	/**
	 * Retrieves all the sub region ids for the specified country id
	 * 
	 * @param countryId
	 *            the id of the country
	 * @return sub region ids
	 */
	@SuppressWarnings("unused")
	private List<Integer> getSubregions(Integer countryId) {
		StringBuffer q = new StringBuffer("select r.id from "
				+ Region.class.getSimpleName()).append(
				" r where r.parent.id =:country").append(" order by r.id");
		Query query = entityManager.createQuery(q.toString());
		query.setParameter("country", countryId);

		return query.getResultList();
	}

	@SuppressWarnings("unused")
	@Override
	public Map<String, Object> getTPEGeoJSON(Integer cultivarId,
			Integer countryId, Integer swindowId, String year, String scenario) {
		// The convex hull instance
		ITPEConvexHull convexHull = new ConvexHull();
		Map<String, Object> polygonGeoJSON = new LinkedHashMap<String, Object>();
		List<FeaturePolygon> polygonFeatures = new LinkedList<FeaturePolygon>();
		GeometryPolygon polygonGeometry = new GeometryPolygon();
		FeatureProperty polygonProperty = new FeatureProperty();
		// TODO First query for each sub region and create the polygon features
		// based on the yield.
		// Get all the municipios for the specified country id.
		List<Integer> municipios = this.getMunicipios(countryId);
		// Make sure we don't pass the empty or null sub region list
		if (!municipios.isEmpty() && municipios != null) {
			StringBuffer q = new StringBuffer("from " + entityClass.getName());
			Float min, max;

			for (Integer municipio : municipios) {
				// TODO Query for ranges (HFE, LFE, FE)
				// TODO Check if the sub region has atleast 4 points for a
				// given range (HFE, LFE)
				// TODO Add index for yield wrr14 in the schema
				for (Cluster cluster : Cluster.values()) {
					// TODO Order by yield.
					q.append(" r where r.station.region.id =:municipio")
							.append(" and r.cultivar.id =:cultivar")
							.append(" and r.window.id =:swindow")
							.append(" and r.scenario =:scenario")
							.append(" and r.cluster =:cluster")
							.append(" and r.year =:year")
							.append(" order by r.id asc");

					Query query = entityManager.createQuery(q.toString());
					query.setParameter("municipio", municipio);
					query.setParameter("cultivar", cultivarId);
					query.setParameter("swindow", swindowId);
					query.setParameter("year", year);
					query.setParameter("scenario", scenario);
					query.setParameter("cluster", cluster.getEnv());

					List<PhenologyGrowth> results = query.getResultList();
					PhenologyGrowth phenologyGrowth = null;
					// List<List<Double>> coordinates = new
					// LinkedList<List<Double>>();
					List<HullPoint> convexHullCoordinates = new LinkedList<HullPoint>();
					for (Iterator<PhenologyGrowth> iterator = results
							.iterator(); iterator.hasNext();) {
						phenologyGrowth = iterator.next();

						// coordinates.add(phenologyGrowth.getStation()
						// .getCoordinates());
						// TODO Remove the coordinates
						// coordinates.add(phenologyGrowth.getCoordinates());

						convexHullCoordinates.add(phenologyGrowth
								.getConvexHullCoordinates());

					}
					// TODO Add the first coordinate once again to close the
					// GeoJSON polygon. In GeoJSON, the first and last points
					// have to be the same.
					// if (!coordinates.isEmpty() && coordinates != null)
					// coordinates.add(coordinates.get(0));

					if (phenologyGrowth != null)
						// Get the properties from the last record.
						polygonProperty = new FeatureProperty(
								phenologyGrowth.getCultivar().getName(),
								phenologyGrowth.getYear(),
								phenologyGrowth.getStation().getName(),
								ClusterColor.valueOf(cluster.name()).toString(),
								cluster.name().toString(),
								phenologyGrowth.getCultivar().getCrop()
										.getName(),
								phenologyGrowth.getScenario().name().toString(),
								phenologyGrowth.getCluster()
										+ "_"
										+ phenologyGrowth.getStation()
												.getRegion().getId());
					// Add the coordinates to the polygon.
					// polygonGeometry = new GeometryPolygon(
					// new ArrayList<List<List<Double>>>(
					// Arrays.asList(coordinates)));

					polygonGeometry = new GeometryPolygon(
							convexHull
									.getRectilinearConvexHullPolygon(convexHullCoordinates),
							null);

					polygonFeatures.add(new FeaturePolygon(FEATURES_TYPE,
							polygonGeometry, polygonProperty));
				}
			}
			// Create feature collection
			polygonGeoJSON.put(GEOJSON_KEY_TYPE,
					GEOJSON_VALUE_FEATURE_COLLECTION);
			// Add the feature to the feature collection
			polygonGeoJSON.put(GEOJSON_KEY_FEATURES, polygonFeatures);
		}

		return polygonGeoJSON;
	}

	@Override
	public List<Chart> getTPEColumnSeries(Integer subregionId,
			Integer categoryId, String scenario, Integer cultivarId,
			String year, Integer swindow) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getTPEYears(Integer countryId, Integer cultivarId) {
		StringBuffer q = new StringBuffer("select distinct r.year from "
				+ entityClass.getName())
				.append(" r where r.region.id =:country")
				.append(" or r.region.parent.parent.id =:country")
				.append(" and r.cultivar.id =:cultivar")
				.append(" order by r.year asc");

		Query query = entityManager.createQuery(q.toString());
		query.setParameter("country", countryId);
		query.setParameter("cultivar", cultivarId);

		return query.getResultList();
	}

	/**
	 * Retrieves all the municipios (sub region) ids for the specified country
	 * id
	 * 
	 * @param countryId
	 *            the id of the country
	 * @return municipios (municipalities) ids
	 */
	private List<Integer> getMunicipios(Integer countryId) {
		StringBuffer q = new StringBuffer("select r.id from "
				+ Region.class.getSimpleName()).append(
				" r where r.parent.parent.id =:country").append(
				" order by r.id asc");
		Query query = entityManager.createQuery(q.toString());
		query.setParameter("country", countryId);

		return query.getResultList();
	}

	@Override
	public List<BoxPlot> getTPEBox(Integer country, Integer cultivar) {
		List<BoxPlot> data = new LinkedList<BoxPlot>();
		List<Environment> environments = getEnvironments();
		Environment env;
		// org.apache.commons.math3.util
		List<String> years = new LinkedList<String>();
		years = getTPEYears(country, cultivar);

		Double lowerLimit;
		Double upperLimit;
		Double range;

		for (Iterator<Environment> envIterator = environments.iterator(); envIterator
				.hasNext();) {
			env = envIterator.next();
			List<Float> yield = new LinkedList<Float>();
			List<List<Float>> YieldList = new LinkedList<List<Float>>();
			for (String year : years) {
				StringBuffer q = new StringBuffer("select r.wrr14 from "
						+ entityClass.getName())
						.append(" r where r.region.id =:country")
						.append(" or r.region.parent.parent.id =:country")
						.append(" and r.cultivar.id =:cultivar")
						.append(" and r.environment.id =:environment")
						.append(" and r.year =:year")
						.append(" order by r.wrr14 asc");

				Query query = entityManager.createQuery(q.toString());

				query.setParameter("country", country);
				query.setParameter("cultivar", cultivar);
				query.setParameter("year", year);
				query.setParameter("environment", env.getId());

				List<Number> results = query.getResultList();

				// List<PhenologyGrowth> results = query.getResultList();
				yield = new LinkedList<Float>();
				// YieldList = new LinkedList<List<Float>>();
				// Quartiles
				Number q1, q2, qm, q3, q4;

				q1 = StatisticsTPE.minValue(results);
				q2 = StatisticsTPE.quartile1(results);
				qm = StatisticsTPE.median(results);
				q3 = StatisticsTPE.quartile3(results);
				q4 = StatisticsTPE.maxValue(results);

				yield.add(Float.parseFloat(String.valueOf(q1)));
				yield.add(Float.parseFloat(String.valueOf(q2)));
				yield.add(Float.parseFloat(String.valueOf(qm)));
				yield.add(Float.parseFloat(String.valueOf(q3)));
				yield.add(Float.parseFloat(String.valueOf(q4)));
				// yield.add((Float) q2);
				// yield.add((Float) q3);
				// yield.add((Float) q4);

				YieldList.add(yield);
				yield = new LinkedList<Float>();

				// Calculate outliers
				range = (Double.valueOf(String.valueOf(q3)) - Double
						.valueOf(String.valueOf(q2)));
				lowerLimit = Double.valueOf(String.valueOf(q2)) - 1.5 * range;
				upperLimit = Double.valueOf(String.valueOf(q3)) + 1.5 * range;
				
				//TODO Query: select r.year, r.wrr14 from PhenologyGrowth r where r.wrr14 < lower order by r.wrr14
				//TODO Query: select r.year, r.wrr14 from PhenologyGrowth r where r.wrr14 >upper order by r.wrr14
				//TODO Add the result to the series data.
				//TODO Include other params r.region.id,r.cultivar.id,r.environment.id,r.year,r.wrr14

			}
			data.add(new BoxPlot(YieldList, env.getCode(), env.getColor(), env
					.getColor()));

		}

		return data;
	}

	private List<Environment> getEnvironments() {

		StringBuffer q = new StringBuffer("from "
				+ Environment.class.getSimpleName()).append(" r ");
		Query query = entityManager.createQuery(q.toString());

		return query.getResultList();
	}

	@Override
	public List<Object> getStressCategories(List<String> stressSeries,
			Integer cultivarId, Integer countryId) {

		// TODO Consider environments.
		List<Environment> environments = getEnvironments();
		// for (Environment env : environments)

		StringBuffer q = new StringBuffer("select r.dae from "
				+ entityClass.getName())
				.append(" r where r.region.id =:country")
				.append(" and r.cultivar.id =:cultivar")
				.append(" and r.series.name in(:series)")
				.append(" and r.environment.id =:environment")
				.append(" order by r.dae asc");

		Query query = entityManager.createQuery(q.toString());

		query.setParameter("country", countryId);
		query.setParameter("cultivar", cultivarId);
		query.setParameter("series", stressSeries);
		// Get the id of the first record
		// TODO To refactor this
		query.setParameter("environment", environments.get(0).getId());

		List<Object> results = query.getResultList();

		return results;
	}

	@Override
	public Map<String, Object> getSeriesData(Integer cultivarId,
			Integer countryId) {
		// Map<HFE, List<Map<LAI_SERIES, Object>>>
		// Map<String, List<Map<String, Object>>> environmentMap
		Map<String, Object> environmentMap = new LinkedHashMap<String, Object>();
		// Series list for the particular environment
		List<Map<String, Object>> series = new LinkedList<Map<String, Object>>();
		// Series
		Map<String, Object> seriesMap = new LinkedHashMap<String, Object>();
		List<Map<String, Object>> seriesList = new LinkedList<Map<String, Object>>();
		List<Map<String, Object>> plotBands = new LinkedList<Map<String, Object>>();
		List<Environment> environments = getEnvironments();
		List<Integer> clusters = Utils.getClusters();
		List<Series> seriesTypes = getSeriesTypes();
		Cultivar cultivar = getCultivar(cultivarId);
		List<Object> categories = getStressCategories(Utils.getStressSeries(),
				cultivarId, countryId);
		Series secondSeriesType = null;
		// Map<String, Object> laiData = new LinkedHashMap<String, Object>();
		// List<Map<String, Object>> seriesList = new LinkedList<Map<String,
		// Object>>();
		// Map<String, Object> seriesMap = new LinkedHashMap<String, Object>();
		// Map<String, Object> markerMap = new LinkedHashMap<String, Object>();
		Boolean add = false;
		for (Environment environment : environments) {
			series = new LinkedList<Map<String, Object>>();
			// log.info("=======" + environment.getCode() + "=======");
			for (Series type : seriesTypes) {
				add = false;
				// log.info("typeName: " + type.getName() + " typeId: "+
				// type.getId());
				plotBands = new LinkedList<Map<String, Object>>();
				seriesMap = new LinkedHashMap<String, Object>();
				seriesList = new LinkedList<Map<String, Object>>();
				List<Object[]> queryResult = null;
				List<Object[]> queryResult2 = null;
				String queryString = null;
				String queryString2 = null;

				String index = null;
				String seriesType = null;
				String seriesType2 = null;
				boolean multiAxis = false;
				String titleXaxis = null;
				String titleYaxis = null;
				String titleY2axis = null;
				String titleLegend = null;
				if (type.getName().toLowerCase().equals(TYPE_LAI)) {
					// queryString = new
					// StringBuffer("select r.dae, r.lai, r.actualTranspiration from ");
					// log.info("Ïn LAI Type...");
					queryString = "select r.actualTranspiration, r.lai from ";
					queryString2 = "select r.dae, r.actualTranspiration from ";
					index = TYPE_LAI;
					multiAxis = true;
					seriesType = TYPE_SPLINE;
					seriesType2 = TYPE_COLUMN;
					titleXaxis = "Days After Emergency";
					titleYaxis = "LAI and Actual Transpiration";
					titleLegend = "LAI";
					secondSeriesType = getSeriesByName(TYPE_TRW);
				} else if (type.getName().toLowerCase().equals(TYPE_PCEW)) {
					// queryString = new
					// StringBuffer("select r.dae, r.stressIndex from ");
					queryString = "select r.dae, r.stressIndex from ";
					index = TYPE_PCEW;
					multiAxis = false;
					seriesType = TYPE_SPLINE;
					// seriesType2 = TYPE_SPLINE;
					titleXaxis = "Days After Emergency";
					titleYaxis = "Stress Index (ETa/ETp)";
					titleLegend = "Stress Profile";
				} else if (type.getName().toLowerCase().equals(TYPE_RAIN_S)) {
					// queryString = new
					// StringBuffer("select r.dae, r.averageWeeklyRain from ");
					queryString = "select r.dae, r.averageWeeklyRain from ";
					index = TYPE_RAIN_S;
					multiAxis = false;
					seriesType = TYPE_SPLINE;
					// seriesType2 = TYPE_SPLINE;
					titleXaxis = "Days After Emergency";
					titleYaxis = "Average Weekly Rainfall (mm)";
					titleLegend = "Stress Profile";
				} else if (type.getName().toLowerCase().equals(TYPE_RAIN_CUM)) {
					// queryString = new
					// StringBuffer("select r.dae, r.raincum from ");
					queryString = "select r.dae, r.raincum from ";
					index = TYPE_RAIN_CUM;
					multiAxis = false;
					seriesType = TYPE_COLUMN;
					// seriesType2 = TYPE_SPLINE;
					titleXaxis = "Days After Emergency";
					titleYaxis = "Accumulated Rainfall (mm)";
					titleLegend = "";
				}

				else if (type.getName().toLowerCase().equals(TYPE_WAGT)) {
					// queryString = new
					// StringBuffer("select r.dae, r.wagt from ");
					queryString = "select r.dae, r.wagt from ";
					index = TYPE_WAGT;
					multiAxis = false;
					seriesType = TYPE_COLUMN;
					// seriesType2 = TYPE_SPLINE;
					titleXaxis = "Days After Emergency";
					titleYaxis = "Total Dry Matter (Kg/ha)";
					titleLegend = "Stress Profile";
				} else if (type.getName().equals(TYPE_TMIN)) {
					queryString = "select r.dae, r.tminc from ";
					index = TYPE_TMIN;
					multiAxis = false;
					seriesType = TYPE_COLUMN;
					// seriesType2 = TYPE_SPLINE;
					titleXaxis = "Days After Emergency";
					titleYaxis = "TMAX";
					titleLegend = "Stress Profile";
				} else if (type.getName().equals(TYPE_TMAX)) {
					queryString = "select r.dae, r.tmaxc from ";
					index = TYPE_TMAX;
					multiAxis = false;
					seriesType = TYPE_COLUMN;
					// seriesType2 = TYPE_SPLINE;
					titleXaxis = "Days After Emergency";
					titleYaxis = "TMAX";
					titleLegend = "Stress Profile";
				} else if (type.getName().equals(TYPE_TRW)) {
					queryString = "select r.dae, r.actualTranspiration from ";
					index = TYPE_TRW;
					multiAxis = false;
					seriesType = TYPE_COLUMN;
					// seriesType2 = TYPE_SPLINE;
					titleXaxis = "Days After Emergency";
					titleYaxis = "Actual Transpiration";
					titleLegend = "Stress Profile";
				}

				// else if (type.getName().equals(TYPE_TRC)
				// || type.getName().equals(TYPE_DTR)
				// || type.getName().equals(TYPE_DVS)
				// ) {
				// // TODO Do nothing
				// continue;
				// }
				// The data list for y axis
				List<Object> data = new LinkedList<Object>();
				// Data list for the 2nd y axis
				List<Object> data2 = new LinkedList<Object>();
				// The categories list
				List<Object> cats = new LinkedList<Object>();

				if (queryString != null) {
					for (Integer cluster : clusters) {
						data = new LinkedList<Object>();
						data2 = new LinkedList<Object>();
						queryResult = getSeries(queryString, cluster,
								cultivarId, countryId, environment.getId(),
								type.getId());

						if (multiAxis) {
							queryResult2 = getSeries(queryString2, cluster,
									cultivarId, countryId, environment.getId(),
									secondSeriesType.getId());
							if (queryResult2 != null && !queryResult2.isEmpty()) {
								for (Object[] result2 : queryResult2) {

									// cats.add((Float) result2[0]);
									data2.add(result2[1]);
								}
								seriesList
										.add(getSeriesMap(data2, seriesType2,
												Utils.getClusterColor(cluster),
												cluster));
							}
						}

						if (queryResult != null && !queryResult.isEmpty()) {
							add = true;
							cats = new LinkedList<Object>();
							for (Object[] result : queryResult) {

								cats.add((Float) result[0]);
								data.add(result[1]);
								// if (multiAxis)
								// data2.add(result[2]);
							}
							// log.info("Cats:" + cats);
							// log.info("Data: " + data);
							seriesList.add(getSeriesMap(data, seriesType,
									Utils.getClusterColor(cluster), cluster));
						}
					}

					if (add) {
						seriesMap.put(CATEGORIES, cats);
						// series.add(seriesMap);
						seriesMap.put(SERIES, seriesList);
						seriesMap.put(TYPE, type.getName().toUpperCase());
						seriesMap.put(ENVIRONMENT, environment.getCode());
						seriesMap.put(TITLE, type.getName());
						seriesMap.put(TITLE_Y, titleYaxis);
						// seriesMap.put(TITLE_Y2, type.getName());
						// TODO Modify this
						seriesMap.put(TITLE_X, titleXaxis);
						seriesMap.put(LEGEND_TITLE, titleLegend);

						// TODO Add plot bands
						plotBands = getPlotBands(cultivar);
						log.info(plotBands);
						seriesMap.put(PLOT_BANDS, plotBands);

						series.add(seriesMap);
					}
				}
			}
			environmentMap.put(environment.getCode(), series);
		}

		return environmentMap;
	}

	private Series getSeriesByName(String name) {
		StringBuffer q = new StringBuffer("from "
				+ Series.class.getSimpleName())
				.append(" r where r.name =:name");
		Query query = entityManager.createQuery(q.toString());
		query.setParameter("name", name);
		return (Series) query.getSingleResult();
	}

	private Cultivar getCultivar(Integer cultivarId) {
		StringBuffer q = new StringBuffer("from "
				+ Cultivar.class.getSimpleName())
				.append(" r where r.id =:cultivar");
		Query query = entityManager.createQuery(q.toString());
		query.setParameter("cultivar", cultivarId);

		return (Cultivar) query.getSingleResult();
	}

	private List<Map<String, Object>> getPlotBands(Cultivar cultivar) {
		// TODO Complete this
		List<String> colors = new ArrayList<String>(Arrays.asList(
				"rgba(256, 10, 10, 0.1)", "rgba(5, 256, 5, 0.2)",
				"rgba(68, 170, 213, 0.1)"));

		List<String> titles = new ArrayList<String>(Arrays.asList(
				BAND_VEGETATIVE, BAND_REPRODUCTIVE, BAND_FILLING_GRAIN));

		List<Map<String, Object>> bands = new LinkedList<Map<String, Object>>();

		// log.info(cultivar.getVegetativeStart());
		bands.add(tpeBand(cultivar.getVegetativeStart(),
				cultivar.getVegetativeEnd(), colors.get(0), titles.get(0)));

		bands.add(tpeBand(cultivar.getReproductiveStart(),
				cultivar.getReproductiveEnd(), colors.get(1), titles.get(1)));

		bands.add(tpeBand(cultivar.getFillingGrainStart(),
				cultivar.getFillingGrainEnd(), colors.get(2), titles.get(2)));

		return bands;
	}

	private Map<String, Object> tpeBand(Integer startDay, Integer endDay,
			String color, String title) {
		// Map<String, Object> band = new LinkedHashMap<String, Object>();
		Map<String, Object> bandsMap = new LinkedHashMap<String, Object>();
		Map<String, Object> labelMap = new LinkedHashMap<String, Object>();
		Map<String, Object> styleMap = new LinkedHashMap<String, Object>();

		bandsMap.put(FROM, (Integer) startDay);
		bandsMap.put(TO, (Integer) endDay);
		bandsMap.put(COLOR, color);
		labelMap = new LinkedHashMap<String, Object>();
		labelMap.put(BAND_TEXT, title);
		styleMap = new LinkedHashMap<String, Object>();
		styleMap.put(COLOR, "#006600");
		styleMap.put("fontWeight", "bold");
		// styleMap.put("fontSize", 16);
		labelMap.put("style", styleMap);
		// style: {
		// color: 'blue',
		// fontWeight: 'bold'
		// }

		bandsMap.put(LABEL, labelMap);

		return bandsMap;
	}

	private Map<String, Object> getSeriesMap(List<Object> data,
			String seriesType, String clusterColor, Integer cluster) {

		Map<String, Object> seriesMap = new LinkedHashMap<String, Object>();
		Map<String, Object> markerMap = new LinkedHashMap<String, Object>();
		// Integer intv = 14;
		seriesMap.put(TYPE, seriesType);
		seriesMap.put(NAME, cluster);
		seriesMap.put(COLOR, clusterColor);
		seriesMap.put(DATA, data);
		seriesMap.put("pointInterval", 7);
		seriesMap.put("pointStart", 21);
		if (seriesType.equals(TYPE_SPLINE)) {
			markerMap.put(LINE_WIDTH, 2);
			markerMap.put(LINE_COLOR, clusterColor);
			markerMap.put(FILL_COLOR, clusterColor);
			markerMap.put(ENABLED, false);
			seriesMap.put(MARKER, markerMap);
		}
		return seriesMap;
	}

	private List<Object[]> getSeries(String queryStr, Integer cluster,
			Integer cultivarId, Integer countryId, Integer environment,
			Integer typeId) {

		// List<String> categories = getSeriesCategories(typeId);
		// log.info("Cluster: " + cluster + " Series: " + categories);
		// if (categories.isEmpty() || categories == null)
		// return null;
		// log.info("country: " + countryId + " cluster: " + cluster +
		// " cultivar: " + cultivarId + " environment: " + environment);
		StringBuffer q = new StringBuffer(queryStr);

		// log.info(queryStr);
		q.append(entityClass.getName())
				.append(" r where r.region.id =:country")
				.append(" and r.cultivar.id =:cultivar")
				.append(" and r.series.id =:series")
				.append(" and r.environment.id =:environment")
				.append(" and r.cluster =:cluster")
				.append(" order by r.dae asc");
		// log.info("Query string...");
		// log.info(q);
		Query query = entityManager.createQuery(q.toString());

		query.setParameter("country", countryId);
		query.setParameter("cultivar", cultivarId);
		query.setParameter("series", typeId);
		query.setParameter("cluster", cluster);
		query.setParameter("environment", environment);

		List<Object[]> results = query.getResultList();
		return results;
	}

	private List<Series> getSeriesTypes() {

		StringBuffer q = new StringBuffer("from "
				+ Series.class.getSimpleName()).append(" r ");
		Query query = entityManager.createQuery(q.toString());

		return query.getResultList();
	}
}
