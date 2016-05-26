<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
	<head>
		<title>Config body</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<link rel="stylesheet" type="text/css" href="/web/css/style.css"/>
	</head>

	<body>
		<table class="body">
			<tr class="title">
				<th width="5%">No.</th>
				<th width="30%">Name</th>
				<th width="45%">Description</th>
				<th width="20%">Settings</th>
			</tr>

			<c:forEach var="dashboard" items="${Dashboard}" varStatus="counter">
				<tr class="content">
					<td align="right">
						${counter.count}
					</td>
					<td>
						${dashboard.name}
					</td>
					<td>
						${dashboard.description}
					</td>
					<td>
						TO DO
					</td>
				</tr>
			</c:forEach>
		</table>

		<div id="add" class="footer">
			<a class="add" href="javascript:window.location='page?action=add_dashboard'">Add dashboard</a>
		</div>
	</body>
</html>