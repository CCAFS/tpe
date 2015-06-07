var colorValues = [ "red", "blue", "green", "yellow", "purple", "pink",
		"orange" ], colorValuesTPE = [ "red", "blue", "green" ], colorValuesStability = [
		"#990000", "#009900", "#000099" ], colorValuesClimate = [ "black" ], colors;
/**
 * The function that initializes the Google Map
 */
function initializeMap(data) {

	var infoWindow = new google.maps.InfoWindow({
		content : ""
	});

	/*
	 * $('#zoomImage').hide(); $('#container').mouseenter(function(e) {
	 * $('#zoomImage').show(); }).mouseleave(function(e) {
	 * $('#zoomImage').hide(); });
	 */

	viewPlot();
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
	var defaultZoom = data.zoomCus;
	// Variable for TPE map
	var map;
	// Google map options
	var mapOptions = {
		zoom : defaultZoom,
		center : defaultLatLng,
		mapTypeId : google.maps.MapTypeId.ROADMAP,
		// draggableCursor : 'crosshair',
		draggableCursor : 'default',

		zoomControl : true,
		zoomControlOptions : {
			// style: google.maps.ZoomControlStyle.DEFAULT,
			style : google.maps.ZoomControlStyle.SMALL,
			position : google.maps.ControlPosition.LEFT_CENTER
		},
		scaleControl : true,
		panControl : false,
		navigationControl : false,
		streetViewControl : false,
		mapTypeControl : false,
		overviewMapControl : false,
		rotateControl : false,
	}

	console.log('Selected Output: ' + selectedOutput);
	// Create the new map and make sure the tpe_map div
	// exists
	map = new google.maps.Map(document.getElementById('tpe_map'), mapOptions);

	// If the selected map option is SOIL then create the marker cluster
	// features for the soil points
	// var bounds = new google.maps.LatLngBounds();
	if (selectedOutput.toUpperCase() == OUTPUT_SOIL) {
		// Load admin regions
		loadAdminRegions(map, data);

		// Create the soil map
		createSoilFeatures(map, data);
		// Add legend
		createLegend(map);

		// Add graphics.
		// Use default json data
		// By default display the soil plots
		// Use the clay texture code 'C'. This will display the chart for clay
		// by default.
		// var probJSON = dataJSON['C'];
		createSoilGraphics(dataJSON['C']);

	} else if (selectedOutput.toUpperCase() == OUTPUT_CLIMATE) {

		// console.log('Initializing climate');
		// Load admin regions
		loadAdminRegions(map, data);

		// Add features
		createClimateFeatures(map, data);

		// Add legend
		createLegend(map);

		// Add climate graphics
		createClimateGraphics(data.stationId, data.stationName);
		// console.log('Initialization completed');
	}

	else if (selectedOutput.toUpperCase() == OUTPUT_STABILITY) {
		// Load admin regions
		loadAdminRegions(map, data);
		// Create Stability map with features
		createStabilityFeatures(map, data);
		// Add legend
		createLegend(map);
	}

	else if (selectedOutput.toUpperCase() == OUTPUT_AREA) {

		// Add features
		createAreaFeatures(map, data);

		// Add legend
		createLegend(map);

	} else if (selectedOutput.toUpperCase() == OUTPUT_TPE) {
		// Load admin regions
		loadAdminRegions(map, data);
		// Create TPE map features
		createTPEFeatures(map, data)

		// Add legend
		createLegend(map);
		// Add graphics.
		createTPEGraphics(hfeSeries);

	}

	// Add Style
	addStyle(map);

	// Add click listener
	addClickListener(map);

	// Add mouseover events
	addMouseOverListener(map);

	// Add mouseout events
	addMouseOutListener(map);
}

/**
 * Creates the features for the environment groups
 * 
 * @param map
 *            the map to contain the features
 * @param data
 *            the data containing the features.
 */
function createAreaFeatures(map, data) {
	// console.log('AREA selected.');
	// console.log(data.areaGeoJson);
	// Add the GeoJson features to the map
	map.data.addGeoJson(data.areaGeoJson, {
		idPropertyName : "id"
	});
}

/**
 * Creates the climate features.
 * 
 * @param map
 *            the map object to contain the features
 * @param data
 *            the data containing the features
 */
function createClimateFeatures(map, data) {
	// Add climate regions

	if (data.regionJson != null) {
		map.data.addGeoJson(data.regionJson, {
			idPropertyName : "id"
		});
		// console.log('regionJson is not null');
	}
	// console.log('regionJson is null');

	// Add the GeoJson features to the map, stations
	if (data.geoJson != null)
		map.data.addGeoJson(data.geoJson, {
			idPropertyName : "id"
		});
}
/**
 * Creates climate graphics
 */
function createClimateGraphics(stationId, stationName) {
	// By default display the clamate charts
	// Create rainfall and radiation plot
	// Use a default station id and it is name
	// TODO Get default station id and name from the database
	if (climateSeriesJSON != null)
		rainfallRadiationPlot(climateSeriesJSON, true, stationId, stationName);
}

/**
 * This function creates the legend depending on the selected output
 */
function createLegend(map) {
	// get the legend container, create a legend, add a legend renderer fn
	// var $legendContainer = $('#legend-container')
	var $legendContainer = $('<div id="legend-container"><h3>' + legendTitle
			+ '</h3></div>'), $legend = $('<div id="legend">').appendTo(
			$legendContainer), renderLegend = function(colorValuesArray) {
		$legend.empty();

		if (selectedOutput.toUpperCase() == OUTPUT_SOIL) {
			$.each(colorValuesArray, function(index, val) {
				var texture;
				if (val == 'red') {
					texture = 'Sand';
				} else if (val == 'blue') {
					texture = 'Loam';
				} else if (val == 'green') {
					texture = 'Clay';
				} else if (val == 'yellow') {
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
		} else if (selectedOutput.toUpperCase() == OUTPUT_TPE) {

			// Gray color for municipality
			var $leg_municipio = $('<div style="height:25px;">').append(
					$('<div class="legend-color-box">').css({
						backgroundColor : '#bebebe',
					})).append(
					$('<div class="legend_text">').html('Municipality'));
			/*
			 * // Black for boundary or country var $leg_rice_prod = $('<div
			 * style="height:25px;">').append( $('<div
			 * class="legend-color-box">').css({ backgroundColor : '#000000',
			 * })).append( $('<div class="legend_text">').html( 'Rice
			 * Production Region'));
			 */

			$legend.append($leg_municipio);
			/* $legend.append($leg_rice_prod); */

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
		} else if (selectedOutput.toUpperCase() == OUTPUT_STABILITY) {
			// Gray color for municipality
			var $leg_municipio = $('<div style="height:25px;">').append(
					$('<div class="legend-color-box">').css({
						backgroundColor : '#bebebe',
					})).append(
					$('<div class="legend_text">').html('Municipality'));
			/*
			 * // Black for boundary or country var $leg_rice_prod = $('<div
			 * style="height:25px;">').append( $('<div
			 * class="legend-color-box">').css({ backgroundColor : '#000000',
			 * })).append( $('<div class="legend_text">').html( 'Rice
			 * Production Region'));
			 */

			$legend.append($leg_municipio);
			/* $legend.append($leg_rice_prod); */

			$.each(colorValuesArray, function(index, val) {
				var env_col;
				if (val == '#990000') {
					env_col = 'Environment Stability High (>87%)';
				} else if (val == '#000099') {
					env_col = 'Environment Stability Middle (62-87%)';
				} else if (val == '#009900') {
					env_col = 'Environment Stability Low (50-62%)';
				}

				var $div = $('<div style="height:25px;">').append(
						$('<div class="legend-color-box">').css({
							backgroundColor : val,
						}))
						.append($('<div class="legend_text">').html(env_col));

				$legend.append($div);
			});
			// renderLegend(colorValuesTPE);
		} else if (selectedOutput.toUpperCase() == OUTPUT_CLIMATE) {
			// Gray color for municipality
			var $leg_municipio = $('<div style="height:25px;">').append(
					$('<div class="legend-color-box">').css({
						backgroundColor : '#bebebe',
					})).append(
					$('<div class="legend_text">').html('Municipality'));
			// Black for boundary or country
			var $leg_rice_prod = $('<div style="height:25px;">').append(
					$('<div class="legend-color-box">').css({
						backgroundColor : '#000099',
					})).append(
					$('<div class="legend_text">').html(
							'Rice Production Region'));

			$legend.append($leg_municipio);
			$legend.append($leg_rice_prod);

			$.each(colorValuesArray, function(index, val) {
				var climate_feature;
				if (val == 'blue') {
					climate_feature = 'Region';
				} else if (val == 'black') {
					climate_feature = 'Station';
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

	if (selectedOutput.toUpperCase() == OUTPUT_SOIL)
		renderLegend(colorValues);
	else if ((selectedOutput.toUpperCase() == OUTPUT_TPE)
			|| (selectedOutput.toUpperCase() == OUTPUT_AREA))
		renderLegend(colorValuesTPE);
	else if (selectedOutput.toUpperCase() == OUTPUT_STABILITY)
		renderLegend(colorValuesStability);
	else if (selectedOutput.toUpperCase() == OUTPUT_CLIMATE)
		renderLegend(colorValuesClimate);

	// add the legend to the map
	map.controls[google.maps.ControlPosition.LEFT_CENTER]
			.push($legendContainer[0]);

}
/**
 * Adds the Area map features to the map
 * 
 * @param map
 *            the initialized map to contain the features
 * @param data
 *            the data containing features to add
 */
function createAreaFeatures(map, data) {
	// Add the GeoJson features to the map
	map.data.addGeoJson(data.areaGeoJson, {
		idPropertyName : "id"
	});
}

/**
 * Adds the stability map features to the map
 * 
 * @param map
 *            the initialized map to contain the features
 * @param data
 *            the data containing features to add
 */
function createStabilityFeatures(map, data) {
	// Add the GeoJson features to the map
	map.data.addGeoJson(data.stabilityGeoJson, {
		idPropertyName : "id"
	});
}

/**
 * The function that creates TPE Google map features
 * 
 * @param map
 *            the map object to contain the features
 * @param data
 *            the data used to create features
 */
function createTPEFeatures(map, data) {
	// Add the TPE for the selected country features to the map.
	map.data.addGeoJson(data.tpeGeoJson, {
		idPropertyName : "id"
	});

}
/**
 * Creates TPE graphics
 */
function createTPEGraphics(series) {
	// Show the box plot
	// Display the TPE Box plot
	if ((categoriesJSON != null) && (boxJSON != null))
		createTPEBoxPlot(categoriesJSON, boxJSON, true);
	// Display the Relative Transpiration Ration Plot
	// Iterate through the list of maps of favourable data.
	// Then get the map of PCEW
	if (series != null)
		$.each(series, function(indx, seriesMap) {
			// If it is map og PCEW
			if (seriesMap.type == 'LAI') {
				// Create LAI Plot
				plotLAI(seriesMap, 'LAI', true);
			} else if (seriesMap.type == 'PCEW') {
				plotPCEW(seriesMap, 'PCEW', true);
			} else if (seriesMap.type == 'WAGT') {
				plotWAGT(seriesMap, 'WAGT', true);
			} else if (seriesMap.type == 'RAINSUM') {
				plotRAIN(seriesMap, 'RAINSUM', true);
			} else if (seriesMap.type == 'RAINCUM') {
				plotRAINCUM(seriesMap, 'RAINCUM', true);
			}
		});
}

/**
 * Loads the administrative regions (municipios, states, countries, boundaries.
 * 
 * @param map
 * @param data
 */
function loadAdminRegions(map, data) {
	if ((data.regionGeoJson != null) || (data.municipiosGeoJson != null)
			|| (data.statesGeoJson != null) || (data.tpeBoundaryJson != null)) {
		console
				.log('Municipios, regions, boundary and states are available!!!');
		// Add the selected country polygon feature to the map.
		map.data.addGeoJson(data.regionGeoJson, {
			idPropertyName : "id"
		});

		// Add the selected country municipios polygon feature to the map.
		map.data.addGeoJson(data.municipiosGeoJSON, {
			idPropertyName : "id"
		});
		// TODO Remove or don't add the states/polygons.
		// Add the selected country states polygon features to the map.
		// map.data.addGeoJson(data.statesGeoJson, {
		// idPropertyName : "id"
		// });
		// Add tpe boundary json data

		map.data.addGeoJson(data.tpeBoundaryJson, {
			idPropertyName : "id"
		});
	}
}
/**
 * This function add style or styles the map Google features
 * 
 * @param map
 *            the map with features to style
 */
function addStyle(map) {

	//
	// Style the station markers
	map.data.setStyle(function(feature) {
		var name = feature.getProperty('name');
		var color = feature.getProperty('color');

		if ((feature.getProperty('name') == ENV_LFE)
				|| (feature.getProperty('name') == ENV_HFE)
				|| (feature.getProperty('name') == ENV_FE)) {

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
		}

		else if ((feature.getProperty('featureType') == 'REGION')) {

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
		}

		else if (feature.getProperty('name') == 'TPE_BOUNDARY') {
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
			// strokeColor : "#000000",
			strokeColor : "#bebebe",
			// strokeOpacity : 0.5,
			fillOpacity : 0,
			// fillOpacity: 0.20,
			fillColor : "#ffffff"
		};
	});
}
/**
 * The function that creates the Google Map for visualizing soil data and
 * associated plots
 * 
 * @param map
 * @param data
 */
function createSoilFeatures(map, data) {

	var markerClusterer = new MarkerClusterer(
			map,
			null,
			{
				styles : [
						{

							height : 53,
							url : "http://google-maps-utility-library-v3.googlecode.com/svn/trunk/markerclusterer/images/m1.png",
							width : 53
						},
						{
							height : 56,
							url : "http://google-maps-utility-library-v3.googlecode.com/svn/trunk/markerclusterer/images/m2.png",
							width : 56
						},
						{
							height : 66,
							url : "http://google-maps-utility-library-v3.googlecode.com/svn/trunk/markerclusterer/images/m3.png",
							width : 66
						},
						{
							height : 78,
							url : "http://google-maps-utility-library-v3.googlecode.com/svn/trunk/markerclusterer/images/m4.png",
							width : 78
						},
						{
							height : 90,
							url : "http://google-maps-utility-library-v3.googlecode.com/svn/trunk/markerclusterer/images/m5.png",
							width : 90
						} ]
			});

	markerClusterer.setMap(map);

	google.maps.event.addListener(map.data, 'addfeature', function(e) {
		if (e.feature.getGeometry().getType() === 'Point') {
			var marker = new google.maps.Marker({
				position : e.feature.getGeometry().get(),
				title : e.feature.getProperty('name'),
				map : map,
				icon : {
					/*
					 * url : 'img/' + e.feature.getProperty('color') + '.png'
					 */
					url : 'http://maps.google.com/mapfiles/ms/icons/'
							+ e.feature.getProperty('color') + '.png',
					animation : google.maps.Animation.DROP
				/*
				 * url : 'http://maps.google.com/mapfiles/ms/icons/' +
				 * e.feature.getProperty('color') + '-dot.png'
				 */
				/*
				 * size : new google.maps.Size(10, 10), scaledSize : new
				 * google.maps.Size(10, 10)
				 */
				// origin : new google.maps.Point(0,
				// 0),
				// anchor : new google.maps.Point(0,
				// 0)
				// url : "img/station_green.png"
				// url :
				// "${ctx}/img/station_green.png"
				}
			});
			// Add a click event to the marker
			google.maps.event.addListener(marker, 'click', function(marker, e) {
				return function() {
					// show an infowindow on
					// click
					// Show the info window
					// only for other
					// features but not
					// country
					infoWindow.setContent('<div class="info_window">' + '<h2>'
							+ e.feature.getProperty('name') + '</h2>'
							+ featureInfo(e) + '</div>');
					var anchor = new google.maps.MVCObject();
					// anchor.set("position",
					// e.latLng);
					anchor.set("position", e.feature.getGeometry().get());
					anchor.set("options", {
						pixelOffset : new google.maps.Size(0, 0)
					});
					infoWindow.open(map, anchor);
				};
			}(marker, e));

			// Add the Hover event to the marker
			google.maps.event.addListener(marker, 'mouseover', function(marker,
					e) {
				return function() {

					e.feature.setProperty('selected', true);
					$('#info').show();
					$('#info h2').text(e.feature.getProperty('name'));
					// $('#info
					// span').text(e.feature.getProperty('stationName'));
					$('#info span').html(featureInfo(e));

					// Display the plot only
					// for the soil point
					// features
					var probJSON = soilJson[e.feature.getProperty('code')];
					createSoilPlot(categoriesJSON, probJSON, true);

				};
			}(marker, e));

			// Add the mouseout event to the marker
			// Hide the info window
			google.maps.event.addListener(marker, 'mouseout', function(marker,
					e) {
				return function() {
					e.feature.setProperty('selected', false);
					$('#info').hide();
				};
			}(marker, e));

			// Add the marker to the marker cluster

			markerClusterer.addMarker(marker);

			// bounds.extend(e.feature.getGeometry().get());
			// map.fitBounds(bounds);
			map.setCenter(e.feature.getGeometry().get());
		}
	});

	// layer = map.data.addGeoJson(data.geoJson);
	map.data.addGeoJson(data.geoJson, {
		idPropertyName : "id"
	});
	map.data.setMap(null);

}
/**
 * Creates soil graphics
 */
function createSoilGraphics(data) {
	// By default display the soil plots
	// Use the clay texture code 'C'. This will display the chart for clay
	// by default.
	// var probJSON = dataJSON['C'];
	// createSoilPlot(categoriesJSON, probJSON, true);
	createSoilPlot(categoriesJSON, data, true);
}

/**
 * Adds click listerner events when the features are clicked.
 * 
 * @param map
 *            the map with the clicked features
 */
function addClickListener(map) {
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
		if (e.feature.getProperty('featureType') == OUTPUT_SOIL) {
			currentOutput = OUTPUT_SOIL;
			// TODO To eliminate this because the soil points are added to the
			// marker clusterer
			// TODO This is not accessed.
			// Display the plot only for the soil feature
			var probJSON = dataJSON[e.feature.getProperty('code')];
			// createSoilPlot(categoriesJSON, probJSON, true);
			// createSoilPlot(categoriesJSON, probJSON, true);

			createSoilGraphics(probJSON);

		} else if (e.feature.getProperty('featureType') == OUTPUT_TPE) {
			// Display the TPE Box plot
			createTPEBoxPlot(categoriesJSON, boxJSON, true);

			// createLAIPlot(categoriesList, seriesLai, plotBands);
			// createPCEWPlot(categoriesList, seriesPcew, plotBands);
			// createTempRainPlot(categoriesTempRain, seriesTempRain, true);
			var environmentSeries;
			currentOutput = OUTPUT_TPE;
			if (e.feature.getProperty('name') == ENV_HFE) {
				// Create Graphics
				createTPEGraphics(hfeSeries);
				clickedFeature = ENV_HFE;
			} else if (e.feature.getProperty('name') == ENV_LFE) {
				// Create Graphics
				createTPEGraphics(lfeSeries);
				clickedFeature = ENV_LFE;
			} else if (e.feature.getProperty('name') == ENV_FE) {
				// Create Graphics
				createTPEGraphics(feSeries);
				clickedFeature = ENV_FE;
			}

		} else if (e.feature.getProperty('featureType') == 'STABILITY') {

			// createLAIPlot(categoriesList, seriesLai, plotBands);
			// createPCEWPlot(categoriesList, seriesPcew, plotBands);
			// createTempRainPlot(categoriesTempRain, seriesTempRain, true);
			var environmentSeries;
			currentOutput = 'TPE';
			if (e.feature.getProperty('name') == 'HFE') {
				environmentSeries = hfeSeries;
				// TODO Create stability graphics
				clickedFeature = 'HFE';
			} else if (e.feature.getProperty('name') == 'LFE') {
				environmentSeries = lfeSeries;
				// TODO Create stability graphics
				clickedFeature = 'LFE';
			} else if (e.feature.getProperty('name') == 'FE') {
				environmentSeries = feSeries;
				// TODO Create stability graphics
				clickedFeature = 'FE';
			}

		}

		else if (e.feature.getProperty('featureType') == 'CLIMATE') {
			currentOutput = 'CLIMATE';
			// Display the plot only for the soil point features
			// var plotJSON = e.feature.getProperty('plotData');
			// createClimatePlot(plotJSON, true);

			// Create rainfall and radiation plot
			// rainfallRadiationPlot(climateSeriesJSON, true,
			// e.feature.getProperty('stationId'),
			// e.feature.getProperty('name'));

			createClimateGraphics(e.feature.getProperty('stationId'), e.feature
					.getProperty('name'));

			clickedFeature = e.feature.getProperty('stationId');
			clickedFeatureName = e.feature.getProperty('name');
		}
	});
}

function addMouseOverListener(map) {
	/* Add mouse events to trigger the TPE feature info */
	map.data
			.addListener(
					'mouseover',
					function(e) {
						e.feature.setProperty('selected', true);

						// Don't display the info window for the feature type
						// country

						if (e.feature.getProperty('name') == 'TPE_BOUNDARY') {
							// TODO: If feature is boundary, then Do nothing
							// console.log(e.feature.getProperty('name'));
						}
						// else if (e.feature.getProperty('name') == 'Brazil') {
						// TODO: If feature is country, then Do nothing
						// console.log(e.feature.getProperty('name'));
						// } else if (e.feature.getProperty('name') ==
						// 'Colombia') {

						// console.log(e.feature.getProperty('name'));
						// }
						else {

							$('#info').show();
							if (e.feature.getProperty('featureType') == OUTPUT_TPE) {
								$('#info h2')
										.text(
												'TPE: '
														+ e.feature
																.getProperty('name'));
								// $('#info
								// h2').text(e.feature.getProperty('name'));

								var strokeColor, fillColor;

								// Add plots using series data
								if (e.feature.getProperty('name') == ENV_HFE) {
									// Create the feature graphics
									createTPEGraphics(hfeSeries)

									strokeColor = COLOR_HFE;// #00ff00
									fillColor = COLOR_HFE;// #00ff00

									// console.log(hfeSeries);

								} else if (e.feature.getProperty('name') == ENV_LFE) {
									// Create graphics
									createTPEGraphics(lfeSeries)

									strokeColor = COLOR_LFE;// #ff0000
									fillColor = COLOR_LFE;// #ff0000

								} else if (e.feature.getProperty('name') == ENV_FE) {

									// Create feature graphics
									createTPEGraphics(feSeries)
									// console.log(feSeries);

									strokeColor = COLOR_FE;// #0041a0
									fillColor = COLOR_FE;// #0041a0
								}

								// Revert feature style
								map.data.overrideStyle(e.feature, {
									strokeColor : strokeColor,
									fillOpacity : 0.5,
									fillColor : fillColor
								});

							}

							else if ((e.feature.getProperty('featureType') == OUTPUT_SOIL)
									|| (e.feature.getProperty('featureType') == 'STATION')
									|| (e.feature.getProperty('featureType') == 'ENVIRONMENT')) {
								// Display the plot only for the soil point
								// features
								if (dataJSON != null) {
									var probJSON = dataJSON[e.feature
											.getProperty('code')];
									// createSoilPlot(categoriesJSON, probJSON,
									// true);
									createSoilGraphics(probJSON);
								}
							} else if (e.feature.getProperty('featureType') == OUTPUT_STABILITY) {
								// $('#info
								// h2').text(e.feature.getProperty('name'));
								$('#info h2')
										.text(
												'STABILITY: '
														+ e.feature
																.getProperty('name'));
							} else if (e.feature.getProperty('featureType') == OUTPUT_AREA) {
								// $('#info
								// h2').text(e.feature.getProperty('name'));
								$('#info h2')
										.text(
												'RICE AREA: '
														+ e.feature
																.getProperty('name'));
							} else if (e.feature.getProperty('featureType') == OUTPUT_CLIMATE) {

								$('#info h2').text(
										e.feature.getProperty('name'));
								if (climateSeriesJSON != null)
									createClimateGraphics(e.feature
											.getProperty('stationId'),
											e.feature.getProperty('name'));
							} else if (e.feature.getProperty('featureType') == 'REGION') {

								$('#info h2').text(
										e.feature.getProperty('name'));
							}

							else {
								$('#info h2').text(
										e.feature.getProperty('name'));
							}

							// $('#info
							// h2').text(e.feature.getProperty('name'));
							// $('#info
							// span').text(e.feature.getProperty('stationName'));
							$('#info span').html(featureInfo(e));
						}

					});
}
/**
 * This adds the mouseout events
 * 
 * @param map
 *            the map with features being affected.
 */
function addMouseOutListener(map) {
	map.data.addListener('mouseout', function(e) {
		e.feature.setProperty('selected', false);
		// Hide the Info window
		$('#info').hide();

		/*
		 * map.data.overrideStyle(e.feature, { strokeColor : '#990000',
		 * fillColor : '#009900' });
		 */

		if ((e.feature.getProperty('name') == ENV_LFE)
				|| (e.feature.getProperty('name') == ENV_HFE)
				|| (e.feature.getProperty('name') == ENV_FE)) {
			map.data.overrideStyle(e.feature, {
				strokeColor : e.feature.getProperty('colour'),
				fillOpacity : 0.20,
				fillColor : e.feature.getProperty('colour')
			});
		}

		// Dynamically show the graphics using the last clicked feature id or
		// key
		if (currentOutput == OUTPUT_TPE) {
			var lastSeries;
			if (clickedFeature == ENV_HFE)
				lastSeries = hfeSeries;
			else if (clickedFeature == ENV_LFE)
				lastSeries = lfeSeries;
			else if (clickedFeature == ENV_FE)
				lastSeries = feSeries;

			if (e.feature.getProperty('featureType') != OUTPUT_TPE) {
				// Iterate the list of series map
				createTPEGraphics(lastSeries);
			}
		} else if (currentOutput == OUTPUT_CLIMATE) {

			if (e.feature.getProperty('featureType') == 'REGION')
				map.data.overrideStyle(e.feature, {
					strokeColor : e.feature.getProperty('colour'),
					fillOpacity : 0.20,
					fillColor : e.feature.getProperty('colour')
				});

			// rainfallRadiationPlot(climateSeriesJSON, true,
			// clickedFeature,clickedFeatureName);

			createClimateGraphics(clickedFeature, clickedFeatureName);

		}
		// TODO Add Stability, Area and Soil
	});
}

/**
 * This function returns the feature info details for the currently hovered or
 * clicked feature
 * 
 * @param event
 *            the feature event
 * @returns {String} feature info
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

		if (event.feature.getProperty("country") != null)
			$htmlText = $htmlText + '<div>Country: '
					+ event.feature.getProperty("country");

		$htmlText = $htmlText + '<div>Municipality/Station: '
				+ event.feature.getProperty("stationName");

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
		// $htmlText = $htmlText + '<div>Radiation: ' +
		// event.feature.getProperty("radiation") + '</div>';

	} else if (event.feature.getProperty('featureType') == OUTPUT_TPE) {

		$htmlText = '<div style="color:' + event.feature.getProperty('colour')
				+ '";">' + event.feature.getProperty('description') + '</div>';

		$htmlText = $htmlText + '<div>Crop: '
				+ event.feature.getProperty("crop") + '</div>';

		$htmlText = $htmlText + '<div>Cultivar: '
				+ event.feature.getProperty("cultivar") + '</div>';
	} else if (event.feature.getProperty('featureType') == OUTPUT_STABILITY) {

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

// Create the climate plot chart
function createClimatePlot(seriesJSON, smallPlot) {

	// $('#rain_radiation').show();
	var renderTo = 'rain_radiation';// Default div
	var dialogDiv = 'dialog-chart';
	var fontSize = '8px';
	var titleFontSize = '10px;'
	var legendX, legendY, width, height;
	var marginBottom, marginTop, marginLeft, marginRight, spacingBottom, credits, legend, labelsEnabled, yAxisTitle, xAxisTitle, exporting;
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
		credits = false;
		legend = false;
		labelsEnabled = false;
		exporting = false;
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
		credits = true;
		legend = true;
		labelsEnabled = true;
		exporting = true;
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
							// borderRadius : 5,
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
										this.renderer.image(
												'img/ccafs_logo.png', 90, 0,
												120, 50).on('click',
												function() {
													// Add CCAFS Link
													// location.href =
													// 'http://www.ccafs-tpe.org'
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
							enabled : credits,
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
								enabled : labelsEnabled,
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
							},
							labels : {
								enabled : labelsEnabled
							}
						},
						/*
						 * legend : { enabled : false },
						 */
						legend : {
							enabled : legend,
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
							enabled : exporting,
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
	var marginBottom, marginTop, marginLeft, marginRight, spacingBottom, credits, legend, labelsEnabled, yAxisTitle, xAxisTitle, exporting;
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
		credits = false;
		legend = false;
		labelsEnabled = false;
		exporting = false;
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
		credits = true;
		legend = true;
		labelsEnabled = true;
		exporting = true;
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
							// borderRadius : 5,
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
										this.renderer.image(
												'img/ccafs_logo.png', 90, 0,
												120, 50).on('click',
												function() {
													// Add CCAFS Link
													// location.href =
													// 'http://www.ccafs-tpe.org'
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
							enabled : credits,
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
								enabled : labelsEnabled,
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
								enabled : labelsEnabled,
								format : '{value}%'
							},
							floor : 0,
							ceiling : 100
						},
						legend : {
							enabled : legend,
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
							enabled : exporting,
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
	// console.log('These are categories');
	// console.log(categories);
	// console.log('These are series');
	// console.log(series);
	// $('#plot_box').show();
	var renderTo = 'plot_box';// Default div
	var dialogDiv = 'dialog-chart';
	var fontSize = '8px';
	var titleFontSize = '10px;'
	var legendX, legendY, width, height;
	var marginBottom, marginTop, marginLeft, marginRight, spacingBottom, credits, legend, labelsEnabled, yAxisTitle, xAxisTitle, exporting;
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
		credits = false;
		legend = false;
		labelsEnabled = false;
		yAxisTitle = null;
		xAxisTitle = null;
		exporting = false;
	} else {
		// Jsp page div
		renderTo = dialogDiv;
		// Chart font size for the dialog chart
		fontSize = '14px';
		titleFontSize = '30px';
		legendX = 120;
		legendY = 20;
		spacingBottom = 50;
		// width = 600;
		// height = null;
		credits = true;
		legend = true;
		labelsEnabled = true;
		yAxisTitle = 'Yield (Kg/ha)';
		xAxisTitle = 'Harvest Years';
		exporting = true;
	}
	var chart = new Highcharts.Chart(
			{
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
					// borderRadius : 5,
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
										.image('img/zoom-in.png', 10, 2, 20, 20)
										.on(
												'click',
												function() {
													createTPEBoxPlot(
															categories, series,
															false);
													$('#dialog-plot').dialog(
															'open');
													// $(".ui-widget-header").hide();
													// $('.ui-dialog-titlebar').remove();
													// $(".ui-dialog-titlebar").hide();
												})
										.css({
											cursor : 'Pointer'
										})
										.attr({
											class : 'zoom'
										})
										.css({
											position : 'relative',
											"margin-left" : "-90px"
										// opacity : 0.75
										})
										.attr({
											zIndex : 300,
											id : 'zoomImage'
										})
										.add()
										.on(
												'mouseover',
												function() {

													// Call the function that
													// displays the info
													// about to view when the
													// control is clicked
													graphicsInfo(
															'Click to view a zoomed Boxplot',
															'You will view a more bigger Boxplot when you click.');

												}).on('mouseout', function() {
											// Hide the info window when
											// the mouse leaves the
											// control
											$('#info').hide();

										}).add();

							} else {
								this.renderer.image('img/ccafs_logo.png', 90,
										0, 120, 50).on('click', function() {
									// Add CCAFS Link
									// location.href =
									// 'http://www.ccafs-tpe.org'
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
					enabled : credits,
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
					enabled : legend,
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
				xAxis : [ {
					categories : series.categories,
					// offset : 0,
					// gridLineWidth : 1,
					// width : 200,
					title : {
						text : xAxisTitle,
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
						enabled : labelsEnabled,
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
						text : yAxisTitle,
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
						enabled : labelsEnabled,
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
					enabled : exporting,
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
	var marginBottom, marginTop, marginLeft, marginRight, spacingBottom, credits, legend, labelsEnabled, yAxisTitle, xAxisTitle, exporting;
	var showLegendTitle = true, showLegendTitle2 = true, titleSet = false, titleSet2 = false;
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

		credits = false;
		legend = false;
		labelsEnabled = false;
		exporting = false;
	} else {
		// Jsp page div
		renderTo = dialogDiv;
		// Chart font size for the dialog chart
		fontSize = '14px';
		titleFontSize = '30px';
		legendX = 70;
		legendY = 80;
		spacingBottom = 50;
		width = null;
		height = null;
		credits = true;
		legend = true;
		labelsEnabled = true;
		exporting = true;
	}
	$('#' + renderTo)
			.highcharts(
					{
						credits : {
							enabled : credits,
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
							// borderRadius : 5,
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
										this.renderer.image(
												'img/ccafs_logo.png', 90, 0,
												120, 50).on('click',
												function() {
													// Add CCAFS Link
													// location.href =
													// 'http://www.ccafs-tpe.org'
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
							tickInterval : 7,
							labels : {
								enabled : labelsEnabled
							}
						},
						yAxis : {
							title : {
								text : seriesMap.yaxis
							},
							labels : {
								enabled : labelsEnabled,
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
							enabled : legend,
							layout : 'vertical',
							verticalAlign : 'top',
							align : 'left',
							// align : 'left',
							x : legendX,
							// verticalAlign : 'bottom',
							y : legendY,
							floating : true,
							// draggable: true,

							/*
							 * title : { text : seriesMap.legendTitle },
							 */
							// / floating : true,
							// draggable : true
							// zIndex : 400
							labelFormatter : function() {
								if (this.type == 'column') {
									// if (!titleSet) {
									// titleSet = true;
									// return seriesMap.legendTitle;
									// }

									// if (showLegendTitle == true) {
									// showLegendTitle = false;
									return seriesMap.legendTitle + ' '
											+ this.name;
									// }
									// return this.name;

								} else if ((this.type == 'spline')
										|| (this.type == 'Dash')
										|| (this.type == 'LongDashDot')) {
									// if (!titleSet2) {
									// titleSet2 = true;
									// return seriesMap.legendTitle2;
									// }
									// if (showLegendTitle2 == true) {
									// showLegendTitle2 = false;
									return seriesMap.legendTitle2 + ' '
											+ this.name;
									// }
									// return this.name;
								} else
									return this.name;

								/*
								 * if (this.x == 0) return '<h2>' +
								 * seriesMap.legendTitle + '</h2>' +
								 * this.name; else if (this.x == 2) return '<div
								 * class="second"><h2>Title Group 2</h2>' +
								 * this.name + '</div>'; else return this.name;
								 */

							},
							// symbolPadding: 10,
							backgroundColor : '#FFFFFF',
							useHTML : true
						},
						series : seriesMap.series,
						exporting : {
							enabled : exporting,
							buttons : {
								contextButton : {
									// symbol: 'circle',
									symbol : 'url(img/download_32.png)'
								}
							}
						}
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
	var marginBottom, marginTop, marginLeft, marginRight, spacingBottom, credits, legend, labelsEnabled, yAxisTitle, xAxisTitle, exporting;
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

		credits = false;
		legend = false;
		labelsEnabled = false;
		exporting = false;
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
		credits = true;
		legend = true;
		labelsEnabled = true;
		exporting = true;
	}

	$('#' + renderTo).highcharts(
			{
				credits : {

					enabled : credits,
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
					// borderRadius : 5,
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
									// location.href =
									// 'http://www.ccafs-tpe.org'
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
						enabled : labelsEnabled,
						overflow : 'justify',
						format : '{value:.1f}'
					}
				},
				legend : {
					enabled : legend,
					// layout : 'horizontal',
					// align : 'left',
					x : legendX,
					// verticalAlign : 'bottom',
					y : legendY,
					floating : true,

					title : {
						text : seriesMap.legendTitle
					}
				// floating : true,
				// draggable : true
				// zIndex : 400
				},
				series : seriesMap.series,
				exporting : {
					enabled : exporting,
					buttons : {
						contextButton : {
							// symbol: 'circle',
							symbol : 'url(img/download_32.png)'
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
	var marginBottom, marginTop, marginLeft, marginRight, spacingBottom, credits, legend, labelsEnabled, yAxisTitle, xAxisTitle, exporting;
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
		credits = false;
		legend = false;
		labelsEnabled = false;
		exporting = false;
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
		credits = true;
		legend = true;
		labelsEnabled = true;
		exporting = true;
	}
	$('#' + renderTo)
			.highcharts(
					{
						credits : {
							enabled : credits,
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
							// borderRadius : 5,
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
										this.renderer.image(
												'img/ccafs_logo.png', 90, 0,
												120, 50).on('click',
												function() {
													// Add CCAFS Link
													// location.href =
													// 'http://www.ccafs-tpe.org'
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
							labels : {
								enabled : labelsEnabled
							}
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
								enabled : labelsEnabled,
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
							enabled : legend,
							// layout : 'horizontal',
							// align : 'left',
							x : legendX,
							// verticalAlign : 'bottom',
							y : legendY,
							floating : true,
							title : {
								text : seriesMap.legendTitle
							}
						// floating : true,
						// draggable : true
						// zIndex : 400
						},
						series : seriesMap.series,

						exporting : {
							enabled : exporting,
							buttons : {
								contextButton : {
									// symbol: 'circle',
									symbol : 'url(img/download_32.png)'
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
	var legendX, legendY, spacingBottom, itemWidth, width, height, credits, legend, labelsEnabled, yAxisTitle, xAxisTitle, exporting;
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

		credits = false;
		legend = false;
		labelsEnabled = false;
		xAxisTitle = 'Months';
		exporting = false;
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
		credits = true;
		legend = true;
		labelsEnabled = true;
		xAxisTitle = 'Months';
		exporting = true;
	}

	$('#' + renderTo).highcharts(
			{
				credits : {
					enabled : credits,
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
					// borderRadius : 5,
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
									// location.href =
									// 'http://www.ccafs-tpe.org'
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
						enabled : labelsEnabled,
						rotation : -45,
						style : {
							fontSize : fontSize,
							fontFamily : 'Verdana, sans-serif'
						}
					},
					title : {
						text : xAxisTitle
					},
					crosshair : true

				},
				yAxis : [ { // Primary yAxis
					labels : {
						enabled : labelsEnabled,
						// format : '{value}°C',
						style : {
							color : '#4572A7',
							fontSize : fontSize
						},
						format : '{value:.1f}'
					// format : '{value:.2f}'
					},
					min : 0,
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
						enabled : labelsEnabled,
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
				},

				{ // Primary yAxis
					labels : {
						enabled : labelsEnabled,
						// format : '{value}°C',
						style : {
							color : '#4572A7',
							fontSize : fontSize
						},
						format : '{value:.1f}'
					// format : '{value:.2f}'
					},
					min : 0,
					title : {
						text : 'Min Temperature (°C)',
						style : {
							color : '#4572A7'
						}
					}
				// opposite : true
				}, { // Tertially yAxis, Max Temperature
					title : {
						text : 'Max Temperature (°C)',
						rotation : -90,
						x : 10,
						style : {
							color : '#4572A7'
						}
					},
					min : 0,
					labels : {
						enabled : labelsEnabled,
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
				}

				],
				tooltip : {
					shared : true
				},
				legend : {
					enabled : legend,
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
					enabled : exporting,
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
	var marginBottom, marginTop, marginLeft, marginRight, spacingBottom, credits, legend, labelsEnabled, yAxisTitle, xAxisTitle, exporting;
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
		credits = false;
		legend = false;
		labelsEnabled = false;
		yAxisTitle = seriesMap.yaxis;
		xAxisTitle = seriesMap.xaxis;
		exporting = false;
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
		credits = true;
		legend = true;
		labelsEnabled = true;
		yAxisTitle = seriesMap.yaxis;
		xAxisTitle = seriesMap.xaxis;
		exporting = true;
	}
	$('#' + renderTo)
			.highcharts(
					{
						credits : {
							enabled : credits,
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
							// borderRadius : 5,
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
										this.renderer.image(
												'img/ccafs_logo.png', 90, 0,
												120, 50).on('click',
												function() {
													// Add CCAFS Link
													// location.href =
													// 'http://www.ccafs-tpe.org'
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
								text : xAxisTitle,
								align : 'high'
							},
							gridLineWidth : 1,
							plotBands : seriesMap.plotBands,
							// min : seriesMap.categories[0],
							// min:14,
							max : 91,
							tickInterval : 7,
							labels : {
								enabled : labelsEnabled
							}
						},
						yAxis : {
							title : {
								text : yAxisTitle
							},
							// gridLineWidth : 1,
							labels : {
								enabled : labelsEnabled,
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
							enabled : legend,
							// layout : 'horizontal',
							// align : 'left',
							x : legendX,
							// verticalAlign : 'bottom',
							y : legendY,
							floating : true,
							title : {
								text : seriesMap.legendTitle
							}
						// floating : true,
						// draggable : true
						// zIndex : 400
						},
						series : seriesMap.series,

						exporting : {
							enabled : exporting,
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
	var marginBottom, marginTop, marginLeft, marginRight, spacingBottom, credits, legend, labelsEnabled, yAxisTitle, xAxisTitle, exporting;
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
		credits = false;
		legend = false;
		labelsEnabled = false;
		yAxisTitle = seriesMap.yaxis;
		xAxisTitle = seriesMap.xaxis;
		exporting = false;
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
		credits = true;
		legend = true;
		labelsEnabled = true;
		yAxisTitle = seriesMap.yaxis;
		xAxisTitle = seriesMap.xaxis;
		exporting = true;
	}

	var chart = new Highcharts.Chart(
			{

				// $('#' + renderTo).highcharts(
				// {
				credits : {
					enabled : credits,
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
					// borderRadius : 5,
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
									// location.href =
									// 'http://www.ccafs-tpe.org'
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
						// text : seriesMap.xaxis,
						text : xAxisTitle,
						align : 'high'
					},
					gridLineWidth : 1,
					plotBands : seriesMap.plotBands,
					max : 91,
					tickInterval : 7,
					labels : {
						enabled : labelsEnabled
					}
				},
				yAxis : {
					title : {
						// text : seriesMap.yaxis
						text : yAxisTitle
					},
					labels : {
						enabled : labelsEnabled,
						overflow : 'justify',
						format : '{value:.1f}'
					}
				},
				legend : {
					enabled : legend,
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
					enabled : exporting,
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

function createGraphicsPlot(seriesMap, environment, smallPlot, nameOfPlot) {

}
