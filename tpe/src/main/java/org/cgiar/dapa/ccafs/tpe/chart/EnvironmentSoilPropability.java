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
package org.cgiar.dapa.ccafs.tpe.chart;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * The class that provides the environement soil probability data for ploting
 * the stacked column plots
 * 
 * @author nmatovu
 *
 */
public class EnvironmentSoilPropability {
	// Map<Environment,List<Probability>>
	private Map<String, List<Float>> probabilityData = new LinkedHashMap<String, List<Float>>();
	// List<sowingDate>
	/**
	 * The sowing date
	 */
	private List<String> sowingDate = new LinkedList<String>();
	
	
	public Map<String, List<Float>> getProbabilityData() {
		return probabilityData;
	}

	public void setProbabilityData(Map<String, List<Float>> probabilityData) {
		this.probabilityData = probabilityData;
	}

	public List<String> getSowingDate() {
		return sowingDate;
	}

	public void setSowingDate(List<String> sowingDate) {
		this.sowingDate = sowingDate;
	}

}
