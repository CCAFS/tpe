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
								title : 'Welcome to TPE Visualization Platform',
								html : 'Please take a quick tour of how to use the platform.',
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
								title : 'Step 1: Select the Map Options',
								html : 'First select the map options here on the left hand side of the page.',
								buttons : {
									Prev : -1,
									Next : 1
								},
								focus : 1,
								position : {
									container : '#select_output',
									x : 248,
									y : -10,
									width : 300,
									arrow : 'lm'
								},
								submit : tourSubmitFunc
							},
							{
								title : 'Step 2: View Google Map Results',
								html : 'After selecting the map options, view the target population environments.',
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
								title : 'Step 3: View the TPE Graphics',
								html : 'Click on the desired Google Map feature, then the corresponding Graphics will be displayed here on the right hand side of the page.',
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
								title : 'Step 4: View the Zoomed Graphics',
								html : 'Click on the zoom icon or button to view a zoom graphic.',
								buttons : {
									Prev : -1,
									Next : 1
								},
								focus : 1,
								position : {
									container : '#plot_box',
									x : -320,
									y : 0,
									width : 300,
									arrow : 'rt'
								},
								submit : tourSubmitFunc
							},
							{
								title : 'Step 4: View more Graphics',
								html : 'To view more graphics, please click on any of the following titles.',
								buttons : {
									Done : 2
								},
								focus : 0,
								position : {
									container : '#analytics',
									x : -300,
									y : 400,
									width : 280,
									arrow : 'rm'// rb
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