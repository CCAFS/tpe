$(document).ready(function() {
	jQuery.ajaxSetup({
		beforeSend : function() {
			jQuery("#loading").show();
		},
		complete : function() {
			jQuery("#loading").hide();
		}
	});
	
	// href for loading the selected params
	var href_output = "paramsOut.action";
	// Get the preselected Output (TPE, SOIL, CLIMATE)
	var outputValue = $("select#select_output").val();
	// var preselected_output = $("select#select_output").val();

	// The preselected country, crop
	var preselected_country, preselected_crop, preselected_cultivar;

	// href for loading the sub regions
	// var href_subregions = "subregions.action";
	// The url for loading the crop cultivars
	var href_cultivars = "cultivars.action";
	// The url to load the years
	var href_years = "years.action";

	// Get the select#select_output text
	var outputControl = document.getElementById('select_output');
	var outputText = outputControl.options[outputControl.selectedIndex].text;

	// console.log("Output VALUE: " + outputValue);
	// console.log("Output Text: " + outputText);
	// Initialize the sub regions

	// console.log('preselected tpe item value: ' + outputValue);
	// console.log('preselected tpe item text: ' + outputText);

	$("#params_out").load(href_output, {
		// Pass the selected param to the server before loading the
		// corresponding output params div
		selectedOutput : outputValue

	}, function() {
		// Initialize or preload the variables
		loadVariables(outputText);
		// Initialize or load the Google Map when all the variables are loaded
		initializeGoogleMap();
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
		outputText = outputControl.options[outputControl.selectedIndex].text;
		outputValue = $("select#select_output").val();
		// $('#params_out').hide().slideDown(1000);
		// Get the selected output id or text
		outputValue = $(this).val();
		// console.log('Selected out: ' + outputValue);
		// Dynamically load the params div
		$("#params_out").load(href_output, {
			// Pass the parameters to the action to return the params div
			selectedOutput : outputValue
		}, function() {
			// Load the variables for the selected output
			loadVariables(outputText);
			// Load the Google Map after the variables are loaded.
			initializeGoogleMap();
		}).hide().slideDown(1000);
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

	/**
	 * This function loads all the sub variables depending on the selected TPE
	 * output selected
	 */
	function loadVariables(otext) {
		// The variable type used to determine the variable to return from the
		// server
		var variableType;
		// Use the selected output text to determine the variables to display or
		// show
		switch (otext.toUpperCase()) {

		case 'TPE':
			// console.log('TPE SELECTED: '+ot.toUpperCase());
			// Get the preselected crop id
			preselected_crop = $("select#select_crop").val();
			// By default pre load the crop cultivars, when the crop is selected
			// by default
			$("#params_cultivars").load(href_cultivars, {
				// Pass the selected crop id parameter to the action to query
				// crop cultivars
				selectedCrop : preselected_crop
			}, function() {
				// Hide and slide the params_cultivars div
				$(this).hide().slideDown(1000);
				/* $(this).slideUp(0).slideDown(1000); */

				// Load the Google Map, the selected crop cultivar changes
				 initializeGoogleMap();
			});

			// Automatically reload the crop cultivars when the crop change
			$("select#select_crop").change(function() {
				var selectedCrop = $(this).val();
				$("#params_cultivars").load(href_cultivars, {
					// Pass the crop parameters to the action to query crop
					// cultivars
					selectedCrop : selectedCrop
				}, function() {
					// Hide and slide the params_cultivars div
					$(this).hide().slideDown(1000);
					/* $(this).slideUp(0).slideDown(1000); */

					// Load the Google Map, the selected crop changes
					initializeGoogleMap();
				});

			});

			// Automatically reload the Google Map when the selected crop change
			$("select#select_cultivar").change(function() {
				initializeGoogleMap();
			});

			/*
			 * // Load the sub regions for the selected country
			 * loadSubregions(); // Automatically reload the google map
			 * $("select#select_regions").change(function() { // Load the Google
			 * Map, the selected sub regions changes initializeGoogleMap();
			 * 
			 * }); $("select#select_regions").click(function() { // Load the
			 * Google Map, the selected sub regions changes
			 * initializeGoogleMap();
			 * 
			 * });
			 */

			// Load the years
			// loadYears();
			// Automatically reload the Google Map when the selected years
			// change
			/*
			 * $("select#select_years").change(function() {
			 * initializeGoogleMap(); });
			 */
			/*
			 * $("select#select_years").click(function() {
			 * initializeGoogleMap(); });
			 */
			// Automatically reload the Google Map when the selected scenario
			// change
			$("select#select_scenario").change(function() {
				initializeGoogleMap();
			});

			// Automatically reload the Google Map when the selected sowing
			// window
			// change
			/*
			 * $("select#select_window").change(function() {
			 * initializeGoogleMap(); });
			 */
			$("select#select_country").change(function() {
				initializeGoogleMap();
			});

			break;

		case 'SOIL':
			/*
			 * // console.log('TPE SELECTED: '+ot); // Load the sub regions for
			 * the selected country loadSubregions(); // Automatically reload
			 * the google map $("select#select_regions").change(function() { //
			 * Load the Google Map, the selected sub regions changes
			 * initializeGoogleMap();
			 * 
			 * }); $("select#select_regions").click(function() { // Load the
			 * Google Map, the selected sub regions changes
			 * initializeGoogleMap();
			 * 
			 * });
			 */
			// Automatically reload the Google Map when the soil properties
			// change
			/*
			 * $("select#select_properties").change(function() {
			 * initializeGoogleMap(); });
			 */
			/*$("select#select_properties").click(function() {
				initializeGoogleMap();
			});*/

			// Automatically reload the Google Map when the soil textures change
			/*
			 * $("select#select_textures").change(function() {
			 * initializeGoogleMap(); });
			 */
			$("select#select_textures").click(function() {
				initializeGoogleMap();
			});

			// Automatically reload the map when the country change
			$("select#select_country").change(function() {
				initializeGoogleMap();
			});

			break;

		case 'CLIMATE':

			$("select#select_country").change(function() {
				initializeGoogleMap();
			});

			// console.log('TPE SELECTED: ' + outputText.toUpperCase());
			// Automatically reload the Google Map when the climate properties
			// change

			/*$("select#select_indicators").change(function() {
				initializeGoogleMap();
			});*/

			/*
			 * $("select#select_properties").click(function() {
			 * initializeGoogleMap(); });
			 */
			// Load the sub regions
			/*
			 * loadSubregions(); // Automatically reload the google map
			 * 
			 * $("select#select_regions").change(function() { // Load the Google
			 * Map, the selected sub regions changes initializeGoogleMap();
			 * 
			 * });
			 * 
			 * $("select#select_regions").click(function() { // Load the Google
			 * Map, the selected sub regions changes initializeGoogleMap();
			 * 
			 * });
			 */

			// Load the years
			// loadYears();
			// Automatically reload the Google Map when the selected years
			// change
			/*
			 * $("select#select_years").change(function() {
			 * initializeGoogleMap(); });
			 */
			/*
			 * $("select#select_years").click(function() {
			 * initializeGoogleMap(); });
			 */

			// Automatically reload the Google Map when the soil textures change
			/*
			 * $("select#select_stations").change(function() {
			 * initializeGoogleMap(); });
			 */
			/*
			 * $("select#select_stations").click(function() {
			 * initializeGoogleMap(); });
			 */

			break;
		default:
			break;
		}
	}

	/**
	 * This function loads the sub regions
	 */
	function loadSubregions() {
		// Get the preselected country id
		preselected_country = $("select#select_country").val();
		// By default pre load the sub regions, when the initial country is
		// preselected by default
		$("#params_regions").load(href_subregions, {
			// Pass the parameters to the action to query sub regions
			selectedCountry : preselected_country
		}, function() {
			// Hide and slide the params_region div
			$(this).hide().slideDown(1000);
			/* $(this).slideUp(0).slideDown(1000); */

			// Load the Google Map, the selected country changes
			initializeGoogleMap();
		});

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

				// Load the Google Map, the selected country changes
				initializeGoogleMap();
			});

		});

	}

	/**
	 * This function loads the years based on the selected TPE output parameter
	 */
	function loadYears() {
		// Get the preselected country id
		var _country = $("select#select_country").val();
		var _cultivar;
		// Get the preselected crop id
		// preselected_crop = $("select#select_crop").val();

		outputText = outputControl.options[outputControl.selectedIndex].text;

		if (outputText == 'TPE') {
			// If TPE was selected, load the years based on the selected crop
			// cultivar and selected country
			// Get the preselected crop cultivar id
			_cultivar = $("select#select_cultivar").val();
			// TODO To add more params

		} else if (outputText == 'CLIMATE') {
			// If CLIMATE was selected, then load the years based on the
			// selected country only
			// TODO To add more params
			// Set the cultivar to null, because CLIMATE was selected.
			_cultivar = null;

		}

		$("#params_years").load(href_years, {
			// Pass the parameters to the action to query sub regions
			selectedCountry : _country,
			selectedCultivar : _cultivar
		}, function() {
			// Hide and slide the params_region div
			$(this).hide().slideDown(1000);
			/* $(this).slideUp(0).slideDown(1000); */

			// Load the Google Map, the selected country changes
			// initializeGoogleMap();
		});

	}

});