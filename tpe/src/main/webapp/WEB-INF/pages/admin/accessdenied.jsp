<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
<title>Access denied!</title>
</head>

<body>
	<h1>Unauthorized Access !!</h1>
	<!-- <p>You do not have the right to perform the action!</p> -->

	<c:if test="${not empty error}">
		<div style="color: red">
			You do not have the right to perform the action!<br /> Caused :
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>

	<p class="message">Access denied!</p>
	<a href="javascript: window.back();">Go back to login page</a> or
	<a href="<c:url value="/" />">to TPE platform home page</a>

	<%-- <p>Return to <a href="javascript: window.back();">previous page</a> or <a href="<c:url value="/" />">to TPE platform home page</a></p> --%>
</body>
</html>