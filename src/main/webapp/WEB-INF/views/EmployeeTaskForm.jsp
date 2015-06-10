<%@ include file="./IncludeLib.jsp"%>
<!doctype html>
<html lang="us">
<head>
<meta charset="utf-8">
<%@ include file="./IncludeHeaderItems.jsp"%>
<script>
	$(function() {
		$("#assignProject").button();
		$("#cancelAssignProject").button();
		$("select[name = category]").selectmenu();
		$("select[name = status]").selectmenu();
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
<title>ETMS - Project Assign Page</title>
</head>
<body>
	<a href="${context}">Home</a>
	<br />
	<h1>
		<fmt:message key="employeeTaskDataForm.title" />
	</h1>
	<c:if test="${errorMessage != null}">
		<%@ include file="./ErrorMessage.jsp"%>
	</c:if>
	<br />
	<form:form method='post' action='./assignProject'
		commandName="employeeTask">
		<table>
			<tr>
				<td style="vertical-align: top"><fmt:message
						key="employeeTaskDataForm.employee" /></td>
				<td><form:select path="employeeId" size="10">
						<c:forEach items="${employeeList}" var="curEmployee">
							<form:option value="${curEmployee.getEmployeeId() }">
							${curEmployee.getEmployeeId()}
								- ${curEmployee.getFirstName()} ${curEmployee.getLastName()}
							</form:option>
						</c:forEach>
					</form:select> <form:errors path="employeeId" cssClass="error" /></td>
			</tr>
			<tr>
				<td><fmt:message key="employeeTaskDataForm.project" /></td>
				<td><form:select path="projectId">
						<c:forEach items="${projectList}" var="curProject">
							<form:option value="${curProject.getProjectId() }">
							${ curProject.getName() }
							</form:option>
						</c:forEach>
					</form:select> <form:errors path="projectId" cssClass="error" /></td>
			</tr>
			<tr>
				<td style="vertical-align: top"><fmt:message
						key="employeeTaskDataForm.description" /></td>
				<td><form:input path="description" /> <form:errors
						path="description" cssClass="error" /></td>
			</tr>
			<tr>
				<td><fmt:message key="employeeTaskDataForm.startDate" /></td>
				<td><form:input path="startDate" /> <form:errors
						path="startDate" cssClass="error" /></td>
			</tr>
			<tr>
				<td><fmt:message key="employeeTaskDataForm.dueDate" /></td>
				<td><form:input path="dueDate" /> <form:errors path="dueDate"
						cssClass="error" /></td>
			</tr>
			<tr>
				<td><fmt:message key="employeeTaskDataForm.actualStartDate" /></td>
				<td><form:input path="actualStartDate" /> <form:errors
						path="actualStartDate" cssClass="error" /></td>
			</tr>
			<tr>
				<td><fmt:message key="employeeTaskDataForm.actualEndDate" /></td>
				<td><form:input path="actualEndDate" /> <form:errors
						path="actualEndDate" cssClass="error" /></td>
			</tr>
			<tr>
				<td><fmt:message key="employeeTaskDataForm.status" /></td>
				<td><form:select path="status">
						<form:options items="${statusList}" />
					</form:select> <form:errors path="status" cssClass="error" /></td>
			</tr>
			<tr>
				<td><fmt:message
						key="employeeTaskDataForm.reasonOfMissingDueDate" /></td>
				<td><form:textarea path="reasonOfMissingDueDate" /></td>
			</tr>
			<tr>
				<td><fmt:message key="employeeTaskDataForm.category" /></td>
				<td><form:select path="category">
						<form:options items="${categoryList}" />
					</form:select> <form:errors path="category" cssClass="error" /></td>
			</tr>
			<tr>
				<td>
					<button type="submit" id="assignProject">
						<fmt:message key="employeeTaskDataForm.assignBtn" />
					</button>
				</td>
				<td>
					<button type="button" id="cancelAssignProject"
						onclick="javascript:cancelAction('${context}');">
						<fmt:message key="employeeTaskDataForm.cancelBtn" />
					</button>
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>