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
 * This class represents the station entity in the crop simulation model.
 * @author NMATOVU
 *
 */
@Entity
@Table(name="station")
public class Station extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1447281619852036942L;
	/**
	 * The primary key of the weather station
	 */
	private Integer id;
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
	private Float latitude;
	/**
	 * The longitude of the weather station
	 */
	private Float longitude;

	/**
	 * The altitude of the weather station
	 */
	private Float altitude;

	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "region_name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne(targetEntity = Region.class)
	@JoinColumn(name = "region_id", referencedColumnName = "region_id")
	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	@Column(name = "station_number")
	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	@Column(name = "latitude")
	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	@Column(name = "longitude")
	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	@Column(name = "altitude")
	public Float getAltitude() {
		return altitude;
	}

	public void setAltitude(Float altitude) {
		this.altitude = altitude;
	}

}
