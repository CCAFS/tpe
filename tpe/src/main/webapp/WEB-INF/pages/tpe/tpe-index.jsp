<%@ include file="/common/taglibs.jsp"%>
<html lang="en">
<head>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no">
<title>CCAFS TPE Platform</title>
<style>
</style>

<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

<script src="https://maps.googleapis.com/maps/api/js?v=3.exp"></script>
<script type="text/javascript" src="${ctx}/script/select-params.js"></script>
<script type="text/javascript" src="${ctx}/script/init-map.js"></script>
<script type='text/javascript'>
	$(document).ready(function() {

		/* $.getJSON('testGeoJson.json', function(data) {
								console.log('GEOJON DATA: ' + data);
							}); */

		/* $.ajax({
			type : 'GET',
			url : 'script/test.json',
			dataType : 'json'
		}).done(function(data) {
			console.log(JSON.stringify(data));
		}); */

	});
</script>
</head>

<body>
	<div id="select_variables">
		<h4 class="expanded">Select Variables</h4>
		<div id="variables">
			<h3>Output</h3>
			<s:select name="selectedOutput" listKey="id" listValue="name"
				id="select_output" list="outputs" value="preselectedOutput"
				cssStyle="width:100%;" multiple="false" size="1" required="true" />
			<div id="params_out"></div>
			<!-- <div id="params_soil"></div>
			<div id="params_climate"></div> -->
		</div>
	</div>

	<div id="tpe_main">
		<!--  TPE Google Map-->
		<div id="tpe_map" class="tpe_map_min"></div>
		<div id="info">
			<h2>Feature Infos goes here</h2>
			<b>Details</b>: <span id="info_details">Details!</span>
		</div>
		<div id="legend-container">
			<h3>TPE Legend</h3>
		</div>
		<div id="tpe_analytics">
			<h4 class="expanded">TPE Analytics</h4>
			<div id="analytics">
				<div class="tpe_plot"></div>
				<div class="tpe_plot"></div>
				<div class="tpe_plot"></div>
				<div class="tpe_plot"></div>
			</div>
		</div>
	</div>

</body>
</html>