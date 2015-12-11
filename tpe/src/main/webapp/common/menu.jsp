<%@ include file="/common/taglibs.jsp"%>

<head>
<script type="text/javascript">
	$(document).ready(function() {
		$("#menu_main ul#menu li#li-work").hover(function() {
			$('#sub-regions').show();
		}, function() {
			$('#sub-regions').hide();
		});

		$("#menu_main ul#menu li#li-visual").hover(function() {
			$('#sub-visualize').show();
		}, function() {
			$('#sub-visualize').hide();
		});

		$("#menu_main ul#menu li#li-resources").hover(function() {
			$('#sub-resources').show();
		}, function() {
			$('#sub-resources').hide();
		});

	});
</script>
</head>
<div id="main_header">
	<div id="top_header">
		<div id="platform_title">
			<!-- <h1 id=platform_name> -->
			<a href="https://ccafs.cgiar.org/"><img
				src="${ctx}/img/platform-logo.png" height="106" width="550" /></a>
			<!-- </h1> -->
			<h2 class="menu-secondary">
				<a href="<s:url namespace="/" action="aboutUs" />"> About Us</a> <a
					href="<s:url namespace="/" action="contactUs" />">Contact Us</a>
			</h2>
		</div>
		<div id="ccafs_logo">
			<a href="https://ccafs.cgiar.org/"></a>
		</div>
	</div>
	<div id="menu_main">
		<ul id="menu">
			<li><a href="<c:url value='/' />">Home
					<h6>Welcome</h6>
			</a></li>
			<li id="li-visual"><a id="visual"
				href="<s:url namespace="/" action="toolIndex" />">Visualization
					<h6>Maps & Graphics</h6>
			</a> <%-- <div id="menu-sub-visualize">
					<div id="sub-visualize">
						<div id="sub-visualize-items" style="clear: right;">
							<table style="width: 100%;">
								<colgroup>
									<col width="25%" />
									<col width="25%" />
									<col width="25%" />
									<col />
								</colgroup>
								<tr>
									<td><div class="col-left">
											<a href="${ctx}/visualization.jspx?param=climate"><img
												src="${ctx}/img/vis-climate.png" alt="Climate" /> </a>
										</div>
										<div class="col-right">
											<a href="${ctx}/visualization.jspx?param=climate">Climate</a>
										</div></td>
									<td>
										<div class="col-left">
											<a href="${ctx}/visualization.jspx?param=soil"><img
												src="${ctx}/img/vis-soil.png" alt="Soil" /> </a>
										</div>
										<div class="col-right">
											<a href="${ctx}/visualization.jspx?param=soil">Soil</a>
										</div>
									</td>
									<td><div class="col-left">
											<a href="${ctx}/visualization.jspx?param=stability"><img
												src="${ctx}/img/vis-stability.png" alt="Stability" /> </a>
										</div>
										<div class="col-right">
											<a href="${ctx}/visualization.jspx?param=stability">Stability</a>
										</div></td>
									<td><div class="col-left">
											<a href="${ctx}/visualization.jspx?param=tpe"><img
												src="${ctx}/img/vis-tpe.png" alt="TPE" /> </a>
										</div>
										<div class="col-right">
											<a href="${ctx}/visualization.jspx?param=tpe">TPE</a>
										</div></td>
								</tr>
							</table>
						</div>
					</div>
				</div> --%></li>
			<li id="li-work"><a id="ourwork"
				href="<s:url namespace="/casestudy" action="index" />">Regions
					<h6>Where we work</h6>
			</a> <!-- Wherewe work sub menu --> <%-- <div id="menu-sub-regions">
					<div id="sub-regions">
						<div id="sub-regions-items" style="clear: right;">
							<table style="width: 100%;">
								<colgroup>
									<col width="33%" />
									<col width="33%" />
									<col />
								</colgroup>
								<tr>
									<td><div class="col-left colombia">
											<a href="${ctx}/casestudy/colombian.jspx"><img
												src="${ctx}/img/region-colombia.png" alt="Colombia" /> </a>
										</div>
										<div class="col-right">
											<a href="${ctx}/casestudy/colombian.jspx">Colombia <br />Lowland
												Rice
											</a>
										</div></td>
									<td>
										<div class="col-left brazil">
											<a href="${ctx}/casestudy/brazilian.jspx"><img
												src="${ctx}/img/region-brazil.png" alt="Brazil" /> </a>
										</div>
										<div class="col-right">
											<a href="${ctx}/casestudy/brazilian.jspx">Brazil <br />Upland
												Rice
											</a>
										</div>
									</td>
									<td><div class="col-left lamerica">
											<a href="${ctx}/casestudy/lamerica.jspx"><img
												src="${ctx}/img/region-lamerica.png" alt="Latin-America" />
											</a>
										</div>
										<div class="col-right">
											<a href="${ctx}/casestudy/lamerica.jspx">Latin America <br />Rice
											</a>
										</div></td>
								</tr>
							</table>
						</div>
					</div>
				</div> --%></li>
			<%-- <li><a href="<s:url namespace="/" action="documentation" />">Documentation
					<h6>Methodology</h6>
			</a></li> --%>
			<li id="li-resources"><a
				href="<s:url namespace="/" action="resources" />">Resources
					<h6>Tutorials & Dowloads</h6>
			</a>
				<div id="menu-sub-resources">
					<div id="sub-resources">
						<div id="sub-resources-items" style="clear: right;">
							<table style="width: 100%;">
								<colgroup>
									<col width="50%" />
									<col />
								</colgroup>
								<tr>
									<td><div class="col-left">

											<a href="<s:url namespace="/" action="documentation" />"><img
												src="${ctx}/resources/document.png" alt="Documentation" /> </a>
										</div>
										<div class="col-right">
											<a href="<s:url namespace="/" action="documentation" />">Documentation
											</a>
										</div></td>

									<td><div class="col-left">
											<a href="<s:url namespace="/" action="resources" />"><img
												src="${ctx}/resources/help.png" alt="Tutorials" /> </a>
										</div>
										<div class="col-right">
											<a href="${ctx}/tutorials.jspx">Tutorials </a>
										</div></td>
									<td><div class="col-left">
											<a href="<s:url namespace="/json" action="queryParams" />"><img
												src="${ctx}/resources/xls.png" alt="Downloads" /> </a>
										</div>
										<div class="col-right">
											<a href="<s:url namespace="/json" action="queryParams" />">Downloads</a>
										</div></td>
								</tr>
							</table>
						</div>
					</div>
				</div></li>
			<%-- <li><a href="<s:url namespace="/" action="contactUs" />">Contact Us</a></li> --%>
			<%-- <li><a href="<s:url namespace="/json" action="queryParams" />">Query Data</a></li> --%>
		</ul>
	</div>
	<!-- </div> -->
	<!-- 	</div> -->
</div>
