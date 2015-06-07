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
import org.cgiar.dapa.ccafs.tpe.entity.Region;
import org.cgiar.dapa.ccafs.tpe.util.ParamType;

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
	// private List<Property> properties;
	// private List<Integer> selectedProperties;
	// private Integer preselectedProperty;

	// private List<Property> indicators;
	// private List<Integer> selectedIndicators;
	// private Integer preselectedIndicator;

	// private List<Region> regions;
	// private List<Integer> selectedRegions;
	// private List<Integer> preselectedRegions;
	// private List<String> years;
	// private List<String> selectedYears;
	// private String preselectedYear;
	/*
	 * private List<Station> stations; private List<Integer> selectedStations;
	 * private List<Integer> preselectedStations;
	 */
	private List<Crop> crops;
	private Integer selectedCrop;
	private Integer preselectedCrop;
	private List<Cultivar> cultivars;
	private Integer selectedCultivar;
	private Integer preselectedCultivar;

	// private List<WindowSowing> swindows;
	// private Integer preselectedWindow;
	// private Integer selectedWindow;
	// private List<String> scenarios;
	// private String selectedScenario;
	// private Integer preselectedScenario;

	/**
	 * This method will load the output variables.
	 */
	public String execute() {
		// Retrieve the out put variables from the database
		outputs = tpeService.getOutputs();
		if (!outputs.isEmpty() && outputs != null) {
			// Make sure the outputs list is not empty or null, before setting
			// the default preselect value
			//1
			preselectedOutput = outputs.get(1).getId();

			log.info("Output not null: " + preselectedOutput);
		} else {
			// If empty or null, then initialize the outputs with the empty
			// arraylist
			outputs = new ArrayList<Category>();

			log.info("Output is null");
		}

		// Object regionGeoJSON = Utils.loadGeoJSON("brazil", JSON_REGION);
		// log.info(regionGeoJSON);

		// Object statesGeoJson = Utils.loadGeoJSON("brazil", JSON_STATES);
		// log.info(statesGeoJson);

		// Object tpeGeoJson = Utils.readJSON("rice", "brazil", "brsprimavera");
		// log.info(tpeGeoJson);

		return ActionSupport.SUCCESS;
	}

	/**
	 * Loads the soil, tpe and climate parameters or variables from the database
	 * 
	 * @return
	 */
	public String paramsOut() {
		// Retrieve the countries from the database
		// countries = tpeService.getCountries();
		// Retrieve countries and continents (Latin America)
		countries = tpeService.getCountriesAndContinents();

		// Initialize the years
		// years = new ArrayList<String>();
		if (!countries.isEmpty() && countries != null) {
			// Preselect the country
			//countries.size()-1
			preselectedCountry = countries.get(countries.size()-1).getId();
			log.info("Country Id: " + preselectedCountry);
			// TODO Ignore the sub regions and stations in the beta version
			// Get the sub regions
			/*
			 * regions = tpeService.getSubregionsByCountry(preselectedCountry);
			 * if (!regions.isEmpty() && regions != null) preselectedRegions =
			 * Utils.getRegionIds(regions);
			 */
		} else
			countries = new ArrayList<Region>();
		/*
		 * regions = new ArrayList<Region>(); preselectedRegions = new
		 * ArrayList<Integer>();
		 */
		if (this.getSelectedOutput() != null) {
			log.info("Output not !=null: " + selectedOutput);
			// Get the name of the selected output from the database
			String outputName = tpeService
					.getCategoryById(this.getSelectedOutput()).getName()
					.toUpperCase();
			log.info("Output Name: " + outputName);
			if (outputName.equals(ParamType.TPE.name())
					|| outputName.equals(ParamType.STABILITY.name())
					|| outputName.equals(ParamType.AREA.name())) {
				log.info("Output Name equals Stability: " + outputName);
				// Retrieve all the crops from the database
				crops = tpeService.getAllCrops();
				// Generate the scenarios from the utils class
				// scenarios = Utils.getScenarios();
				// if (!scenarios.isEmpty() && scenarios != null)
				// // Select the first item in the list: zero based index
				// preselectedScenario = 0;

				if (!crops.isEmpty() && crops != null) {

					// Preselect the crop
					preselectedCrop = crops.get(0).getId();
					// Get the crop cultivars for the preselected crop
					cultivars = tpeService.getCultivarsByCrop(preselectedCrop);
					log.info("Crop not null: " + preselectedCrop);
					// Preselect the crop cultivar
					if (!cultivars.isEmpty() && cultivars != null) {

						preselectedCultivar = cultivars.get(0).getId();

						log.info("Cultivar not null: " + preselectedCultivar);

						// Retrieve the years based on the selected country and
						// crop
						// cultivar
						/*
						 * years =
						 * tpeService.getTPEYears(countries.get(0).getId(),
						 * preselectedCultivar); if (!years.isEmpty() && years
						 * != null) preselectedYear = years.get(0);
						 */
						// Retrieve the sowing windows for the selected cultivar
						/*
						 * swindows = tpeService
						 * .getWindowSowingByCultivar(preselectedCultivar);
						 * 
						 * if (!swindows.isEmpty() && swindows != null) { //
						 * Preselect the swindow preselectedWindow =
						 * swindows.get(0).getId(); }
						 */
					}
				}

				return TPE;
			} else if (outputName.equals(ParamType.SOIL.name())) {

				// textures = tpeService.getSoilTextures();
				// if (!textures.isEmpty() && textures != null)
				// // preselectedTexture = textures.get(0).getId();
				// // preselectedTextures = textures.get(0).getId();
				//
				// setPreselectedTextures(Utils.getTextureId(textures));
				// else
				// textures = new ArrayList<Soil>();
				// Get soil properties
				// properties = tpeService.getSoilProperties();
				// if (!properties.isEmpty() && properties != null) {
				// preselectedProperty = properties.get(3).getId();
				// // log.info("Soil Properties: " + properties.size());
				// } else
				// properties = new ArrayList<Property>();
				return SOIL;
			} else if (outputName.equals(ParamType.CLIMATE.name())) {
				// The climate indicator will be selected before the year is
				// selected
				// indicators = tpeService.getClimateProperties();
				// if (!indicators.isEmpty() && indicators != null)
				// preselectedIndicator = indicators.get(0).getId();
				// else
				// indicators = new ArrayList<Property>();

				// if (!countries.isEmpty() && countries != null) {
				// TODO The stations and sub regions will be ignored in the beta
				// version but will be consired in the improved version
				// Get stations for the first country in the list
				/*
				 * stations = tpeService.getStationsByRegion(countries.get(0)
				 * .getId()); if (!stations.isEmpty() && stations != null) {
				 * preselectedStations = Utils.getStationIds(stations); } else
				 * stations = new ArrayList<Station>();
				 */

				// Get years
				// TODO Preselect the years based on the selected country and
				// climate property.

				// The years will be populated basing on the selected year (s)
				/*
				 * years = tpeService.getClimateYears(countries.get(0).getId());
				 * if (!years.isEmpty() && years != null) preselectedYear =
				 * years.get(0);
				 */

				// TODO Add the dates FROM and TO

				// }
				return CLIMATE;
			}
		}

		return ActionSupport.SUCCESS;
	}

	/**
	 * This method handles the display of sub regions
	 * 
	 * @return
	 */
	public String loadSubregions() {

		/*
		 * preselectedRegions = new ArrayList<Integer>(); regions = new
		 * ArrayList<Region>(); if (selectedCountry != null) { // Get the sub
		 * regions regions = tpeService.getSubregionsByCountry(selectedCountry);
		 * // Preselect all the sub regions by default if (!regions.isEmpty() &&
		 * regions != null) preselectedRegions = Utils.getRegionIds(regions); }
		 */
		return SUBREGIONS;
	}

	/**
	 * This method handles the display of crop cultivars
	 * 
	 * @return
	 */
	public String loadCultivars() {
		// Retrieve the crop cultivars for the selected crop
		if (selectedCrop != null) {
			cultivars = tpeService.getCultivarsByCrop(selectedCrop);
			log.info("# of cultivars: " + cultivars.size());
			if (!cultivars.isEmpty() && cultivars != null)
				// Preselect the id of the first crop cultivar in the
				// list
				preselectedCultivar = cultivars.get(0).getId();
			// log.info("# of cultivars: " + cultivars.size());
		}
		return CULTIVARS;

	}

	/**
	 * This method handles the display of years
	 * 
	 * @return
	 */
	public String loadYears() {

		// Retrieve the years for the selected region based on the climate
		/*
		 * if ((selectedCultivar == null) && selectedCountry != null) { years =
		 * tpeService.getClimateYears(getSelectedCountry());
		 * 
		 * } else if (selectedCultivar != null && selectedCountry != null) { //
		 * Retrieve the years based on the crop cultivar and country years =
		 * tpeService.getTPEYears(selectedCountry, selectedCultivar); } else
		 * years = new ArrayList<String>();
		 * 
		 * if (!years.isEmpty() && years != null) // Preselect the first year in
		 * the list preselectedYear = years.get(0); // log.info("# of years: " +
		 * years.size());
		 */
		return YEARS;

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

}
