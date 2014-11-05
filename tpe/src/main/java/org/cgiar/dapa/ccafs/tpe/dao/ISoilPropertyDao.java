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

import org.cgiar.dapa.ccafs.tpe.entity.SoilProperty;

/**
 * The interface that defines the soil property DAO methods
 * 
 * @author NMATOVU
 *
 */
public interface ISoilPropertyDao extends IGenericDao<SoilProperty, Long> {
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
	 * and sub regions.
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
	 * @return soil features
	 */
	Map<String, Object> getSoilFeaturesByRegion(Integer propertyId,
			Integer subregion);

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
			Integer countryId);
}
