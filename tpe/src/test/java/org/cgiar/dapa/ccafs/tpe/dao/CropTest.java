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

import java.util.List;

import org.apache.log4j.Logger;
import org.cgiar.dapa.ccafs.tpe.entity.Crop;

/**
 * The class that tests for the crops query methods which are implemented in the
 * AgristatService class
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
		log.info("About to test crop queries");
		// Query all the existing crops in the database
		List<Crop> crops = tpeService.getAllCrops();
		assertNotNull(crops);
		assertTrue(crops.size() > 0);
		log.info("Available crops: " + crops.size());
	}
}
