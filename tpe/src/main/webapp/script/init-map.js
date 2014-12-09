//$(document)
//	.ready(

var probabilitiesJSON, categoriesJSON;

/**
 * Depending on the selected OUTPUT text, get the corresponding selected params.
 * For SOIL
 * (selectedTexture,selectedProperties,selectedCoutry,selectedSubregions) For
 * TPE (selectedCrop selectedCultivar, selectedCoutry, selectedYears,
 * selectedSowingWindow) For CLIMATE (selectedProperties, selectedCountry,
 * selectedSubregions, selectedYears, selectedStations)
 */
function initializeGoogleMap() {
	// console.log('Initializing the Google Map....');
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
	var selectedOutput;
	var output = document.getElementById('select_output');
	selectedOutput = output.options[output.selectedIndex].text;

	// console.log('Out put: ' + selectedOutput.toUpperCase());
	// The actions
	var soilGeoJsonAction = 'soilGeoJson.geojson';
	var tpeGeoJsonAction = 'tpeGeoJson.geojson';
	var climateGeoJsonAction = 'climateGeoJson.geojson';
	var params, actionJson;

	// Use the switch statement to determine the selected output, from the first
	// select drop down box
	// TPE, SOIL or CLIMATE
	switch (selectedOutput.toUpperCase()) {
	case 'TPE':
		// Selected TPE. The get the
		// corresponding selected TPE
		// params

		// The action url for the soil map
		actionJson = 'tpeGeoJson.geojson';
		// Call the geoJsonData function and pass the params y action
		geoJsonData(actionJson);
		// initializeMap();
		break;

	case 'SOIL':

		// The action url for the soil map
		actionJson = 'soilGeoJson.geojson';
		// Call the geoJsonData function and pass the params y action
		geoJsonData(actionJson);

		break;

	case 'CLIMATE':

		// The action url for the soil map
		actionJson = 'climateGeoJson.geojson';
		// Call the geoJsonData function and pass the params y action
		geoJsonData(actionJson);
		// initializeMap();
		break;
	default:
		break;
	}

}

/**
 * The function that initializes the Google Map
 */
function initializeMap(data) {

	createSoilPlot();

	// var mapStyle = [ ];

	// Default map properties (BRAZIL)
	// var defaultLatLng = new google.maps.LatLng(-14.235004, -51.92528);

	// Default properties for Colombia
	// new google.maps.LatLng(4.214943141390651, -73.828125)
	// var defaultLatLng = new
	// google.maps.LatLng(4.214943141390651, -73.828125);

	// Initialise the map using the selected country coordinates
	var defaultLatLng = new google.maps.LatLng(data.lat, data.lng);

	// The default zoom level of the map.
	// var defaultZoom = 4;
	// It is assumed that maps for different countries will have different
	// default zoom. Such as Brazil =4 and Colombia =6
	var defaultZoom = data.zoom;
	// Variable for TPE map
	var map;
	// Google map options
	var mapOptions = {
		zoom : defaultZoom,
		center : defaultLatLng,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	}

	// Create the new map and make sure the tpe_map div
	// exists
	map = new google.maps.Map(document.getElementById('tpe_map'), mapOptions);

	// Add markers or features
	// map.data.loadGeoJson('', {idPropertyName : 'STATE'});
	// Load the JSON to show places
	// map.data.loadGeoJson(markersAction,{idPropertyName:'id'});

	// Load GeoJSON.
	/*
	 * var stations = $.getJSON(markersAction); //same as
	 * map.data.loadGeoJson(); stations.then(function(data) { for (i in data) {
	 * console.log(i + ':' + data[i]) map.data.addGeoJson(data[i], {
	 * idPropertyName : "stationId" }); } });
	 */

	// Add the GeoJson features to the map
	map.data.addGeoJson(data.geoJson, {
		idPropertyName : "id"
	});
	// console.log(data.geoJson);
	// Add the selected country polygon feature to the map.
	map.data.addGeoJson(data.countryGeoJson, {
		idPropertyName : "id"
	});
	/*
	 * $.getJSON(markersAction, function(data) { console.log(data.soilGeoJson)
	 * console.log(data.countryGeoJson) map.data.addGeoJson(data.soilGeoJson, {
	 * idPropertyName : "id" }); // Add the country polygons
	 * map.data.addGeoJson(data.countryGeoJson, { idPropertyName : "id" }); });
	 */

	/*
	 * $.getJSON(markersAction, function(data) { $.each(data.stations,
	 * function(i, item) { console.log(i + ':' + item) map.data.addGeoJson(item, {
	 * idPropertyName : "stationId" }); }); });
	 */
	// Style the station markers
	map.data.setStyle(function(feature) {
		var name = feature.getProperty('name');
		var color = feature.getProperty('color');
		return {
			icon : {
				url : 'img/' + color + '.png'
			// url : "img/station_green.png"
			// url : "${ctx}/img/station_green.png"
			},
			visible : true,
			clickable : true,
			title : name,
			strokeWeight : 2,
			strokeColor : "#990000",
			// strokeOpacity : 0.5,
			// fillOpacity: 0.20,
			fillColor : "#009900"
		};
	});
	var colorValues = [ "red", // 1
	"blue", // 2
	"green", // 3
	"brown", // 4
	"purple", // 5
	"pink", "black", "orange" ];

	// get the legend container, create a legend, add a legend renderer fn
	var $legendContainer = $('#legend-container'), $legend = $(
			'<div id="legend">').appendTo($legendContainer), renderLegend = function(
			colorValuesArray) {
		$legend.empty();
		$.each(colorValuesArray, function(index, val) {
			var texture;
			if (val == 'red') {
				texture = 'Sand';
			} else if (val == 'blue') {
				texture = 'Loam';
			} else if (val == 'green') {
				texture = 'Clay';
			} else if (val == 'brown') {
				texture = 'Sand Loam';
			} else if (val == 'purple') {
				texture = 'Clay Loam';
			} else if (val == 'orange') {
				texture = 'Clay Sand';
			} else if (val == 'pink') {
				texture = 'Sand Clay Loam';
			} else if (val == 'black') {
				texture = 'Station';
			}

			var $div = $('<div style="height:25px;">').append(
					$('<div class="legend-color-box">').css({
						backgroundColor : val,
					})).append($('<div class="legend_text">').html(texture));

			$legend.append($div);
		});
	}

	// make a legend for the first time
	renderLegend(colorValues);

	// add the legend to the map
	map.controls[google.maps.ControlPosition.LEFT_BOTTOM]
			.push($legendContainer[0]);

	var infoWindow = new google.maps.InfoWindow({
		content : ""
	});

	// listen for click events
	map.data.addListener('click', function(e) {

		// show an infowindow on click
		// infowindow.setContent("<div
		// style='width:150px; text-align:
		// center;'>"+myHTML+"</div>");
		// Show the info window only for other features but not country
		if ((e.feature.getProperty('featureType') == 'SOIL')
				|| (e.feature.getProperty('featureType') == 'STATION')
				|| (e.feature.getProperty('featureType') == 'ENVIRONMENT')) {
			infoWindow.setContent('<div class="info_window">' + '<h2>'
					+ e.feature.getProperty('name') + '</h2>' + featureInfo(e)
					+ '</div>');
			var anchor = new google.maps.MVCObject();
			anchor.set("position", e.latLng);
			infoWindow.open(map, anchor);

		}

		// TODO Create the plot
		if (e.feature.getProperty('featureType') == 'SOIL') {
			// Display the plot only for the soil feature
			var probJSON = probabilitiesJSON[e.feature.getProperty('code')];
			createSoilPlot(categoriesJSON, probJSON);
		}
	});

	/* Add mouse events to trigger the TPE feature info */
	map.data.addListener('mouseover', function(e) {
		e.feature.setProperty('selected', true);

		// Don't display the info window for the feature type country
		if ((e.feature.getProperty('featureType') == 'SOIL')
				|| (e.feature.getProperty('featureType') == 'STATION')
				|| (e.feature.getProperty('featureType') == 'ENVIRONMENT')) {
			$('#info').show();
			$('#info h2').text(e.feature.getProperty('name'));
			// $('#info span').text(e.feature.getProperty('stationName'));
			$('#info span').html(featureInfo(e));
		}
		// document.getElementById('info-box').textContent =
		// event.feature.j.NOMBRE_MPI;
		// map.data.revertStyle();
		map.data.overrideStyle(e.feature, {
			strokeColor : 'green',
			fillColor : 'red'
		});

		// Display the corresponding plot chart

		// TODO Add chart

		if (e.feature.getProperty('featureType') == 'SOIL') {
			// Display the plot only for the soil point features
			var probJSON = probabilitiesJSON[e.feature.getProperty('code')];
			createSoilPlot(categoriesJSON, probJSON);
		}

	});
	map.data.addListener('mouseout', function(e) {
		e.feature.setProperty('selected', false);
		$('#info').hide();
		map.data.overrideStyle(e.feature, {
			strokeColor : '#990000',
			fillColor : '#009900'
		});
	});

}
/**
 * This function returns the feature info details
 */
function featureInfo(event) {

	var $htmlText = '';
	// If the station was clicked
	if (event.feature.getProperty('featureType') == 'STATION') {
		$htmlText = $htmlText + '<div>Station: '
				+ event.feature.getProperty("stationName");
		$htmlText = $htmlText + '</div><div>Number:'
				+ event.feature.getProperty('stationNumber') + '</div>';

		// TODO Add climate data into each station feature

	} else if (event.feature.getProperty('featureType') == 'SOIL') {
		// If the soil icon was clicked
		$htmlText = '<div>Region: ' + event.feature.getProperty("regionName");
		$htmlText = $htmlText + '</div><div>Station: '
				+ event.feature.getProperty("stationName");
		$htmlText = $htmlText + '</div><div>Texture: '
				+ event.feature.getProperty("soilName");

		$htmlText = $htmlText + '</div><div>Point: '
				+ event.feature.getProperty("lat") + ','
				+ event.feature.getProperty("lng");

		$htmlText = $htmlText + '</div><div>'
				+ event.feature.getProperty("soilPropertyName") + ': '
				+ event.feature.getProperty("soilPropertyValue") + '</div>';
	} else {
		// If the Country or State region was clicked.
		$htmlText = '<div>Region: ' + event.feature.getProperty('regionName')
				+ '</div>';
		// TODO Add more details
	}

	return $htmlText;
}
/**
 * This action retrieves the Geo Json data and then calls the initializeMap()
 * function.
 */
function geoJsonData(action) {

	// console.log(parameters);
	$.ajax({
		type : "GET",
		async : false,// thats the trick
		url : action,
		data : $('#tpe_index').serialize(),
		dataType : "json",
		success : function(dataJson) { //
			// var columnData = null, columnNames = result.colNames;
			// console.log(dataJson.geoJson);
			// console.log(dataJson.countryGeoJson);

			// probabilitiesJSON = dataJson.data;
			probabilitiesJSON = dataJson.probabilities;

			// console.log(dataJson.probabilities);
			categoriesJSON = dataJson.categories;

			initializeMap(dataJson);
		}
	});

}

// Create the chart plot

function createSoilPlot(categoriesJSON, seriesJSON) {

	$('#env_soil_container')
			.highcharts(
					{
						chart : {
							type : 'column',
							marginTop : 70,
							marginLeft : 65,

						},
						credits : {
							enabled : true,
							text : 'Source: CCAFS TPE (www.ccafs.org)',
							href : 'http://www.ccafs.org',
							style : {
								color : '#4e2700',
								fontWeight : 'bold',
								fontSize : '10px',
							}
						},
						title : {
							text : 'Environment Soil',
							style : {
								color : '#4e2700',
								fontWeight : 'bold',
								fontSize : '12px',
								// backgroundColor : 'green',
								border : '1px solid black'
							}
						},
						xAxis : {
							categories : categoriesJSON,
							labels : {
								rotation : -45,
								style : {
									fontSize : '10px',
									fontFamily : 'Verdana, sans-serif'
								}
							},
							title : {
								text : 'Planting Dates'
							},
						},
						yAxis : {
							min : 0,
							title : {
								text : 'Probabiliti of Occurance'
							},
							stackLabels : {
								enabled : false
							},
							labels : {
								format : '{value}%'
							},
							floor : 0,
							ceiling : 100
						},
						legend : {

							itemStyle : {
								color : '#000000',
								// fontWeight : 'bold',
								fontSize : '10px'
							},

							align : 'right',
							// x : -70,
							verticalAlign : 'top',
							y : 22,
							floating : true,
							backgroundColor : (Highcharts.theme && Highcharts.theme.background2)
									|| 'white',
							borderColor : '#CCC',
							borderWidth : 1,
							shadow : false
						},
						tooltip : {
							formatter : function() {
								return '<b>' + this.series.name + ': ' + this.y
										+ '</b>' + '<br/><b>Planting Date: '
										+ this.x + '</b>'

							}
						},
						plotOptions : {
							column : {
								stacking : 'normal',
								dataLabels : {
									enabled : false
								}
							}
						},
						series : seriesJSON
					});

}
