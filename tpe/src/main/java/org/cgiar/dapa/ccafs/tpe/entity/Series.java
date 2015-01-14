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
 * The category class provides series of a particular type group classes.
 * 
 * @author NMATOVU
 *
 */
@Entity
@Table(name = "series")
@AttributeOverride(name = "id", column = @Column(name = "series_id"))
public class Series extends BaseEntity {

	private static final long serialVersionUID = 4125448263059429436L;

	/**
	 * The name of the category
	 */
	private String name;

	/**
	 * The series type
	 */
	private Type type;

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne(targetEntity = Type.class)
	@JoinColumn(name = "type_id", referencedColumnName = "type_id")
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

}
