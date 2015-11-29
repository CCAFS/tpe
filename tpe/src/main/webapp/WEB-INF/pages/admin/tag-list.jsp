<%@ include file="/common/taglibs.jsp"%>
<html lang="en">
<head>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no">
<title>Tags</title>
<style>
</style>
<script>
	$(document).ready(function() {

	});
</script>
</head>

<body>
	<div id="documentation">
		<div class="pane_left">
			<s:iterator value="tags">
				<div class="tg">
					<h5>
						<a href="/tpe/admin/tag.jspx?tag=<s:property value='name' />"><s:property value="name" /> (<s:property value="postCount" />)</a>
					</h5>
					<p>
						<s:property value="weight" />
					</p>
				</div>
			</s:iterator>
		</div>
		<div class="pane_right">
			<jsp:include page="../tpe/right_pane.jsp" />
		</div>
	</div>
</body>
</html>
