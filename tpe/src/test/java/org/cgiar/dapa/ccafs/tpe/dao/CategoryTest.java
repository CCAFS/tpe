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

import org.cgiar.dapa.ccafs.tpe.entity.Category;

/**
 * This class tests for the category DAO and service methods
 * 
 * @author NMATOVU
 *
 */
public class CategoryTest extends BaseTest {

	/**
	 * Retrieves categories based on the specified entity name or class name
	 */
	public void testGetCategoriesByEntity() {
		// Lists the categories.
		List<Category> categories = new ArrayList<Category>();
		// Entity name (class name).
		String entityName = ENTITY_REGION;
		// Retrieve the categories by the specified name.
		categories = tpeService.getCategoriesByEntity(entityName);
		assertNotNull(categories);
		assertEquals(0, categories.size());
	}

	/**
	 * This tests for the retrieval of the category by its primary key or id
	 */
	public void testGetCategoryById() {
		// The id to retrieve the category
		Integer categoryId = 1;
		// Get the category
		Category category = tpeService.getCategoryById(categoryId);
		assertNotNull(category);
		assertEquals(categoryId, category.getId());
	}

	public void testGetOutputs() {

		List<Category> outputs = tpeService.getOutputs();
		assertNotNull(outputs);
		assertEquals(3, outputs.size());
	}
}
