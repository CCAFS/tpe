var colorValues = [ "red", "blue", "green", "yellow", "purple", "pink",
		"lightblue", "orange" ], colorValuesTPE = [ "red", "blue", "green" ], colorValuesStability = [
		"#990000", "#009900", "#000099" ], colorValuesClimate = [ "black" ], colors;
// The variable that stores the clicked soil point.
var clickedSoilCode, sel_country, scountry;
var sel_crop,sel_cultivar,sel_map;

/**
 * The function that initializes the Google Map
 */
function initializeMap(data) {
	scountry = document.getElementById('select_country');
	sel_country = scountry.options[scountry.selectedIndex].text;
	sel_crop = $("#select_crop option:selected").text();
	
	sel_cultivar = $("#select_cultivar option:selected").text();
	sel_map = $("#select_output option:selected").text();

	//console.log(sel_map);
	//console.log(sel_cultivar);
	
	var infoWindow = new google.maps.InfoWindow({
		content : ""
	});

	viewPlot();
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

	// Create two data layers.
	// The crop production or growing layer
	var productionLayer = new google.maps.Data();
	// The features layer
	var featuresLayer = new google.maps.Data();
	// Create the new map and make sure the tpe_map div
	// exists
	map = new google.maps.Map(document.getElementById('tpe_map'), mapOptions);

	// If the selected map option is SOIL then create the marker cluster
	// features for the soil points
	// var bounds = new google.maps.LatLngBounds();
	if (selectedOutput.toUpperCase() == OUTPUT_SOIL) {
		// Load admin regions
		loadAdminRegions(map, data);

		// Create the soil map. Pass in the features and production layers.
		// Features layer will contain the soil features and the production
		// layer will contain the rice production region.
		createSoilFeatures(map, data, featuresLayer, productionLayer);
		// Add legend
		createLegend(map);

		// Add graphics.
		// Use default json data
		// By default display the soil plots
		// Use the clay texture code 'C'. This will display the chart for clay
		// by default.
		// var probJSON = dataJSON['C'];
		createSoilGraphics(seriesJSON['C']);

	} else if (selectedOutput.toUpperCase() == OUTPUT_CLIMATE) {
		// Load admin regions
		loadAdminRegions(map, data);

		// Add features
		createClimateFeatures(map, data);

		// Add legend
		createLegend(map);

		// Add climate graphics
		createClimateGraphics(null, null);
	}

	else if (selectedOutput.toUpperCase() == OUTPUT_STABILITY) {
		// Load admin regions
		loadAdminRegions(map, data);
		// Create Stability map with features
		createStabilityFeatures(map, data);
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
 * Creates the climate features.
 * 
 * @param map
 *            the map object to contain the features
 * @param data
 *            the data containing the features
 */
function createClimateFeatures(map, data) {
	// Add climate regions
	// Add the crop production region if not null
	$.ajax({

		type : "GET",
		contentType : "application/json; charset=utf-8",
		url : "/tpe/resources/" + sel_country.toLowerCase()
				+ "_rice_growing.json",
		data : "{}",
		dataType : "json",
		success : function(data) {
			if (null != data) {
				map.data.addGeoJson(data, {
					idPropertyName : "id"
				});
			}
		},
		error : function(data) {
			// alert("Error");
		}
	});
	// if (data.growingRegionsJson != null) {
	// map.data.addGeoJson(data.growingRegionsJson, {
	// idPropertyName : "id"
	// });
	// }
	// Add the GeoJson features to the map, stations
	if (data.featuresJson != null)
		map.data.addGeoJson(data.featuresJson, {
			idPropertyName : "id"
		});
}
/**
 * Creates climate graphics
 */
function createClimateGraphics(dataSeries, stationName) {
	// By default display the climate charts
	// Create rainfall and radiation plot
	// Use a default station id and it is name
	// TODO Get default station id and name from the database
	if (dataSeries != null && stationName != null)
		rainfallRadiationPlot(dataSeries, true, stationName);
}

/**
 * This function creates the legend depending on the selected output
 */
function createLegend(map) {
	// get the legend container, create a legend, add a legend renderer fn
	// var $legendContainer = $('#legend-container')
	var $legendContainer = $('<div id="legend-container">'), $legend = $(
			'<div id="legend">').appendTo($legendContainer), renderLegend = function(
			colorValuesArray) {
		$legend.empty();
		$legend.append($('<h3>' + legendTitle + '</h3>'));

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
					texture = 'Sandy Clay';
				} else if (val == 'pink') {
					texture = 'Sand Clay Loam';
				} else if (val == 'lightblue') {
					texture = 'Silt Clay Loam';
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
			// Gray color for municipality
			var $leg_municipio = $('<div style="height:25px;">').append(
					$('<div class="legend-color-box">').css({
						backgroundColor : '#bebebe',
					})).append(
					$('<div class="legend_text">').html('Municipality'));

			$legend.append($leg_municipio);
		} else if (selectedOutput.toUpperCase() == OUTPUT_STABILITY) {

			$.each(colorValuesArray, function(index, val) {
				var env_col;
				if (val == '#990000') {
					env_col = 'Environment Stability Low (50-62%)';
				} else if (val == '#000099') {
					env_col = 'Environment Stability Middle (62-87%)';
				} else if (val == '#009900') {
					env_col = 'Environment Stability High (>87%)';
				}

				var $div = $('<div style="height:25px;">').append(
						$('<div class="legend-color-box">').css({
							backgroundColor : val,
						}))
						.append($('<div class="legend_text">').html(env_col));

				$legend.append($div);
			});
			// Gray color for municipality
			var $leg_municipio = $('<div style="height:25px;">').append(
					$('<div class="legend-color-box">').css({
						backgroundColor : '#bebebe',
					})).append(
					$('<div class="legend_text">').html('Municipality'));

			$legend.append($leg_municipio);
		} else if (selectedOutput.toUpperCase() == OUTPUT_CLIMATE) {

			// Black for boundary or country
			var $leg_rice_prod = $('<div style="height:25px;">').append(
					$('<div class="legend-color-box">').css({
						// backgroundColor : '#90ee90'// Light green
						backgroundColor : '#009900'// Light green
					})).append(
					$('<div class="legend_text">').html(
							'Rice Production Region'));

			$legend.append($leg_municipio);
			$legend.append($leg_rice_prod);

			$.each(colorValuesStability, function(index, val) {
				var climate_feature, $div;
				if (val == '#990000') {
					climate_feature = 'Weather Info';
					$div = $('<div style="height:25px;">').append(
							$('<div class="legend-color-box">').css({
								backgroundColor : val,
							})).append(
							$('<div class="legend_text">')
									.html(climate_feature));
				}
				// else if (val == 'red') {
				// climate_feature = 'Station';
				// }

				$legend.append($div);
			});
			// Gray color for municipality
			var $leg_municipio = $('<div style="height:25px;">').append(
					$('<div class="legend-color-box">').css({
						backgroundColor : '#bebebe'
					})).append(
					$('<div class="legend_text">').html('Municipality'));
		}
	}

	if (selectedOutput.toUpperCase() == OUTPUT_SOIL)
		renderLegend(colorValues);
	else if (selectedOutput.toUpperCase() == OUTPUT_TPE)
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
 * Adds the stability map features to the map
 * 
 * @param map
 *            the initialized map to contain the features
 * @param data
 *            the data containing features to add
 */
function createStabilityFeatures(map, data) {
	// Add the GeoJson features to the map

	$.ajax({
		type : "GET",
		contentType : "application/json; charset=utf-8",
		url : "/tpe/resources/" + sel_country.toLowerCase() + "_"
				+ sel_crop.toLowerCase() + "_brs primavera_stability.json",
		data : "{}",
		dataType : "json",
		success : function(data) {
			if (null != data) {
				map.data.addGeoJson(data, {
					idPropertyName : "id"
				});
			}
		},
		error : function(data) {
			// alert("Error");
		}
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
//console.log("/tpe/resources/" + sel_country.toLowerCase() + "_" + sel_crop.toLowerCase() + "_"+ sel_cultivar.toLowerCase() + "_"+ sel_map.toLowerCase() + ".json");
	$.ajax({

		type : "GET",
		contentType : "application/json; charset=utf-8",
		//url : "/tpe/resources/" + sel_country.toLowerCase() + "_" + sel_crop.toLowerCase() + "_brs primavera_tpe.json",
		url : "/tpe/resources/" + sel_country.toLowerCase() + "_" + sel_crop.toLowerCase() + "_"+ sel_cultivar.toLowerCase() + "_"+ sel_map.toLowerCase() + ".json",
		data : "{}",
		dataType : "json",
		success : function(data) {
			if (null != data) {
				map.data.addGeoJson(data, {
					idPropertyName : "id"
				});
			}
		},
		error : function(data) {
			// alert("Error");
		}
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
	if (series != null) {
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
	} else {
		clearCharts('#plot_box');
		clearCharts('#plot_pcew');
		clearCharts('#plot_raincum');
		clearCharts('#plot_rainsum');
		clearCharts('#plot_wagt');
		clearCharts('#plot_lai');
	}
}

function clearCharts(div) {
	
		var chart = $(div).highcharts();
		if (chart != null) {
		var seriesLength = chart.series.length;
		for (var i = seriesLength - 1; i > -1; i--) {
			chart.series[i].remove();
		}
	}
}
/**
 * Loads the administrative regions (municipios, states, countries, boundaries.
 * 
 * @param map
 * @param data
 */
function loadAdminRegions(map, data) {
	// Country boundary
	$.ajax({

		type : "GET",
		contentType : "application/json; charset=utf-8",
		url : "/tpe/resources/" + sel_country.toLowerCase() + ".json",
		data : "{}",
		dataType : "json",
		success : function(data) {

			if (data != null)
				// Add the selected country polygon feature to the map.
				map.data.addGeoJson(data, {
					idPropertyName : "id"
				});
		},
		error : function(data) {
			// alert("Error");
		}
	});

	// Municipalities
	$.ajax({

		type : "GET",
		contentType : "application/json; charset=utf-8",
		url : "/tpe/resources/" + sel_country.toLowerCase()
				+ "_municipios.json",
		data : "{}",
		dataType : "json",
		success : function(data) {

			// Add the selected country municipios polygon feature to the map.
			if (data != null)
				map.data.addGeoJson(data, {
					idPropertyName : "ADM2_NAME"
				});
		},
		error : function(data) {
			// alert("Error");
		}
	});
	// TODO Remove or don't add the states/polygons.
	// Add the selected country states polygon features to the map.
	// map.data.addGeoJson(data.statesGeoJson, {
	// idPropertyName : "id"
	// });

	// TPE Boundary
	$.ajax({

		type : "GET",
		contentType : "application/json; charset=utf-8",
		url : "/tpe/resources/" + sel_country.toLowerCase() + "_boundary.json",
		data : "{}",
		dataType : "json",
		success : function(data) {

			// Add tpe boundary json data
			if (data != null)
				map.data.addGeoJson(data, {
					idPropertyName : "id"
				});
		},
		error : function(data) {
			// alert("Error");
		}
	});

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
				|| (feature.getProperty('name') == ENV_FE)
				|| (feature.getProperty('name') == STABILITY_HIGH)
				|| (feature.getProperty('name') == STABILITY_MIDDLE)
				|| (feature.getProperty('name') == STABILITY_LOW)
				|| (feature.getProperty('name') == 'Cluster_1')
				|| (feature.getProperty('name') == 'Cluster_2')
				|| (feature.getProperty('name') == 'Cluster_3')
				|| (feature.getProperty('name') == 'Cluster_4')
				|| (feature.getProperty('name') == 'Cluster_5')
				) {

			return {
				visible : true,
				clickable : true,
				title : name,
				strokeWeight : 1,
				strokeColor : feature.getProperty('colour'),
				// strokeOpacity : 1,
				// fillOpacity : 0.5,//1,// 0
				fillColor : feature.getProperty('colour'),
				zIndex : 1000
			};
		}

		else if (feature.getProperty('featureType') == 'REGION') {

			return {
				visible : true,
				clickable : true,
				title : name,
				strokeWeight : 1,
				// strokeColor : feature.getProperty('colour'),
				strokeColor : '#009900',
				// strokeOpacity : 1,
				// fillOpacity : 1,
				fillColor : '#009900',
				// fillColor : feature.getProperty('colour'),
				zIndex : 1000
			};
		} else if (feature.getProperty('featureType') == 'COUNTRY') {

			return {
				visible : true,
				clickable : true,
				title : name,
				strokeWeight : 2,
				// strokeColor : feature.getProperty('colour'),
				strokeColor : '#000',
				// strokeOpacity : 1,
				fillOpacity : 0,
				fillColor : '#ffffff',
				// fillColor : feature.getProperty('colour'),
				zIndex : 1000
			};
		} else if (feature.getProperty('name') == 'TPE_BOUNDARY') {
			return {
				visible : true,
				clickable : true,
				title : name,
				strokeWeight : 2,
				strokeColor : "#000",
				// fillOpacity : 1,
				// fillOpacity: 0.20,
				fillOpacity : 0,
				fillColor : "#ffffff"
			};
		} else if ((feature.getProperty('name') == 'municipality')
				|| (feature.getProperty('regionType') == 'municipality')) {
			return {
				visible : true,
				clickable : true,
				title : name,
				strokeWeight : 2,
				strokeColor : "#989898",
				// fillOpacity : 1,
				// fillOpacity: 0.20,
				fillColor : "#ffffff"
			};
		}

		else if (feature.getProperty('featureType') == OUTPUT_CLIMATE) {
			// If the feature belongs to Latin America, then return the blue
			// icons.
			if (feature.getProperty('featureIcon') == true)
				return {
					icon : {
						url : 'img/weather_info.png',// url :
						// 'img/station_blue.png',
						size : new google.maps.Size(10, 10),
						scaledSize : new google.maps.Size(10, 10)

					// path: google.maps.SymbolPath.CIRCLE,
					// scale: 3,
					// fillColor: "#F00",
					// fillOpacity: 0.4,
					// strokeWeight: 0.4
					},
					visible : true,
					clickable : true,
					title : name,
					strokeWeight : 1,
					strokeColor : "#ffffff",
					// fillOpacity : 0,
					fillOpacity : 1,
					// Add the title that will be displayed as the mouse
					// rollover
					title : feature.getProperty('name')
				};
			// For any other country, red icons will be returned.
			return {
				icon : {
					url : 'img/station_red.png',
					size : new google.maps.Size(10, 10),
					scaledSize : new google.maps.Size(10, 10)
				},
				visible : true,
				clickable : true,
				title : name,
				strokeWeight : 1,
				strokeColor : "#ffffff",
				// fillOpacity : 0,
				fillOpacity : 1,
				// Add the title that will be displayed as the mouse rollover
				title : feature.getProperty('name')
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
function createSoilFeatures(map, data, featLayer, prodLayer) {

	$.ajax({

		type : "GET",
		contentType : "application/json; charset=utf-8",
		url : "/tpe/resources/" + sel_country.toLowerCase()
				+ "_rice_growing.json",
		data : "{}",
		dataType : "json",
		success : function(data) {
			if (null != data) {
				map.data.addGeoJson(data, {
					idPropertyName : "id"
				});
				// map.data.addGeoJson(data.growingRegionsJson, {
				// idPropertyName : "id"
				// });

				prodLayer.setMap(map);
				// Set the style for the production layer
				prodLayer.setStyle(function(feature) {
					// All production features are of featureType REGION
					if (feature.getProperty('featureType') == 'REGION') {
						return {
							visible : true,
							clickable : true,
							title : name,
							strokeWeight : 1,
							// strokeColor : feature.getProperty('colour'),
							strokeColor : '#009900',
							// strokeOpacity : 1,
							// fillOpacity : 1,
							fillColor : '#009900',
							// fillColor : feature.getProperty('colour'),
							zIndex : 1000
						};
					}
				});

				// Addmouseover event
				prodLayer.addListener('mouseover', function(e) {
					if (e.feature.getProperty('featureType') == 'REGION') {
						e.feature.setProperty('selected', true);
						// Show the info window
						$('#info').show();
						// Show the country name
						$('#info h2').text(
								e.feature.getProperty('name')
										+ ' Rice Growing Region');
						// Hide the background image
						$('#info h2').css({
							'background-image' : 'none'
						});

						prodLayer.overrideStyle(e.feature, {
							fillOpacity : 0,
							fillColor : '#002200',
							strokeWeight : 1,
							// strokeColor :
							// feature.getProperty('colour'),
							strokeColor : '#002200'
						});
					}
				});

				// Add mouseout event
				prodLayer.addListener('mouseout', function(e) {
					if (e.feature.getProperty('featureType') == 'REGION') {
						e.feature.setProperty('selected', false);
						// Hide the Info window
						$('#info h2').text('');
						$('#info span').html('');
						$('#info').hide();
						prodLayer.overrideStyle(e.feature, {
							fillOpacity : 0.4,// 0.5,
							fillColor : '#009900',
							strokeWeight : 1,
							// strokeColor : feature.getProperty('colour'),
							strokeColor : '#009900'
						});
					}
				});
			}
		},
		error : function(data) {
			// alert("Error");
		}
	});

	// if (data.growingRegionsJson != null) {
	// map.data.addGeoJson(data.growingRegionsJson, {
	// idPropertyName : "id"
	// });
	// }
	// else
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
	// google.maps.event.addListener(map.data, 'addfeature', function(e) {
	google.maps.event.addListener(featLayer, 'addfeature', function(e) {
		if (e.feature.getGeometry().getType() === 'Point') {
			var marker = new google.maps.Marker({
				position : e.feature.getGeometry().get(),
				title : e.feature.getProperty('name'),
				map : map,
				// snippet : e.feature.getProperty('color'),
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
			/*
			 * google.maps.event.addListener(marker, 'click', function(marker,
			 * e) { return function() { // show an infowindow on // click //
			 * Show the info window // only for other // features but not //
			 * country infoWindow.setContent('<div class="info_window">' + '<h2>' +
			 * e.feature.getProperty('name') + '</h2>' + featureInfo(e) + '</div>');
			 * var anchor = new google.maps.MVCObject(); //
			 * anchor.set("position", // e.latLng); anchor.set("position",
			 * e.feature.getGeometry().get()); anchor.set("options", {
			 * pixelOffset : new google.maps.Size(0, 0) }); infoWindow.open(map,
			 * anchor); }; }(marker, e));
			 */

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
					var probJSON = seriesJSON[e.feature.getProperty('code')];
					createSoilPlot(categoriesJSON, probJSON, true);

				};
			}(marker, e));

			// Add the mouseout event to the marker
			// Hide the info window
			google.maps.event.addListener(marker, 'mouseout', function(marker,
					e) {
				return function() {
					e.feature.setProperty('selected', false);
					$('#info h2').text('');
					$('#info span').html('');
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

	// Add the mouseover for the marker clusterer. When the clusterer is
	// hovered, then the details will be displayed in the infor window (# of
	// soil points, color, etc).
	// Display the info window on top right corner of the map
	google.maps.event
			.addListener(
					markerClusterer,
					"mouseover",
					function(cluster) {

						$('#info').show();

						var m = cluster.getMarkers();
						var p = [];
						var clayMk = 0, sandMk = 0, loamMk = 0, clayLoamMk = 0, sandyLoamMk = 0, sandyClayMk = 0, sandClayLoamMk = 0, siltClayLoamMk = 0;
						for (var i = 0; i < m.length; i++) {
							var clor = m[i].getTitle();
							if (clor == 'Sand') {
								// Sand//red;
								sandMk++;
							} else if (clor == 'Loam') {
								// 'Loam'//blue;
								loamMk++;
							} else if (clor == 'Clay') {
								clayMk++// 'Clay'//green;
							} else if (clor == 'Sandy Loam') {
								sandyLoamMk++// 'Sand Loam'//yellow;
							} else if (clor == 'Clay Loam') {
								clayLoamMk++// 'Clay Loam'//purple;
							} else if (clor == 'Sandy Clay') {
								sandyClayMk++// 'Sandy Clay'//orange;
							} else if (clor == 'Sandy Clay Loam') {
								sandClayLoamMk++// 'Sand Clay Loam';//pink
							} else if (clor == 'Silty Clay Loam') {
								siltClayLoamMk++// 'Silt Clay Loam';//lightblue
							}
							// p.push(m[i].getPosition());
						}

						$('#info h2').text(cluster.getSize() + ' soil points');

						var $clusterText = '<div id="marker_clusterer">';
						// If the station was clicked
						if (sandMk > 0)
							$clusterText = $clusterText
									+ '<div><h3 style="background : url(http://maps.google.com/mapfiles/ms/icons/red.png) no-repeat right;">'
									+ sandMk + 'Sand points</h3></div>';
						if (loamMk > 0)
							$clusterText = $clusterText
									+ '<div><h3 style="background : url(http://maps.google.com/mapfiles/ms/icons/blue.png) no-repeat right;">'
									+ loamMk + 'Loam points</h3></div>';
						if (clayMk > 0)
							$clusterText = $clusterText
									+ '<div><h3 style="background : url(http://maps.google.com/mapfiles/ms/icons/green.png) no-repeat right;">'
									+ clayMk + 'Clay points</h3></div>';
						if (sandyLoamMk > 0)
							$clusterText = $clusterText
									+ '<div><h3 style="background : url(http://maps.google.com/mapfiles/ms/icons/yellow.png) no-repeat right;">'
									+ sandyLoamMk
									+ 'Sandy Loam points</h3></div>';
						if (clayLoamMk > 0)
							$clusterText = $clusterText
									+ '<div><h3 style="background : url(http://maps.google.com/mapfiles/ms/icons/purple.png) no-repeat right;">'
									+ clayLoamMk
									+ 'Clay Loam points</h3></div>';
						if (sandyClayMk > 0)
							$clusterText = $clusterText
									+ '<div><h3 style="background : url(http://maps.google.com/mapfiles/ms/icons/orange.png) no-repeat right;">'
									+ sandyClayMk
									+ 'Sandy Clay points</h3></div>';
						if (sandClayLoamMk > 0)
							$clusterText = $clusterText
									+ '<div><h3 style="background : url(http://maps.google.com/mapfiles/ms/icons/pink.png) no-repeat right;">'
									+ sandClayLoamMk
									+ 'Sand Clay Loam points</h3></div>';
						if (siltClayLoamMk > 0)
							$clusterText = $clusterText
									+ '<div><h3 style="background : url(http://maps.google.com/mapfiles/ms/icons/lightblue.png) no-repeat right;">'
									+ siltClayLoamMk
									+ 'Silt Clay Loam points</h3></div>';
						$('#info span').html($clusterText + '</div>');

					});

	// Add the mouseout event that will hide the info window.
	google.maps.event.addListener(markerClusterer, "mouseout",
			function(cluster) {
				// Hide the info window
				$('#info h2').text('');
				$('#info span').html('');
				$('#info').hide();
			});

	// layer = map.data.addGeoJson(data.geoJson);
	if (data.featuresJson != null) {
		// map.data.addGeoJson(data.featuresJson, {
		// idPropertyName : "id"
		// });

		featLayer.addGeoJson(data.featuresJson, {
			idPropertyName : "id"
		});
	}

	// map.data.setMap(null);
	featLayer.setMap(null);
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
			// infoWindow.setContent('<div class="info_window">' + '<h2>'
			// + e.feature.getProperty('name') + '</h2>' + featureInfo(e)
			// + '</div>');
			// var anchor = new google.maps.MVCObject();
			// anchor.set("position", e.latLng);
			// infoWindow.open(map, anchor);

		}

		// TODO Create the plot
		if (e.feature.getProperty('featureType') == OUTPUT_SOIL) {
			currentOutput = OUTPUT_SOIL;
			// TODO To eliminate this because the soil points are added to the
			// marker clusterer
			// TODO This is not accessed.
			// Display the plot only for the soil feature
			var probJSON = seriesJSON[e.feature.getProperty('code')];
			// createSoilPlot(categoriesJSON, probJSON, true);
			// createSoilPlot(categoriesJSON, probJSON, true);
			// Track and store the id of the clicked soil point.
			// Store the clicked id into a global variable
			clickedSoilCode = e.feature.getProperty('code');

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
			var cnt = e.feature.getProperty("countryName") == null ? '' : ' '
					+ e.feature.getProperty("countryName");

			createClimateGraphics(e.feature.getProperty('dataSeries'),
					e.feature.getProperty('name') + ' (' + cnt + ')');

			clickedFeature = e.feature.getProperty('dataSeries');
			clickedFeatureName = e.feature.getProperty('name') + ' (' + cnt
					+ ')';

			// Add title to the infos
			// TODO Uncomment it to add the static info window
			/*
			 * $('#info-static').show(); $('#info-static
			 * h2').text(e.feature.getProperty('name')); $('#info-static
			 * span').html(featureInfoStatic(e));
			 */

			//
		}
	});
}

function addMouseOverListener(map) {
	/* Add mouse events to trigger the TPE feature info */
	// Clear the infor window first
	// Hide the Info window
	// $('#info h2').text('');
	// $('#info span').html('');
	// $('#info').hide();
	map.data
			.addListener(
					'mouseover',
					function(e) {
						e.feature.setProperty('selected', true);

						// Don't display the info window for the feature type
						// country

						if ((e.feature.getProperty('name') == 'TPE_BOUNDARY')
								|| (e.feature.getProperty('featureType') == 'COUNTRY')) {
							// TODO: If feature is boundary, then Do nothing
							map.data.overrideStyle(e.feature, {
								strokeColor : '#000',
								fillOpacity : 0,
								fillColor : '#ffffff'
							});
						}

						// else if (e.feature.getProperty('name') == 'Brazil') {
						// TODO: If feature is country, then Do nothing
						// } else if (e.feature.getProperty('name') ==
						// 'Colombia') {

						// }
						else {
							$('#info').show();
							if (e.feature.getProperty('name') == 'municipality') {
								// TODO: If feature is boundary, then Do nothing
								map.data.overrideStyle(e.feature, {
									strokeColor : '#000',
									fillOpacity : 0,
									fillColor : '#989898'
								});
								// $('#info').show();
								$('#info h2')
										.text(
												'ADM2 Name: '
														+ e.feature
																.getProperty('ADM2_NAME'));
								$('#info span')
										.html(
												'ADM1 Name: '
														+ e.feature
																.getProperty('ADM1_NAME'));

							}

							if (e.feature.getProperty('regionType') == 'municipality') {

								map.data.overrideStyle(e.feature, {
									strokeColor : '#000',
									fillOpacity : 0,
									fillColor : '#989898'
								});
								// $('#info').show();
								$('#info h2')
										.text(
												'Admin Name: '
														+ e.feature
																.getProperty('name'));
								$('#info span')
										.html(
												'Admin Name: '
														+ e.feature
																.getProperty('name'));
							}

							if (e.feature.getProperty('featureType') == OUTPUT_TPE) {
								// Remove the background image from h2
								$('#info h2').css({
									'background-image' : 'none',
									'background-repeat' : 'no-repeat',
									'background-position' : 'right'
								});

								// $('#info h2').text('TPE: '+
								// e.feature.getProperty('name'));
								$('#info h2').css('color',
										e.feature.getProperty('colour')).text(
										e.feature.getProperty('description'));

								// $('#info
								// h2').text(e.feature.getProperty('name'));

								var strokeColor, fillColor;

								// Add plots using series data
								if (e.feature.getProperty('name') == ENV_HFE) {
									// Create the feature graphics
									createTPEGraphics(hfeSeries)

									strokeColor = COLOR_HFE;// #00ff00
									fillColor = COLOR_HFE;// #00ff00
								} else if (e.feature.getProperty('name') == ENV_LFE) {
									// Create graphics
									createTPEGraphics(lfeSeries)

									strokeColor = COLOR_LFE;// #ff0000
									fillColor = COLOR_LFE;// #ff0000

								} else if (e.feature.getProperty('name') == ENV_FE) {

									// Create feature graphics
									createTPEGraphics(feSeries)

									strokeColor = COLOR_FE;// #0041a0
									fillColor = COLOR_FE;// #0041a0
								}
								else if (e.feature.getProperty('name') == 'Cluster_1') {

									// Create feature graphics
									createTPEGraphics(feSeries)

									strokeColor = '#990000';// #0041a0
									//fillColor = COLOR_FE;// #0041a0
								}
								else if (e.feature.getProperty('name') == 'Cluster_2') {

									// Create feature graphics
									createTPEGraphics(feSeries)

									strokeColor = '#000099';// #0041a0
									//fillColor = COLOR_FE;// #0041a0
								}
								else if (e.feature.getProperty('name') == 'Cluster_3') {

									// Create feature graphics
									createTPEGraphics(feSeries)

									strokeColor = '#009900';// #0041a0
									//fillColor = COLOR_FE;// #0041a0
								}
								else if (e.feature.getProperty('name') == 'Cluster_4') {

									// Create feature graphics
									createTPEGraphics(feSeries)

									strokeColor = '#800080';// #0041a0
									//fillColor = COLOR_FE;// #0041a0
								}
								else if (e.feature.getProperty('name') == 'Cluster_5') {

									// Create feature graphics
									createTPEGraphics(feSeries)

									strokeColor = '#FF8C00';// #0041a0
									//fillColor = COLOR_FE;// #0041a0
								}
								

								// Revert feature style
								map.data.overrideStyle(e.feature, {
									strokeColor : strokeColor,
									fillOpacity : 0,// 0.5//1
									fillColor : '#ffffff'// fillColor
								});

							}

							else if ((e.feature.getProperty('featureType') == OUTPUT_SOIL)
									|| (e.feature.getProperty('featureType') == 'STATION')
									|| (e.feature.getProperty('featureType') == 'ENVIRONMENT')) {
								// Display the plot only for the soil point
								// features
								if (seriesJSON != null) {
									var probJSON = seriesJSON[e.feature
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
												'Stability: '
														+ e.feature
																.getProperty('name'));
								var strokeColor, fillColor;

								// Add plots using series data
								if (e.feature.getProperty('name') == STABILITY_HIGH) {
									// Create the feature graphics
									createTPEGraphics(hfeSeries)

									strokeColor = '#009900';// #009900
									fillColor = '#009900';// #009900
								} else if (e.feature.getProperty('name') == STABILITY_MIDDLE) {

									// Create feature graphics
									createTPEGraphics(feSeries)
									strokeColor = '#000099';
									fillColor = '#000099';

								} else if (e.feature.getProperty('name') == STABILITY_LOW) {

									// Create graphics
									createTPEGraphics(lfeSeries)

									strokeColor = '#990000';
									fillColor = '#990000';
								}

								// Revert feature style
								map.data.overrideStyle(e.feature, {
									strokeColor : strokeColor,
									fillOpacity : 0,// 0.5,
									fillColor : '#ffffff'// fillColor
								});
							} else if (e.feature.getProperty('featureType') == OUTPUT_CLIMATE) {
								// Add title to the infos
								$('#info h2').text(
										e.feature.getProperty('name'));
								if (e.feature.getProperty('name') != 'TPE_BOUNDARY')
									$('#info h2')
											.css(
													{
														'background-image' : 'url(img/station_red.png)',
														'background-repeat' : 'no-repeat',
														'background-position' : 'right'
													});

								// if (seriesJSON != null)

								var cnt = e.feature.getProperty("countryName") == null ? ''
										: ' '
												+ e.feature
														.getProperty("countryName");
								createClimateGraphics(e.feature
										.getProperty('dataSeries'), e.feature
										.getProperty('name')
										+ ' (' + cnt + ')');
								if (e.feature.getProperty('featureIcon') == true) {
									map.data
											.overrideStyle(
													e.feature,
													{
														icon : {
															url : 'img/station_red.png',
															size : new google.maps.Size(
																	10, 10),
															scaledSize : new google.maps.Size(
																	10, 10)
														},
														title : e.feature
																.getProperty('name')
													});
								}

							} else if (e.feature.getProperty('featureType') == 'REGION') {
								// var strokeColor = '#009900', fillColor =
								// '#009900';
								$('#info h2').text(
										e.feature.getProperty('name')
												+ ' Rice Growing Region');
								$('#info h2').css({
									'background-image' : 'none'
								});
								// countryId is the id of the feature
								// var countryId = e.feature.getProperty('id');
								/*
								 * map.data .forEach(function(feature) { if
								 * (feature .getProperty('featureType') ==
								 * OUTPUT_CLIMATE) if (feature
								 * .getProperty('countryName') == countryId) {
								 * map.data .overrideStyle( feature, { icon : {
								 * url : 'img/station_red.png', size : new
								 * google.maps.Size( 10, 10), scaledSize : new
								 * google.maps.Size( 10, 10) } }); } });
								 */

								// Revert feature style
								map.data.overrideStyle(e.feature, {
									fillOpacity : 0,
									fillColor : '#002200',
									strokeWeight : 1,
									// strokeColor :
									// feature.getProperty('colour'),
									strokeColor : '#002200'
								});

							} else {
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
 * This hides the info window
 */
function hideInfoWindow() {
	// Hide the Info window
	$('#info h2').text('');
	$('#info span').html('');
	$('#info h2').css({
		'background-image' : 'none',
		'background-repeat' : 'no-repeat',
		'background-position' : 'right'
	});
	$('#info').hide();

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

		/*
		 * map.data.overrideStyle(e.feature, { strokeColor : '#990000',
		 * fillColor : '#009900' });
		 */

		if ((e.feature.getProperty('name') == ENV_LFE)
				|| (e.feature.getProperty('name') == ENV_HFE)
				|| (e.feature.getProperty('name') == ENV_FE)
				|| (e.feature.getProperty('name') == STABILITY_HIGH)
				|| (e.feature.getProperty('name') == STABILITY_MIDDLE)
				|| (e.feature.getProperty('name') == STABILITY_LOW)
				|| (e.feature.getProperty('name') == 'Cluster_1')
				|| (e.feature.getProperty('name') == 'Cluster_2')
				|| (e.feature.getProperty('name') == 'Cluster_3')
				|| (e.feature.getProperty('name') == 'Cluster_4')
				|| (e.feature.getProperty('name') == 'Cluster_5')
		) {
			map.data.overrideStyle(e.feature, {
				strokeColor : e.feature.getProperty('colour'),
				fillOpacity : 0.4,// 1,// 0.20//0,
				fillColor : e.feature.getProperty('colour')
			});

		} else if ((e.feature.getProperty('name') == 'municipality')
				|| (e.feature.getProperty('regionType') == 'municipality')) {
			map.data.overrideStyle(e.feature, {
				strokeColor : '#989898',
				fillOpacity : 0.4,// 1,
				fillColor : '#ffffff'
			});
		}

		// Dynamically show the graphics using the last clicked feature id or
		// key
		if (e.feature.getProperty('featureType') == OUTPUT_TPE) {
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
		} else if (e.feature.getProperty('featureType') == OUTPUT_CLIMATE) {

			hideInfoWindow();
			/*
			 * if (e.feature.getProperty('featureType') == 'REGION')
			 * map.data.overrideStyle(e.feature, { strokeColor :
			 * e.feature.getProperty('colour'), fillOpacity : 0.20, fillColor :
			 * e.feature.getProperty('colour') });
			 */

			// if (e.feature.getProperty('featureType') == OUTPUT_CLIMATE)
			if (e.feature.getProperty('featureIcon') == true)
				map.data.overrideStyle(e.feature, {
					icon : {
						url : 'img/weather_info.png',// url :
						// 'img/station_blue.png',
						size : new google.maps.Size(10, 10),
						scaledSize : new google.maps.Size(10, 10)
					}
				});

			// rainfallRadiationPlot(climateSeriesJSON, true,
			// clickedFeature,clickedFeatureName);
			createClimateGraphics(clickedFeature, clickedFeatureName);

		} else if (e.feature.getProperty('featureType') == 'REGION') {
			map.data.overrideStyle(e.feature, {
				fillOpacity : 0.4,// 0.5,
				fillColor : '#009900',
				strokeWeight : 1,
				// strokeColor : feature.getProperty('colour'),
				strokeColor : '#009900'
			});

		} else if (e.feature.getProperty('featureType') == OUTPUT_STABILITY) {
			map.data.overrideStyle(e.feature, {
				strokeColor : e.feature.getProperty('colour'),
				fillOpacity : 0.4,// 1,// 0.20//0,
				fillColor : e.feature.getProperty('colour')
			});
		} else if (e.feature.getProperty('featureType') == OUTPUT_SOIL) {
			// If the clickedSoilCode variable is not null.
			if (clickedSoilCode != null)
				// Display the plot only for the soil point
				// feature that was clicked. Use the soil code that was stored
				// in the global variable. clickedSoilCode
				if (seriesJSON != null) {
					// Get the data series for the clicked point.
					var probJSON = seriesJSON[e.feature.getProperty('code')];
					createSoilGraphics(probJSON);
				}
		}
		// TODO Add Stability
	});
}

function featureInfoStatic(event) {
	var $htmlText = '';
	if (event.feature.getProperty('featureType') == 'CLIMATE') {

		if (event.feature.getProperty("regionName") != null)
			$htmlText = $htmlText + '<div>Region: '
					+ event.feature.getProperty("regionName");

		if (event.feature.getProperty("countryName") != null)
			$htmlText = $htmlText + '<div>Country: '
					+ event.feature.getProperty("countryName");
		if (event.feature.getProperty("stateName") != null)
			$htmlText = $htmlText + '<div>State: '
					+ event.feature.getProperty("stateName");
		if (event.feature.getProperty("municipalityName") != null)
			$htmlText = $htmlText + '<div>Municipality: '
					+ event.feature.getProperty("municipalityName");
		if (event.feature.getProperty("plantingDate") != null)
			$htmlText = $htmlText + '<div>Planting Month: '
					+ event.feature.getProperty("plantingDate");
		// Add a table of tmin, tmax and precipitation for the currently hovered
		// station
		if (event.feature.getProperty('infoSeries') != null) {
			var infoSeries = event.feature.getProperty('infoSeries');
			// Add a table for tmin, tmax and precipitation.
			$htmlText = $htmlText
					+ '<div><table id="info-series-table">'
					+ '<tr><th>Month:</th><th>J</th><th>F</th><th>M</th><th>A</th><th>M</th><th>J</th>'
					+ '<th>J</th><th>A</th><th>S</th><th>O</th><th>N</th><th>D</th></tr>';
			$.each(infoSeries, function(key, listOfValues) {
				var unit = (key == 'tmax') || (key == 'tmin') ? ' (°C)'
						: ' (mm)';
				$htmlText = $htmlText + '<tr><td>' + key + unit + '</td>';
				// if (listOfValues != null&&(listOfValues.length==12))
				$.each(listOfValues, function(index, value) {
					$htmlText = $htmlText + '<td>'
							+ parseFloat(value).toFixed(2) + '</td>';
				});
				// else {
				// var i;
				// for (i = 0; i < 12; ++i) {
				// $htmlText = $htmlText + '<td>-</td>';
				// }
				// }

				$htmlText = $htmlText + '</tr>'
			});
			$htmlText = $htmlText + '</table></div>';
		}
	}
	return $htmlText;

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
		// Country
		$htmlText = '<div>Country: ' + event.feature.getProperty("countryName");

		// State
		$htmlText = $htmlText + '</div><div>State: '
				+ event.feature.getProperty("stateName");

		// Municipality
		$htmlText = $htmlText + '</div><div>Municipality: '
				+ event.feature.getProperty("municipalityName");

		// Station
		$htmlText = $htmlText + '</div><div>Station: '
				+ event.feature.getProperty("stationName");
		// Add the coordinates
		$htmlText = $htmlText + '</div><div>Point: '
				+ event.feature.getProperty("lat") + ','
				+ event.feature.getProperty("lng");
		// Add the soil properties
		// PH
		var ph = event.feature.getProperty("ph") == null ? '--' : event.feature
				.getProperty("ph");

		$htmlText = $htmlText + '</div><div>PH: ' + ph + '</div>';
		// Clay
		var clay = event.feature.getProperty("clay") == null ? '--'
				: event.feature.getProperty("clay");
		$htmlText = $htmlText + '<div>Clay: ' + clay + '(%)</div>';

		// Silt
		var silt = event.feature.getProperty("silt") == null ? '--'
				: event.feature.getProperty("silt");

		$htmlText = $htmlText + '<div>Silt: ' + silt + '(%)</div>';

		// Sand
		var sand = event.feature.getProperty("sand") == null ? '--'
				: event.feature.getProperty("sand");
		$htmlText = $htmlText + '<div>Sand: ' + sand + '(%)</div>';

		// Depth
		var depth = event.feature.getProperty("depth") == null ? '--'
				: event.feature.getProperty("depth");
		$htmlText = $htmlText + '<div>Depth: ' + depth + ' (g/cm-3)</div>';

		// availableSoilWater
		var asw = event.feature.getProperty("availableSoilWater") == null ? '--'
				: event.feature.getProperty("availableSoilWater");
		$htmlText = $htmlText + '<div>Available soil moisture: ' + asw
				+ '(m3 m-3)</div>';
		// Bulk Density
		var bd = event.feature.getProperty("bulkDensity") == null ? '--'
				: event.feature.getProperty("bulkDensity");
		$htmlText = $htmlText + '<div>Bulk Density: ' + bd + '</div>';
		// Cation Exchange
		var ce = event.feature.getProperty("cationExchange") == null ? '--'
				: event.feature.getProperty("cationExchange");
		$htmlText = $htmlText + '<div>Cation exchange capacity: ' + ce
				+ '(cmol kg-1)</div>';
		// Organic Carbon
		var oc = event.feature.getProperty("organicCarbon") == null ? '--'
				: event.feature.getProperty("organicCarbon");
		$htmlText = $htmlText + '<div>Organic Carbon: ' + oc + '(g kg-1)</div>';
		// Organic Matter
		var om = event.feature.getProperty("organicMatter") == null ? '--'
				: event.feature.getProperty("organicMatter");
		$htmlText = $htmlText + '<div>Organic Matter: ' + om + '</div>';
		// Water Content Field Capacity
		var smf = event.feature.getProperty("waterContentFieldCapacity") == null ? '--'
				: event.feature.getProperty("waterContentFieldCapacity");
		$htmlText = $htmlText + '<div>Soil moisture at Field capacity: ' + smf
				+ ' (m3 m-3)</div>';
		// Taxonomy
		var tax = event.feature.getProperty("taxonomy") == null ? '--'
				: event.feature.getProperty("taxonomy");
		$htmlText = $htmlText + '<div>Taxonomy: ' + tax + '</div>';
		// Water Capacity Wilt Point
		var wcw = event.feature.getProperty("waterCapacityWiltPoint") == null ? '--'
				: event.feature.getProperty("waterCapacityWiltPoint");
		$htmlText = $htmlText + '<div>Soil moisture at Wilting point: ' + wcw
				+ '(m3 m-3)</div>';
	} else if (event.feature.getProperty('featureType') == 'CLIMATE') {

		if (event.feature.getProperty("regionName") != null)
			$htmlText = $htmlText + '<div>Region: '
					+ event.feature.getProperty("regionName");

		if (event.feature.getProperty("countryName") != null)
			$htmlText = $htmlText + '<div>Country: '
					+ event.feature.getProperty("countryName");
		if (event.feature.getProperty("stateName") != null)
			$htmlText = $htmlText + '<div>State: '
					+ event.feature.getProperty("stateName");
		if (event.feature.getProperty("municipalityName") != null)
			$htmlText = $htmlText + '<div>Municipality: '
					+ event.feature.getProperty("municipalityName");
		if (event.feature.getProperty("plantingDate") != null)
			$htmlText = $htmlText + '<div>Planting Month: '
					+ event.feature.getProperty("plantingDate");
		// Add a table of tmin, tmax and precipitation for the currently hovered
		// station
		if (event.feature.getProperty('infoSeries') != null) {
			var infoSeries = event.feature.getProperty('infoSeries');
			// Add a table for tmin, tmax and precipitation.
			$htmlText = $htmlText
					+ '<div><table id="monthly-series">'
					+ '<tr style="text-align: center;"><td style="text-align: left;">Month:</td><td>J</td><td>F</td><td>M</td><td>A</td><td>M</td><td>J</td>'
					+ '<td>J</td><td>A</td><td>S</td><td>O</td><td>N</td><td>D</td></tr>';
			$
					.each(
							infoSeries,
							function(key, listOfValues) {
								var unit = (key == 'tmax') || (key == 'tmin') ? '(°C)'
										: ((key == 'prec') ? '(mm)'
												: '(MJ/m2.day)');// (kJm-2d-1)
								$htmlText = $htmlText
										+ '<tr style="text-align: center;"><td style="text-align: left;text-transform: capitalize;">'
										+ (key == 'radi' ? 'S.Rad' : key)
										+ unit + '</td>';
								// text-transform: capitalize;
								if (listOfValues != null)
									$.each(listOfValues,
											function(index, value) {
												$htmlText = $htmlText
														+ '<td>'
														+ parseFloat(value)
																.toFixed(1)
														+ '</td>';
											});
								else {
									var i;
									for (i = 0; i < 12; ++i) {
										$htmlText = $htmlText + '<td>-</td>';
									}
								}

								$htmlText = $htmlText + '</tr>'
							});
			$htmlText = $htmlText + '</table></div>';
		}

		// $htmlText = $htmlText + '<div>Station No:'+
		// event.feature.getProperty('stationNumber') + '</div>';
		// Add climate properties here
		// // Min Temperature
		// $htmlText = $htmlText + '<div>Min Temperature: '
		// + event.feature.getProperty("minT") + '</div>';
		// // Max Temperature
		// $htmlText = $htmlText + '<div>Max Temperature: '
		// + event.feature.getProperty("maxT") + '</div>';
		// // Precipitation
		// $htmlText = $htmlText + '<div>Precipitation: '
		// + event.feature.getProperty("precipitation") + '</div>';
		// Radiation
		// $htmlText = $htmlText + '<div>Radiation: ' +
		// event.feature.getProperty("radiation") + '</div>';

	} else if (event.feature.getProperty('featureType') == OUTPUT_TPE) {

		$htmlText = '<div style="color:' + event.feature.getProperty('colour')
				+ '";">' + event.feature.getProperty('name') + '</div>';

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
		$htmlText = $htmlText + '<div>'
				+ event.feature.getProperty("ADM2_NAME") + '</div>';
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
	$("div[role=dialog] button:contains('Close')").css({'margin':'0','line-height':'.5em','font-size':'.85em','font-weight':'normal'});
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
								//fontFamily : 'serif',
								fontFamily : 'Open Sans, Helvetica, Arial, sans-serif',
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
								color : '#707070',
								//fontWeight : 'bold',
								fontSize : fontSize
							}
						},
						title : {
							text : 'Environment Sensibility',
							style : {
								color : '#656360',
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
									fontSize : fontSize
									//fontFamily : 'Verdana, sans-serif'
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
	$("div[role=dialog] button:contains('Close')").css({'margin':'0','line-height':'.5em','font-size':'.85em','font-weight':'normal'});
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
								//fontFamily : 'serif',
								fontFamily : 'Open Sans, Helvetica, Arial, sans-serif',
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
								color : '#707070',
								//fontWeight : 'bold',
								fontSize : fontSize
							}
						},
						title : {
							text : 'Environment Soil',
							style : {
								color : '#656360',
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
									fontSize : fontSize
									//fontFamily : 'Verdana, sans-serif'
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
	$("div[role=dialog] button:contains('Close')").css({'margin':'0','line-height':'.5em','font-size':'.85em','font-weight':'normal'});
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
						//fontFamily : 'serif',
						fontFamily : 'Open Sans, Helvetica, Arial, sans-serif',
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
						color : '#707070',
						//fontWeight : 'bold',
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
						//fontFamily : 'MuseoS500',
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
							fontSize : fontSize
							//fontFamily : 'Verdana, sans-serif'
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
							fontSize : fontSize
							//fontFamily : 'Verdana, sans-serif'
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
	$("div[role=dialog] button:contains('Close')").css({'margin':'0','line-height':'.5em','font-size':'.85em','font-weight':'normal'});
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
		// Create the graphic description for the zoomed plot.
		// Grab the dialog div
		$('#dialog-infos').html(seriesMap.title + ' ' + seriesMap.subTitle);
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
							style : {
								color : '#707070',
								//fontWeight : 'bold',
								fontSize : fontSize
							},
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
								//fontFamily : 'serif',
								fontFamily : 'Open Sans, Helvetica, Arial, sans-serif',
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
								color : '#656360',
								// fontWeight : 'bold'
								fontSize : fontSize
							}
						},
						subtitle : {
							text : seriesMap.subTitle,
							style : {
								color : '#656360',
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
	$("div[role=dialog] button:contains('Close')").css({'margin':'0','line-height':'.5em','font-size':'.85em','font-weight':'normal'});
	// $('#plot_pcew').show();
	var renderTo = 'plot_pcew';// Default div
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
		$('#dialog-infos').html(seriesMap.title + ' ' + seriesMap.subTitle);
	}

	$('#' + renderTo).highcharts(
			{
				credits : {

					enabled : credits,
					// This hides highcharts.com from the legend
					// enabled : false
					text : 'Source: CCAFS TPE (www.ccafs-tpe.org)',
					href : 'http://www.ccafs-tpe.org',
					style : {
						color : '#707070',
						//fontWeight : 'bold',
						fontSize : fontSize
					},
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
						//fontFamily : 'serif',
						fontFamily : 'Open Sans, Helvetica, Arial, sans-serif',
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
						color : '#656360',
						// fontWeight : 'bold',
						fontSize : fontSize
					}
				},
				subtitle : {
					text : seriesMap.subTitle,
					style : {
						color : '#656360',
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
	$("div[role=dialog] button:contains('Close')").css({'margin':'0','line-height':'.5em','font-size':'.85em','font-weight':'normal'});
	// $('#plot_rainsum').show();
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
		$('#dialog-infos').html(seriesMap.title + ' ' + seriesMap.subTitle);
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
							style : {
								color : '#707070',
								//fontWeight : 'bold',
								fontSize : fontSize
							},
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
								//fontFamily : 'serif',
								fontFamily : 'Open Sans, Helvetica, Arial, sans-serif',
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
								color : '#656360',
								// fontWeight : 'bold'
								fontSize : fontSize
							}
						},
						subtitle : {
							text : seriesMap.subTitle,
							style : {
								color : '#656360',
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

function rainfallRadiationPlot(dataSeries, smallPlot, stationName) {
	$("div[role=dialog] button:contains('Close')").css({'margin':'0','line-height':'.5em','font-size':'.85em','font-weight':'normal'});
	var categories, series;
	if (dataSeries != null) {
		categories = dataSeries['categories'];
		series = dataSeries['series'];
	}
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
		titleFontSize = '18px';
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
		// Create the graphic description for the zoomed plot.
		// Grab the dialog div
		$('#dialog-infos').html(
				'Average Monthly Precipitation, Temperature and Radiation from '
						+ stationName);
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
							style : {
								color : '#707070',
								//fontWeight : 'bold',
								fontSize : fontSize
							},
							position : {
								align : 'right'
							// x : 10
							},
							style : {
								color : '#656360',
								fontWeight : 'bold',
								fontSize : fontSize
							}
						},
						chart : {
							width : width,
							height : height,
							zoomType : 'xy',
							style : {
								//fontFamily : 'serif',
								fontFamily : 'Open Sans, Helvetica, Arial, sans-serif',
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
							 * , marginBottom : 60, marginTop : 25, marginLeft :
							 * 60, marginRight : 10
							 */
							events : {
								load : function() {
									if (smallPlot) {
										this.renderer.image('img/zoom-in.png',
												10, 2, 20, 20).on(
												'click',
												function() {
													rainfallRadiationPlot(
															dataSeries, false,
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
												'height', '8').attr('width',
												'8');

									} else {
										this.renderer.image(
												'img/ccafs_logo.png', 40, 0,
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
									if (smallPlot) {
										rainfallRadiationPlot(dataSeries,
												false, stationName);
										// $('#report').html('click on title');
										$('#dialog-plot').dialog('open');
									}
								}
							}
						},
						title : {
							text : 'Average Monthly Precipitation, Temperature and Radiation',
							style : {
								color : '#656360',
								// fontWeight : 'bold',
								fontSize : titleFontSize
							}
						},
						subtitle : {
							text : stationName,
							style : {
								color : '#656360'
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
									fontSize : fontSize
									//fontFamily : 'Verdana, sans-serif'
								}
							},
							title : {
								text : xAxisTitle,
								zIndex : 10
							},
							zIndex : 10,
							crosshair : true

						},
						yAxis : [ { // Secondary yAxis
							title : {
								text : 'Rainfall (mm)',
								rotation : -90,
								x : 5,
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
							}
						// ,
						// opposite : true
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
							title : {
								text : 'Temperature (°C)',
								style : {
									color : '#4572A7'
								},
								rotation : -90,
								x : 5
							},
							opposite : true
						// linkedTo:0
						}, { // Tertially yAxis
							labels : {
								enabled : labelsEnabled,
								// format : '{value}(MJ/m2.day)',
								style : {
									color : '#4572A7',
									fontSize : fontSize
								},
								// format : '{value:.1f}'
								format : '{value:.1f}'
							},
							// format : '{value:.1f}(MJ/m2.day)' },
							// min : 0,
							title : {
								text : '',
								// text : 'Radiation (MJ/m2.day)',
								style : {
									color : '#4572A7'
								},
								rotation : -90,
								x : 5
							},
							opposite : true
						},
						/*
						 * , { // Tertially yAxis, Max Temperature title : {
						 * text : 'Max Temperature (°C)', rotation : -90, x :
						 * 10, style : { color : '#4572A7' } }, //
						 * //////////////////////////////////////////// min : 0,
						 * labels : { enabled : labelsEnabled, // format :
						 * '{value} mm', // rotation: -45, style : { color :
						 * '#4572A7', fontSize : fontSize }, format :
						 * '{value:.1f}' // format : '{value:.2f}' }, opposite :
						 * true }
						 */
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
								//fontFamily : 'MuseoS500',
								// fontWeight : 'bold',
								fontSize : fontSize
							// width : itemWidth
							},
							// title : {
							// text : ':: Drag'
							// },
							floating : true,
							draggable : true,
							zIndex : 1
						},
						series : series,
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

						plotOptions : {
							series : {
								events : {
									legendItemClick : function(event) {
										var visibility = this.visible ? 'visible'
												: 'hidden';
										// var chart;
										// index=3 (radiation)
										if (this.chart.series[3] == null) {

											return true;
										}

										if (this.index == 3) {
											if (this.visible) {
												this.chart.series[1].show();
												this.chart.series[2].show();
												this.chart.yAxis[2].setTitle({
													text : null
												});
												this.chart.yAxis[1].setTitle({
													text : 'Temperature (°C)'
												});

											} else {
												this.chart.series[1].hide();
												this.chart.series[2].hide();
												this.chart.yAxis[1].setTitle({
													text : null
												});
												this.chart.yAxis[2]
														.setTitle({
															text : 'Radiation (MJ/m2.day)'
														});
											}
										} else if (this.index == 1) {// index=1
											// (min
											// temperature)
											if (this.visible) {
												this.chart.series[3].show();
												this.chart.series[2].hide();
												this.chart.yAxis[1].setTitle({
													text : null
												});
												this.chart.yAxis[2]
														.setTitle({
															text : 'Radiation (MJ/m2.day)'
														});

											} else {
												this.chart.series[3].hide();
												this.chart.series[2].show();
												this.chart.yAxis[1].setTitle({
													text : 'Temperature (°C)'
												});
												this.chart.yAxis[2].setTitle({
													text : null
												});
											}
										} else if (this.index == 2) {// index=2
											// (max
											// temperature)
											if (this.visible) {
												this.chart.series[3].show();
												this.chart.series[1].hide();
												this.chart.yAxis[1].setTitle({
													text : null
												});
												this.chart.yAxis[2]
														.setTitle({
															text : 'Radiation (MJ/m2.day)'
														});
											} else {
												this.chart.series[3].hide();
												this.chart.series[1].show();
												this.chart.yAxis[1].setTitle({
													text : 'Temperature (°C)'
												});
												this.chart.yAxis[2].setTitle({
													text : null
												});
											}
										}

										// var seriesIndex = this.index;
										// var series = this.chart.series;
										//					                    
										// for (var i = 0; i < series.length;
										// i++)
										// {
										// if (series[i].index != seriesIndex)
										// {
										//					                            
										// series[i].visible ? series[i].hide()
										// : series[i].show();
										// }
										// }

										return true;
									}
								}
							}
						}

					});

	// $('#toggle-series').click(function() {
	// $($('.highcharts-legend-item')[2]).click() });
	// $($('.highcharts-legend-item')[0]).click() });

	$('#toggle-series').click(function() {
		$($('.highcharts-legend-item')[3]).click()
	});

	$('#toggle-series').trigger('click');
}

function plotRAINCUM(seriesMap, environment, smallPlot) {
	$("div[role=dialog] button:contains('Close')").css({'margin':'0','line-height':'.5em','font-size':'.85em','font-weight':'normal'});
	// $('#plot_rainsum').show();
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
		$('#dialog-infos').html(seriesMap.title + ' ' + seriesMap.subTitle);
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
							style : {
								color : '#707070',
								//fontWeight : 'bold',
								fontSize : fontSize
							},
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
								//fontFamily : 'serif',
								fontFamily : 'Open Sans, Helvetica, Arial, sans-serif',
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
								color : '#656360',
								// fontWeight : 'bold'
								fontSize : fontSize
							}
						},
						subtitle : {
							text : seriesMap.subTitle,
							style : {
								color : '#656360',
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
	$("div[role=dialog] button:contains('Close')").css({'margin':'0','line-height':'.5em','font-size':'.85em','font-weight':'normal'});
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
		$('#dialog-infos').html(seriesMap.title + ' ' + seriesMap.subTitle);
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
					style : {
						color : '#707070',
						//fontWeight : 'bold',
						fontSize : fontSize
					},
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
						fontFamily : 'Open Sans, Helvetica, Arial, sans-serif',
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
						color : '#656360',
						// fontWeight : 'bold',
						fontSize : fontSize
					}
				},
				subtitle : {
					text : seriesMap.subTitle,
					style : {
						color : '#656360',
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
	// });

}

function createGraphicsPlot(seriesMap, environment, smallPlot, nameOfPlot) {

}
