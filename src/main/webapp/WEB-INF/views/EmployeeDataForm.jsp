<%@ include file="./IncludeLib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<td><fmt:message key="employeeDataForm.firstnameLabel" /></td>
			<td><form:input path="firstName" /> <form:errors
					path="firstName" cssClass="error" /></td>
		</tr>
		<tr>
			<td><fmt:message key="employeeDataForm.lastnameLabel" /></td>
			<td><form:input path="lastName" /> <form:errors path="lastName"
					cssClass="error" /></td>
		</tr>
		<tr>
			<td><fmt:message key="employeeDataForm.middlenameLabel" /></td>
			<td><form:input path="middleName" /> <form:errors
					path="middleName" cssClass="error" /></td>
		</tr>
		<tr>
			<td><fmt:message key="employeeDataForm.emailidLabel" /></td>
			<td><form:input path="email" /> <form:errors path="email"
					cssClass="error" /></td>
		</tr>
		<tr>
			<td><fmt:message key="employeeDataForm.birthDateLabel" /></td>
			<td><form:input path="birthDate" /> <form:errors
					path="birthDate" cssClass="error" /></td>
		</tr>
		<tr>
			<td><fmt:message key="employeeDataForm.joiningDateLabel" /></td>
			<td><form:input path="joiningDate" /> <form:errors
					path="joiningDate" cssClass="error" /></td>
		</tr>
		<tr>
			<td><fmt:message key="employeeDataForm.contactNoLabel" /></td>
			<td><form:input path="contactNo" /> <form:errors
					path="contactNo" cssClass="error" /></td>
		</tr>
		<tr>
			<td><fmt:message key="employeeDataForm.currentAddressLabel" />
			</td>
			<td><form:input path="currentAddress" /> <form:errors
					path="currentAddress" cssClass="error" /></td>
		</tr>
		<tr>
			<td><fmt:message key="employeeDataForm.departmentLabel" /></td>
			<td><form:select path="department">
					<form:options items="${departmentList}" />
				</form:select></td>
		</tr>
		<tr>
			<td><fmt:message key="employeeDataForm.designationLabel" /></td>
			<td><form:select path="designation">
					<form:options items="${designationList}" />
				</form:select></td>
		</tr>
	</table>
</body>
</html>