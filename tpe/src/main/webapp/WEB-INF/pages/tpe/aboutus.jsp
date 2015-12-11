<%@ include file="/common/taglibs.jsp"%>
<html lang="en">
<head>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<title>About Us</title>
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
				<!--Contact Area-->
				<div class="sec_details">
					<h4>About CCAFS</h4>
					<p>The CGIAR Research Programme on Climate Change, Agriculture
						and Food Security (CCAFS) is a research partnership between CGIAR
						and Future Earth, the global environmental change community aiming
						to identify and address the most important interactions, synergies
						and trade- offs between climate change and agriculture. Our goal
						is to promote a food-secure world through the provision of
						science-based efforts that support sustainable agriculture and
						enhance livelihoods while adapting to climate change and
						conserving natural resources and environmental services.</p>
					<p>
						As a collective effort coordinated by the International Center for
						Tropical Agriculture (CIAT), CCAFS facilitates action across 15
						CGIAR centers and other CGIAR Research Programs while involving
						farmers, policy makers, donors and other stakeholders, to
						integrate their knowledge and needs into the tools and approaches
						that are developed. For more information visit <a
							href="www.ccafs.cgiar.org">www.ccafs.cgiar.org</a>
					</p>
					<table style="margin: auto;">
						<tr>
							<td style="margin-right: 20px;">Led By</td>
							<td>Strategic Partner</td>
						</tr>
						<tr>
							<td><div style="margin-right: 40px;" class="collab">
									<a href="http://ciat.cgiar.org/" target="_blank"><img
										src="${ctx}/img/logo_ciat.png" alt="CIAT" /> </a>
								</div></td>
							<td class="left-border"><div class="collab">
									<a href="https://www.embrapa.br/" target="_blank"><img
										src="${ctx}/img/logo_embrapa.png" alt="EMBRAPA" /> </a>
								</div></td>
						</tr>
					</table>
				</div>
				<div class="sec_details">
					<h4>How to cite Us</h4>
					<p>CGIAR Research Program on Climate Change and Agricultural
						Food Security (CCAFS) 2015. Target Population of Environments
						Platform. http://www.ccafs-tpe.org/</p>
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
					<h4>Address and Inquiries</h4>
					<h4>Embrapa contact:</h4>
					<ul>
						<li>Alexandre B. Heinemann, project leader</li>
						<li>E-mail: alexandre.heinemann@embrapa.br</li>
						<li>Embrapa Rice & Beans Rodovia GO–462 km 12 Zona Rural,
							75375-000 Santo Antonio de Goiás, GO, Brazil</li>
					</ul>
					<br />
					<h4>CIAT / CCAFS Contact:</h4>
					<ul>
						<li>Julian Ramirez-Villegas, project co-Investigator</li>
						<li>E-mail: j.r.villegas@cgiar.org</li>
						<li>International Center for Tropical Agriculture (CIAT), km
							17 recta Cali-Palmira, AA6713</li>
						<li>Landline: +57 (2) 445 0100</li>
						<li>Fax: +57 (2) 445 0073</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>