/**
 * The implementation of the TPE Tool welcome impromptu dialog form
 */
jQuery(document)
		.ready(
				function($) {
					/*
					 * $(document) .ready( function() {
					 */

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
								title : 'Welcome to Visualization',
								html : 'Please click next to take a quick tour of how to visualize the results.',
								buttons : {
									Next : 1
								},
								focus : 0,
								position : {
									container : '#tpe_map',
									x : 300,
									y : 10,
									width : 300,
									arrow : 'tl'
								},
								submit : tourSubmitFunc
							},
							{
								title : 'Step 1: Select Output Result',
								html : '<div class="step-parent-large">Select Output (Climate, Soil, Stability or TPE) from the dropdown.'
										+ 'Each selected output will dynamically dislay other corresponding parameters below. If Climate or Soil was selected, '
										+ 'then select the region else if TPE or Stability was selected, then select crop then cultiva and then region respectively.'
										+ '<div class="step-wrap">'
										+ '<div><img src="img/output-climate.png" width="120" /></div>'
										+ '<div><img src="img/output-tpe.png" width="120" /></div>'
										+ '</div></div>',
								buttons : {
									Prev : -1,
									Next : 1
								},
								focus : 1,
								position : {
									container : '#tpe_map',
									x : 270,
									y : 20,
									width : 450,
									arrow : 'lt'
								},
								submit : tourSubmitFunc
							},
							{
								title : 'Step 2: Visualize Results',
								html : '<div class="step-parent">After selecting the output, the results will be displayed dynamically on the Google map.'
										+ '<div class="step-img"><img src="resources/visualization.png" width="250" /></div></div>',
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
								title : 'Step 3: Zoom Control',
								html : '<div class="step-parent">View detailed results by zooming in or out using the zoom control.'
										+ '<div class="step-img"><img src="img/map-zoom.png" width="30" /></div></div>',
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
									arrow : 'lm'
								},
								submit : tourSubmitFunc
							},
							{
								title : 'Step 4: The Legend',
								html : '<div class="step-parent">The legend provides the information needed for the map results to make sense. It explains what the symbols/colors mean.'
										+ '<div class="step-img"><img src="img/map-legend.png" width="200" /></div></div>',
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
									arrow : 'lb'
								},
								submit : tourSubmitFunc
							},
							{
								title : 'Step 5: View Graphics',
								html : '<div class="step-parent">Click on any feature on the map, then the corresponding Graphics will be displayed dynamically here.'
										+ '<div class="step-img"><img src="img/map-graphics.png" width="200" height="161" /></div></div>',
								buttons : {
									Prev : -1,
									Next : 1
								},
								focus : 1,
								position : {
									container : '#tpe_analytics',
									x : -290,
									y : 10,
									width : 300,
									arrow : 'rt'
								},
								submit : tourSubmitFunc
							},
							{
								title : 'Step 6: View Info Window',
								html : '<div class="step-parent">Hover (mouse) on any feature on the map, then the corresponding information will be displayed dynamically here in the info window'
										+ '<div class="step-img"><img src="img/map-infow.png" width="250" /></div></div>',
								buttons : {
									Prev : -1,
									Next : 1
								},
								focus : 1,
								position : {
									container : '#tpe_map',
									x : 500,
									y : 10,
									width : 300,
									arrow : 'rt'
								},
								submit : tourSubmitFunc
							},
							{
								title : 'Step 7: Zoom Graphics',
								html : '<div class="step-parent">Click on the zoom/search icon to view a large graphic. The large graphic has more options for export (pdf, png, etc), printing and a legend.'
										+ '<div class="step-img"><img src="img/zoom-in.png" width="32" /></div></div>',
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
								title : 'Step 8: View more Graphics',
								html : '<div class="step-parent">Click on More Graphics to view more graphics. <p>'
										+ '<input type="checkbox" id="dont-show-again-vis">Don\'t show this again</p>.'
										+ '<div class="step-img"><img src="img/more-graphics.png" width="200" height="68" /></div></div>',
								buttons : {
									Done : 2
								},
								focus : 0,
								position : {
									container : '#analytics',
									x : -300,
									y : 400,
									width : 300,
									arrow : 'rt'// rm// rb'
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

						$("html, body").animate({
							scrollTop : 0
						}, "slow");
					});

					// Return to the top of the page when the close button is
					// clicked
					$('.jqiclose').click(function() {

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
					
					
					// Set the cook if the 'don't show this again is clicked
					dontShowOverlayGuide();
					
				});


function dontShowOverlayGuide() {

	$('#dont-show-again-vis').click(function() {
		if (this.checked) {
			$.cookie('dntshowGuide', 'yes', {
				expires : 30
			// set value='yes' and expiration in 30 days
			});
		} else {
			// Nothing will happen.
			// dialog will be displayed again
		}
	});
}
