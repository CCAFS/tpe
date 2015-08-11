<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<title>TPE Documentation</title>
<script type="text/javascript">
	$(document).ready(function() {

		$(".doc-index a, a.doc-index").click(function() {
			$('.pane_left').load("${ctx}/docs/docs.action");
			return false
		});
		$(".project a,a.project").click(function() {
			$('.pane_left').load("${ctx}/docs/project.action");
			return false
		});
		$(".scientific a, a.scientific").click(function() {
			$('.pane_left').load("${ctx}/docs/scientific.action");
			return false
		});
	});
</script>
</head>
<body>
	<div id="documentation">
		<h4 class="navigation">
			<a class="doc-index" href="#">Documentation</a><a class="project"
				href="#">Project</a><a class="scientific" href="#">Scientific</a>
		</h4>
		<div>
			<div class="pane_left">
				<!--Documentation Area-->
				<div class="doc-list project">
					<div class="img">
						<a><img src="${ctx}/img/tpe_map.jpg" alt="image" /> </a>
					</div>
					<div class="doc-min project">
						<h4>
							<a>Project</a>
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
							of Theme 1 on Adaptation to Progressive Change, Develop breeding
							strategies for addressing abiotic and biotic stresses under
							future climate change, including changes in the mean and
							variability of climate. The overall goal of TPE Platform is to
							show the results of the identification and characterization of
							TPE zones for rice and bean production areas in Colombia and
							Brazil and provides a module for replicating the same kind of
							analysis in other geographical areas.</p>
					</div>
				</div>
				<div class="doc-list scientific">
					<div class="img">
						<a href="#"><img src="${ctx}/img/tpe_map.jpg" alt="image" />
						</a>
					</div>
					<div class="doc-min scientific">
						<h4>
							<a href="#">Scientific Documentation</a>
						</h4>
						<p>This is about TPE Platform</p>
					</div>
				</div>
				<div class="doc-list brazilian">
					<div class="img">
						<a href="#"><img src="${ctx}/img/tpe_map.jpg" alt="image" />
						</a>
					</div>
					<div class="doc-min brazilian">
						<h4>
							<a href="#">Brazilian Case Study</a>
						</h4>
						<p>Brazilian Case Study</p>
					</div>
				</div>
				<div class="doc-list colombian">
					<div class="img">
						<a href="#"><img src="${ctx}/img/tpe_map.jpg" alt="image" />
						</a>
					</div>
					<div class="doc-min colombian">
						<h4>
							<a href="#">Colombian Case Study</a>
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