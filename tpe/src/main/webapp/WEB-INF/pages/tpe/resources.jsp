<%@ include file="/common/taglibs.jsp"%>
<html lang="en">
<head>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/script/flickr-tpe.js"></script>
<script type="text/javascript" src="${ctx}/script/tpe-tags.js"></script>
<title>TPE Documentation</title>
</head>

<body>
	<div id="documentation">
		<h4 class="navigation">
			<a href="#">Other Resources</a>
		</h4>
		<div>
			<div class="pane_left">
				<div class="tpe_sec">
					<div class="tpe_sec_img">
						<div class="img_box">
							<a href="${ctx}/tutorials.jspx"><img src="${ctx}/img/tpe_map.jpg"
								alt="image" /> </a>
						</div>
					</div>
					<div class="doc-min">
						<h4>
							<a href="${ctx}/tutorials.jspx">Tutorials</a>
						</h4>
						<p>Target Population of Environments (TPE) Platform is an
							online tool for the identification of target population
							environments (TPEs). This tutorial provides an overview of how
							TPE platform works and will teach you how to use the platform.
							The figure below shows how TPE platform's most important
							components. The rest of this chapter describes the tool and
							sections in this figure.</p>
					</div>
				</div>

			</div>
			<div class="pane_right">
				<jsp:include page="right_pane.jsp" />
			</div>
		</div>
	</div>
</body>