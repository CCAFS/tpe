<%@ include file="/common/taglibs.jsp"%>
<head>

<script>
	jQuery(document).ready(function($) {
		/* $(document).ready(function() { */

		//Footer Items
		$('.colombia').hover(function() {
			$("img", this).attr("src", "${ctx}/img/region-colombia-hover.png");
		}, function() {
			$("img", this).attr("src", "${ctx}/img/region-colombia.png");
		});

		$('.brazil').hover(function() {
			$("img", this).attr("src", "${ctx}/img/region-brazil-hover.png");
		}, function() {
			$("img", this).attr("src", "${ctx}/img/region-brazil.png");
		});

		$('.lamerica').hover(function() {
			$("img", this).attr("src", "${ctx}/img/region-lamerica-hover.png");
		}, function() {
			$("img", this).attr("src", "${ctx}/img/region-lamerica.png");
		});

	});
</script>
</head>

<!--Footer section-->
<div class="footer">
	<div class="footer_contents">
		<table>
			<tr>
				<td><div class="collab">
						<a href="http://ciat.cgiar.org/" target="_blank"><img
							src="${ctx}/img/logo_ciat.png" alt="CIAT" /> </a>
					</div></td>
				<td class="left-border">
					<div class="collab">
						<a href="http://ccafs.cgiar.org/" target="_blank"><img
							src="${ctx}/img/logo.png" alt="CCAFS" /> </a>
					</div>
				</td>
				<td class="left-border"><div class="collab">
						<a href="https://www.embrapa.br/" target="_blank"><img
							src="${ctx}/img/logo_embrapa.png" alt="EMBRAPA" /> </a>
					</div></td>
			</tr>

			<tr>
				<td><c:choose>
						<c:when test="${pageContext.request.userPrincipal.name != null}">
							<security:authorize access="hasRole('ROLE_ADMIN')">
								<!-- For login user -->
								<c:url value="/j_spring_security_logout" var="logoutUrl" />
								<form action="${logoutUrl}" method="post" id="logoutForm">
									<h2 class="nav-secondary">
										<a href="<s:url namespace="/admin" action="users" />">Manage
											Users</a> | ${pageContext.request.userPrincipal.name} | <a
											href="javascript:formSubmit()"> Logout</a>
									</h2>
								</form>
								<script>
									function formSubmit() {
										document.getElementById("logoutForm")
												.submit();
									}
								</script>
							</security:authorize>
						</c:when>
						<c:otherwise>
							<h2 class="nav-secondary">
								<a href="<c:url value="/login.jspx"/>">Admin Login</a>
							</h2>
						</c:otherwise>
					</c:choose></td>
				<td><div id="tpe_copyright">
						<a href="http://www.ccafs-tpe.org" target="_blank">CCAFS
							Target Population Environments Platform</a> <br /> Copyright &#169;
						2014
					</div></td>
				<td><div id="follow_us">
						<a href="https://www.twitter.com/ccafstpe"><img
							src="${ctx}/img/twitter.png" alt="twitter" /></a>&nbsp<a
							href="https://www.facebook.com/ccafstpe"><img
							src="${ctx}/img/facebook.png" alt="facebook" /></a>&nbsp<a
							href="https://www.youtube.com/ccafstpe"><img
							src="${ctx}/img/youtube.png" alt="in" /></a>&nbsp<a
							href="www.flickr.com/tpe_platform"><img
							src="${ctx}/img/flickr.png" alt="in" /></a>&nbsp<a
							href="https://www.rssfeed.com/ccafs_tpe"><img
							src="${ctx}/img/rss.png" alt="email" /></a>
					</div></td>
			</tr>
		</table>

	</div>
</div>