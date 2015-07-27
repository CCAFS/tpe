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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionSupport;

/**
 * The contact us action that is responsible for the user's contact information
 * regarding the TPE Project.
 * 
 * @author nmatovu
 * 
 */
public class LoginAction extends BaseAction {

	private static final long serialVersionUID = 6690887194964795471L;
	private static final String ACCESS_DENIED = "denied";
	private String error;

	@SuppressWarnings("unused")
	private Log log = LogFactory.getLog(this.getClass());

	// TODO Add the user field and login credentials
	@Override
	public String execute() {
		if (getError() == null) {
			addActionMessage("You have logged in successfully!");
			return ActionSupport.SUCCESS;
		}
		addActionError("Invalid username or password");
		return ERROR;

		// return ActionSupport.SUCCESS;
	}

//	@Override
//	public void validate() {
//
//		if (getError() != null)
//			addFieldError("userBean.username",
//					"Por favor Insira o Username de Utilizador");
//
//	}

	public String denied() {

		return ACCESS_DENIED;
	}

	public String loginFailure() {

		return ActionSupport.ERROR;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
