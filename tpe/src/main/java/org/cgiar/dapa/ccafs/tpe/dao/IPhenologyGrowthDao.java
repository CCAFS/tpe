/*****************************************************************
 * This file is part of CCAFS Target Population Environments Identification Platform.
 * CCAFS TPE is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * at your option) any later version.
 * CCAFS TPE Identification Platform is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with CCAFS TPE Identification Platform. If not, see <http://www.gnu.org/licenses/>.
 *****************************************************************/
package org.cgiar.dapa.ccafs.tpe.dao;

import java.util.List;
import java.util.Map;

import org.cgiar.dapa.ccafs.tpe.chart.BoxPlot;
import org.cgiar.dapa.ccafs.tpe.chart.Chart;
import org.cgiar.dapa.ccafs.tpe.entity.PhenologyGrowth;

/**
 * This interface defines the crop phenology and growth dao methods and extends
 * the base generic dao interface
 * 
 * @author NMATOVU
 *
 */
public interface IPhenologyGrowthDao extends IGenericDao<PhenologyGrowth, Long> {

	// TODO To use the region ISO code
	/**
	 * Retrieves the map of TPE regions (Map<RegionISO,Map<Latitude,Longitude>>)
	 * for the specified crop cultivar, region (country) and sowing window in
	 * the given year
	 * 
	 * @param cultivarId
	 *            cultivar id
	 * @param regionId
	 *            country id
	 * @param swindowId
	 *            sowing window id
	 * @param year
	 *            year
	 * @return Map of TPE regions
	 */
	Map<String, Map<Double, Double>> getTPERegions(Integer cultivarId,
			Integer regionId, Integer swindowId, String year);

	/**
	 * Retrieves the map TPE regions (Map<RegionISO,Map<Latitude,Longitude>>)
	 * for the specified crop cultivar, region (country), sowing window, and
	 * scenario in the given year
	 * 
	 * @param cultivarId
	 *            the crop cultivar id
	 * @param regionId
	 *            country id
	 * @param swindowId
	 *            the sowing window id
	 * @param year
	 *            the year
	 * @param scenario
	 *            the selected scenario (rainfed or irrigated)
	 * @return Map of TPE regions
	 */
	Map<String, Map<Double, Double>> getTPERegions(Integer cultivarId,
			Integer regionId, Integer swindowId, String year, String scenario);

	/**
	 * Retrieves the tpe soil textures with thier corresponding regions and crop
	 * yield from the specified country, crop cultivar, sowing window in the
	 * given year.
	 * 
	 * @param cultivarId
	 *            the crop cultivar id
	 * @param regionId
	 *            the country or region id
	 * @param swindowId
	 *            the sowing window
	 * @param year
	 *            the year
	 * @return tpe soil and corresponding regions and yield
	 */
	Map<String, Map<String, Double>> getTPESoil(Integer cultivarId,
			Integer regionId, Integer swindowId, String year);

	/**
	 * Retrieves the tpe soil textures with thier corresponding regions and crop
	 * yield from the specified country, crop cultivar, sowing window in the
	 * given year.
	 * 
	 * @param cultivarId
	 *            the crop cultivar id
	 * @param regionId
	 *            the region or country id
	 * @param swindowId
	 *            the sowing window id
	 * @param year
	 *            the year
	 * @param scenario
	 *            the selected scenario
	 * @return tpe soil map with corresponding regions and yield
	 */
	Map<String, Map<String, Double>> getTPESoil(Integer cultivarId,
			Integer regionId, Integer swindowId, String year, String scenario);

	/**
	 * Retrieves the TPE records for the specified variables. The result
	 * Map<String,Object> will be returned as GeoJSON by the actions defined in
	 * struts.xml
	 * 
	 * @param cultivarId
	 *            the if of the selected crop cultivar
	 * @param countryId
	 *            the id of the selected country
	 * @param swindowId
	 *            the id of the selected window sowing
	 * @param year
	 *            the selected year
	 * @param scenario
	 *            the selected scenario
	 * @return TPE GeoJSON
	 */
	Map<String, Object> getTPEGeoJSON(Integer cultivarId, Integer countryId,
			Integer swindowId, String year, String scenario);

	/**
	 * Retrieves the chart series for a selected (clicked) sub region from the
	 * Google Map. The retrieves data will be returned (or converted to JSON) as
	 * JSON data by the action in the struts.xml.
	 * 
	 * @param subregionId
	 *            the id of the clicked (selected) sub region (region)
	 * @param categoryId
	 *            the id of the selected category
	 * @param scenario
	 *            the selected scenario
	 * @param cultivarId
	 *            the id of the selected crop cultivar
	 * @param year
	 *            the selected year
	 * @param swindow
	 *            the id of the selected window sowing
	 * @return Chart data
	 */
	List<Chart> getTPEColumnSeries(Integer subregionId, Integer categoryId,
			String scenario, Integer cultivarId, String year, Integer swindow);

	/**
	 * Retrieves the years based on the selected country and crop cultivar
	 * 
	 * @param countryId
	 *            the id of the selected country
	 * @param cultivarId
	 *            the is of the selected cultivar
	 * @return years
	 */
	List<String> getTPEYears(Integer countryId, Integer cultivarId);

	/**
	 * Gets the TPE box plot data for the selected country and crop cultivar.
	 * 
	 * @param country
	 *            the selected country id
	 * @param cultivar
	 *            the selected crop cultivar id
	 * @return data
	 */
	Map<String, Object> getTPEBox(Integer country, Integer cultivar);

	// Map<String, List<Object>> getStressCategories(List<String> stressSeries);
	/**
	 * Gets the categories for the charts which is the DAE (Day After Emergency)
	 * 
	 * @param stressSeries
	 *            the series names
	 * @param cultivarId
	 *            the selected cultivar id
	 * @param countryId
	 *            the selected country id
	 * @return categories
	 */
	List<Object> getStressCategories(List<String> stressSeries,
			Integer cultivarId, Integer countryId);

	/**
	 * Gets the categories for the charts which is the DAE (Day After Emergency)
	 * 
	 * @param cultivarId
	 *            cultivar id
	 * @param countryId
	 *            country id
	 * @return lai series data
	 */
	Map<String, Object> getSeriesData(Integer cultivarId, Integer countryId);
}
