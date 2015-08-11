$(document)
		.ready(
				function() {
					$('#panel-top').on("click", function() {
						window.location = "documentation.jspx";
					});
					$('#panel-bottom').on("click", function() {
						window.location = "contribute.jspx";
					});
					$('#panel-left').on("click", function() {
						window.location = "visualization.jspx";
					});
					$('#panel-right').on("click", function() {
						window.location = "resources.jspx";
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
													'color', '#ef2d38').text(
													'Visualization');
											$('#home-panel-infos span')
													.html(
															'Click to visualize TPE results (Climate, Soil, Stability and TPE)');
											$("#panel-img")
													.html(
															'<img class="panel-img" src="img/visualization.png" />');
										} else if ($(this).is('#panel-bottom')) {
											$('#home-panel-infos h2').css(
													'color', '#a6d832').text(
													'Get Involved');
											$('#home-panel-infos span')
													.html(
															'Click to learn how you can get involved in this study.');
											$("#panel-img")
													.html(
															'<img class="panel-img" src="img/getinvolved.png" />');
										} else if ($(this).is('#panel-right')) {
											$('#home-panel-infos h2').css(
													'color', '#6ba2dc').text(
													'Resources');
											$('#home-panel-infos span')
													.html(
															'Click to access more resources.');
										} else if ($(this).is('#panel-top')) {
											$('#home-panel-infos h2').css(
													'color', '#f47216').text(
													'Documentation');
											$('#home-panel-infos span')
													.html(
															'Click to view the scientific documentation.');
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

				/*	$("#panel-left,#panel-bottom,#panel-top,#panel-right")
							.hover(
									function() {
										$($(this)).find('img').attr('src',
												'img/left-home.png');
									});*/

					$("#panel-left").hover(function() {
						$("img", this).attr("src", "img/left-hover.png");
					}, function() {
						$("img", this).attr("src", "img/left-home.png");
					});

					$("#panel-right").hover(function() {
						$("img", this).attr("src", "img/right-hover.png");
					}, function() {
						$("img", this).attr("src", "img/right-home.png");
					});

					$("#panel-top").hover(function() {
						$("img", this).attr("src", "img/top-hover.png");
					}, function() {
						$("img", this).attr("src", "img/top-home.png");
					});

					$("#panel-bottom").hover(function() {
						$("img", this).attr("src", "img/bottom-hover.png");
					}, function() {
						$("img", this).attr("src", "img/bottom-home.png");
					});

					/* }); */

					// Model Dialog
					/* $(function() { */

					var appendthis = ("<div class='modal-overlay js-modal-close'></div>");

					$('a[data-modal-id]').click(function(e) {
						e.preventDefault();
						$("body").append(appendthis);
						$(".modal-overlay").fadeTo(500, 0.7);
						// $(".js-modalbox").fadeIn(500);
						var modalBox = $(this).attr('data-modal-id');
						$('#' + modalBox).fadeIn($(this).data());
					});

					$(".js-modal-close, .modal-overlay").click(
							function() {
								$(".modal-box, .modal-overlay").fadeOut(500,
										function() {
											$(".modal-overlay").remove();
										});
							});
					$(window).resize(function() {
						$(".modal-box").css({
							top : '20px',
							left : '20%'
						});

					});

					$(window).resize();

					$('a[data-modal-id]').click();

				});