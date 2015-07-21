<%@ include file="/common/taglibs.jsp"%>
<div id="main_header"> 
	<!-- <div id="header_content"> -->
	<!-- <div id="center-head"> -->
	<!-- <a href="http://www.ccafs-tpe.org">
				<div id="head_logo"></div>
			</a> -->
	<div id="top_header">
	<p>
      Hello <b><c:out value="${pageContext.request.remoteUser}"/></b>
    </p>
	 <c:url var="logoutUrl" value="/"/>
    <form class="form-inline" action="${logoutUrl}" method="post">
      <input type="submit" value="Log out" />
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
		<div id="platform_title">
			<h1 id=platform_name>
				<a href="https://ccafs.cgiar.org/">Target Population of
					Environments</a>
			</h1>
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
			<li><a href="<s:url namespace="/" action="contactUs" />">Contact
					Us</a></li>
		</ul>
	</div>
	<!-- </div> -->
	<!-- 	</div> -->
</div>
