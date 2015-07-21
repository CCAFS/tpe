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

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * The class that provides the environement soil probability data for ploting
 * the stacked column plots
 * 
 * @author nmatovu
 *
 */
public class Probability implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6008372178313292379L;
	// Map<Environment,List<Probability>>
	private List<Float> data = new LinkedList<Float>();
	// List<sowingDate>
	/**
	 * The sowing date
	 */
	// private List<String> categories = new LinkedList<String>();

	private String name;
	/**
	 * The series color.
	 */
	private String color;

	public Probability(String name, List<Float> data, String color) {
		super();
		this.name = name;
		this.data = data;
		this.color = color;
	}

	public List<Float> getData() {
		return data;
	}

	public void setData(List<Float> data) {
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
