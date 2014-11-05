$(document).ready(function() {
	// href for loading the soil params
	var href_output = "paramsOut.action";
	// Get the preselected Output (TPE, SOIL, CLIMATE)
	var out = $("select#select_output").val();
	// var preselected_output = $("select#select_output").val();
	console.log('Selected out: ' + out);
	// Initialize the sub regions
	$("#params_out").load(href_output, {
		// Pass the selected param to the server before loading the
		// corresponding output params div
		selectedOutput : out
	}, function() {

	});
	// Automatically reload the corresponding output params div when the output
	// item is selected
	$("select#select_output").click(function() {
		// Get the selected output id or text
		out = $(this).val();
		console.log('Selected out: ' + out);
		$("#params_out").load(href_output, {
			// Pass the parameters to the action to return the params div
			selectedOutput : out
		}, function() {
			/* $(this).slideUp(0).slideDown(1000); */
		});
	});
	// Automatically reload the sub regions when the country change
	$("select#select_output").change(function() {
		$('#params_out').slideUp(0).slideDown(1000);
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