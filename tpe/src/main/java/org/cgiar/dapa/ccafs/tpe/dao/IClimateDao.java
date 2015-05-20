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
import java.util.Map;

import org.cgiar.dapa.ccafs.tpe.entity.Climate;

/**
 * This interface defines the DAO methods for the climate in the model
 * 
 * @author NMATOVU
 *
 */
public interface IClimateDao extends IGenericDao<Climate, Long> {

	/**
	 * Retrieves the climate data for the specified country that will be
	 * returned as GeoJSON format by the action
	 * 
	 * @param countryId
	 *            the country id
	 * @param indicators
	 *            the climate indicators
	 * 
	 * @return climate GeoJSON features
	 */
	Map<String, Object> getClimateGeoJSON(Integer countryId,
			List<Integer> indicators);

	/**
	 * LIst all the years linked to the specified country id (or its sub regions
	 * and stations) from the climate table.
	 * 
	 * @param countryId
	 *            country id
	 * @return years
	 */
	List<String> getClimateYears(Integer countryId);

	/**
	 * Retrieves the climate series data from the database
	 * 
	 * @param country
	 *            the selected country id
	 * @return climate series
	 */
	Map<String, Object> getClimateSeries(Integer country);

	/**
	 * List climate records for the specified stations.
	 * 
	 * @param stationIds
	 *            list of station ids
	 * @return climate
	 */
	List<Climate> getClimateByStations(List<Integer> stationIds);

	/**
	 * Lists climate records from the database for the specified regions
	 * 
	 * @param regionIds
	 *            list of region ids
	 * @return climate
	 */
	List<Climate> getClimateByRegions(List<Integer> regionIds);
}
