/*$(function() {*/
jQuery(document).ready(function($){

	// Make the Map Options dreaggable
	// Initialize the draggable with the containment option
	// specified:
	
/*	$(function() {*/
		$("#select_variables").draggable({
			containment : "#tpe_main",
			cursor : 'move',
			snap : '#tpe_main'
		});
	/*});*/

	// For the graphics dialog window
	$(window).resize(function() {
		$('.ui-dialog').css({
			'width' : $(window).width(),
			'height' : $(window).height(),
			'left' : '0px',
			'top' : '0px'
		});
	}).resize();

	jQuery.ajaxSetup({
		beforeSend : function() {
			jQuery("#loading").show();

		},
		complete : function() {
			jQuery("#loading").hide();
		}
	});

	// href for loading the selected params
	var href_output = "map/options.action";
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
	// var href_years = "years.action";

	// Get the select#select_output text
	var outputControl = document.getElementById('select_output');
	var outputText = outputControl.options[outputControl.selectedIndex].text;

	$("#params_out").load(href_output, {
		// Pass the selected param to the server before loading
		// the
		// corresponding output params div
		selectedOutput : outputValue

	}, function() {
		// Add the info dialog that shows the welcome infos and
		// briefly explains the platform to the user

		// Initialize or preload the variables
		loadVariables(outputText);
		// Initialize or load the Google Map when all the
		// variables are loaded
		$('#TourLink').trigger('click');
	});

	// Automatically reload the sub regions when the country
	// change
	$("select#select_output").change(function() {
		outputText = outputControl.options[outputControl.selectedIndex].text;
		outputValue = $("select#select_output").val();
		// $('#params_out').hide().slideDown(1000);
		// Get the selected output id or text
		outputValue = $(this).val();
		// console.log('Selected out: ' +
		// outputValue);
		// Dynamically load the params div
		$("#params_out").load(href_output, {
			// Pass the parameters to the action
			// to return the params div
			selectedOutput : outputValue
		}, function() {
			// Load the variables for the
			// selected output
			loadVariables(outputText);
			// Load the Google Map after the
			// variables are loaded. 
			visualizeResults();
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

	// Slide toggle the legend h3 header.
	// This will collapse or expand the legend contents.
	$('#legend-container h3').click(function() {
		$(this).toggleClass('expanded').toggleClass('collapsed');
		$('.legend').stop().slideToggle('slow', function() {

		});
	});

	// Collapse these graphics on load
	$('.plot_lai').hide();
	$('.plot_wagt').hide();
	$('.plot_rainsum').hide();
	$('.plot_raincum').hide();

	// Slide toggle the particular graphics div when the h4 is
	// clicked on

	var wagt = document.getElementById('wagt');
	var lai = document.getElementById('lai');
	var rainsum = document.getElementById('rainsum');
	var raincum = document.getElementById('raincum');

	$('#plot_slide h3').click(
			function() {
				$(this).toggleClass('expanded').toggleClass('collapsed');
				// If expanded add class
				// .current_grafico
				// else remove it
				if ($(this).hasClass('expanded')) {
					if ($(this).hasClass('current_grafico')) {

					} else
						$(this).addClass('current_grafico');
				} else
					$(this).removeClass('current_grafico');

				$(this).siblings('h3').each(function() {
					if ($(this).hasClass('expanded')) {
						$(this).removeClass('expanded');
					}

					if ($(this).hasClass('collapsed')) {

					} else {
						$(this).addClass('collapsed');
					}
					// Remove the current_grafoc
					// class if it exists
					$(this).removeClass('current_grafico');
				});

				// console.log($(this));
				$(this).next('div').stop().slideToggle('slow', function() {

				});

				if ($(this).next('div').siblings('div:visible')) {
					$(this).next('div').siblings('div:visible').slideToggle(
							'slow', function() {

							});
				} else
					$(this).next('div').siblings('div').stop().slideToggle(
							'slow', function() {

							});
			});

	/**
	 * This function loads all the sub variables depending on the selected TPE
	 * output selected
	 */
	function loadVariables(otext) {
		// The variable type used to determine the variable to
		// return from the
		// server
		var variableType;
		// Use the selected output text to determine the
		// variables to display or
		// show
 
		switch (otext.toUpperCase()) {

		case 'TPE':
		case 'STABILITY':
			// console.log('TPE SELECTED: '+ot.toUpperCase());
			// Get the preselected crop id
			preselected_crop = $("select#select_crop").val();
			// By default pre load the crop cultivars, when the
			// crop is selected
			// by default
			$("#params_cultivars").load(href_cultivars, {
				// Pass the selected crop id parameter to the
				// action to query
				// crop cultivars
				selectedCrop : preselected_crop
			}, function() {
				// Hide and slide the params_cultivars div
				$(this).hide().slideDown(1000);
				/* $(this).slideUp(0).slideDown(1000); */

				// Load the Google Map, the selected crop
				// cultivar changes 
				visualizeResults();
			});

			// Automatically reload the crop cultivars when the
			// crop change
			$("select#select_crop").change(function() {
				var selectedCrop = $(this).val();
				$("#params_cultivars").load(href_cultivars, {
					// Pass the crop parameters to the action to
					// query crop
					// cultivars
					selectedCrop : selectedCrop
				}, function() {
					// Hide and slide the params_cultivars div
					$(this).hide().slideDown(1000);
					/* $(this).slideUp(0).slideDown(1000); */

					// Load the Google Map, the selected crop
					// changes 
				});

			});

			// Automatically reload the Google Map when the
			// selected crop change
			$("select#select_cultivar").change(function() { 
				visualizeResults();
			});
		 
			$("select#select_country").change(function() { 
				visualizeResults();
			});

			break;

		case 'SOIL':
		 
			visualizeResults();
			// Automatically reload the map when the country
			// change
			$("select#select_country").change(function() {
				visualizeResults(); 
			});

			break;

		case 'CLIMATE':

			visualizeResults();

			// console.log('Getting climate case');
			$("select#select_country").change(function() {
				// console.log('Getting climate casing');
				visualizeResults(); 
			});

			
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
		// By default pre load the sub regions, when the initial
		// country is
		// preselected by default
		$("#params_regions").load(href_subregions, {
			// Pass the parameters to the action to query sub
			// regions
			selectedCountry : preselected_country
		}, function() {
			// Hide and slide the params_region div
			$(this).hide().slideDown(1000);
			/* $(this).slideUp(0).slideDown(1000); */

			// Load the Google Map, the selected country changes 
			visualizeResults();
		});

		// Automatically reload the sub regions when the country
		// change
		$("select#select_country").change(function() {
			var selectedCountry = $(this).val();
			$("#params_regions").load(href_subregions, {
				// Pass the parameters to the action to query
				// sub regions
				selectedCountry : selectedCountry
			}, function() {
				// Hide and slide the params_region div
				$(this).hide().slideDown(1000);
				/* $(this).slideUp(0).slideDown(1000); */

				// Load the Google Map, the selected country
				// changes 
				visualizeResults();
			});

		});
	}
});