<%@ include file="/common/taglibs.jsp"%>

<head>
<script type="text/javascript">
	$(document).ready(function() {
		$("#menu_main ul#menu li#liwork").hover(function() {
			$('#where-work-m').show();
		}, function() {
			$('#where-work-m').hide();
		});

	});
</script>
</head>
<div id="main_header">
	<div id="top_header">
		<%-- 	<c:choose>
			<c:when test="${pageContext.request.userPrincipal.name != null}">
				<security:authorize access="hasRole('ROLE_ADMIN')">
					<!-- For login user -->
					<c:url value="/j_spring_security_logout" var="logoutUrl" />
					<form action="${logoutUrl}" method="post" id="logoutForm">
						<h2 class="nav-secondary">
							<a href="<s:url namespace="/admin" action="users" />">Manage
								Users</a> | ${pageContext.request.userPrincipal.name} | <a
								href="javascript:formSubmit()"> Logout</a> | <a
								href="<s:url namespace="/" action="contactUs" />">Contact Us</a>
						</h2>
					</form>
					<script>
						function formSubmit() {
							document.getElementById("logoutForm").submit();
						}
					</script>
				</security:authorize>
			</c:when>
			<c:otherwise>
				<h2 class="nav-secondary">
					<a href="<c:url value="/login.jspx"/>"> Login</a> | <a
						href="<s:url namespace="/" action="contactUs" />">Contact Us</a>
				</h2>
			</c:otherwise>
		</c:choose> --%>

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
			<li><a href="<c:url value='/' />">Home</a></li>
			<li><a href="<s:url namespace="/" action="toolIndex" />">Visualization</a></li>
			<li id="liwork"><a id="ourwork"
				href="<s:url namespace="/casestudy" action="index" />">Where we
					work</a> <!-- Wherewe work sub menu -->
				<div id="menu-sub">
					<div style="float: left;">
						<h5 style="clear: right; width: 150px; color: #fff;">Regions</h5>
					</div>
					<div id="where-work-m">
						<div id="where-w" style="clear: right;">
							<table style="width: 100%;">
								<colgroup>
									<col width="33%" />
									<col width="33%" />
									<col />
								</colgroup>
								<tr>
									<td><div class="col-left colombia">
											<a href="${ctx}/casestudy/colombian.jspx"><img
												src="${ctx}/img/work-colombia.png" alt="Colombia" /> </a>
										</div>
										<div class="col-right">
											<a href="${ctx}/casestudy/colombian.jspx">Colombia <br />Lowland
												Rice
											</a>
										</div></td>
									<td>
										<div class="col-left brazil">
											<a href="${ctx}/casestudy/brazilian.jspx"><img
												src="${ctx}/img/work-brazil.png" alt="Brazil" /> </a>
										</div>
										<div class="col-right">
											<a href="${ctx}/casestudy/brazilian.jspx">Brazil <br />Upland
												Rice
											</a>
										</div>
									</td>
									<td><div class="col-left lamerica">
											<a href="${ctx}/casestudy/lamerica.jspx"><img
												src="${ctx}/img/work-lamerica.png" alt="Latin-America" /> </a>
										</div>
										<div class="col-right">
											<a href="${ctx}/casestudy/lamerica.jspx">Latin America <br />Rice
											</a>
										</div></td>
								</tr>
							</table>
						</div>
					</div>
				</div></li>
			<li><a href="<s:url namespace="/" action="documentation" />">Documentation</a></li>
			<li><a href="<s:url namespace="/" action="resources" />">Resources</a></li>
			<%-- <li><a href="<s:url namespace="/" action="contactUs" />">Contact Us</a></li> --%>
			<li><a href="<s:url namespace="/json" action="queryParams" />">Query
					Data</a></li>
		</ul>
	</div>
	<!-- </div> -->
	<!-- 	</div> -->
</div>
