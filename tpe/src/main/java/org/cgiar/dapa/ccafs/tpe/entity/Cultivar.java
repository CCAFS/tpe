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

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * This class holds information about the crop cultivar or variety in the crop
 * simulation model.
 * 
 * @author NMATOVU
 *
 */
@Entity
@Table(name = "cultivar")
@AttributeOverride(name = "id", column = @Column(name = "cultivar_id"))
public class Cultivar extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9150226326293233905L;

	/**
	 * The name of the crop cultivar in the crop model
	 */
	private String name;
	/**
	 * The crop to which the cultivar belongs
	 */
	private Crop crop;
	/**
	 * The start day of the vegetative plot band
	 */
	private Integer vegetativeStartDay;
	/**
	 * The end day of the vegetative plot band
	 */
	private Integer vegetativeEndDay;
	/**
	 * The start day of the reproductive plot band
	 */
	private Integer reproductiveStartDay;
	/**
	 * The end day of the reproductive plot band
	 */
	private Integer reproductiveEndDay;
	/**
	 * The start day of the filling grain plot band
	 */
	private Integer fillingGrainStartDay;
	/**
	 * The end day of the filling grain plot band
	 */
	private Integer fillingGrainEndDay;

	@Column(name = "cultivar_name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne(targetEntity = Crop.class)
	@JoinColumn(name = "crop_id", referencedColumnName = "crop_id")
	public Crop getCrop() {
		return crop;
	}

	public void setCrop(Crop crop) {
		this.crop = crop;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(getName()).append(" ").append(" [").append(getId())
				.append("]");
		return sb.toString();

	}

	@Column(name = "vegetative_start_day")
	public Integer getVegetativeStartDay() {
		return vegetativeStartDay;
	}

	public void setVegetativeStartDay(Integer vegetativeStartDay) {
		this.vegetativeStartDay = vegetativeStartDay;
	}

	@Column(name = "vegetative_end_day")
	public Integer getVegetativeEndDay() {
		return vegetativeEndDay;
	}

	public void setVegetativeEndDay(Integer vegetativeEndDay) {
		this.vegetativeEndDay = vegetativeEndDay;
	}

	@Column(name = "reproductive_start_day")
	public Integer getReproductiveStartDay() {
		return reproductiveStartDay;
	}

	public void setReproductiveStartDay(Integer reproductiveStartDay) {
		this.reproductiveStartDay = reproductiveStartDay;
	}

	@Column(name = "reproductive_end_day")
	public Integer getReproductiveEndDay() {
		return reproductiveEndDay;
	}

	public void setReproductiveEndDay(Integer reproductiveEndDay) {
		this.reproductiveEndDay = reproductiveEndDay;
	}

	@Column(name = "fillinggrain_start_day")
	public Integer getFillingGrainStartDay() {
		return fillingGrainStartDay;
	}

	public void setFillingGrainStartDay(Integer fillingGrainStartDay) {
		this.fillingGrainStartDay = fillingGrainStartDay;
	}

	@Column(name = "fillinggrain_end_day")
	public Integer getFillingGrainEndDay() {
		return fillingGrainEndDay;
	}

	public void setFillingGrainEndDay(Integer fillingGrainEndDay) {
		this.fillingGrainEndDay = fillingGrainEndDay;
	}

}
