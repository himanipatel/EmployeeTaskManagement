<%@ include file="./IncludeLib.jsp"%>
<!doctype html>
<html lang="us">
<head>
<meta charset="utf-8">
<%@ include file="./IncludeHeaderItems.jsp"%>
<script>
	$(function() {
		$("#tabs").tabs();
	});
</script>
<style>
body {
	font-size: 12px;
	margin: 10px;
}
</style>
<title>ETMS - Home Page</title>
</head>
<body>
	<h1 align="center">Welcome to Employee Task Management System</h1>
	<br />
	<div id="tabs">
		<ul>
			<li><a href="#tabs-1">Employee</a></li>
			<li><a href="#tabs-3">Task</a></li>
		</ul>
		<div id="tabs-1">
			<a href="${context}/newEmployeeAddForm">Add New Employee</a> <br />
			<a href="${context}/newViewEmployeeForm">View &amp; Manage
				Employee</a>
		</div>
		<div id="tabs-3">
			<a href="${context}/newEmployeeTaskForm">Assign Project To
				Employee</a>
		</div>
	</div>

</body>
</html>