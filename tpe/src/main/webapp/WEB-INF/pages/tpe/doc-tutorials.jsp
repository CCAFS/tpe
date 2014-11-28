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
			<!--TPE Project Tutorials Area-->
			<h4 class="navigation">
				<a href="documentation.jspx">Documentation</a> > <a href="#">Tutorials</a>
			</h4>
			<div class="sec_details">
				<h4>How to use TPE tool?</h4>
				<p>Brief description how to us teh TPE tool</p>
			</div>
			<div class="sec_details">
				<h4>Video Tutorials</h4>
				<p>Embed the Youtube TPE Video tutorial links</p>
			</div>

			<div class="sec_details">
				<h4>TPE Scripts</h4>
				<p>Provides a list of TPE Scripts</p>
			</div>

			<div class="sec_details">
				<h4>How to download results?</h4>
				<p>A brief description how a user can download the results.</p>
			</div>

		</div>
		<div class="pane_right">
			<jsp:include page="right_pane.jsp" />
		</div>
	</div>
</body>
</html>