<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ include file="/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>

 <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js" type="text/javascript"></script>
<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/south-street/jquery-ui.css" /> 


<title><decorator:title default="Untitled page" /> | <fmt:message
		key="webapp.name" /></title>
<%@ include file="/common/meta.jsp"%>
<decorator:head />

</head>
<body
	<decorator:getProperty property="body.id" writeEntireProperty="true"/>
	<decorator:getProperty property="body.class" writeEntireProperty="true"/>>

	<div style="" class="noprint">
		<jsp:include page="/common/menu.jsp" />
	</div>
	<!--Main Content Area-->
	<div class="main_content">
		<div class="content">
			<decorator:body />
		</div>
		<div class="clear_footer"></div>
	</div>

	<jsp:include page="/common/footer.jsp" />

	<%-- <jsp:include page="/common/footer.jsp" /> --%>
	<%-- <script type="text/javascript"
		src="<s:url value="/script/gears/gears_init.js" />"></script>
	<s:action name="google/analytics" namespace="/" executeResult="true"
		ignoreContextParams="true" /> --%>
</body>
</html>
