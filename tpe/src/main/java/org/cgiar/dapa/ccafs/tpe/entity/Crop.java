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
import javax.persistence.Table;

/**
 * The crop class represents the crop entity fields
 * 
 * @author NMATOVU
 *
 */
@Entity
@Table(name = "crop")
@AttributeOverride(name = "id", column = @Column(name = "crop_id"))
public class Crop extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1989776373116359061L;

	/**
	 * The name of the crop
	 */
	private String name;

	/**
	 * The crop category
	 */
	// private Category category;

	@Column(name = "crop_name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
