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

import java.util.Arrays;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * This class tests for the soil DAO and service methods
 * 
 * @author NMATOVU
 *
 */
public class SoilGeoJsonTest extends BaseTest {

	private Logger log = Logger.getLogger(this.getClass());

	/**
	 * Retrieves the soil records that will be turned into GeoJson format in the
	 * struts.xml configurations
	 */
	public void testGetSoilGeoJson() {
		// The soil property id
		Integer propertyId = 1;
		// The country id property
		Integer countryId = 1;
		Map<String, Object> soilFeatures = tpeService.getSoilGeoJson(
				Arrays.asList(propertyId), countryId);
		assertNotNull(soilFeatures);
		assertEquals(2, soilFeatures.size());
		log.info(soilFeatures.size());
	}

}
