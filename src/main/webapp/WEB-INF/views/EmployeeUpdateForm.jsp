<%@ include file="./IncludeLib.jsp"%>
<!doctype html>
<html lang="us">
<head>
<meta charset="utf-8">
<%@ include file="./IncludeHeaderItems.jsp"%>
<script>
	$(function() {
		$("#cancelUpdateEmployee").button();
		$("#updateEmployee").button();
		$("select[name = department]").selectmenu();
		$("select[name = designation]").selectmenu();
	});

	function cancelAction(url) {
		document.location.href = url;
	}
</script>
<style>
body {
	font-size: 14px;
	margin: 10px;
}
</style>
<title>ETMS - Employee Update Page</title>
</head>
<body>
	<a href="${context}">Home</a>
	<br />
	<h1>
		<fmt:message key="employeeDataForm.title" />
	</h1>
	<c:if test="${errorMessage != null}">
		<%@ include file="./ErrorMessage.jsp"%>
	</c:if>
	<br />
	<form:form method='post' action='./updateEmployeeProfile'
		commandName="employee">
		<table>
			<tr>
				<td><fmt:message key="employeeDataForm.employeeIdLabel" /></td>
				<td><form:input path="employeeId" readonly="true" /></td>
			</tr>
		</table>
		<%@ include file="./EmployeeDataForm.jsp"%>
		<table>
			<tr>
				<td><form:button type="submit" id="updateEmployee">
						<fmt:message key="employeeDataForm.updateBtn" />
					</form:button></td>
				<td><form:button type="button" id="cancelUpdateEmployee"
						onclick="javascript:cancelAction('${context}/newViewEmployeeForm');">
						<fmt:message key="employeeDataForm.cancelBtn" />
					</form:button></td>
			</tr>
		</table>
	</form:form>
</body>
</html>