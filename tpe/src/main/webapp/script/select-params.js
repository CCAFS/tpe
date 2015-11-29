/*$(function() {*/
jQuery(document)
		.ready(
				function($) {

					// Make the Map Options dreaggable
					// Initialize the draggable with the containment option
					// specified:

					/* $(function() { */
					$("#select_variables").draggable({
						containment : "#tpe_main",
						cursor : 'move',
						snap : '#tpe_main'
					});
					/* }); */

					// For the graphics dialog window
					$(window).resize(function() {
						$('.ui-dialog').css({
							'width' : $(window).width(),
							'height' : $(window).height(),
							'left' : '0px',
							'top' : '0px'
						});
					}).resize();

					var href_options = "/tpe/json/map/options.action";
					var cropCultivars;
					$
							.ajax({
								type : 'GET',
								url : href_options,
								data : {},
								dataType : 'json',
								success : function(data) {
									var optionsMap = '', optionsRegions = '', optionsCrops = '', optionsCultivars = '';
									var listMap = data.maps, listRegions = data.regions, listCrops = data.crops, listCultivars = data.cultivars;
									// Global variable
									cropCultivars = data.cultivars;
									// Map options
									$.each(listMap, function(key, value) {
										optionsMap += '<option value="' + key
												+ '">' + value + '</option>';
									});
									$("select#select_output").html(optionsMap);

									// Regions
									$.each(listRegions, function(key, value) {
										optionsRegions += '<option value="'
												+ key + '">' + value
												+ '</option>';
									});
									$("select#select_country").html(
											optionsRegions);

									// Crops
									$.each(listCrops, function(key, value) {
										optionsCrops += '<option value="' + key
												+ '">' + value + '</option>';
									});
									$("select#select_crop").html(optionsCrops);

									// Cultivars
									var i = 1;
									$
											.each(
													listCultivars,
													function(key, cultivarsMap) {
														if (i == 1) {
															$
																	.each(
																			cultivarsMap,
																			function(
																					key,
																					value) {
																				optionsCultivars += '<option value="'
																						+ key
																						+ '">'
																						+ value
																						+ '</option>';
																			});
															i = i + 1;
														}
													});
									$("select#select_cultivar").html(
											optionsCultivars);

									var sel = document
											.getElementById('select_output');
									// console.log("Id of selected option: " +
									// sel.options[sel.selectedIndex].value);
									// console.log("Text of selected option: " +
									// sel.options[sel.selectedIndex].text);
									var selText = sel.options[sel.selectedIndex].text;
									if ((selText == 'TPE')
											|| (selText == 'Stability'))
										$('#params_cultivars').show();
									else
										$('#params_cultivars').hide();

									visualizeResults();
								}
							});

					// Automatically reload the sub regions when the country
					// change
					$(
							"select#select_output,select#select_country,select#select_crop,select#select_cultivar")
							.change(
									function() {
										// Check the cook if the user has not
										// disabled the
										// dialog
										// create cookie 'dntshow' with no value
										var disableGuide = $
												.cookie('dntshowGuide');

										if (disableGuide == 'yes') {
											// Don't show the dialog
										} else {
											// Initialize or load the Google Map
											// when all the
											// variables are loaded
											$('#TourLink').trigger('click');
											// cookie has no value so launch
											// home overlay dialog on page load
										}

										// console.log($(this).attr('id'));
										// console.log($(this).val());
										if ($(this).attr('id') == 'select_output') {
											// console.log($("#select_output
											// option:selected").text());
											var selMap = $(
													"#select_output option:selected")
													.text();
											// var sel =
											// document.getElementById('select_output');
											// console.log("Id of selected
											// option: " +
											// sel.options[sel.selectedIndex].value);
											// console.log("Text of selected
											// option: " +
											// sel.options[sel.selectedIndex].text);
											// var selText=
											// sel.options[sel.selectedIndex].text;
											if ((selMap == 'TPE')
													|| (selMap == 'Stability'))
												$('#params_cultivars').show();
											else
												$('#params_cultivars').hide();
										} else if ($(this).attr('id') == 'select_crop') {
											var selCropId = $(this).val();
											if (selCropId != null) {
												var cultsMap = cropCultivars[selCropId];
												var cultOptions = '';
												$
														.each(
																cultsMap,
																function(key,
																		value) {
																	cultOptions += '<option value="'
																			+ key
																			+ '">'
																			+ value
																			+ '</option>';
																});
											}
											$("select#select_cultivar").html(
													cultOptions);
										}

										   clearCharts('#plot_box');
									       clearCharts('#plot_pcew');
									       clearCharts('#plot_raincum');
									       clearCharts('#plot_rainsum');
									       clearCharts('#plot_wagt');
									       clearCharts('#plot_lai'); 
									       
									       clearCharts('#soil_plot');
									       clearCharts('#rain_radiation'); 
									       
									       hideInfoWindow();
										// Load the Google Map after the
										// variables are loaded.
										visualizeResults();
									});

					// Slide toggle the select variables
					$('#select_variables h4').click(
							function() {
								$(this).toggleClass('expanded').toggleClass(
										'collapsed');
								$('#variables').stop().slideToggle('slow',
										function() {

										});
							});

					// Slide toggle the select tpe analytics divs
					$('#tpe_analytics h4').click(
							function() {

								$('#analytics').stop().slideToggle('slow',
										function() {

										});
								$('#tpe_analytics')
										.toggleClass('tpe_analytics');
								$(this).toggleClass('expanded').toggleClass(
										'collapsed');
								$('#tpe_map').toggleClass('tpe_map_min')
										.toggleClass('tpe_map_max');
							});

					// Slide toggle the legend h3 header.
					// This will collapse or expand the legend contents.
					$('#legend-container h3').click(
							function() {
								$(this).toggleClass('expanded').toggleClass(
										'collapsed');
								$('.legend').stop().slideToggle('slow',
										function() {

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

					$('#plot_slide h3')
							.click(
									function() {
										$(this).toggleClass('expanded')
												.toggleClass('collapsed');
										// If expanded add class
										// .current_grafico
										// else remove it
										if ($(this).hasClass('expanded')) {
											if ($(this).hasClass(
													'current_grafico')) {

											} else
												$(this).addClass(
														'current_grafico');
										} else
											$(this).removeClass(
													'current_grafico');

										$(this).siblings('h3').each(
												function() {
													if ($(this).hasClass(
															'expanded')) {
														$(this).removeClass(
																'expanded');
													}

													if ($(this).hasClass(
															'collapsed')) {

													} else {
														$(this).addClass(
																'collapsed');
													}
													// Remove the current_grafoc
													// class if it exists
													$(this).removeClass(
															'current_grafico');
												});

										$(this).next('div').stop().slideToggle(
												'slow', function() {

												});

										if ($(this).next('div').siblings(
												'div:visible')) {
											$(this).next('div').siblings(
													'div:visible').slideToggle(
													'slow', function() {

													});
										} else
											$(this).next('div').siblings('div')
													.stop().slideToggle('slow',
															function() {

															});
									});

				});