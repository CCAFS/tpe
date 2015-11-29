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
 * The TPE action provides the methods that load the selection variables for the
 * TPE, SOIL and CLIMATE modules.
 * 
 * @author NMATOVU
 *
 */
public class TPEAction extends BaseAction {

	private static final long serialVersionUID = -4801328465223186532L;

	@SuppressWarnings("unused")
	private Log LOG = LogFactory.getLog(this.getClass());
	/**
	 * Default parameter from jspe page (climate, soil, stability or tpe).
	 */
	private String param = null;

	private Map<Integer, String> maps;
	private Map<Integer, String> regions;
	private Map<Integer, String> crops;
	private Map<Integer, Map<Integer, String>> cultivars;

	/**
	 * This method will load the output variables.
	 */
	public String execute() {
		// Retrieve the out put variables from the database
		// outputs = tpeService.getOutputs();

		maps = tpeService.getMapOptions();
		// if (maps != null && !maps.isEmpty())
		// Make sure the outputs list is not empty or null, before
		// setting
		// the default preselect value
		// 0:Climate
		// 1:Soil
		// 2=Stability
		// 3:TPE
		regions = tpeService.getRegions();
		// countries.size()-1
		// preselectedCountry = countries.get(0).getId();
		crops = tpeService.getCrops();

		cultivars = tpeService.getCultivars();

		return ActionSupport.SUCCESS;
	}

	/**
	 * This method handles the display of years
	 * 
	 * @return
	 */
	public String loadYears() {

		return YEARS;

	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public Map<Integer, String> getMaps() {
		return maps;
	}

	public void setMaps(Map<Integer, String> maps) {
		this.maps = maps;
	}

	public Map<Integer, String> getRegions() {
		return regions;
	}

	public void setRegions(Map<Integer, String> regions) {
		this.regions = regions;
	}

	public Map<Integer, String> getCrops() {
		return crops;
	}

	public void setCrops(Map<Integer, String> crops) {
		this.crops = crops;
	}

	public Map<Integer, Map<Integer, String>> getCultivars() {
		return cultivars;
	}

	public void setCultivars(Map<Integer, Map<Integer, String>> cultivars) {
		this.cultivars = cultivars;
	}

}