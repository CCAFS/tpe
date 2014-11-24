<%@ include file="/common/taglibs.jsp"%>

<div class="main_header">
	<div class="header_content">
		<div class="logo">
			<a href="http://www.ccafs-tpe.org"><img src="img/logo.png" alt="TPE" />
			</a>
		</div>
		<div class="menu_container">
			<div id="tpe_title">
				<!-- <h1>Target Population Environments Identification Platform</h1> -->
				<a href="index.jspx"> <img src="img/top.png" width="680" height="45" /></a>
			</div>
			<div id="tpe_menu">
				<ul id="menu">
					<li><a href="<c:url value='/' />">Home</a></li>
					<li><a href="<s:url namespace="/" action="toolIndex" />">TPE Tool</a></li>
					<li><a href="<s:url namespace="/" action="documentation" />">Documentation</a></li>
					<li><a href="<s:url namespace="/" action="contactUs" />">Contact Us</a></li>
					<li><a href="">CCAFS Sites</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>