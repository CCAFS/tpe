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
				<p>The Target Population Environment Platform is part of the
					2years collaborative project between EMBRAPA and CIAT
					"Methodological development of an online tool for the
					identification of Target Population Environments: improving the
					predictions of agricultural production using crop models".</p>
				<p>The two-year project (2012 - 2014) aims to calibrate and
					validate models for improved yield prediction and to develop an
					online tool for the identification of Target Population
					Environments (TPEs), i.e., a set of environments to which the
					improved crop varieties developed by breeding programs need to be
					adapted. The research attempts to support the implementation of
					TPEs globally for important crops, to understand their behavior
					under different environmental conditions and to contribute to
					decision making in breeding programs. The project will deliver the
					second objective of Theme 1 on Adaptation to Progressive Change,
					"Develop breeding strategies for addressing abiotic and biotic
					stresses under future climate change, including changes in the mean
					and variability of climate,".</p>
				<p>The overall goal of TPE Platform is to show the results of
					the identification and characterization of TPE zones for rice and
					bean production areas in Colombia and Brazil and provides a module
					for replicating the same kind of analysis in other geographical
					areas.</p>
			</div>
			<div class="sec_details">
				<h4>Next Steps</h4>
				<p>Providing a module for replicating the same kind of analysis
					in other geographical areas.</p>
			</div>
		</div>
		<div class="pane_right">
			<jsp:include page="right_pane.jsp" />
		</div>
	</div>
</body>
</html>