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
package org.cgiar.dapa.ccafs.tpe.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cgiar.dapa.ccafs.tpe.entity.User;
import org.cgiar.dapa.ccafs.tpe.util.Utils;

import com.opensymphony.xwork2.ActionSupport;

/**
 * The action for managing the user's login information.
 * 
 * @author nmatovu
 *
 */
public class AdminAction extends BaseAction {
	private static final long serialVersionUID = -7540861343691943892L;

	private static final String ENABLED_YES = "Yes";
	private static final String ENABLED_NO = "No";

	private Log LOG = LogFactory.getLog(this.getClass());
	// private User user;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private Boolean enabled;
	private String email;
	private List<String> roles;
	private List<String> status;
	private String selectedStatus;
	private List<String> selectedRoles;
	private String defaultStatus;

	@Override
	public String execute() {
		// users = tpeService.getUsers();
		// status = new ArrayList<String>(Arrays.asList(ENABLED_YES,
		// ENABLED_NO));
		// roles = Utils.getUserRoles();
		// if (firstName == null || firstName.length() == 0)
		// return ActionSupport.INPUT;
		status = new ArrayList<String>(Arrays.asList(ENABLED_YES, ENABLED_NO));
		roles = Utils.getUserRoles();
		return ActionSupport.SUCCESS;
	}

	public String update() {
		status = new ArrayList<String>(Arrays.asList(ENABLED_YES, ENABLED_NO));
		roles = Utils.getUserRoles();
		Boolean stat = false;
		if (selectedStatus != null)
			stat = selectedStatus.equals(ENABLED_YES) ? true : false;
		try {
			tpeService.updateUser(new User(firstName, lastName, username,
					password, stat, Utils.createRoles(selectedRoles), email));
		} catch (PersistenceException e) {
			addActionError(e.getMessage());
			LOG.error(e.getMessage());
			return ActionSupport.INPUT;
		}

		return ActionSupport.SUCCESS;
	}

	public String add() {
		status = new ArrayList<String>(Arrays.asList(ENABLED_YES, ENABLED_NO));
		roles = Utils.getUserRoles();
		Boolean stat = false;
		if (selectedStatus != null)
			stat = selectedStatus.equals(ENABLED_YES) ? true : false;

		try {
			tpeService.addUser(new User(firstName, lastName, username,
					password, stat, Utils.createRoles(selectedRoles), email));
		} catch (PersistenceException e) {
			addActionError(e.getMessage());
			LOG.error(e.getMessage());
			return ActionSupport.INPUT;
		}

		return ActionSupport.SUCCESS;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public List<String> getStatus() {
		return status;
	}

	public void setStatus(List<String> status) {
		this.status = status;
	}

	public String getSelectedStatus() {
		return selectedStatus;
	}

	public void setSelectedStatus(String selectedStatus) {
		this.selectedStatus = selectedStatus;
	}

	public List<String> getSelectedRoles() {
		return selectedRoles;
	}

	public void setSelectedRoles(List<String> selectedRoles) {
		this.selectedRoles = selectedRoles;
	}

	public String getDefaultStatus() {
		return ENABLED_YES;
	}

	public void setDefaultStatus(String defaultStatus) {
		this.defaultStatus = defaultStatus;
	}

}
