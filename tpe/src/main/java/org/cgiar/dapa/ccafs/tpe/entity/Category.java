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
 * The category class provides fields that categorizes the other tpe entity
 * classes.
 * 
 * @author NMATOVU
 *
 */
@Entity
@Table(name = "category")
@AttributeOverride(name = "id", column = @Column(name = "category_id"))
public class Category extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2176599493718673300L;

	/**
	 * The name of the category
	 */
	private String name;

	/**
	 * The entity class for this category
	 */
	private String entity;
	/**
	 * The description of the category
	 */
	private String description;

	@Column(name = "category_name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "entity_class")
	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	@Column(name = "category_description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
