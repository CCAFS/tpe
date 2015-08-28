/**
 * This action retrieves the Geo Json data and then calls the initializeMap()
 * function.
 */
function loadJson(action, map) { 
	$.ajax({
		type : "GET",
		async : false,// thats the trick
		url : action,
		data : $('#tpe_index').serialize(),
		dataType : "json",
		success : function(jsonResult) {
 
			// If TPE or Stability option was selected. Then assign the
			// associated data to the variables
			if ((map == OUTPUT_TPE) || (map == OUTPUT_STABILITY)) {
				boxJSON = jsonResult.boxJson; 
				categoriesJSON = jsonResult.categoriesJson;
				var seriesDataMap = jsonResult.seriesJson;
				// var hfeSeries, lfeSeries, feSeries; 
				if (seriesDataMap != null)
					$.each(seriesDataMap, function(envKey, listOfSeriesMap) {
						if (envKey == ENV_HFE) {
							hfeSeries = listOfSeriesMap; 
						} else if (envKey == ENV_LFE) {
							lfeSeries = listOfSeriesMap;
						} else if (envKey == ENV_FE) {
							feSeries = listOfSeriesMap;
						}
					});

			} else if (map == OUTPUT_CLIMATE) {

				categoriesJSON = null;
				hfeSeries = null;
				lfeSeries = null;
				feSeries = null;
				boxJSON = null;

			} else if (map == OUTPUT_SOIL) { 
				seriesJSON = jsonResult.seriesJson;
				categoriesJSON = jsonResult.categoriesJson; 

				hfeSeries = null;
				lfeSeries = null;
				feSeries = null;
				boxJSON = null;
			}
			initializeMap(jsonResult);
			// $('.graphics-info').trigger('click');
		}
	});

}