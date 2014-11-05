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

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
	 * The soil GeoJson data.
	 */
	private Map<String, Object> tpeGeoJson;

	/**
	 * The id of the selected country
	 */
	private Integer country;
	/**
	 * The id of the selected crop cultivar
	 */
	private Integer cultivar;
	/**
	 * The selected year
	 */
	private String year;
	/**
	 * The id of the selected sowing window;
	 */
	private Integer swindow;
	/**
	 * The id of the selected scenario
	 */
	private Integer scenario;
	/**
	 * The id of the selected crop
	 */
	private Integer crop;

	public String execute() {
		// Retrieve the data that will be converted into GeoJson by this action
		// from the struts.xml
		// TODO Get the parameters from the session or pass them from the ajax
		// call.
		tpeGeoJson = tpeService.getTPEGeoJSON(cultivar, country, swindow, year,
				scenario);

		return ActionSupport.SUCCESS;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Integer getSwindow() {
		return swindow;
	}

	public void setSwindow(Integer swindow) {
		this.swindow = swindow;
	}

	public Integer getScenario() {
		return scenario;
	}

	public void setScenario(Integer scenario) {
		this.scenario = scenario;
	}

	public Map<String, Object> getTpeGeoJson() {
		return tpeGeoJson;
	}

	public void setTpeGeoJson(Map<String, Object> tpeGeoJson) {
		this.tpeGeoJson = tpeGeoJson;
	}

	public Integer getCountry() {
		return country;
	}

	public void setCountry(Integer country) {
		this.country = country;
	}

	public Integer getCultivar() {
		return cultivar;
	}

	public void setCultivar(Integer cultivar) {
		this.cultivar = cultivar;
	}

	public Integer getCrop() {
		return crop;
	}

	public void setCrop(Integer crop) {
		this.crop = crop;
	}

}
