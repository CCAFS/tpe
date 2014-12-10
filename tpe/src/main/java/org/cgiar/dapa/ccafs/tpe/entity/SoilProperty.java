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
package org.cgiar.dapa.ccafs.tpe.entity;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * This class represents the soil properties in the simulation model. Each soil
 * property is categorized as physical, chemical or hydraulic property. Each
 * soil texture or type has different property values from different regions or
 * stations.
 * <p>
 * Soil Property Categories
 * <ul>
 * <li>Chemical</li>
 * <li>Physical</li>
 * <li>Hydraulic</li>
 * </ul>
 * </p>
 * 
 * @author NMATOVU
 *
 */
@Entity
@Table(name = "soil_property")
@AttributeOverride(name = "id", column = @Column(name = "property_id"))
public class SoilProperty extends BaseResult {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7821763723455753821L;

	/**
	 * The category of this property (chemical, physical or hydraulic)
	 */
	// private Category category;
	/**
	 * The soils that relates to this record
	 */
	private Soil soil;
	/**
	 * The station that relates to this property
	 */
	private Station station;
	/**
	 * The model
	 */
	private Model model;
	/**
	 * The average organic carbon (chamical property)
	 */
	private Float organicCarbon;
	/**
	 * The water content at field capacity. (hydraulic property)
	 */
	private Float waterCFCapacity;
	/**
	 * The water capacity at wilting point and it follows under the hydraulic
	 * category.
	 */
	private Float waterCWpoint;
	/**
	 * The soil ph. The ph is categorized as a chemical properties.
	 */
	private Float ph;
	/**
	 * The soil depth of the soil layer
	 */
	private Float depth;
	/**
	 * The averagef soil organic matter
	 */
	private Float organicMatter;
	/**
	 * The Taxonomy
	 */
	private Integer taxnomy;
	/**
	 * Bulk density
	 */
	private Float bulkDensity;
	/**
	 * The cation exchange capacity;
	 */
	private Float cationExchange;
	/**
	 * Available soil water;
	 */
	private Float availableSoilWater;
	/**
	 * The longitude of the soil texture from a given region
	 */
	private Double longitude;
	/**
	 * The latitude of the soil texture from a given region
	 */
	private Double latitude;

	/**
	 * Soil property value. Since different properties have different data
	 * types, we chose to use string or object data type for all.
	 */
	// private Double propertyValue = 0d;
	/**
	 * The soil property
	 */
	// private Property property;

	@ManyToOne(targetEntity = Soil.class)
	@JoinColumn(name = "soil_id", referencedColumnName = "soil_id")
	public Soil getSoil() {
		return soil;
	}

	public void setSoil(Soil soil) {
		this.soil = soil;
	}

	@ManyToOne(targetEntity = Station.class)
	@JoinColumn(name = "station_id", referencedColumnName = "station_id")
	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	@ManyToOne(targetEntity = Model.class)
	@JoinColumn(name = "model_id", referencedColumnName = "model_id")
	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	@Column(name = "longitude")
	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Column(name = "latitude")
	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	/**
	 * The coordinates. The ordering of x and y are important, this means that
	 * when representing latitude and longitiude the order is
	 * [longitude,latitude].
	 * 
	 * @return coordinates [longitude,latitude]
	 */
	@Transient
	public List<Double> getCoordinates() {
		// return new LinkedList<Double>(Arrays.asList(this.getLatitude(),
		// this.getLongitude()));
		if (getLatitude() != null && getLongitude() != null)
			return new LinkedList<Double>(Arrays.asList(this.getLongitude(),
					this.getLatitude()));

		return new LinkedList<Double>();

	}

	@Column(name = "organic_carbon")
	public Float getOrganicCarbon() {
		return organicCarbon;
	}

	public void setOrganicCarbon(Float organicCarbon) {
		this.organicCarbon = organicCarbon;
	}

	@Column(name = "water_content_field_capacity")
	public Float getWaterCFCapacity() {
		return waterCFCapacity;
	}

	public void setWaterCFCapacity(Float waterCFCapacity) {
		this.waterCFCapacity = waterCFCapacity;
	}

	@Column(name = "water_capacity_wilt_point")
	public Float getWaterCWpoint() {
		return waterCWpoint;
	}

	public void setWaterCWpoint(Float waterCWpoint) {
		this.waterCWpoint = waterCWpoint;
	}

	@Column(name = "ph")
	public Float getPh() {
		return ph;
	}

	public void setPh(Float ph) {
		this.ph = ph;
	}

	@Column(name = "depth")
	public Float getDepth() {
		return depth;
	}

	public void setDepth(Float depth) {
		this.depth = depth;
	}

	@Column(name = "organic_matter")
	public Float getOrganicMatter() {
		return organicMatter;
	}

	public void setOrganicMatter(Float organicMatter) {
		this.organicMatter = organicMatter;
	}

	@Column(name = "taxonomy")
	public Integer getTaxnomy() {
		return taxnomy;
	}

	public void setTaxnomy(Integer taxnomy) {
		this.taxnomy = taxnomy;
	}

	@Column(name = "bulky_density")
	public Float getBulkDensity() {
		return bulkDensity;
	}

	public void setBulkDensity(Float bulkDensity) {
		this.bulkDensity = bulkDensity;
	}

	@Column(name = "cation_exchange")
	public Float getCationExchange() {
		return cationExchange;
	}

	public void setCationExchange(Float cationExchange) {
		this.cationExchange = cationExchange;
	}

	@Column(name = "available_soil_water")
	public Float getAvailableSoilWater() {
		return availableSoilWater;
	}

	public void setAvailableSoilWater(Float availableSoilWater) {
		this.availableSoilWater = availableSoilWater;
	}

}
