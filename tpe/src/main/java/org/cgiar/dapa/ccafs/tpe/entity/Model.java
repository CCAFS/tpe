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
 * This class represents the crop simulation model entity in the system
 * 
 * @author NMATOVU
 *
 */
@Entity
@Table(name = "model")
@AttributeOverride(name = "id", column = @Column(name = "model_id"))
public class Model extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2762568204746213997L;

	/**
	 * The name of the model
	 */
	private String title;
	/**
	 * The date the model was created
	 */
	private Date createdOn;
	/**
	 * The simulation number
	 */
	private Integer number;
	/**
	 * The description about the model
	 */
	private String description;
	/**
	 * The crop cultivar attached to this model
	 */
	private Cultivar cultivar;

	@Column(name = "model_date")
	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Column(name = "number")
	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	@Column(name = "model_description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "model_title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@ManyToOne(targetEntity = Cultivar.class)
	@JoinColumn(name = "cultivar_id", referencedColumnName = "cultivar_id")
	public Cultivar getCultivar() {
		return cultivar;
	}

	public void setCultivar(Cultivar cultivar) {
		this.cultivar = cultivar;
	}
}
