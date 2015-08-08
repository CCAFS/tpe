<%@ include file="/common/taglibs.jsp"%>
<html lang="en">
<head>
<title>Error</title>
</head>
<body>
	<div id="contact_main">
		<h4 class="navigation">An error has occured</h4>
		<div>
		<s:actionerror />
		<s:fielderror />
		<%
	    out.println(((java.lang.Throwable) request.getAttribute("exception")).getMessage());
	    %>
		</div>
	</div>
</body>
</html>