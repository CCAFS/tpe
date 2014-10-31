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
public class Property extends BaseGeo {

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

	private Float waterCFCapacity;

	private Float availableSoilWater;

	private Float bulkDensity;

	private Float waterCWPoint;

	private Float depth;
	private Float ph;

	public Property() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Property(String name, Double yield) {
		super();
		this.name = name;
		this.yield = yield;
	}

	public Property(String name, Double yield, String stationName,
			String regionName, Integer stationNumber, String year) {
		super();
		this.name = name;
		this.yield = yield;
		this.stationName = stationName;
		this.regionName = regionName;
		this.stationNumber = stationNumber;
		this.year = year;
	}

	public Property(String soilTexture, Float waterCFCapacity,
			Float availableSoilWater, Float bulkDensity, Float ph,
			String stationName, Float waterCWPoint, Float depth,
			String regionName) {
		super();
		this.soilTexture = soilTexture;
		this.waterCFCapacity = waterCFCapacity;
		this.availableSoilWater = availableSoilWater;
		this.bulkDensity = bulkDensity;
		this.ph = ph;
		this.stationName = stationName;
		this.waterCWPoint = waterCWPoint;
		this.depth = depth;
		this.regionName = regionName;
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

	public Float getWaterCFCapacity() {
		return waterCFCapacity;
	}

	public void setWaterCFCapacity(Float waterCFCapacity) {
		this.waterCFCapacity = waterCFCapacity;
	}

	public Float getAvailableSoilWater() {
		return availableSoilWater;
	}

	public void setAvailableSoilWater(Float availableSoilWater) {
		this.availableSoilWater = availableSoilWater;
	}

	public Float getBulkDensity() {
		return bulkDensity;
	}

	public void setBulkDensity(Float bulkDensity) {
		this.bulkDensity = bulkDensity;
	}

	public Float getWaterCWPoint() {
		return waterCWPoint;
	}

	public void setWaterCWPoint(Float waterCWPoint) {
		this.waterCWPoint = waterCWPoint;
	}

	public Float getDepth() {
		return depth;
	}

	public void setDepth(Float depth) {
		this.depth = depth;
	}

	public Float getPh() {
		return ph;
	}

	public void setPh(Float ph) {
		this.ph = ph;
	}

}
