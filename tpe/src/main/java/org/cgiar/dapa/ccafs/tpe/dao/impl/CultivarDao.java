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

import org.cgiar.dapa.ccafs.tpe.dao.ICultivarDao;
import org.cgiar.dapa.ccafs.tpe.entity.Cultivar;

@SuppressWarnings("unchecked")
public class CultivarDao extends GenericDao<Cultivar, Integer> implements
		ICultivarDao {

	// private Logger log = Logger.getLogger(this.getClass());

	public CultivarDao() {
		super(Cultivar.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Cultivar> getCultivarsByCrop(Integer cropId) {

		StringBuffer q = new StringBuffer("from " + entityClass.getName())
				.append(" r where r.crop.id =:cropId");
		Query query = entityManager.createQuery(q.toString());
		query.setParameter("cropId", cropId);

		return query.getResultList();
	}

	@Override
	public List<String> getYearsByCultivar(Integer cultivarId) {
		// TODO Auto-generated method stub
		return null;
	}

}
