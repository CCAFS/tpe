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

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.cgiar.dapa.ccafs.tpe.dao.ISoilPropertyDao;
import org.cgiar.dapa.ccafs.tpe.entity.SoilProperty;

/**
 * This class implements the soil property interface methods
 * 
 * @author NMATOVU
 *
 */
@SuppressWarnings("unchecked")
public class SoilPropertyDao extends GenericDao<SoilProperty, Long>
		implements ISoilPropertyDao {

	public SoilPropertyDao() {
		super(SoilProperty.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Map<Integer, Map<Double, Double>> getSoilDistribution(
			Integer soilId, Integer regionId, Integer categoryId) {
		// Soil distribution map
		Map<Integer, Map<Double, Double>> distribution = new LinkedHashMap<Integer, Map<Double, Double>>();
		// Point map
		Map<Double, Double> pointMap = new LinkedHashMap<Double, Double>();

		StringBuffer q = new StringBuffer(
				"select r.longitude, r.latitude from " + entityClass.getName())
				.append(" r where r.station.region.id =:region")
				.append(" and r.category.id =:category")
				.append(" and r.soil.id =:soil");

		Query query = entityManager.createQuery(q.toString());

		query.setParameter("category", categoryId);
		query.setParameter("region", regionId);
		query.setParameter("soil", soilId);
		List<Object[]> results = query.getResultList();
		// List<Object[]> results = query.getResultList();
		// for (Object[] result : results) {
		// System.out.println("Longitude: " + result[0] + ", Latitude: " +
		// result[1]);
		// }
		Object[] point;
		for (Iterator<Object[]> iterator = results.iterator(); iterator
				.hasNext();) {
			// Get the point
			point = iterator.next();
			// Add the point to the point map
			pointMap.put(Double.valueOf((String) point[0]),
					Double.valueOf((String) point[1]));
		}
		// Add all the points to the point distribution map
		distribution.put(soilId, pointMap);
		return distribution;
	}

	@Override
	public Map<Integer, Map<Double, Double>> getSoilDistribution(
			List<Integer> soilIds, Integer regionId, Integer categoryId) {
		// Soil distribution map
		Map<Integer, Map<Double, Double>> distribution = new LinkedHashMap<Integer, Map<Double, Double>>();

		if (soilIds != null && !soilIds.isEmpty()) {
			// For each soil texture id, retrieve the corresponding soil points
			// from the particular region and property category
			for (Integer soil : soilIds) {
				this.getSoilDistribution(soil, regionId, categoryId);
				// Add all the points to the point distribution map
				distribution.putAll(this.getSoilDistribution(soil, regionId,
						categoryId));
			}
		}

		return distribution;
	}

}
