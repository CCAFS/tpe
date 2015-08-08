<%@ include file="/common/taglibs.jsp"%>
<html lang="en">
<s:head />
<head>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no">
<title>Users</title>
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
<script>
	$(document).ready(function() {

	});
</script>
</head>

<body>
	<div id="user-main">

		<s:actionmessage />
		<s:actionerror />

		<s:if test="hasActionErrors()">
			<div class="errors">
				<s:actionerror />
			</div>
		</s:if>

		<s:if test="hasActionMessages()">
			<div class="errors">
				<s:actionmessage />
			</div>
		</s:if>

		<!-- <h1>TPE Platform Administration</h1> -->
		<div id="users">
			<h4 style="margin-bottom: 5px; color: #000000;">Platform
				Administrators</h4>
			<a href="addAdmin.jspx">Add Admin</a>
			<s:iterator value="users">
				<div class="user">
					<h4>
						<s:text name="user.username"/>: <a
							href="${ctx}/admin/editAdmin.jspx?username=<s:property value='username' />"><s:property
								value="username" /></a>
					</h4>
					<p>
						<s:text name="user.name"/>:
						<s:property value="name" />
					</p>
					<p>
						<s:text name="user.email"/>:
						<s:property value="email" />
					</p>
					<p>
						<s:text name="user.enabled"/>:
						<s:property value="enabled" />
					</p>
					<h4><s:text name="user.roles"/>:</h4>
					<s:iterator value="roles">
						<div class="role">
							<s:property value="name" />
						</div>
					</s:iterator>
					<hr>
				</div>
			</s:iterator>
		</div>
	</div>
</body>
</html>
