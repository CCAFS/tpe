<%@ include file="/common/taglibs.jsp"%>
<html lang="en">
<head>
<meta charset="utf-8">
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
					<h4>Project Coordination</h4>
					<div class="member">
						<div class="member_photo">
							<img src="${ctx}/resources/alex.jpg" />
						</div>
						<div class="member_infos">
							<h5>Dr. Alexandre B. Heinemann (project Leader)</h5>
							<p>Embrapa Arroz e Feijao, Empresa Brasileira de Pesquisa
								Agropecuaria (Embrapa)</p>
							<p>E-mail: alexandre.heinemann@embrapa.br</p>
						</div>
					</div>
					<div class="member">
						<div class="member_photo">
							<img src="${ctx}/resources/julian.jpg" />
						</div>
						<div class="member_infos">
							<h5>Dr. Julian Ramirez-Villegas (project co-Investigator)</h5>
							<p>International Center for Tropical Agriculture (CIAT)</p>
							<p>School of Earth and Environment, University of Leeds</p>
							<p>CGIAR Research Program on Climate Change, Agriculture and
								Food Security (CCAFS)</p>
							<p>E-mail: j.r.villegas@cgiar.org</p>
						</div>
					</div>
					<h4>Project research and development support</h4>
					<div class="member">
						<div class="member_photo">
							<img src="${ctx}/resources/camilo.png" />
						</div>
						<div class="member_infos">
							<h5>Camilo Barrios P&#233rez (research assistant, crop modeling)</h5>
							<p>International Center for Tropical Agriculture (CIAT)</p>
							<p>CGIAR Research Program on Climate Change, Agriculture and
								Food Security (CCAFS)</p>
							<p>E-mail: c.barrios@cgiar.org</p>
						</div>
					</div>
					<div class="member">
						<div class="member_photo">
							<img src="${ctx}/resources/davido.jpg" />
						</div>
						<div class="member_infos">
							<h5>David Arango Londo&#241o (research assistant, statistical
								analysis)</h5>
							<p>International Center for Tropical Agriculture (CIAT)</p>
							<p>CGIAR Research Program on Climate Change, Agriculture and
								Food Security (CCAFS)</p>
							<p>E-mail: d.arango@cgiar.org</p>
						</div>
					</div>
					<div class="member">
						<div class="member_photo">
							<img src="${ctx}/resources/nmatovu.jpg" />
						</div>
						<div class="member_infos">
							<h5>Noah Matovu (web developer)</h5>
							<p>IT and web consultant</p>
							<p>E-mail: noahmatovu@yahoo.com</p>
						</div>
					</div>
					<h4>CCAFS Contacts</h4>
					<div class="member">
						<div class="member_photo">
							<img src="${ctx}/resources/andy.jpg" />
						</div>
						<div class="member_infos">
							<h5>Dr. Andy Jarvis (CCAFS Flagship Leader)</h5>
							<p>International Center for Tropical Agriculture (CIAT)</p>
							<p>CGIAR Research Program on Climate Change, Agriculture and
								Food Security (CCAFS)</p>
							<p>E-mail: a.jarvis@cgiar.org</p>
						</div>
					</div>

					<div class="member">
						<div class="member_photo">
							<img src="${ctx}/resources/osana.png" />
						</div>
						<div class="member_infos">
							<h5>Dr. Osana Bonilla-Findji (CCAFS Science Officer)</h5>
							<p>International Center for Tropical Agriculture (CIAT)</p>
							<p>CGIAR Research Program on Climate Change, Agriculture and
								Food Security (CCAFS)</p>
							<p>E-mail: o.bonilla@cgiar.org</p>
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
					<h4>Address and Inquiries</h4>
					<h4>Embrapa contact:</h4>
					<ul>
						<li>Alexandre B. Heinemann, project leader</li>
						<li>E-mail: alexandre.heinemann@embrapa.br</li>
						<li>Embrapa Rice & Beans Rodovia GO-462 km 12 Zona Rural,
							75375-000 Santo Antonio de Goi&#225s, GO, Brazil</li>
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