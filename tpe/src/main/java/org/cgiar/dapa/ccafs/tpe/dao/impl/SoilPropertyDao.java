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
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cgiar.dapa.ccafs.tpe.dao.ISoilPropertyDao;
import org.cgiar.dapa.ccafs.tpe.entity.SoilProperty;
import org.cgiar.dapa.ccafs.tpe.geojson.FeaturePoint;
import org.cgiar.dapa.ccafs.tpe.geojson.GeometryPoint;
import org.cgiar.dapa.ccafs.tpe.util.FeatureType;

/**
 * This class implements the soil property interface methods
 * 
 * @author NMATOVU
 *
 */
@SuppressWarnings("unchecked")
public class SoilPropertyDao extends GenericDao<SoilProperty, Long> implements
		ISoilPropertyDao {

	// private static final Object FEATURE_TYPE_STATION = "STATION";

	private static final String SOIL_LAT = "lat";
	private static final String SOIL_LNG = "lng";
	private static final String SOIL_CODE = "code";
	private static final String AVAILABLE_SOIL_WATER = "availableSoilWater";
	private static final String BULK_DENSITY = "bulkDensity";
	private static final String CATION_EXCHANGE = "cationExchange";
	private static final String SOIL_DEPTH = "depth";
	private static final String ORGANIC_CARBON = "organicCarbon";
	private static final String SOIL_PH = "ph";
	private static final String ORGANIC_MATTER = "organicMatter";
	private static final String WATER_CONTENT_FIELD_CAPACITY = "waterContentFieldCapacity";
	private static final String TAXONOMY = "taxonomy";
	private static final String WATER_CAPACITY_WILT_POINT = "waterCapacityWiltPoint";
	// private static final String PLOT_CATEGORIES = "categories";
	// private static final String PLOT_DATA = "data";
	private Log log = LogFactory.getLog(this.getClass());

	public SoilPropertyDao() {
		super(SoilProperty.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Map<Integer, Map<Double, Double>> getSoilDistribution(
			Integer soilId, Integer regionId, Integer categoryId) {
		// Soil distribution map
		Map<Integer, Map<Double, Double>> distribution = new LinkedHashMap<Integer, Map<Double, Double>>();
		// Point map
		Map<Double, Double> pointMap = new LinkedHashMap<Double, Double>();

		StringBuffer q = new StringBuffer(
				"select r.longitude, r.latitude from " + entityClass.getName())
				.append(" r where r.station.region.id =:region")
				.append(" and r.property.id =:property")
				.append(" and r.soil.id =:soil");

		Query query = entityManager.createQuery(q.toString());

		query.setParameter("property", categoryId);
		query.setParameter("region", regionId);
		query.setParameter("soil", soilId);
		List<Object[]> results = query.getResultList();
		// List<Object[]> results = query.getResultList();
		// for (Object[] result : results) {
		// System.out.println("Longitude: " + result[0] + ", Latitude: " +
		// result[1]);
		// }
		Object[] point;
		for (Iterator<Object[]> iterator = results.iterator(); iterator
				.hasNext();) {
			// Get the point
			point = iterator.next();
			// Add the point to the point map
			pointMap.put(Double.valueOf((String) point[0]),
					Double.valueOf((String) point[1]));
		}
		// Add all the points to the point distribution map
		distribution.put(soilId, pointMap);
		return distribution;
	}

	@Override
	public Map<Integer, Map<Double, Double>> getSoilDistribution(
			List<Integer> soilIds, Integer regionId, Integer categoryId) {
		// Soil distribution map
		Map<Integer, Map<Double, Double>> distribution = new LinkedHashMap<Integer, Map<Double, Double>>();

		if (soilIds != null && !soilIds.isEmpty()) {
			// For each soil texture id, retrieve the corresponding soil points
			// from the particular region and property category
			for (Integer soil : soilIds) {
				this.getSoilDistribution(soil, regionId, categoryId);
				// Add all the points to the point distribution map
				distribution.putAll(this.getSoilDistribution(soil, regionId,
						categoryId));
			}
		}

		return distribution;
	}

	@Override
	public Map<String, Object> getSoilFeaturesByCountry(Integer propertyId,
			Integer countryId, Boolean continent) {

		return this.getSoilGeoJson(
				new ArrayList<Integer>(Arrays.asList(propertyId)), countryId,
				continent);
	}

	@Override
	public Map<String, Object> getSoilFeaturesByRegions(Integer propertyId,
			List<Integer> subregions) {
		// The features
		List<FeaturePoint> features = new LinkedList<FeaturePoint>();
		// The soil features map
		Map<String, Object> soilFeatures = new LinkedHashMap<String, Object>();
		// r.station.region.id
		StringBuffer q = new StringBuffer("from " + entityClass.getName())
				.append(" r where r.station.region.id in (:regions)").append(
						" and r.property.id =:property");

		Query query = entityManager.createQuery(q.toString());

		query.setParameter("property", propertyId);
		query.setParameter("regions", subregions);

		List<SoilProperty> results = query.getResultList();
		SoilProperty soilProperty;
		GeometryPoint geometry;
		// FeatureProperty property;
		Map<String, Object> properties = new LinkedHashMap<String, Object>();
		for (Iterator<SoilProperty> iterator = results.iterator(); iterator
				.hasNext();) {
			soilProperty = iterator.next();
			// Create the feature geometry
			geometry = new GeometryPoint(new LinkedList<Double>(
					soilProperty.getCoordinates()));
			// Create the feature properties
			// TODO Add the soil properties
			// properties.put(soilProperty.getProperty().getName(),
			// soilProperty.getPropertyValue());
			properties.put(SOIL_NAME, soilProperty.getSoil().getName());
			properties.put(STATION_NAME, soilProperty.getStation().getName());
			properties.put(REGION_NAME, soilProperty.getStation().getRegion()
					.getName());

			// property = new FeatureProperty(soilProperty.getSoil().getName(),
			// soilProperty.getWaterCFCapacity(),
			// soilProperty.getAvailableSoilWater(),
			// soilProperty.getBulkDensity(), soilProperty.getPh(),
			// soilProperty.getStation().getName(),
			// soilProperty.getWaterCWpoint(), soilProperty.getDepth(),
			// soilProperty.getStation().getRegion().getName());
			// Add the feature to the feature list
			features.add(new FeaturePoint(FEATURES_TYPE, geometry, properties));
		}
		soilFeatures.put(GEOJSON_KEY_TYPE, GEOJSON_VALUE_FEATURE_COLLECTION);
		// Add the feature to the feature collection
		soilFeatures.put(GEOJSON_KEY_FEATURES, features);
		return soilFeatures;
	}

	@Override
	public Map<String, Object> getSoilFeaturesByRegion(Integer propertyId,
			Integer subregion) {

		return this.getSoilFeaturesByRegions(propertyId,
				new ArrayList<Integer>(Arrays.asList(subregion)));
	}

	@Override
	public Map<String, Object> getSoilGeoJson(List<Integer> propertyIds,
			Integer countryId, Boolean continent) {

		// TODO Use the projection class to query the properties and geometry
		// Initialize the features list with new LinkedList to keep the desired
		// sorting
		List<FeaturePoint> features = new LinkedList<FeaturePoint>();
		// Initialize the soil features map with new LinkedHashMap()
		Map<String, Object> soilFeatures = new LinkedHashMap<String, Object>();
		// TODO Should get the region name not the country name
		// r.station.region.parent.id
		StringBuffer q;
		if (continent)
			q = new StringBuffer("from " + entityClass.getName())
					.append(" r where r.region.parent.parent.parent.parent.id =:region");
		else
			q = new StringBuffer("from " + entityClass.getName())
					.append(" r where r.region.parent.id =:region").append(" or r.station.region.region.parent.id=:region");

		q.append(" and r.continent =:continent");
		// q = new StringBuffer("from " + entityClass.getName()).append(
		// " r where r.station.region.parent.id =:region").append(
		// " or r.station.region.parent.parent.id =:region");
		// }

		Query query = entityManager.createQuery(q.toString());

		query.setParameter("continent", continent);
		query.setParameter("region", countryId);
		// TODO Remove the station id
		// query.setParameter("station", 1);

		List<SoilProperty> results = query.getResultList();
		// log.info("# of soil properties: " + results.size());
		SoilProperty soilProperty;
		GeometryPoint geometry;
		// int count = 0;
		// FeatureProperty property;
		Map<String, Object> properties = new LinkedHashMap<String, Object>();
		// Create a decimal format
		DecimalFormat df = new DecimalFormat("#.##");
		if (results != null) {
			log.info(results.size());
			for (Iterator<SoilProperty> iteratorProperty = results.iterator(); iteratorProperty
					.hasNext();) {
				soilProperty = iteratorProperty.next();
				// log.info("soil properties: " + soilProperty.toString());

				// Create the feature geometry
				geometry = new GeometryPoint(soilProperty.getCoordinates());
				// Add soil properties
				properties.put(AVAILABLE_SOIL_WATER,
						soilProperty.getAvailableSoilWater());
				properties.put(BULK_DENSITY, soilProperty.getBulkDensity());
				properties.put(CATION_EXCHANGE,
						soilProperty.getCationExchange());
				properties.put(SOIL_DEPTH, soilProperty.getDepth());
				properties.put(ORGANIC_CARBON, soilProperty.getOrganicCarbon());
				properties.put(ORGANIC_MATTER, soilProperty.getOrganicMatter());
				properties.put(SOIL_PH, soilProperty.getPh());
				properties.put(TAXONOMY, soilProperty.getTaxnomy());
				properties.put(WATER_CONTENT_FIELD_CAPACITY,
						soilProperty.getWaterCFCapacity());
				properties.put(WATER_CAPACITY_WILT_POINT,
						soilProperty.getWaterCWpoint());

				properties.put(SOIL_TEXTURE_CLAY, soilProperty.getClay());

				properties.put(SOIL_TEXTURE_SILT, soilProperty.getSilt());

				properties.put(SOIL_TEXTURE_SAND, soilProperty.getSand());
			
				properties.put(SOIL_LAT,df.format( soilProperty.getLatitude()));
				properties.put(SOIL_LNG, df.format(soilProperty.getLongitude()));

				if (continent) {

					properties.put(COUNTRY_NAME, soilProperty.getRegion()
							.getParent().getParent().getName());

					properties.put(STATE_NAME, soilProperty.getRegion()
							.getParent().getName());

					properties.put(MUNICIPALITY_NAME, soilProperty.getRegion()
							.getName());
					properties.put(STATION_NAME, soilProperty.getRegion()
							.getName());
				} else {
					properties.put(COUNTRY_NAME, soilProperty.getRegion().getParent().getName());
					properties.put(STATION_NAME, soilProperty.getRegion().getName());

				}

				if (soilProperty.getSoil() != null) {
					properties.put(FEATURE_ID, soilProperty.getId() + "_"
							+ soilProperty.getSoil().getName());
					properties.put(FEATURE_NAME, soilProperty.getSoil()
							.getName());

					properties.put(SOIL_CODE, soilProperty.getSoil().getCode());

					properties.put(FEATURE_COLOR, soilProperty.getSoil()
							.getColor());
					properties.put(SOIL_NAME, soilProperty.getSoil().getName());
				}

				properties.put(FEATURE_TYPE, FeatureType.SOIL.toString());

				// TODO Add plot data to properties for each soil feature
				// Map<environment,List,<probability>>
				// Get the data map for the soil type

				// if (soilProperty.getSoil() != null)
				// properties.put(PLOT_DATA,
				// soilDataMap.get(soilProperty.getSoil().getCode()));

				// TODO Add the categories
				// List<Sowing dates>
				// properties.put(PLOT_CATEGORIES, categories);

				// log.info("Adding the feature..." + properties.size());
				// Add the feature to the feature list
				features.add(new FeaturePoint(FEATURES_TYPE, geometry,
						properties));
				// Re initialize the properties map
				properties = new LinkedHashMap<String, Object>();

			}

		}

		// Add the results to the soil features map
		// The soil features map includes features for soil textures and weather
		// stations
		soilFeatures.put(GEOJSON_KEY_TYPE, GEOJSON_VALUE_FEATURE_COLLECTION);
		// Add the feature to the feature collection
		soilFeatures.put(GEOJSON_KEY_FEATURES, features);
		return soilFeatures;
	}
}
