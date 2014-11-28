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
			<!--Documentation Area-->
			<h4 class="navigation">
				<a href="#">Documentation</a>
			</h4>

			<div class="tpe_sec">
				<div class="tpe_sec_img">
					<a href="project.jspx"><img src="img/tpe_map.jpg" alt="image" />
					</a>
				</div>
				<h4>
					<a href="project.jspx">Project</a>
				</h4>
				<p>This is about TPE Platform</p>
			</div>
			<div class="tpe_sec">
				<div class="tpe_sec_img">
					<a href="scientific.jspx"><img src="img/tpe_map.jpg"
						alt="image" /> </a>
				</div>
				<h4>
					<a href="scientific.jspx">Scientific Documentation</a>
				</h4>
				<p>This is about TPE Platform</p>
			</div>
			<div class="tpe_sec">
				<div class="tpe_sec_img">
					<a href="tutorials.jspx"><img src="img/tpe_map.jpg" alt="image" />
					</a>
				</div>
				<h4>
					<a href="tutorials.jspx">Tutorials</a>
				</h4>
				<p>This is about TPE Platform</p>
			</div>

		</div>
		<div class="pane_right">
			<jsp:include page="right_pane.jsp" />
		</div>
	</div>
</body>
</html>