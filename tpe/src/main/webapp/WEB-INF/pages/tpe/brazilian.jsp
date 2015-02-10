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
		<h4 class="navigation">
			<a href="documentation.jspx">Documentation</a> > <a href="#">Brazilian
				Case Study</a>
		</h4>
		<div>
			<div class="pane_left">
				<!--Brazilian Case Study-->
				<div class="sec_details">
					<h4>Brazilian Case Study</h4>
					<p>Brazilian Case Study Details Here</p>
				</div>
			</div>
			<div class="pane_right">
				<jsp:include page="right_pane.jsp" />
			</div>
		</div>
	</div>
</body>
</html>