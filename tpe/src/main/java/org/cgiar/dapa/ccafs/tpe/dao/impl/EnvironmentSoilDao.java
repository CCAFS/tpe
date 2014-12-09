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

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.cgiar.dapa.ccafs.tpe.chart.Probability;
import org.cgiar.dapa.ccafs.tpe.dao.IEnvironmentSoilDao;
import org.cgiar.dapa.ccafs.tpe.entity.Environment;
import org.cgiar.dapa.ccafs.tpe.entity.EnvironmentSoil;
import org.cgiar.dapa.ccafs.tpe.entity.Soil;

@SuppressWarnings("unchecked")
public class EnvironmentSoilDao extends GenericDao<EnvironmentSoil, Integer>
		implements IEnvironmentSoilDao {

	public EnvironmentSoilDao() {
		super(EnvironmentSoil.class);
	}

	@Override
	public List<String> getEnvSowingDates(Integer country) {
		// TODO consider the cultivar
		List<String> categories = new LinkedList<String>();

		StringBuffer q = new StringBuffer("select distinct r.sowingDate from "
				+ entityClass.getName())
				.append(" r where r.region.id =:region").append(
						" order by r.sowingDate asc");
		// TODO Add cultivar
		Query query = entityManager.createQuery(q.toString());
		query = entityManager.createQuery(q.toString());
		query.setParameter("region", country);

		categories = query.getResultList();

		return categories;
	}

	@Override
	public Map<String, List<Probability>> getEnvSoilProbabilities(
			Integer country) {
		// TODO Add or consider cultivar in the query params
		Map<String, List<Probability>> probabilties = new LinkedHashMap<String, List<Probability>>();
		List<Probability> data = new LinkedList<Probability>();
		List<Soil> textures = getSoilTextures();
		List<Environment> environments = getEnvironments();
		List<EnvironmentSoil> results = new LinkedList<EnvironmentSoil>();
		for (Soil soil : textures) {
			for (Environment environment : environments) {
				StringBuffer q = new StringBuffer("from "
						+ EnvironmentSoil.class.getName())
						.append(" r where r.region.id =:region")
						.append(" and r.soil.id =:soil")
						.append(" and r.environment.id =:environment")
						.append(" order by r.sowingDate asc");

				Query query = entityManager.createQuery(q.toString());
				query = entityManager.createQuery(q.toString());
				query.setParameter("region", country);
				query.setParameter("soil", soil.getId());
				query.setParameter("environment", environment.getId());
				results = query.getResultList();
				List<Float> probs = new LinkedList<Float>();
				if (results != null && !results.isEmpty()) {
					for (EnvironmentSoil env : results)
						probs.add(env.getProbability());
					// data.put(environment.getCode(), dt);
					data.add(new Probability(environment.getCode(), probs,
							environment.getColor()));
					probs = new LinkedList<Float>();
				}
			}
			probabilties.put(soil.getCode(), data);
			data = new LinkedList<Probability>();
		}

		return probabilties;
	}

	private List<Environment> getEnvironments() {

		StringBuffer q = new StringBuffer("from " + Environment.class.getName())
				.append(" r");
		Query query = entityManager.createQuery(q.toString());
		query = entityManager.createQuery(q.toString());

		return query.getResultList();
	}

	private List<Soil> getSoilTextures() {

		StringBuffer q = new StringBuffer("from " + Soil.class.getName())
				.append(" r");
		Query query = entityManager.createQuery(q.toString());
		query = entityManager.createQuery(q.toString());

		return query.getResultList();
	}

}
