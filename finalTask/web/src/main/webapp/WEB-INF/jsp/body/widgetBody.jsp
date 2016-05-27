<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
	<head>
		<title>Widget body</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<link rel="stylesheet" type="text/css" href="/web/css/style.css"/>
	</head>

	<body>
		<table class="body">
			<tr class="title">
				<th width="5%">No.</th>
				<th width="25%">Name</th>
				<th width="20%">Metric type</th>
				<th width="15%">Period</th>
				<th width="15%">Refresh interval</th>
				<th width="20%">Actions</th>
			</tr>

			<c:forEach var="widget" items="${Widget}" varStatus="counter">
				<tr class="content">
					<td align="right">
						${counter.count}
					</td>
					<td>
						${widget.name}
					</td>
					<td>
						${widget.metricType.toString()}
					</td>
					<td>
						${widget.period.toString()}
					</td>
					<td>
						${widget.refreshInterval.toString()}
					</td>
					<td>
						TO DO
					</td>
				</tr>
			</c:forEach>
		</table>

		<a class="add" href="javascript:window.location='/web/widget/add'">Add widget</a>
	</body>
</html>