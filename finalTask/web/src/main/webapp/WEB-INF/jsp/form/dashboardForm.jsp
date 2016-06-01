<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
	<head>
		<title>Add dashboard</title>
		<link rel="stylesheet" type="text/css" href="/web/css/style.css"/>
		<script type="text/javascript">
			function setVisibility(number) {
				document.editDashboardForm.number.value = number;
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

	<body onLoad="setVisibility('${(empty IdWidget) ? 4 : fn:length(IdWidget)}')">
		<c:set var="action" value="${(empty Id) ? 'add_dashboard' : 'modify_dashboard'}"/>

		<form method="POST" name="editDashboardForm" action="/web/edit">
			<input type="hidden" name="action" value="${action}">
			<input type="hidden" name="number">
			<input type="hidden" name="Id" value="${Id}">

			<table class="form">
				<tr><td class="title" colspan="2">Dashboard settings
				<tr><td class="error" colspan="2">${error}

				<tr><td class="name">Name
					<td><input type="text" name="Name" size="30" maxlength="30" value="${Name}">

				<tr><td class="name">Description
					<td><input type="text" name="Description" size="30" maxlength="100" value="${Description}">

				<tr><td class="name">Number of widgets
					<td><a href="javascript:setVisibility(1)">1</a>
						<a href="javascript:setVisibility(2)">2</a>
						<a href="javascript:setVisibility(4)">4</a>

				<tr><c:forEach var="i" begin="1" end="4">
					<td><select name="IdWidget${i}">
							<c:choose>
								<c:when test="${not empty Widget}">
									<c:forEach var="widget" items="${Widget}" varStatus="counter">
										<c:choose>
											<c:when test="${IdWidget[i-1] eq counter.count}">
												<option value="${widget.id}" selected>${widget.name}</option>
											</c:when>
											<c:otherwise>
												<option value="${widget.id}">${widget.name}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<option disabled>Empty</option>
								</c:otherwise>
							</c:choose>
						</select>
					<c:if test="${i % 2 == 0}">
						<tr>
					</c:if>
				</c:forEach>
			</table>
		</form>
		<form method="POST" name="cancelForm" action="/web/dashboard/show"></form>

		<input class="form" type="submit" value="Save" onClick="document.editDashboardForm.submit()">
		<input class="form" type="button" value="Back" onClick="document.cancelForm.submit()">
	</body>
</html>