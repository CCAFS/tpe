/**
 * Platform tags
 */
$(document).ready(
		function() {
			$.ajax({
				type : 'GET',
				url : 'json/tagsJson.action',
				data : {
				// regions : _regions,
				},
				dataType : 'json',
				// processData : false,
				// contentType : false,
				// async : true,
				success : function(data) {
					// console.log(data.tags);
					$.each(data.tags, function(index, tag) {
						$(
								'<div class="tag"><a style="font-size: '
										+ tag.weight + 'px" href="#">'
										+ tag.name + '</a></div>').appendTo(
								'#tags');
					});

					// When the tag is clicked, reload on the left pane. Don't
					// refresh
					// the right panel (tags, flickr, news)
					$(".tag a").click(
							function(e) {
								// console.log('TEXT: ' + $(this).text());
								// console.log('HTML: ' + $(this).html());
								$('.pane_left').load(
										"posts.action?tag=" + $(this).text());
								// Add the tag name as the title.
								$('#tag-title').html($(this).text());
								e.preventDefault();
							});

				},
				error : function(xhr, desc, err) {
					console.log("error");

				}
			});

		});