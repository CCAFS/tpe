<%@ include file="/common/taglibs.jsp"%>
<html lang="en">
<head>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<%-- <script type="text/javascript" src="${ctx}/script/flickr-tpe.js"></script> --%>
<script type="text/javascript" src="${ctx}/script/tpe-tags.js"></script>
<title>Resources</title>
</head>

<body>
	<div id="documentation">
		<!-- <h4 class="navigation">
			<a href="#">Other Resources</a>
		</h4> -->
		<div>
			<div class="pane_left">
				<div class="tpe_sec">
					<div class="tpe_sec_img">
						<div class="img_box">
							<a href="${ctx}/documentation.jspx"><img
								src="${ctx}/resources/document-big.png" alt="documentation" /> </a>
						</div>
					</div>
					<div class="doc-min">
						<h4>
							<a href="${ctx}/documentation.jspx">Documentation</a>
						</h4>
						<p>This provides TPE documentation.</p>
					</div>
				</div>
				<div class="tpe_sec">
					<div class="tpe_sec_img">
						<div class="img_box">
							<a href="${ctx}/tutorials.jspx"><img
								src="${ctx}/resources/help-big.png" alt="tutorials" /> </a>
						</div>
					</div>
					<div class="doc-min">
						<h4>
							<a href="${ctx}/tutorials.jspx">Tutorials</a>
						</h4>
						<p>This provides tutorials about how to use TPE platform.</p>
					</div>
				</div>

				<div class="tpe_sec">
					<div class="tpe_sec_img">
						<div class="img_box">
							<a href="${ctx}/json/queryParams.jspx"><img
								src="${ctx}/resources/xls-big.png" alt="query-results" /> </a>
						</div>
					</div>
					<div class="doc-min">
						<h4>
							<a href="${ctx}/json/queryParams.jspx">Query and Download
								data</a>
						</h4>
						<p>The system provides an option for querying and downloading
							soil and climate data.</p>
					</div>
				</div>

			</div>
			<div class="pane_right">
				<jsp:include page="right_pane.jsp" />
			</div>
		</div>
	</div>
</body>