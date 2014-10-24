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

import org.cgiar.dapa.ccafs.tpe.entity.SoilProperty;

/**
 * The interface that defines the soil property DAO methods
 * 
 * @author NMATOVU
 *
 */
public interface ISoilPropertyDao extends IGenericDao<SoilProperty, Integer> {
	/**
	 * Retrieves the soil distribution (latitude and longitude points) for a
	 * specified soil texture, region and property category.
	 * 
	 * @param soilId
	 *            soil texture id
	 * @param regionId
	 *            region or country id
	 * @param categoryId
	 *            property category id
	 * @return soil distribution
	 */
	Map<Integer, Map<Double, Double>> getSoilDistribution(Integer soilId,
			Integer regionId, Integer categoryId);

	/**
	 * Retrieves the soil distribution (latitude and longitude points) for
	 * different specified soil textures, region and property category.
	 * 
	 * @param soilIds
	 *            soil texture ids
	 * @param regionId
	 *            region id
	 * @param categoryId
	 *            soil property category id
	 * @return soil distribution
	 */
	Map<Integer, Map<Double, Double>> getSoilDistribution(
			List<Integer> soilIds, Integer regionId, Integer categoryId);
}
