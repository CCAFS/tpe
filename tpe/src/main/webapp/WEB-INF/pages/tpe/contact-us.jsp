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
		<div id="contact_team">
			<h4>CCAFS TPE Team</h4>
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
		<!--Contact Us Form Area-->
		<div id="contact_form">
			<h4>Contact Us</h4>

			<form action="contactUs" method="post">
				<div id="contact_message">
					<table id="contact_table">
						<tr>
							<td><s:label key="contact.name" /></td>
							<td><s:textfield id="name" name="name" size="50"
									placeholder="%{getText('contact.name')}"></s:textfield></td>
						</tr>
						<tr>
							<td><s:label key="contact.organization" /></td>
							<td><s:textfield id="organization" name="organization"
									size="50" placeholder="%{getText('contact.organization')}"></s:textfield></td>
						</tr>
						<tr>
							<td><s:label key="contact.email" /></td>
							<td><s:textfield id="email" name="email" size="50"
									placeholder="%{getText('contact.email')}">
								</s:textfield></td>
						</tr>
						<tr>
							<td><s:label key="contact.message" /></td>
							<td><s:textarea name="message" cols="50" rows="17"
									placeholder="%{getText('contact.message')}"
									cssStyle="overflow: scroll; resize: none;" /></td>
						</tr>
						<tr>
							<td></td>
							<td style="text-align: right;"><s:submit value="Send"/></td>
						</tr>
					</table>



					<%-- <s:textarea key="contact.message" name="message" cols="65" rows="17" cssStyle="overflow: scroll; resize: none;" /> --%>

				</div>
			</form>

		</div>
	</div>
</body>
</html>