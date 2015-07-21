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
package org.cgiar.dapa.ccafs.tpe.service;

import java.util.Map;

/**
 * The interface that implements the email service methods
 * 
 * @author NOAH
 * 
 */
public interface ITPEMailService {
	/**
	 * This sends the mail notification to the system admin about the new user
	 * wishing or interested to contribute to the platform. It uses the velocity
	 * template for the body.
	 *
	 * @param templateVariables
	 *            Variables to use when processing the template.
	 */
	public void notifyAdmin(final Map<String, Object> templateVariables);

	/**
	 * This sends the mail notification to the user who is wishing or interested
	 * to contribute to the platform. So after the user sends the request, he or
	 * she will be notified automatically about the receipt of the request.
	 * 
	 * @param templateVariables
	 *            Variables to use when processing the template.
	 */
	public void notifyUser(final String userEmail,
			final Map<String, Object> templateVariables);

	/**
	 * This sends a pre-configured mailmessage
	 * 
	 * @param message
	 *            the pre configured mail message to send
	 */
	public void sendPreConfiguredMail(String message);

	/**
	 * This sends the user's contact infos to the admin or platform support
	 * team.
	 * 
	 * @param templateVariables
	 */
	public void contactUs(Map<String, Object> templateVariables);
}
