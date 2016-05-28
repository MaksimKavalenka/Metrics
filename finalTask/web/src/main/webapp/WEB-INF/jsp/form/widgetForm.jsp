<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
	<head>
		<title>Add widget</title>
		<link rel="stylesheet" type="text/css" href="/web/css/style.css"/>
		<link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/css/bootstrap-combined.min.css" rel="stylesheet">
		<link rel="stylesheet" type="text/css" media="screen" href="http://tarruda.github.com/bootstrap-datetimepicker/assets/css/bootstrap-datetimepicker.min.css">
		<script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
		<script type="text/javascript" src="http://tarruda.github.com/bootstrap-datetimepicker/assets/js/bootstrap-datetimepicker.min.js"></script>
		<script type="text/javascript">
			$(function() {
				$('#from').datetimepicker({language: 'en-EN'});
				$('#to').datetimepicker({language: 'en-EN'});
			});
		</script>
	</head>

	<body>
		<form method="POST" name="addWidgetForm" action="/web/edit?action=add_widget">
			<table class="form">
				<tr><td class="title" colspan="2">Widget settings
				<tr><td class="error" colspan="2">${error}

				<tr><td class="name">Name
					<td><input type="text" name="Name" size="30" maxlength="30">

				<tr><td class="name">Metric type
					<td><select name="MetricType">
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

				<tr><td class="name">Refresh interval
					<td><select name="RefreshInterval">
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

				<tr><td class="name"><input type="radio" name="Custom" value="false" checked>Period
					<td><select name="Period">
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

				<tr><td class="name"><input type="radio" name="Custom" value="true">From/To
					<td><div class="well">
							<div id="from" class="input-append date">
								<input type="text" name="From" data-format="dd.MM.yyyy hh:mm:ss"/>
								<span class="add-on">
									<i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
								</span>
							</div>
							<div id="to" class="input-append date">
								<input type="text" name="To" data-format="dd.MM.yyyy hh:mm:ss"/>
								<span class="add-on">
									<i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
								</span>
							</div>
						</div>
			</table>
		</form>
		<form method="POST" name="cancelForm" action="/web/widget/show"></form>

		<input class="form" type="submit" value="Save" onClick="javascript:document.addWidgetForm.submit()">
		<input class="form" type="button" value="Back" onClick="javascript:document.cancelForm.submit()">
	</body>
</html>