<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
	<head>
		<title>Dashboards</title>
		<link rel="stylesheet" type="text/css" href="/webapp/css/style.css"/>
	</head>

	<body>
		<table class="main">
			<tr class="head">
				<td>
					<c:import url="../title/mainTitle.jsp"/>
				</td>
			</tr>
			<tr class="body">
				<td>
					<c:import url="../body/dashboardBody.jsp"/>
				</td>
			</tr>
		</table>
	</body>
</html>