<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

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
				var count = ${fn:length(Widget)};
				var width, height;
				switch (count) {
					case 1:
						width = ($(window).width() * 0.95) + "px";
						height = ($(window).height() * 0.7) + "px";
						break;
					case 2:
					case 3:
					case 4:
						width = ($(window).width() / 2  * 0.95) + "px";
						height = ($(window).height() / 2 * 0.7) + "px";
						break;
				}
				for (i = 1; i <= count; i++) {
					document.getElementById('placeholder' + i).style.height = height;
					document.getElementById('placeholder' + i).style.width = width;
				}
				$('div.chart').each(function(index, element) {
					show(element.id);
					setInterval(function() {show(element.id);}, $('#' + element.id).attr('data-time'));
				});
			});

			function show(id) {
				var widgetId = $('#' + id).attr('data-id');
				$.ajax({
					type: 'POST',
					url: '/web/edit?action=chart&Id='+widgetId,
					dataType: 'html',
					success: function (url) {
						$.getJSON(url, function(data) {
							var rawData = [];
							$.each(data, function(index, element) {
								var utc = 10800000;
								rawData.push([element.date + utc, element.value]);
							});
							var options = {xaxis: {mode: 'time'}}
							$.plot($('#' + id), [{data: rawData}], options);
						});
					}
				});
			}
		</script>
	</head>

	<body>
		<table class="body">
			<tr><td style="font-size: 20px;">${Name}
			<tr><td style="font-size: 12px;">${Description}<tr>
			<c:forEach var="widget" items="${Widget}" varStatus="counter">
				<td class="chart">
					<div class="head">${widget.name}</div>
					<div class="chart" id="placeholder${counter.count}" data-id="${widget.id}" data-time="${widget.refreshInterval.nanoTime}"></div>
				</td>
				<c:if test="${counter.count % 2 == 0}">
					<tr>
				</c:if>
			</c:forEach>
		</table>
	</body>
</html>