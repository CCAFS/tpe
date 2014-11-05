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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
	 * The soil GeoJson map that will provide the GeoJson features on the Google
	 * Map
	 */
	private Map<String, Object> soilGeoJson = new LinkedHashMap<String, Object>();
	/**
	 * The country id from the selection pane
	 */
	private Integer countryId;
	/**
	 * The soil properties from the selection pane
	 */
	private List<Integer> properties;
	/**
	 * The selected soil texture ids from the selection pane.
	 */
	private List<Integer> textures;

	public String execute() {
		// TODO Pass the country param from the jsp page.
		countryId = 1;
		// TODO Pass properties params from the jsp page.
		properties = new ArrayList<Integer>(Arrays.asList(1));
		// Retrieve the soil GeoJson data from the database
		soilGeoJson = tpeService.getSoilGeoJson(properties, countryId);

		return ActionSupport.SUCCESS;
	}

	public Map<String, Object> getSoilGeoJson() {
		return soilGeoJson;
	}

	public void setSoilGeoJson(Map<String, Object> soilGeoJson) {
		this.soilGeoJson = soilGeoJson;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public List<Integer> getProperties() {
		return properties;
	}

	public void setProperties(List<Integer> properties) {
		this.properties = properties;
	}

	public List<Integer> getTextures() {
		return textures;
	}

	public void setTextures(List<Integer> textures) {
		this.textures = textures;
	}

}
