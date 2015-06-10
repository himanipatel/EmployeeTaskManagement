<%@ include file="./IncludeLib.jsp"%>
<!doctype html>
<html lang="us">
<head>
<meta charset="utf-8">
<%@ include file="./IncludeHeaderItems.jsp"%>
<script>
	$(function() {
		$("#cancelAddEmployee").button();
		$("#addEmployee").button();
		$("select[name = department]").selectmenu();
		$("select[name = designation]").selectmenu();
	});
	
	function cancelAction(url){
		document.location.href = url;
	}
</script>
<style>
body {
	font-size: 14px;
	margin: 10px;
}
</style>
<title>ETMS - Employee Add Page</title>
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
	<form:form method='post' action='./processNewEmployeeProfile'
		commandName="employee">
		<%@ include file="./EmployeeDataForm.jsp"%>
		<table>
			<tr>
				<td>
					<button type="submit" id="addEmployee">
						<fmt:message key="employeeDataForm.addBtn" />
					</button>
				</td>
				<td>
					<button type="button" id="cancelAddEmployee"
						onclick="javascript:cancelAction('${context}');">
						<fmt:message key="employeeDataForm.cancelBtn" />
					</button>
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>