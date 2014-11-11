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

import org.apache.log4j.Logger;
import org.cgiar.dapa.ccafs.tpe.entity.Property;
import org.cgiar.dapa.ccafs.tpe.entity.Soil;

/**
 * This class tests for the soil DAO and service methods
 * 
 * @author NMATOVU
 *
 */
public class SoilTest extends BaseTest {

	private Logger log = Logger.getLogger(this.getClass());

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
		Map<String, Object> soilFeatures = tpeService.getSoilFeaturesByCountry(
				propertyId, countryId);
		assertNotNull(soilFeatures);
		assertEquals(2, soilFeatures.size());

		List<Integer> subregions = new ArrayList<Integer>(
				Arrays.asList(1, 2, 3));
		// Retrieve the soil features for the specified sub regions
		soilFeatures = tpeService.getSoilFeaturesByRegions(propertyId,
				subregions);
		assertNotNull(soilFeatures);
		assertEquals(2, soilFeatures.size());

		Integer subregion = 1;
		// Retrieve the soil features for the specified sub region
		soilFeatures = tpeService
				.getSoilFeaturesByRegion(propertyId, subregion);
		assertNotNull(soilFeatures);
		assertEquals(2, soilFeatures.size());

		List<Integer> propertyIds = new ArrayList<Integer>(Arrays.asList(1, 2));
		// Ret()rieve soil GeoJson by a list of properties
		soilFeatures = tpeService.getSoilGeoJson(propertyIds, countryId);
		assertNotNull(soilFeatures);
		assertEquals(2, soilFeatures.size());

	}

	public void testGetSoilTextures() {
		List<Soil> textures = tpeService.getSoilTextures();
		assertNotNull(textures);
		assertEquals(5, textures.size());
	}

	public void testGetSoilProperties() {
		List<Property> properties = tpeService.getAllProperties();
		assertNotNull(properties);
		//assertEquals(3, properties.size());

		Integer propertyId = 1;
		Property property = tpeService.getPropertyById(propertyId);
		assertNotNull(property);

		Integer categoryId = 1;
		properties = tpeService.getPropertiesByCategory(categoryId);
		assertNotNull(properties);
		//assertEquals(3, properties.size());

		properties = tpeService.getSoilProperties();
		assertNotNull(properties);
		log.info("Soil Properties: " + properties.size());
	}
}
