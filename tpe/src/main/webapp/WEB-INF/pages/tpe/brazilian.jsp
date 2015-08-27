<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<title>Brazil Case Study</title>
<script type="text/javascript">
	
</script>
</head>
<body>
	<div id="documentation">
		<h4 class="navigation">
			<a class="doc-index" href="${ctx}/casestudy/index.jspx">Home</a><a
				class="brazilian" href="#">/ Wherewe work/ Brazil</a>
		</h4>
		<div>
			<div class="pane_left">
				<!--Documentation Area-->
				<div class="study">
					<h4>
						<a href="#">Brazil - Lowlands</a>
					</h4>
					<%-- <div class="study-img">
						<h5>In Bajo Cauca and central regions...</h5>
						<img src="${ctx}/img/case-colombia-big.jpg" height="100"
							width="100" />
						<h5>Rice</h5>
					</div> --%>
					<p>Brazil Uplands</p>
				</div>
			</div>
			<div class="pane_right">
				<jsp:include page="right_pane.jsp" />
			</div>
		</div>
	</div>
</body>
</html>