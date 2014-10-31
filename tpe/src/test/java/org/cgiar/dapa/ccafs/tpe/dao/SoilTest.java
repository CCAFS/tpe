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

import org.cgiar.dapa.ccafs.tpe.entity.Soil;

/**
 * This class tests for the soil DAO and service methods
 * 
 * @author NMATOVU
 *
 */
public class SoilTest extends BaseTest {

	/**
	 * Retrieves categories based on the specified entity name or class name
	 */
	public void testGetSoil() {
		Integer soilId = 1;
		// Get soil texture by id
		Soil texture = tpeService.getSoilById(soilId);
		assertNotNull(texture);
		assertEquals(soilId, texture.getId());
	}

	/**
	 * This tests for the retrieval of the soil texture distribution
	 */
	public void testGetSoilDistribution() {
		// TODO To use soil code instead of soil id
		// The soil property id
		Integer categoryId = 1;
		// The region id
		Integer regionId = 1;
		// The soil id
		Integer soilId = 1;
		List<Integer> soilIds = new ArrayList<Integer>(
				Arrays.asList(1, 2, 3, 4));
		Map<Integer, Map<Double, Double>> distribution = tpeService
				.getSoilDistribution(soilId, regionId, categoryId);
		assertNotNull(distribution);
		assertEquals(0, distribution.size());

		// Retrieve soil distribution for more than one soil texture
		distribution = tpeService.getSoilDistribution(soilIds, regionId,
				categoryId);
		assertNotNull(distribution);
		assertEquals(0, distribution.size());
	}

	/**
	 * Retrieves the soil records that will be turned into GeoJson format in the
	 * struts.xml configurations
	 */
	public void testGetSoilGeoJson() {
		// The soil property id
		Integer propertyId = 1;
		// The country id property
		Integer countryId = 1;
		Map<String, Object> soilFeatures = tpeService.getSoilFeatures(
				propertyId, countryId);
	}
}
