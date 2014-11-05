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
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionSupport;

/**
 * This action contains the methods for the generation of the Station GeoJSON
 * data for the TPE Google Map
 * 
 * @author NMATOVU
 *
 */
public class StationGeoJsonAction extends BaseAction {

	private static final long serialVersionUID = 2439242303969872550L;
	@SuppressWarnings("unused")
	private Log log = LogFactory.getLog(this.getClass());
	private Map<String, Object> stations = new LinkedHashMap<String, Object>();
	private Integer countryId = 1;

	public String execute() {
		// TODO Pass the country id from jsp page using session key or ajax
		// request
		// Retrieve the stations from the database
		stations = tpeService.getStationGeoJson(countryId);
		return ActionSupport.SUCCESS;
	}

	public Map<String, Object> getStations() {
		return stations;
	}

	public void setStations(Map<String, Object> stations) {
		this.stations = stations;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

}
