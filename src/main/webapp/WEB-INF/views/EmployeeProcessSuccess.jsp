<%@ include file="./IncludeLib.jsp"%>
<html>
<head>
<title>Employee Profile Process Success</title>
</head>
<body>

	<a href="${context}">Home</a>
	<br />

	<c:if test="${processType eq 'employeeAdd'}">
		
			New Employee with employee Id <b> ${employee.employeeId}</b> has been
			added.
		
	</c:if>
	<c:if test="${processType eq 'employeeUpdate'}">
		
			Employee with employee Id <b> ${employee.employeeId}</b> has been
			updated.
		
	</c:if>
	<c:if test="${processType eq 'employeeDelete'}">
		
			Employee with employee Id <b> ${employee.employeeId}</b> has been
			deleted.
		
	</c:if>
</body>
</html>