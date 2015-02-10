<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html lang="en">
<head>
<meta charset="utf-8">
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/script/flickr-tpe.js"></script>
<title>TPE Documentation</title>
</head>

<body>
	<div id="documentation">
		<h4 class="navigation">
			<a href="#">Documentation</a>
		</h4>
		<div>
			<div class="pane_left">
				<!--Documentation Area-->
				<div class="tpe_sec">
					<div class="tpe_sec_img">
						<div class="img_box">
							<a href="project.jspx"><img src="img/tpe_map.jpg" alt="image" />
							</a>
						</div>
					</div>
					<div class="doc-min">
						<h4>
							<a href="project.jspx">Project</a>
						</h4>
						<p>The Target Population Environment Platform is part of the
							2years collaborative project between EMBRAPA and CIAT
							"Methodological development of an online tool for the
							identification of Target Population Environments: improving the
							predictions of agricultural production using crop models". The
							two-year project (2012 - 2014) aims to calibrate and validate
							models for improved yield prediction and to develop an online
							tool for the identification of Target Population Environments
							(TPEs), i.e., a set of environments to which the improved crop
							varieties developed by breeding programs need to be adapted. The
							research attempts to support the implementation of TPEs globally
							for important crops, to understand their behavior under different
							environmental conditions and to contribute to decision making in
							breeding programs. The project will deliver the second objective
							of Theme 1 on Adaptation to Progressive Change, “Develop
							breeding strategies for addressing abiotic and biotic stresses
							under future climate change, including changes in the mean and
							variability of climate,” . The overall goal of TPE Platform is
							to show the results of the identification and characterization of
							TPE zones for rice and bean production areas in Colombia and
							Brazil and provides a module for replicating the same kind of
							analysis in other geographical areas.</p>
					</div>
				</div>
				<div class="tpe_sec">
					<div class="tpe_sec_img">
						<div class="img_box">
							<a href="scientific.jspx"><img src="img/tpe_map.jpg"
								alt="image" /> </a>
						</div>
					</div>
					<div class="doc-min">
						<h4>
							<a href="scientific.jspx">Scientific Documentation</a>
						</h4>
						<p>This is about TPE Platform</p>
					</div>
				</div>
				<div class="tpe_sec">
					<div class="tpe_sec_img">
						<div class="img_box">
							<a href="brazilian.jspx"><img src="img/tpe_map.jpg"
								alt="image" /> </a>
						</div>
					</div>
					<div class="doc-min">
						<h4>
							<a href="brazilian.jspx">Brazilian Case Study</a>
						</h4>
						<p>Brazilian Case Study</p>
					</div>
				</div>
				<div class="tpe_sec">
					<div class="tpe_sec_img">
						<div class="img_box">
							<a href="colombian.jspx"><img src="img/tpe_map.jpg"
								alt="image" /> </a>
						</div>
					</div>
					<div class="doc-min">
						<h4>
							<a href="colombian.jspx">Colombian Case Study</a>
						</h4>
						<p>Colombian Case Study</p>
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