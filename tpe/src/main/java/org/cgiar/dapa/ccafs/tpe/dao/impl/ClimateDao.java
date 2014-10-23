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

import org.cgiar.dapa.ccafs.tpe.dao.IClimateDao;
import org.cgiar.dapa.ccafs.tpe.entity.Climate;

/**
 * This class implements the methods defined in the Climate DAO interface
 * 
 * @author NMATOVU
 *
 */
@SuppressWarnings("unchecked")
public class ClimateDao extends GenericDao<Climate, Integer> implements
		IClimateDao {

	public ClimateDao() {
		super(Climate.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Climate> getClimateByStations(List<Integer> stationIds,
			Integer categoryId, String year) {
		StringBuffer q = new StringBuffer("from " + entityClass.getName())
				.append(" r where r.station.id in (:stations)")
				.append(" and r.category.id =:category")
				.append(" and r.year =:year");

		Query query = entityManager.createQuery(q.toString());
		query.setParameter("stations", stationIds);
		query.setParameter("category", categoryId);
		query.setParameter("year", year);
		return query.getResultList();
	}

	@Override
	public List<Climate> getClimateByRegions(List<Integer> regionIds,
			Integer categoryId, String year) {
		StringBuffer q = new StringBuffer("from " + entityClass.getName())
				.append(" r where r.station.region.id in (:regions)")
				.append(" and r.category.id =:category")
				.append(" and r.year =:year");

		Query query = entityManager.createQuery(q.toString());
		query.setParameter("regions", regionIds);
		query.setParameter("category", categoryId);
		query.setParameter("year", year);
		return query.getResultList();
	}

}
