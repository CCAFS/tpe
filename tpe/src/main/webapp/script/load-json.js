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

			// console.log(dataJson.areaGeoJson);
			// If TPE or Stability option was selected. Then assign the
			// associated data to the variables
			if ((map == OUTPUT_TPE) || (map == OUTPUT_STABILITY)) {
				boxJSON = jsonResult.boxplotData;
				// console.log(dataJson.probabilities);
				categoriesJSON = jsonResult.categories;
				var seriesDataMap = jsonResult.seriesData;
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
				
//				initializeMap(jsonResult);

			} else if (map == OUTPUT_CLIMATE) {
				// console.log(dataJson.probabilities);
				categoriesJSON = jsonResult.categories;
				climateSeriesJSON = jsonResult.seriesData;
//				initializeMap(jsonResult.geoJson);
				// var seriesDataMap = dataJson.seriesData;
				//console.log('Loading climate JSON data');
			} else if (map == OUTPUT_SOIL) {
				soilJson = dataJson.dataJson;
				categoriesJSON = jsonResult.categories;
//				initializeMap(jsonResult.geoJson);
			}
			else if (map == OUTPUT_AREA) {
//				soilJson = dataJson.dataJson;
//				categoriesJSON = jsonResult.categories;
				
			}
			// else if (map == 'AREA') {
			//
			// } else {
			//
			// }
			initializeMap(jsonResult);
			//////////////////////initializeMap(jsonResult.geoJson);
			// $('.graphics-info').trigger('click');
		}
	});

}