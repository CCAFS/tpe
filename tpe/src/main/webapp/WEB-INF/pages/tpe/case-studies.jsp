<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<title>TPE Documentation</title>
</head>
<body>
	<div id="documentation">
		<h4 class="navigation">
			<a class="doc-index" href="#">Case Studies</a> / Where we work
		</h4>
		<div>
			<div class="pane_left">
				<!--Where we work Area-->
				<div class="doc-list colombian">
					<div class="img">
						<a href="${ctx}/casestudy/colombian.jspx"><img
							src="${ctx}/img/case-colombia.jpg" alt="image" /> </a>
					</div>
					<div class="doc-min colombian">
						<h4>
							<a href="${ctx}/casestudy/colombian.jspx">Colombia - Lowlands</a>
						</h4>
						<p>Researchers from the University of Oxford arrived in Beora,
							Nepal on a hot, humid day in May of this year. It was here,
							between the mid-hills region of Nepal and the border of India..</p>
					</div>
				</div>
				<div class="doc-list brazilian">
					<div class="img">
						<a href="${ctx}/casestudy/brazilian.jspx"><img
							src="${ctx}/img/case-brazil.jpg" alt="image" /> </a>
					</div>
					<div class="doc-min brazilian">
						<h4>
							<a href="${ctx}/casestudy/brazilian.jspx">Brazil - Uplands</a>
						</h4>
						<p>Fifty-five year old Rosalia Shemdoe feels empowered. She
							lives and works in Yamba village in the Lushoto district of
							northern Tanzania, but she just got back from what..</p>
					</div>
				</div>

				<div class="doc-list lamerica">
					<div class="img">
						<a href="${ctx}/casestudy/lamerica.jspx"><img
							src="${ctx}/img/case-latin.jpg" alt="image" /> </a>
					</div>
					<div class="doc-min lamerica">
						<h4>
							<a href="${ctx}/casestudy/lamerica.jspx">CCAFS in Nepal:
								Finding the future of Beora - Part I </a>
						</h4>
						<p>Around 120 km west of the capital Kathmandu, this is a
							hazy, sweltering, lowland plain, where daytime temperatures hover
							around 45 degrees Celsius, with little respite after dark.</p>
					</div>
				</div>
			</div>
			<div class="pane_right">
				<jsp:include page="right_pane.jsp" />
			</div>
		</div>
	</div>
</body>
</html>