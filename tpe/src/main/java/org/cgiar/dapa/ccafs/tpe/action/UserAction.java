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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cgiar.dapa.ccafs.tpe.entity.User;
import org.cgiar.dapa.ccafs.tpe.exception.UserNotFoundException;
import org.cgiar.dapa.ccafs.tpe.util.Utils;

import com.opensymphony.xwork2.ActionSupport;

/**
 * The action for managing the user's login information.
 * 
 * @author nmatovu
 *
 */
public class UserAction extends BaseAction {

	private static final long serialVersionUID = 1513217043716563227L;

	private static final String ENABLED_YES = "Yes";

	private static final String ENABLED_NO = "No";

	private static final String RESULT_EDIT = "edit";

	private Log LOG = LogFactory.getLog(this.getClass());
	private User user;
	private List<String> roles;
	private List<User> users;
	private List<String> status;
	@SuppressWarnings("unused")
	private String defaultStatus;
	private String username;

	@Override
	public String execute() {

		users = tpeService.getUsers();
		status = new ArrayList<String>(Arrays.asList(ENABLED_YES, ENABLED_NO));
		roles = Utils.getUserRoles();

		return ActionSupport.SUCCESS;
	}

	public String list() {

		return this.execute();
	}

	public String add() {

		status = new ArrayList<String>(Arrays.asList(ENABLED_YES, ENABLED_NO));
		roles = Utils.getUserRoles();

		return ActionSupport.INPUT;
	}

	public String edit() {

		status = new ArrayList<String>(Arrays.asList(ENABLED_YES, ENABLED_NO));
		roles = Utils.getUserRoles();

		try {
			user = tpeService.findUserByUsername(getUsername());
			LOG.info(user.toString());
		} catch (UserNotFoundException e) {
			LOG.error(e.getMessage());
		}

		return RESULT_EDIT;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<String> getStatus() {
		return status;
	}

	public void setStatus(List<String> status) {
		this.status = status;
	}

	public String getDefaultStatus() {
		return ENABLED_YES;
	}

	public void setDefaultStatus(String defaultStatus) {
		this.defaultStatus = defaultStatus;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
