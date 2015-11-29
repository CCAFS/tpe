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
	private Log LOG = LogFactory.getLog(this.getClass());

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
	// private Object regionJSON;
	/**
	 * The municipalities Geo JSON object
	 */
	// private Object municipalitiesJson;

	/**
	 * The TPE GeoJson map that will provide the GeoJson features on the Google
	 * Map
	 */

	// private Object featuresJson;

	/**
	 * The country or region latitude coordinate.
	 */
	protected Double lat;
	/**
	 * The country or region longitude to initialize the lng
	 */
	protected Double lng;

	/**
	 * The tpe boundary geo json
	 */
	// private Object tpeBoundaryJson;
	/**
	 * The list of box plot data
	 */
	private Map<String, Object> boxJson = new LinkedHashMap<String, Object>();
	private List<String> categoriesJson = new LinkedList<String>();

	/**
	 * The Highcharts categories for the LAI, PCEW, RAIN_CUM, RAIN_S,WAGT plots
	 */
	// Map<LAI, [1,2,3,4]>
	// private Map<String, List<Object>> categoriesStress;
	// private List<Object> categoriesStress;

	private Map<String, Object> seriesJson;
	/**
	 * The crop cultivar
	 */
	private Cultivar cultivar;

	private Boolean showhelp = true;

	public String execute() {

		// TODO Change country to region
		if (getSelectedCountry() != null) {
			// log.info("country not null: " + getSelectedCountry());
			this.setRegion(tpeService.getRegionById(getSelectedCountry()));
			setLat(getRegion().getLatitude());
			setLng(getRegion().getLongitude());
			this.setZoom(this.getRegion().getZoom());

			/*
			 * try { regionJSON = Utils.loadGeoJSON(getRegion().getName(),
			 * JSON_REGION); } catch (IOException e) { // TODO Auto-generated
			 * catch block // e.printStackTrace(); LOG.error(e.getMessage()); }
			 */

			/*
			 * try {
			 * setMunicipalitiesJson(Utils.loadGeoJSON(getRegion().getName(),
			 * JSON_MUNICIPIOS)); } catch (IOException e) { // TODO
			 * Auto-generated catch block // e.printStackTrace();
			 * LOG.error(e.getMessage()); }
			 */
			if (selectedCultivar != null) {
				// log.info("Cultivar:  " + selectedCultivar);
				this.setCultivar(tpeService.getCultivar(selectedCultivar));
				// Load the TPE geo json dat

				/*
				 * try { featuresJson =
				 * Utils.readJSON(this.getCultivar().getCrop()
				 * .getName().toLowerCase(), region.getName(), this
				 * .getCultivar().getName().toLowerCase(), JSON_MAP_TPE); }
				 * catch (IOException e) { // TODO Auto-generated catch block //
				 * e.printStackTrace(); LOG.error(e.getMessage()); }
				 */

				seriesJson = tpeService.getSeriesData(getCultivar().getId(),
						getSelectedCountry());

				/*
				 * try { tpeBoundaryJson =
				 * Utils.loadGeoJSON(getRegion().getName() .toLowerCase(),
				 * JSON_BOUNDARY); } catch (IOException e) { // TODO
				 * Auto-generated catch block // e.printStackTrace();
				 * LOG.error(e.getMessage()); }
				 */

				boxJson = tpeService.getTPEBox(getSelectedCountry(),
						getSelectedCultivar());

				categoriesJson = tpeService.getTPEYears(getSelectedCountry(),
						getSelectedCultivar());
			}

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

	public Map<String, Object> getSeriesJson() {
		return seriesJson;
	}

	public void setSeriesJson(Map<String, Object> seriesJson) {
		this.seriesJson = seriesJson;
	}

	public Cultivar getCultivar() {
		return cultivar;
	}

	public void setCultivar(Cultivar cultivar) {
		this.cultivar = cultivar;
	}

	public Boolean getShowhelp() {
		return showhelp;
	}

	public void setShowhelp(Boolean showhelp) {
		this.showhelp = showhelp;
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