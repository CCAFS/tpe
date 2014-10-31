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
package org.cgiar.dapa.ccafs.tpe.util;

import org.cgiar.dapa.ccafs.tpe.entity.Region;

/**
 * This interface defines the constants used by class in the application
 * 
 * @author NMATOVU
 *
 */
public interface Constants {
	/**
	 * Defines the region class name
	 */
	public static final String ENTITY_REGION = Region.class.getSimpleName();
	/**
	 * A constant that represents the feature type in the Google Map API
	 */
	public static final String GEOJSON_VALUE_FEATURE = "Feature";
	/**
	 * A constant that represents the geojson point type
	 */
	public static final String GEOJSON_VALUE_POINT = "Point";
	/**
	 * A constant that represents the feature collection
	 */
	public static final String GEOJSON_VALUE_FEATURE_COLLECTION = "FeatureCollection";
	public static final String GEOJSON_KEY_GEOMETRY = "geometry";
	public static final String GEOJSON_KEY_PROPERTIES = "properties";
	public static final String GEOJSON_KEY_COORDINATES = "coordinates";
	public static final String GEOJSON_KEY_TYPE = "type";
	public static final String GEOJSON_KEY_FEATURES = "features";
}
