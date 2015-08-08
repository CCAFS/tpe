<%@ include file="/common/taglibs.jsp"%>
<html lang="en">
<s:head />
<head>
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
</head>

<body>
	<div id="user-main">
		<div id="user-add">
			<h4 style="margin-bottom: 5px; color: #000000;">Add New Admin</h4>
			<s:form action="addUser.jspx" id="adminForm" name="adminForm"
				method="post" validate="true">
				<table>
					<tr>
						<td><s:text name="user.username" />:</td>
						<td><s:textfield name="username" id="username" /></td>
					</tr>
					<tr>
						<td><s:text name="user.password" />:</td>
						<td><s:password name="password" id="password" /></td>
					</tr>
					<tr>
						<td><s:text name="user.firstname" />:</td>
						<td><s:textfield name="firstName" id="firstName" /></td>
					</tr>
					<tr>
						<td><s:text name="user.lastname" />:</td>
						<td><s:textfield name="lastName" id="lastName" /></td>
					</tr>
					<tr>
						<td><s:text name="user.email" />:</td>
						<td><s:textfield name="email" id="email" /></td>
					</tr>
					<tr>
						<td><s:text name="user.enabled" />:</td>
						<td><s:radio id="enabled" name="selectedStatus"
								list="#{'Yes':'Yes', 'No':'No'}" value="defaultStatus" /></td>
					</tr>
					<tr>
						<td><s:text name="user.roles" />:</td>
						<td><s:select id="roles" name="selectedRoles" multiple="true"
								list="#{'ROLE_ADMIN':'ROLE_ADMIN', 'ROLE_USER':'ROLE_USER'}"></s:select></td>
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
</body>
</html>
