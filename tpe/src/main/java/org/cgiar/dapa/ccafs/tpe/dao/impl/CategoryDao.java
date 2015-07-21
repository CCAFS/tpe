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
package org.cgiar.dapa.ccafs.tpe.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.cgiar.dapa.ccafs.tpe.dao.ICategoryDao;
import org.cgiar.dapa.ccafs.tpe.entity.Category;

/**
 * This class implements the methods defined in the category dao interface.
 * 
 * @author NMATOVU
 *
 */
@SuppressWarnings("unchecked")
public class CategoryDao extends GenericDao<Category, Integer> implements
		ICategoryDao {

	public CategoryDao() {
		super(Category.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Category> getCategoriesByEntity(String entityName) {
		StringBuffer q = new StringBuffer("from " + entityClass.getName())
				.append(" r where r.description =:entity")
				.append(" order by r.name");
		Query query = entityManager.createQuery(q.toString());
		query.setParameter("entity", entityName);

		return query.getResultList();
	}

	@Override
	public List<Category> getOutputs() {
		StringBuffer q = new StringBuffer("from " + entityClass.getName())
				.append(" r where r.description =:description").append(
						" order by r.name asc");
		Query query = entityManager.createQuery(q.toString());
		query.setParameter("description", CATEGORY_DESCRIPTION_MAP);

		return query.getResultList();
	}
}
