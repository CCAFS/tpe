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

import java.util.List;

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
public class ClimateGeoJsonAction extends BaseGeoJsonAction {

	private static final long serialVersionUID = 8564417689624834186L;
	@SuppressWarnings("unused")
	private Log log = LogFactory.getLog(this.getClass());

	/**
	 * The selected weather stations from the jsp page
	 */
	private List<Integer> stations;
	/**
	 * The soil properties from the selection pane
	 */
	private List<Integer> properties;

	public String execute() {
		// TODO Pass the country param from the jsp page.
		// TODO Pass properties params from the jsp page.

		// Retrieve the soil GeoJson data from the database
		if (stations != null || !stations.isEmpty()
				&& !this.getSelectedRegions().isEmpty() || this.getSelectedRegions() != null
				&& !getSelectedYears().isEmpty() || getSelectedYears() != null
				&& getProperties() != null || !getProperties().isEmpty()) {
			// Assume the user selected specific stations and sub regions
			// Query for those specific params
			// TODO Query climateGeoJson for the selected states and sub regions

		} else
			// Assume the user selected all stations and all sub regions
			this.setGeoJson(tpeService.getClimateGeoJSON(
					getProperties().get(0), this.getSelectedCountry(), getSelectedYears()
							.get(0)));

		return ActionSupport.SUCCESS;
	}

	public List<Integer> getStations() {
		return stations;
	}

	public void setStations(List<Integer> stations) {
		this.stations = stations;
	}

	public List<Integer> getProperties() {
		return properties;
	}

	public void setProperties(List<Integer> properties) {
		this.properties = properties;
	}

}
