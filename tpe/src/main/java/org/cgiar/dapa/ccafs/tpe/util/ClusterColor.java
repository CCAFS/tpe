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

package org.cgiar.dapa.ccafs.tpe.util;

/**
 * The enum that defines the cluster colors for the Target Population
 * Environments. HFE (High Favorable Environment), LFE (Low Favorable
 * Environment) and FE (Favorable Environment)
 * 
 * @author NMATOVU
 *
 */
public enum ClusterColor {
	LFE("#990000"), HFE("#009900"), FE("#ADD8E6");
	/**
	 * The color
	 */
	private String color;

	private ClusterColor(String color) {
		this.color = color;
	}

	public String getColor() {
		return color;
	}

}
