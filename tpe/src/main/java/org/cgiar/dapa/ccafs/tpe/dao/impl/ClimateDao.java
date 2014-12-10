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

import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.cgiar.dapa.ccafs.tpe.chart.ClimatePlot;
import org.cgiar.dapa.ccafs.tpe.dao.IClimateDao;
import org.cgiar.dapa.ccafs.tpe.entity.Climate;
import org.cgiar.dapa.ccafs.tpe.entity.Station;
import org.cgiar.dapa.ccafs.tpe.geojson.FeaturePoint;
import org.cgiar.dapa.ccafs.tpe.geojson.GeometryPoint;
import org.cgiar.dapa.ccafs.tpe.util.FeatureType;

/**
 * This class implements the methods defined in the Climate DAO interface
 * 
 * @author NMATOVU
 *
 */
@SuppressWarnings({ "unchecked", "unused" })
public class ClimateDao extends GenericDao<Climate, Long> implements
		IClimateDao {

	private static final String TMIN = "minT";
	private static final String TMAX = "maxT";
	private static final String RADIATION = "radiation";
	private static final String PRECIPITATION = "precipitation";
	private static final String PLOT_DATA = "plotData";

	public ClimateDao() {
		super(Climate.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Climate> getClimateByStations(List<Integer> stationIds,
			Integer categoryId, String year) {
		StringBuffer q = new StringBuffer("from " + entityClass.getName())
				.append(" r where r.station.id in (:stations)")
				.append(" and r.category.id =:category")
				.append(" and r.year =:year");

		Query query = entityManager.createQuery(q.toString());
		query.setParameter("stations", stationIds);
		query.setParameter("category", categoryId);
		query.setParameter("year", year);
		return query.getResultList();
	}

	@Override
	public List<Climate> getClimateByRegions(List<Integer> regionIds,
			Integer categoryId, String year) {
		StringBuffer q = new StringBuffer("from " + entityClass.getName())
				.append(" r where r.station.region.id in (:regions)")
				.append(" and r.category.id =:category")
				.append(" and r.year =:year");

		Query query = entityManager.createQuery(q.toString());
		query.setParameter("regions", regionIds);
		query.setParameter("category", categoryId);
		query.setParameter("year", year);
		return query.getResultList();
	}

	@Override
	public List<Climate> getClimate(Date fromDate, Date toDate,
			Integer regionId, Integer categoryId) {
		StringBuffer q = new StringBuffer("from " + entityClass.getName())
				.append(" r where r.station.region.id =:region")
				.append(" and r.category.id =:category")
				.append(" and r.recordedOn between :fromDate and :toDate");

		Query query = entityManager.createQuery(q.toString());
		// query.setMaxResults(rows);
		// query.setFirstResult((page - 1) * rows);
		query.setParameter("category", categoryId);
		query.setParameter("region", regionId);
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);

		return query.getResultList();
	}

	@Override
	public List<Station> getStationsByClimate(Date fromDate, Date toDate,
			Integer categoryId, Integer regionId) {
		StringBuffer q = new StringBuffer("select r.station from "
				+ entityClass.getName())
				.append(" r where r.station.region.id =:region")
				.append(" and r.category.id =:category")
				.append(" and r.recordedOn between :fromDate and :toDate");

		Query query = entityManager.createQuery(q.toString());
		// query.setMaxResults(rows);
		// query.setFirstResult((page - 1) * rows);
		query.setParameter("category", categoryId);
		query.setParameter("region", regionId);
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);

		return query.getResultList();
	}

	@Override
	public Map<String, Object> getClimateGeoJSON(Integer countryId,
			List<Integer> indicators) {

		// TODO Ignore the indicators
		// Initialize the climateGeoJSON with a LinkedHashMap();
		Map<String, Object> climateGeoJSON = new LinkedHashMap<String, Object>();
		List<FeaturePoint> climateFeatures = new LinkedList<FeaturePoint>();
		GeometryPoint climateGeometry;
		// FeatureProperty climateProperty;
		Map<String, Object> properties = new LinkedHashMap<String, Object>();
		StringBuffer q = new StringBuffer("from " + entityClass.getName())
				.append(" r where r.station.region.parent.id =:country")
				.append(" or r.station.region.parent.parent.id =:country");
		Query query = entityManager.createQuery(q.toString());
		query.setParameter("country", countryId);
		// query.setParameter("indicators", indicators);
		List<Climate> results = query.getResultList();
		Climate climate;
		for (Iterator<Climate> iterator = results.iterator(); iterator
				.hasNext();) {
			climate = iterator.next();
			climateGeometry = new GeometryPoint(climate.getStation()
					.getCoordinates());
			properties.put(FEATURE_NAME, climate.getStation().getName());
			properties.put(STATION_NUMBER, climate.getStation().getNumber());
			properties.put(STATION_NAME, climate.getStation().getName());
			properties.put(TMIN, climate.getTmin());
			properties.put(TMAX, climate.getTmax());
			properties.put(RADIATION, climate.getRadiation());
			properties.put(PRECIPITATION, climate.getPrecipitation());
			properties.put(REGION_NAME, climate.getStation().getRegion()
					.getName());
			properties.put(PROPERTY_AUTHOR, climate.getAuthor());
			properties.put(PROPERTY_SOURCE, climate.getSource());
			properties.put(FEATURE_COLOR, STATION_COLOR_BLACK);
			properties.put(FEATURE_TYPE, FeatureType.CLIMATE.toString());

			// Add chart data
			// TODO Complete
			List<Object> dat = new LinkedList<Object>();
			List<List<Object>> data = new LinkedList<List<Object>>();
			// Max temp
			dat.add(TMAX);
			dat.add(climate.getTmax());
			data.add(dat);
			dat = new LinkedList<Object>();
			// Min Temp
			dat.add(TMIN);
			dat.add(climate.getTmin());
			data.add(dat);
			dat = new LinkedList<Object>();
			// Precipitation
			dat.add(PRECIPITATION);
			dat.add(climate.getPrecipitation());
			data.add(dat);
			dat = new LinkedList<Object>();
			properties.put(
					PLOT_DATA,
					new LinkedList<ClimatePlot>(Arrays.asList(new ClimatePlot(
							"Environment Sensibility", data))));

			climateFeatures.add(new FeaturePoint(FEATURES_TYPE,
					climateGeometry, properties));
			// Re initialize the properties map
			properties = new LinkedHashMap<String, Object>();
		}
		climateGeoJSON.put(GEOJSON_KEY_TYPE, GEOJSON_VALUE_FEATURE_COLLECTION);
		// Add the feature to the feature collection
		climateGeoJSON.put(GEOJSON_KEY_FEATURES, climateFeatures);
		return climateGeoJSON;
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

}
