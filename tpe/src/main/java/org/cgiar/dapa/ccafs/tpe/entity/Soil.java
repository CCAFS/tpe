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
 * This class represents the soil texture or type (clay, sand, loam, etc) in the
 * model. Each soil type has soil properties and values from a particular region
 * or station where it located.
 * 
 * @author NMATOVU
 *
 */
@Entity
@Table(name = "soil")
@AttributeOverride(name = "id", column = @Column(name = "soil_id"))
public class Soil extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3993570566249209461L;

	/**
	 * The name of the soil
	 */
	private String name;
	/**
	 * The code or abbreviation of the soil type
	 */
	private String code;
	/**
	 * The assigned color of the soil
	 */
	private String color;

	@Column(name = "soil_name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "soil_code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(getName()).append(" ").append(" [").append(getId())
				.append("]");
		return sb.toString();

	}

	@Column(name = "soil_color")
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
