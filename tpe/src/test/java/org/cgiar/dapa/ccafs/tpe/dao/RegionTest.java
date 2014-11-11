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
import org.cgiar.dapa.ccafs.tpe.entity.Region;

/**
 * This class contains tests for the region DAO and service methods
 * 
 * @author NMATOVU
 *
 */
public class RegionTest extends BaseTest {

	@SuppressWarnings("unused")
	private Logger log = Logger.getLogger(this.getClass());

	/**
	 * Retrieves all the regions of category country from the database
	 */
	public void testGetCountries() {
		// List of countries
		List<Region> countries = new ArrayList<Region>();
		countries = tpeService.getCountries();
		assertNotNull(countries);
	//	assertEquals(0, countries.size());
	}

	/**
	 * Retrieves all the subregions for the specified country (parent region).
	 * It does not consider the category of the subregions to retrieve from the
	 * database
	 */
	public void testGetSubregion() {
		// TODO Consider the category of the subregions to retrieve (state,
		// provinces, etc)
		// List of subregions
		List<Region> subregions = new ArrayList<Region>();
		Integer countryId = 1;
		subregions = tpeService.getSubregionsByCountry(countryId);
		assertNotNull(subregions);
	//	assertEquals(0, subregions.size());
	}

	/**
	 * Retrieves the region by its id
	 */
	public void testGetRegionById() {
		Integer countryId = 1;
		// The country
		// Retrieve the region by id
		Region country = tpeService.getRegionById(countryId);
		assertNotNull(country);
		assertEquals(countryId, country.getId());
		log.info(country);
	}

}
