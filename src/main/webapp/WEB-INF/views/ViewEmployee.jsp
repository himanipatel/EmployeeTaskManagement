<%@ include file="./IncludeLib.jsp"%>
<!doctype html>
<html lang="us">
<head>
<meta charset="utf-8">
<%@ include file="./IncludeHeaderItems.jsp"%>
<script>
	$(function() {
		$("#button").button();
	});
</script>
<style>
body {
	font-size: 14px;
	margin: 10px;
}

table.hovertable {
	font-family: verdana, arial, sans-serif;
	font-size: 11px;
	color: #333333;
	border-width: 1px;
	border-color: #999999;
	border-collapse: collapse;
}

table.hovertable th {
	background-color: #FAAC58;
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
}

table.hovertable tr {
	background-color: #CEF6EC;
}

table.hovertable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
}
</style>
<title>ETMS - Employee Search Page</title>
</head>
<body>
	<a href="${context}">Home</a>
	<br />
	<form:form method='post' action='./processViewEmployeeForm'>
		<table>
			<tr>
				<td>First Name:</td>
				<td><input type="text" name="firstName" /></td>
			</tr>
			<tr>
				<td>Last Name:</td>
				<td><input type="text" name="lastName" /></td>
			</tr>
		</table>
		<br />
		<button name="btnSearch" id="button" class="demoHeaders">Search</button>
	</form:form>
	<br />
	<c:if test="${errorMessage != null}">
		<%@ include file="./ErrorMessage.jsp"%>
	</c:if>
	<br />
	<c:if test="${employeeList != null}">
		<table class="hovertable">
			<thead>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Middle Name</th>
					<th>Email</th>
					<th>Birth Date</th>
					<th>Joining Date</th>
					<th>Contact No</th>
					<th>Current Address</th>
					<th>Department</th>
					<th>Designation</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<c:forEach items="${employeeList}" var="curEmployee">
				<tbody>
					<tr onmouseover="this.style.backgroundColor='#A9D0F5';"
						onmouseout="this.style.backgroundColor='#CEF6EC';">
						<td>${curEmployee.getFirstName()}</td>
						<td>${curEmployee.getLastName()}</td>
						<td>${curEmployee.getMiddleName()}</td>
						<td>${curEmployee.getEmail()}</td>
						<td>${curEmployee.getBirthDate()}</td>
						<td>${curEmployee.getJoiningDate()}</td>
						<td>${curEmployee.getContactNo()}</td>
						<td>${curEmployee.getCurrentAddress()}</td>
						<td>${curEmployee.getDepartment()}</td>
						<td>${curEmployee.getDesignation()}</td>
						<td><a
							href="./newEmployeeUpdateForm?employeeId=${curEmployee.getEmployeeId()}">Update</a></td>
						<td><a
							href="./newEmployeeDeleteForm?employeeId=${curEmployee.getEmployeeId()}">Remove</a></td>
					</tr>
				</tbody>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>