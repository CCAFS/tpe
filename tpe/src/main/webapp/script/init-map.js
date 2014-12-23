//$(document)
//	.ready(

var dataJSON, categoriesJSON;

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
		mapTypeId : google.maps.MapTypeId.ROADMAP,
		draggableCursor : 'crosshair',
		draggableCursor : 'default'
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

	// Add the selected country states polygon features to the map.
	map.data.addGeoJson(data.statesGeoJson, {
		idPropertyName : "id"
	});
	// Add tpe boundary json data

	map.data.addGeoJson(data.tpeBoundaryJson, {
		idPropertyName : "id"
	});

	// Add the TPE for the selected country features to the map.
	map.data.addGeoJson(data.tpeGeoJson, {
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

		if ((feature.getProperty('name') == 'LFE')
				|| (feature.getProperty('name') == 'HFE')
				|| (feature.getProperty('name') == 'FE')) {

			return {
				visible : true,
				clickable : true,
				title : name,
				strokeWeight : 1,
				strokeColor : feature.getProperty('colour'),
				// strokeOpacity : 1,
				// fillOpacity : 1,
				fillColor : feature.getProperty('colour')
			};
		} else if (feature.getProperty('name') == 'TPE_BOUNDARY') {
			return {
				visible : true,
				clickable : true,
				title : name,
				strokeWeight : 2,
				strokeColor : "#000",
				fillOpacity : 0,
				// fillOpacity: 0.20,
				fillColor : "#ffffff"
			};
		}

		return {
			icon : {
				url : 'img/' + color + '.png'
			// url : "img/station_green.png"
			// url : "${ctx}/img/station_green.png"
			},
			visible : true,
			clickable : true,
			title : name,
			strokeWeight : 1,
			strokeColor : "#bdbdbd",
			// strokeColor : "#000000",
			// strokeOpacity : 0.5,
			fillOpacity : 0,
			// fillOpacity: 0.20,
			fillColor : "#ffffff"
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
				|| (e.feature.getProperty('featureType') == 'ENVIRONMENT')
				|| (e.feature.getProperty('featureType') == 'CLIMATE')) {
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
			var probJSON = dataJSON[e.feature.getProperty('code')];
			createSoilPlot(categoriesJSON, probJSON);
		} else if (e.feature.getProperty('featureType') == 'TPE') {
			// Display the TPE Box plot
			createTPEBoxPlot(categoriesJSON, dataJSON);
		}
	});

	/* Add mouse events to trigger the TPE feature info */
	map.data.addListener('mouseover', function(e) {
		e.feature.setProperty('selected', true);

		// Don't display the info window for the feature type country

		/*
		 * if ((e.feature.getProperty('featureType') == 'SOIL') ||
		 * (e.feature.getProperty('featureType') == 'STATION') ||
		 * (e.feature.getProperty('featureType') == 'ENVIRONMENT') ||
		 * (e.feature.getProperty('featureType') == 'CLIMATE')) {
		 */

		$('#info').show();
		$('#info h2').text(e.feature.getProperty('name'));
		// $('#info span').text(e.feature.getProperty('stationName'));
		$('#info span').html(featureInfo(e));
		/* } */
		// document.getElementById('info-box').textContent =
		// event.feature.j.NOMBRE_MPI;
		// map.data.revertStyle();
		if (e.feature.getProperty('name') == 'LFE') {
			map.data.overrideStyle(e.feature, {
				strokeColor : '#ff0000',
				fillOpacity : 0.5,
				fillColor : '#ff0000'
			});
		} else if (e.feature.getProperty('name') == 'HFE') {
			map.data.overrideStyle(e.feature, {
				strokeColor : '#00ff00',
				fillOpacity : 0.5,
				fillColor : '#00ff00'
			});
		} else if (e.feature.getProperty('name') == 'FE') {
			map.data.overrideStyle(e.feature, {
				strokeColor : '#0041a0',
				fillOpacity : 0.5,
				fillColor : '#0041a0'
			});
		}

		// Display the corresponding plot chart

		// TODO Add Soil chart
		if (e.feature.getProperty('featureType') == 'SOIL') {
			// Display the plot only for the soil point features
			var probJSON = dataJSON[e.feature.getProperty('code')];
			createSoilPlot(categoriesJSON, probJSON);
		} else if (e.feature.getProperty('featureType') == 'TPE') {

			console.log(categoriesJSON);
			console.log(dataJSON);

			// Display the TPE Box plot
			createTPEBoxPlot(categoriesJSON, dataJSON);
		}

		// TODO Add Climate chart
		else if (e.feature.getProperty('featureType') == 'CLIMATE') {
			// Display the plot only for the soil point features
			var plotJSON = e.feature.getProperty('plotData');
			createClimatePlot(plotJSON);
		}

	});
	map.data.addListener('mouseout', function(e) {
		e.feature.setProperty('selected', false);
		$('#info').hide();

		/*
		 * map.data.overrideStyle(e.feature, { strokeColor : '#990000',
		 * fillColor : '#009900' });
		 */

		if ((e.feature.getProperty('name') == 'LFE')
				|| (e.feature.getProperty('name') == 'HFE')
				|| (e.feature.getProperty('name') == 'FE')) {
			map.data.overrideStyle(e.feature, {
				strokeColor : e.feature.getProperty('colour'),
				fillOpacity : 0.20,
				fillColor : e.feature.getProperty('colour')
			});
		}

	});

}
/**
 * This function returns the feature info details
 */
function featureInfo(event) {

	var $htmlText = '';
	// If the station was clicked
	if (event.feature.getProperty('featureType') == 'STATION') {
		/*
		 * $htmlText = $htmlText + '<div>Station: '+
		 * event.feature.getProperty("stationName");
		 */

		$htmlText = $htmlText + '<div>Station No:'
				+ event.feature.getProperty('stationNumber') + '</div>';

		// TODO Add climate data into each station feature

	} else if (event.feature.getProperty('featureType') == 'SOIL') {
		// If the soil icon was clicked
		$htmlText = '<div>Region: ' + event.feature.getProperty("regionName");
		$htmlText = $htmlText + '</div><div>Station: '
				+ event.feature.getProperty("stationName");
		// Add the coordinates
		$htmlText = $htmlText + '</div><div>Point: '
				+ event.feature.getProperty("lat") + ','
				+ event.feature.getProperty("lng");
		// Add the soil properties
		// PH
		$htmlText = $htmlText + '</div><div>PH: '
				+ event.feature.getProperty("ph") + '</div>';
		// Depth
		$htmlText = $htmlText + '</div><div>Depth: '
				+ event.feature.getProperty("depth") + '</div>';
		// availableSoilWater
		$htmlText = $htmlText + '</div><div>Available Soil Water: '
				+ event.feature.getProperty("availableSoilWater") + '</div>';
		// Bulk Density
		$htmlText = $htmlText + '</div><div>Bulk Density: '
				+ event.feature.getProperty("bulkDensity") + '</div>';
		// Cation Exchange
		$htmlText = $htmlText + '</div><div>Cation Exchange: '
				+ event.feature.getProperty("cationExchange") + '</div>';
		// Organic Carbon
		$htmlText = $htmlText + '</div><div>Organic Carbon: '
				+ event.feature.getProperty("organicCarbon") + '</div>';
		// Organic Matter
		$htmlText = $htmlText + '</div><div>Organic Matter: '
				+ event.feature.getProperty("organicMatter") + '</div>';
		// Water Content Field Capacity
		$htmlText = $htmlText + '</div><div>Water Content Field Capacity: '
				+ event.feature.getProperty("waterContentFieldCapacity")
				+ '</div>';
		// Taxonomy
		$htmlText = $htmlText + '</div><div>Taxonomy: '
				+ event.feature.getProperty("taxonomy") + '</div>';
		// Water Capacity Wilt Point
		$htmlText = $htmlText + '</div><div>Water Capacity Wilt Point: '
				+ event.feature.getProperty("waterCapacityWiltPoint")
				+ '</div>';
	} else if (event.feature.getProperty('featureType') == 'CLIMATE') {
		/*
		 * $htmlText = $htmlText + '<div>Station: '+
		 * event.feature.getProperty("stationName");
		 */

		$htmlText = $htmlText + '<div>Station No:'
				+ event.feature.getProperty('stationNumber') + '</div>';
		// Add climate properties here
		// Min Temperature
		$htmlText = $htmlText + '</div><div>Min Temperature: '
				+ event.feature.getProperty("minT") + '</div>';
		// Max Temperature
		$htmlText = $htmlText + '</div><div>Max Temperature: '
				+ event.feature.getProperty("maxT") + '</div>';
		// Precipitation
		$htmlText = $htmlText + '</div><div>Precipitation: '
				+ event.feature.getProperty("precipitation") + '</div>';
		// Radiation
		$htmlText = $htmlText + '</div><div>Radiation: '
				+ event.feature.getProperty("radiation") + '</div>';

	} else {
		// If the Country or State region was clicked.
		$htmlText = '<div>Region: ' + event.feature.getProperty('name')
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
			dataJSON = dataJson.dataJson;

			// console.log(dataJson.probabilities);
			categoriesJSON = dataJson.categories;

			initializeMap(dataJson);
		}
	});

}

// Create the climate plot chart
function createClimatePlot(seriesJSON) {
	$('#env_container').highcharts({
		chart : {
			type : 'column'
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
			text : 'Environment Sensibility'
		},
		subtitle : {
			text : 'Source: <a href="http://www.ccafs.org">CCAFS</a>'
		},
		xAxis : {
			type : 'category',
			labels : {
				rotation : -45,
				style : {
					fontSize : '12px',
					fontFamily : 'Verdana, sans-serif'
				}
			}
		},
		yAxis : {
			min : 0,
			title : {
				text : '% of R2'
			}
		},
		legend : {
			enabled : false
		},
		tooltip : {
			pointFormat : 'Environment Sensibility: <b>{point.y:.1f} %</b>'
		},
		series : seriesJSON
	});
}

// Create the SOIL chart plot
function createSoilPlot(categoriesJSON, seriesJSON) {

	$('#env_container')
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

// Create Box Plot
function createTPEBoxPlot(cats, ser) {

	$('#env_container').highcharts({
		chart : {
			type : 'boxplot'
		},

		title : {
			text : 'TPE Box Plot'
		},

		legend : {
		 enabled: false
		},

		xAxis : [{
			categories : ser,
			offset : 0,
			gridLineWidth : 1,
//			width : 200,
			title : {
				text : 'Harvest Years'
			}
//			left : 324
		} ],

		yAxis : {
			title : {
				text : 'Yield (Kg/ha)'
			},
			plotLines : [ {
				value : 932,
				color : 'red',
				width : 1,
				label : {
					text : 'Theoretical mean: 932',
					align : 'center',
					style : {
						color : 'gray'
					}
				}
			} ]
		},

		series : cats

	});
}
