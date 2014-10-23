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
 * This class represents the window sowing steps (dates) for a given sowing window in the model.
 * 
 * @author NMATOVU
 *
 */
@Entity
@Table(name = "window_steps")
@AttributeOverride(name = "id", column = @Column(name = "step_id"))
public class WindowStep extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 293576359212510844L;

	/**
	 * The sowing window
	 */
	private WindowSowing window;
	/**
	 * The sowing date in the sowing window
	 */
	private Date sowingDate;

	@ManyToOne(targetEntity = WindowSowing.class)
	@JoinColumn(name = "window_id", referencedColumnName = "window_id")
	public WindowSowing getWindow() {
		return window;
	}

	public void setWindow(WindowSowing window) {
		this.window = window;
	}

	@Column(name = "sowing_date")
	public Date getSowingDate() {
		return sowingDate;
	}

	public void setSowingDate(Date sowingDate) {
		this.sowingDate = sowingDate;
	}
}
