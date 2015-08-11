<%@ include file="/common/taglibs.jsp"%>
<div id="main_header">
	<!-- <div id="header_content"> -->
	<!-- <div id="center-head"> -->
	<!-- <a href="http://www.ccafs-tpe.org">
				<div id="head_logo"></div>
			</a> -->
	<div id="top_header">
		<%-- <security:authorize access="isAnonymous()" >
				<li>
					<c:url value="/login.jspx" var="loginUrl"/>
					<a href="${loginUrl}">login</a>
				</li>
			</security:authorize>
			<security:authorize access="isAuthenticated()" >
				<li>
				<c:url value="/login.jspx" var="logoutUrl"/>
					<a href="${logoutUrl}">logout</a>
				</li>
			</security:authorize> --%>

		<c:choose>
			<c:when test="${pageContext.request.userPrincipal.name != null}">
				<security:authorize access="hasRole('ROLE_ADMIN')">
					<!-- For login user -->
					<c:url value="/j_spring_security_logout" var="logoutUrl" />
					<form action="${logoutUrl}" method="post" id="logoutForm">
						<%-- <input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" /> --%>
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
		</c:choose>

		<div id="platform_title">
			<!-- <h1 id=platform_name> -->
			<a href="https://ccafs.cgiar.org/"><img
				src="${ctx}/img/platform-logo.png" height="106" width="550" /></a>
			<!-- </h1> -->
		</div>
		<div id="ccafs_logo">
			<a href="https://ccafs.cgiar.org/"></a>
		</div>
	</div>
	<div id="menu_main">
		<ul id="menu">
			<li><a href="<c:url value='/' />">Home</a></li>
			<li><a href="<s:url namespace="/" action="toolIndex" />">Visualization</a></li>
			<li><a href="<s:url namespace="/" action="documentation" />">Documentation</a></li>
			<li><a href="<s:url namespace="/" action="resources" />">Resources</a></li>
		    <li><a href="<s:url namespace="/" action="contactUs" />">Contact Us</a></li> 
		</ul>
	</div>
	<!-- </div> -->
	<!-- 	</div> -->
</div>
