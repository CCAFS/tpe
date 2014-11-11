<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Error</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="BookStore">
<meta name="author" content="asic">

 
<style>
body {
	padding-top: 60px;
	/* 60px to make the container go all the way to the bottom of the topbar */
}
</style>  
</head>

<body>
<body>

	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a> <a class="brand" href="#">An error occurred</a> <input
					type="button" class="btn btn-info" value="Back to Welcome Page"
					onclick="goHome()" />
			</div>
		</div>
	</div>

	<div class="container">

		<h2></h2>
		<s:actionerror />
		<s:fielderror />

		<%
	    out.println(((java.lang.Throwable) request.getAttribute("exception")).getMessage());
	    %>

	</div>
	<!-- /container -->
 

	<script type="text/javascript">
		
		var urlBaseWelcome = '<s:url value="/" />';
		
		function goHome() {
			document.location = urlBaseWelcome;
		}
		
	</script>

</body>
</html>