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

import java.util.List;
import java.util.Map;

import org.cgiar.dapa.ccafs.tpe.chart.Chart;
import org.cgiar.dapa.ccafs.tpe.chart.Probability;
import org.cgiar.dapa.ccafs.tpe.entity.Category;
import org.cgiar.dapa.ccafs.tpe.entity.Climate;
import org.cgiar.dapa.ccafs.tpe.entity.Crop;
import org.cgiar.dapa.ccafs.tpe.entity.Cultivar;
import org.cgiar.dapa.ccafs.tpe.entity.PhenologyGrowth;
import org.cgiar.dapa.ccafs.tpe.entity.Region;
import org.cgiar.dapa.ccafs.tpe.entity.Soil;
import org.cgiar.dapa.ccafs.tpe.entity.Station;
import org.cgiar.dapa.ccafs.tpe.entity.Tag;
import org.cgiar.dapa.ccafs.tpe.exception.PlatformException;
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
	 * @param scenario
	 *            the scenario (rainfed or irrigated)
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
	 *            the scenario
	 * @return tpe soil map with corresponding regions and yield
	 */
	Map<String, Map<String, Double>> getTPESoil(Integer cultivarId,
			Integer regionId, Integer swindowId, String year, String scenario);

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
			Integer countryId, Boolean continent);

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
	 * Retrieves the climate data for the specified country that will be
	 * returned as GeoJSON format by the action
	 * 
	 * @param countryId
	 *            the country id
	 * @param indicators
	 *            the climate indicators
	 * @param regionCategory
	 *            category of the region
	 * 
	 * @return climate GeoJSON features
	 */
	Map<String, Object> getClimateGeoJSON(Integer countryId,
			List<Integer> indicators, Boolean continent);

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
	 * LIst all the years linked to the specified country id (or its sub regions
	 * and stations) from the climate table.
	 * 
	 * @param countryId
	 *            country id
	 * @return years
	 */
	List<String> getClimateYears(Integer countryId);

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
	 * Retrieves the station Geo Json data from the database for the specified
	 * country id
	 * 
	 * @param countryId
	 *            the id of the country
	 * @return stations
	 */
	Map<String, Object> getStationGeoJson(Integer countryId);

	/**
	 * Retrieves the soil properties data map for the specified country and soil
	 * property ids. The retrieved query records will later be returned as
	 * GroJson by the struts.xml actions
	 * 
	 * @param propertyIds
	 *            the list of soil property ids
	 * @param countryId
	 *            the id of the country
	 * @return soil GeoJson
	 */
	Map<String, Object> getSoilGeoJson(List<Integer> propertyIds,
			Integer countryId, Boolean continent);

	/**
	 * Gets the sowing dates
	 * 
	 * @param country
	 *            the country id
	 * @return sowing dates
	 */
	List<String> getEnvSowingDates(Integer country);

	/**
	 * Gets the environment soil probabilities
	 * 
	 * @param country
	 *            country id
	 * @return probabilities
	 */
	Map<String, List<Probability>> getEnvSoilProbabilities(Integer country);

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

	/**
	 * Retrieves the crop cultivar from the database for the specified id
	 * 
	 * @param cultivarId
	 *            cultivar id
	 * @return cultivar
	 */
	Cultivar getCultivar(Integer cultivarId);

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

	/**
	 * Retrieves the climate series data from the database
	 * 
	 * @param country
	 *            the selected country id
	 * @param continent
	 *            the boolean value if the region is a continent.
	 * @return climate series
	 */
	Map<String, Object> getClimateSeries(Integer country, boolean continent);

	/**
	 * Retrieves all the platform tags from the database
	 * 
	 * @return tags
	 */
	List<Tag> getAllTags();

	/**
	 * List climate records for the specified stations.
	 * 
	 * @param stationIds
	 *            list of station ids
	 * @return climate
	 */
	List<Climate> getClimateByStations(List<Integer> stationIds);

	/**
	 * Lists climate records from the database for the specified regions
	 * 
	 * @param regionIds
	 *            list of region ids
	 * @return climate
	 */
	List<Climate> getClimateByRegions(List<Integer> regionIds);

	/**
	 * Retrieves countries and continents from the regions table.
	 * 
	 * @return countries and continents
	 */
	List<Region> getCountriesAndContinents();

	List<Object[]> getClimateData(Integer countryId);

	List<PhenologyGrowth> getTestSeries(Integer countryId, Integer cultivarId);

	/**
	 * Retrieves all the tags from the database with the specified enabled
	 * value(true or false).;
	 * 
	 * @param enabled
	 *            the boolean value; true if the tag is enabled and false if
	 *            not.
	 * @return tags
	 */
	List<Tag> getTags(boolean enabled);

	/**
	 * Adds a new tag with its associated posts (links) or updates an existing
	 * tag or its post links.
	 * 
	 * @param name
	 *            the name of the tag to add or update
	 * @param url
	 *            the url link of the tag to add or update.
	 * @param weight
	 *            the weight of the tag to add or update (The weight will be
	 *            used in the html font size).
	 * @param enabled
	 *            boolean value, true for enabled and false for disabled one.
	 */
	void addTag(String name, String url, Integer weight, Boolean enabled)
			throws PlatformException;

	/**
	 * Retrieves the tag from the database by the specified name.
	 * 
	 * @param name
	 *            the name of the tag to look for
	 * @return Tag
	 */
	Tag findTagByName(String name);

	/**
	 * Saves a new tag into the database
	 * 
	 * @param tag
	 *            the new tag record to save into the database
	 * @throws PlatformException
	 */
	void addTag(Tag tag) throws PlatformException;

}
