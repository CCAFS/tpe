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
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * This class represents the weather station entity in the crop simulation
 * model. Each station in the model belongs to a particular region and has geo
 * location codes (longitude and latitude).
 * 
 * @author NMATOVU
 *
 */
@Entity
@Table(name = "station")
// @AttributeOverride(name = "id", column = @Column(name = "station_id"))
public class Station extends BaseEntity implements Coordinate {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1447281619852036942L;

	/**
	 * The name of the weather station
	 */
	private String name;
	/**
	 * The region where the weather station is located or found
	 */
	private Region region;
	/**
	 * The weather station number from the given region, it is numeric 1,2,3...
	 */
	private Integer number;
	/**
	 * The latitude of the weather station
	 */
	private Double latitude;
	/**
	 * The longitude of the weather station
	 */
	private Double longitude;
	/**
	 * The country where the station is located.This is used in case the the
	 * station is not attached to any state or municipios.
	 */
	private String country;

	// /**
	// * The altitude of the weather station
	// */
	// private Double altitude;

	@Column(name = "name")
	public String getName() {
		// Replace all the underscores (_)
		if (name != null)
			return name.replaceAll(Pattern.quote("_"), " ");
		return null;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne(targetEntity = Region.class)
	@JoinColumn(name = "region_id", referencedColumnName = "id")
	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	@Column(name = "number")
	public Integer getNumber() {

		if (this.number != null)
			return number;
		return 0;
	}

	public void setNumber(Integer number) {
		this.number = number;
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

	// @Column(name = "altitude")
	// public Double getAltitude() {
	// return altitude;
	// }
	//
	// public void setAltitude(Double altitude) {
	// this.altitude = altitude;
	// }

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(getName()).append(" ").append(" [").append(getId())
				.append("][").append(getLatitude()).append(",")
				.append(getLongitude()).append("]");
		return sb.toString();

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

	@Column(name = "country")
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
