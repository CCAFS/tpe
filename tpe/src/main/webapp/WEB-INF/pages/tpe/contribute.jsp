<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Contribute</title>


<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
<script>
	$(document)
			.ready(
					function() {

						$(
								'#tpe-bubble-1,#tpe-bubble-2,#tpe-bubble-3,#tpe-bubble-4,#tpe-bubble-5,#tpe-bubble-6,#tpe-bubble-7')
								.hover(function() {
									$('#involve-info-window').show(); // Show the info window
									/* $(this).animate({
										opacity : '0'
									}); */
								}, function() {
									$('#involve-info-window').hide(); // Hide the info window
									/* $(this).animate({
										opacity : '1'
									}); */
								});

						$("#send").click(
								function(e) {
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
										var data = $('#contributeForm')
												.serialize();
										$('#send').attr("disabled", true);
										$.ajax({
											global : false,
											url : "contactInfo.action",
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
												$('.notify-involved').show()
														.delay(5000).fadeOut();
												$('#send').removeAttr(
														"disabled");
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
		<div class="sec_cont">
			<h1>Get Involved with TPE Platform</h1>
			<div id="cartoon-map">
				<div id="motion-1"></div>
				<div id="motion-2"></div>
				<div id="motion-3"></div>
				<div id="motion-4"></div>
				<div id="motion-5"></div>
				<div id="motion-6"></div>
				<div id="motion-7"></div>
				<div id="tpe-bubble-1">Why only beans and rice? Can I
					contribute maize datasets for Peru?</div>
				<div id="tpe-bubble-2">This cultivar doesn't do well here, how
					can I target the favorable region?</div>
				<div id="tpe-bubble-3">What about other crops, maize, cassava?</div>
				<div id="tpe-bubble-4">When will TPE be implemented in Africa?</div>
				<div id="tpe-bubble-5">I have climate and soil datasets for
					maize, how can I contribute?</div>
				<div id="tpe-bubble-6">How can I target favorable environments
					in Mexico?</div>
				<div id="tpe-bubble-7">We've the TPE Visualization tools that
					searches crop specific mega environments</div>

			</div>
		</div>
		<div id="get-involved">
			<!-- Brief Description of how to get involved in the project -->
			<div id="get-involved-info">
				<h1>HOW TO GET INVOLVED</h1>
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
					<p>Please fill out the following form and then click Get
						Involved.</p>
					<table id="join_table">
						<tr>
							<td><s:label key="contact.name" cssClass="join-text-label" /></td>
						<tr>
							<td><input id="name" name="name" class="join-text"
								type="text" placeholder="Your Name"></input></td>
						<tr>
							<td><s:label key="contact.organization"
									cssClass="join-text-label" /></td>
						</tr>
						<tr>
							<td><input id="organization" name="organization"
								class="join-text" type="text" placeholder="Organization Name"></input></td>
						</tr>
						<tr>
							<td><s:label key="contact.email" cssClass="join-text-label" /></td>
						</tr>
						<tr>
							<td><input id="email" name="email" class="join-text"
								type="text" placeholder="Your Email Address"></input></td>
						</tr>

						<tr>
							<td><s:label key="join.message" cssClass="join-text-label" /></td>
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
	<div id="involve-info-window">This is the info window</div>
</body>
</html>