<%@ include file="./IncludeLib.jsp"%>
<!doctype html>
<html lang="us">
<head>
<meta charset="utf-8">
<title>Error Message</title>
</head>
<body>
	<div class="ui-widget">
		<div class="ui-state-error ui-corner-all" style="padding: 0 .7em;">
			<p>
				<span class="ui-icon ui-icon-alert"
					style="float: left; margin-right: .3em;"></span> <strong>Error:</strong>
				${errorMessage}
			</p>
		</div>
	</div>
</body>
</html>