$(document).ready(function() {
	// href for loading the selected params
	var href_output = "paramsOut.action";
	// Get the preselected Output (TPE, SOIL, CLIMATE)
	var outputValue = $("select#select_output").val();
	// var preselected_output = $("select#select_output").val();

	// href for loading the sub regions
	var href_subregions = "subregions.action";

	// Get the preselected country id
	var preselected_country = $("select#select_country").val();

	// Get the select#select_output text
	var outputControl = document.getElementById('select_output');
	var outputText = outputControl.options[outputControl.selectedIndex].text;

	console.log(outputValue);
	// Initialize the sub regions
	$("#params_out").load(href_output, {
		// Pass the selected param to the server before loading the
		// corresponding output params div
		selectedOutput : outputValue
	}, function() {

	});

	// Automatically reload the corresponding output params div when the output
	// item is selected
	/*
	 * $("select#select_output").click(function() { // Get the selected output
	 * id or text out = $(this).val(); console.log('Selected out: ' + out);
	 * $("#params_out").load(href_output, { // Pass the parameters to the action
	 * to return the params div selectedOutput : out }, function() {
	 * $(this).slideUp(0).slideDown(1000); }); });
	 */
	// Automatically reload the sub regions when the country change
	$("select#select_output").change(function() {
		// $('#params_out').hide().slideDown(1000);
		// Get the selected output id or text
		outputValue = $(this).val();
		console.log('Selected out: ' + outputValue);
		// Dynamically load the params div
		$("#params_out").load(href_output, {
			// Pass the parameters to the action to return the params div
			selectedOutput : outputValue
		}, function() {
			/* $(this).slideUp(0).slideDown(1000); */
			// Load the Google Map when the selected output chnages
			initializeGoogleMap();
		}).hide().slideDown(1000);
	});

	// Use the selected output text to determine the variables to display or
	// show
	switch (outputText.toUpperCase()) {
	case 'TPE':
		// Automatically reload the Google Map when the selected crop change
		$("select#select_crop").change(function() {
			initializeGoogleMap();
		});

		// Automatically reload the Google Map when the selected crop change
		$("select#select_cultivar").change(function() {
			initializeGoogleMap();
		});

		// Automatically reload the Google Map when the selected scenario change
		$("select#select_scenario").change(function() {
			initializeGoogleMap();
		});
		// Automatically reload the Google Map when the selected years change
		$("select#select_years").change(function() {
			initializeGoogleMap();
		});
		// Automatically reload the Google Map when the selected sowing window
		// change
		$("select#select_window").change(function() {
			initializeGoogleMap();
		});
		break;

	case 'SOIL':

		// console.log("Preselected Country: "
		// + preselected_regions);
		// Initialize the sub regions
		$("#params_regions").load(href_subregions, {
			// Pass the selected country to the server before loading the
			// corresponding sub regions
			selectedCountry : preselected_country
		}, function() {
			// $(this).slideUp(0).slideDown(1000);
		});
		// Automatically reload the Google Map when the soil properties change
		$("select#select_properties").change(function() {
			initializeGoogleMap();
		});

		// Automatically reload the Google Map when the soil textures change
		$("select#select_textures").change(function() {
			initializeGoogleMap();
		});

		break;

	case 'CLIMATE':
		// Automatically reload the Google Map when the climate properties
		// change
		$("select#select_properties").change(function() {
			initializeGoogleMap();
		});
		// Automatically reload the Google Map when the soil textures change
		$("select#select_stations").change(function() {
			initializeGoogleMap();
		});
		// Automatically reload the Google Map when the selected years change
		$("select#select_years").change(function() {
			initializeGoogleMap();
		});
		break;
	default:
		break;
	}
	// Automatically reload the sub regions when the country change
	$("select#select_country").change(function() {
		var selectedCountry = $(this).val();
		$("#params_regions").load(href_subregions, {
			// Pass the parameters to the action to query sub regions
			selectedCountry : selectedCountry
		}, function() {
			// Hide and slide the params_region div
			$(this).hide().slideDown(1000);
			/* $(this).slideUp(0).slideDown(1000); */

			// Load the Google Map, the selected country chnages
			initializeGoogleMap();
		});

	});

	// Slide toggle the select variables
	$('#select_variables h4').click(function() {
		$(this).toggleClass('expanded').toggleClass('collapsed');
		$('#variables').stop().slideToggle('slow', function() {

		});
	});

	// Slide toggle the select tpe analytics divs
	$('#tpe_analytics h4').click(function() {

		$('#analytics').stop().slideToggle('slow', function() {

		});
		$('#tpe_analytics').toggleClass('tpe_analytics');
		$(this).toggleClass('expanded').toggleClass('collapsed');
		$('#tpe_map').toggleClass('tpe_map_min').toggleClass('tpe_map_max');
	});
});