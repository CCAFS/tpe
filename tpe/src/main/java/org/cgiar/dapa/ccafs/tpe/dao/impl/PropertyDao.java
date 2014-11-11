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

import org.cgiar.dapa.ccafs.tpe.dao.IPropertyDao;
import org.cgiar.dapa.ccafs.tpe.entity.Climate;
import org.cgiar.dapa.ccafs.tpe.entity.Property;
import org.cgiar.dapa.ccafs.tpe.entity.Soil;

/**
 * This class implements the methods that are defined in the IPropertyDao
 * interface.
 * 
 * @author NMATOVU
 * 
 */
@SuppressWarnings("unchecked")
public class PropertyDao extends GenericDao<Property, Integer> implements
		IPropertyDao {
	// private Logger log = Logger.getLogger(this.getClass());

	// private static final String PROPERTY_CATEGORY_SOIL = Soil.class
	// .getSimpleName();
	private static final String PROPERTY_ENTITY_SOIL = Soil.class
			.getSimpleName();
	private static final String PROPERTY_CATEGORY_CLIMATE = Climate.class
			.getSimpleName();

	public PropertyDao() {
		super(Property.class);

	}

	@Override
	public List<Property> getPropertiesByCategory(Integer categoryId) {
		StringBuffer q = new StringBuffer("from " + entityClass.getName())
				.append(" r where r.category.id =:category").append(
						" order by r.name asc");
		Query query = entityManager.createQuery(q.toString());
		query.setParameter("category", categoryId);

		return query.getResultList();
	}

	@Override
	public List<Property> getSoilProperties() {
		StringBuffer q = new StringBuffer("from " + entityClass.getName())
				.append(" r where r.entity =:entity").append(
						" order by r.name asc");
		Query query = entityManager.createQuery(q.toString());
		query.setParameter("entity", PROPERTY_ENTITY_SOIL);

		return query.getResultList();
	}

	@Override
	public List<Property> getClimateProperties() {
		StringBuffer q = new StringBuffer("from " + entityClass.getName())
				.append(" r where r.category.name =:category").append(
						" order by r.name asc");
		Query query = entityManager.createQuery(q.toString());
		query.setParameter("category", PROPERTY_CATEGORY_CLIMATE);

		return query.getResultList();

	}

}
