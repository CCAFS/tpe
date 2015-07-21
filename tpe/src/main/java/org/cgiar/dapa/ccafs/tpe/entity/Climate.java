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
	// private Integer day;
	/**
	 * The average daily minimum temperature for all years
	 */
	private Double tmin;
	/**
	 * The average daily maximum temperature for all years
	 */
	private Double tmax;
	/**
	 * The average daily irradiance for all years
	 */
	private Double radiation;

	// private Property property;
	/**
	 * The category of the climate
	 */
	private Category category;
	/**
	 * The weather station that relates to this climate data
	 */
	private Station station;
	/**
	 * The average daily precipitation for all years
	 */
	private Double precipitation;
	/**
	 * The region that relates to this climate from the given station
	 */
	// private Region region;
	/**
	 * The year in which the climate was recorded
	 */
	// private String year;
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
	// private Date recordedOn;
	/**
	 * The value of the climate property
	 */
	// private Double propertyValue;
	/**
	 * The date the climate data was recorded.
	 */
	// private Date date;
	/**
	 * The month
	 */
	private Integer month;

	private Integer monthPlantingDate;

	private Level level;

	/**
	 * The latitude of the weather station
	 */
	private Double latitude;
	/**
	 * The longitude of the weather station
	 */
	private Double longitude;

	/**
	 * The region where the weather station is located or found
	 */
	private Region region;

	@ManyToOne(targetEntity = Category.class)
	@JoinColumn(name = "category_id", referencedColumnName = "id")
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@ManyToOne(targetEntity = Station.class)
	@JoinColumn(name = "station_id", referencedColumnName = "id")
	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	@Column(name = "source")
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Column(name = "author")
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Column(name = "min_temperature")
	public Double getTmin() {
		return tmin;
	}

	public void setTmin(Double tmin) {
		this.tmin = tmin;
	}

	@Column(name = "max_temperature")
	public Double getTmax() {
		return tmax;
	}

	public void setTmax(Double tmax) {
		this.tmax = tmax;
	}

	@Column(name = "precipitation")
	public Double getPrecipitation() {
		return precipitation;
	}

	public void setPrecipitation(Double precipitation) {
		this.precipitation = precipitation;
	}

	@Column(name = "radiation")
	public Double getRadiation() {
		return radiation;
	}

	public void setRadiation(Double radiation) {
		this.radiation = radiation;
	}

	@Column(name = "month")
	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	@Column(name = "month_planting_date")
	public Integer getMonthPlantingDate() {
		return monthPlantingDate;
	}

	public void setMonthPlantingDate(Integer monthPlantingDate) {
		this.monthPlantingDate = monthPlantingDate;
	}

	@ManyToOne(targetEntity = Level.class)
	@JoinColumn(name = "level_id", referencedColumnName = "id")
	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	@Column(name = "latitude")
	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	@Column(name = "longitude")
	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@ManyToOne(targetEntity = Region.class)
	@JoinColumn(name = "region_id", referencedColumnName = "id")
	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
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

		return new LinkedList<Double>(Arrays.asList(this.getLongitude(),
				this.getLatitude()));

	}
}
