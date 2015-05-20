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
//@AttributeOverride(name = "id", column = @Column(name = "cultivar_id"))
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
	private Integer vegetativeStart;
	/**
	 * The end day of the vegetative plot band
	 */
	private Integer vegetativeEnd;

	/**
	 * The start day of the filling grain HFE plot band
	 */
	private Integer fillinggrainHFEStart;

	/**
	 * The start day of the filling grain LFE plot band
	 */
	private Integer fillinggrainLFEStart;

	/**
	 * The start day of the filling grain FE plot band
	 */
	private Integer fillinggrainFEStart;

	/**
	 * The end day of the reproductive HFE plot band
	 */
	private Integer reproductiveHFEEnd;

	/**
	 * The end day of the reproductive LFE plot band
	 */
	private Integer reproductiveLFEEnd;

	/**
	 * The end day of the reproductive FE plot band
	 */
	private Integer reproductiveFEEnd;
	/**
	 * The start day of the filling grain plot band
	 */
	// private Integer fillingGrainStart;
	/**
	 * The end day of the filling grain is the same for all (HFE, LFE and FE)
	 * plot band
	 */
	private Integer fillingGrainEnd;
	/**
	 * The reproductive start date is the same for all the HFE, LFE and FE
	 */
	private Integer reproductiveStart;

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne(targetEntity = Crop.class)
	@JoinColumn(name = "crop_id", referencedColumnName = "id")
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

	@Column(name = "vegetative_start")
	public Integer getVegetativeStart() {
		return vegetativeStart;
	}

	public void setVegetativeStart(Integer vegetativeStart) {
		this.vegetativeStart = vegetativeStart;
	}

	@Column(name = "vegetative_end")
	public Integer getVegetativeEnd() {
		return vegetativeEnd;
	}

	public void setVegetativeEnd(Integer vegetativeEnd) {
		this.vegetativeEnd = vegetativeEnd;
	}

	// @Column(name = "reproductive_start")
	// @Column(name = "reproductive_end")

	// @Column(name = "fillinggrain_start")

	@Column(name = "fillinggrain_end")
	public Integer getFillingGrainEnd() {
		return fillingGrainEnd;
	}

	public void setFillingGrainEnd(Integer fillingGrainEnd) {
		this.fillingGrainEnd = fillingGrainEnd;
	}

	@Column(name = "reproductive_hfe_end")
	public Integer getReproductiveHFEEnd() {
		return reproductiveHFEEnd;
	}

	public void setReproductiveHFEEnd(Integer reproductiveHFEEnd) {
		this.reproductiveHFEEnd = reproductiveHFEEnd;
	}

	@Column(name = "reproductive_lfe_end")
	public Integer getReproductiveLFEEnd() {
		return reproductiveLFEEnd;
	}

	public void setReproductiveLFEEnd(Integer reproductiveLFEEnd) {
		this.reproductiveLFEEnd = reproductiveLFEEnd;
	}

	@Column(name = "reproductive_fe_end")
	public Integer getReproductiveFEEnd() {
		return reproductiveFEEnd;
	}

	public void setReproductiveFEEnd(Integer reproductiveFEEnd) {
		this.reproductiveFEEnd = reproductiveFEEnd;
	}

	@Column(name = "fillinggrain_hfe_start")
	public Integer getFillinggrainHFEStart() {
		return fillinggrainHFEStart;
	}

	public void setFillinggrainHFEStart(Integer fillinggrainHFEStart) {
		this.fillinggrainHFEStart = fillinggrainHFEStart;
	}

	@Column(name = "fillinggrain_lfe_start")
	public Integer getFillinggrainLFEStart() {
		return fillinggrainLFEStart;
	}

	public void setFillinggrainLFEStart(Integer fillinggrainLFEStart) {
		this.fillinggrainLFEStart = fillinggrainLFEStart;
	}

	@Column(name = "fillinggrain_fe_start")
	public Integer getFillinggrainFEStart() {
		return fillinggrainFEStart;
	}

	public void setFillinggrainFEStart(Integer fillinggrainFEStart) {
		this.fillinggrainFEStart = fillinggrainFEStart;
	}

	@Column(name = "reproductive_start")
	public Integer getReproductiveStart() {
		return reproductiveStart;
	}

	public void setReproductiveStart(Integer reproductiveStart) {
		this.reproductiveStart = reproductiveStart;
	}

}
