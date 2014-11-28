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
			<!--Project Documentation Area-->
			<h4 class="navigation">
				<a href="documentation.jspx">Documentation</a> > <a href="#">Project</a>
			</h4>
			<div class="sec_details">
				<h4>Project Summary</h4>
				<p>Project Summary Documentation Details Here</p>
			</div>
			<div class="sec_details">
				<h4>Next Steps</h4>
				<p>Next steps for the project here</p>
			</div>
		</div>
		<div class="pane_right">
			<jsp:include page="right_pane.jsp" />
		</div>
	</div>
</body>
</html>