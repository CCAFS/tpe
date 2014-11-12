//$(document)
//	.ready(

/**
 * Depending on the selected OUTPUT text, get the corresponding selected params.
 * For SOIL
 * (selectedTexture,selectedProperties,selectedCoutry,selectedSubregions) For
 * TPE (selectedCrop selectedCultivar, selectedCoutry, selectedYears,
 * selectedSowingWindow) For CLIMATE (selectedProperties, selectedCountry,
 * selectedSubregions, selectedYears, selectedStations)
 */
function initializeGoogleMap() {
	console.log('Initializing the Google Map....');
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

	console.log('Out put: ' + selectedOutput.toUpperCase());
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
		/*
		 * selectedCrop = $("select#select_crop").val(); selectedCultivar =
		 * $("select#select_cultivar").val(); selectedCountry =
		 * $("select#select_country").val(); selectedYears =
		 * $("select#select_years").val(); selectedSWindow =
		 * $("select#select_window").val(); selectedScenario =
		 * $("select#select_scenario").val();
		 */
		// Initialise the TPE map for the selected params
		// The params list
		/*params = {
			crop : selectedCrop,
			cultivar : selectedCultivar,
			country : selectedCountry,
			years : selectedYears,
			scenario : selectedScenario,
			swindow : selectedSWindow
		};*/
		// The action url for the soil map
		actionJson = 'tpeGeoJson.geojson';
		// Call the geoJsonData function and pass the params y action
		geoJsonData(actionJson);
		// initializeMap();
		break;

	case 'SOIL':
		// Soil is selected. Then get
		// the corresponding selcted
		// SOIL params
		/*
		 * selectedTextures = $("select#select_textures").val();
		 * selectedProperties = $("select#select_properties").val();
		 * selectedCountry = $("select#select_country").val(); selectedRegions =
		 * $("select#select_regions").val();
		 */
		// Initialise the soil map for the selected params
		// Retrieve Geo Json using AJAX
		// The params list
		/*
		 * params = { properties : selectedProperties, textures :
		 * selectedTextures, country : selectedCountry };
		 */
		// The action url for the soil map
		actionJson = 'soilGeoJson.geojson';
		// Call the geoJsonData function and pass the params y action
		geoJsonData(actionJson);
		/*
		 * $.getJSON(markersAction, { properties : selectedProperties, textures :
		 * selectedTextures, regionId : selectedCountry }, function(data) {
		 * console.log(data.soilGeoJson) console.log(data.countryGeoJson) //
		 * Call the initialize map function initializeMap(data); });
		 */

		break;

	case 'CLIMATE':
		// Climate is selected: The get
		// the corresponding selected
		// params
		/*
		 * selectedStations = $("select#select_stations").val();
		 * selectedProperties = $("select#select_properties").val();
		 * selectedCountry = $("select#select_country").val(); selectedYears =
		 * $("select#select_years").val(); selectedRegions =
		 * $("select#select_regions").val();
		 */
		// Initialise the Climate map for the selected params
		// The params list
		/*
		 * params = { properties : selectedProperties, stations :
		 * selectedStations, regions : selectedRegions, years : selectedYears,
		 * country : selectedCountry };
		 */
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

	// Add the selected country polygon feature to the map.
	map.data.addGeoJson(data.countryGeoJson, {
		idPropertyName : "id"
	});

	// Add the GeoJson features to the map
	map.data.addGeoJson(data.geoJson, {
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
	map.data.addListener('click', function(event) {

		/*
		 * var text = ""; // If the station was clicked if
		 * (event.feature.getProperty("featureType") == 'STATION') { text =
		 * "Region: " + event.feature.getProperty("regionName") + "<br/>Station: " +
		 * event.feature.getProperty("stationName") + '<br/>Number:' +
		 * event.feature.getProperty('stationNumber'); } else if
		 * (event.feature.getProperty("featureType") == 'SOIL') { // If the soil
		 * icon was clicked text = "Region: " +
		 * event.feature.getProperty("regionName") + "<br/>Station: " +
		 * event.feature.getProperty("stationName") + "<br/><b>Texture: " +
		 * event.feature.getProperty("soilName") + "<br/>" +
		 * event.feature.getProperty("soilPropertyName") + ": " +
		 * event.feature.getProperty("soilPropertyValue"); } else { // If the
		 * Country or State region was clicked. text = "Region: " +
		 * event.feature.getProperty("regionName"); // TODO Add more details }
		 */

		// show an infowindow on click
		// infowindow.setContent("<div
		// style='width:150px; text-align:
		// center;'>"+myHTML+"</div>");
		infoWindow.setContent('<div class="info_window">' + featureInfo(event)
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
		// $('#info span').text(e.feature.getProperty('stationName'));
		$('#info span').text(featureInfo(e));

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
/**
 * This function returns the feature info details
 */
function featureInfo(event) {
	var text = "";
	// If the station was clicked
	if (event.feature.getProperty("featureType") == 'STATION') {
		text = "Region: " + event.feature.getProperty("regionName") +

		"<br/>Station: " + event.feature.getProperty("stationName")
				+ '<br/>Number:' + event.feature.getProperty('stationNumber');

	} else if (event.feature.getProperty("featureType") == 'SOIL') {
		// If the soil icon was clicked
		text = "Region: " + event.feature.getProperty("regionName")
				+ "<br/>Station: " + event.feature.getProperty("stationName")
				+ "<br/><b>Texture: " + event.feature.getProperty("soilName")
				+ "<br/>" + event.feature.getProperty("soilPropertyName")
				+ ": " + event.feature.getProperty("soilPropertyValue");
	} else {
		// If the Country or State region was clicked.
		text = "Region: " + event.feature.getProperty("regionName");
		// TODO Add more details
	}

	return text;
}
/**
 * This action retrieves the Geo Json data and then calls the initializeMap()
 * function.
 */
function geoJsonData(action) {

	/*
	 * $.getJSON(action, $('#tpe_index').serialize(), function(data) {
	 * console.log(data); // console.log(data.geoJson);
	 * console.log(data.countryGeoJson); // Call the initialize map function
	 * initializeMap(data); });
	 */

	// console.log(parameters);
	$.ajax({
		type : "GET",
		url : action,
		data : $('#tpe_index').serialize(),
		dataType : "json",
		success : function(dataJson) { //
			// var columnData = null, columnNames = result.colNames;
			// console.log(dataJson.geoJson);
			console.log(dataJson.countryGeoJson);
			initializeMap(dataJson);
		}
	});

}
// );
