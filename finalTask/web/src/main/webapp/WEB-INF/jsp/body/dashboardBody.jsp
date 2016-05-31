<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
	<head>
		<title>Config body</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<link rel="stylesheet" type="text/css" href="/web/css/style.css"/>
		<script type="text/javascript">
			function modifyDashboard(id) {
				document.modifyDashboardForm.Id.value = id;
				document.modifyDashboardForm.submit();
			}
		</script>
		<script type="text/javascript">
			function deleteDashboard(id) {
				document.deleteDashboardForm.Id.value = id;
				document.deleteDashboardForm.submit();
			}
		</script>
	</head>

	<body>
		<form method="POST" name="modifyDashboardForm" action="/web/dashboard/modify">
			<input type="hidden" name="Id">
		</form>
		<form method="POST" name="deleteDashboardForm" action="/web/edit?action=delete_dashboard">
			<input type="hidden" name="Id">
		</form>

		<table class="body">
			<tr class="title">
				<th width="5%">No.
				<th width="30%">Name
				<th width="45%">Description
				<th width="20%">Actions

			<c:forEach var="dashboard" items="${Dashboard}" varStatus="counter">
				<tr class="content">
					<td align="right">${counter.count}
					<td>${dashboard.name}
					<td>${dashboard.description}
					<td>
						<input class="action" type="button" value="Modify" onClick="modifyDashboard(${dashboard.id})">
						<input class="action" type="button" value="Delete" onClick="deleteDashboard(${dashboard.id})">
			</c:forEach>
		</table>

		<a class="add" href="javascript:window.location='/web/dashboard/add'">Add dashboard</a>
	</body>
</html>