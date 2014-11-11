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
	private Double lat;
	/**
	 * The country or region longitude to initialize the lng
	 */
	private Double lng;
	/**
	 * The selected TPE output from the jsp page
	 */
	private String output;
	/**
	 * The id of the selected country from the jsp page
	 */
	private Integer country;
	/**
	 * The selected sub regions from the jsp page
	 */
	private List<Integer> regions;
	/**
	 * The default Google Map zoom
	 */
	private Integer zoom=4;
	/**
	 * The selected list of years
	 */
	private List<String> years;
	/**
	 * The field for holding the selected country geo json data from the json
	 * file from the server
	 */
	private Object countryGeoJson;

	/**
	 * The soil GeoJson map that will provide the GeoJson features on the Google
	 * Map
	 */
	private Map<String, Object> geoJson = new LinkedHashMap<String, Object>();

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

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public Integer getCountry() {
		return country;
	}

	public void setCountry(Integer country) {
		this.country = country;
	}

	public List<Integer> getRegions() {
		return regions;
	}

	public void setRegions(List<Integer> regions) {
		this.regions = regions;
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

	public List<String> getYears() {
		return years;
	}

	public void setYears(List<String> years) {
		this.years = years;
	}

	public Object getCountryGeoJson() {
		return countryGeoJson;
	}

	public void setCountryGeoJson(Object countryGeoJson) {
		this.countryGeoJson = countryGeoJson;
	}

}
