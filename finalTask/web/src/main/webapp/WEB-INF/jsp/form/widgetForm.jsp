<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
	<head>
		<title>Add widget</title>
		<link rel="stylesheet" type="text/css" href="/web/css/style.css"/>
	</head>

	<body>
		<form method="POST" name="widgetForm" action="/web/edit">
			<input type=hidden name="action" value="add_widget">

			<table class="form">
				<tr>
					<td colspan="2" align="center">
						<c:choose>
							<c:when test="${empty error}">
								<b class="success">The widget is saved successfully</b>
							</c:when>
							<c:otherwise>
								<b class="error">${error}</b>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">Widget settings</td>
				</tr>
				<tr>
					<td class="name">Name</td>
					<td>
						<input type="text" name="Name" size="30" maxlength="30">
					</td>
				</tr>
				<tr>
					<td class="name">Metric type</td>
					<td>
						<select name="MetricType">
							<c:choose>
								<c:when test="${not empty MetricType}">
									<c:forEach var="metricType" items="${MetricType}" varStatus="counter">
										<option value="${counter.count}">${metricType}</option>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<option disabled>Empty</option>
								</c:otherwise>
							</c:choose>
						</select>
					</td>
				</tr>
				<tr>
					<td class="name">Period</td>
					<td>
						<select name="Period">
							<c:choose>
								<c:when test="${not empty Period}">
									<c:forEach var="period" items="${Period}" varStatus="counter">
										<option value="${counter.count}">${period}</option>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<option disabled>Empty</option>
								</c:otherwise>
							</c:choose>
						</select>
					</td>
				</tr>
				<tr>
					<td class="name">Refresh interval</td>
					<td>
						<select name="RefreshInterval">
							<c:choose>
								<c:when test="${not empty RefreshInterval}">
									<c:forEach var="refreshInterval" items="${RefreshInterval}" varStatus="counter">
										<option value="${counter.count}">${refreshInterval}</option>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<option disabled>Empty</option>
								</c:otherwise>
							</c:choose>
						</select>
					</td>
				</tr>
			</table>
		</form>
		<form method="POST" name="cancelForm" action="/web/widget/show"></form>

		<input class="agree" type="submit" value="Save" onClick="JavaScript:document.widgetForm.submit()">
		<input class="cancel" type="button" value="Back" onClick="JavaScript:document.cancelForm.submit()">
	</body>
</html>