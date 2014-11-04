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

import java.util.List;
import java.util.Map;

import org.cgiar.dapa.ccafs.tpe.util.Utils;

/**
 * The class that provides the highcharts type data for a different chart types;
 * <ul>
 * <li>
 * spline</li>
 * <li>stacked</li>
 * <li>columns</li>
 * <li>bars</li>
 * <li>columns</li>
 * </ul>
 * 
 * @author nmatovu
 * 
 */
public class Chart {
	/**
	 * The name of the spline line on the plot chart
	 */
	private String name;
	/**
	 * marker: {symbol: 'square'}
	 */
	private Map<String, String> marker = Utils.initializeSplineMarker();
	/**
	 * The list of data.
	 * <ul>
	 * <li>
	 * data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5]</li>
	 * <li>
	 * data: [2, 2, 3, 2, 1]</li>
	 * </ul>
	 */
	private List<Object> data;
	/**
	 * The chart categories,
	 * 
	 * <ul>
	 * <li>
	 * categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May','Jun', 'Jul', 'Aug',
	 * 'Sep', 'Oct', 'Nov', 'Dec']</li>
	 * <li>
	 * categories: ['Clay', 'Sand', 'Loan', 'SandLoam', 'SandClay']</li>
	 * </ul>
	 */
	private List<Object> categories;
	/**
	 * The source of the data: 'Source: WorldClimate.com'
	 */
	private String source;
	/**
	 * The stack name used to group the stacked columns. <b>series: [{name:
	 * 'Env1',data: [5, 3, 4, 7, 2],stack: 'November_01'}</b>
	 */
	private String stack;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, String> getMarker() {
		return marker;
	}

	public void setMarker(Map<String, String> marker) {
		this.marker = marker;
	}

	public List<Object> getCategories() {
		return categories;
	}

	public void setCategories(List<Object> categories) {
		this.categories = categories;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public List<Object> getData() {
		return data;
	}

	public void setData(List<Object> data) {
		this.data = data;
	}

	public String getStack() {
		return stack;
	}

	public void setStack(String stack) {
		this.stack = stack;
	}

}
