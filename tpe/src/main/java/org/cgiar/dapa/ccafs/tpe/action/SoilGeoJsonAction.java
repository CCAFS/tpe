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

import java.io.IOException;
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

	private Log LOG = LogFactory.getLog(this.getClass());

	/**
	 * The soil properties from the selection pane
	 */
	// private List<Integer> selectedProperties;
	/**
	 * The selected soil texture ids from the selection pane.
	 */
	// private List<Integer> selectedTextures;
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
	 * The corresponding country states geojson data
	 */
	// private Object statesGeoJson;

	/**
	 * The soil GeoJson map that will provide the GeoJson features on the Google
	 * Map
	 */
	protected Map<String, Object> featuresJson = new LinkedHashMap<String, Object>();

	private Map<String, List<Probability>> seriesJson = new LinkedHashMap<String, List<Probability>>();
	// private Map<String, List<Map<String, Object>>> probabilities = new
	// LinkedHashMap<String, List<Map<String, Object>>>();

	private List<String> categoriesJson = new LinkedList<String>();
	private Object tpeBoundaryJson;
	/**
	 * The country (Colombia or Brazil) or region(Latin America) geo JSON object
	 */
	private Object regionJson;

	/**
	 * The municipalities Geo JSON object
	 */
	private Object municipalitiesJson;

	/**
	 * The JSON for the growing regions or areas for the currently selected crop
	 * and cultiva from the selected country or Continent.
	 */
	private Object growingRegionsJson;

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

		if (this.getSelectedCountry() != null) {

			this.setRegion(tpeService.getRegionById(getSelectedCountry()));

			setLat(getRegion().getLatitude());
			setLng(getRegion().getLongitude());

			this.setZoom(this.getRegion().getZoom());

			// Load the country geojson file
			/*
			 * setCountryGeoJson(Utils.loadJSON(this.getPath() + "script/" +
			 * getRegion().getName().toUpperCase() + ".geo.json"));
			 */
			try {
				setRegionJson(Utils.loadGeoJSON(getRegion().getName(),
						JSON_REGION));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				LOG.error(e.getMessage());
			}

			// Load the corresponding country states
			//
			// this.statesGeoJson = Utils.loadJSONData(this.getPath() +
			// "script/"
			// + getRegion().getName().toUpperCase() + ".STATES.geo.json");
			categoriesJson = tpeService.getEnvSowingDates(getSelectedCountry());

			setSeriesJson(tpeService
					.getEnvSoilProbabilities(getSelectedCountry()));
			// log.info("About to query data.");
			boolean continent = false;
			if (getRegion().getCategory().getName().equals(CATEGORY_CONTINENT))
				continent = true;

			if (this.getSelectedCountry() != 2)
				this.setFeaturesJson(this.tpeService.getSoilGeoJson(null,
						getSelectedCountry(), continent));

			// TODO Add cultivar parameter
			/*
			 * this.setTpeBoundaryJson(Utils.loadJSONData(this.getPath() +
			 * "script/" + getRegion().getName().toUpperCase() +
			 * ".BOUNDARY.json"));
			 */

			try {
				municipalitiesJson = Utils.loadGeoJSON(getRegion().getName(),
						JSON_MUNICIPIOS);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				LOG.error(e.getMessage());
			}

			try {
				tpeBoundaryJson = Utils.loadGeoJSON(getRegion().getName(),
						JSON_BOUNDARY);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				LOG.error(e.getMessage());
			}

			// Load the crop growing areas json file for the selected region.
			// TODO Add the select option for crop for climate
			// TODO Remove constant for crop
			try {
				setGrowingRegionsJson(Utils.readJSON(CROP_RICE, region
						.getName().toLowerCase(), JSON_MAP_GROWING));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				LOG.error(e.getMessage());
			}

		}

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

	public Map<String, Object> getFeaturesJson() {
		return featuresJson;
	}

	public void setFeaturesJson(Map<String, Object> featuresJson) {
		this.featuresJson = featuresJson;
	}

	public Object getTpeBoundaryJson() {
		return tpeBoundaryJson;
	}

	public void setTpeBoundaryJson(Object tpeBoundaryJson) {
		this.tpeBoundaryJson = tpeBoundaryJson;
	}

	public Integer getZoom() {
		return zoom;
	}

	public void setZoom(Integer zoom) {
		this.zoom = zoom;
	}

	public Object getRegionJson() {
		return regionJson;
	}

	public void setRegionJson(Object regionJson) {
		this.regionJson = regionJson;
	}

	public Object getMunicipalitiesJson() {
		return municipalitiesJson;
	}

	public void setMunicipalitiesJson(Object municipalitiesJson) {
		this.municipalitiesJson = municipalitiesJson;
	}

	public List<String> getCategoriesJson() {
		return categoriesJson;
	}

	public void setCategoriesJson(List<String> categoriesJson) {
		this.categoriesJson = categoriesJson;
	}

	public Map<String, List<Probability>> getSeriesJson() {
		return seriesJson;
	}

	public void setSeriesJson(Map<String, List<Probability>> seriesJson) {
		this.seriesJson = seriesJson;
	}

	public Object getGrowingRegionsJson() {
		return growingRegionsJson;
	}

	public void setGrowingRegionsJson(Object growingRegionsJson) {
		this.growingRegionsJson = growingRegionsJson;
	}

}