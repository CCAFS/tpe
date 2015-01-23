<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Contribute</title>
</head>
<body>
	<div id="contribute">
		<!--Contribute Data Area-->
		<div class="sec_cont">
			<h1>Get Involved with TPE Platform</h1>
			<p>Target Population of Environments Platform provides data
				initially for only two crops (Beans and Rice) from Brazil, Colombia
				and Latin America. If you are interested to get involved in
				improving the platform and to cover more target population
				environments for different crops from other countries, there are
				many ways to join the TPE project.</p>
			<p>The TPE project is open to anyone wishing to share and
				contribute data, information or topics relevant to Target Population
				of Environments for various crops. Please state your motivation,
				crop of your interest, country and how you would wish to contribute
				using the form below. Also you can contact our support team using
				the contact Fpage for more information.</p>
			<p>The development of the agricultural sector in recent years has
				been marked by great challenges due to new conditions and permanent
				changes in the global climate regime, challenges which present an
				imminent risk to food security for the most vulnerable. It is
				crucial to cooperate and work together across multiple countries and
				institutions to seek strategies and appropriate response mechanisms
				against such eventualities.</p>
		</div>

		<div id="join_form">
			<h1>Get Involved</h1>
			<form action="getInvolved" method="post">
				<p>Please fill out the following form and then click Get
					Involved.</p>
				<table id="join_table">
					<tr>
						<td><s:label key="contact.name" cssClass="join-text-label" /></td>
					<tr>
						<td><s:textfield id="name" name="name" cssClass="join-text"
								placeholder="%{getText('contact.name')}"></s:textfield></td>
					<tr>
						<td><s:label key="contact.organization"
								cssClass="join-text-label" /></td>
					</tr>
					<tr>
						<td><s:textfield id="organization" name="organization"
								cssClass="join-text"
								placeholder="%{getText('contact.organization')}"></s:textfield>
						</td>
					</tr>
					<tr>
						<td><s:label key="contact.email" cssClass="join-text-label" /></td>
					</tr>
					<tr>
						<td><s:textfield id="email" name="email" cssClass="join-text"
								placeholder="%{getText('contact.email')}">
							</s:textfield></td>
					</tr>

					<tr>
						<td><s:label key="join.crop" cssClass="join-text-label" /></td>
					</tr>
					<tr>
						<td><s:textfield id="crop" name="crop" cssClass="join-text"
								placeholder="%{getText('join.crop')}">
							</s:textfield></td>
					</tr>

					<tr>
						<td><s:label key="join.cultivar" cssClass="join-text-label" /></td>
					</tr>
					<tr>
						<td><s:textfield id="cultivar" name="cultivar"
								cssClass="join-text" placeholder="%{getText('join.cultivar')}">
							</s:textfield></td>
					</tr>

					<tr>
						<td><s:label key="join.motivation" cssClass="join-text-label" /></td>
					</tr>
					<tr>
						<td><s:textfield id="motivation" name="motivation"
								cssClass="join-text" placeholder="%{getText('join.motivation')}">
							</s:textfield></td>
					</tr>

					<tr>
						<td><s:label key="join.area" cssClass="join-text-label" /></td>
					</tr>
					<tr>
						<td><s:textfield id="area" name="area" cssClass="join-text"
								placeholder="%{getText('join.area')}">
							</s:textfield></td>
					</tr>

					<tr>
						<td><s:label key="join.region" cssClass="join-text-label" /></td>
					</tr>
					<tr>
						<td><s:textfield id="reagion" name="region"
								cssClass="join-text" placeholder="%{getText('join.region')}">
							</s:textfield></td>
					</tr>

					<tr>
						<td><s:label key="join.message" cssClass="join-text-label" /></td>
					</tr>
					<tr>
						<td><s:textarea name="message" rows="17"
								cssClass="join-text-area"
								placeholder="%{getText('join.message')}"
								cssStyle="resize: none;" /></td>
					</tr>
					<tr>
						<td>
					</tr>
					<tr>
						<td style="text-align: right;"><s:submit id="send"
								value="Get Involved" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>