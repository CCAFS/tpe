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
			<div id="tag-add">
				<h1>
					<s:property value='tagItem.name' />
					(
					<s:property value="tagItem.postCount" />
					)
					<button class="button-add" id="send"
						onclick="location.href='/tpe/admin/tag/add.jspx'">Add
						Post</button>
				</h1>
				<s:form action="tag/add.jspx" id="tagForm" name="tagForm"
					method="post" validate="true">
					<table style="width: 100%;">
						<colgroup>
							<col width="15%" />
							<col />
						</colgroup>
						<tr>
							<td><s:text name="tag.name" />:</td>
							<td><s:textfield name="name" id="name"
									value="%{tagItem.name}" /></td>
						</tr>
						<tr>
							<td><s:text name="tag.enabled" />:</td>
							<td><s:radio id="enabled" name="selectedStatus"
									list="#{'Yes':'Yes', 'No':'No'}" value="%{tagItem.status}" /></td>
						</tr>
					</table>


					<s:iterator value="tagItem.posts">
						<div class="tag-post">
							<h2>Posts</h2>
							<table style="width: 100%;">
								<colgroup>
									<col width="15%" />
									<col />
								</colgroup>
								<tr>
									<td><s:text name="post.title" />:</td>
									<td><s:textfield name="name" id="name" value="%{title}"
											size="200" /></td>
								</tr>
								<tr>
									<td><s:text name="post.url" />:</td>
									<td><s:textfield name="name" id="name" value="%{url}" /></td>
								</tr>
							</table>
						</div>
					</s:iterator>


				</s:form>
			</div>
		</div>
		<div class="pane_right">
			<jsp:include page="../tpe/right_pane.jsp" />
		</div>
	</div>
</body>
</html>
