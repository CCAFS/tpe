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
<script
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=true"></script>
<script type="text/javascript" src="${ctx}/script/init-map.js"></script>
<script type="text/javascript" src="${ctx}/script/select-params.js"></script>
<script src="http://code.highcharts.com/highcharts.js"></script>
<script src="http://code.highcharts.com/highcharts-more.js"></script>
<script src="http://code.highcharts.com/modules/exporting.js"></script>
<script src="${ctx}/script/jquery-impromptu.js" type="text/javascript"></script>
<link rel="stylesheet" media="all" type="text/css"
	href="${ctx}/css/jquery-impromptu.css" />
<script src="${ctx}/script/tpe-impromptu.js" type="text/javascript"></script>
<script src="${ctx}/script/markerclusterer.js" type="text/javascript"></script>
<!-- <script	src="http://google-maps-utility-library-v3.googlecode.com/svn/trunk/markerclusterer/src/markerclusterer.js"></script> -->
<%-- <script type="text/javascript" src="${ctx}/script/init-map.js"></script> --%>
<script>
	$(document).ready(function() {

	});
</script>
</head>

<body>
	<s:form id="tpe_index" name="tpe_index">
		<p>
			<a href="#" class="tour" id="TourLink"></a>
		</p>

		<div id="select_variables">
			<h4 class="expanded">Map Options</h4>
			<div id="variables">
				<h3>Map</h3>
				<s:select name="selectedOutput" listKey="id" listValue="name"
					id="select_output" list="outputs" value="preselectedOutput"
					cssClass="map-options-select" multiple="false" required="true" />
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
						<div id="plot_box">
							<span
								style="font-size: 1.5em; line-height: 1.5em; margin: auto; color: #4e2700"></span>
						</div>
					</div>
					<div class="plot_pcew">
						<div id="plot_pcew">
							<span
								style="font-size: 1.5em; line-height: 1.5em; margin: auto; color: #4e2700"></span>
						</div>
					</div>
					<div id="plot_slide">
						<!-- <h2 class="more">More Graphics</h2> -->
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
						<div id="soil_plot">
							<span
								style="font-size: 1.5em; line-height: 1.5em; margin: auto; color: #4e2700">
							</span>
						</div>
					</div>

					<!--  CLIMATE Graphics Here-->
					<div class="rain_radiation">
						<div id="rain_radiation">
							<span
								style="font-size: 1.5em; line-height: 1.5em; margin: auto; color: #4e2700">
							</span>
						</div>
					</div>

				</div>
			</div>
		</div>
		<div id="dialog-plot">
			<div id="dialog-chart"></div>
			<div id="dialog-infos">The brief description of the chart and
				related details</div>
		</div>
		<div class="graphics-info"></div>

	</s:form>
</body>
</html>