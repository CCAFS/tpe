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

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * This class represents the climate in the simulation model
 * 
 * @author NMATOVU
 *
 */
@Entity
@Table(name = "climate")
@AttributeOverride(name = "id", column = @Column(name = "climate_id"))
public class Climate extends BaseResult {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6058420084256307174L;

	/**
	 * The day, it is numeric
	 */
	private Integer day;
	/**
	 * The daily minimum temperature.
	 */
	// private Float tmin;
	/**
	 * The daily maximum temperature
	 */
	// private Float tmax;
	/**
	 * The daily irradiance
	 */
	// private Float irradiance;

	private Property property;
	/**
	 * The category of the climate
	 */
	private Category category;
	/**
	 * The weather station that relates to this climate data
	 */
	private Station station;
	/**
	 * The daily precipitation
	 */
	// private Float precipitation;
	/**
	 * The region that relates to this climate from the given station
	 */
	// private Region region;
	/**
	 * The year in which the climate was recorded
	 */
	private String year;
	/**
	 * The source of the climatic or weather data
	 */
	private String source;
	/**
	 * The author that provided the climatic data
	 */
	private String author;
	/**
	 * The date the when the climate was recorded.
	 */
	private Date recordedOn;
	/**
	 * The value of the climate property
	 */
	private Double propertyValue;
	/**
	 * The date the climate data was recorded.
	 */
	private Date date;

	@Column
	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	@ManyToOne(targetEntity = Category.class)
	@JoinColumn(name = "category_id", referencedColumnName = "category_id")
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@ManyToOne(targetEntity = Station.class)
	@JoinColumn(name = "station_id", referencedColumnName = "station_id")
	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	// @ManyToOne(targetEntity = Region.class)
	// @JoinColumn(name = "region_id", referencedColumnName = "region_id")
	// public Region getRegion() {
	// return region;
	// }
	//
	// public void setRegion(Region region) {
	// this.region = region;
	// }

	@Column
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Column
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Column
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Column(name = "recordedOn")
	public Date getRecordedOn() {
		return recordedOn;
	}

	public void setRecordedOn(Date recordedOn) {
		this.recordedOn = recordedOn;
	}

	@ManyToOne(targetEntity = Property.class)
	@JoinColumn(name = "property_id", referencedColumnName = "property_id")
	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	@Column(name = "property_value")
	public Double getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(Double propertyValue) {
		this.propertyValue = propertyValue;
	}

	@Column(name = "date")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
