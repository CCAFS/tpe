/**
 * This action retrieves the Geo Json data and then calls the initializeMap()
 * function.
 */
function loadJson(action, map) {
//	console.log(action);
//	console.log(map);
	$.ajax({
		type : "GET",
		async : false,// thats the trick
		url : action,
		data : $('#tpe_index').serialize(),
		dataType : "json",
		success : function(jsonResult) {

			// console.log(dataJson.areaGeoJson);
			// If TPE or Stability option was selected. Then assign the
			// associated data to the variables
			if ((map == OUTPUT_TPE) || (map == OUTPUT_STABILITY)) {
				boxJSON = jsonResult.boxJson;
				// console.log(dataJson.probabilities);
				categoriesJSON = jsonResult.categoriesJson;
				var seriesDataMap = jsonResult.seriesJson;
				// var hfeSeries, lfeSeries, feSeries;
				// console.log(seriesDataMap);
				if (seriesDataMap != null)
					$.each(seriesDataMap, function(envKey, listOfSeriesMap) {
						if (envKey == ENV_HFE) {
							hfeSeries = listOfSeriesMap;
							// console.log(listOfSeriesMap);
						} else if (envKey == ENV_LFE) {
							lfeSeries = listOfSeriesMap;
						} else if (envKey == ENV_FE) {
							feSeries = listOfSeriesMap;
						}
					});

				// initializeMap(jsonResult);

			} else if (map == OUTPUT_CLIMATE) {
				// console.log(dataJson.probabilities);
				// climateSeriesJSON = jsonResult.seriesJson;

				//seriesJSON = jsonResult.seriesJson;

				categoriesJSON = null;
				hfeSeries = null;
				lfeSeries = null;
				feSeries = null;
				boxJSON = null;

				// initializeMap(jsonResult.geoJson);
				// var seriesDataMap = dataJson.seriesData;
				// console.log('Loading climate JSON data');
			} else if (map == OUTPUT_SOIL) {
				// soilJson = dataJson.seriesJson;
				seriesJSON = jsonResult.seriesJson;
				categoriesJSON = jsonResult.categoriesJson;
				// initializeMap(jsonResult.geoJson);

				hfeSeries = null;
				lfeSeries = null;
				feSeries = null;
				boxJSON = null;
			}
			initializeMap(jsonResult);
			// ////////////////////initializeMap(jsonResult.geoJson);
			// $('.graphics-info').trigger('click');
		}
	});

}