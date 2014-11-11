//$(document)
//	.ready(
/**
 * The function that initializes the Google Map
 */
function initializeMap() {

	// var mapStyle = [ ];

	var markersAction = 'soilGeoJson.geojson';
	// Default map properties (BRAZIL)
	var defaultLatLng = new google.maps.LatLng(-14.235004, -51.92528);

	// Defaukt properties for Colombia
	// new google.maps.LatLng(4.214943141390651, -73.828125)
	// var defaultLatLng = new
	// google.maps.LatLng(4.214943141390651, -73.828125);

	// The default zoom level of the map
	var defaultZoom = 4;
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
	$.getJSON(markersAction, function(data) {
		console.log(data.soilGeoJson)
		console.log(data.countryGeoJson)
		map.data.addGeoJson(data.soilGeoJson, {
			idPropertyName : "id"
		});
		// Add the country polygons
		map.data.addGeoJson(data.countryGeoJson, {
			idPropertyName : "id"
		});
	});

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

						var text = "";
						if (event.feature.getProperty("featureType") == 'STATION') {
							text = "Region: "
									+ event.feature.getProperty("regionName")
									+

									"<br/>Station: "
									+ event.feature.getProperty("stationName")
									+ '<br/>Number:'
									+ event.feature
											.getProperty('stationNumber')

						} else if (event.feature.getProperty("featureType") == 'SOIL') {

							text = "Region: "
									+ event.feature.getProperty("regionName")
									+ "<br/>Station: "
									+ event.feature.getProperty("stationName")
									+ "<br/><b>Texture: "
									+ event.feature.getProperty("soilName")
									+ "<br/>"
									+ event.feature
											.getProperty("soilPropertyName")
									+ ": "
									+ event.feature
											.getProperty("soilPropertyValue")
									+ "</b>"
						}

						// show an infowindow on click

						// infowindow.setContent("<div
						// style='width:150px; text-align:
						// center;'>"+myHTML+"</div>");
						infoWindow
								.setContent('<div style="line-height:1.35;overflow:hidden;white-space:nowrap;width:150px; text-align:left;">'
										+

										text

										+ '</div>');
						var anchor = new google.maps.MVCObject();
						anchor.set("position", event.latLng);
						infoWindow.open(map, anchor);
					});

	/* Add mouse events to trigger the TPE feature info */
	map.data.addListener('mouseover', function(e) {
		e.feature.setProperty('selected', true);
		$('#info').show();
		$('#info h2').text(e.feature.getProperty('name'));
		$('#info span').text(e.feature.getProperty('stationName'));
		// document.getElementById('info-box').textContent =
		// event.feature.j.NOMBRE_MPI;
		// map.data.revertStyle();
		map.data.overrideStyle(e.feature, {
			strokeColor : 'green',
			fillColor : 'red'
		});

	});
	map.data.addListener('mouseout', function(e) {
		e.feature.setProperty('selected', false);
		$('#info').hide();
		map.data.overrideStyle(e.feature, {
			strokeColor : '#990000',
			fillColor : '#009900'
		});
	});
	// Test GeoJson
	/*
	 * $.getJSON('${ctx}/script/testGeoJson.json', function(data) {
	 * console.log(data); });
	 */

	/*
	 * map.data.addListener('mouseover', function(event) {
	 * document.getElementById('info-box').textContent =
	 * event.feature.j.NOMBRE_MPI; map.data.revertStyle();
	 * map.data.overrideStyle(event.feature,
	 * {strokeColor:'red',fillColor:'red'}); });
	 */

}

// );
