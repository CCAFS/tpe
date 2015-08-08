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
	<div id="tags">
		<s:iterator value="tags">
			<div class="tg">
				<h1>
					<a href="<s:property value='url' />"><s:property value="name" /></a>
				</h1>
				<ul>
					<s:iterator value="posts">
						<li><s:property value="title" />
							<s:property value="url" /></li>
					</s:iterator>
				</ul>
			</div>
		</s:iterator>
	</div>
</body>
</html>
