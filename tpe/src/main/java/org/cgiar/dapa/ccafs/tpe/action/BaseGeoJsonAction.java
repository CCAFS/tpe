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

import org.cgiar.dapa.ccafs.tpe.entity.Region;

/**
 * The base action for the GeoJson action classes.
 * 
 * @author NMATOVU
 *
 */
public abstract class BaseGeoJsonAction extends BaseAction {

	private static final long serialVersionUID = -3777587049139967101L;
	/**
	 * The country or region latitude
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
	protected List<Integer> selectedRegions;
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
	protected Region region;
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

	public Integer getZoom() {
		return zoom;
	}

	public void setZoom(Integer zoom) {
		this.zoom = zoom;
	}

	public Map<String, Object> getGeoJson() {
		return geoJson;
	}

	public void setGeoJson(Map<String, Object> geoJson) {
		this.geoJson = geoJson;
	}

	public Object getCountryGeoJson() {
		return countryGeoJson;
	}

	public void setCountryGeoJson(Object countryGeoJson) {
		this.countryGeoJson = countryGeoJson;
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

	public List<Integer> getSelectedRegions() {
		return selectedRegions;
	}

	public void setSelectedRegions(List<Integer> selectedRegions) {
		this.selectedRegions = selectedRegions;
	}

	public List<String> getSelectedYears() {
		return selectedYears;
	}

	public void setSelectedYears(List<String> selectedYears) {
		this.selectedYears = selectedYears;
	}

}
