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
	});
	// Automatically reload the sub regions when the country is selected
	$("select#select_country").click(function() {
		// Get the selected country id
		var selectedCountry = $(this).val();
		$("#params_regions").load(href_subregions, {
			// Pass the parameters to the action to query sub regions
			selectedCountry : selectedCountry
		}, function() {
		});
	});
	// Automatically reload the sub regions when the country change
	$("select#select_regions").change(function() {
	});
});