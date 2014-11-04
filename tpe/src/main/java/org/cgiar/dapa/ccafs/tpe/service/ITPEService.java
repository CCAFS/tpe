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
package org.cgiar.dapa.ccafs.tpe.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.cgiar.dapa.ccafs.tpe.chart.Chart;
import org.cgiar.dapa.ccafs.tpe.entity.Category;
import org.cgiar.dapa.ccafs.tpe.entity.Climate;
import org.cgiar.dapa.ccafs.tpe.entity.Crop;
import org.cgiar.dapa.ccafs.tpe.entity.Cultivar;
import org.cgiar.dapa.ccafs.tpe.entity.Property;
import org.cgiar.dapa.ccafs.tpe.entity.Region;
import org.cgiar.dapa.ccafs.tpe.entity.Scenario;
import org.cgiar.dapa.ccafs.tpe.entity.Soil;
import org.cgiar.dapa.ccafs.tpe.entity.Station;
import org.cgiar.dapa.ccafs.tpe.entity.WindowSowing;
import org.cgiar.dapa.ccafs.tpe.projection.LatLng;

/**
 * This interface defines the TPE service methods
 * 
 * @author NMATOVU
 *
 */
public interface ITPEService {
	/**
	 * This retrieves all the crop records from the database
	 * 
	 * @return crops
	 */
	List<Crop> getAllCrops();

	/**
	 * Retrieves the crop record from the database with the specified id
	 * 
	 * @param cropId
	 * @return crop
	 */
	Crop getCropById(Integer cropId);

	/**
	 * Retrieves cultivars from the database for the specified crop id
	 * 
	 * @param cropId
	 *            the crop id to retrieve the cultivars
	 * @return crop cultivars
	 */
	List<Cultivar> getCultivarsByCrop(Integer cropId);

	/**
	 * Retrieves the number of years for the specified crop cultivar based on
	 * the simulation model
	 * 
	 * @param cultivarId
	 * @return years
	 */
	List<String> getYearsByCultivar(Integer cultivarId);

	/**
	 * Retrieves all the window sowing for teh specified crop cultivar id
	 * 
	 * @param cultivarId
	 *            the crop cultivar id to retrieve the sowing windows
	 * @return window sowing
	 */
	List<WindowSowing> getWindowSowingByCultivar(Integer cultivarId);

	/**
	 * Retrieves all the regions of the category country from the database
	 * 
	 * @return countries
	 */
	List<Region> getCountries();

	/**
	 * Retrieves all the subregions (COUNTRY, STATE, PROVINCE OR DISTRICT) for
	 * the specified region id (parent region) or country id.
	 * 
	 * @param countryId
	 *            country or parent id
	 * @return subregions
	 */
	List<Region> getSubregionsByCountry(Integer countryId);

	/**
	 * Gets the region by the specified id
	 * 
	 * @param regionId
	 * @return region
	 */
	Region getRegionById(Integer regionId);

	/**
	 * Retrieves all categories for the specified entity name.
	 * 
	 * @param entityName
	 * @return categories
	 */
	List<Category> getCategoriesByEntity(String entityName);

	/**
	 * Retrieves the category record from database by its id
	 * 
	 * @param categoryId
	 * @return category
	 */
	Category getCategoryById(Integer categoryId);

	/**
	 * Retrieves the weather stations located in the specified region.
	 * 
	 * @param regionId
	 *            region id used to query the stations
	 * @return stations
	 */
	List<Station> getStationsByRegion(Integer regionId);

	/**
	 * Retrieves the station by the specified id
	 * 
	 * @param stationId
	 *            id of the station to retrieve
	 * @return station
	 */
	Station getStationById(Integer stationId);

	/**
	 * Retrieves all the scenarios from the database
	 * 
	 * @return scenarios
	 */
	List<Scenario> getAllScenarios();

	/**
	 * Retrieves the scenario by its id
	 * 
	 * @param scenarioId
	 *            the id of the scenario to retrieve
	 * @return scenario
	 */
	Scenario getScenarioById(Integer scenarioId);

	/**
	 * Retrieves the climate records for the specified category from the
	 * database from the specified weather stations located from particular
	 * region(s) for the specified year.
	 * 
	 * @param stationIds
	 *            the ids of the stations from which to retrieve the climate
	 * 
	 * @param categoryId
	 *            the category id of the climate to retrieve
	 * 
	 * @param year
	 *            the year for the climate to retrieve
	 * @return climate
	 */
	List<Climate> getClimateByStations(List<Integer> stationIds,
			Integer categoryId, String year);

	/**
	 * Retrieves all the climate information for the specified category from the
	 * database from all the weather stations from the specified regions for the
	 * specified year.
	 * 
	 * @param regionIds
	 *            primary keys of the regions from which to retrieve the climate
	 *            data
	 * @param categoryId
	 *            the category id of the climate to retrieve
	 * @param year
	 *            the year for the climate to retrieve
	 * @return climate
	 */
	List<Climate> getClimateByRegions(List<Integer> regionIds,
			Integer categoryId, String year);

	// TODO To include order by.
	// TODO Set start row and max rows from the database
	/**
	 * Retrieves climate from to a particular date for a given category from the
	 * given region (country)
	 * 
	 * @param fromDate
	 *            from date
	 * @param toDate
	 *            to date
	 * @param regionId
	 *            country or region id
	 * @param categoryId
	 *            category id of the climate category
	 * @return climate
	 */
	List<Climate> getClimate(Date fromDate, Date toDate, Integer regionId,
			Integer categoryId);

	// TODO Add order by and max rows and start row
	/**
	 * Retrieves all stations associated with the specified climate category
	 * between a certain date range from a specified region (country)
	 * 
	 * @param fromDate
	 *            starting date
	 * @param toDate
	 *            to date
	 * @param categoryId
	 *            climate category id
	 * @param regionId
	 *            region (country) id
	 * @return stations
	 */
	List<Station> getStationsByClimate(Date fromDate, Date toDate,
			Integer categoryId, Integer regionId);

	/**
	 * Retrieve the soil texture by the primary key
	 * 
	 * @param soilId
	 *            the soil id
	 * @return soil texture
	 */
	Soil getSoilById(Integer soilId);

	/**
	 * Retrieves the soil distribution (latitude and longitude points) for a
	 * specified soil texture, region and property category.
	 * 
	 * @param soilId
	 *            soil texture id
	 * @param regionId
	 *            region or country id
	 * @param categoryId
	 *            property category id
	 * @return soil distribution
	 */
	Map<Integer, Map<Double, Double>> getSoilDistribution(Integer soilId,
			Integer regionId, Integer categoryId);

	// TODO To use soil code instead of soil id
	/**
	 * Retrieves the soil distribution (latitude and longitude points) for
	 * different specified soil textures, region and property category.
	 * 
	 * @param soilIds
	 *            soil texture ids
	 * @param regionId
	 *            region id
	 * @param categoryId
	 *            soil property category id
	 * @return soil distribution
	 */
	Map<Integer, Map<Double, Double>> getSoilDistribution(
			List<Integer> soilIds, Integer regionId, Integer categoryId);

	// Map<station,Map<lat.lng>>
	/**
	 * Retrieves stations and their corresponding location points from the
	 * specified region.
	 * 
	 * @param regionId
	 *            region id
	 * @return stations
	 */
	Map<Integer, Map<Double, Double>> getStationsPoints(Integer regionId);

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
	 * @param scenarioId
	 *            the scenario (rainfed or irrigated)
	 * @return Map of TPE regions
	 */
	Map<String, Map<Double, Double>> getTPERegions(Integer cultivarId,
			Integer regionId, Integer swindowId, String year, Integer scenarioId);

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
	 * @param scenarioId
	 *            the scenario id
	 * @return tpe soil map with corresponding regions and yield
	 */
	Map<String, Map<String, Double>> getTPESoil(Integer cultivarId,
			Integer regionId, Integer swindowId, String year, Integer scenarioId);

	/**
	 * Retrieves the weather stations from each specified regions (sub regions
	 * such as states)
	 * 
	 * @param regions
	 *            region ids
	 * @return stations per region
	 */
	Map<String, List<Station>> getStationPerRegion(List<Integer> regions);

	/**
	 * Retrieves the staions for each of the specified region
	 * 
	 * @param regions
	 *            region ids
	 * @return stations per region
	 */
	Map<String, List<LatLng>> getStationByRegion(List<Integer> regions);

	/**
	 * Retrieves the soil features for the specified country and soil property
	 * id
	 * 
	 * @param propertyId
	 *            the soil property id
	 * @param countryId
	 *            the country id
	 * @return soil features
	 */
	Map<String, Object> getSoilFeaturesByCountry(Integer propertyId,
			Integer countryId);

	/**
	 * Retrieves the soil features for the specified soil property category id
	 * and sub region.
	 * 
	 * @param propertyId
	 *            the soil property category id
	 * @param subregions
	 *            the list of sub region ids
	 * @return soil features
	 */
	Map<String, Object> getSoilFeaturesByRegions(Integer propertyId,
			List<Integer> subregions);

	/**
	 * Retrieves the soil features for the specified region id and the soil
	 * property category id.
	 * 
	 * @param propertyId
	 * @param subregion
	 * @return soil GeoJSON features
	 */
	Map<String, Object> getSoilFeaturesByRegion(Integer propertyId,
			Integer subregion);

	/**
	 * Retrieves the climate data for the specified country, category and year,
	 * that will be returned as GeoJSON format by the action
	 * 
	 * @param categoryId
	 *            climate category id
	 * @param countryId
	 *            the country id
	 * @param year
	 *            the year
	 * @return climate GeoJSON features
	 */
	Map<String, Object> getClimateGeoJSON(Integer categoryId,
			Integer countryId, String year);

	/**
	 * Retrieves the TPE records for the specified variables. The result
	 * Map<String,Object> will be returned as GeoJSON by the actions defined in
	 * struts.xml
	 * 
	 * @param cultivarId
	 *            the crop cultivar id
	 * @param countryId
	 *            the country id
	 * @param swindowId
	 *            the sowing window id
	 * @param year
	 *            the year
	 * @return TPE GeoJSON features
	 */
	Map<String, Object> getTPEGeoJSON(Integer cultivarId, Integer countryId,
			Integer swindowId, String year);

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
	 * @param scenarioId
	 *            the selected scenario id
	 * @return TPE GeoJSON
	 */
	Map<String, Object> getTPEGeoJSON(Integer cultivarId, Integer countryId,
			Integer swindowId, String year, Integer scenarioId);

	/**
	 * Retrieves the chart series for a selected (clicked) sub region from the
	 * Google Map. The retrieves data will be returned (or converted to JSON) as
	 * JSON data by the action in the struts.xml.
	 * 
	 * @param subregionId
	 *            the id of the clicked (selected) sub region (region)
	 * @param categoryId
	 *            the id of the selected category
	 * @param scenarioId
	 *            the id of the selected scenario
	 * @param cultivarId
	 *            the id of the selected crop cultivar
	 * @param year
	 *            the selected year
	 * @param swindow
	 *            the id of the selected window sowing
	 * @return Chart data
	 */
	List<Chart> getTPEColumnSeries(Integer subregionId, Integer categoryId,
			Integer scenarioId, Integer cultivarId, String year, Integer swindow);

	/**
	 * Retrieves all the soil textures from the database.
	 * 
	 * @return soil textures
	 */
	List<Soil> getSoilTextures();

	/**
	 * Retrieves all the outputs from the database
	 * 
	 * @return outputs
	 */
	List<Category> getOutputs();

	/**
	 * Retrieves all the properties from the database
	 * 
	 * @return properties
	 */
	List<Property> getAllProperties();

	/**
	 * Retrieves a property for the specified id
	 * 
	 * @param propertyId
	 *            the id of the property to retrieve
	 * @return property
	 */
	Property getPropertyById(Integer propertyId);

	/**
	 * Retrieves the properties for the specified category
	 * 
	 * @param categoryId
	 *            the id of the category
	 * @return properties
	 */
	List<Property> getPropertiesByCategory(Integer categoryId);

	/**
	 * LIst all the years linked to the specified country id (or its sub regions
	 * and stations) from the climate table.
	 * 
	 * @param countryId
	 *            country id
	 * @return years
	 */
	List<String> getClimateYears(Integer countryId);

	/**
	 * Retrieves all the properties of soil category
	 * 
	 * @return properties
	 */
	List<Property> getSoilProperties();

	/**
	 * Retrieves all the properties of climate category from the database
	 * 
	 * @return properties
	 */
	List<Property> getClimateProperties();

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

}
