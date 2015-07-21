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

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.cgiar.dapa.ccafs.tpe.dao.IStationDao;
import org.cgiar.dapa.ccafs.tpe.entity.Region;
import org.cgiar.dapa.ccafs.tpe.entity.Station;
import org.cgiar.dapa.ccafs.tpe.geojson.FeaturePoint;
import org.cgiar.dapa.ccafs.tpe.geojson.GeometryPoint;
import org.cgiar.dapa.ccafs.tpe.projection.LatLng;

/**
 * This class provides the implementations for the station dao interface
 * 
 * @author NMATOVU
 *
 */
@SuppressWarnings("unchecked")
public class StationDao extends GenericDao<Station, Integer> implements
		IStationDao {

	public StationDao() {
		super(Station.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Station> getStationsByRegion(Integer regionId) {
		StringBuffer q = new StringBuffer("from " + entityClass.getName())
				.append(" r where r.region.id =:region").append(
						" order by r.name");
		Query query = entityManager.createQuery(q.toString());
		query.setParameter("region", regionId);

		return query.getResultList();
	}

	@Override
	public Map<Integer, Map<Double, Double>> getStationsPoints(Integer regionId) {

		// Soil distribution map
		Map<Integer, Map<Double, Double>> stationsMap = new LinkedHashMap<Integer, Map<Double, Double>>();
		// Point map
		Map<Double, Double> pointMap = new LinkedHashMap<Double, Double>();

		StringBuffer q = new StringBuffer(
				"select r.id, r.longitude, r.latitude from "
						+ entityClass.getName())
				.append(" r where r.region.id =:region");

		Query query = entityManager.createQuery(q.toString());
		query.setParameter("region", regionId);

		List<Object[]> results = query.getResultList();
		// List<Object[]> results = query.getResultList();
		// for (Object[] result : results) {
		// System.out.println("Id: " + result[0] + ", Longitude: " +
		// result[1]);
		// }
		Object[] point;
		for (Iterator<Object[]> iterator = results.iterator(); iterator
				.hasNext();) {
			// Get the point
			point = iterator.next();
			// Add the point to the point map
			pointMap.put(Double.valueOf((String) point[1]),
					Double.valueOf((String) point[2]));
			stationsMap.put(Integer.valueOf((String) point[0]), pointMap);
			pointMap = new LinkedHashMap<Double, Double>();
		}

		return stationsMap;
	}

	@Override
	public Map<String, List<Station>> getStationPerRegion(List<Integer> regions) {

		// Map<region,List<station>>
		Map<String, List<Station>> stationsMap = new LinkedHashMap<String, List<Station>>();
		// List of stations
		List<Station> stations = new LinkedList<Station>();

		StringBuffer q;
		Query query;
		Integer id;
		if (regions != null && !regions.isEmpty()) {
			for (Iterator<Integer> iterator = regions.iterator(); iterator
					.hasNext();) {
				id = iterator.next();
				q = new StringBuffer("from " + entityClass.getName())
						.append(" r where r.region.id =:id");
				query = entityManager.createQuery(q.toString());
				query.setParameter("id", id);
				stations = query.getResultList();
				stationsMap.put(getRegionById(id).getAlphaISO(), stations);
			}
		}

		return stationsMap;
	}

	/**
	 * Retrieves the region by the specified id
	 * 
	 * @param id
	 *            region id
	 * @return region
	 */
	private Region getRegionById(Integer id) {
		StringBuffer q = new StringBuffer("from " + Region.class.getName())
				.append(" r where r.id =:id");
		Query query = entityManager.createQuery(q.toString());
		query.setParameter("id", id);

		List<Region> results = query.getResultList();
		if (results.isEmpty()) {
			return null;
		} else {
			return (Region) results.get(0);
		}

		// return (Region) query.getSingleResult();
	}

	@Override
	public Map<String, List<LatLng>> getStationByRegion(List<Integer> regions) {
		// Map<region,List<station>>
		Map<String, List<LatLng>> stationsMap = new LinkedHashMap<String, List<LatLng>>();
		// List of station projections
		List<LatLng> stations = new LinkedList<LatLng>();

		StringBuffer q;
		Query query;
		Integer id;
		if (regions != null && !regions.isEmpty()) {
			for (Iterator<Integer> iterator = regions.iterator(); iterator
					.hasNext();) {
				id = iterator.next();
				q = new StringBuffer(
						"select NEW org.cgiar.dapa.ccafs.tpe.projection.LatLng(r.name, r.number, r.region.name, r.latitude, r.longitude) from "
								+ entityClass.getName())
						.append(" r where r.region.id =:id");
				query = entityManager.createQuery(q.toString());
				query.setParameter("id", id);
				stations = query.getResultList();
				stationsMap.put(getRegionById(id).getAlphaISO(), stations);
			}
		}

		return stationsMap;
	}

	@Override
	public Map<String, Object> getStationGeoJson(Integer countryId) {
		Map<String, Object> stationGeoJSON = new LinkedHashMap<String, Object>();
		List<FeaturePoint> stationFeatures = new LinkedList<FeaturePoint>();
		GeometryPoint stationGeometry;
		// FeatureProperty stationProperty;
		Map<String, Object> properties = new LinkedHashMap<String, Object>();

		StringBuffer q = new StringBuffer("from " + entityClass.getName())
				.append(" r where r.region.parent.id =:country");

		Query query = entityManager.createQuery(q.toString());
		query.setParameter("country", countryId);
		List<Station> results = query.getResultList();

		Station station;
		for (Iterator<Station> iterator = results.iterator(); iterator
				.hasNext();) {
			station = iterator.next();
			stationGeometry = new GeometryPoint(station.getCoordinates());
			properties.put(STATION_ID, station.getId());
			properties.put(STATION_NAME, station.getName());
			// properties.put(STATION_NUMBER, station.getNumber());
			properties.put(REGION_NAME, station.getRegion().getName());
			properties.put(STATION_COORDINATES, station.getCoordinates());

			stationFeatures.add(new FeaturePoint(FEATURES_TYPE,
					stationGeometry, properties));
			// Empty the station map or initalise it
			properties = new LinkedHashMap<String, Object>();
		}
		stationGeoJSON.put(GEOJSON_KEY_TYPE, GEOJSON_VALUE_FEATURE_COLLECTION);
		// Add the feature to the feature collection
		stationGeoJSON.put(GEOJSON_KEY_FEATURES, stationFeatures);

		return stationGeoJSON;
	}

}
