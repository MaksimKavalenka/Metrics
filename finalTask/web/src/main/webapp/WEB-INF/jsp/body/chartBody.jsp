<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
	<head>
		<title>Chart body</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<link rel="stylesheet" type="text/css" href="/web/css/style.css"/>
		<script type="text/javascript" src="/web/js/jquery.js"></script>
		<script type="text/javascript" src="/web/js/jquery.flot.js"></script>
	</head>

	<body>
		<table class="body">
			<c:forEach var="widget" items="${Widget}" varStatus="counter">
				<td class="chart">
					<div class="head">${widget.name}</div>
					<div id="placeholder" class="chart">
					</div>
				</td>
				<c:if test="${counter.count % 2 == 0}">
					<tr class="content">
				</c:if>
			</c:forEach>
		</table>
	</body>
</html>