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

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This class tests for the tpe (phenology and growth) DAO and service methods
 * 
 * @author NMATOVU
 *
 */
public class TPETest extends BaseTest {

	/**
	 * Retrieves Target Population Environment regions and their corresponding
	 * GEO points (lat & lon) based on the specified crop cultivar, region
	 * (country), sowing window and scenario for all soil textures
	 */
	public void testGetRegions() {
		// The map of regions. Map<RegionISO,Map<Latitude,Longitude>>
		Map<String, Map<Double, Double>> regions = new LinkedHashMap<String, Map<Double, Double>>();

		Integer cultivarId = 1;
		// TODO To consider the region ISO code
		Integer regionId = 1;
		Integer swindowId = 1;
		String year = "2012";
		// Retrieve the regions.
		regions = tpeService.getTPERegions(cultivarId, regionId, swindowId,
				year);
		assertNotNull(regions);
		assertEquals(0, regions.size());

		Integer scenarioId = 1;
		// TODO Use region ISO code
		// Retrieve the regions. Include the scenario
		regions = tpeService.getTPERegions(cultivarId, regionId, swindowId,
				year, scenarioId);
		assertNotNull(regions);
		assertEquals(0, regions.size());
	}

	/**
	 * Tests for the retrieval of the TPE soils and the corresponding yield for
	 * the specified cultivar, country, year, sowing window and scenario
	 */
	public void testGetTPESoil() {
		// The map of soil. Map<SoilCode,Map<regionISO,yield>>
		Map<String, Map<String, Double>> tpeSoils = new LinkedHashMap<String, Map<String, Double>>();

		Integer cultivarId = 1;
		Integer regionId = 1;
		Integer swindowId = 1;
		String year = "2012";
		// Retrieve the tpe soil map.
		tpeSoils = tpeService.getTPESoil(cultivarId, regionId, swindowId, year);
		assertNotNull(tpeSoils);
		assertEquals(0, tpeSoils.size());

		Integer scenarioId = 1;
		// Retrieve the tpe soil. Include the scenario
		tpeSoils = tpeService.getTPESoil(cultivarId, regionId, swindowId, year,
				scenarioId);
		assertNotNull(tpeSoils);
		assertEquals(0, tpeSoils.size());
	}

	/**
	 * Tests for the retrieval of the TPE sowing windows and the corresponding
	 * region and yield for the specified cultivar, country, year, sowing window
	 * and scenario
	 */
	@SuppressWarnings("unused")
	public void testGetTPESowingWindow() {
		// The map of sowing window. Map<window,Map<regionISO,yield>>
		Map<String, Map<String, Double>> tpeWindow = new LinkedHashMap<String, Map<String, Double>>();
	}

}
