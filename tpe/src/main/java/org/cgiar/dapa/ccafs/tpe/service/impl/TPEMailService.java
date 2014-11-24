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
package org.cgiar.dapa.ccafs.tpe.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cgiar.dapa.ccafs.tpe.service.ITPEMailService;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class TPEMailService implements ITPEMailService {
	@SuppressWarnings("unused")
	private final Log log = LogFactory.getLog(this.getClass());
	private MailSender mailSender;
	private SimpleMailMessage defaultMail;

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setDefaultMail(SimpleMailMessage defaultMail) {
		this.defaultMail = defaultMail;
	}

	@Override
	public void sendMail(String to, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
		mailSender.send(message);

	}

	@Override
	public void sendPreConfiguredMail(String message) {
		SimpleMailMessage mailMessage = new SimpleMailMessage(defaultMail);
		mailMessage.setText(message);
		mailSender.send(mailMessage);

	}

}
