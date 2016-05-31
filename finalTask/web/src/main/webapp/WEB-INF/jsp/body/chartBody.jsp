<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
	<head>
		<title>Chart body</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<link rel="stylesheet" type="text/css" href="/web/css/style.css"/>
		<script type="text/javascript" src="/web/js/jquery.js"></script>
		<script type="text/javascript" src="/web/js/jquery.flot.js"></script>
		<script type="text/javascript" src="/web/js/jquery.flot.time.js"></script>
	</head>

	<body>
		<div id="placeholder" class="demo-placeholder" style="padding: 0px; position: relative;">
			<canvas class="flot-base" style="direction: ltr; position: absolute; left: 0px; top: 0px; width: 818px; height: 413px;" width="818" height="413"></canvas>
			<div class="flot-text" style="position: absolute; top: 0px; left: 0px; bottom: 0px; right: 0px; font-size: smaller; color: rgb(84, 84, 84);">
				<div class="flot-x-axis flot-x1-axis xAxis x1Axis" style="position: absolute; top: 0px; left: 0px; bottom: 0px; right: 0px; display: block;">
					<div class="flot-tick-label tickLabel" style="position: absolute; max-width: 68px; top: 395px; left: 13px; text-align: center;">1990</div>
					<div class="flot-tick-label tickLabel" style="position: absolute; max-width: 68px; top: 395px; left: 91px; text-align: center;">1991</div>
					<div class="flot-tick-label tickLabel" style="position: absolute; max-width: 68px; top: 395px; left: 169px; text-align: center;">1992</div>
					<div class="flot-tick-label tickLabel" style="position: absolute; max-width: 68px; top: 395px; left: 247px; text-align: center;">1993</div>
					<div class="flot-tick-label tickLabel" style="position: absolute; max-width: 68px; top: 395px; left: 325px; text-align: center;">1994</div>
					<div class="flot-tick-label tickLabel" style="position: absolute; max-width: 68px; top: 395px; left: 404px; text-align: center;">1995</div>
					<div class="flot-tick-label tickLabel" style="position: absolute; max-width: 68px; top: 395px; left: 482px; text-align: center;">1996</div>
					<div class="flot-tick-label tickLabel" style="position: absolute; max-width: 68px; top: 395px; left: 560px; text-align: center;">1997</div>
					<div class="flot-tick-label tickLabel" style="position: absolute; max-width: 68px; top: 395px; left: 638px; text-align: center;">1998</div>
					<div class="flot-tick-label tickLabel" style="position: absolute; max-width: 68px; top: 395px; left: 716px; text-align: center;">1999</div>
				</div>
				<div class="flot-y-axis flot-y1-axis yAxis y1Axis" style="position: absolute; top: 0px; left: 0px; bottom: 0px; right: 0px; display: block;">
					<div class="flot-tick-label tickLabel" style="position: absolute; top: 382px; left: 2px; text-align: right;">310</div>
					<div class="flot-tick-label tickLabel" style="position: absolute; top: 334px; left: 2px; text-align: right;">320</div>
					<div class="flot-tick-label tickLabel" style="position: absolute; top: 287px; left: 2px; text-align: right;">330</div>
					<div class="flot-tick-label tickLabel" style="position: absolute; top: 239px; left: 2px; text-align: right;">340</div>
					<div class="flot-tick-label tickLabel" style="position: absolute; top: 191px; left: 2px; text-align: right;">350</div>
					<div class="flot-tick-label tickLabel" style="position: absolute; top: 143px; left: 2px; text-align: right;">360</div>
					<div class="flot-tick-label tickLabel" style="position: absolute; top: 96px; left: 2px; text-align: right;">370</div>
					<div class="flot-tick-label tickLabel" style="position: absolute; top: 48px; left: 2px; text-align: right;">380</div>
					<div class="flot-tick-label tickLabel" style="position: absolute; top: 0px; left: 2px; text-align: right;">390</div>
				</div>
			</div>
			<canvas class="flot-overlay" style="direction: ltr; position: absolute; left: 0px; top: 0px; width: 818px; height: 413px;" width="818" height="413"></canvas>
		</div>
	</body>
</html>