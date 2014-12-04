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

@Entity
@Table(name = "environment_soil")
@AttributeOverride(name = "id", column = @Column(name = "env_soil_id"))
public class EnvironmentSoil extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2357190751243656050L;
	private Environment environment;
	private Soil soil;
	private String sowingDate;
	private Float probability;

	@ManyToOne(targetEntity = Environment.class)
	@JoinColumn(name = "environment_id", referencedColumnName = "environment_id")
	public Environment getEnvironment() {
		return environment;
	}

	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	@ManyToOne(targetEntity = Soil.class)
	@JoinColumn(name = "soil_id", referencedColumnName = "soil_id")
	public Soil getSoil() {
		return soil;
	}

	public void setSoil(Soil soil) {
		this.soil = soil;
	}

	@Column(name = "prob_occurance")
	public Float getProbability() {
		return probability;
	}

	public void setProbability(Float probability) {
		this.probability = probability;
	}

	@Column(name = "sowing_date")
	public String getSowingDate() {
		return sowingDate;
	}

	public void setSowingDate(String sowingDate) {
		this.sowingDate = sowingDate;
	}

}
