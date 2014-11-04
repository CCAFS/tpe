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
import org.cgiar.dapa.ccafs.tpe.entity.Crop;
import org.cgiar.dapa.ccafs.tpe.entity.Cultivar;
import org.cgiar.dapa.ccafs.tpe.entity.Property;
import org.cgiar.dapa.ccafs.tpe.entity.Region;
import org.cgiar.dapa.ccafs.tpe.entity.Soil;
import org.cgiar.dapa.ccafs.tpe.entity.Station;
import org.cgiar.dapa.ccafs.tpe.entity.WindowSowing;
import org.cgiar.dapa.ccafs.tpe.util.ParamType;
import org.cgiar.dapa.ccafs.tpe.util.Utils;

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
	private Log log = LogFactory.getLog(this.getClass());
	private List<Category> outputs;
	private Integer selectedOutput;
	private Integer preselectedOutput;
	private List<Soil> textures;
	private List<Integer> selectedTextures;
	private Integer preselectedTexture;
	private List<Region> countries;
	private Integer selectedCountry;
	private Integer preselectedCountry;
	private List<Property> properties;
	private List<Integer> selectedProperties;
	private Integer preselectedProperty;
	private List<Region> regions;
	private List<Integer> selectedRegions;
	private List<Integer> preselectedRegions;
	private List<String> years;
	private List<String> selectedYears;
	private String preselectedYear;
	private List<Station> stations;
	private List<Integer> selectedStations;
	private List<Integer> preselectedStations;
	private List<Crop> crops;
	private Integer selectedCrop;
	private Integer preselectedCrop;
	private List<Cultivar> cultivars;
	private Integer selectedCultivar;
	private Integer preselectedCultivar;
	private List<WindowSowing> swindows;
	private Integer preselectedWindow;
	private Integer selectedWindow;

	/**
	 * This method will load the output variables.
	 */
	public String execute() {
		// Retrieve the out put variables from the database
		outputs = tpeService.getOutputs();
		if (!outputs.isEmpty() && outputs != null)
			// Make sure the outputs list is not empty or null, before setting
			// the default preselect value
			preselectedOutput = outputs.get(1).getId();
		else
			// If empty or null, then initialize the outputs with the empty
			// arraylist
			outputs = new ArrayList<Category>();
		return ActionSupport.SUCCESS;
	}

	/**
	 * Loads the soil, tpe and climate parameters or variables from the database
	 * 
	 * @return
	 */
	public String paramsOut() {
		// Retrieve the countries from the database
		countries = tpeService.getCountries();
		// Initialize the years
		years = new ArrayList<String>();
		if (!countries.isEmpty() && countries != null) {
			// Preselect the country
			preselectedCountry = countries.get(0).getId();
			// Get the sub regions
			regions = tpeService.getSubregionsByCountry(preselectedCountry);
			if (!regions.isEmpty() && regions != null)
				preselectedRegions = Utils.getRegionIds(regions);
		} else {
			countries = new ArrayList<Region>();
			regions = new ArrayList<Region>();
			preselectedRegions = new ArrayList<Integer>();
		}

		if (this.getSelectedOutput().equals(ParamType.TPE.getId())) {

			// Retrieve all the crops from the database
			crops = tpeService.getAllCrops();

			if (!crops.isEmpty() && crops != null) {
				// Preselect the crop
				preselectedCrop = crops.get(0).getId();
				// Get the crop cultivars for the preselected crop
				cultivars = tpeService.getCultivarsByCrop(preselectedCrop);
				// Preselect the crop cultivar
				if (!cultivars.isEmpty() && cultivars != null) {
					preselectedCultivar = cultivars.get(0).getId();
					// Retrieve the years based on the selected country and crop
					// cultivar
					years = tpeService.getTPEYears(countries.get(0).getId(),
							preselectedCultivar);
					if (!years.isEmpty() && years != null)
						preselectedYear = years.get(0);
					// Retrieve the sowing windows for the selected cultivar
					swindows = tpeService
							.getWindowSowingByCultivar(preselectedCultivar);

					if (!swindows.isEmpty() && swindows != null) {
						// Preselect the swindow
						preselectedWindow = swindows.get(0).getId();
					}
				}
			}

			return TPE;
		} else if (this.getSelectedOutput().equals(ParamType.SOIL.getId())) {

			textures = tpeService.getSoilTextures();
			if (!textures.isEmpty() && textures != null)
				preselectedTexture = textures.get(0).getId();
			else
				textures = new ArrayList<Soil>();
			// Get soil properties
			properties = tpeService.getSoilProperties();
			if (!properties.isEmpty() && properties != null)
				preselectedProperty = properties.get(0).getId();
			else
				properties = new ArrayList<Property>();
			return SOIL;
		} else if (this.getSelectedOutput().equals(ParamType.CLIMATE.getId())) {
			if (!countries.isEmpty() && countries != null) {
				// Get stations for the first country in the list
				stations = tpeService.getStationsByRegion(countries.get(0)
						.getId());
				if (!stations.isEmpty() && stations != null) {
					preselectedStations = Utils.getStationIds(stations);
				} else
					stations = new ArrayList<Station>();

				properties = tpeService.getClimateProperties();
				if (!properties.isEmpty() && properties != null)
					preselectedProperty = properties.get(0).getId();
				else
					properties = new ArrayList<Property>();
				// Get years
				// TODO Preselect the years based on the selected country and
				// climate property.
				years = tpeService.getClimateYears(countries.get(0).getId());
				if (!years.isEmpty() && years != null)
					preselectedYear = years.get(0);

			}
			return CLIMATE;
		}

		return ActionSupport.SUCCESS;
	}

	/**
	 * Retrieves the sub regions for the specified or selected country from the
	 * TPE page
	 * 
	 * @return
	 */
	public String subregionParams() {

		// Initialize the preselectedRegions and regions list with empty
		// arraylists
		preselectedRegions = new ArrayList<Integer>();
		regions = new ArrayList<Region>();

		if (selectedCountry != null) {
			// Get the sub regions
			regions = tpeService.getSubregionsByCountry(selectedCountry);
			// Preselect all the sub regions by default
			if (!regions.isEmpty() && regions != null)
				preselectedRegions = Utils.getRegionIds(regions);
		}

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

	public List<Soil> getTextures() {
		return textures;
	}

	public void setTextures(List<Soil> textures) {
		this.textures = textures;
	}

	public List<Integer> getSelectedTextures() {
		return selectedTextures;
	}

	public void setSelectedTextures(List<Integer> selectedTextures) {
		this.selectedTextures = selectedTextures;
	}

	public Integer getPreselectedTexture() {
		return preselectedTexture;
	}

	public void setPreselectedTexture(Integer preselectedTexture) {
		this.preselectedTexture = preselectedTexture;
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

	public List<Property> getProperties() {
		return properties;
	}

	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}

	public List<Integer> getSelectedProperties() {
		return selectedProperties;
	}

	public void setSelectedProperties(List<Integer> selectedProperties) {
		this.selectedProperties = selectedProperties;
	}

	public Integer getPreselectedProperty() {
		return preselectedProperty;
	}

	public void setPreselectedProperty(Integer preselectedProperty) {
		this.preselectedProperty = preselectedProperty;
	}

	public List<Region> getRegions() {
		return regions;
	}

	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}

	public List<Integer> getSelectedRegions() {
		return selectedRegions;
	}

	public void setSelectedRegions(List<Integer> selectedRegions) {
		this.selectedRegions = selectedRegions;
	}

	public List<Integer> getPreselectedRegions() {
		return preselectedRegions;
	}

	public void setPreselectedRegions(List<Integer> preselectedRegions) {
		this.preselectedRegions = preselectedRegions;
	}

	public List<String> getYears() {
		return years;
	}

	public void setYears(List<String> years) {
		this.years = years;
	}

	public List<String> getSelectedYears() {
		return selectedYears;
	}

	public void setSelectedYears(List<String> selectedYears) {
		this.selectedYears = selectedYears;
	}

	public String getPreselectedYear() {
		return preselectedYear;
	}

	public void setPreselectedYear(String preselectedYear) {
		this.preselectedYear = preselectedYear;
	}

	public List<Station> getStations() {
		return stations;
	}

	public void setStations(List<Station> stations) {
		this.stations = stations;
	}

	public List<Integer> getSelectedStations() {
		return selectedStations;
	}

	public void setSelectedStations(List<Integer> selectedStations) {
		this.selectedStations = selectedStations;
	}

	public List<Integer> getPreselectedStations() {
		return preselectedStations;
	}

	public void setPreselectedStations(List<Integer> preselectedStations) {
		this.preselectedStations = preselectedStations;
	}

	public List<Crop> getCrops() {
		return crops;
	}

	public void setCrops(List<Crop> crops) {
		this.crops = crops;
	}

	public Integer getSelectedCrop() {
		return selectedCrop;
	}

	public void setSelectedCrop(Integer selectedCrop) {
		this.selectedCrop = selectedCrop;
	}

	public Integer getPreselectedCrop() {
		return preselectedCrop;
	}

	public void setPreselectedCrop(Integer preselectedCrop) {
		this.preselectedCrop = preselectedCrop;
	}

	public List<Cultivar> getCultivars() {
		return cultivars;
	}

	public void setCultivars(List<Cultivar> cultivars) {
		this.cultivars = cultivars;
	}

	public Integer getSelectedCultivar() {
		return selectedCultivar;
	}

	public void setSelectedCultivar(Integer selectedCultivar) {
		this.selectedCultivar = selectedCultivar;
	}

	public Integer getPreselectedCultivar() {
		return preselectedCultivar;
	}

	public void setPreselectedCultivar(Integer preselectedCultivar) {
		this.preselectedCultivar = preselectedCultivar;
	}

	public List<WindowSowing> getSwindows() {
		return swindows;
	}

	public void setSwindows(List<WindowSowing> swindows) {
		this.swindows = swindows;
	}

	public Integer getPreselectedWindow() {
		return preselectedWindow;
	}

	public void setPreselectedWindow(Integer preselectedWindow) {
		this.preselectedWindow = preselectedWindow;
	}

	public Integer getSelectedWindow() {
		return selectedWindow;
	}

	public void setSelectedWindow(Integer selectedWindow) {
		this.selectedWindow = selectedWindow;
	}

}
