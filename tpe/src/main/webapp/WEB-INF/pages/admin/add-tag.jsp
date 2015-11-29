<%@ include file="/common/taglibs.jsp"%>
<html lang="en">
<s:head />
<head>
<title>Tag</title>
<style type="text/css">
errors {
	background-color: #FFCCCC;
	border: 1px solid #CC0000;
	width: 400px;
	margin-bottom: 8px;
}

.errors li {
	list-style: none;
}
</style>
</head>

<body>
	<div id="documentation">
		<div class="pane_left">
			<div id="tag-add">
				<h4 style="margin-bottom: 5px; color: #000000;">Add New Tag</h4>
				<s:form action="tag/add.jspx" id="tagForm" name="tagForm"
					method="post" validate="true">
					<table style="width: 100%;">
						<colgroup>
							<col width="15%" />
							<col />
						</colgroup>
						<tr>
							<td><s:text name="tag.name" />:</td>
							<td><s:textfield name="name" id="name" /></td>
						</tr>
						<tr>
							<td><s:text name="tag.enabled" />:</td>
							<td><s:radio id="enabled" name="selectedStatus"
									list="#{'Yes':'Yes', 'No':'No'}" value="defaultStatus" /></td>
						</tr>
						<tr>
							<td><s:text name="tag.posts" />:</td>
							<td><s:textarea name="posts" cols="1" id="posts" rows="5"
									wrap="true" /></td>
						</tr>
						<tr>
							<td></td>
							<td style="text-align: right;"><s:submit
									cssClass="button-add" id="add" value="Add" /></td>
						</tr>
					</table>
				</s:form>
			</div>
		</div>
		<div class="pane_right">
			<jsp:include page="../tpe/right_pane.jsp" />
		</div>
	</div>
</body>
</html>
