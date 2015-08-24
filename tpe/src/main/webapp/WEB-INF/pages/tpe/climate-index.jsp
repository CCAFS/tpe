<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/flick/jquery-ui.css" />

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jqgrid/4.6.0/css/ui.jqgrid.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"
	type="text/javascript"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jqgrid/4.6.0/js/jquery.jqGrid.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jqgrid/4.6.0/js/i18n/grid.locale-en.js"></script>

<title>Climate</title>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						var outputValue = $("select#select_output").val();
						var preselected_country, preselected_crop, preselected_cultivar;
						var href_query_soil = "querySoil.action";
						var href_query_climate = "queryClimate.action";
						// Get the select#select_output text
						var outputControl = document
								.getElementById('select_output');
						var outputText = outputControl.options[outputControl.selectedIndex].text;

						/* $("#params_out").load(href_output, {
							selectedOutput : 1
						}, function() {
							// Initialize or preload the variables
							loadVariables(outputText);
						
						}); */

						$(
								"select#select_output,select#select_country,select#select_param")
								.change(
										function() {
											/* 	outputText = outputControl.options[outputControl.selectedIndex].text;
												outputValue = $("select#select_output").val();
												outputValue = $(this).val(); */

											var href, option, selOption = document
													.getElementById('select_output');
											option = selOption.options[selOption.selectedIndex].text;

											if (option == 'Climate') {
												href = href_query_climate;
												$('#climate-params').show()
														.animate({
															left : 0
														});
											} else {
												href = href_query_soil;
												$('#climate-params').hide();
											}
											$("#sections").load(href, {
											/* selectedOutput : outputValue */
											}, function() {

											});
										});

						//Hide the climate parameters if the soil is selected
						/* $('#select_output'). */

						/* $('#climate-params') */
						
						$("#select_params").draggable({
							containment : "#sections",
							cursor : 'move',
							snap : '#sections'
						});
						
					});
</script>
<style type="text/css">
</style>
</head>
<body>
	<!-- The query form -->
	<s:form id="params_form" name="params_form">
		<div id="select_params">
			<h4 class="expanded">Query Options</h4>
			<div id="variables">
				<h3>Category</h3>
				<s:select name="selectedOutput" listKey="id" listValue="name"
					id="select_output" list="outputs" value="preselectedOutput"
					cssClass="map-options-select" multiple="false" required="true" />
				<h3>
					Region<span style="color: #990000; font-size: 10px;"></span>
				</h3>
				<s:select name="selectedCountry" listKey="id" listValue="name"
					id="select_country" list="countries" value="preselectedCountry"
					cssClass="map-options-select" multiple="false" required="true" />
				<div id="climate-params">
					<h3>
						Parameters<span style="color: #990000; font-size: 10px;"></span>
					</h3>
					<s:select name="selectedParam" listKey="id" listValue="name"
						id="select_param" list="params" value="preselectedParam"
						cssClass="map-options-select" multiple="false" required="true" />
				</div>
			</div>
		</div>
	</s:form>
	<!-- The div for the grid data -->
	<div id="sections"></div>
</body>
</html>