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
 * This class represents the window sowing in the crop simulation model
 * 
 * @author NMATOVU
 *
 */
@Entity
@Table(name = "window_sowing")
@AttributeOverride(name = "id", column = @Column(name = "window_id"))
public class WindowSowing extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5332502250816201705L;

	/**
	 * The title of the sowing window
	 */
	private String title;
	/**
	 * The start date of the sowing window
	 */
	private Date startDate;
	/**
	 * The end date of the sowing window
	 */
	private Date endDate;
	/**
	 * The crop cultivar attached to this window
	 */
	private Cultivar cultivar;
	/**
	 * The model that relates to this window.
	 */
	private Model model;

	@Column(name = "window_title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "start_date")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column(name = "end_date")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@ManyToOne(targetEntity = Cultivar.class)
	@JoinColumn(name = "cultivar_id", referencedColumnName = "cultivar_id")
	public Cultivar getCultivar() {
		return cultivar;
	}

	public void setCultivar(Cultivar cultivar) {
		this.cultivar = cultivar;
	}

	@ManyToOne(targetEntity = Model.class)
	@JoinColumn(name = "model_id", referencedColumnName = "model_id")
	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

}
