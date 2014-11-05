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
package org.cgiar.dapa.ccafs.tpe.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.cgiar.dapa.ccafs.tpe.entity.Station;
import org.cgiar.dapa.ccafs.tpe.projection.LatLng;

/**
 * This class contains tests for the weather station dao and service methods
 * 
 * @author NMATOVU
 *
 */
public class StationTest extends BaseTest {
	/**
	 * This method tests for the retrieval of the weather stations located in
	 * the specified region.
	 */
	public void testGetStationsByRegion() {
		// Instantiate the list of the weather stations
		List<Station> stations = new ArrayList<Station>();
		// The region id
		Integer regionId = 1;
		// Retrieve the stations by the region
		stations = tpeService.getStationsByRegion(regionId);

		assertNotNull(stations);
		assertEquals(0, stations.size());
	}

	/**
	 * Retrieves the stations and their corresponding points from the specified
	 * region
	 */
	public void testGetStationPoints() {
		// The region id
		Integer regionId = 1;
		// Map<station,Map<lat.lng>>
		Map<Integer, Map<Double, Double>> stations = tpeService
				.getStationsPoints(regionId);
		assertNotNull(stations);
		assertEquals(0, stations.size());
	}

	/**
	 * This tests for the retrieval of the station by its primary key or id
	 */
	public void testGetStationById() {
		// The id to retrieve the station
		Integer stationId = 1;
		// Get the station
		Station station = tpeService.getStationById(stationId);
		assertNotNull(station);
		assertEquals(stationId, station.getId());
	}

	/**
	 * Tests for the retrieval of stations per region
	 */
	public void testGetStationByRegion() {
		// List of region ids (sub regions may states)
		List<Integer> regions = new ArrayList<Integer>(Arrays.asList(1, 2));
		// Map<regionISO, List<LatLng>>
		// TODO To use region ISO
		Map<String, List<LatLng>> stationByRegions = tpeService
				.getStationByRegion(regions);
		assertNotNull(stationByRegions);
		assertEquals(0, stationByRegions.size());
		// Map<regionISO, List<station>>
		Map<String, List<Station>> stationPerRegions = tpeService
				.getStationPerRegion(regions);
		assertNotNull(stationPerRegions);
		assertEquals(0, stationPerRegions.size());
	}

	public void testGetStationFeatures() {

		// The country id property
		Integer countryId = 4;

		Map<String, Object> stationFeatures = tpeService
				.getStationGeoJson(countryId);
		assertNotNull(stationFeatures);
		assertEquals(2, stationFeatures.size());
	}
}
