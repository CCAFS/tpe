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
import org.cgiar.dapa.ccafs.tpe.entity.Cultivar;
import org.cgiar.dapa.ccafs.tpe.util.Utils;

import com.opensymphony.xwork2.ActionSupport;

/**
 * The action that is responsible for the generation of Stability GeoJSON data
 * for the Stability Google Maps
 * 
 * @author NMATOVU
 *
 */
public class StabilityGeoJsonAction extends BaseAction {

	private static final long serialVersionUID = -6047311965565977530L;

	private Log log = LogFactory.getLog(this.getClass());

	/**
	 * The id of the selected crop cultivar
	 */
	private Integer selectedCultivar;

	/**
	 * The id of the selected crop
	 */
	private Integer selectedCrop;
	/**
	 * The selected TPE output from the jsp page
	 */
	protected String selectedOutput;
	/**
	 * The id of the selected country from the jsp page
	 */
	protected Integer selectedCountry;

	/**
	 * The default Google Map zoom
	 */
	protected Integer zoom = 4;

	/**
	 * The country (Colombia or Brazil) or region(Latin America) geo JSON object
	 */
	private Object regionJson;
	/**
	 * The municipalities Geo JSON object
	 */
	private Object municipalitiesJson;

	/**
	 * The Stability GeoJson map that will provide the GeoJson features on the
	 * Google Map
	 */

	private Object featuresJson;

	/**
	 * The country or region latitude coordinate.
	 */
	protected Double lat;
	/**
	 * The country or region longitude to initialize the lng
	 */
	protected Double lng;
	/**
	 * The corresponding country states geojson data
	 */
	// private Object statesGeoJson;
	/**
	 * The tpe boundary geo json
	 */
	private Object tpeBoundaryJson;
	/**
	 * The list of box plot data
	 */
	private Map<String, Object> boxJson = new LinkedHashMap<String, Object>();
	private List<String> categoriesJson = new LinkedList<String>();

	/**
	 * The Highcharts categories for the LAI, PCEW, RAIN_CUM, RAIN_S,WAGT plots
	 */

	private Map<String, Object> seriesJson;
	/**
	 * The crop cultivar
	 */
	private Cultivar cultivar;

	public String execute() {

		hs = hsr.getSession();

		// Retrieve the data that will be converted into GeoJson by this action
		// from the struts.xml
		// TODO Get the parameters from the session or pass them from the ajax
		// call. 
		// TODO Change country to region
		if (getSelectedCountry() != null) { 
			this.setRegion(tpeService.getRegionById(getSelectedCountry()));
			setLat(getRegion().getLatitude());
			setLng(getRegion().getLongitude());
			this.setZoom(this.getRegion().getZoom());

			// Loads the Country(Brazil, Colombia) or Region (Latin America) Geo
			// json file.
			// setCountryGeoJson(Utils.loadJSON(this.getPath() + "script/"
			// + getRegion().getName().toUpperCase() + ".geo.json"));
			// Loads the Country(Brazil, Colombia) or Region (Latin America) Geo
			// json file.
			regionJson = Utils.loadGeoJSON(getRegion().getName(), JSON_REGION); 
			// Load the states geo json data
			// this.setStatesGeoJson(Utils.loadJSONData(this.getPath() +
			// "script/"
			// + getRegion().getName().toUpperCase() + ".STATES.geo.json"));

			// TODO Don't load the states or polygon json objects
			/*
			 * statesGeoJson = Utils.loadGeoJSON(getRegion().getName(),
			 * JSON_STATES);
			 */

			municipalitiesJson = Utils.loadGeoJSON(getRegion().getName(),
					JSON_MUNICIPIOS);
			if (selectedCultivar != null) {
				// log.info("Cultivar not null:  " + selectedCultivar);
				this.setCultivar(tpeService.getCultivar(selectedCultivar));
				// Load the TPE geo json dat

				// this.setTpeGeoJson(Utils.loadJSONData(this.getPath()+
				// "script/" + getRegion().getName().toUpperCase() + "."+
				// getCultivar().getName().toUpperCase()+ ".TPE.geo.json"));
				// TODO Get selected crop and cultivar params to load the
				// stability
				// object
				// stabilityGeoJson = Utils.readJSON("rice", region.getName(),
				// "brsprimavera", JSON_MAP_STABILITY);

				setFeaturesJson(Utils.readJSON(this.getCultivar().getCrop()
						.getName().toLowerCase(), region.getName(), this
						.getCultivar().getName().toLowerCase(),
						JSON_MAP_STABILITY));

				// Get the categories for LAI, WAGT, etc
				// It is the same for all clusters and environments
				// TODO Add params.
				// categoriesStress =
				// tpeService.getStressCategories(Utils.getStressSeries(),
				// getCultivar().getId(),getSelectedCountry());

				seriesJson = tpeService.getSeriesData(getCultivar().getId(),
						getSelectedCountry());
			}

			// Loads the TPE boundary for the selected country and crop
			// this.setTpeBoundaryJson(Utils.loadJSONData(this.getPath()
			// + "script/" + getRegion().getName().toUpperCase()
			// + ".BOUNDARY.json"));

			// tpeBoundaryJson = Utils.loadGeoJSON(getRegion().getName(),
			// JSON_BOUNDARY);

			tpeBoundaryJson = Utils.loadGeoJSON(getRegion().getName(),
					JSON_BOUNDARY);
			boxJson = tpeService.getTPEBox(getSelectedCountry(),
					getSelectedCultivar());

			categoriesJson = tpeService.getTPEYears(getSelectedCountry(),
					getSelectedCultivar());
		}

		return ActionSupport.SUCCESS;
	}

	public Integer getSelectedCultivar() {
		return selectedCultivar;
	}

	public void setSelectedCultivar(Integer selectedCultivar) {
		this.selectedCultivar = selectedCultivar;
	}

	public Integer getSelectedCrop() {
		return selectedCrop;
	}

	public void setSelectedCrop(Integer selectedCrop) {
		this.selectedCrop = selectedCrop;
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

	public Object getTpeBoundaryJson() {
		return tpeBoundaryJson;
	}

	public void setTpeBoundaryJson(Object tpeBoundaryJson) {
		this.tpeBoundaryJson = tpeBoundaryJson;
	}

	public Cultivar getCultivar() {
		return cultivar;
	}

	public void setCultivar(Cultivar cultivar) {
		this.cultivar = cultivar;
	}

	public Object getFeaturesJson() {
		return featuresJson;
	}

	public void setFeaturesJson(Object featuresJson) {
		this.featuresJson = featuresJson;
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

	public Map<String, Object> getSeriesJson() {
		return seriesJson;
	}

	public void setSeriesJson(Map<String, Object> seriesJson) {
		this.seriesJson = seriesJson;
	}

	public List<String> getCategoriesJson() {
		return categoriesJson;
	}

	public void setCategoriesJson(List<String> categoriesJson) {
		this.categoriesJson = categoriesJson;
	}

	public Map<String, Object> getBoxJson() {
		return boxJson;
	}

	public void setBoxJson(Map<String, Object> boxJson) {
		this.boxJson = boxJson;
	}

}
