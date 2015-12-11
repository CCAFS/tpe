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