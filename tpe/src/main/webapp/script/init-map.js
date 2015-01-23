//$(document)
//	.ready(

var dataJSON, categoriesJSON, seriesTempRain, categoriesTempRain, tpeDialogTitle = 'CCAFS TPE Graphics', legendTitle;
var hfeSeries, lfeSeries, feSeries, boxJSON, climateSeriesJSON, clickedFeature, currentOutput, clickedFeatureName, selectedFeature, selectedOutput;

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
	// var selectedOutput;
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
		// Set the legend title for TPE
		legendTitle = 'Legend: TPE Environments';
		hideShow('TPE');
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
		legendTitle = 'Legend: Soil Texture';
		hideShow('SOIL');
		// The action url for the soil map
		actionJson = 'soilGeoJson.geojson';
		// Call the geoJsonData function and pass the params y action
		geoJsonData(actionJson);

		break;

	case 'CLIMATE':
		legendTitle = 'Legend: Climate';
		hideShow('CLIMATE');
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

	/*
	 * $('#zoomImage').hide(); $('#container').mouseenter(function(e) {
	 * $('#zoomImage').show(); }).mouseleave(function(e) {
	 * $('#zoomImage').hide(); });
	 */

	viewPlot();
	// dialogBoxPlot(categoriesJSON, dataJSON);

	// createSoilPlot();

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
				fillColor : feature.getProperty('colour'),
				zIndex : 1000
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
				url : 'img/' + color + '.png',
				size : new google.maps.Size(10, 10),
				scaledSize : new google.maps.Size(10, 10)
			// origin : new google.maps.Point(0, 0),
			// anchor : new google.maps.Point(0, 0)
			// url : "img/station_green.png"
			// url : "${ctx}/img/station_green.png"
			},
			visible : true,
			clickable : true,
			title : name,
			strokeWeight : 1,
			// strokeColor : "#bdbdbd",
			strokeColor : "#000000",
			// strokeOpacity : 0.5,
			fillOpacity : 0,
			// fillOpacity: 0.20,
			fillColor : "#ffffff"
		};
	});
	var colorValues = [ "red", "blue", "green", "brown", "purple", "pink",
			"orange" ], colorValuesTPE = [ "red", "blue", "green" ], colorValuesClimate = [
			"blue", "black" ], colors;

	// get the legend container, create a legend, add a legend renderer fn
	// var $legendContainer = $('#legend-container')
	var $legendContainer = $('<div id="legend-container"><h3>' + legendTitle
			+ '</h3></div>'), $legend = $('<div id="legend">').appendTo(
			$legendContainer), renderLegend = function(colorValuesArray) {
		$legend.empty();

		if (selectedOutput.toUpperCase() == 'SOIL') {
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
				}
				// else if (val == 'black') {
				// texture = 'Station';
				// }

				var $div = $('<div style="height:25px;">').append(
						$('<div class="legend-color-box">').css({
							backgroundColor : val,
						}))
						.append($('<div class="legend_text">').html(texture));

				$legend.append($div);
			});
			// renderLegend(colorValues);
		} else if (selectedOutput.toUpperCase() == 'TPE') {
			$.each(colorValuesArray, function(index, val) {
				var env_col;
				if (val == 'red') {
					env_col = 'LFE: Low Favourable Environment';
				} else if (val == 'blue') {
					env_col = 'FE: Favourable Environment';
				} else if (val == 'green') {
					env_col = 'HFE: High Favourable Environment';
				}

				var $div = $('<div style="height:25px;">').append(
						$('<div class="legend-color-box">').css({
							backgroundColor : val,
						}))
						.append($('<div class="legend_text">').html(env_col));

				$legend.append($div);
			});
			// renderLegend(colorValuesTPE);
		} else if (selectedOutput.toUpperCase() == 'CLIMATE') {
			$.each(colorValuesArray, function(index, val) {
				var climate_feature;
				if (val == 'black') {
					climate_feature = 'Station';
				} else if (val == 'blue') {
					climate_feature = 'Station Region';
				}

				var $div = $('<div style="height:25px;">').append(
						$('<div class="legend-color-box">').css({
							backgroundColor : val,
						})).append(
						$('<div class="legend_text">').html(climate_feature));

				$legend.append($div);
			});
		}
	}

	if (selectedOutput.toUpperCase() == 'SOIL')
		renderLegend(colorValues);
	else if (selectedOutput.toUpperCase() == 'TPE')
		renderLegend(colorValuesTPE);
	else if (selectedOutput.toUpperCase() == 'CLIMATE')
		renderLegend(colorValuesClimate);
	// make a legend for the first time

	// renderLegend(colorValues);
	// Remove the control first if it exists.
	// map.controls[google.maps.ControlPosition.LEFT_BOTTOM].removeAt(0);
	// map.controls[google.maps.ControlPosition.LEFT_BOTTOM].clear();

	// var indexOfControl = null,
	// bottomCenterControls =
	// map.controls[google.maps.ControlPosition.LEFT_BOTTOM];
	// bottomCenterControls.forEach( function ( element,
	// index ) {
	// if( element.id == 'legend' ) {
	// indexOfControl = index; console.log(index);
	// }
	// } );
	//	
	// if( indexOfControl ) {
	// bottomCenterControls.removeAt( indexOfControl );
	// }

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
			currentOutput = 'SOIL';
			// Display the plot only for the soil feature
			var probJSON = dataJSON[e.feature.getProperty('code')];
			// createSoilPlot(categoriesJSON, probJSON, true);
		} else if (e.feature.getProperty('featureType') == 'TPE') {
			// Display the TPE Box plot
			createTPEBoxPlot(categoriesJSON, boxJSON, true);

			// createLAIPlot(categoriesList, seriesLai, plotBands);
			// createPCEWPlot(categoriesList, seriesPcew, plotBands);
			// createTempRainPlot(categoriesTempRain, seriesTempRain, true);
			var environmentSeries;
			currentOutput = 'TPE';
			if (e.feature.getProperty('name') == 'HFE') {
				environmentSeries = hfeSeries;
				clickedFeature = 'HFE';
			} else if (e.feature.getProperty('name') == 'LFE') {
				environmentSeries = lfeSeries;
				clickedFeature = 'LFE';
			} else if (e.feature.getProperty('name') == 'FE') {
				environmentSeries = feSeries;
				clickedFeature = 'FE';
			}
			// Iterate the list of series map
			$.each(environmentSeries,
					function(indx, seriesMap) {

						if (seriesMap.type == 'LAI') {
							// Create LAI Plot
							plotLAI(seriesMap, e.feature.getProperty('name'),
									true);
						} else if (seriesMap.type == 'PCEW') {
							plotPCEW(seriesMap, e.feature.getProperty('name'),
									true);
						} else if (seriesMap.type == 'WAGT') {
							plotWAGT(seriesMap, e.feature.getProperty('name'),
									true);
						} else if (seriesMap.type == 'RAINSUM') {
							plotRAIN(seriesMap, e.feature.getProperty('name'),
									true);
						} else if (seriesMap.type == 'RAINCUM') {
							plotRAINCUM(seriesMap, e.feature
									.getProperty('name'), true);
						}
					});

		} else if (e.feature.getProperty('featureType') == 'CLIMATE') {
			currentOutput = 'CLIMATE';
			// Display the plot only for the soil point features
			// var plotJSON = e.feature.getProperty('plotData');
			// createClimatePlot(plotJSON, true);

			// Create rainfall and radiation plot
			rainfallRadiationPlot(climateSeriesJSON, true, e.feature
					.getProperty('stationId'), e.feature.getProperty('name'));
			clickedFeature = e.feature.getProperty('stationId');
			clickedFeatureName = e.feature.getProperty('name');
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

		if (e.feature.getProperty('name') == 'TPE_BOUNDARY') {

			// console.log(e.feature.getProperty('name'));
		} else if (e.feature.getProperty('name') == 'Brazil') {

			// console.log(e.feature.getProperty('name'));
		} else if (e.feature.getProperty('name') == 'Colombia') {

			// console.log(e.feature.getProperty('name'));
		} else {

			$('#info').show();
			$('#info h2').text(e.feature.getProperty('name'));
			// $('#info span').text(e.feature.getProperty('stationName'));
			$('#info span').html(featureInfo(e));
		}
		/* } */
		// document.getElementById('info-box').textContent =
		// event.feature.j.NOMBRE_MPI;
		// map.data.revertStyle();
		if (e.feature.getProperty('name') == 'LFE') {
			// selectedFeature = e;
			map.data.overrideStyle(e.feature, {
				strokeColor : '#ff0000',
				fillOpacity : 0.5,
				fillColor : '#ff0000'
			});
		} else if (e.feature.getProperty('name') == 'HFE') {
			// selectedFeature = e;
			map.data.overrideStyle(e.feature, {
				strokeColor : '#00ff00',
				fillOpacity : 0.5,
				fillColor : '#00ff00'
			});
		} else if (e.feature.getProperty('name') == 'FE') {
			// selectedFeature = e;
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
			createSoilPlot(categoriesJSON, probJSON, true);
		} else if (e.feature.getProperty('featureType') == 'TPE') {
			// hideShow('TPE');
			// console.log(categoriesJSON);
			// console.log(dataJSON);

			// Display the TPE Box plot
			createTPEBoxPlot(categoriesJSON, boxJSON, true);

			// createLAIPlot(categoriesList, seriesLai, plotBands);
			// createPCEWPlot(categoriesList, seriesPcew, plotBands);
			// createTempRainPlot(categoriesTempRain, seriesTempRain, true);

			// Add plots using series data
			var environmentSeries;
			if (e.feature.getProperty('name') == 'HFE') {
				environmentSeries = hfeSeries;
				// console.log(hfeSeries);
			} else if (e.feature.getProperty('name') == 'LFE') {
				environmentSeries = lfeSeries;
			} else if (e.feature.getProperty('name') == 'FE') {
				environmentSeries = feSeries;
				// console.log(feSeries);
			}
			// Iterate the list of series map
			$.each(environmentSeries,
					function(indx, seriesMap) {

						if (seriesMap.type == 'LAI') {
							// Create LAI Plot
							plotLAI(seriesMap, e.feature.getProperty('name'),
									true);
						} else if (seriesMap.type == 'PCEW') {
							plotPCEW(seriesMap, e.feature.getProperty('name'),
									true);
						} else if (seriesMap.type == 'WAGT') {
							plotWAGT(seriesMap, e.feature.getProperty('name'),
									true);
						} else if (seriesMap.type == 'RAINSUM') {
							plotRAIN(seriesMap, e.feature.getProperty('name'),
									true);
						} else if (seriesMap.type == 'RAINCUM') {
							plotRAINCUM(seriesMap, e.feature
									.getProperty('name'), true);
						}
					});

		}

		// TODO Add Climate chart
		else if (e.feature.getProperty('featureType') == 'CLIMATE') {
			// Display the plot only for the soil point features
			// var plotJSON = e.feature.getProperty('plotData');
			// createClimatePlot(plotJSON, true);

			// Create rainfall and radiation plot
			rainfallRadiationPlot(climateSeriesJSON, true, e.feature
					.getProperty('stationId'), e.feature.getProperty('name'));
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

		// Dynamically show the graphics using the last clicked TPE id
		if (currentOutput == 'TPE') {
			// selectedFeature.feature.setProperty('selected', true);
			//			
			// if ((selectedFeature.feature.getProperty('name') == 'LFE')
			// || (selectedFeature.feature.getProperty('name') == 'HFE')
			// || (selectedFeature.feature.getProperty('name') == 'FE')) {
			// map.data.overrideStyle(selectedFeature.feature, {
			// strokeColor : selectedFeature.feature.getProperty('colour'),
			// fillOpacity : 0.5,
			// fillColor : selectedFeature.feature.getProperty('colour')
			// });
			// }

			var lastSeries;
			if (clickedFeature == 'HFE')
				lastSeries = hfeSeries;
			else if (clickedFeature == 'LFE')
				lastSeries = lfeSeries;
			else if (clickedFeature == 'FE')
				lastSeries = feSeries;

			if (e.feature.getProperty('featureType') != 'TPE') {
				// Iterate the list of series map
				$.each(lastSeries,
						function(indx, seriesMap) {

							if (seriesMap.type == 'LAI') {
								// Create LAI Plot
								plotLAI(seriesMap, e.feature
										.getProperty('name'), true);
							} else if (seriesMap.type == 'PCEW') {
								plotPCEW(seriesMap, e.feature
										.getProperty('name'), true);
							} else if (seriesMap.type == 'WAGT') {
								plotWAGT(seriesMap, e.feature
										.getProperty('name'), true);
							} else if (seriesMap.type == 'RAINSUM') {
								plotRAIN(seriesMap, e.feature
										.getProperty('name'), true);
							} else if (seriesMap.type == 'RAINCUM') {
								plotRAINCUM(seriesMap, e.feature
										.getProperty('name'), true);
							}
						});
			}
		} else if (currentOutput == 'CLIMATE') {

			rainfallRadiationPlot(climateSeriesJSON, true, clickedFeature,
					clickedFeatureName);
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
		$htmlText = $htmlText + '<div>Depth: '
				+ event.feature.getProperty("depth") + '</div>';
		// availableSoilWater
		$htmlText = $htmlText + '<div>Available Soil Water: '
				+ event.feature.getProperty("availableSoilWater") + '</div>';
		// Bulk Density
		$htmlText = $htmlText + '<div>Bulk Density: '
				+ event.feature.getProperty("bulkDensity") + '</div>';
		// Cation Exchange
		$htmlText = $htmlText + '<div>Cation Exchange: '
				+ event.feature.getProperty("cationExchange") + '</div>';
		// Organic Carbon
		$htmlText = $htmlText + '<div>Organic Carbon: '
				+ event.feature.getProperty("organicCarbon") + '</div>';
		// Organic Matter
		$htmlText = $htmlText + '<div>Organic Matter: '
				+ event.feature.getProperty("organicMatter") + '</div>';
		// Water Content Field Capacity
		$htmlText = $htmlText + '<div>Water Content Field Capacity: '
				+ event.feature.getProperty("waterContentFieldCapacity")
				+ '</div>';
		// Taxonomy
		$htmlText = $htmlText + '<div>Taxonomy: '
				+ event.feature.getProperty("taxonomy") + '</div>';
		// Water Capacity Wilt Point
		$htmlText = $htmlText + '<div>Water Capacity Wilt Point: '
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
		$htmlText = $htmlText + '<div>Min Temperature: '
				+ event.feature.getProperty("minT") + '</div>';
		// Max Temperature
		$htmlText = $htmlText + '<div>Max Temperature: '
				+ event.feature.getProperty("maxT") + '</div>';
		// Precipitation
		$htmlText = $htmlText + '<div>Precipitation: '
				+ event.feature.getProperty("precipitation") + '</div>';
		// Radiation
		$htmlText = $htmlText + '<div>Radiation: '
				+ event.feature.getProperty("radiation") + '</div>';

	} else if (event.feature.getProperty('featureType') == 'TPE') {

		$htmlText = '<div style="color:' + event.feature.getProperty('colour')
				+ '";">' + event.feature.getProperty('description') + '</div>';

		$htmlText = $htmlText + '<div>Crop: '
				+ event.feature.getProperty("crop") + '</div>';

		$htmlText = $htmlText + '<div>Cultivar: '
				+ event.feature.getProperty("cultivar") + '</div>';
	}

	else {
		// If the Country or State region was clicked.
		// $htmlText = '<div>Region: ' + event.feature.getProperty('name') +
		// '</div>';
		// TODO Add more details
	}

	return $htmlText;
}
/**
 * This action retrieves the Geo Json data and then calls the initializeMap()
 * function.
 */
function geoJsonData(action) {
	// Hide the charts
	// $('#env_container').hide();
	// $('#plot_lai').hide();
	// $('#plot_temprain').hide();
	// $('#plot_pcew').hide();
	// $('#rain_radiation').hide();

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

			boxJSON = dataJson.boxplotData;

			// console.log(dataJson.probabilities);
			categoriesJSON = dataJson.categories;

			climateSeriesJSON = dataJson.seriesData;
			// categoriesList = dataJson.categoriesList;
			// plotBands = dataJson.plotBands;
			// seriesLai = dataJson.seriesLai;
			// seriesPcew = dataJson.seriesPcew;
			// seriesRainCum = dataJson.seriesRainCum;
			// seriesTempRain = dataJson.seriesTempRain;
			// categoriesTempRain = dataJson.categoriesTempRain;
			var seriesDataMap = dataJson.seriesData;
			// var hfeSeries, lfeSeries, feSeries;
			// console.log(seriesDataMap);
			if (seriesDataMap != null)
				$.each(seriesDataMap, function(envKey, listOfSeriesMap) {
					// console.log(envKey);
					// console.log(listOfSeriesMap);
					if (envKey == 'HFE') {
						hfeSeries = listOfSeriesMap;
						// console.log(listOfSeriesMap);
					} else if (envKey == 'LFE') {
						lfeSeries = listOfSeriesMap;
					} else if (envKey == 'FE') {
						feSeries = listOfSeriesMap;
					}
				});

			initializeMap(dataJson);
		}
	});

}

// Create the climate plot chart
function createClimatePlot(seriesJSON, smallPlot) {

	// $('#rain_radiation').show();
	var renderTo = 'rain_radiation';// Default div
	var dialogDiv = 'dialog-chart';
	var fontSize = '8px';
	var titleFontSize = '10px;'
	var legendX, legendY, width, height;
	var marginBottom, marginTop, marginLeft, marginRight, spacingBottom;
	// Set the div where to render the plot
	if (smallPlot) {
		// Dialog div
		renderTo = 'env_container';
		// Chart font size
		fontSize = '8px';
		titleFontSize = '10px';
		legendX = 0;
		legendY = 25;
		marginBottom: 35;
		marginTop: 10;
		marginLeft: 60;
		marginRight: 10;
		spacingBottom = 35;
		width = 241;
		height = 200;
	} else {
		// Jsp page div
		renderTo = dialogDiv;
		// Chart font size for the dialog chart
		fontSize = '14px';
		titleFontSize = '30px';
		legendX = 120;
		legendY = 20;
		spacingBottom = 50;
		width = null;
		height = null;
	}
	$('#' + renderTo)
			.highcharts(
					{
						chart : {
							width : width,
							height : height,
							type : 'column',
							/*
							 * style : { fontFamily : 'serif', fontSize : '8px' },
							 * marginBottom : 60, marginTop : 10, marginLeft :
							 * 60, marginRight : 10
							 */
							style : {
								fontFamily : 'serif',
								fontSize : fontSize
							},
							plotBorderWidth : 1,
							// spacingTop:2,
							// spacingRight:5,
							spacingBottom : spacingBottom,
							// spacingLeft:2,
							borderWidth : 1,
							borderRadius : 5,
							borderColor : '#999',
							// margin: [15,6,15,15],
							marginBottom : marginBottom,
							marginTop : marginTop,
							marginLeft : marginLeft,
							marginRight : marginRight,

							events : {
								load : function() {
									if (smallPlot) {

										this.renderer.image('img/zoom-in.png',
												10, 2, 20, 20).on(
												'click',
												function() {
													createClimatePlot(
															seriesJSON, false);
													$('#dialog-plot').dialog(
															'open');
												}).css({
											cursor : 'Pointer'
										}).css({
											position : 'relative',
											"margin-left" : "-90px"
										// opacity : 0.75
										}).attr({
											zIndex : 300,
											id : 'zoomImage'
										}).add();
									} else {
										this.renderer
												.image('img/ccafs_logo.png',
														90, 0, 120, 50)
												.on(
														'click',
														function() {
															// Add CCAFS Link
															location.href = 'http://www.ccafs-tpe.org'
														})

												.css({
													cursor : 'Pointer'
												}).css({
													position : 'relative',
													"margin-left" : "-90px"
												// opacity : 0.75
												}).attr({
													zIndex : 100
												}).add();
									}
								},

								click : function() {
									// If the small graphic was clicked on
									if (smallPlot) {
										createClimatePlot(seriesJSON, false);
										// $('#report').html('click on title');
										$('#dialog-plot').dialog('open');
									}

								}
							}
						},
						credits : {
							enabled : true,
							text : 'Source: CCAFS TPE (www.ccafs.org)',
							href : 'http://www.ccafs.org',
							style : {
								color : '#4e2700',
								fontWeight : 'bold',
								fontSize : fontSize
							}
						},
						title : {
							text : 'Environment Sensibility',
							style : {
								color : '#4e2700',
								// fontWeight : 'bold',
								fontSize : fontSize
							}
						},
						subtitle : {
							text : 'Source: <a href="http://www.ccafs.org">CCAFS</a>'
						},
						xAxis : {
							type : 'category',
							labels : {
								rotation : -45,
								style : {
									fontSize : fontSize,
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
						/*
						 * legend : { enabled : false },
						 */
						legend : {

							itemStyle : {
								color : '#000000',
								// fontWeight : 'bold',
								fontSize : fontSize
							},
							// itemWidth : 60,
							align : 'left',
							x : 0,
							verticalAlign : 'bottom',
							y : legendY,
							floating : true,
							backgroundColor : (Highcharts.theme && Highcharts.theme.background2)
									|| 'white',
							borderColor : '#CCC',
							// borderWidth : 1,
							shadow : false
						},
						tooltip : {
							pointFormat : 'Environment Sensibility: <b>{point.y:.1f} %</b>'
						},
						series : seriesJSON,
						exporting : {
							buttons : {
								contextButton : {
									// symbol: 'circle',
									symbol : 'url(img/download_32.png)',
								// symbolStrokeWidth : 1,
								// symbolFill : '#bada55',
								// symbolStroke : '#330033',
								// symbolX : 6,
								// symbolY : 6
								}
							}
						}
					});
}

// Create the SOIL chart plot
function createSoilPlot(categoriesJSON, seriesJSON, smallPlot) {
	// $('#soil_plot').show();
	var renderTo = 'soil_plot';// Default div
	var dialogDiv = 'dialog-chart';
	var fontSize = '8px';
	var titleFontSize = '10px;'
	var legendX, legendY, width, height;
	var marginBottom, marginTop, marginLeft, marginRight, spacingBottom;
	// Set the div where to render the plot
	if (smallPlot) {
		// Dialog div
		renderTo = 'soil_plot';
		// Chart font size
		fontSize = '8px';
		titleFontSize = '10px';
		legendX = 0;
		legendY = 25;
		marginBottom: 35;
		marginTop: 10;
		marginLeft: 60;
		marginRight: 10;
		spacingBottom = 35;
		width = 241;
		height = 200;
	} else {
		// Jsp page div
		renderTo = dialogDiv;
		// Chart font size for the dialog chart
		fontSize = '14px';
		titleFontSize = '30px';
		legendX = 120;
		legendY = 20;
		spacingBottom = 50;
		width = null;
		height = null;
	}
	$('#' + renderTo)
			.highcharts(
					{
						chart : {
							width : width,
							height : height,
							type : 'column',
							// marginTop : 70,
							// marginLeft : 65,
							/*
							 * style : { fontFamily : 'serif', fontSize : '8px' },
							 * marginBottom : 60, marginTop : 30, marginLeft :
							 * 60, marginRight : 2
							 */

							style : {
								fontFamily : 'serif',
								fontSize : fontSize
							},
							plotBorderWidth : 1,
							// spacingTop:2,
							// spacingRight:5,
							spacingBottom : spacingBottom,
							// spacingLeft:2,
							borderWidth : 1,
							borderRadius : 5,
							borderColor : '#999',
							// margin: [15,6,15,15],
							marginBottom : marginBottom,
							marginTop : marginTop,
							marginLeft : marginLeft,
							marginRight : marginRight,

							events : {
								load : function() {
									if (smallPlot) {

										this.renderer.image('img/zoom-in.png',
												10, 2, 20, 20).on(
												'click',
												function() {
													createSoilPlot(
															categoriesJSON,
															seriesJSON, false);
													$('#dialog-plot').dialog(
															'open');
												}).css({
											cursor : 'Pointer'
										}).css({
											position : 'relative',
											"margin-left" : "-90px"
										// opacity : 0.75
										}).attr({
											zIndex : 300,
											id : 'zoomImage'
										}).add();
									} else {
										this.renderer
												.image('img/ccafs_logo.png',
														90, 0, 120, 50)
												.on(
														'click',
														function() {
															// Add CCAFS Link
															location.href = 'http://www.ccafs-tpe.org'
														})

												.css({
													cursor : 'Pointer'
												}).css({
													position : 'relative',
													"margin-left" : "-90px"
												// opacity : 0.75
												}).attr({
													zIndex : 100
												}).add();
									}
								},

								click : function() {
									// If the small graphic was clicked on
									if (smallPlot) {
										createSoilPlot(categoriesJSON,
												seriesJSON, false);
										// $('#report').html('click on title');
										$('#dialog-plot').dialog('open');
									}

								}
							}

						},
						credits : {
							enabled : true,
							text : 'Source: CCAFS TPE (www.ccafs.org)',
							href : 'http://www.ccafs.org',
							style : {
								color : '#4e2700',
								fontWeight : 'bold',
								fontSize : fontSize
							}
						},
						title : {
							text : 'Environment Soil',
							style : {
								color : '#4e2700',
								fontWeight : 'bold',
								fontSize : titleFontSize,
								// backgroundColor : 'green',
								border : '1px solid black'
							}
						},
						xAxis : {
							categories : categoriesJSON,
							labels : {
								rotation : -45,
								style : {
									fontSize : fontSize,
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
								fontSize : fontSize
							},
							// itemWidth : 60,
							align : 'left',
							x : 0,
							verticalAlign : 'bottom',
							y : legendY,
							floating : true,
							backgroundColor : (Highcharts.theme && Highcharts.theme.background2)
									|| 'white',
							borderColor : '#CCC',
							// borderWidth : 1,
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
						series : seriesJSON,
						exporting : {
							buttons : {
								contextButton : {
									// symbol: 'circle',
									symbol : 'url(img/download_32.png)',
								// symbolStrokeWidth : 1,
								// symbolFill : '#bada55',
								// symbolStroke : '#330033',
								// symbolX : 6,
								// symbolY : 6
								}
							}
						}
					});

}

// Create Box Plot
function createTPEBoxPlot(categories, series, smallPlot) {
	// $('#plot_box').show();
	var renderTo = 'plot_box';// Default div
	var dialogDiv = 'dialog-chart';
	var fontSize = '8px';
	var titleFontSize = '10px;'
	var legendX, legendY, width, height;
	var marginBottom, marginTop, marginLeft, marginRight, spacingBottom;
	// Set the div where to render the plot
	if (smallPlot) {
		// Dialog div
		renderTo = 'plot_box';
		// Chart font size
		fontSize = '8px';
		titleFontSize = '10px';
		legendX = 20;
		legendY = 25;
		marginBottom: 2;
		marginTop: 10;
		marginLeft: 60;
		marginRight: 10;
		spacingBottom = 35;
		width = 241;
		height = 200;
	} else {
		// Jsp page div
		renderTo = dialogDiv;
		// Chart font size for the dialog chart
		fontSize = '14px';
		titleFontSize = '30px';
		legendX = 120;
		legendY = 20;
		spacingBottom = 50;
		width = null;
		height = null;
	}
	var chart = new Highcharts.Chart({
		chart : {
			width : width,
			height : height,
			type : 'boxplot',
			renderTo : renderTo,
			style : {
				fontFamily : 'serif',
				fontSize : fontSize
			},
			plotBorderWidth : 1,
			// spacingTop:2,
			// spacingRight:5,
			spacingBottom : spacingBottom,
			// spacingLeft:2,
			borderWidth : 1,
			borderRadius : 5,
			borderColor : '#999',
			// margin: [15,6,15,15],
			marginBottom : marginBottom,
			marginTop : marginTop,
			marginLeft : marginLeft,
			marginRight : marginRight,

			events : {
				load : function() {
					if (smallPlot) {

						this.renderer.image('img/zoom-in.png', 10, 2, 20, 20)
								.on(
										'click',
										function() {
											createTPEBoxPlot(categories,
													series, false);
											$('#dialog-plot').dialog('open');
										}).css({
									cursor : 'Pointer'
								}).css({
									position : 'relative',
									"margin-left" : "-90px"
								// opacity : 0.75
								}).attr({
									zIndex : 300,
									id : 'zoomImage'
								}).add();
					} else {
						this.renderer.image('img/ccafs_logo.png', 90, 0, 120,
								50).on('click', function() {
							// Add CCAFS Link
							location.href = 'http://www.ccafs-tpe.org'
						}).css({
							cursor : 'Pointer'
						}).css({
							position : 'relative',
							"margin-left" : "-90px"
						// opacity : 0.75
						}).attr({
							zIndex : 100
						}).add();
					}

					/*
					 * if (smallPlot) { // add report div var ch = this, x = 20,
					 * y = 57; ch.flashText = ch.renderer .text( '<div
					 * id="flash"><div id="report">Click to enlarge</div></div>',
					 * x, y + 10, true).css({ // width: circleradius*2, color :
					 * '#009900', fontSize : '30px', textAlign : 'center'
					 * }).attr({ zIndex : 101 }).add(); }
					 */
				},

				click : function() {
					// If the small graphic was clicked on
					if (smallPlot) {
						createTPEBoxPlot(categories, series, false);
						// $('#report').html('click on title');
						$('#dialog-plot').dialog('open');
					}

				}
			}
		},
		credits : {
			enabled : true,
			text : 'Source: CCAFS TPE (www.ccafs.org)',
			href : 'http://www.ccafs.org',
			style : {
				color : '#4e2700',
				fontWeight : 'bold',
				fontSize : fontSize
			}
		},
		title : {
			text : 'TPE Trend Yield - Boxplot',
			style : {
				color : '#009900',
				// fontWeight : 'bold',
				fontSize : titleFontSize
			}
		},
		legend : {
			enabled : true,
			layout : 'horizontal',
			align : 'left',
			x : legendX,
			verticalAlign : 'bottom',
			y : legendY,
			floating : true,
			backgroundColor : '#FFFFFF',
			itemStyle : {
				color : '#000',
				fontFamily : 'MuseoS500',
				// fontWeight : 'bold',
				fontSize : fontSize
			}
		},

		xAxis : [
		/*
		 * { labels : { x : 5, useHTML : true, formatter : function() { return '<img
		 * src="img/ccafs_logo.png" style="height:50px; width:50px;"><img>'; } } },
		 */
		{
			categories : series.categories,
			// offset : 0,
			// gridLineWidth : 1,
			// width : 200,
			title : {
				text : 'Harvest Years',
				style : {
					color : '#000000',
					fontSize : fontSize,
				}
			},
			// left : 324
			events : {

				click : function() {
					if (smallPlot) {
						createTPEBoxPlot(categories, series, false);
						// $('#report').html('click on title');
						$('#dialog-plot').dialog('open');
					}
				}
			},
			gridLineWidth : 1,
			labels : {
				rotation : -45,
				style : {
					fontSize : fontSize,
					fontFamily : 'Verdana, sans-serif'
				}
			}
		} ],

		yAxis : {
			min : 0,
			title : {
				text : 'Yield (Kg/ha)',
				style : {
					color : '#000000',
					fontSize : fontSize,
				}
			},
			events : {

				click : function() {
					if (smallPlot) {
						createTPEBoxPlot(categories, series, false);
						// $('#report').html('click on title');
						$('#dialog-plot').dialog('open');
					}
				}
			},
			labels : {
				formatter : function() {
					return this.value.toString();
				},
				style : {
					fontSize : fontSize,
					fontFamily : 'Verdana, sans-serif'
				}

			}
		},
		labels : {
			events : {

				click : function() {
					if (smallPlot) {
						createTPEBoxPlot(categories, series, false);
						// $('#report').html('click on title');
						$('#dialog-plot').dialog('open');
					}
				}
			}
		},

		series : series.series,
		exporting : {
			buttons : {
				contextButton : {
					// symbol: 'circle',
					symbol : 'url(img/download_32.png)',
				// symbolStrokeWidth : 1,
				// symbolFill : '#bada55',
				// symbolStroke : '#330033',
				// symbolX : 6,
				// symbolY : 6
				}
			}
		}
	});

}

function plotLAI(seriesMap, environment, smallPlot) {

	// console.log(seriesMap);

	// $('#plot_lai').show();
	var renderTo = 'plot_lai';// Default div
	var dialogDiv = 'dialog-chart';
	var fontSize = '8px';
	var titleFontSize = '10px;'
	var legendX, legendY, width, height;
	var marginBottom, marginTop, marginLeft, marginRight, spacingBottom;

	// Set the div where to render the plot
	if (smallPlot) {
		// Dialog div
		renderTo = 'plot_lai';
		// Chart font size
		fontSize = '8px';
		titleFontSize = '10px';
		legendX = 0;
		legendY = 20;
		marginBottom: 35;
		marginTop: 10;
		marginLeft: 60;
		marginRight: 10;
		spacingBottom = 35;
		width = 241;
		height = 200;
	} else {
		// Jsp page div
		renderTo = dialogDiv;
		// Chart font size for the dialog chart
		fontSize = '14px';
		titleFontSize = '30px';
		legendX = 0;
		legendY = 20;
		spacingBottom = 50;
		width = null;
		height = null;
	}
	$('#' + renderTo)
			.highcharts(
					{
						credits : {
							// This hides highcharts.com from the legend
							// enabled : false
							text : 'Source: CCAFS TPE (www.ccafs-tpe.org)',
							href : 'http://www.ccafs-tpe.org',
							// href: null
							// style : {
							// width : 300
							// },
							position : {
								align : 'left',
								x : 10
							}
						},
						chart : {
							width : width,
							height : height,
							/*
							 * style : { fontFamily : 'serif', fontSize : '8px' },
							 * marginBottom : 50, marginTop : 25, marginLeft :
							 * 50, marginRight : 10 // width: null, // height:
							 * null
							 */
							style : {
								fontFamily : 'serif',
								fontSize : fontSize
							},
							plotBorderWidth : 1,
							// spacingTop:2,
							// spacingRight:5,
							spacingBottom : spacingBottom,
							// spacingLeft:2,
							borderWidth : 1,
							borderRadius : 5,
							borderColor : '#999',
							// margin: [15,6,15,15],
							marginBottom : marginBottom,
							marginTop : marginTop,
							marginLeft : marginLeft,
							marginRight : marginRight,

							events : {
								load : function() {
									if (smallPlot) {

										this.renderer
												.image('img/zoom-in.png', 10,
														2, 20, 20)
												.on(
														'click',
														function() {
															plotLAI(
																	seriesMap,
																	environment,
																	false);
															$('#dialog-plot')
																	.dialog(
																			'open');
														}).css({
													cursor : 'Pointer'
												}).css({
													position : 'relative',
													"margin-left" : "-90px"
												// opacity : 0.75
												}).attr({
													zIndex : 300,
													id : 'zoomImage'
												}).add();
									} else {
										this.renderer
												.image('img/ccafs_logo.png',
														90, 0, 120, 50)
												.on(
														'click',
														function() {
															// Add CCAFS Link
															location.href = 'http://www.ccafs-tpe.org'
														}).css({
													cursor : 'Pointer'
												}).css({
													position : 'relative',
													"margin-left" : "-90px"
												// opacity : 0.75
												}).attr({
													zIndex : 100
												}).add();
									}

									/*
									 * if (smallPlot) { // add report div var ch =
									 * this, x = 20, y = 57; ch.flashText =
									 * ch.renderer .text( '<div id="flash"><div
									 * id="report">Click to enlarge</div></div>',
									 * x, y + 10, true).css({ // width:
									 * circleradius*2, color : '#009900',
									 * fontSize : '30px', textAlign : 'center'
									 * }).attr({ zIndex : 101 }).add(); }
									 */
								},

								click : function() {
									// If the small graphic was clicked on
									if (smallPlot) {
										plotLAI(seriesMap, environment, false);
										// $('#report').html('click on title');
										$('#dialog-plot').dialog('open');
									}

								}
							}
						},
						title : {
							text : seriesMap.title,
							style : {
								color : '#4e2700',
								// fontWeight : 'bold'
								fontSize : fontSize
							}
						},
						subtitle : {
							text : seriesMap.subTitle,
							style : {
								color : '#4e2700',
								// fontWeight : 'bold',
								fontSize : fontSize
							}
						},
						xAxis : {
							categories : seriesMap.categories,
							title : {
								text : seriesMap.xaxis,
								align : 'high'
							},
							gridLineWidth : 1,
							plotBands : seriesMap.plotBands,
							max : 94,
							tickInterval : 7
						},
						yAxis : {
							title : {
								text : seriesMap.yaxis
							},
							labels : {
								overflow : 'justify',
								format : '{value:.1f}'
							}
						},
						plotOptions : {
							column : {
								stacking : 'normal',
								dataLabels : {
									enabled : false,
									color : (Highcharts.theme && Highcharts.theme.dataLabelsColor)
											|| 'white'
								}
							}
						},
						legend : {
							// layout : 'horizontal',
							// align : 'left',
							x : legendX,
							// verticalAlign : 'bottom',
							y : legendY,
							floating : true,
							// backgroundColor : '#FFFFFF',
							// itemWidth : itemWidth,
							// itemStyle : {
							// color : '#000',
							// fontFamily : 'MuseoS500',
							// // fontWeight : 'bold',
							// fontSize : fontSize
							// // width : itemWidth
							// },
							title : {
								text : seriesMap.legendTitle
							}
						// floating : true,
						// draggable : true
						// zIndex : 400
						},
						series : seriesMap.series,
						exporting : {
							buttons : {
								contextButton : {
									// symbol: 'circle',
									symbol : 'url(img/download_32.png)',
								// symbolStrokeWidth : 1,
								// symbolFill : '#bada55',
								// symbolStroke : '#330033',
								// symbolX : 6,
								// symbolY : 6
								}
							}
						}
					});
}

function viewPlot() {

	$('#dialog-plot').dialog({
		autoOpen : false,
		modal : true,
		title : tpeDialogTitle,
		// bgiframe : true,

		// width : $(window).width(),
		// height : $(window).height(),

		height : 'auto',
		// height : 800,
		width : 'auto',
		// width : 850,

		// fullScreen: true,
		// fullScreenForce: true,

		draggable : true,
		resizable : true,
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

}

function plotPCEW(seriesMap, environment, smallPlot) {
	// console.log(seriesMap.series);
	// console.log(seriesMap.categories);
	// $('#plot_pcew').show();
	var renderTo = 'plot_pcew';// Default div
	// console.log($('#' + renderTo).height());
	var dialogDiv = 'dialog-chart';
	var fontSize = '8px';
	var titleFontSize = '10px;'
	var legendX, legendY, width, height;
	var marginBottom, marginTop, marginLeft, marginRight, spacingBottom;
	// Set the div where to render the plot
	if (smallPlot) {
		// Dialog div
		renderTo = 'plot_pcew';
		// Chart font size
		fontSize = '8px';
		titleFontSize = '10px';
		legendX = 0;
		legendY = 20;
		marginBottom: 35;
		marginTop: 10;
		marginLeft: 60;
		marginRight: 10;
		spacingBottom = 35;
		width = 241;
		height = 200;
	} else {
		// Jsp page div
		renderTo = dialogDiv;
		// Chart font size for the dialog chart
		fontSize = '14px';
		titleFontSize = '30px';
		legendX = 0;
		legendY = 20;
		spacingBottom = 50;
		width = null;
		height = null;
	}

	$('#' + renderTo).highcharts(
			{
				credits : {
					// This hides highcharts.com from the legend
					// enabled : false
					text : 'Source: CCAFS TPE (www.ccafs-tpe.org)',
					href : 'http://www.ccafs-tpe.org',
					// href: null
					// style : {
					// width : 300
					// },
					position : {
						align : 'left',
						x : 10
					}
				},
				chart : {
					width : width,
					height : height,
					/*
					 * style : { fontFamily : 'serif', fontSize : '8px' },
					 * marginBottom : 60, marginTop : 25, marginLeft : 45,
					 * marginRight : 10
					 */
					style : {
						fontFamily : 'serif',
						fontSize : fontSize
					},
					plotBorderWidth : 1,
					// spacingTop:2,
					// spacingRight:5,
					spacingBottom : spacingBottom,
					// spacingLeft:2,
					borderWidth : 1,
					borderRadius : 5,
					borderColor : '#999',
					// margin: [15,6,15,15],
					marginBottom : marginBottom,
					marginTop : marginTop,
					marginLeft : marginLeft,
					marginRight : marginRight,

					events : {
						load : function() {
							if (smallPlot) {

								this.renderer.image('img/zoom-in.png', 10, 2,
										20, 20).on('click', function() {
									plotPCEW(seriesMap, environment, false);
									$('#dialog-plot').dialog('open');
								}).css({
									cursor : 'Pointer'
								}).css({
									position : 'relative',
									"margin-left" : "-90px"
								// opacity : 0.75
								}).attr({
									zIndex : 300,
									id : 'zoomImage'
								}).add();
							} else {
								this.renderer.image('img/ccafs_logo.png', 90,
										0, 120, 50).on('click', function() {
									// Add CCAFS Link
									location.href = 'http://www.ccafs-tpe.org'
								}).css({
									cursor : 'Pointer'
								}).css({
									position : 'relative',
									"margin-left" : "-90px"
								// opacity : 0.75
								}).attr({
									zIndex : 100
								}).add();
							}

							/*
							 * if (smallPlot) { // add report div var ch = this,
							 * x = 20, y = 57; ch.flashText = ch.renderer .text( '<div
							 * id="flash"><div id="report">Click to enlarge</div></div>',
							 * x, y + 10, true).css({ // width: circleradius*2,
							 * color : '#009900', fontSize : '30px', textAlign :
							 * 'center' }).attr({ zIndex : 101 }).add(); }
							 */
						},

						click : function() {
							// If the small graphic was clicked on
							if (smallPlot) {
								plotPCEW(seriesMap, environment, false);
								// $('#report').html('click on title');
								$('#dialog-plot').dialog('open');
							}

						}
					}
				},
				title : {
					text : seriesMap.title,
					style : {
						color : '#4e2700',
						// fontWeight : 'bold',
						fontSize : fontSize
					}
				},
				subtitle : {
					text : seriesMap.subTitle,
					style : {
						color : '#4e2700',
						// fontWeight : 'bold',
						fontSize : fontSize
					}
				},
				xAxis : {
					categories : seriesMap.categories,
					title : {
						text : seriesMap.xaxis,
						align : 'high'
					},
					gridLineWidth : 1,
					plotBands : seriesMap.plotBands,
					max : 91,
					tickInterval : 7
				},
				yAxis : {
					title : {
						text : seriesMap.yaxis
					},
					labels : {
						overflow : 'justify',
						format : '{value:.1f}'
					}
				},
				legend : {
					// layout : 'horizontal',
					// align : 'left',
					x : legendX,
					// verticalAlign : 'bottom',
					y : legendY,
					floating : true,
					// backgroundColor : '#FFFFFF',
					// itemWidth : itemWidth,
					// itemStyle : {
					// color : '#000',
					// fontFamily : 'MuseoS500',
					// // fontWeight : 'bold',
					// fontSize : fontSize
					// // width : itemWidth
					// },
					title : {
						text : seriesMap.legendTitle
					}
				// floating : true,
				// draggable : true
				// zIndex : 400
				},
				series : seriesMap.series,
				exporting : {
					buttons : {
						contextButton : {
							// symbol: 'circle',
							symbol : 'url(img/download_32.png)',
						// symbolStrokeWidth : 1,
						// symbolFill : '#bada55',
						// symbolStroke : '#330033',
						// symbolX : 6,
						// symbolY : 6
						}
					}
				},
				tooltip : {
					formatter : function() {
						return 'Cluster: <b>' + this.series.name
								+ '</b><br/>Stress Index: <b>' + this.y
								+ '</b>' + '<br/><b>Day After Emergency: '
								+ this.x + '</b>'

					}
				}
			});
}

function plotRAIN(seriesMap, environment, smallPlot) {
	// $('#plot_rainsum').show();

	// console.log(seriesMap.plotBands);
	var renderTo = 'plot_rainsum';// Default div
	var dialogDiv = 'dialog-chart';
	var fontSize = '8px';
	var titleFontSize = '10px;'
	var legendX, legendY, width, height;
	var marginBottom, marginTop, marginLeft, marginRight, spacingBottom;
	// Set the div where to render the plot
	if (smallPlot) {
		// Dialog div
		renderTo = 'plot_rainsum';
		// Chart font size
		fontSize = '8px';
		titleFontSize = '10px';
		legendX = 0;
		legendY = 20;
		marginBottom: 35;
		marginTop: 10;
		marginLeft: 60;
		marginRight: 10;
		spacingBottom = 35;
		width = 241;
		height = 200;
	} else {
		// Jsp page div
		renderTo = dialogDiv;
		// Chart font size for the dialog chart
		fontSize = '14px';
		titleFontSize = '30px';
		legendX = 0;
		legendY = 20;
		spacingBottom = 50;
		width = null;
		height = null;
	}
	$('#' + renderTo)
			.highcharts(
					{
						credits : {
							// This hides highcharts.com from the legend
							// enabled : false
							text : 'Source: CCAFS TPE (www.ccafs-tpe.org)',
							href : 'http://www.ccafs-tpe.org',
							// href: null
							// style : {
							// width : 300
							// },
							position : {
								align : 'left',
								x : 10
							}
						},
						chart : {
							width : width,
							height : height,
							/*
							 * style : { fontFamily : 'serif', fontSize : '8px' },
							 * marginBottom : 50, marginTop : 25, marginLeft :
							 * 50, marginRight : 10 // width: null, // height:
							 * null
							 */
							style : {
								fontFamily : 'serif',
								fontSize : fontSize
							},
							plotBorderWidth : 1,
							// spacingTop:2,
							// spacingRight:5,
							spacingBottom : spacingBottom,
							// spacingLeft:2,
							borderWidth : 1,
							borderRadius : 5,
							borderColor : '#999',
							// margin: [15,6,15,15],
							marginBottom : marginBottom,
							marginTop : marginTop,
							marginLeft : marginLeft,
							marginRight : marginRight,

							events : {
								load : function() {
									if (smallPlot) {

										this.renderer
												.image('img/zoom-in.png', 10,
														2, 20, 20)
												.on(
														'click',
														function() {
															plotRAIN(
																	seriesMap,
																	environment,
																	false);
															$('#dialog-plot')
																	.dialog(
																			'open');
														}).css({
													cursor : 'Pointer'
												}).css({
													position : 'relative',
													"margin-left" : "-90px"
												// opacity : 0.75
												}).attr({
													zIndex : 300,
													id : 'zoomImage'
												}).add();
									} else {
										this.renderer
												.image('img/ccafs_logo.png',
														90, 0, 120, 50)
												.on(
														'click',
														function() {
															// Add CCAFS Link
															location.href = 'http://www.ccafs-tpe.org'
														}).css({
													cursor : 'Pointer'
												}).css({
													position : 'relative',
													"margin-left" : "-90px"
												// opacity : 0.75
												}).attr({
													zIndex : 100
												}).add();
									}

									/*
									 * if (smallPlot) { // add report div var ch =
									 * this, x = 20, y = 57; ch.flashText =
									 * ch.renderer .text( '<div id="flash"><div
									 * id="report">Click to enlarge</div></div>',
									 * x, y + 10, true).css({ // width:
									 * circleradius*2, color : '#009900',
									 * fontSize : '30px', textAlign : 'center'
									 * }).attr({ zIndex : 101 }).add(); }
									 */
								},

								click : function() {
									// If the small graphic was clicked on
									if (smallPlot) {
										plotRAIN(seriesMap, environment, false);
										// $('#report').html('click on title');
										$('#dialog-plot').dialog('open');
									}

								}
							}
						},
						title : {
							text : seriesMap.title,
							style : {
								color : '#4e2700',
								// fontWeight : 'bold'
								fontSize : fontSize
							}
						},
						subtitle : {
							text : seriesMap.subTitle,
							style : {
								color : '#4e2700',
								// fontWeight : 'bold',
								fontSize : fontSize
							}
						},
						xAxis : {
							categories : seriesMap.categories,
							title : {
								text : seriesMap.xaxis,
								align : 'high'
							},
							gridLineWidth : 1,
							plotBands : seriesMap.plotBands,
							// min : seriesMap.categories[0],
							// min:14,
							max : 91,
							tickInterval : 7,
						// minTickInterval: 14,
						// minRange: seriesMap.categories[0]
						// ordinal : true
						},
						yAxis : {
							title : {
								text : seriesMap.yaxis
							},
							// gridLineWidth : 1,
							labels : {
								overflow : 'justify',
								format : '{value:.1f}'
							}
						// min : 0,
						// min:14,
						// max : 84,
						// tickInterval : 14
						},
						plotOptions : {
							column : {
								stacking : 'normal',
								dataLabels : {
									enabled : true,
									color : (Highcharts.theme && Highcharts.theme.dataLabelsColor)
											|| 'white'
								}
							}
						},
						legend : {
							// layout : 'horizontal',
							// align : 'left',
							x : legendX,
							// verticalAlign : 'bottom',
							y : legendY,
							floating : true,
							// backgroundColor : '#FFFFFF',
							// itemWidth : itemWidth,
							// itemStyle : {
							// color : '#000',
							// fontFamily : 'MuseoS500',
							// // fontWeight : 'bold',
							// fontSize : fontSize
							// // width : itemWidth
							// },
							title : {
								text : seriesMap.legendTitle
							}
						// floating : true,
						// draggable : true
						// zIndex : 400
						},
						series : seriesMap.series,

						exporting : {
							buttons : {
								contextButton : {
									// symbol: 'circle',
									symbol : 'url(img/download_32.png)',
								// symbolStrokeWidth : 1,
								// symbolFill : '#bada55',
								// symbolStroke : '#330033',
								// symbolX : 6,
								// symbolY : 6
								}
							}
						},
						tooltip : {
							formatter : function() {
								return 'Cluster: <b>' + this.series.name
										+ '</b><br/>Average Rainfall: <b>'
										+ this.y + ' mm</b>'
										+ '<br/><b>Day After Emergency: '
										+ this.x + '</b>'

							}
						}
					});
}

function rainfallRadiationPlot(seriesMap, smallPlot, station, stationName) {
	var categories, series;
	if (seriesMap != null) {
		categories = seriesMap['categories'];
		series = seriesMap['series'];
	}

	// console.log(series[station]);

	// $('#rain_radiation').show();
	var renderTo = 'rain_radiation';// Default div
	var dialogDiv = 'dialog-chart';
	var fontSize = '8px';
	var titleFontSize = '10px;'
	var legendX, legendY, spacingBottom, itemWidth, width, height;
	// Set the div where to render the plot
	if (smallPlot) {
		// Dialog div
		renderTo = 'rain_radiation';
		// Chart font size
		fontSize = '8px';
		titleFontSize = '10px';
		legendX = 20;
		legendY = 25;
		spacingBottom = 35;
		// itemWidth = 40;
		width = 241;
		height = 200;
	} else {
		// Jsp page div
		renderTo = dialogDiv;
		// Chart font size for the dialog chart
		fontSize = '14px';
		titleFontSize = '24px';
		legendX = 120;
		legendY = 20;
		spacingBottom = 50;
		// itemWidth = 100;
		width = null;
		height = null;
	}

	$('#' + renderTo).highcharts(
			{
				credits : {
					// This hides highcharts.com from the legend
					// enabled : false
					text : 'Source: CCAFS TPE (www.ccafs-tpe.org)',
					href : 'http://www.ccafs-tpe.org',
					// href: null
					// style : {
					// width : 300
					// },
					position : {
						align : 'right'
					// x : 10
					},
					style : {
						color : '#4e2700',
						fontWeight : 'bold',
						fontSize : fontSize
					}
				},
				chart : {
					width : width,
					height : height,
					zoomType : 'xy',
					style : {
						fontFamily : 'serif',
						fontSize : fontSize
					},
					plotBorderWidth : 1,
					// spacingTop:2,
					// spacingRight:5,
					spacingBottom : spacingBottom,
					// spacingLeft:2,
					borderWidth : 1,
					borderRadius : 5,
					borderColor : '#999',
					// margin: [15,6,15,15],
					/*
					 * , marginBottom : 60, marginTop : 25, marginLeft : 60,
					 * marginRight : 10
					 */
					events : {
						load : function() {
							if (smallPlot) {
								this.renderer.image('img/zoom-in.png', 10, 2,
										20, 20)
										.on(
												'click',
												function() {
													rainfallRadiationPlot(
															seriesMap, false,
															station,
															stationName);
													$('#dialog-plot').dialog(
															'open');
												}).css({
											cursor : 'Pointer'
										}).css({
											position : 'relative',
											"margin-left" : "-90px"
										// opacity : 0.75
										}).attr({
											zIndex : 300,
											id : 'zoomImage'
										}).add();

								// $('.highcharts-legend-item
								// rect').attr('r',
								// '0');
								$('.highcharts-legend-item rect').attr(
										'height', '8').attr('width', '8');

							} else {
								this.renderer.image('img/ccafs_logo.png', 90,
										0, 120, 50).on('click', function() {
									// Add CCAFS Link
									location.href = 'http://www.ccafs-tpe.org'
								}).css({
									cursor : 'Pointer'
								}).css({
									position : 'relative',
									"margin-left" : "-90px"
								// opacity : 0.75
								}).attr({
									zIndex : 100
								}).add();
							}
						},
						click : function() {
							if (smallPlot) {
								rainfallRadiationPlot(seriesMap, false,
										station, stationName);
								// $('#report').html('click on title');
								$('#dialog-plot').dialog('open');
							}
						}
					}
				},
				title : {
					text : 'Average Monthly Rainfall and Radiation',
					style : {
						color : '#4e2700',
						// fontWeight : 'bold',
						fontSize : titleFontSize
					}
				},
				subtitle : {
					text : stationName + ' Station',
					style : {
						color : '#4e2700'
					// fontWeight : 'bold',
					// fontSize : '10px',
					}
				},
				xAxis : {
					categories : categories,
					labels : {
						rotation : -45,
						style : {
							fontSize : fontSize,
							fontFamily : 'Verdana, sans-serif'
						}
					},
					title : {
						text : 'Months'
					}

				},
				yAxis : [ { // Primary yAxis
					labels : {
						// format : '{value}°C',
						style : {
							color : '#4572A7',
							fontSize : fontSize
						},
						format : '{value:.1f}'
					// format : '{value:.2f}'
					},
					title : {
						text : 'Radation (MJ/m2.day)',
						style : {
							color : '#4572A7'
						}
					}
				}, { // Secondary yAxis
					title : {
						text : 'Rainfall (mm)',
						rotation : -90,
						x : 10,
						style : {
							color : '#4572A7'
						}
					},
					labels : {
						// format : '{value} mm',
						// rotation: -45,
						style : {
							color : '#4572A7',
							fontSize : fontSize
						},
						format : '{value:.1f}'
					// format : '{value:.2f}'
					},
					opposite : true
				} ],
				tooltip : {
					shared : true
				},
				legend : {
					layout : 'horizontal',
					align : 'left',
					x : legendX,
					verticalAlign : 'bottom',
					y : legendY,
					floating : true,
					backgroundColor : '#FFFFFF',
					// itemWidth : itemWidth,
					itemStyle : {
						color : '#000',
						fontFamily : 'MuseoS500',
						// fontWeight : 'bold',
						fontSize : fontSize
					// width : itemWidth
					},
					// title : {
					// text : ':: Drag'
					// },
					floating : true,
					draggable : true
				// zIndex : 400
				},
				series : series[station],
				exporting : {
					buttons : {
						contextButton : {
							// symbol: 'circle',
							symbol : 'url(img/download_32.png)',
						// symbolStrokeWidth : 1,
						// symbolFill : '#bada55',
						// symbolStroke : '#330033',
						// symbolX : 6,
						// symbolY : 6
						}
					}
				}
			});
}

function plotRAINCUM(seriesMap, environment, smallPlot) {
	// $('#plot_rainsum').show();

	// console.log(seriesMap.plotBands);
	var renderTo = 'plot_raincum';// Default div
	var dialogDiv = 'dialog-chart';
	var fontSize = '8px';
	var titleFontSize = '10px;'
	var legendX, legendY, width, height;
	var marginBottom, marginTop, marginLeft, marginRight, spacingBottom;
	// Set the div where to render the plot
	if (smallPlot) {
		// Dialog div
		renderTo = 'plot_raincum';
		// Chart font size
		fontSize = '8px';
		titleFontSize = '10px';
		legendX = 0;
		legendY = 20;
		marginBottom: 35;
		marginTop: 10;
		marginLeft: 60;
		marginRight: 10;
		spacingBottom = 35;
		width = 241;
		height = 200;
	} else {
		// Jsp page div
		renderTo = dialogDiv;
		// Chart font size for the dialog chart
		fontSize = '14px';
		titleFontSize = '30px';
		legendX = 0;
		legendY = 20;
		spacingBottom = 50;
		width = null;
		height = null;
	}
	$('#' + renderTo)
			.highcharts(
					{
						credits : {
							// This hides highcharts.com from the legend
							// enabled : false
							text : 'Source: CCAFS TPE (www.ccafs-tpe.org)',
							href : 'http://www.ccafs-tpe.org',
							// href: null
							// style : {
							// width : 300
							// },
							position : {
								align : 'left',
								x : 10
							}
						},
						chart : {
							/*
							 * style : { fontFamily : 'serif', fontSize : '8px' },
							 * marginBottom : 50, marginTop : 25, marginLeft :
							 * 50, marginRight : 10 // width: null, // height:
							 * null
							 */
							width : width,
							height : height,
							style : {
								fontFamily : 'serif',
								fontSize : fontSize
							},
							plotBorderWidth : 1,
							// spacingTop:2,
							// spacingRight:5,
							spacingBottom : spacingBottom,
							// spacingLeft:2,
							borderWidth : 1,
							borderRadius : 5,
							borderColor : '#999',
							// margin: [15,6,15,15],
							marginBottom : marginBottom,
							marginTop : marginTop,
							marginLeft : marginLeft,
							marginRight : marginRight,

							events : {
								load : function() {
									if (smallPlot) {

										this.renderer
												.image('img/zoom-in.png', 10,
														2, 20, 20)
												.on(
														'click',
														function() {
															plotRAINCUM(
																	seriesMap,
																	environment,
																	false);
															$('#dialog-plot')
																	.dialog(
																			'open');
														}).css({
													cursor : 'Pointer'
												}).css({
													position : 'relative',
													"margin-left" : "-90px"
												// opacity : 0.75
												}).attr({
													zIndex : 300,
													id : 'zoomImage'
												}).add();
									} else {
										this.renderer
												.image('img/ccafs_logo.png',
														90, 0, 120, 50)
												.on(
														'click',
														function() {
															// Add CCAFS Link
															location.href = 'http://www.ccafs-tpe.org'
														}).css({
													cursor : 'Pointer'
												}).css({
													position : 'relative',
													"margin-left" : "-90px"
												// opacity : 0.75
												}).attr({
													zIndex : 100
												}).add();
									}

									/*
									 * if (smallPlot) { // add report div var ch =
									 * this, x = 20, y = 57; ch.flashText =
									 * ch.renderer .text( '<div id="flash"><div
									 * id="report">Click to enlarge</div></div>',
									 * x, y + 10, true).css({ // width:
									 * circleradius*2, color : '#009900',
									 * fontSize : '30px', textAlign : 'center'
									 * }).attr({ zIndex : 101 }).add(); }
									 */
								},

								click : function() {
									// If the small graphic was clicked on
									if (smallPlot) {
										plotRAINCUM(seriesMap, environment,
												false);
										// $('#report').html('click on title');
										$('#dialog-plot').dialog('open');
									}

								}
							}
						},
						title : {
							text : seriesMap.title,
							style : {
								color : '#4e2700',
								// fontWeight : 'bold'
								fontSize : fontSize
							}
						},
						subtitle : {
							text : seriesMap.subTitle,
							style : {
								color : '#4e2700',
								// fontWeight : 'bold',
								fontSize : fontSize
							}
						},
						xAxis : {
							categories : seriesMap.categories,
							title : {
								text : seriesMap.xaxis,
								align : 'high'
							},
							gridLineWidth : 1,
							plotBands : seriesMap.plotBands,
							// min : seriesMap.categories[0],
							// min:14,
							max : 91,
							tickInterval : 7,
						// minTickInterval: 14,
						// minRange: seriesMap.categories[0]
						// ordinal : true
						},
						yAxis : {
							title : {
								text : seriesMap.yaxis
							},
							// gridLineWidth : 1,
							labels : {
								overflow : 'justify',
								format : '{value:.1f}'
							}
						// min : 0,
						// min:14,
						// max : 84,
						// tickInterval : 14
						},
						plotOptions : {
							column : {
								stacking : 'normal',
								dataLabels : {
									enabled : false,
									color : (Highcharts.theme && Highcharts.theme.dataLabelsColor)
											|| 'white'
								}
							}
						},
						legend : {
							// layout : 'horizontal',
							// align : 'left',
							x : legendX,
							// verticalAlign : 'bottom',
							y : legendY,
							floating : true,
							// backgroundColor : '#FFFFFF',
							// itemWidth : itemWidth,
							// itemStyle : {
							// color : '#000',
							// fontFamily : 'MuseoS500',
							// // fontWeight : 'bold',
							// fontSize : fontSize
							// // width : itemWidth
							// },
							title : {
								text : seriesMap.legendTitle
							}
						// floating : true,
						// draggable : true
						// zIndex : 400
						},
						series : seriesMap.series,

						exporting : {
							buttons : {
								contextButton : {
									// symbol: 'circle',
									symbol : 'url(img/download_32.png)',
								// symbolStrokeWidth : 1,
								// symbolFill : '#bada55',
								// symbolStroke : '#330033',
								// symbolX : 6,
								// symbolY : 6
								}
							}
						},
						tooltip : {
							formatter : function() {
								return 'Cluster: <b>' + this.series.name
										+ '</b><br/>Accumulated Rainfall: <b>'
										+ this.y + ' mm</b>'
										+ '<br/><b>Day After Emergency: '
										+ this.x + '</b>'

							}
						}
					});
}

function plotWAGT(seriesMap, environment, smallPlot) {
	// console.log(seriesMap.series);
	// console.log(seriesMap.categories);
	// $('#plot_pcew').show();
	var renderTo = 'plot_wagt';// Default div
	var dialogDiv = 'dialog-chart';
	var fontSize = '8px';
	var titleFontSize = '10px;'
	var legendX, legendY, width, height;
	var marginBottom, marginTop, marginLeft, marginRight, spacingBottom;
	// Set the div where to render the plot
	if (smallPlot) {
		// Dialog div
		renderTo = 'plot_wagt';
		// Chart font size
		fontSize = '8px';
		titleFontSize = '10px';
		legendX = 0;
		legendY = 20;
		marginBottom: 35;
		marginTop: 10;
		marginLeft: 60;
		marginRight: 10;
		spacingBottom = 35;
		width = 241;
		height = 200;
	} else {
		// Jsp page div
		renderTo = dialogDiv;
		// Chart font size for the dialog chart
		fontSize = '14px';
		titleFontSize = '30px';
		legendX = 10;
		legendY = 20;
		spacingBottom = 50;
		marginBottom: 80;
		width = null;
		height = null;
		// marginTop: 10;
		// marginLeft: 60;
		// marginRight: 10;
	}

	var chart = new Highcharts.Chart(
			{

				// $('#' + renderTo).highcharts(
				// {
				credits : {
					// This hides highcharts.com from the legend
					// enabled : false
					text : 'Source: CCAFS TPE (www.ccafs-tpe.org)',
					href : 'http://www.ccafs-tpe.org',
					// href: null
					// style : {
					// width : 300
					// },
					position : {
						align : 'left',
						x : 10
					}
				},
				chart : {
					/*
					 * style : { fontFamily : 'serif', fontSize : '8px' },
					 * marginBottom : 60, marginTop : 25, marginLeft : 45,
					 * marginRight : 10
					 */
					width : width,
					height : height,
					renderTo : renderTo,
					style : {
						fontFamily : 'serif',
						fontSize : fontSize
					},
					plotBorderWidth : 1,
					// spacingTop:2,
					// spacingRight:5,
					spacingBottom : spacingBottom,
					// spacingLeft:2,
					borderWidth : 1,
					borderRadius : 5,
					borderColor : '#999',
					// margin: [15,6,15,15],
					marginBottom : marginBottom,
					marginTop : marginTop,
					marginLeft : marginLeft,
					marginRight : marginRight,
					reflow : true,
					events : {
						load : function() {
							chart = this;
							if (smallPlot) {

								this.renderer.image('img/zoom-in.png', 10, 2,
										20, 20).on('click', function() {
									plotWAGT(seriesMap, environment, false);
									$('#dialog-plot').dialog('open');
								}).css({
									cursor : 'Pointer'
								}).css({
									position : 'relative',
									"margin-left" : "-90px"
								// opacity : 0.75
								}).attr({
									zIndex : 300,
									id : 'zoomImage'
								}).add();
							} else {
								this.renderer.image('img/ccafs_logo.png', 90,
										0, 120, 50).on('click', function() {
									// Add CCAFS Link
									location.href = 'http://www.ccafs-tpe.org'
								}).css({
									cursor : 'Pointer'
								}).css({
									position : 'relative',
									"margin-left" : "-90px"
								// opacity : 0.75
								}).attr({
									zIndex : 100
								}).add();
							}

							/*
							 * if (smallPlot) { // add report div var ch = this,
							 * x = 20, y = 57; ch.flashText = ch.renderer .text( '<div
							 * id="flash"><div id="report">Click to enlarge</div></div>',
							 * x, y + 10, true).css({ // width: circleradius*2,
							 * color : '#009900', fontSize : '30px', textAlign :
							 * 'center' }).attr({ zIndex : 101 }).add(); }
							 */
						},

						click : function() {
							// If the small graphic was clicked on
							if (smallPlot) {
								plotWAGT(seriesMap, environment, false);
								// $('#report').html('click on title');
								$('#dialog-plot').dialog('open');
							}

						}
					}
				},
				title : {
					text : seriesMap.title,
					style : {
						color : '#4e2700',
						// fontWeight : 'bold',
						fontSize : fontSize
					}
				},
				subtitle : {
					text : seriesMap.subTitle,
					style : {
						color : '#4e2700',
						// fontWeight : 'bold',
						fontSize : fontSize
					}
				},
				xAxis : {
					categories : seriesMap.categories,
					title : {
						text : seriesMap.xaxis,
						align : 'high'
					},
					gridLineWidth : 1,
					plotBands : seriesMap.plotBands,
					max : 91,
					tickInterval : 7
				},
				yAxis : {
					title : {
						text : seriesMap.yaxis
					},
					labels : {
						overflow : 'justify',
						format : '{value:.1f}'
					}
				},
				legend : {
					// layout : 'horizontal',
					// align : 'left',
					x : legendX,
					// verticalAlign : 'bottom',
					y : legendY,
					floating : true,
					// backgroundColor : '#FFFFFF',
					// itemWidth : itemWidth,
					// itemStyle : {
					// color : '#000',
					// fontFamily : 'MuseoS500',
					// // fontWeight : 'bold',
					// fontSize : fontSize
					// // width : itemWidth
					// },
					title : {
						text : seriesMap.legendTitle
					}
				// floating : true,
				// draggable : true
				// zIndex : 400
				},
				series : seriesMap.series,
				exporting : {
					buttons : {
						contextButton : {
							// symbol: 'circle',
							symbol : 'url(img/download_32.png)',
						// symbolStrokeWidth : 1,
						// symbolFill : '#bada55',
						// symbolStroke : '#330033',
						// symbolX : 6,
						// symbolY : 6
						}
					},
					enableImages : true
				},
				tooltip : {
					formatter : function() {
						return 'Cluster: <b>' + this.series.name
								+ '</b><br/>Total Dry Matter: <b>' + this.y
								+ '</b>' + '<br/><b>Day After Emergency: '
								+ this.x + '</b>'

					}
				},
				plotOptions : {
					column : {
						stacking : 'normal',
						dataLabels : {
							enabled : false,
							color : (Highcharts.theme && Highcharts.theme.dataLabelsColor)
									|| 'white'
						}
					}
				}
			});

	// $('#' + renderTo).parent().prev('h3').click(function() {
	// // chart.redraw();
	// chart.setSize($(document).width(), $(document).height() / 2, false);
	// console.log('Redrawing the plot...');
	// });

}

function hideShow(graphic) {

	if (graphic == 'TPE') {
		$('#plot_slide').show();
		// Hide the charts
		$('.plot_box').show();
		$('.soil_plot').hide();
		// $('.plot_lai').show();
		// $('.plot_temprain').show();
		$('.plot_pcew').show();
		$('.rain_radiation').hide();
		// $('.plot_wagt').show();
		// $('.plot_rainsum').show();
		// $('.plot_raincum').show();
		$('#analytics h3').show();
	} else if (graphic == 'SOIL') {
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
