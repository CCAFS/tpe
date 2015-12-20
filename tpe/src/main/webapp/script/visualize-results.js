var dataJSON, categoriesJSON, seriesTempRain, categoriesTempRain, tpeDialogTitle = 'CCAFS TPE Graphics', legendTitle='Legend:';
var hfeSeries, lfeSeries, feSeries, boxJSON, clickedFeature, currentOutput, clickedFeatureName, selectedFeature, selectedOutput;
var OUTPUT_SOIL = 'SOIL', OUTPUT_CLIMATE = 'CLIMATE', OUTPUT_TPE = 'TPE', OUTPUT_STABILITY = 'STABILITY';
var ENV_HFE = 'HFE', ENV_LFE = 'LFE', ENV_FE = 'FE', seriesJSON;
var COLOR_HFE = '#00ff00', COLOR_LFE = '#ff0000', COLOR_FE = '#0041a0';
var STABILITY_HIGH='high',STABILITY_MIDDLE='middle',STABILITY_LOW='low';

/**
 * Depending on the selected OUTPUT text, get the corresponding selected params.
 * For SOIL
 * (selectedTexture,selectedProperties,selectedCoutry,selectedSubregions) For
 * TPE (selectedCrop selectedCultivar, selectedCoutry, selectedYears,
 * selectedSowingWindow) For CLIMATE (selectedProperties, selectedCountry,
 * selectedSubregions, selectedYears, selectedStations)
 */
function visualizeResults() {

	// Get the parameters
	// var selectedCountry, selectedOutput, selectedScenario, selectedTexture,
	// selectedProperties, selectedRregions, selectedYears, selectedStations,
	// selectedCrop, selectedCultivar, selectedSWindow;
	// The currently selected output
	// var output =
	// $("select#select_output").val();
	// The currently selected soil
	// texture
	// Get the selected output
	// Get the select#select_output text
	// var selectedOutput;
	var output = document.getElementById('select_output');
	selectedOutput = output.options[output.selectedIndex].text;

	// The actions
	var soilGeoJsonAction = 'json/soilGeoJson.geojson';
	var tpeGeoJsonAction = 'json/tpeGeoJson.geojson';
	var climateGeoJsonAction = 'json/climateGeoJson.geojson';
	var stabilityGeoJsonAction = 'json/stabilityGeoJson.geojson';
	var params, actionJson;
	// Use the switch statement to determine the selected output, from the first
	// select drop down box
	// TPE, SOIL or CLIMATE 
	switch (selectedOutput.toUpperCase()) {
	case 'TPE':
		// Set the legend title for TPE
		//legendTitle = 'Legend: TPE Environments';
		hideShow('TPE');
		// Selected TPE. The get the
		// corresponding selected TPE
		// params

		// The action url for the TPE map
		actionJson = 'json/tpeGeoJson.geojson';
		// Call the geoJsonData function and pass the params y action
		loadJson(actionJson, 'TPE');
		// initializeMap();

		break;
	case 'STABILITY':
		// Set the legend title for STABILITY Map
		//legendTitle = 'Legend: TPE Environment Stability';
		hideShow('STABILITY');
		// Selected STABILITY. The get the
		// corresponding selected STABILITY
		// params

		// The action url for the Stability map
		actionJson = 'json/stabilityGeoJson.geojson';
		// Call the geoJsonData function and pass the params y action
		loadJson(actionJson, 'STABILITY');
		// initializeMap();

		break;

	case 'SOIL':
		//legendTitle = 'Legend: Soil Texture';
		hideShow('SOIL');
		// The action url for the soil map
		actionJson = 'json/soilGeoJson.geojson';
		// Call the geoJsonData function and pass the params y action
		loadJson(actionJson, 'SOIL');

		break;

	case 'CLIMATE':
		//legendTitle = 'Legend: Climate';
		hideShow('CLIMATE');
		// The action url for the soil map
		actionJson = 'json/climateGeoJson.geojson';
		// Call the geoJsonData function and pass the params y action
		loadJson(actionJson, 'CLIMATE');
		// initializeMap(); 
		break;
	default:
		break;
	}

}

function hideShow(graphic) {

	if (graphic == 'TPE') {
		$('#plot_slide').show();
		// Hide the charts
		$('.plot_box').show();
		$('.soil_plot').hide();
		// $('.plot_lai').show();//Will be hidden by default
		// $('.plot_temprain').show();
		$('.plot_pcew').show();
		$('.rain_radiation').hide();
		//$('.plot_wagt').show();//Will be hidden by default.
		// $('.plot_rainsum').show();//This will be hiden by default
		// $('.plot_raincum').show();//Will be hidden by default.
		$('#analytics h3').show();
	} 
	else if (graphic == 'STABILITY') {
		$('#plot_slide').hide();
		// Hide the charts
		$('.plot_box').hide();
		$('.soil_plot').hide();
		// $('.plot_lai').show();//Will be hidden by default
		// $('.plot_temprain').show();
		$('.plot_pcew').hide();
		$('.rain_radiation').hide();
		//$('.plot_wagt').show();//Will be hidden by default.
		// $('.plot_rainsum').show();//This will be hiden by default
		// $('.plot_raincum').show();//Will be hidden by default.
		$('#analytics h3').hide();
	} 
	else if (graphic == 'SOIL') {
		$('#plot_slide').hide();
		$('#analytics h3').hide();
		$('.soil_plot').show();
		$('.plot_box').hide();
		$('.plot_lai').hide();
		// $('.plot_temprain').hide();
		$('.plot_pcew').hide();
		$('.rain_radiation').hide();
		$('.plot_wagt').hide();
		$('.plot_rainsum').hide();
		$('.plot_raincum').hide();
	} else if (graphic == 'CLIMATE') {
		$('#plot_slide').hide();
		$('#analytics h3').hide();
		$('.plot_box').hide();
		$('.plot_lai').hide();
		// $('.plot_temprain').hide();
		$('.plot_pcew').hide();
		$('.rain_radiation').show();
		$('.soil_plot').hide();
		$('.plot_wagt').hide();
		$('.plot_rainsum').hide();
		$('.plot_raincum').hide();
	}
}

function viewPlot() {

	$(window).resize(function() {
	    $("#dialog-plot").dialog("option", "width", '800');
	    $("#dialog-plot").dialog("option", "height", 'auto');
	});
	
	$('#dialog-plot').dialog({
		autoOpen : false,
		modal : true,
		title : tpeDialogTitle,
		// bgiframe : true,

		// width : $(window).width(),
		// height : $(window).height(),

		/*height : 'auto',*/
		height : 'auto',
		/*maxHeight: 600,
		maxWidth: 600,*/
		/*width : 'auto',*/
		 width : 800,
		autoResize: true,
		/* create: function( event, ui ) {
			    // Set maxWidth
			    $(this).css("maxWidth", "600px");
			  },*/

		// fullScreen: true,
		// fullScreenForce: true,

		draggable : true,
		resizable : false,
		// open : function() {
		// $(".ui-dialog-titlebar").hide();
		// },

		"buttons" : [ {
			id : "bttnClose",
			text : "Close",
			click : function() {
				$('#dialog-plot').dialog('close');
			}
		} ]
	});

	// For closing the dialog
	$('#close-button').click(function() {
		$('#dialog-plot').dialog('close');
	});

	// Hide the title bar of the dialog
	// $(".ui-widget-header").hide();
	$('.ui-dialog-titlebar').remove();
	// $(".ui-dialog-titlebar").hide();

}

/**
 * Displays the graphics info when the graphics controls are hovered over.
 * 
 */
function graphicsInfo(info, desc) {
	$('#info').show();
	// The info about what to view when the user clicks the control
	$('#info h2').text(info);
	// Add the description of the action
	$('#info span').html(desc);

	// $htmlText = $htmlText + '<div>Station No:'
	// + event.feature.getProperty('stationNumber') + '</div>';

	// return $htmlText;
}
 