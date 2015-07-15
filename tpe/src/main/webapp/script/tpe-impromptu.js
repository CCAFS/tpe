/**
 * The implementation of the TPE Tool welcome impromptu dialog form
 */
$(document)
		.ready(
				function() {

					var tourSubmitFunc = function(e, v, m, f) {
						if (v === -1) {
							$.prompt.prevState();
							return false;
						} else if (v === 1) {
							$.prompt.nextState();
							return false;
						}
					}, tourStates = [
							{
								title : 'Welcome to TPE Visualization',
								html : 'Please click next to take a quick tour of how to visualize the TPE results.',
								buttons : {
									Next : 1
								},
								focus : 0,
								position : {
									container : '#tpe_map',
									x : 650,
									y : 10,
									width : 300,
									arrow : 'tl'
								},
								submit : tourSubmitFunc
							},
							{
								title : 'Step 1: Select output map to visualize',
								html : 'Select the map (Climate, Soil, Stability and TPE) from the dropdown to visualize.',
								buttons : {
									Prev : -1,
									Next : 1
								},
								focus : 1,
								position : {
									container : '#tpe_map',
									x : 248,
									y : 20,
									width : 300,
									arrow : 'lm'
								},
								submit : tourSubmitFunc
							},
{
								title : 'Step 2: Select other parameters',
								html : 'If Climate or Soil map was selected in step One (1) above, then select the region or country. else if TPE or Stability map was selected above, then select crop then cultiva and then region or country.',
								buttons : {
									Prev : -1,
									Next : 1
								},
								focus : 1,
								position : {
									container : '#tpe_map',
									x : 248,
									y : 50,
									width : 300,
									arrow : 'lm'
								},
								submit : tourSubmitFunc
							},
							{
								title : 'Step 3: Visualize the results on Google Map',
								html : 'After selecting the map options, the results will be displayed dynamically on the Google map depending on the selected parameters.',
								buttons : {
									Prev : -1,
									Next : 1
								},
								focus : 1,
								position : {
									container : '#tpe_map',
									x : 500,
									y : 50,
									width : 300,
									arrow : 'bc'
								},
								submit : tourSubmitFunc
							},
{
								title : 'Step 4: Zoom in or zoom out',
								html : 'View detailed results by zooming in or out using this control',
								buttons : {
									Prev : -1,
									Next : 1
								},
								focus : 1,
								position : {
									container : '#tpe_map',
									x : 40,
									y : 200,
									width : 300,
									arrow : 'lb'
								},
								submit : tourSubmitFunc
							},
{
								title : 'Step 5: The legend explains the symbols or colors.',
								html : 'The legend provides the information needed for the results map to make sense. It explains the symbols and colors and what they mean.',
								buttons : {
									Prev : -1,
									Next : 1
								},
								focus : 1,
								position : {
									container : '#tpe_map',
									x : 80,
									y : 180,
									width : 300,
									arrow : 'bl'
								},
								submit : tourSubmitFunc
							},
							{
								title : 'Step 6: View the TPE Graphics',
								html : 'Click on any feature on the map, then the corresponding Graphics will be displayed dynamically here on the right hand side of the page.',
								buttons : {
									Prev : -1,
									Next : 1
								},
								focus : 1,
								position : {
									container : '#tpe_analytics',
									x : -290,
									y : 10,
									width : 275,
									arrow : 'rm'
								},
								submit : tourSubmitFunc
							},
{
								title : 'Step 7: View Info Window',
								html : 'Hover (mouse over) on any feature on the map, then the corresponding information will be displayed dynamically here on the top right side of the page in the info window',
								buttons : {
									Prev : -1,
									Next : 1
								},
								focus : 1,
								position : {
									container : '#tpe_map',
									x : 500,
									y : 10,
									width : 275,
									arrow : 'rt'
								},
								submit : tourSubmitFunc
							},
							{
								title : 'Step 8: View the Zoomed Graphics',
								html : 'Click on the zoom icon or button to view a zoomed or a maximized graphic result. The zoomed graphic has more options for export (pdf, png, etc), printing and a legend.',
								buttons : {
									Prev : -1,
									Next : 1
								},
								focus : 1,
								position : {
									container : '#tpe_analytics',
									x : -320,
									y : 40,
									width : 300,
									arrow : 'rt'
								},
								submit : tourSubmitFunc
							},
							{
								title : 'Step 9: View more Graphics',
								html : 'To view more graphics, please click on any of the following titles. Click done to exit.',
								buttons : {
									Done : 2
								},
								focus : 0,
								position : {
									container : '#analytics',
									x : -300,
									y : 400,
									width : 280,
									arrow : 'rt'//rm// rb'
								},
								submit : tourSubmitFunc
							} ];

					// $.prompt(tourStates);
					// run the TPE Welcome tour
					$('#tpe_main')
							.each(
									function(i, el) {
										var $ex = $(this), $run = $ex
												.find('.run'), code = $ex.find(
												'.code').text();
										$run.click(function(e) {
											e.preventDefault();
											(new Function(code))();
										});
									});

					// Return to the top of the page when the Done button is
					// clicked
					$('[name="jqi_5_buttonDone"]').click(function() {
						console.log('####################################');
						$("html, body").animate({
							scrollTop : 0
						}, "slow");
					});

					// Return to the top of the page when the close button is
					// clicked
					$('.jqiclose').click(function() {
						console.log('####################################');
						$("html, body").animate({
							scrollTop : 0
						}, "slow");
					});

					// TPE Welcome tour
					$('#TourLink').click(function(e) {
						// e.preventDefault();
						// $('#tour-tpe').click();
						$.prompt(tourStates);
					});
				});
