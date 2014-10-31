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

import org.cgiar.dapa.ccafs.tpe.entity.Station;
import org.cgiar.dapa.ccafs.tpe.projection.LatLng;

/**
 * This interface defines the station dao methods that are implemented by the
 * station dao class
 * 
 * @author NMATOVU
 *
 */
public interface IStationDao extends IGenericDao<Station, Integer> {

	/**
	 * Retrieves the weather stations located in the specified region.
	 * 
	 * @param regionId
	 *            region id used to query the stations
	 * @return stations
	 */
	List<Station> getStationsByRegion(Integer regionId);

	/**
	 * Retrieves stations and their corresponding location points from the
	 * specified region.
	 * 
	 * @param regionId
	 *            region id
	 * @return stations
	 */
	Map<Integer, Map<Double, Double>> getStationsPoints(Integer regionId);

	/**
	 * Retrieves the weather stations from each specified regions (sub regions
	 * such as states)
	 * 
	 * @param regions
	 *            region ids
	 * @return stations per region
	 */
	Map<String, List<Station>> getStationPerRegion(List<Integer> regions);

	/**
	 * Retrieves the staions for each of the specified region
	 * 
	 * @param regions
	 *            region ids
	 * @return stations per region
	 */
	Map<String, List<LatLng>> getStationByRegion(List<Integer> regions);
}
