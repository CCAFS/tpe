$(document).ready(function() {
	// href for loading the sub regions
	var href_subregions = "subregions.action";
	// Get the preselected country id
	var preselected_country = $("select#select_country").val();
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
	// Automatically reload the sub regions when the country is selected
	// $("select#select_country").click(function() {
	// Get the selected country id
	/*
	 * var selectedCountry = $(this).val();
	 * $("#params_regions").load(href_subregions, { // Pass the parameters to
	 * the action to query sub regions selectedCountry : selectedCountry },
	 * function() { $(this).slideUp(0).slideDown(1000); });
	 */
	// });
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

	// Automatically reload the Google Map when the soil properties change
	$("select#select_properties").change(function() {
		initializeGoogleMap();
	});

	// Automatically reload the Google Map when the soil textures change
	$("select#select_textures").change(function() {
		initializeGoogleMap();
	});

});