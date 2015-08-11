<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<title>TPE Documentation</title>
<script type="text/javascript">
	$(document).ready(function() {

		/* $("a.doc-index").click(function() {
			$('.pane_left').load("${ctx}/casestudy/index.jspx");
			return false
		});
		$(".brazilian a").click(function() {
			$('.pane_left').load("${ctx}/casestudy/brazilian.jspx");
			if ($('.navigation').has('.brazilian').length) {
				// It has that class. Do nothing
			} else {
				//Append it
				$('h4.navigation a.doc-index').append('<a class="brazilian" href="#">Brazilian</a>');
			}
			return false
		});
		$(".colombian a").click(function() {
			$('.pane_left').load("${ctx}/casestudy/colombian.jspx");
			return false
		});
		$(".lamerica a").click(function() {
			$('.pane_left').load("${ctx}/casestudy/lamerica.jspx");
			return false
		}); */
	});
</script>
</head>
<body>
	<div id="documentation">
		<h4 class="navigation">
			<a class="doc-index" href="#">Case Studies</a> / Where we work
		</h4>
		<div>
			<div class="pane_left">
				<!--Documentation Area-->
				<div class="doc-list brazilian">
					<div class="img">
						<a href="${ctx}/casestudy/brazilian.jspx"><img
							src="${ctx}/img/tpe_map.jpg" alt="image" /> </a>
					</div>
					<div class="doc-min brazilian">
						<h4>
							<a href="${ctx}/casestudy/brazilian.jspx">Brazilian Case
								Study</a>
						</h4>
						<p>Brazilian Case Study</p>
					</div>
				</div>
				<div class="doc-list colombian">
					<div class="img">
						<a href="${ctx}/casestudy/colombian.jspx"><img
							src="${ctx}/img/tpe_map.jpg" alt="image" /> </a>
					</div>
					<div class="doc-min colombian">
						<h4>
							<a href="${ctx}/casestudy/colombian.jspx">Colombian Case
								Study</a>
						</h4>
						<p>Colombian Case Study</p>
					</div>
				</div>
				<div class="doc-list lamerica">
					<div class="img">
						<a href="${ctx}/casestudy/lamerica.jspx"><img
							src="${ctx}/img/tpe_map.jpg" alt="image" /> </a>
					</div>
					<div class="doc-min lamerica">
						<h4>
							<a href="${ctx}/casestudy/lamerica.jspx">Latin America Case
								Study</a>
						</h4>
						<p>Latin America Case Study</p>
					</div>
				</div>
			</div>
			<div class="pane_right">
				<jsp:include page="right_pane.jsp" />
			</div>
		</div>
	</div>
</body>
</html>