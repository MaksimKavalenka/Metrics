<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
	<head>
		<title>Chart body</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<link rel="stylesheet" type="text/css" href="/web/css/style.css"/>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
		<script src="http://static.pureexample.com/js/flot/excanvas.min.js"></script>
		<script src="http://static.pureexample.com/js/flot/jquery.flot.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function() {
				$('div.chart').each(function(i, d) {
					show(d.id);
					setInterval(function() {show(d.id);}, $('#' + d.id).attr('data-time'));
				});
			});

			function show(id) {
				var widget = $('#' + id).attr('data-widget');
				$.ajax({
					type: 'POST',
					url: '/web/edit?action=chart&Id='+widget,
					dataType: 'json',
					error: function(xhr, status, error) {
						alert(xhr.responseText);
					},
					success: function (data) {
						var rawData = [];
						$.each(data, function(index, element) {
							rawData.push([element.date, element.value]);
						});
						var options = {xaxis: {mode: 'time'}}
						$.plot($('#' + id), [{data: rawData}], options);
					}
				});
			}
		</script>
	</head>

	<body>
		<table class="body">
			<c:forEach var="widget" items="${Widget}" varStatus="counter">
				<td class="chart">
					<div class="head">${widget.name}</div>
					<div class="chart" id="placeholder${counter.count}" data-widget="${widget.id}" data-time="${widget.refreshInterval.nanoTime}" style="width:900px;height:400px"></div>
				</td>
				<c:if test="${counter.count % 2 == 0}">
					<tr>
				</c:if>
			</c:forEach>
		</table>
	</body>
</html>