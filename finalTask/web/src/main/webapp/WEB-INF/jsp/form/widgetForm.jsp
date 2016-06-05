<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
	<head>
		<title>Add widget</title>
		<link rel="stylesheet" type="text/css" href="/web/css/style.css"/>
	</head>

	<body>
		<c:set var="action" value="${(empty Id) ? 'add_widget' : 'modify_widget'}"/>

		<form method="POST" name="editWidgetForm" action="/web/edit">
			<input type="hidden" name="action" value="${action}">
			<input type="hidden" name="Id" value="${Id}">

			<table class="form">
				<tr><td class="title" colspan="2">Widget settings
				<tr><td class="error" colspan="2">${error}

				<tr><td class="name">Name
					<td><input type="text" name="Name" size="30" maxlength="30" value="${Name}">

				<tr><td class="name">Metric type
					<td><select name="MetricType">
							<c:choose>
								<c:when test="${not empty metricTypeList}">
									<c:forEach var="metricType" items="${metricTypeList}" varStatus="counter">
										<c:choose>
											<c:when test="${metricType eq MetricType}">
												<option value="${counter.count}" selected>${metricType.toString()}</option>
											</c:when>
											<c:otherwise>
												<option value="${counter.count}">${metricType.toString()}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<option disabled>Empty</option>
								</c:otherwise>
							</c:choose>
						</select>

				<tr><td class="name">Refresh interval
					<td><select name="RefreshInterval">
							<c:choose>
								<c:when test="${not empty refreshIntervalList}">
									<c:forEach var="refreshInterval" items="${refreshIntervalList}" varStatus="counter">
										<c:choose>
											<c:when test="${refreshInterval eq RefreshInterval}">
												<option value="${counter.count}" selected>${refreshInterval.toString()}</option>
											</c:when>
											<c:otherwise>
												<option value="${counter.count}">${refreshInterval.toString()}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<option disabled>Empty</option>
								</c:otherwise>
							</c:choose>
						</select>

				<tr><td class="name">
					<c:choose>
						<c:when test="${not Custom}">
							<input type="radio" name="Custom" value="false" checked>Period
						</c:when>
						<c:otherwise>
							<input type="radio" name="Custom" value="false">Period
						</c:otherwise>
					</c:choose>
					<td><select name="Period">
							<c:choose>
								<c:when test="${not empty periodList}">
									<c:forEach var="period" items="${periodList}" varStatus="counter">
										<c:choose>
											<c:when test="${period eq Period}">
												<option value="${counter.count}" selected>${period.toString()}</option>
											</c:when>
											<c:otherwise>
												<option value="${counter.count}">${period.toString()}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<option disabled>Empty</option>
								</c:otherwise>
							</c:choose>
						</select>

				<tr><td class="name">
					<c:choose>
						<c:when test="${Custom}">
							<input type="radio" name="Custom" value="true" checked>From/To
						</c:when>
						<c:otherwise>
							<input type="radio" name="Custom" value="true">From/To
						</c:otherwise>
					</c:choose>
					<td><input type="datetime-local" name="Start" step="1" data-format="dd.MM.yyyy hh:mm:ss" value="${Start}"/>
					<br><input type="datetime-local" name="End" step="1" data-format="dd.MM.yyyy hh:mm:ss" value="${End}"/>
			</table>
		</form>
		<form method="POST" name="cancelForm" action="/web/widget/show"></form>

		<input class="form" type="submit" value="Save" onClick="document.editWidgetForm.submit()">
		<input class="form" type="button" value="Back" onClick="document.cancelForm.submit()">
	</body>
</html>