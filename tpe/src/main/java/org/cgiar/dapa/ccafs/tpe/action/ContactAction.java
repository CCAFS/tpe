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

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cgiar.dapa.ccafs.tpe.service.ITPEMailService;

import com.opensymphony.xwork2.ActionSupport;

/**
 * The contact us action that is responsible for the user's contact information
 * regarding the TPE Project.
 * 
 * @author nmatovu
 * 
 */
public class ContactAction extends BaseAction {

	private static final long serialVersionUID = 6796695210997177976L;

	private static final String USER_NAME = "name";

	private static final String USER_EMAIL = "email";

	private static final String USER_ORGANIZATION = "organization";

	private static final String USER_DETAILS = "details";

	private Log log = LogFactory.getLog(this.getClass());

	private ITPEMailService mailService;
	/**
	 * The user's email address
	 */
	private String email;
	/**
	 * The organization or institution the user attached to.
	 */
	private String organization;
	/**
	 * The user's full name(first and last)
	 */
	private String name;
	/**
	 * The user's detailed information, exciting ideas or questions about the
	 * TPE project
	 */
	private String details;

	// TODO Add the user field and login credentials
	@Override
	public String execute() {
		log.info("executing...");
		return ActionSupport.SUCCESS;
	}

	public String contactus() {
		log.info("Contacting...");
		if (getEmail() != null && getName() != null) {
			log.info("validating...");
			// Create and add the template variables to the map
			Map<String, Object> templateVariables = new HashMap<String, Object>();
			templateVariables.put(USER_NAME, getName());
			templateVariables.put(USER_EMAIL, getEmail());
			templateVariables.put(USER_ORGANIZATION, getOrganization());
			templateVariables.put(USER_DETAILS, getDetails());

			// Send a contact email to the admin or support team
			 mailService.contactUs(templateVariables);
			 log.info("mail service..");
			// Send the email notification to the user confirming the receipt of
			// the request
			 mailService.notifyUser(getEmail(), templateVariables);
			log.info("sent...");
		}
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

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public ITPEMailService getMailService() {
		return mailService;
	}

	public void setMailService(ITPEMailService mailService) {
		this.mailService = mailService;
	}

	/*
	 * @Override public void validate() {
	 * 
	 * if (email.isEmpty()) { addFieldError("email",
	 * "Email Field cannot be left blank!!!"); } else if (!email.contains(".")
	 * && !email.contains("@")) { addFieldError("email",
	 * "Email ID not valid!!!"); } else if (name.isEmpty()) {
	 * addFieldError("name", "Your name field cannot be left blank"); } else if
	 * (details.isEmpty()) { addFieldError("details",
	 * "Please Enter your message here"); } }
	 */
	// @Override
	// public void setServletRequest(HttpServletRequest hsr) {
	// this.hsr = hsr;
	// }

	/*
	 * // Also include an inner class that is used for authentication purposes
	 * private class SMTPAuthenticator extends Authenticator {
	 * 
	 * @Override public PasswordAuthentication getPasswordAuthentication() {
	 * return new PasswordAuthentication(FROM_MAIL, "Ugx#4.24"); } }
	 */

}
