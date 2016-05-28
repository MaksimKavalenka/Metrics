<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
	<head>
		<title>Add dashboard</title>
		<link rel="stylesheet" type="text/css" href="/web/css/style.css"/>
		<link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/css/bootstrap-combined.min.css" rel="stylesheet">
		<script type="text/javascript">
			function setVisibility(number) {
				document.addDashboardForm.number.value = number;
				var widgets = document.getElementsByTagName('select');
				for (i = 1; i < widgets.length; i++) {
					widgets[i].style.display = 'none';
				}
				for (i = 1; i < number; i++) {
					widgets[i].style.display = 'block';
				}
			}
		</script>
		<script type="text/javascript">
			$(function() {
				$('#from').datetimepicker({language: 'en-EN'});
				$('#to').datetimepicker({language: 'en-EN'});
			});
		</script>
	</head>

	<body>
		<form method="POST" name="addDashboardForm" action="/web/edit">
			<input type="hidden" name="action" value="add_dashboard">
			<input type="hidden" name="number" value="4">

			<table class="form">
				<tr><td class="title" colspan="2">Dashboard settings
				<tr><td class="error" colspan="2">${error}

				<tr><td class="name">Name
					<td><input type="text" name="Name" size="30" maxlength="30">

				<tr><td class="name">Description
					<td><input type="text" name="Description" size="30" maxlength="100">

				<tr><td class="name">Number of widgets
					<td><a href="javascript:setVisibility('1')">1</a>
						<a href="javascript:setVisibility('2')">2</a>
						<a href="javascript:setVisibility('4')">4</a>

				<tr><td><select name="IdWidget1">
							<c:choose>
								<c:when test="${not empty Widget}">
									<c:forEach var="widget" items="${Widget}">
										<option value="${widget.id}">${widget.name}</option>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<option disabled>Empty</option>
								</c:otherwise>
							</c:choose>
						</select>
					<td><select name="IdWidget2">
							<c:choose>
								<c:when test="${not empty Widget}">
									<c:forEach var="widget" items="${Widget}">
										<option value="${widget.id}">${widget.name}</option>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<option disabled>Empty</option>
								</c:otherwise>
							</c:choose>
						</select>

				<tr><td><select name="IdWidget3">
							<c:choose>
								<c:when test="${not empty Widget}">
									<c:forEach var="widget" items="${Widget}">
										<option value="${widget.id}">${widget.name}</option>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<option disabled>Empty</option>
								</c:otherwise>
							</c:choose>
						</select>
					<td><select name="IdWidget4">
							<c:choose>
								<c:when test="${not empty Widget}">
									<c:forEach var="widget" items="${Widget}">
										<option value="${widget.id}">${widget.name}</option>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<option disabled>Empty</option>
								</c:otherwise>
							</c:choose>
						</select>
			</table>
		</form>
		<form method="POST" name="cancelForm" action="/web/dashboard/show"></form>

		<input class="form" type="submit" value="Save" onClick="javascript:document.addDashboardForm.submit()">
		<input class="form" type="button" value="Back" onClick="javascript:document.cancelForm.submit()">
	</body>
</html>