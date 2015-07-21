$(document).ready(
		function() {

			// User Id/Group id
			var userid = "2787037@N20";
			$
					.getJSON(
							"http://api.flickr.com/services/feeds/groups_pool.gne?id="
									+ userid
									+ "&lang=en-us&format=json&jsoncallback=?",
							function(data) {
								$.each(data.items, function(index, item) {
									// Retrieve only six photos from the
									// specified group
									if (index == 6)
										return false;
									$(
											'<div class="flickr-img"><a target="_blank" href="'
													+ item.link
													+ '"><img src="'
													+ item.media.m
													+ '"</img></a></div>')
											.appendTo('.flickr-stream');

								});
							});
		});