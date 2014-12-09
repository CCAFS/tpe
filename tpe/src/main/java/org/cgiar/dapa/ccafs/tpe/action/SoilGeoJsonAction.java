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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cgiar.dapa.ccafs.tpe.chart.Probability;
import org.cgiar.dapa.ccafs.tpe.util.Utils;

import com.opensymphony.xwork2.ActionSupport;

/**
 * The action that is responsible for the display of the Soil GeoJSON data for
 * the TPE soil Google Maps
 * 
 * @author NMATOVU
 *
 */
public class SoilGeoJsonAction extends BaseAction {

	private static final long serialVersionUID = -2150409370455878988L;

	@SuppressWarnings("unused")
	private Log log = LogFactory.getLog(this.getClass());

	/**
	 * The soil properties from the selection pane
	 */
	private List<Integer> selectedProperties;
	/**
	 * The selected soil texture ids from the selection pane.
	 */
	private List<Integer> selectedTextures;
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
	// protected List<Integer> selectedRegions;
	/**
	 * The default Google Map zoom
	 */
	protected Integer zoom = 4;
	/**
	 * The selected list of years
	 */
	protected List<String> selectedYears;
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

	private Map<String, List<Probability>> probabilities = new LinkedHashMap<String, List<Probability>>();
	// private Map<String, List<Map<String, Object>>> probabilities = new
	// LinkedHashMap<String, List<Map<String, Object>>>();

	private List<String> categories = new LinkedList<String>();

	public String execute() {

		// Retrieve the soil GeoJson data from the database
		/*
		 * if (this.getSelectedCountry() != null &&
		 * !getSelectedYears().isEmpty() || getSelectedYears() != null &&
		 * getSelectedProperties() != null ||
		 * !getSelectedProperties().isEmpty()) {
		 */

		// if (getRegion() != null) {
		// setLat(getRegion().getLatitude());
		// setLng(getRegion().getLongitude());
		// }

		if (this.getSelectedCountry() != null
				&& getSelectedProperties() != null
				|| !getSelectedProperties().isEmpty()
				&& getSelectedTextures() != null
				|| !getSelectedTextures().isEmpty()) {

			categories = tpeService.getEnvSowingDates(getSelectedCountry());

			probabilities = tpeService
					.getEnvSoilProbabilities(getSelectedCountry());
			// log.info("About to query data.");

			this.setGeoJson(this.tpeService.getSoilGeoJson(
					getSelectedProperties(), getSelectedCountry()));
			// TODO Add cultivar parameter

		}
		// countryGeoJson = Utils.loadJSON(this.getPath()
		// + "script/COLOMBIA.geo.json");

		this.setRegion(tpeService.getRegionById(getSelectedCountry()));

		// log.info(getRegion());
		setLat(getRegion().getLatitude());
		setLng(getRegion().getLongitude());

		// log.info("Lat: " + getLat());
		// log.info("Lng: " + getLng());

		/*
		 * setCountryGeoJson(Utils.loadJSON(this.getPath() +
		 * "script/BRAZIL.geo.json"));
		 */

		this.setZoom(this.getRegion().getZoom());

		setCountryGeoJson(Utils.loadJSON(this.getPath() + "script/"
				+ getRegion().getName().toUpperCase() + ".geo.json"));

		// data = probabilities.get("C");

		// log.info(getCountryGeoJson());
		return ActionSupport.SUCCESS;
	}

	public List<Integer> getSelectedProperties() {
		return selectedProperties;
	}

	public void setSelectedProperties(List<Integer> selectedProperties) {
		this.selectedProperties = selectedProperties;
	}

	public List<Integer> getSelectedTextures() {
		return selectedTextures;
	}

	public void setSelectedTextures(List<Integer> selectedTextures) {
		this.selectedTextures = selectedTextures;
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

	public List<String> getSelectedYears() {
		return selectedYears;
	}

	public void setSelectedYears(List<String> selectedYears) {
		this.selectedYears = selectedYears;
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

	public Map<String, List<Probability>> getProbabilities() {
		return probabilities;
	}

	public void setProbabilities(Map<String, List<Probability>> probabilities) {
		this.probabilities = probabilities;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

}
