<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html lang="en">
<head>
<meta charset="utf-8">
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>

<title>Contact Us</title>
<script type="text/javascript">
	$(document).ready(function() {
		$("#send").click(function(e) {
			var data = $('#contactForm').serialize();
			e.preventDefault();
			$.ajax({
				url : "contactInfo.action",
				type : "POST",
				data : data,
				success : function() {
					//alert("Just contacted the tpe support team...");
				}
			});
			return false;
		});
	});
</script>
</head>

<body>
	<div id="contact_main">
		<!--TPE Team Area-->
		<div class="pane_left">
			<!--Contact Area-->
			<h4 class="navigation">
				<a href="#">About</a>
			</h4>
			<div class="sec_details">
				<h1>Who we are?</h1>
				<h4>Institution</h4>
				<p id="institution">The CGIAR Research Programme on Climate
					Change, Agriculture and Food Security (CCAFS) is a research
					partnership between CGIAR and Future Earth, the global
					environmental change community aiming to identify and address the
					most important interactions, synergies and trade- offs between
					climate change and agriculture. Our goal is to promote a
					food-secure world through the provision of science-based efforts
					that support sustainable agriculture and enhance livelihoods while
					adapting to climate change and conserving natural resources and
					environmental services.</p>
				<p>As a collective effort coordinated by the International
					Center for Tropical Agriculture (CIAT), CCAFS will become a hub
					that facilitates action across 15 CGIAR centers and other CGIAR
					Research Programs while involving farmers, policy makers, donors
					and other stakeholders, to integrate their knowledge and needs into
					the tools and approaches that are developed. For more information
					visit www.ccafs.cgiar.org</p>

				<h4>CCAFS TPE Team</h4>
				<div id="team">
					<div class="team_member">
						<div class="member_photo">
							<img
								src="http://www.cnpaf.embrapa.br/quemsomos/fotos/alexandreheinemann.jpg" />
						</div>
						<div class="name">Alexandre Bryan Heinemann - Ecophysiology
							Research</div>
						<div class="organization">Embrapa Arroz e Feijao Empresa
							Brasileira de Pesquisa Agropecuária (Embrapa)</div>
						<div class="email">alexandre.heinemann@embrapa.br</div>
					</div>
					<div class="team_member">
						<div class="member_photo">
							<img src="img/bcamilo.png" />
						</div>
						<div class="name">Camilo Barrios Perez - Agricultural
							engineer</div>
						<div class="email">Research Assistant - CGIAR Research
							Program on Climate Change, Agriculture and Food Security (CCAFS)</div>
						<div class="organization">International Center for Tropical
							Agriculture (CIAT)</div>
						<div class="email">c.barrios@cgiar.org</div>
					</div>
					<div class="team_member">
						<div class="member_photo">
							<img
								src="http://ccafs.cgiar.org/sites/default/files/styles/medium/public/people/pictures/AndyJarvis.jpg?itok=LKqX_eD8" />
						</div>
						<div class="name">Andy Jarvis - Research Area Director
							Decision and Policy Analysis</div>
						<div class="email">Flagship Leader for Climate Smart
							Agriculture CGIAR Research Program on Climate Change, Agriculture
							and Food Security (CCAFS)</div>
						<div class="email">International Center for Tropical
							Agriculture (CIAT)</div>
						<div class="email">a.jarvis@cgiar.org</div>
					</div>
					<div class="team_member">
						<div class="member_photo">
							<img
								src="http://ccafs.cgiar.org/sites/default/files/styles/medium/public/people/pictures/osana.png?itok=7sEAZ64p" />
						</div>
						<div class="name">Osana Bonilla-Findji - Science Officer</div>
						<div class="email">Theme Adaptation to Progressive Climate
							Change CGIAR Program on Climate Change, Agriculture and Food
							Security (CCAFS)</div>
						<div class="email">International Center for Tropical
							Agriculture (CIAT)</div>
						<div class="email">o.bonilla@cgiar.org</div>
					</div>
					<div class="team_member">
						<div class="member_photo">
							<img src="img/team.png" />
						</div>
						<div class="name">David Arango Londono - Statistical analyst</div>
						<div class="email">CGIAR Research Program on Climate Change,
							Agriculture and Food Security (CCAFS)</div>
						<div class="email">Research Assistant - International Center
							for Tropical Agriculture (CIAT)</div>
						<div class="email">d.arango@cgiar.org</div>
					</div>
					<div class="team_member">
						<div class="member_photo">
							<img
								src="http://ccafs.cgiar.org/sites/default/files/styles/medium/public/people/pictures/j.ramirez-villegas.jpg?itok=uGYsM25E" />
						</div>
						<div class="name">Julian Ramirez-Villegas - Postdoctoral
							Fellow</div>
						<div class="email">School of Earth and Environment,
							University of Leeds, UK CGIAR Research Program on Climate Change,
							Agriculture and Food Security (CCAFS)</div>
						<div class="email">International Center for Tropical
							Agriculture (CIAT)</div>
						<div class="email">j.r.villegas@cgiar.org</div>
					</div>
					<div class="team_member">
						<div class="member_photo">
							<img src="img/Carlitos.jpg" />
						</div>
						<div class="name">Carlos Eduardo Navarro - Research
							assistant</div>
						<div class="email">International Center for Tropical
							Agriculture (CIAT)</div>
						<div class="email">c.e.navarro@cgiar.org</div>
					</div>
					<div class="team_member">
						<div class="member_photo">
							<img src="img/team.png" />
						</div>
						<div class="name">Noah Matovu - Systems Engineer</div>
						<div class="email">DAPA-CCAFS</div>
						<div class="email">n.matovu@cgiar.org</div>
					</div>
				</div>
			</div>

			<div class="sec_details">
				<h1>About CCAFS TPE</h1>
				<p>The Target Population Environment Platform is part of the
					2years collaborative project between EMBRAPA and CIAT
					"Methodological development of an online tool for the
					identification of Target Population Environments: improving the
					predictions of agricultural production using crop models".</p>
				<p>The two-year project (2012 - 2014) aims to calibrate and
					validate models for improved yield prediction and to develop an
					online tool for the identification of Target Population
					Environments (TPEs), i.e., a set of environments to which the
					improved crop varieties developed by breeding programs need to be
					adapted. The research attempts to support the implementation of
					TPEs globally for important crops, to understand their behavior
					under different environmental conditions and to contribute to
					decision making in breeding programs. The project will deliver the
					second objective of Theme 1 on Adaptation to Progressive Change,
					"Develop breeding strategies for addressing abiotic and biotic
					stresses under future climate change, including changes in the mean
					and variability of climate,".</p>
				<p>The overall goal of TPE Platform is to show the results of
					the identification and characterization of TPE zones for rice and
					bean production areas in Colombia and Brazil and provides a module
					for replicating the same kind of analysis in other geographical
					areas.</p>
			</div>

			<div class="sec_details">
				<h1>How to cite Us</h1>
				<p>CGIAR Research Program on Climate Change and Agricultural
					Food Security (CCAFS) 2015. Target Population of Environments
					Platform. http://www.ccafs-tpe.org/</p>
			</div>
		</div>
		<div class="pane_right">
			<div id="contact_pane">

				<!--action="contactInfo" method="post"  -->
				<form name="contactForm" id="contactForm" action="contactInfo"
					method="post">
					<div class="contact-info">
						<h1>Have a question or an exciting new idea?</h1>
						<!-- We would love to hear from you.	Have a question or an exciting new idea? -->
					</div>
					<table id="contact_table">
						<tr>
							<td><s:label key="contact.name"
									cssClass="contact-text-label" /></td>
						<tr>
							<td><s:textfield id="name" name="name"
									cssClass="contact-text"
									placeholder="%{getText('contact.name')}"></s:textfield></td>
						<tr>
							<td><s:label key="contact.organization"
									cssClass="contact-text-label" /></td>
						</tr>
						<tr>
							<td><s:textfield id="organization" name="organization"
									cssClass="contact-text"
									placeholder="%{getText('contact.organization')}"></s:textfield>
							</td>
						</tr>
						<tr>
							<td><s:label key="contact.email"
									cssClass="contact-text-label" /></td>
						</tr>
						<tr>
							<td><s:textfield id="email" name="email"
									cssClass="contact-text"
									placeholder="%{getText('contact.email')}">
								</s:textfield></td>
						</tr>
						<tr>
							<td><s:label key="contact.message"
									cssClass="contact-text-label" /></td>
						</tr>
						<tr>
							<td><s:textarea name="details" rows="17" id="details"
									cssClass="join-text-area"
									placeholder="%{getText('contact.message')}"
									cssStyle="resize: none;" /></td>
						</tr>
						<tr>
							<td>
						</tr>
						<tr>
							<td style="text-align: right;"><s:submit id="send"
									name="send" value="Send" /></td>
						</tr>
					</table>
				</form>
			</div>

			<!-- The Physical Address -->
			<div id="contact_address">
				<h1>Address & Inquiries</h1>
				<h4>Headquarters Office</h4>
				<ul>
					<li>Km 17, Recta Cali-Palmira</li>
					<li>Apartado Aereo 6713</li>
					<li>Cali, Colombia</li>
					<li>Phone: +57 2 4450000 (direct), +1 650 8336625 (via USA)</li>
					<li>Fax: +57 2 4450073 (direct), +1 650 8336626 (via USA)</li>
					<li>E-mail: ciat@cgiar.org</li>
					<li>Contact: Elcio Guimaraes, Regional Director</li>
					<li>E-mail: e.guimaraes@cgiar.org</li>
					<li></li>
				</ul>

			</div>

		</div>

	</div>
</body>
</html>