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

import org.cgiar.dapa.ccafs.tpe.entity.Category;

/**
 * This interface defines the category methods.
 * 
 * @author NMATOVU
 *
 */
public interface ICategoryDao extends IGenericDao<Category, Integer> {

	/**
	 * Retrieves all categories for the specified entity name.
	 * 
	 * @param entityName
	 * @return categories
	 */
	List<Category> getCategoriesByEntity(String entityName);

	/**
	 * Retrieves the TPE outputs
	 * 
	 * @return outputs
	 */
	List<Category> getOutputs();

}
