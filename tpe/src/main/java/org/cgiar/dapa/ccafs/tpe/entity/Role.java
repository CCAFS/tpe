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

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role extends BaseEntity {

	private static final long serialVersionUID = 765566153483807905L;
	// private User user;
	private String name;
	private List<User> users;

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Role(String name) {
		super();
		// this.user = user;
		this.name = name;
	}

	// @ManyToOne(targetEntity = User.class)
	// @JoinColumn(name = "user_id", referencedColumnName = "id")
	// public User getUser() {
	// return user;
	// }

	// public void setUser(User user) {
	// this.user = user;
	// }

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "roles")
	// @JoinTable(name = "user_role", joinColumns = { @JoinColumn(name =
	// "role_id") }, inverseJoinColumns = { @JoinColumn(name = "user_id") })
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}