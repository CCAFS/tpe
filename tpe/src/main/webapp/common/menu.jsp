<%@ include file="/common/taglibs.jsp"%>

<div id="main_header">
	<div id="header_content">
		<div id="head_background">
			<a href="http://www.ccafs-tpe.org"><img src="img/header.png" alt="TPE" />
			</a>
		</div>
		<div id="tpe_header">
			<div id="tpe_logo">
				<a href="index.jspx"> <img src="img/tpe_header.png" /></a>
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
		
		<div id="ccafs_logo">
		<a href="http://ccafs.cgiar.org/"> <img src="img/ccafs_logo.png" /></a>
		</div>
	</div>
</div>