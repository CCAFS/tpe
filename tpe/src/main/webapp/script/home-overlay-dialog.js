$(document)
		.ready(
				function() {
					$('#panel-top').on("click", function() {
						window.location = "casestudy/index.jspx";
					});
					$('#panel-bottom').on("click", function() {
						window.location = "contribute.jspx";
					});
					$('#panel-left').on("click", function() {
						window.location = "documentation.jspx";
					});
					$('#panel-right').on("click", function() {
						window.location = "visualization.jspx";
					});
					// Panel info events
					// Left panel (Visualization)
					$('#panel-left,#panel-bottom,#panel-top,#panel-right')
							.mousemove(
									function(e) {
										// $('#home-panel-infos').css({'top':e.clientY,'left':e.clientX});
										var w = Math
												.max(
														document.documentElement.clientWidth,
														window.innerWidth || 0)
										var h = Math
												.max(
														document.documentElement.clientHeight,
														window.innerHeight || 0)
										var d = $(document).scrollTop();
										var pos = $(this).position();

										// If left panel
										if ($(this).is('#panel-left')) {
											$('#home-panel-infos h2').css(
													'color', '#a6d832').text(
													'Documentation');
											$('#home-panel-infos span')
													.html(
															'Click to view the scientific documentation.');
											$("#panel-img")
													.html(
															'<img class="panel-img" src="resources/getinvolved.png" />');
										} else if ($(this).is('#panel-bottom')) {
											$('#home-panel-infos h2').css(
													'color', '#ef2d38').text(
													'Get Involved');
											$('#home-panel-infos span')
													.html(
															'Click to learn how you can get involved in this study.');
											$("#panel-img")
													.html(
															'<img class="panel-img" src="resources/getinvolved.png" />');
										} else if ($(this).is('#panel-right')) {
											$('#home-panel-infos h2').css(
													'color', '#6ba2dc').text(
													'Visualization');
											$('#home-panel-infos span')
													.html(
															'Click to visualize TPE results (Climate, Soil, Stability and TPE)');
											$("#panel-img")
													.html(
															'<img class="panel-img" src="resources/visualization.png" />');
										} else if ($(this).is('#panel-top')) {
											$('#home-panel-infos h2').css(
													'color', '#f47216').text(
													'Methodology');
											$('#home-panel-infos span')
													.html(
															'Click to learn more about this methology and case studies.');
										}
										$('#home-panel-infos').offset({
											left : e.pageX,
											top : e.pageY + 40
										});

										$('#home-panel-infos').show();
										// Change the div position if partially
										// visible.
										// if (pos.top > h + d || pos.top > h -
										// d){
										if (e.pageY > h + d || e.pageY > h - d) {
											$('#home-panel-infos')
													.offset(
															{
																left : e.pageX,
																top : e.pageY
																		- ($(
																				this)
																				.height() - 50)
															});
										}
										// horizontal
										// #ef2d38 red
										// orange #f47216
										// #a6d832 green
										// #6ba2dc blue
										// if (pos.left < 0 - $(this).width() ||
										// pos.left > w){
										/*
										 * if (e.pageX< 0 - $(this).width() ||
										 * e.pageX > w){
										 * $('#home-panel-infos').offset({ left:
										 * e.pageX-$(this).height(), top:
										 * e.pageY }); }
										 */

										/*
										 * if($('#home-panel-infos').visible(true)){
										 * $('#home-panel-infos').offset({ left:
										 * e.pageX, top:
										 * e.pageY-$(this).height() }).slide(
										 * 2000 ); }
										 */

									});

					$("#panel-left,#panel-bottom,#panel-top,#panel-right")
							.mouseout(function() {
								$("#home-panel-infos").hide();
							});

					/*
					 * $("#panel-left,#panel-bottom,#panel-top,#panel-right")
					 * .hover( function() { $($(this)).find('img').attr('src',
					 * 'img/left-home.png'); });
					 */

					$("#panel-left").hover(function() {
						$("img", this).attr("src", "img/1-left-hover.png");
					}, function() {
						$("img", this).attr("src", "img/1-left.png");
					});

					$("#panel-right").hover(function() {
						$("img", this).attr("src", "img/3-right-hover.png");
					}, function() {
						$("img", this).attr("src", "img/3-right.png");
					});

					$("#panel-top").hover(function() {
						$("img", this).attr("src", "img/2-top-hover.png");
					}, function() {
						$("img", this).attr("src", "img/2-top.png");
					});

					$("#panel-bottom").hover(function() {
						$("img", this).attr("src", "img/4-bottom-hover.png");
					}, function() {
						$("img", this).attr("src", "img/4-bottom.png");
					});

					// Set the cook if the 'don't show this again is clicked
					dontShowOverlay();
					// Check the cook if the user has not disabled the dialog
					// create cookie 'dntshow' with no value
					var disableDialog = $.cookie('dntshow');

					if (disableDialog == 'yes') {
						// Don't show the dialog
					} else {
						// Show the dialog form
						loadHomeOverlayDialog();
						// cookie has no value so launch
						// home overlay dialog on page load
					}
					// Launch or show the home overlay dialog
					// loadHomeOverlayDialog();
				});

function dontShowOverlay() {

	$('#dont-show-again').click(function() {
		if (this.checked) {
			$.cookie('dntshow', 'yes', {
				expires : 30
			// set value='yes' and expiration in 30 days
			});
		} else {
			// Nothing will happen.
			// dialog will be displayed again
		}
	});
}

function loadHomeOverlayDialog() {

	$('#overlay-dialog').dialog({
		autoOpen : true,
		modal : false,
		title : 'Welcome to CCAFS-TPE Platform',
		// bgiframe : true,

		// width : $(window).width(),
		// height : $(window).height(),

		/* height : 800, */
		/* width : 'auto', */
		width : 850,
		height : 'auto',

		// fullScreen: true,
		// fullScreenForce: true,

		draggable : true,
		resizable : false,
		// open : function() {
		// $(".ui-dialog-titlebar").hide();
		// },

		"buttons" : [ {
			id : "bttnClose",
			text : "Return to website",
			click : function() {
				$('#overlay-dialog').dialog('close');
			}
		} ]
	});

	// For closing the dialog
	$('a.close').click(function() {
		$('#overlay-dialog').dialog('close');
	});

	// Hide the title bar of the dialog
	// $(".ui-widget-header").hide();
	$('.ui-dialog-titlebar').remove();
	// $(".ui-dialog-titlebar").hide();

}