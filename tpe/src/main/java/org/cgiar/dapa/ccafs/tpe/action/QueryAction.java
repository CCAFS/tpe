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
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cgiar.dapa.ccafs.tpe.entity.Category;
import org.cgiar.dapa.ccafs.tpe.entity.Region;
import org.cgiar.dapa.ccafs.tpe.util.Param;
import org.cgiar.dapa.ccafs.tpe.util.Utils;

import com.opensymphony.xwork2.ActionSupport;

/**
 * The TPE action provides the methods that load the selection variables for the
 * TPE, SOIL and CLIMATE modules.
 * 
 * @author NMATOVU
 *
 */
public class QueryAction extends BaseAction {
	private static final long serialVersionUID = -8836395406607171896L;
	@SuppressWarnings("unused")
	private Log log = LogFactory.getLog(this.getClass());
	private List<Category> outputs;
	private Integer selectedOutput;
	private Integer preselectedOutput;
	// private List<Soil> textures;
	// private List<Integer> selectedTextures;
	// private List<Integer> preselectedTextures;
	private List<Region> countries;
	private Integer selectedCountry;
	private Integer preselectedCountry;
	private List<Param> params;
	private Integer selectedParam;
	private Integer preselectedParam;

	/**
	 * This method will load the output variables.
	 */
	public String execute() {
		outputs = Utils.queryOptions();
		// 0:Climate// 1:Soil// 2=Stability// 3:TPE
		if (!outputs.isEmpty() && outputs != null)
			preselectedOutput = outputs.get(0).getId();

		countries = tpeService.getCountriesAndContinents();
		if (!countries.isEmpty() && countries != null)
			// Preselect the country// countries.size()-1// preselectedCountry =
			// countries.get(0).getId();
			preselectedCountry = countries.get(0).getId();
		else
			countries = new ArrayList<Region>();

		params = Utils.queryParams();
		if (!params.isEmpty() && params != null)
			preselectedParam = params.get(0).getId();

		return ActionSupport.SUCCESS;
	}

	public List<Category> getOutputs() {
		return outputs;
	}

	public void setOutputs(List<Category> outputs) {
		this.outputs = outputs;
	}

	public Integer getSelectedOutput() {
		return selectedOutput;
	}

	public void setSelectedOutput(Integer selectedOutput) {
		this.selectedOutput = selectedOutput;
	}

	public Integer getPreselectedOutput() {
		return preselectedOutput;
	}

	public void setPreselectedOutput(Integer preselectedOutput) {
		this.preselectedOutput = preselectedOutput;
	}

	public List<Region> getCountries() {
		return countries;
	}

	public void setCountries(List<Region> countries) {
		this.countries = countries;
	}

	public Integer getSelectedCountry() {
		return selectedCountry;
	}

	public void setSelectedCountry(Integer selectedCountry) {
		this.selectedCountry = selectedCountry;
	}

	public Integer getPreselectedCountry() {
		return preselectedCountry;
	}

	public void setPreselectedCountry(Integer preselectedCountry) {
		this.preselectedCountry = preselectedCountry;
	}

	public List<Param> getParams() {
		return params;
	}

	public void setParams(List<Param> params) {
		this.params = params;
	}

	public Integer getSelectedParam() {
		return selectedParam;
	}

	public void setSelectedParam(Integer selectedParam) {
		this.selectedParam = selectedParam;
	}

	public Integer getPreselectedParam() {
		return preselectedParam;
	}

	public void setPreselectedParam(Integer preselectedParam) {
		this.preselectedParam = preselectedParam;
	}

}