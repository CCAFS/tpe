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

	// private static final Object COUNTRY_COLOMBIA = "COLOMBIA";

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
	 * The field for holding the selected country or continent geo json data.
	 * This will only hold data for the country boundary.
	 */
	protected Object regionJson;

	/**
	 * The features json includes weather stations with thier corresponding
	 * climatic information (min & max temperature, precipitation). These
	 * features will be loaded on the Google Map
	 */
	protected Map<String, Object> featuresJson = new LinkedHashMap<String, Object>();
	/**
	 * The corresponding country states geojson data
	 */
	// private Object statesGeoJson;
	/**
	 * The series data map
	 */
	private Map<String, Object> seriesJson;
	/**
	 * The JSON for the growing regions or areas for the currently selected crop
	 * and cultiva from the selected country or Continent.
	 */
	private Object growingRegionsJson;

	/**
	 * The municipalities Geo JSON object
	 */
	private Object municipalitiesJson;

	public String execute() {

		// Retrieve the soil GeoJson data from the database
		if (getSelectedCountry() != null) {
			 log.info("COUNTRY: " + selectedCountry);
			this.setRegion(tpeService.getRegionById(getSelectedCountry()));
			setLat(getRegion().getLatitude());
			setLng(getRegion().getLongitude());
			this.setZoom(this.getRegion().getZoom());
			 log.info("Loaded Region, Now loading GeoJson");
			boolean continent = false;
			if (getRegion().getCategory().getName().equals(CATEGORY_CONTINENT))
				continent = true;
			 log.info("Region is a continent: "+continent);
			// TODO Initially dont consider selection of climate indicators
			this.setFeaturesJson(tpeService.getClimateGeoJSON(
					this.getSelectedCountry(), null, continent));
			 log.info("Loaded features json");
			// Get the climate series data from the database
			// TODO Get series separately for each hovered station
			// seriesJson =
			// tpeService.getClimateSeries(getSelectedCountry(),continent);
			// log.info("Loaded series data");

			// log.info("Loading RegionJSON file");

			// Load the crop growing areas json file for the selected region.
			// TODO Add the select option for crop for climate
			// TODO Remove constant for crop
			try {
				setGrowingRegionsJson(Utils.readJSON(CROP_RICE, region
						.getName().toLowerCase(), JSON_MAP_GROWING));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				log.error(e.getMessage());
			}

			 log.info("Loaded growing regions");
			// Add or load the country Json data
			try {
				regionJson = Utils.loadGeoJSON(getRegion().getName()
						.toLowerCase(), JSON_REGION);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				log.error(e.getMessage());
			}
			 log.info("Loaded country json");
			// Add municipalities JSON
			try {
				setMunicipalitiesJson(Utils.loadGeoJSON(getRegion().getName(),
						JSON_MUNICIPIOS));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				log.error(e.getMessage());
			}

			 log.info("Added municipios");
		}

		// setCountryGeoJson(Utils.loadJSON(this.getPath() + "script/" +
		// getRegion().getName().toUpperCase() + ".geo.json"));
		// Load states geo json data

		// if (getRegion().getName().toUpperCase().equals(COUNTRY_COLOMBIA))
		// this.setStatesGeoJson(Utils.loadJSONData(this.getPath() + "script/"
		// + getRegion().getName().toUpperCase() + ".CLIMATE.geo.json"));
		// else
		// this.setStatesGeoJson(Utils.loadJSONData(this.getPath() + "script/"
		// + getRegion().getName().toUpperCase() + ".STATES.geo.json"));

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

	public Object getRegionJson() {
		return regionJson;
	}

	public void setRegionJson(Object regionJson) {
		this.regionJson = regionJson;
	}

	public Map<String, Object> getFeaturesJson() {
		return featuresJson;
	}

	public void setFeaturesJson(Map<String, Object> featuresJson) {
		this.featuresJson = featuresJson;
	}

	public Object getGrowingRegionsJson() {
		return growingRegionsJson;
	}

	public void setGrowingRegionsJson(Object growingRegionsJson) {
		this.growingRegionsJson = growingRegionsJson;
	}

	public Object getMunicipalitiesJson() {
		return municipalitiesJson;
	}

	public void setMunicipalitiesJson(Object municipalitiesJson) {
		this.municipalitiesJson = municipalitiesJson;
	}

	public Map<String, Object> getSeriesJson() {
		return seriesJson;
	}

	public void setSeriesJson(Map<String, Object> seriesJson) {
		this.seriesJson = seriesJson;
	}

}