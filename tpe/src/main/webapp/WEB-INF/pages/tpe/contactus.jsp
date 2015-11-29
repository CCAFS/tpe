<%@ include file="/common/taglibs.jsp"%>
<html lang="en">
<head>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
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
					url : "${ctx}/contact_us.action",
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
		<!-- <h4 class="navigation">
			<a href="#">About Us</a>
		</h4> -->
		<!--TPE Team Area-->
		<div>
			<div class="pane_left">
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
							<img src="${ctx}/resources/bcamilo.png" />
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
							<img src="${ctx}/resources/david.jpg" />
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
							<img src="${ctx}/resources/Carlitos.jpg" />
						</div>
						<div class="member_infos">
							<h5>Carlos Eduardo Navarro - Research assistant</h5>
							<p>International Center for Tropical Agriculture (CIAT)</p>
							<p>c.e.navarro@cgiar.org</p>
						</div>
					</div>

					<div class="member">
						<div class="member_photo">
							<img src="${ctx}/img/team.png" />
						</div>
						<div class="member_infos">
							<h5>Noah Matovu - Systems Engineer</h5>
							<p></p>
							<p>noahmatovu@yahoo.com</p>
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
								<td>
									<%-- <s:label key="contact.name"
										cssClass="contact-text-label" /> --%>
								</td>
							<tr>
								<td><input id="name" name="name" class="contact-text"
									type="text" placeholder="Your Name"></input></td>
							<tr>
								<td>
									<%-- <s:label key="contact.organization"
										cssClass="contact-text-label" /> --%>
								</td>
							</tr>
							<tr>
								<td><input id="organization" name="organization"
									class="contact-text" type="text"
									placeholder="Organization Name"></input></td>
							</tr>
							<tr>
								<td>
									<%-- <s:label key="contact.email"
										cssClass="contact-text-label" /> --%>
								</td>
							</tr>
							<tr>
								<td><input id="email" name="email" class="contact-text"
									type="text" placeholder="Your email"></input></td>
							</tr>
							<tr>
								<td>
									<%-- <s:label key="contact.message"
										cssClass="contact-text-label" /> --%>
								</td>
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