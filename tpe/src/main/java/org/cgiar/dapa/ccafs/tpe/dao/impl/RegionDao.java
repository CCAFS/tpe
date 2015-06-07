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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Query;

import org.cgiar.dapa.ccafs.tpe.dao.IRegionDao;
import org.cgiar.dapa.ccafs.tpe.entity.Region;
import org.cgiar.dapa.ccafs.tpe.util.RegionCategory;

@SuppressWarnings("unchecked")
public class RegionDao extends GenericDao<Region, Integer> implements
		IRegionDao {
	// private Logger log = Logger.getLogger(this.getClass());

	public RegionDao() {
		super(Region.class);

	}

	@Override
	public List<Region> getCountries() {
		StringBuffer q = new StringBuffer("from " + entityClass.getName())
				.append(" r where r.category.name =:category");
		Query query = entityManager.createQuery(q.toString());
		query.setParameter("category", RegionCategory.COUNTRY.name());
		return query.getResultList();
	}

	@Override
	public List<Region> getSubregionsByCountry(Integer countryId) {
		StringBuffer q = new StringBuffer("from " + entityClass.getName())
				.append(" r where r.parent.id =:country").append(
						" order by r.name");
		Query query = entityManager.createQuery(q.toString());
		query.setParameter("country", countryId);

		return query.getResultList();
	}

	@Override
	public List<Region> getCountriesAndContinents() {
		StringBuffer q = new StringBuffer("from " + entityClass.getName())
				.append(" r where r.category.name in(:categories)");
		Query query = entityManager.createQuery(q.toString());
		query.setParameter(
				"categories",
				new ArrayList<String>(Arrays.asList(
						RegionCategory.COUNTRY.name(),
						RegionCategory.CONTINENT.name())));
		return query.getResultList();
	}

}
