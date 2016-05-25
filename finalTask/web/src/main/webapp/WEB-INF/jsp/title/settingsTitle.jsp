<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
	<head>
		<title>Settings Menu</title>
		<link rel="stylesheet" type="text/css" href="/web/css/style.css"/>
	</head>

	<body>
		<section class="border">
			<input type="button" class="button" value="Configs" onClick="javascript:window.location='settings?action=config'">
			<input type="button" class="button" value="Widgets" onClick="javascript:window.location='settings?action=widget'">
		</section>
	</body>
</html>