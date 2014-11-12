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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cgiar.dapa.ccafs.tpe.dao.ISoilPropertyDao;
import org.cgiar.dapa.ccafs.tpe.entity.SoilProperty;
import org.cgiar.dapa.ccafs.tpe.entity.Station;
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
			Integer countryId) {

		return this.getSoilGeoJson(
				new ArrayList<Integer>(Arrays.asList(propertyId)), countryId);
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
			properties.put(soilProperty.getProperty().getName(),
					soilProperty.getPropertyValue());
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
			Integer countryId) {

		log.info(propertyIds);
		log.info(countryId);
		// TODO Use the projection class to query the properties and geometry
		// Initialize the features list with new LinkedList to keep the desired
		// sorting
		List<FeaturePoint> features = new LinkedList<FeaturePoint>();
		// Initialize the soil features map with new LinkedHashMap()
		Map<String, Object> soilFeatures = new LinkedHashMap<String, Object>();
		// TODO Should get the region name not the country name
		// r.station.region.parent.id
		StringBuffer q = new StringBuffer("from " + entityClass.getName())
				.append(" r where r.station.region.parent.id =:region")
				.append(" or r.region.parent.parent.id =:region")
				.append(" and r.property.id in (:properties)");

		Query query = entityManager.createQuery(q.toString());

		query.setParameter("properties", propertyIds);
		query.setParameter("region", countryId);

		List<SoilProperty> results = query.getResultList();
		log.info("# of soil properties: " + results.size());
		SoilProperty soilProperty;
		GeometryPoint geometry;
		// FeatureProperty property;
		Map<String, Object> properties = new LinkedHashMap<String, Object>();
		if (results != null)
			for (Iterator<SoilProperty> iterator = results.iterator(); iterator
					.hasNext();) {
				soilProperty = iterator.next();
				// Create the feature geometry
				geometry = new GeometryPoint(soilProperty.getCoordinates());
				// Add soil properties
				properties.put(soilProperty.getProperty().getName(),
						soilProperty.getPropertyValue());
				properties.put(SOIL_NAME, soilProperty.getSoil().getName());
				properties.put(STATION_NAME, soilProperty.getStation()
						.getName());
				properties.put(REGION_NAME, soilProperty.getStation()
						.getRegion().getName());
				properties
						.put(FEATURE_COLOR, soilProperty.getSoil().getColor());
				properties.put(FEATURE_ID, soilProperty.getId() + "_"
						+ soilProperty.getSoil().getName());
				properties.put(FEATURE_NAME, soilProperty.getSoil().getName());
				properties.put(SOIL_PROPERTY_VALUE,
						soilProperty.getPropertyValue());
				properties.put(SOIL_PROPERTY_NAME, soilProperty.getProperty()
						.getName());
				properties.put(FEATURE_TYPE, FeatureType.SOIL.toString());

				// Add the feature to the feature list
				features.add(new FeaturePoint(FEATURES_TYPE, geometry,
						properties));
				// Re initialize the properties map
				properties = new LinkedHashMap<String, Object>();
			}
		// Query and add station features
		// The Google Map will contain both the weather stations and the soil
		// features
		q = new StringBuffer("from " + Station.class.getName()).append(
				" r where r.region.parent.id =:region").append(
				" or r.region.parent.parent.id =:region");
		query = entityManager.createQuery(q.toString());
		query.setParameter("region", countryId);

		List<Station> stations = query.getResultList();
		log.info("# of stations: " + stations.size());
		Station station;
		properties = new LinkedHashMap<String, Object>();

		if (stations != null)
			for (Iterator<Station> iterator = stations.iterator(); iterator
					.hasNext();) {
				station = iterator.next();
				// Create the feature geometry
				geometry = new GeometryPoint(station.getCoordinates());
				// Create the feature properties

				properties.put(STATION_NAME, station.getName());
				properties.put(STATION_NUMBER, station.getNumber());
				properties.put(REGION_NAME, station.getRegion().getName());
				properties.put(COUNTRY_NAME, station.getRegion().getParent()
						.getName());
				properties.put(FEATURE_ID,
						station.getId() + "_" + station.getName());
				properties.put(FEATURE_NAME, station.getName());
				properties.put(FEATURE_COLOR, STATION_COLOR_GREEN);
				properties.put(FEATURE_TYPE, FeatureType.STATION.toString());
				// property = new FeatureProperty(station.getName(),
				// station.getNumber(), station.getRegion().getName(), station
				// .getRegion().getParent().getName());
				// Add the feature to the feature list
				features.add(new FeaturePoint(FEATURES_TYPE, geometry,
						properties));
				// Re initialize the properties map
				properties = new LinkedHashMap<String, Object>();
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
