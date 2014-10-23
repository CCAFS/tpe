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
import java.util.List;

import org.apache.log4j.Logger;
import org.cgiar.dapa.ccafs.tpe.entity.Crop;
import org.cgiar.dapa.ccafs.tpe.entity.Cultivar;
import org.cgiar.dapa.ccafs.tpe.entity.WindowSowing;

/**
 * The class that tests for the crops, cultivar, sowing window methods which are
 * implemented in the AgristatService class
 * 
 * @author NOAH
 * 
 */
public class CropTest extends BaseTest {

	private Logger log = Logger.getLogger(this.getClass());

	/**
	 * The method that tests getAllCrops method
	 */
	public void testGetAllCrops() {
		log.info("About to test get all cropS");
		// List of all the existing crops in the database
		List<Crop> crops = tpeService.getAllCrops();
		assertNotNull(crops);
		assertTrue(crops.size() > 0);
		log.info("Available crops: " + crops.size());

	}

	/**
	 * Gets the crop by its primary key
	 */
	public void testGetCropById() {
		// Crop variable
		Crop crop;
		// Crop id.
		Integer cropId = 1;
		// Get the crop from the database
		crop = tpeService.getCropById(cropId);
		// Assert
		assertNotNull(crop);
		assertEquals(cropId, crop.getId());

	}

	/**
	 * Gets all the cultivars for the specified crop id.
	 */
	public void testGetCultivarsByCrop() {
		// Instantiate a new list of cultivars.
		List<Cultivar> cultivars = new ArrayList<Cultivar>();
		// The crop id. Get the crop id of rice.
		// Crop id
		Integer cropId = 1;
		// Retrieve the cultivars for the specified crop id
		cultivars = tpeService.getCultivarsByCrop(cropId);
		assertNotNull(cultivars);
		assertEquals(0, cultivars.size());

	}

	/**
	 * Gets the number of years based on the simulation model for the specified
	 * cultivar
	 */
	public void getYearsByCultivars() {
		List<String> years = new ArrayList<String>();
		Integer cultivarId = 1;
		years = tpeService.getYearsByCultivar(cultivarId);
		assertNotNull(years);
		assertEquals(0, years.size());
	}

	/**
	 * Retrieves the window sowing records by the specfied cultivar id
	 */
	public void testGetWindowSowingByCultivar() {
		// List of window sowing records
		List<WindowSowing> windows = new ArrayList<WindowSowing>();
		Integer cultivarId = 1;
		// Get windows
		windows = tpeService.getWindowSowingByCultivar(cultivarId);
		assertNotNull(windows);
		assertEquals(0, windows.size());
	}

}
