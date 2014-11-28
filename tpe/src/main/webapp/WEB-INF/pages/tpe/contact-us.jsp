<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html lang="en">
<head>
<meta charset="utf-8">
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>

<title>Contact Us</title>

</head>

<body>
	<div id="contact_main">
		<!--TPE Team Area-->
		<div class="pane_left">
			<!--Contact Area-->
			<h4 class="navigation">
				<a href="#">Contact</a>
			</h4>
			<div class="sec_details">
				<h4>Who we are?</h4>
				<h3>Institution</h3>
				<p>Scientific Documentation Details Here</p>

				<h3>TPE Team</h3>
				<div id="team">
					<div class="team_member">
						<div class="member_photo">
							<img src="img/team.png" />
						</div>
						<div class="name">Camilo Barrios</div>
						<div class="email">Organization: CCAFS</div>
						<div class="position">Position: Systems Engineer</div>
						<div class="role">Role: Developer</div>
						<div class="email">Email: c.barrios@cgiar.org</div>
					</div>
					<div class="team_member">
						<div class="member_photo">
							<img src="img/photo.png" />
						</div>
						<div class="name">Camilo Barrios</div>
						<div class="email">Organization: CCAFS</div>
						<div class="position">Position: Systems Engineer</div>
						<div class="role">Role: Developer</div>
						<div class="email">Email: c.barrios@cgiar.org</div>
					</div>
					<div class="team_member">
						<div class="member_photo">
							<img src="img/photo.png" />
						</div>
						<div class="name">Camilo Barrios</div>
						<div class="email">Organization: CCAFS</div>
						<div class="position">Position: Systems Engineer</div>
						<div class="role">Role: Developer</div>
						<div class="email">Email: c.barrios@cgiar.org</div>
					</div>
					<div class="team_member">
						<div class="member_photo">
							<img src="img/photo.png" />
						</div>
						<div class="name">Camilo Barrios</div>
						<div class="email">Organization: CCAFS</div>
						<div class="position">Position: Systems Engineer</div>
						<div class="role">Role: Developer</div>
						<div class="email">Email: c.barrios@cgiar.org</div>
					</div>
					<div class="team_member">
						<div class="member_photo">
							<img src="img/team.png" />
						</div>
						<div class="name">Camilo Barrios</div>
						<div class="email">Organization: CCAFS</div>
						<div class="position">Position: Systems Engineer</div>
						<div class="role">Role: Developer</div>
						<div class="email">Email: c.barrios@cgiar.org</div>
					</div>
					<div class="team_member">
						<div class="member_photo">
							<img src="img/photo.png" />
						</div>
						<div class="name">Camilo Barrios</div>
						<div class="email">Organization: CCAFS</div>
						<div class="position">Position: Systems Engineer</div>
						<div class="role">Role: Developer</div>
						<div class="email">Email: c.barrios@cgiar.org</div>
					</div>

				</div>
			</div>

			<div class="sec_details">
				<h4>About Us</h4>
				<p>A brief description about us.</p>
			</div>

			<div class="sec_details">
				<h4>How to cite Us</h4>
				<p>A brief description how to cite us.</p>
			</div>
		</div>
		<div class="pane_right">
			<div id="contact_pane">
				<h4>Contact Us</h4>
				<form action="contactUs" method="post">
					<table id="contact_table">
						<tr>
							<td><s:label key="contact.name" /></td>
						<tr>
							<td><s:textfield id="name" name="name"
									placeholder="%{getText('contact.name')}"></s:textfield></td>
						<tr>
							<td><s:label key="contact.organization" /></td>
						</tr>
						<tr>
							<td><s:textfield id="organization" name="organization"
									placeholder="%{getText('contact.organization')}"></s:textfield>
							</td>
						</tr>
						<tr>
							<td><s:label key="contact.email" /></td>
						</tr>
						<tr>
							<td><s:textfield id="email" name="email"
									placeholder="%{getText('contact.email')}">
								</s:textfield></td>
						</tr>
						<tr>
							<td><s:label key="contact.message" /></td>
						</tr>
						<tr>
							<td><s:textarea name="message" rows="17"
									placeholder="%{getText('contact.message')}"
									cssStyle="resize: none;" /></td>
						</tr>
						<tr>
							<td>
						</tr>
						<tr>
							<td style="text-align: right;"><s:submit id="send"
									value="Send" /></td>
						</tr>
					</table>
				</form>
			</div>

		</div>

	</div>
</body>
</html>