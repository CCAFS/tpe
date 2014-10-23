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

import org.cgiar.dapa.ccafs.tpe.entity.Climate;

/**
 * This interface defines the DAO methods for the climate in the model
 * 
 * @author NMATOVU
 *
 */
public interface IClimateDao extends IGenericDao<Climate, Integer> {

	/**
	 * Retrieves the climate records for the specified category from the
	 * database from the specified weather stations located from particular
	 * region(s) for the specified year.
	 * 
	 * @param stationIds
	 *            the ids of the stations from which to retrieve the climate
	 * 
	 * @param categoryId
	 *            the category id of the climate to retrieve
	 * 
	 * @param year
	 *            the year for the climate to retrieve
	 * @return climate
	 */
	List<Climate> getClimateByStations(List<Integer> stationIds,
			Integer categoryId, String year);

	/**
	 * Retrieves all the climate information for the specified category from the
	 * database from all the weather stations from the specified regions for the
	 * specified year.
	 * 
	 * @param regionIds
	 *            primary keys of the regions from which to retrieve the climate
	 *            data
	 * @param categoryId
	 *            the category id of the climate to retrieve
	 * @param year
	 *            the year for the climate to retrieve
	 * @return climate
	 */
	List<Climate> getClimateByRegions(List<Integer> regionIds,
			Integer categoryId, String year);
}
