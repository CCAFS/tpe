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

import org.cgiar.dapa.ccafs.tpe.entity.Cultivar;

public interface ICultivarDao extends IGenericDao<Cultivar, Integer> {

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
	 * @return
	 */
	List<String> getYearsByCultivar(Integer cultivarId);
}
