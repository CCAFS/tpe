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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Climate extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6058420084256307174L;
	/**
	 * The primary key of the climate.
	 */
	private Integer id;
	/**
	 * The day, it is numeric
	 */
	private Integer day;
	/**
	 * The daily minimum temperature.
	 */
	private Float tmin;
	/**
	 * The daily maximum temperature
	 */
	private Float tmax;
	/**
	 * The daily irradiance
	 */
	private Float irradiance;
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
	private Float precipitation;
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

	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column
	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	@Column
	public Float getTmin() {
		return tmin;
	}

	public void setTmin(Float tmin) {
		this.tmin = tmin;
	}

	@Column
	public Float getTmax() {
		return tmax;
	}

	public void setTmax(Float tmax) {
		this.tmax = tmax;
	}

	@Column
	public Float getIrradiance() {
		return irradiance;
	}

	public void setIrradiance(Float irradiance) {
		this.irradiance = irradiance;
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
	@JoinColumn(name = "staion_id", referencedColumnName = "station_id")
	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	@Column
	public Float getPrecipitation() {
		return precipitation;
	}

	public void setPrecipitation(Float precipitation) {
		this.precipitation = precipitation;
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

}
