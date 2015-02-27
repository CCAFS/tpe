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

import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.app.VelocityEngine;
import org.cgiar.dapa.ccafs.tpe.service.ITPEMailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

public class TPEMailService implements ITPEMailService {
	protected static final Object PARAM_USER_MAIL = null;
	protected static final String SUBJECT_USER = "REQUEST RECEIVED";
	protected static final String SUBJECT_ADMIN = "NEW TPE PLATFORM CONTRIBUTOR";
	private final Log log = LogFactory.getLog(this.getClass());
	private JavaMailSender mailSender;
	private SimpleMailMessage defaultMail;
	private VelocityEngine velocityEngine;
	private String adminEmail;
	private String supportEmail;

	// private String userInfo;
	// private String adminInfo;

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public void setSupportEmail(String supportEmail) {
		this.supportEmail = supportEmail;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setDefaultMail(SimpleMailMessage defaultMail) {
		this.defaultMail = defaultMail;
	}

	@Override
	public void sendPreConfiguredMail(String message) {
		log.info("Sending the default mail...");
		SimpleMailMessage mailMessage = new SimpleMailMessage(defaultMail);
		mailMessage.setText(message);
		mailSender.send(mailMessage);

		log.info("Sent...");

	}

	@Override
	public void notifyAdmin(final Map<String, Object> templateVariables) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage,
						true);
				message.setTo(supportEmail);
				message.setFrom(new InternetAddress(adminEmail));
				message.setSubject(SUBJECT_ADMIN);
				String body = VelocityEngineUtils.mergeTemplateIntoString(
						velocityEngine, "templates/notify-admin.vm", "UTF-8",
						templateVariables);
				log.info(body);
				message.setText(body, true);
			}
		};
		this.mailSender.send(preparator);

	}

	@Override
	public void notifyUser(final String userEmail,
			final Map<String, Object> templateVariables) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage,
						true);
				message.setTo(userEmail);
				message.setFrom(new InternetAddress(adminEmail));
				message.setSubject(SUBJECT_USER);
				String body = VelocityEngineUtils.mergeTemplateIntoString(
						velocityEngine, "templates/notify-user.vm", "UTF-8",
						templateVariables);
				log.info(body);
				message.setText(body, true);
			}
		};
		this.mailSender.send(preparator);

	}

	@Override
	public void contactUs(final Map<String, Object> templateVariables) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage,
						true);
				message.setTo(supportEmail);
				message.setFrom(new InternetAddress(adminEmail));
				message.setSubject(SUBJECT_ADMIN);
				String body = VelocityEngineUtils.mergeTemplateIntoString(
						velocityEngine, "templates/contact-us.vm", "UTF-8",
						templateVariables);
				log.info(body);
				message.setText(body, true);
			}
		};
		this.mailSender.send(preparator);

	}

}
