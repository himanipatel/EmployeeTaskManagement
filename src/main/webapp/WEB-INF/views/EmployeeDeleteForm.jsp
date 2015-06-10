<%@ include file="./IncludeLib.jsp"%>
<!doctype html>

<html lang="us">
<head>
<meta charset="utf-8">
<%@ include file="./IncludeHeaderItems.jsp"%>
<title>ETMS - Employee Remove Page</title>
<script>
	$(function() {
		$("#btnYes").button();
		$("#btnNo").button();
		$("#tryAgain").button();
		$("#deleteOk").button();
	});

	function cancelAction(url) {
		document.location.href = url;
	}
</script>
</head>
<body>
	<a href="${context}">Home</a>
	<br />
	<c:if test="${errorMessage != null}">
		<%@ include file="./ErrorMessage.jsp"%>
	</c:if>
	<br />
	<form:form method='post'
		action='./deleteEmployeeProfile?employeeId=${employeeId}'>
		<div>
			Are you sure you want to remove employee with id ${employeeId} ? <br />
			<button id="btnYes" type="submit">Yes</button>
			<button id="btnNo" type="button"
				onclick="javascript:cancelAction('${context}');">No</button>
		</div>
	</form:form>
</body>
</html>