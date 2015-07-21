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
package org.cgiar.dapa.ccafs.tpe.projection;

import java.util.Map;

/**
 * The soil texture distribution projection
 * 
 * @author NMATOVU
 *
 */
public class Texture {
	// private Double lat;
	// private Double lng;
	private String regionISO;
	private String parentISO;
	// private String color;
	// private Double yield;
	// private String year;
	private Map<Double, Double> points;

	public String getRegionISO() {
		return regionISO;
	}

	public void setRegionISO(String regionISO) {
		this.regionISO = regionISO;
	}

	public String getParentISO() {
		return parentISO;
	}

	public void setParentISO(String parentISO) {
		this.parentISO = parentISO;
	}

	public Map<Double, Double> getPoints() {
		return points;
	}

	public void setPoints(Map<Double, Double> points) {
		this.points = points;
	}

}
