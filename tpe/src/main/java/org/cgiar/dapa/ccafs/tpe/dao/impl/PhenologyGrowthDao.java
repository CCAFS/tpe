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
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.cgiar.dapa.ccafs.tpe.dao.IPhenologyGrowthDao;
import org.cgiar.dapa.ccafs.tpe.entity.PhenologyGrowth;
import org.cgiar.dapa.ccafs.tpe.entity.Region;
import org.cgiar.dapa.ccafs.tpe.entity.Soil;

/**
 * This class provides the implementation of the methods defined in the
 * IPhenologyGrowth interface and it extends the Generic Dao class.
 * 
 * @author NMATOVU
 *
 */
@SuppressWarnings("unchecked")
public class PhenologyGrowthDao extends GenericDao<PhenologyGrowth, Integer>
		implements IPhenologyGrowthDao {

	public PhenologyGrowthDao() {
		super(PhenologyGrowth.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Map<String, Map<Double, Double>> getTPERegions(Integer cultivarId,
			Integer regionId, Integer swindowId, String year) {
		// regions map
		Map<String, Map<Double, Double>> regionsMap = new LinkedHashMap<String, Map<Double, Double>>();
		// Point map
		Map<Double, Double> geoMap = new LinkedHashMap<Double, Double>();

		StringBuffer q = new StringBuffer("select r.region from "
				+ entityClass.getName())
				.append(" r where r.region.parent.id =:country")
				.append(" and r.cultivar.id =:cultivar")
				.append(" and r.window.id =:swindow")
				.append(" and r.year =:year");

		Query query = entityManager.createQuery(q.toString());

		query.setParameter("swindow", swindowId);
		query.setParameter("country", regionId);
		query.setParameter("cultivar", cultivarId);
		query.setParameter("year", year);

		List<Region> results = query.getResultList();

		for (Region region : results) {
			geoMap.put(region.getLatitude(), region.getLongitude());
			regionsMap.put(region.getAlphaISO(), geoMap);
		}

		return regionsMap;
	}

	@Override
	public Map<String, Map<Double, Double>> getTPERegions(Integer cultivarId,
			Integer regionId, Integer swindowId, String year, Integer scenarioId) {
		// regions map
		Map<String, Map<Double, Double>> regionsMap = new LinkedHashMap<String, Map<Double, Double>>();
		// Point map
		Map<Double, Double> geoMap = new LinkedHashMap<Double, Double>();

		StringBuffer q = new StringBuffer("select r.region from "
				+ entityClass.getName())
				.append(" r where r.region.parent.id =:country")
				.append(" and r.cultivar.id =:cultivar")
				.append(" and r.window.id =:swindow")
				.append(" and r.year =:year")
				.append(" and r.scenario.id =:scenario");

		Query query = entityManager.createQuery(q.toString());

		query.setParameter("swindow", swindowId);
		query.setParameter("country", regionId);
		query.setParameter("cultivar", cultivarId);
		query.setParameter("year", year);
		query.setParameter("scenario", scenarioId);

		List<Region> results = query.getResultList();

		for (Region region : results) {
			geoMap.put(region.getLatitude(), region.getLongitude());
			regionsMap.put(region.getAlphaISO(), geoMap);
		}

		return regionsMap;
	}

	@Override
	public Map<String, Map<String, Double>> getTPESoil(Integer cultivarId,
			Integer regionId, Integer swindowId, String year) {
		// soil map Map<SoilCode,Map<regionISO,yield>>
		Map<String, Map<String, Double>> soilMap = new LinkedHashMap<String, Map<String, Double>>();
		// region-yield map (Map<regionISO,yield>)
		Map<String, Double> regionYieldMap = new LinkedHashMap<String, Double>();
		// TODO To retrieve r.soil.code, r.region.lat, etc
		StringBuffer q = new StringBuffer(
				"select r.soil, r.region, r.wrr14 from "
						+ entityClass.getName())
				.append(" r where r.region.parent.id =:country")
				.append(" and r.cultivar.id =:cultivar")
				.append(" and r.window.id =:swindow")
				.append(" and r.year =:year");

		Query query = entityManager.createQuery(q.toString());

		query.setParameter("swindow", swindowId);
		query.setParameter("country", regionId);
		query.setParameter("cultivar", cultivarId);
		query.setParameter("year", year);

		List<Object[]> results = query.getResultList();

		for (Object[] res : results) {
			regionYieldMap.put(String.valueOf(res[1]),
					Double.valueOf((String) res[2]));
			soilMap.put(((Soil) res[0]).getCode(), regionYieldMap);
		}

		return soilMap;
	}

	@Override
	public Map<String, Map<String, Double>> getTPESoil(Integer cultivarId,
			Integer regionId, Integer swindowId, String year, Integer scenarioId) {
		// soil map Map<SoilCode,Map<regionISO,yield>>
		Map<String, Map<String, Double>> soilMap = new LinkedHashMap<String, Map<String, Double>>();
		// region-yield map (Map<regionISO,yield>)
		Map<String, Double> regionYieldMap = new LinkedHashMap<String, Double>();
		// TODO To retrieve r.soil.code, r.region.lat, etc
		StringBuffer q = new StringBuffer(
				"select r.soil, r.region, r.wrr14 from "
						+ entityClass.getName())
				.append(" r where r.region.parent.id =:country")
				.append(" and r.cultivar.id =:cultivar")
				.append(" and r.window.id =:swindow")
				.append(" and r.year =:year")
				.append(" and r.scenario.id =:scenario");

		Query query = entityManager.createQuery(q.toString());

		query.setParameter("swindow", swindowId);
		query.setParameter("country", regionId);
		query.setParameter("cultivar", cultivarId);
		query.setParameter("year", year);
		query.setParameter("scenario", scenarioId);

		List<Object[]> results = query.getResultList();

		for (Object[] res : results) {
			regionYieldMap.put(String.valueOf(res[1]),
					Double.valueOf((String) res[2]));
			soilMap.put(((Soil) res[0]).getCode(), regionYieldMap);
		}

		return soilMap;
	}

}
