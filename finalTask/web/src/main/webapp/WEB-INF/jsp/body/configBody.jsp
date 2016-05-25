<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
	<head>
		<title>Config Body</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<link rel="stylesheet" type="text/css" href="/web/css/style.css"/>
	</head>

	<body>
		<table class="body">
			<tr class="title">
				<td width="5%">No.</td>
				<td width="30%">Name</td>
				<td width="45%">Description</td>
				<td width="20%">Settings</td>
			</tr>

			<c:forEach var="config" items="${Config}" varStatus="counter">
				<tr>
					<td>
						${counter.count}
					</td>
					<td>
						${config.name}
					</td>
					<td>
						${counter.description}
					</td>
					<td>
						TO DO
					</td>
				</tr>
			</c:forEach>

			<tr>
				<td colspan="4"><a class="title">Add Config</a></td>
			</tr>

		</table>
	</body>
</html>