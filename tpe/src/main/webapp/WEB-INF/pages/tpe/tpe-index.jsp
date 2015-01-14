<%@ include file="/common/taglibs.jsp"%>
<html lang="en">
<head>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no">
<title>CCAFS TPE Platform</title>
<style>
</style>

<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/south-street/jquery-ui.css" />
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp"></script>
<script type="text/javascript" src="${ctx}/script/init-map.js"></script>
<script type="text/javascript" src="${ctx}/script/select-params.js"></script>
<script src="http://code.highcharts.com/highcharts.js"></script>
<script src="http://code.highcharts.com/highcharts-more.js"></script>
<script src="http://code.highcharts.com/modules/exporting.js"></script>
<%-- <script type="text/javascript" src="${ctx}/script/init-map.js"></script> --%>
<script type='text/javascript'>
 
</script>
</head>

<body>
	<s:form id="tpe_index" name="tpe_index">
		<div id="select_variables">
			<h4 class="expanded">Select Variables</h4>
			<div id="variables">
				<h3>Output</h3>
				<s:select name="selectedOutput" listKey="id" listValue="name"
					id="select_output" list="outputs" value="preselectedOutput"
					cssStyle="width:100%;" multiple="false" size="3" required="true" />
				<div id="params_out"></div>
			</div>
		</div>

		<div id="tpe_main">
			<!--  TPE Google Map-->
			<div id="tpe_map" class="tpe_map_min">
				<div id="loading">
					<img src="${ctx}/img/ajax-loader.gif" />
				</div>
			</div>
			<div id="info">
				<h2></h2>
				<span id="info_details">Details!</span>
			</div>
			<div id="legend-container">
				<h3>Legend: Soil Texture</h3>
			</div>
			<div id="tpe_analytics">
				<h4 class="expanded">TPE Analytics</h4>
				<div id="analytics">
					<div class="tpe_plot">
						<div id="env_container"></div>
					</div>
					<div class="tpe_plot">
						<div id="plot_lai"></div>
					</div>
					<div class="tpe_plot">
						<div id="plot_pcew"></div>
					</div>
					<div class="tpe_plot">
						<div id="plot_temprain"></div>
					</div>
				</div>
			</div>
		</div>
		<div id="dialog-plot"><div id="dialog-chart"></div></div>
	</s:form>
</body>
</html>