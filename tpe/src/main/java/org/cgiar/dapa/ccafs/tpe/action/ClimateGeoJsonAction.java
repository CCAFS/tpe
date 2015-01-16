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
import org.cgiar.dapa.ccafs.tpe.util.Utils;

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

	private static final Object COUNTRY_COLOMBIA = "COLOMBIA";

	@SuppressWarnings("unused")
	private Log log = LogFactory.getLog(this.getClass());

	/**
	 * The selected weather stations from the jsp page
	 */
	// TODO Ignore the station selection in the beta version
	// private List<Integer> selectedStations;

	/**
	 * The soil properties from the selection pane
	 */
	// private List<Integer> selectedIndicators;

	/**
	 * The country or region latitude coordinate.
	 */
	protected Double lat;
	/**
	 * The country or region longitude to initialize the lng
	 */
	protected Double lng;

	/**
	 * The selected TPE output from the jsp page
	 */
	protected String selectedOutput;
	/**
	 * The id of the selected country from the jsp page
	 */
	protected Integer selectedCountry;
	/**
	 * The selected sub regions from the jsp page
	 */
	// TODO Ignore the state selection or sub regions in the beta version
	// protected List<Integer> selectedRegions;
	/**
	 * The default Google Map zoom
	 */
	protected Integer zoom = 4;
	/**
	 * The selected list of years
	 */
	// protected List<String> selectedYears;
	/**
	 * The field for holding the selected country geo json data from the json
	 * file from the server
	 */
	protected Object countryGeoJson;

	/**
	 * The soil GeoJson map that will provide the GeoJson features on the Google
	 * Map
	 */
	protected Map<String, Object> geoJson = new LinkedHashMap<String, Object>();
	/**
	 * The corresponding country states geojson data
	 */
	private Object statesGeoJson;
	/**
	 * The series data map
	 */
	private Map<String, Object> seriesData;

	public String execute() {

		// Retrieve the soil GeoJson data from the database
		if (getSelectedCountry() != null) {

			// TODO Initially dont consider selection of climate indicators
			this.setGeoJson(tpeService.getClimateGeoJSON(
					this.getSelectedCountry(), null));
			// Get the climate series data from the database
			seriesData = tpeService.getClimateSeries(getSelectedCountry());
		}

		this.setRegion(tpeService.getRegionById(getSelectedCountry()));
		setLat(getRegion().getLatitude());
		setLng(getRegion().getLongitude());
		this.setZoom(this.getRegion().getZoom());
		setCountryGeoJson(Utils.loadJSON(this.getPath() + "script/"
				+ getRegion().getName().toUpperCase() + ".geo.json"));
		// Load states geo json data

		if (getRegion().getName().toUpperCase().equals(COUNTRY_COLOMBIA))
			this.setStatesGeoJson(Utils.loadJSONData(this.getPath() + "script/"
					+ getRegion().getName().toUpperCase() + ".CLIMATE.geo.json"));
		else
			this.setStatesGeoJson(Utils.loadJSONData(this.getPath() + "script/"
					+ getRegion().getName().toUpperCase() + ".STATES.geo.json"));

		return ActionSupport.SUCCESS;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public String getSelectedOutput() {
		return selectedOutput;
	}

	public void setSelectedOutput(String selectedOutput) {
		this.selectedOutput = selectedOutput;
	}

	public Integer getSelectedCountry() {
		return selectedCountry;
	}

	public void setSelectedCountry(Integer selectedCountry) {
		this.selectedCountry = selectedCountry;
	}

	public Integer getZoom() {
		return zoom;
	}

	public void setZoom(Integer zoom) {
		this.zoom = zoom;
	}

	public Object getCountryGeoJson() {
		return countryGeoJson;
	}

	public void setCountryGeoJson(Object countryGeoJson) {
		this.countryGeoJson = countryGeoJson;
	}

	public Map<String, Object> getGeoJson() {
		return geoJson;
	}

	public void setGeoJson(Map<String, Object> geoJson) {
		this.geoJson = geoJson;
	}

	public Object getStatesGeoJson() {
		return statesGeoJson;
	}

	public void setStatesGeoJson(Object statesGeoJson) {
		this.statesGeoJson = statesGeoJson;
	}

	public Map<String, Object> getSeriesData() {
		return seriesData;
	}

	public void setSeriesData(Map<String, Object> seriesData) {
		this.seriesData = seriesData;
	}

}
