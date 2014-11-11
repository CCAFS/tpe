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

import org.cgiar.dapa.ccafs.tpe.chart.Chart;
import org.cgiar.dapa.ccafs.tpe.dao.IPhenologyGrowthDao;
import org.cgiar.dapa.ccafs.tpe.entity.PhenologyGrowth;
import org.cgiar.dapa.ccafs.tpe.entity.Region;
import org.cgiar.dapa.ccafs.tpe.entity.Soil;
import org.cgiar.dapa.ccafs.tpe.geojson.FeaturePolygon;
import org.cgiar.dapa.ccafs.tpe.geojson.FeatureProperty;
import org.cgiar.dapa.ccafs.tpe.geojson.GeometryPolygon;
import org.cgiar.dapa.ccafs.tpe.util.Cluster;
import org.cgiar.dapa.ccafs.tpe.util.TPEType;

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

	@SuppressWarnings("unused")
	@Override
	public Map<String, Object> getTPEGeoJSON(Integer cultivarId,
			Integer countryId, Integer swindowId, String year) {
		Map<String, Object> polygonGeoJSON = new LinkedHashMap<String, Object>();
		List<FeaturePolygon> polygonFeatures = new LinkedList<FeaturePolygon>();
		GeometryPolygon polygonGeometry = new GeometryPolygon();
		FeatureProperty polygonProperty = new FeatureProperty();
		// TODO First query for each sub region and create the polygon features
		// based on the yield.
		// Get all sub regions for the specified country id.
		List<Integer> subregions = this.getSubregions(countryId);
		// Make sure we don't pass the empty or null sub region list
		if (!subregions.isEmpty() && subregions != null) {
			StringBuffer q = new StringBuffer("from " + entityClass.getName());
			Float min, max;

			for (Integer subregionId : subregions) {
				// TODO Query for ranges (HFE, LFE, FE)
				// TODO Check if the sub region has atleast 4 points for a
				// given range (HFE, LFE)
				// TODO Add index for yield wrr14 in the schema
				for (TPEType type : TPEType.values()) {
					// TODO Order by yield.
					q.append(" r where r.station.region.id =:region")
							.append(" and r.cultivar.id =:cultivar")
							.append(" and r.window.id =:swindow")
							.append(" and r.year =:year");
					// TODO Provide the TPE Range of values to that classify the
					// environments.
					// Min and max are set here for just testing purposes. Need
					// to provide the range that catgorizes the environments.
					if (type.equals(TPEType.HFE)) {
						min = new Float(600.2);
						// max = new Float(1000.0);
						q.append(" and r.wrr14 =>:min");
					} else if (type.equals(TPEType.LFE)) {
						min = new Float(200.2);
						max = new Float(600.0);
						q.append(" and r.wrr14 between :min and :max");
					} else {
						// Default FE:
						// No need to specify the min value for the FE
						max = new Float(200.0);
						q.append(" and r.wrr14 <:max");
					}
					q.append(" order by r.id asc");

					Query query = entityManager.createQuery(q.toString());
					query.setParameter("region", subregionId);
					query.setParameter("cultivar", cultivarId);
					query.setParameter("swindow", swindowId);
					query.setParameter("year", year);
					List<PhenologyGrowth> results = query.getResultList();
					PhenologyGrowth phenologyGrowth = null;
					List<List<Double>> coordinates = new LinkedList<List<Double>>();

					for (Iterator<PhenologyGrowth> iterator = results
							.iterator(); iterator.hasNext();) {
						phenologyGrowth = iterator.next();
						coordinates.add(phenologyGrowth.getStation()
								.getCoordinates());

					}
					// TODO Add the first coordinate once again to close the
					// GeoJSON polygon. In GeoJSON, the first and last points
					// have to be the same.
					coordinates.add(coordinates.get(0));

					if (phenologyGrowth != null)
						// Get the properties from the last record.
						polygonProperty = new FeatureProperty(phenologyGrowth
								.getCultivar().getName(),
								phenologyGrowth.getYear(), phenologyGrowth
										.getStation().getName());
					// Add the coordinates to the polygon.
					polygonGeometry = new GeometryPolygon(
							new ArrayList<List<List<Double>>>(
									Arrays.asList(coordinates)));

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

	/**
	 * Retrieves all the sub region ids for the specified country id
	 * 
	 * @param countryId
	 *            the id of the country
	 * @return sub region ids
	 */
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
		Map<String, Object> polygonGeoJSON = new LinkedHashMap<String, Object>();
		List<FeaturePolygon> polygonFeatures = new LinkedList<FeaturePolygon>();
		GeometryPolygon polygonGeometry = new GeometryPolygon();
		FeatureProperty polygonProperty = new FeatureProperty();
		// TODO First query for each sub region and create the polygon features
		// based on the yield.
		// Get all sub regions for the specified country id.
		List<Integer> subregions = this.getSubregions(countryId);
		// Make sure we don't pass the empty or null sub region list
		if (!subregions.isEmpty() && subregions != null) {
			StringBuffer q = new StringBuffer("from " + entityClass.getName());
			Float min, max;

			for (Integer subregionId : subregions) {
				// TODO Query for ranges (HFE, LFE, FE)
				// TODO Check if the sub region has atleast 4 points for a
				// given range (HFE, LFE)
				// TODO Add index for yield wrr14 in the schema
				for (Cluster cluster : Cluster.values()) {
					// TODO Order by yield.
					q.append(" r where r.station.region.id =:region")
							.append(" and r.cultivar.id =:cultivar")
							.append(" and r.window.id =:swindow")
							.append(" and r.scenario =:scenario")
							.append(" and r.cluster =:cluster")
							.append(" and r.year =:year")
							.append(" order by r.id asc");

					// TODO Provide the TPE Range of values to that classify the
					// environments.
					// Min and max are set here for just testing purposes. Need
					// to provide the range that categorizes the environments.

					/*
					 * if (type.equals(TPEType.HFE)) { min = new Float(600.2);
					 * // max = new Float(1000.0);
					 * q.append(" and r.wrr14 =>:min"); } else if
					 * (type.equals(TPEType.LFE)) { min = new Float(200.2); max
					 * = new Float(600.0);
					 * q.append(" and r.wrr14 between :min and :max"); } else {
					 * // Default FE: // No need to specify the min value for
					 * the FE max = new Float(200.0);
					 * q.append(" and r.wrr14 <:max"); }
					 */

					Query query = entityManager.createQuery(q.toString());
					query.setParameter("region", subregionId);
					query.setParameter("cultivar", cultivarId);
					query.setParameter("swindow", swindowId);
					query.setParameter("year", year);
					query.setParameter("scenario", scenario);
					query.setParameter("cluster", cluster.getEnv());

					List<PhenologyGrowth> results = query.getResultList();
					PhenologyGrowth phenologyGrowth = null;
					List<List<Double>> coordinates = new LinkedList<List<Double>>();

					for (Iterator<PhenologyGrowth> iterator = results
							.iterator(); iterator.hasNext();) {
						phenologyGrowth = iterator.next();

						// coordinates.add(phenologyGrowth.getStation()
						// .getCoordinates());
						coordinates.add(phenologyGrowth.getCoordinates());

					}
					// TODO Add the first coordinate once again to close the
					// GeoJSON polygon. In GeoJSON, the first and last points
					// have to be the same.
					if (!coordinates.isEmpty() && coordinates != null)
						coordinates.add(coordinates.get(0));

					if (phenologyGrowth != null)
						// Get the properties from the last record.
						polygonProperty = new FeatureProperty(phenologyGrowth
								.getCultivar().getName(),
								phenologyGrowth.getYear(), phenologyGrowth
										.getStation().getName());
					// Add the coordinates to the polygon.
					polygonGeometry = new GeometryPolygon(
							new ArrayList<List<List<Double>>>(
									Arrays.asList(coordinates)));

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
				.append(" r where r.station.region.parent.id =:country")
				.append(" or r.station.region.id =:country")
				.append(" and r.cultivar.id =:cultivar");

		Query query = entityManager.createQuery(q.toString());
		query.setParameter("country", countryId);
		query.setParameter("cultivar", cultivarId);

		return query.getResultList();
	}

}
