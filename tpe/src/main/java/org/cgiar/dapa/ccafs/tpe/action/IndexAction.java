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

import org.cgiar.dapa.ccafs.tpe.service.ITPEMailService;

import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1886907782804805164L;
	private static final String CONTACT_US_MAIL = "ccafstpe@gmail.com";
	private static final String CONTACT_US_SUBJECT = "CONTACT US";
	private String email;
	private String organization;
	private String name;
	private String message;
	private ITPEMailService mailService;

	public String execute() {

		return ActionSupport.SUCCESS;
	}

	public String documentation() {

		return ActionSupport.SUCCESS;
	}

	public String resources() {

		return ActionSupport.SUCCESS;
	}

	public String contactUs() {

		return ActionSupport.SUCCESS;
	}

	public String contribute() {

		return ActionSupport.SUCCESS;
	}

	public String workshops() {

		return ActionSupport.SUCCESS;
	}

	/**
	 * This method sends the contact us message to the support team
	 */
	public void sendContactMessage() {
		if (message != null && email != null && name != null) {
			// TODO Refactor the email body
			// TODO Use velocity to refactor.
			StringBuffer sb = new StringBuffer();
			sb.append(getName());
			sb.append(getOrganization());
			sb.append(getEmail());
			sb.append(getMessage());
			mailService.sendMail(CONTACT_US_MAIL, CONTACT_US_SUBJECT,
					sb.toString());
		}

	}

	public String project() {
		return ActionSupport.SUCCESS;
	}

	public String tutorials() {
		return ActionSupport.SUCCESS;
	}

	public String scientific() {
		return ActionSupport.SUCCESS;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setMailService(ITPEMailService mailService) {
		this.mailService = mailService;
	}

}
