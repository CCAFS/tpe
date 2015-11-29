<%@ include file="/common/taglibs.jsp"%>
<html lang="en">
<head>
<title>Tags</title>
</head>
<body>
	<div id="documentation">
		<div class="pane_left">
			<div id="posts">
				<h1 id="tag-title">
					Tag:
					<s:property value="tag" />
				</h1>
				<s:iterator value="posts">
					<div class="post">
						<h1>
							<a href="<s:property value='url' />"><s:property
									value="title" /></a>
						</h1>
						<p>
							<s:property value="title" />
						</p>
					</div>
				</s:iterator>
			</div>
		</div>
		<div class="pane_right">
			<jsp:include page="right_pane.jsp" />
		</div>
	</div>
</body>
</html>
