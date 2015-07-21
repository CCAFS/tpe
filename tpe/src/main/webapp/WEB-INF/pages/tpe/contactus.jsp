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
			e.preventDefault();

			//$('span.errorMessage').text('');
			// checking for inputs
			var input = $("#name").val();
			// no error checking
			if (input.length != 0) {
				// send message if no error

				$('.notify-sending').animate({
					width : 'toggle'
				});
				//e.preventDefault();
				var data = $('#contactUs').serialize();
				$('#send').attr("disabled", true);
				$.ajax({
					global : false,
					url : "contact_us.action",
					type : "POST",
					data : data,
					//async : false,
					success : function() {
						//alert("Just contacted the tpe support team...");
						$('#email').val('');
						$('#organization').val('');
						$('#name').val('');
						$('#details').val('');
						$(".notify-sending").hide();
						$('.notify-sent').show().delay(5000).fadeOut();
						$('#send').removeAttr("disabled");
						$("#name").css({
							'border' : '1px solid #ccc'
						})
					}
				});
			} else {
				$("#name").css({
					'border-color' : '#990000'
				})
			}
			return false;
		});
	});
</script>
</head>

<body>
	<div id="contact_main">
		<h4 class="navigation">
			<a href="#">About Us</a>
		</h4>
		<!--TPE Team Area-->
		<div>
			<div class="pane_left">
				<!--Contact Area-->
				<div class="sec_details">
					<h4>Who we are?</h4>
					<h4>Institution</h4>
					<p>The CGIAR Research Programme on Climate Change, Agriculture
						and Food Security (CCAFS) is a research partnership between CGIAR
						and Future Earth, the global environmental change community aiming
						to identify and address the most important interactions, synergies
						and trade- offs between climate change and agriculture. Our goal
						is to promote a food-secure world through the provision of
						science-based efforts that support sustainable agriculture and
						enhance livelihoods while adapting to climate change and
						conserving natural resources and environmental services.</p>
					<p>As a collective effort coordinated by the International
						Center for Tropical Agriculture (CIAT), CCAFS will become a hub
						that facilitates action across 15 CGIAR centers and other CGIAR
						Research Programs while involving farmers, policy makers, donors
						and other stakeholders, to integrate their knowledge and needs
						into the tools and approaches that are developed. For more
						information visit www.ccafs.cgiar.org</p>
				</div>

				<div class="sec_details">
					<h4>About CCAFS TPE</h4>
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
						stresses under future climate change, including changes in the
						mean and variability of climate,".</p>
					<p>The overall goal of TPE Platform is to show the results of
						the identification and characterization of TPE zones for rice and
						bean production areas in Colombia and Brazil and provides a module
						for replicating the same kind of analysis in other geographical
						areas.</p>
				</div>

				<div class="sec_details">
					<h4>How to cite Us</h4>
					<p>CGIAR Research Program on Climate Change and Agricultural
						Food Security (CCAFS) 2015. Target Population of Environments
						Platform. http://www.ccafs-tpe.org/</p>
				</div>
				<div class="team">
					<h4>CCAFS TPE Team</h4>
					<div class="member">
						<div class="member_photo">
							<img
								src="http://www.cnpaf.embrapa.br/quemsomos/fotos/alexandreheinemann.jpg" />
						</div>
						<div class="member_infos">
							<h5>Alexandre Bryan Heinemann - Ecophysiology Research</h5>
							<p>Embrapa Arroz e Feijao Empresa Brasileira de Pesquisa
								Agropecuaria (Embrapa)</p>
							<p>alexandre.heinemann@embrapa.br</p>
						</div>
					</div>

					<div class="member">
						<div class="member_photo">
							<img src="img/bcamilo.png" />
						</div>
						<div class="member_infos">
							<h5>Camilo Barrios Perez - Agricultural Engineer</h5>
							<p>Research Assistant - CGIAR Research Program on Climate
								Change, Agriculture and Food Security (CCAFS)</p>
							<p>International Center for Tropical Agriculture (CIAT)</p>
							<p>c.barrios@cgiar.org</p>
						</div>
					</div>

					<div class="member">
						<div class="member_photo">
							<img
								src="http://ccafs.cgiar.org/sites/default/files/styles/medium/public/people/pictures/AndyJarvis.jpg?itok=LKqX_eD8" />
						</div>
						<div class="member_infos">
							<h5>Andy Jarvis - Research Area Director Decision and Policy
								Analysis</h5>
							<p>Flagship Leader for Climate Smart Agriculture CGIAR
								Research Program on Climate Change, Agriculture and Food
								Security (CCAFS)</p>
							<p>International Center for Tropical Agriculture (CIAT)</p>
							<p>a.jarvis@cgiar.org</p>
						</div>
					</div>

					<div class="member">
						<div class="member_photo">
							<img
								src="http://ccafs.cgiar.org/sites/default/files/styles/medium/public/people/pictures/osana.png?itok=7sEAZ64p" />
						</div>
						<div class="member_infos">
							<h5>Osana Bonilla-Findji - Science Officer</h5>
							<p>Theme Adaptation to Progressive Climate Change CGIAR
								Program on Climate Change, Agriculture and Food Security (CCAFS)</p>
							<p>International Center for Tropical Agriculture (CIAT)</p>
							<p>o.bonilla@cgiar.org</p>
						</div>
					</div>

					<div class="member">
						<div class="member_photo">
							<img src="img/david.jpg" />
						</div>
						<div class="member_infos">
							<h5>David Arango Londono - Statistical analyst</h5>
							<p>CGIAR Research Program on Climate Change, Agriculture and
								Food Security (CCAFS)</p>
							<p>Research Assistant - International Center for Tropical
								Agriculture (CIAT)</p>
							<p>d.arango@cgiar.org</p>
						</div>
					</div>

					<div class="member">
						<div class="member_photo">
							<img
								src="http://ccafs.cgiar.org/sites/default/files/styles/medium/public/people/pictures/j.ramirez-villegas.jpg?itok=uGYsM25E" />
						</div>
						<div class="member_infos">
							<h5>Julian Ramirez-Villegas - Postdoctoral Fellow</h5>
							<p>School of Earth and Environment, University of Leeds, UK
								CGIAR Research Program on Climate Change, Agriculture and Food
								Security (CCAFS)</p>
							<p>International Center for Tropical Agriculture (CIAT)</p>
							<p>j.r.villegas@cgiar.org</p>
						</div>
					</div>

					<div class="member">
						<div class="member_photo">
							<img src="img/Carlitos.jpg" />
						</div>
						<div class="member_infos">
							<h5>Carlos Eduardo Navarro - Research assistant</h5>
							<p>International Center for Tropical Agriculture (CIAT)</p>
							<p>c.e.navarro@cgiar.org</p>
						</div>
					</div>

					<div class="member">
						<div class="member_photo">
							<img src="img/team.png" />
						</div>
						<div class="member_infos">
							<h5>Noah Matovu - Systems Engineer</h5>
							<p>DAPA-CCAFS</p>
							<p>n.matovu@cgiar.org</p>
						</div>
					</div>
				</div>
			</div>
			<div class="pane_right">
				<div id="contact_pane">
					<div class="notify-sent">Message Sent</div>
					<div class="notify-sending">Sending...</div>
					<!--action="contactInfo" method="post"  -->
					<form method="post" id="contactUs">
						<div class="contact-info">
							<h4>Have a question or an exciting new idea?</h4>
							<!-- We would love to hear from you.	Have a question or an exciting new idea? -->
						</div>
						<table id="contact_table">
							<tr>
								<td><s:label key="contact.name"
										cssClass="contact-text-label" /></td>
							<tr>
								<td><input id="name" name="name" class="contact-text"
									type="text" placeholder="Your Name"></input></td>
							<tr>
								<td><s:label key="contact.organization"
										cssClass="contact-text-label" /></td>
							</tr>
							<tr>
								<td><input id="organization" name="organization"
									class="contact-text" type="text"
									placeholder="Organization Name"></input></td>
							</tr>
							<tr>
								<td><s:label key="contact.email"
										cssClass="contact-text-label" /></td>
							</tr>
							<tr>
								<td><input id="email" name="email" class="contact-text"
									type="text" placeholder="Your email"></input></td>
							</tr>
							<tr>
								<td><s:label key="contact.message"
										cssClass="contact-text-label" /></td>
							</tr>
							<tr>
								<td><textarea name="details" rows="10" id="details"
										class="join-text-area" placeholder="Your details here"
										style="resize: none;"></textarea></td>
							</tr>
							<tr>
								<td>
							</tr>
							<tr>
								<td style="text-align: right;">
									<button class="button-send" id="send" name="send">Send</button>
								</td>
							</tr>
						</table>
					</form>
				</div>

				<!-- The Physical Address -->
				<div id="contact_address">
					<h4>Address & Inquiries</h4>
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
	</div>
</body>
</html>