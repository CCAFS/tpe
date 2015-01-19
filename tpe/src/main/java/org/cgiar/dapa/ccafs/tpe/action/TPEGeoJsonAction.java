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
import org.cgiar.dapa.ccafs.tpe.util.Utils;

import com.opensymphony.xwork2.ActionSupport;

/**
 * The action that is responsible for the generation of TPE GeoJSON data for the
 * TPE Google Maps
 * 
 * @author NMATOVU
 *
 */
public class TPEGeoJsonAction extends BaseAction {

	private static final long serialVersionUID = 2409450891248252753L;

	@SuppressWarnings("unused")
	private Log log = LogFactory.getLog(this.getClass());

	/**
	 * The id of the selected crop cultivar
	 */
	private Integer selectedCultivar;

	/**
	 * The id of the selected sowing window;
	 */
	// private Integer selectedWindow;
	/**
	 * The id of the selected scenario
	 */
	// private String selectedScenario;
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
	// protected List<String> selectedYears;
	/**
	 * The field for holding the selected country geo json data from the json
	 * file from the server
	 */
	protected Object countryGeoJson;

	/**
	 * The TPE GeoJson map that will provide the GeoJson features on the Google
	 * Map
	 */
	private Object tpeGeoJson;

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
	private Object statesGeoJson;
	/**
	 * The tpe boundary geo json
	 */
	private Object tpeBoundaryJson;
	/**
	 * The list of box plot data
	 */
	private Map<String, Object> boxplotData = new LinkedHashMap<String, Object>();
	private List<String> categories = new LinkedList<String>();

	// private List<Object> categoriesList;
	// private List<Object> categoriesTempRain;
	// private List<Map<String, Object>> series;
	// private List<Map<String, Object>> plotBands;

	// private List<Map<String, Object>> seriesLai;

	// private List<Map<String, Object>> seriesPcew;

	// private List<Map<String, Object>> seriesRainCum;

	// private List<Map<String, Object>> seriesTempRain;
	/**
	 * The Highcharts categories for the LAI, PCEW, RAIN_CUM, RAIN_S,WAGT plots
	 */
	// Map<LAI, [1,2,3,4]>
	// private Map<String, List<Object>> categoriesStress;
	// private List<Object> categoriesStress;

	private Map<String, Object> seriesData;

	// private Map<String, Object> pcewData;
	// private Map<String, Object> rainCumData;
	// private Map<String, Object> wagtData;
	// private Map<String, Object> rainSData;
	// private Map<String, Object> rainTempData;

	public String execute() {
		// Retrieve the data that will be converted into GeoJson by this action
		// from the struts.xml
		// TODO Get the parameters from the session or pass them from the ajax
		// call.
		// log.info("selected cultivar: " + selectedCultivar);
		// log.info("selected crop: " + selectedCrop);
		if (getSelectedCountry() != null) {

			this.setRegion(tpeService.getRegionById(getSelectedCountry()));
			setLat(getRegion().getLatitude());
			setLng(getRegion().getLongitude());
			this.setZoom(this.getRegion().getZoom());

			// Loads the Country Region Geo json file.
			setCountryGeoJson(Utils.loadJSON(this.getPath() + "script/"
					+ getRegion().getName().toUpperCase() + ".geo.json"));

			// Load the states geo json data
			this.setStatesGeoJson(Utils.loadJSONData(this.getPath() + "script/"
					+ getRegion().getName().toUpperCase() + ".STATES.geo.json"));

			if (selectedCultivar != null) {

				this.setCultivar(tpeService.getCultivar(selectedCultivar));
				// Load the TPE geo json data
				this.setTpeGeoJson(Utils.loadJSONData(this.getPath()
						+ "script/" + getRegion().getName().toUpperCase() + "."
						+ getCultivar().getName().toUpperCase()
						+ ".TPE.geo.json"));

				// Get the categories for LAI, WAGT, etc
				// It is the same for all clusters and environments
				// TODO Add params.
				// categoriesStress =
				// tpeService.getStressCategories(Utils.getStressSeries(),
				// getCultivar().getId(),getSelectedCountry());

				// log.info("Getting the series data... ");

				seriesData = tpeService.getSeriesData(getCultivar().getId(),
						getSelectedCountry());
				// log.info(seriesData.size());
			}

			// Loads the TPE boundary for the selected country and crop
			this.setTpeBoundaryJson(Utils.loadJSONData(this.getPath()
					+ "script/" + getRegion().getName().toUpperCase()
					+ ".BOUNDARY.json"));

			boxplotData = tpeService.getTPEBox(getSelectedCountry(),
					getSelectedCultivar());

			// log.info(getSelectedCountry());
			// log.info(getSelectedCultivar());
			// log.info(dataJson);
			categories = tpeService.getTPEYears(getSelectedCountry(),
					getSelectedCultivar());
			// log.info(categories);

			// LAI
			// categoriesList = ChartUtil.categories();
			// seriesLai = ChartUtil.seriesLAI();
			// plotBands = ChartUtil.plotBands();

			// PCEW
			// seriesPcew = ChartUtil.seriesPCEW();

			// RAIN CUM
			// seriesRainCum = ChartUtil.seriesRainCum();

			// TEMP RAIN
			// setCategoriesTempRain(ChartUtil.categoriesTempRain());
			// seriesTempRain = ChartUtil.seriesTempRain();

		}

		// log.info(getCountryGeoJson());

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

	public Object getCountryGeoJson() {
		return countryGeoJson;
	}

	public void setCountryGeoJson(Object countryGeoJson) {
		this.countryGeoJson = countryGeoJson;
	}

	public Object getTpeGeoJson() {
		return tpeGeoJson;
	}

	public void setTpeGeoJson(Object tpeGeoJson) {
		this.tpeGeoJson = tpeGeoJson;
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

	public Object getStatesGeoJson() {
		return statesGeoJson;
	}

	public void setStatesGeoJson(Object statesGeoJson) {
		this.statesGeoJson = statesGeoJson;
	}

	public Object getTpeBoundaryJson() {
		return tpeBoundaryJson;
	}

	public void setTpeBoundaryJson(Object tpeBoundaryJson) {
		this.tpeBoundaryJson = tpeBoundaryJson;
	}

	public Map<String, Object> getBoxplotData() {
		return boxplotData;
	}

	public void setBoxplotData(Map<String, Object> boxplotData) {
		this.boxplotData = boxplotData;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public Map<String, Object> getSeriesData() {
		return seriesData;
	}

	public void setSeriesData(Map<String, Object> seriesData) {
		this.seriesData = seriesData;
	}

}
