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
package org.cgiar.dapa.ccafs.tpe.service;

import java.util.List;

import org.cgiar.dapa.ccafs.tpe.entity.Crop;
import org.cgiar.dapa.ccafs.tpe.entity.Cultivar;
import org.cgiar.dapa.ccafs.tpe.entity.Region;
import org.cgiar.dapa.ccafs.tpe.entity.WindowSowing;

/**
 * This interface defines the TPE service methods
 * 
 * @author NMATOVU
 *
 */
public interface ITPEService {
	/**
	 * This retrieves all the crop records from the database
	 * 
	 * @return crops
	 */
	List<Crop> getAllCrops();

	/**
	 * Retrieves the crop record from the database with the specified id
	 * 
	 * @param cropId
	 * @return crop
	 */
	Crop getCropById(Integer cropId);

	/**
	 * Retrieves cultivars from the database for the specified crop id
	 * 
	 * @param cropId
	 *            the crop id to retrieve the cultivars
	 * @return crop cultivars
	 */
	List<Cultivar> getCultivarsByCrop(Integer cropId);

	/**
	 * Retrieves the number of years for the specified crop cultivar based on
	 * the simulation model
	 * 
	 * @param cultivarId
	 * @return years
	 */
	List<String> getYearsByCultivar(Integer cultivarId);

	/**
	 * Retrieves all the window sowing for teh specified crop cultivar id
	 * 
	 * @param cultivarId
	 *            the crop cultivar id to retrieve the sowing windows
	 * @return window sowing
	 */
	List<WindowSowing> getWindowSowingByCultivar(Integer cultivarId);

	/**
	 * Retrieves all the regions of the category country from the database
	 * 
	 * @return countries
	 */
	List<Region> getCountries();

	/**
	 * Retrieves all the subregions (COUNTRY, STATE, PROVINCE OR DISTRICT) for
	 * the specified region id (parent region) or country id.
	 * 
	 * @param countryId
	 *            country or parent id
	 * @return subregions
	 */
	List<Region> getSubregionsByCountry(Integer countryId);

	/**
	 * Gets the region by the specified id
	 * 
	 * @param regionId
	 * @return region
	 */
	Region getRegionById(Integer regionId);

}
