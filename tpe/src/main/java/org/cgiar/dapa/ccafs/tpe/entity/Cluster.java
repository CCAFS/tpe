package org.cgiar.dapa.ccafs.tpe.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cluster")
public class Cluster extends BaseEntity {

	private static final long serialVersionUID = 253054040816625861L;
	private String name;
	private String description;

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
