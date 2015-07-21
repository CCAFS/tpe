<%@ page session="false" %>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
<title>Login</title>
</head>
<body>
<div style="margin: 0px auto 0px auto; width: 450px; padding-top: 20%;">
<c:url value="${request.contextPath}/j_spring_security_check" var="loginUrl" />
<%-- <form action="${loginUrl}" method="post"> --%>
<form action="j_spring_security_check" method="post">

<table class="inputform">
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
			<td><s:password name="password" label="Password" id="password" /></td>
		</tr>
<%--
		<tr>
			<td />
			<td><s:checkbox name="_spring_security_remember_me" /> Remember me on this computer.</td>
		</tr>
--%>
		<tr>
			<td />
			<td><s:submit value="Login" /></td>
		</tr>
	</tbody>
</table>
 <input type="hidden" 
                     name="${_csrf.parameterName}" value="${_csrf.token}" />
                     
</form>

<p>Contact <a href="mailto:software@ccafs-tpe.org" title="CCAFS Software Team">software@ccafs-tpe.org</a> for help.</p>


<c:if test="${not empty param.login_error}">
	<div id="errorMessages"><span class="errorMessage">Invalid user name or password!</span></div>
</c:if></div>
<script type="text/javascript">
	$('username').focus();
</script>
</body>
</html>