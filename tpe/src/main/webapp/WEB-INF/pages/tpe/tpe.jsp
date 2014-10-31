<%@ include file="/common/taglibs.jsp"%>
<html lang="en">
<head>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no">
<title>CCAFS TPE Platform</title>
<style>
html, body, #map-canvas {
	height: 100%;
	width: 100%;
	margin: 0px;
	padding: 0px;
	margin: 0px;
}
</style>
<!-- <script
	src="https://maps.googleapis.com/maps/api/js?client=google-developers&v=3.exp"></script> -->
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp"></script>
<script>
	// In this example, we center the map, and add a marker, using a LatLng object
	// literal instead of a google.maps.LatLng object. LatLng object literals are
	// a convenient way to add a LatLng coordinate and, in most cases, can be used
	// in place of a google.maps.LatLng object.

	var map;
	function initialize() {
		var mapOptions = {
			zoom : 8,
			center : {
				lat : 3.359889,
				lng : -76.638565
			}
		};
		map = new google.maps.Map(document.getElementById('map-canvas'),
				mapOptions);

		var marker = new google.maps.Marker({
			// The below line is equivalent to writing:
			// position: new google.maps.LatLng(-34.397, 150.644)
			position : {
				lat : 3.359889,
				lng : -76.638565
			},
			map : map
		});

		// You can use a LatLng literal in place of a google.maps.LatLng object when
		// creating the Marker object. Once the Marker object is instantiated, its
		// position will be available as a google.maps.LatLng object. In this case,
		// we retrieve the marker's position using the
		// google.maps.LatLng.getPosition() method.
		var infowindow = new google.maps.InfoWindow({
			content : '<p>Marker Location:' + marker.getPosition() + '</p>'
		});

		google.maps.event.addListener(marker, 'click', function() {
			infowindow.open(map, marker);
		});
	}

	google.maps.event.addDomListener(window, 'load', initialize);
</script>
</head>

<body>
	<div id="map-canvas" style="height: 500px; width: 500px;"></div>
</body>
</html>