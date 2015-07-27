<%@ page session="false"%>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
<title>Login</title>
<script src="https://code.google.com/apis/gears/gears_init.js"
	type="text/javascript"></script>
</head>
<body>
	<div style="margin: auto; width: 450px; padding-top: 5%;">
		<c:url value="/login.jspx" var="loginUrl" />
		<form id="loginForm" action="${ctx}/login" method="post">
			<table class="inputform">
				<%-- 	<c:if test="${not empty error}">
					<span class="actionError"> Your login attempt was not
						successful, try again. <br /> Invalid username or password. <br />
						<br /> Reason: <c:out
							value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />.
					</span>
				</c:if> --%>
				<c:if test="${not empty error}">
					<span class="actionError"> Your login attempt was not
						successful, try again.<br />
					</span>
					<div id="errorMessages">
						<span class="errorMessage">Invalid user name or password!</span>
					</div>
					<br />
				</c:if>
				<c:if test="${not empty logout}">
					<p>You have been logged out.</p>
				</c:if>

				<colgroup>
					<col width="30%" />
					<col width="70%" />
				</colgroup>
				<tbody>
					<tr>
						<td>Username:</td>
						<td><s:textfield name="username" label="User" id="username" /></td>
					</tr>
					<tr>
						<td>Password:</td>
						<td><s:password name="password" label="Password"
								id="password" /></td>
					</tr>
					<%--
		<tr>
			<td />
			<td><s:checkbox name="_spring_security_remember_me" /> Remember me on this computer.</td>
		</tr>
--%>
					<tr>
						<td />
						<td><button type="submit" class="btn">Log in</button></td>
					</tr>
				</tbody>
			</table>
			<%-- <input type="hidden" 
                     name="${_csrf.parameterName}" value="${_csrf.token}" />  --%>
		</form>
		<br />
		<p>
			Contact <a href="mailto:software@ccafs-tpe.org"
				title="CCAFS Software Team">software@ccafs-tpe.org</a> for help.
		</p>
	</div>
	<script type="text/javascript">
		$('#username').focus();
	</script>
</body>
</html>