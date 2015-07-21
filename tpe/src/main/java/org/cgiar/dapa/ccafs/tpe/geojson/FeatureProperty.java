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
package org.cgiar.dapa.ccafs.tpe.geojson;

/**
 * The GeoJson property class that represents the properties of the feature
 * 
 * @author NMATOVU
 *
 */
public class FeatureProperty extends BaseGeo {

	private static final long serialVersionUID = 1478063607893330103L;
	/**
	 * The name of the property.
	 */
	private String name;
	/**
	 * The yield attached to this property
	 */
	private Double yield;
	/**
	 * Name of the station
	 */
	private String stationName;
	/**
	 * Name of the station where the station is located
	 */
	private String regionName;
	/**
	 * The station number
	 */
	private Integer stationNumber;
	/**
	 * The soil texture
	 */
	private String soilTexture;

	/**
	 * The year that references the data
	 */
	private String year;
	/**
	 * The country
	 */
	private String country;

	// private Float waterCFCapacity;
	//
	// private Float availableSoilWater;
	//
	// private Float bulkDensity;
	//
	// private Float waterCWPoint;
	//
	// private Float depth;
	// private Float ph;
	private String category;
	private Float tmax;
	private Float tmin;
	private Float irradiance;
	private Float precipitation;
	private String source;
	private String author;
	/**
	 * The name of the crop cultivar
	 */
	private String cultivar;
	/**
	 * The color of the feature
	 */
	private String color;
	private String clusterName;
	private String cropName;
	private String windowTitle;
	private String scenarioName;
	/**
	 * The feature id
	 */
	private String id;

	public FeatureProperty() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FeatureProperty(String name, Double yield) {
		super();
		this.name = name;
		this.yield = yield;
	}

	public FeatureProperty(String name, Double yield, String stationName,
			String regionName, Integer stationNumber, String year) {
		super();
		this.name = name;
		this.yield = yield;
		this.stationName = stationName;
		this.regionName = regionName;
		this.stationNumber = stationNumber;
		this.year = year;
	}

	public FeatureProperty(String cultivar, String year, String regionName,
			String clusterColor, String clusterName, String cropName,
			String windowTitle, String scenarioName, String featureId) {
		super();
		this.cultivar = cultivar;
		this.year = year;
		this.regionName = regionName;
		this.color = clusterColor;
		this.clusterName = clusterName;
		this.cropName = cropName;
		this.windowTitle = windowTitle;
		this.scenarioName = scenarioName;
		this.setId(featureId);
	}

	public FeatureProperty(String stationName, Integer stationNumber,
			String regionName, String country) {
		super();
		this.stationName = stationName;
		this.stationNumber = stationNumber;
		this.regionName = regionName;
		this.country = country;
	}

	public FeatureProperty(String year, String category, String stationName,
			Integer stationNumber, String regionName, Float tmax, Float tmin,
			Float irradiance, Float precipitation, String source, String author) {
		super();
		this.year = year;
		this.category = category;
		this.stationName = stationName;
		this.stationNumber = stationNumber;
		this.regionName = regionName;
		this.tmax = tmax;
		this.tmin = tmin;
		this.irradiance = irradiance;
		this.precipitation = precipitation;
		this.source = source;
		this.author = author;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getYield() {
		return yield;
	}

	public void setYield(Double yield) {
		this.yield = yield;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public Integer getStationNumber() {
		return stationNumber;
	}

	public void setStationNumber(Integer stationNumber) {
		this.stationNumber = stationNumber;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getSoilTexture() {
		return soilTexture;
	}

	public void setSoilTexture(String soilTexture) {
		this.soilTexture = soilTexture;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Float getTmax() {
		return tmax;
	}

	public void setTmax(Float tmax) {
		this.tmax = tmax;
	}

	public Float getTmin() {
		return tmin;
	}

	public void setTmin(Float tmin) {
		this.tmin = tmin;
	}

	public Float getIrradiance() {
		return irradiance;
	}

	public void setIrradiance(Float irradiance) {
		this.irradiance = irradiance;
	}

	public Float getPrecipitation() {
		return precipitation;
	}

	public void setPrecipitation(Float precipitation) {
		this.precipitation = precipitation;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCultivar() {
		return cultivar;
	}

	public void setCultivar(String cultivar) {
		this.cultivar = cultivar;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public String getCropName() {
		return cropName;
	}

	public void setCropName(String cropName) {
		this.cropName = cropName;
	}

	public String getWindowTitle() {
		return windowTitle;
	}

	public void setWindowTitle(String windowTitle) {
		this.windowTitle = windowTitle;
	}

	public String getScenarioName() {
		return scenarioName;
	}

	public void setScenarioName(String scenarioName) {
		this.scenarioName = scenarioName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
