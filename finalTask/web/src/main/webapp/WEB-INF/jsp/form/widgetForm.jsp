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
							<option disabled>Choose metric type</option>
							<c:forEach var="metricType" items="${metricTypeList}" varStatus="counter">
								<option value="${counter.count}">${metricType}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td class="name">Period</td>
					<td>
						<select name="Period">
							<option disabled>Choose period</option>
							<c:forEach var="period" items="${periodList}" varStatus="counter">
								<option value="${counter.count}">${period}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td class="name">Refresh interval</td>
					<td>
						<select name="RefreshInterval">
							<option disabled>Choose refresh interval</option>
							<c:forEach var="refreshInterval" items="${refreshIntervalList}" varStatus="counter">
								<option value="${counter.count}">${refreshInterval}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
			</table>
		</form>
		<form method="POST" name="cancelForm" action="/web/page?action=show_widget"></form>

		<input class="agree" type="submit" value="Save" onClick="JavaScript:document.widgetForm.submit()">
		<input class="cancel" type="button" value="Back" onClick="JavaScript:document.cancelForm.submit()">
	</body>
</html>