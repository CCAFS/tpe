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
<script src="${ctx}/script/tpe-overlay-dialog.js"></script>
<%-- <script type="text/javascript" src="${ctx}/script/init-map.js"></script> --%>
<script>
	$(document)
			.ready(
					function() {
						$('.paulund_modal')
								.paulund_modal_box(
										{
											title : 'TPE Visualization',
											description : 'Custom description for TPE Google Map Resutls <br/><img src="img/test.png" /><br/> The brief description to the user what is TPE and how to use it?.',
											height : '500',
											width : '500'
										});

						$('.graphics-info')
								.paulund_modal_box(
										{
											title : 'TPE Analytics',
											description : 'Custom description for TPE Graphics <br/><img src="img/test.png" /><br/>The brief description the currently clicked TPE graphic plot.',
											height : '500',
											width : '500'
										});
					});
</script>
</head>

<body>
	<s:form id="tpe_index" name="tpe_index">

		<div id="select_variables">
			<h4 class="expanded">Visualization Options</h4>
			<div id="variables">
				<h3>Map Output</h3>
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
			<!-- <div id="legend-container">
				<h3>Legend: Soil Texture</h3>
			</div> -->
			<div id="tpe_analytics">
				<h4 class="expanded">TPE Analytics</h4>
				<div id="analytics">
					<!--  TPE Graphics Here-->
					<div class="plot_box">
						<div id="plot_box"></div>
					</div>
					<div class="plot_pcew">
						<div id="plot_pcew"></div>
					</div>
					<div id="plot_slide">
						<h2 class="more">More Graphics</h2>
						<h3 id="raincum" class="collapsed">Cumulatve Rainfall</h3>
						<div class="plot_raincum">
							<div id="plot_raincum"></div>
						</div>
						<h3 id="rainsum" class="collapsed">Average Rainfall</h3>
						<div class="plot_rainsum">
							<div id="plot_rainsum"></div>
						</div>
						<h3 id="wagt" class="collapsed">Total Dry Matter</h3>
						<div class="plot_wagt">
							<div id="plot_wagt"></div>
						</div>
						<h3 id="lai" class="collapsed">Leaf Area Index</h3>
						<div class="plot_lai">
							<div id="plot_lai"></div>
						</div>
					</div>
					<!--  SOIL Graphics Here-->
					<div class="soil_plot">
						<div id="soil_plot"></div>
					</div>

					<!--  CLIMATE Graphics Here-->
					<div class="rain_radiation">
						<div id="rain_radiation"></div>
					</div>

				</div>
			</div>
		</div>
		<div id="dialog-plot">
			<div id="dialog-chart"></div>
		</div>
		<div class="graphics-info"></div>
	</s:form>
	<a href="#" class="paulund_modal">Just for Testing dialog form</a>
</body>
</html>