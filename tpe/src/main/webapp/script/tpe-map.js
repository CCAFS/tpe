$(document)
		.ready(
				function() {

					// var mapStyle = [ ];

					var markersAction = 'stationGeoJson.geojson';
					// Default map properties (BRAZIL)
					var defaultLatLng = new google.maps.LatLng(-14.235004,
							-51.92528);
					// The default zoom level of the map
					var defaultZoom = 4;
					// Variable for TPE map
					var map;
					// List of all station markers to check
					var stationMarkers = {};

					// Google map options
					var mapOptions = {
						zoom : defaultZoom,
						center : defaultLatLng,
						mapTypeId : google.maps.MapTypeId.ROADMAP
					}

					// Create the new map and make sure the tpe_map div
					// exists
					map = new google.maps.Map(document
							.getElementById('tpe_map'), mapOptions);

					// Add markers or features
					// map.data.loadGeoJson('', {idPropertyName : 'STATE'});
					// Load the JSON to show places
					// map.data.loadGeoJson(markersAction,{idPropertyName:'id'});

					// Load GeoJSON.
					/*
					 * var stations = $.getJSON(markersAction); //same as
					 * map.data.loadGeoJson(); stations.then(function(data) {
					 * for (i in data) { console.log(i + ':' + data[i])
					 * map.data.addGeoJson(data[i], { idPropertyName :
					 * "stationId" }); } });
					 */
					$.getJSON(markersAction, function(data) {
						console.log(data.stations)
						map.data.addGeoJson(data.stations, {
							idPropertyName : "stationId"
						});
					});
					/*
					 * $.getJSON(markersAction, function(data) {
					 * $.each(data.stations, function(i, item) { console.log(i +
					 * ':' + item) map.data.addGeoJson(item, { idPropertyName :
					 * "stationId" }); }); });
					 */
					// Style the station markers
					map.data.setStyle(function(feature) {
						var stationName = feature.getProperty('stationName');
						return {
							icon : {
								url : "img/station_green.png"
							// url : "${ctx}/img/station_green.png"
							},
							visible : true,
							clickable : true,
							title : stationName + ' Station'
						};
					});

					// get the legend container, create a legend, add a
					// legend renderer fn
					var legendContainer = $('#legend-container');
					// add the legend to the map
					map.controls[google.maps.ControlPosition.RIGHT_BOTTOM]
							.push(legendContainer[0]);

					var infoWindow = new google.maps.InfoWindow({
						content : ""
					});

					// listen for click events
					map.data
							.addListener(
									'click',
									function(event) {
										// show an infowindow on click

										// infowindow.setContent("<div
										// style='width:150px; text-align:
										// center;'>"+myHTML+"</div>");

										infoWindow
												.setContent('<div style="line-height:1.35;overflow:hidden;white-space:nowrap;width:150px; text-align: center;"> Station Number: '
														+ event.feature.getId()
														+ "<br/>Station Name: "
														+ event.feature
																.getProperty("stationName")
														+ "</div>");
										var anchor = new google.maps.MVCObject();
										anchor.set("position", event.latLng);
										infoWindow.open(map, anchor);
									});
				});
