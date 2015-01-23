$(document)
		.ready(
				function() {
					$
							.getJSON(
									"http://api.flickr.com/services/rest/tpe_platform?method=flickr.people.getPublicPhotos&user_id=tpe_platform&api_key=0f7aeb59277bbd6eeb36ca39c4ce0252",

									// http://api.flickr.com/services/rest/?method=flickr.people.getPublicPhotos&api_key=your_api_key&user_id=tpe_platform
									function(data) {
										$
												.each(
														data.items,
														function(i, item) {
															if (i == 6)
																return false;
															$(
																	'<div class="tpe_sec_img"><a href="'
																			+ item.link
																			+ '"><img src="'
																			+ item.media.m
																			+ '"</img></a></div>')
																	.appendTo(
																			'.flickr-stream');

														});
									});
				});