<%@ include file="/common/taglibs.jsp"%>
<html lang="en">
<head>
<title>Contribute</title>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
<script>
	$(document).ready(function() {

		$("#send").click(function(e) {
			e.preventDefault();
			//$('span.errorMessage').text('');
			// checking for inputs
			var input = $("#name").val();
			// no error checking
			if (input.length != 0) {
				// send message if no error

				$('.sending-involved').animate({
					width : 'toggle'
				});
				//e.preventDefault();
				var data = $('#contributeForm').serialize();
				$('#send').attr("disabled", true);
				$.ajax({
					global : false,
					url : "${ctx}/contactInfo.action",
					type : "POST",
					data : data,
					//async : false,
					success : function() {
						//alert("Just contacted the tpe support team...");
						$('#email').val('');
						$('#organization').val('');
						$('#name').val('');
						$('#details').val('');
						$(".sending-involved").hide();
						$('.notify-involved').show().delay(5000).fadeOut();
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
	<div id="contribute">
		<!--Contribute Data Area-->

		<div id="get-involved">
			<!-- Brief Description of how to get involved in the project -->
			<div id="get-involved-info">
				<h1>How To Get Involved</h1>
				<p>There are many ways to be part of this Target Population of
					Environments Project. Individuals anywhere in the world will be
					able to contribute or get involved.</p>
				<p>We welcome the involvement of scientific documentation and
					data contribution for different crop varieties from other region.</p>
				<p>If you would like to get involved, please complete and submit
					this form and we will get back to you as soon as possible.</p>
			</div>
			<!-- For for getting involved -->

			<div id="join_form">
				<div class="notify-involved">Message Sent</div>
				<div class="sending-involved">Sending...</div>
				<h1>Get Involved</h1>
				<form id="contributeForm" method="post">
					<p>Please fill out the following form and then click Send.</p>
					<table id="join_table">
						<tr>
							<td>
								<%-- <s:label key="contact.name" cssClass="join-text-label" /> --%>
							</td>
						<tr>
							<td><input id="name" name="name" class="join-text"
								type="text" placeholder="Your Name"></input></td>
						<tr>
							<td>
								<%-- <s:label key="contact.organization" 
									cssClass="join-text-label" /> --%>
							</td>
						</tr>
						<tr>
							<td><input id="organization" name="organization"
								class="join-text" type="text" placeholder="Organization Name"></input></td>
						</tr>
						<tr>
							<td>
								<%-- <s:label key="contact.email" cssClass="join-text-label" /> --%>
							</td>
						</tr>
						<tr>
							<td><input id="email" name="email" class="join-text"
								type="text" placeholder="Your Email Address"></input></td>
						</tr>

						<tr>
							<td>
								<%-- <s:label key="join.message" cssClass="join-text-label" /> --%>
							</td>
						</tr>
						<tr>
							<td><textarea name="details" rows="8" class="join-text-area"
									placeholder="Your details" style="resize: none;"></textarea></td>
						</tr>
						<tr>
							<td>
						</tr>
						<tr>
							<td style="text-align: right;">
								<button class="button-send" id="send">Send</button>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</body>
</html>