<%@ include file="/common/taglibs.jsp"%>
<head>
<script type='text/javascript'>
	$(document).ready(function() {

		//var href_regions = "subregions.action";
		//	var selectedCountry = $("select#select_country").val();
		//initializeMap();
		//When the sub regions change. Reload the Google MapF
		$("select#select_regions").change(function() {
			//The currently selected 
			//$("#params_regions").load(href_regions, {
			// Pass the parameters to the action to query sub regions
			//selectedCountry : selectedCountry
			//	}, function() {
			// Hide and slide the params_region div
			//	$(this).hide().slideDown(1000);
			/* $(this).slideUp(0).slideDown(1000); */
			//	});
			//Load or initialize the Google Map here
			initializeGoogleMap();
		});

	});
</script>
</head>

<s:select name="selectedRegions" listKey="id" listValue="name"
	id="select_regions" list="regions" value="preselectedRegions"
	cssStyle="width:100%;" multiple="true" size="4" required="true" />