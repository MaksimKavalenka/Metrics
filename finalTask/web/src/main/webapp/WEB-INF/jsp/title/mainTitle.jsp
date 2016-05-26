<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
	<head>
		<title>Settings Menu</title>
		<link rel="stylesheet" type="text/css" href="/web/css/style.css"/>
	</head>

	<body>
		<section class="border">
			<input type="button" class="button" value="Dashboards" onClick="javascript:window.location='page?action=show_dashboard'">
			<input type="button" class="button" value="Widgets" onClick="javascript:window.location='page?action=show_widget'">
		</section>
	</body>
</html>