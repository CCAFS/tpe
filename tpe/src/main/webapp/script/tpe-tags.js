/**
 * Platform tags
 */
$(document).ready(
		function() {
			$.ajax({
				type : 'GET',
				url : 'tags.action',
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
										+ tag.tagWeight
										+ 'px" target="_blank" href="'
										+ tag.tagUrl + '">' + tag.tagName
										+ '</a></div>').appendTo('#tags');
					});

				},
				error : function(xhr, desc, err) {
					console.log("error");

				}
			});

		});