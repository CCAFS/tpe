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

import java.util.Map;

import org.cgiar.dapa.ccafs.tpe.entity.PhenologyGrowth;

/**
 * This interface defines the crop phenology and growth dao methods and extends
 * the base generic dao interface
 * 
 * @author NMATOVU
 *
 */
public interface IPhenologyGrowthDao extends
		IGenericDao<PhenologyGrowth, Integer> {

	// TODO To use the region ISO code
	/**
	 * Retrieves the map of TPE regions (Map<RegionISO,Map<Latitude,Longitude>>)
	 * for the specified crop cultivar, region (country) and sowing window in
	 * the given year
	 * 
	 * @param cultivarId
	 *            cultivar id
	 * @param regionId
	 *            country id
	 * @param swindowId
	 *            sowing window id
	 * @param year
	 *            year
	 * @return Map of TPE regions
	 */
	Map<String, Map<Double, Double>> getTPERegions(Integer cultivarId,
			Integer regionId, Integer swindowId, String year);

	/**
	 * Retrieves the map TPE regions (Map<RegionISO,Map<Latitude,Longitude>>)
	 * for the specified crop cultivar, region (country), sowing window, and
	 * scenario in the given year
	 * 
	 * @param cultivarId
	 *            the crop cultivar id
	 * @param regionId
	 *            country id
	 * @param swindowId
	 *            the sowing window id
	 * @param year
	 *            the year
	 * @param scenarioId
	 *            the scenario (rainfed or irrigated)
	 * @return Map of TPE regions
	 */
	Map<String, Map<Double, Double>> getTPERegions(Integer cultivarId,
			Integer regionId, Integer swindowId, String year, Integer scenarioId);

	/**
	 * Retrieves the tpe soil textures with thier corresponding regions and crop
	 * yield from the specified country, crop cultivar, sowing window in the
	 * given year.
	 * 
	 * @param cultivarId
	 *            the crop cultivar id
	 * @param regionId
	 *            the country or region id
	 * @param swindowId
	 *            the sowing window
	 * @param year
	 *            the year
	 * @return tpe soil and corresponding regions and yield
	 */
	Map<String, Map<String, Double>> getTPESoil(Integer cultivarId,
			Integer regionId, Integer swindowId, String year);

	/**
	 * Retrieves the tpe soil textures with thier corresponding regions and crop
	 * yield from the specified country, crop cultivar, sowing window in the
	 * given year.
	 * 
	 * @param cultivarId
	 *            the crop cultivar id
	 * @param regionId
	 *            the region or country id
	 * @param swindowId
	 *            the sowing window id
	 * @param year
	 *            the year
	 * @param scenarioId
	 *            the scenario id
	 * @return tpe soil map with corresponding regions and yield
	 */
	Map<String, Map<String, Double>> getTPESoil(Integer cultivarId,
			Integer regionId, Integer swindowId, String year, Integer scenarioId);
}
