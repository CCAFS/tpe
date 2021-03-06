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
 * This class represents the region (country, state, province, municipio,
 * district) in the crop simulation model. The region may have sub regions and
 * also those sub regions could have their own sub regions at different
 * administrative levels.
 * 
 * <p>
 * Region Categories
 * <ul>
 * <li>Country</li>
 * <li>State</li>
 * <li>Province</li>
 * <li>District</li>
 * <li>Municipio</li>
 * </ul>
 * </p>
 * 
 * @author NMATOVU
 *
 */
@Entity
@Table(name = "region")
@AttributeOverride(name = "id", column = @Column(name = "region_id"))
public class Region extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5427626649810939434L;

	/**
	 * The name of the region
	 */
	private String name;
	/**
	 * The International Organization for Standardization code of the region
	 */
	private String alphaISO;
	/**
	 * The latitude of the region
	 */
	private Double latitude;
	/**
	 * The longitude of the region
	 */
	private Double longitude;
	/**
	 * The parent region of this region (The country is the parent of the state)
	 */
	private Region parent;
	/**
	 * The category of the region (country, state, province, district or
	 * municipality)
	 */
	private Category category;
	/**
	 * The three ISO numeric code of the region
	 */
	private Integer numericISO;
	/**
	 * The level or administrative division of the region (level one)
	 */
	private String level;
	/**
	 * The default Google Map zoom for this region
	 */
	private Integer zoom;

	@Column(name = "region_name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "region_alpha_iso")
	public String getAlphaISO() {
		return alphaISO;
	}

	public void setAlphaISO(String alphaISO) {
		this.alphaISO = alphaISO;
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
	@JoinColumn(name = "region_parent", referencedColumnName = "region_id")
	public Region getParent() {
		return parent;
	}

	public void setParent(Region parent) {
		this.parent = parent;
	}

	@ManyToOne(targetEntity = Category.class)
	@JoinColumn(name = "category_id", referencedColumnName = "category_id")
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Column(name = "region_numeric_iso")
	public Integer getNumericISO() {
		return numericISO;
	}

	public void setNumericISO(Integer numericISO) {
		this.numericISO = numericISO;
	}

	@Column(name = "region_level")
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(getName()).append(", ").append(getAlphaISO()).append(" [")
				.append(getId()).append("][").append(getLatitude()).append(",")
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
		// return new LinkedList<Double>(Arrays.asList(this.getLatitude(),
		// this.getLongitude()));

		return new LinkedList<Double>(Arrays.asList(this.getLongitude(),
				this.getLatitude()));
	}

	@Column(name = "zoom")
	public Integer getZoom() {
		return zoom;
	}

	public void setZoom(Integer zoom) {
		this.zoom = zoom;
	}
}
