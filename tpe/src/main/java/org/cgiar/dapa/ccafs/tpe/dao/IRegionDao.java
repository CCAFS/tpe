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

import org.cgiar.dapa.ccafs.tpe.entity.Region;

/**
 * RegionDao Interface class that defines region data access methods that are
 * implemented by the region dao class.
 * 
 * @author NMATOVU
 *
 */
public interface IRegionDao extends IGenericDao<Region, Integer> {
	/**
	 * Gets all the available countries from the database
	 * 
	 * @return list of countries
	 */
	List<Region> getCountries();

	/**
	 * Gets the region records from the database by the specified category
	 * (COUNTRY, STATE, PROVINCE OR DISTRICT)
	 * 
	 * @param type
	 *            region type (COUNTRY, STATE, PROVINCE OR DISTRICT)
	 * @return regions
	 */
	List<Region> getRegions(Integer category);

	/**
	 * Gets all the subregions for the specified region id from the database.
	 * 
	 * @param region
	 *            region id
	 * @return subregions
	 */
	List<Region> getSubregions(Integer region);
}
