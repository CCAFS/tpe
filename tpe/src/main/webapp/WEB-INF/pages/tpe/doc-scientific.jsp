<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html lang="en">
<head>
<meta charset="utf-8">
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<title>TPE Documentation</title>
</head>

<body>
	<div id="documentation">
		<div class="pane_left">
			<!--Scientific Documentation Area-->
			<h4 class="navigation">
				<a href="documentation.jspx">Documentation</a> > <a href="#">Scientific
					Documentation</a>
			</h4>
			<div class="sec_details">
				<h4>TPE Scientific Documentation</h4>
				<p>Scientific Documentation Details Here</p>
			</div>
			<div class="sec_details">
				<h4>Scientific Algorithms</h4>
				<p>Scientific Algorithms and Methods for TPE project</p>
			</div>
		</div>
		<div class="pane_right">
			<jsp:include page="right_pane.jsp" />
		</div>
	</div>
</body>
</html>