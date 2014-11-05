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
package org.cgiar.dapa.ccafs.tpe.action;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionSupport;

/**
 * The action that is responsible for the display of the Climate GeoJSON data
 * for the TPE Climate Google Maps
 * 
 * @author NMATOVU
 *
 */
public class ClimateGeoJsonAction extends BaseAction {

	private static final long serialVersionUID = 8564417689624834186L;
	@SuppressWarnings("unused")
	private Log log = LogFactory.getLog(this.getClass());
	/**
	 * The soil GeoJson map that will provide the GeoJson features on the Google
	 * Map
	 */
	private Map<String, Object> climateGeoJson = new LinkedHashMap<String, Object>();
	/**
	 * The country id from the selection pane
	 */
	private Integer country;
	/**
	 * The id of the selected climate property from the selection pane
	 */
	private Integer property;
	/**
	 * The selected year from the selection pane
	 */
	private String year;
	/**
	 * The selected weather stations from the jsp page
	 */
	private List<Integer> stations;
	/**
	 * The selected subregions from the jsp page
	 */
	private List<Integer> subregions;

	public String execute() {
		// TODO Pass the country param from the jsp page.
		// TODO Pass properties params from the jsp page.

		// Retrieve the soil GeoJson data from the database
		if (stations != null || !stations.isEmpty() && !subregions.isEmpty()
				|| subregions != null) {
			// Assume the user selected specific stations and sub regions
			// Query for those specific params
			// TODO Query climateGeoJson for the selected states and sub regions

		} else
			// Assume the user selected all stations and all sub regions
			climateGeoJson = tpeService.getClimateGeoJSON(property, country,
					year);

		return ActionSupport.SUCCESS;
	}

	public Integer getProperty() {
		return property;
	}

	public void setProperty(Integer property) {
		this.property = property;
	}

	public Map<String, Object> getClimateGeoJson() {
		return climateGeoJson;
	}

	public void setClimateGeoJson(Map<String, Object> climateGeoJson) {
		this.climateGeoJson = climateGeoJson;
	}

	public Integer getCountry() {
		return country;
	}

	public void setCountry(Integer country) {
		this.country = country;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public List<Integer> getStations() {
		return stations;
	}

	public void setStations(List<Integer> stations) {
		this.stations = stations;
	}

	public List<Integer> getSubregions() {
		return subregions;
	}

	public void setSubregions(List<Integer> subregions) {
		this.subregions = subregions;
	}

}
