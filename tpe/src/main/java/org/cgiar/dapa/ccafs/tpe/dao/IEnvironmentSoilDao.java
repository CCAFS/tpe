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
import java.util.Map;

import org.cgiar.dapa.ccafs.tpe.chart.Probability;
import org.cgiar.dapa.ccafs.tpe.entity.EnvironmentSoil;

/**
 * This interface defines the data access methods for the environment and soil
 * 
 * @author NMATOVU
 *
 */
public interface IEnvironmentSoilDao extends
		IGenericDao<EnvironmentSoil, Integer> {

	/**
	 * Gets the sowing dates
	 * 
	 * @param country
	 *            the country id
	 * @return sowing dates
	 */
	List<String> getEnvSowingDates(Integer country);

	/**
	 * Gets the environment soil probabilities
	 * 
	 * @param country
	 *            country id
	 * @return probabilities
	 */
	Map<String, List<Probability>> getEnvSoilProbabilities(Integer country);
}
