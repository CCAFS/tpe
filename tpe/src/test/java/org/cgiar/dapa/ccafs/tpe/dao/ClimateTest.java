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

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.cgiar.dapa.ccafs.tpe.entity.Climate;
import org.cgiar.dapa.ccafs.tpe.entity.Station;

/**
 * This class contains tests for the climate dao and service methods
 * 
 * @author NMATOVU
 *
 */
public class ClimateTest extends BaseTest {
	/**
	 * This method tests for the retrieval of the climate for the specified
	 * category from the given weather stations located in the specified region
	 * for a specific year.
	 */
	public void testGetClimateByStations() {
		// Instantiate the list of the climate
		List<Climate> climate = new ArrayList<Climate>();
		// The list of stations
		List<Integer> stationIds = new ArrayList<Integer>(Arrays.asList(1, 2,
				3, 4));
		// The category id
		Integer categoryId = 1;
		// The year
		String year = "2012";
		// Retrieve the climate by the stations
		climate = tpeService.getClimateByStations(stationIds, categoryId, year);

		assertNotNull(climate);
		assertEquals(0, climate.size());
	}

	/**
	 * This method tests for the retrieval of the climate for the specified
	 * category from all weather stations located in the specified region for a
	 * year
	 */
	public void testGetClimateByRegions() {
		// Instantiate the list of the climate
		List<Climate> climate = new ArrayList<Climate>();
		// Category id
		Integer categoryId = 1;
		// The year
		String year = "2012";
		// The list of regions
		List<Integer> regionIds = new ArrayList<Integer>(Arrays.asList(1, 2, 3,
				4));
		// Retrieve the climate by the regions
		climate = tpeService.getClimateByRegions(regionIds, categoryId, year);

		assertNotNull(climate);
		assertEquals(0, climate.size());
	}

	/**
	 * This tests for the retrieval of climate data for a given range for a
	 * particular category from a a given region.
	 */
	public void testGetClimateByRange() {

		// Climate category
		Integer categoryId = 1;
		// Region
		Integer regionId = 1;
		Date fromDate = null;
		Date toDate = null;
		try {
			fromDate = sdf.parse("20/02/2012");
			toDate = sdf.parse("20/02/2012");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Retrieve the climate
		List<Climate> climate = tpeService.getClimate(fromDate, toDate,
				regionId, categoryId);
		assertNotNull(climate);
		assertEquals(0, climate.size());
	}

	/**
	 * This tests for retrieval of weather stations by climate variables
	 */
	public void testGetStationsByClimate() {
		// Climate category
		Integer categoryId = 1;
		// Region
		Integer regionId = 1;
		Date fromDate = null;
		Date toDate = null;
		try {
			fromDate = sdf.parse("20/02/2012");
			toDate = sdf.parse("20/02/2012");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Retrieve the regions
		List<Station> stations = tpeService.getStationsByClimate(fromDate,
				toDate, categoryId, regionId);
		assertNotNull(stations);
		assertEquals(0, stations.size());
	}

}
