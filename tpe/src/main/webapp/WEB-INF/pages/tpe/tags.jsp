<%@ include file="/common/taglibs.jsp"%>
<html lang="en">
<head>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no">
<title>Tags</title>
<style>
</style>
</head>

<body>
	<div id="tags">
		<s:iterator value="tags">
			<div class="tg">
				<h4>
					<a href="<s:property value='url' />"><s:property value="name" /></a>
				</h4>
				<ul>
					<s:iterator value="posts">
						<li><s:property value="title" /> <s:property value="url" /></li>
					</s:iterator>
				</ul>
			</div>
		</s:iterator>
	</div>
</body>
</html>
