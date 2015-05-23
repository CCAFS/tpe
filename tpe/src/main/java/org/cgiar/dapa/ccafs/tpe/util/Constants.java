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
	public static final String GEOMETRY_TYPE_POINT = "Point";

	/**
	 * A constant that represents the feature type in the Google Map API
	 */
	public static final String FEATURES_TYPE = "Feature";

	/**
	 * A constant that represents the geojson POLYGON type
	 */
	public static final String GEOMETRY_TYPE_POLYGON = "Polygon";
	/**
	 * A constant that represents the feature collection
	 */
	public static final String GEOJSON_VALUE_FEATURE_COLLECTION = "FeatureCollection";
	public static final String GEOJSON_KEY_GEOMETRY = "geometry";
	public static final String GEOJSON_KEY_PROPERTIES = "properties";
	public static final String GEOJSON_KEY_COORDINATES = "coordinates";
	public static final String GEOJSON_KEY_TYPE = "type";
	public static final String GEOJSON_KEY_FEATURES = "features";

	/**
	 * The maker symbol key for the highcharts spline chart
	 */
	public static final String MARKER_SYMBOL = "symbol";
	/**
	 * The constant for the marker symbol square
	 */
	public static final String MARKER_SYMBOL_SQUARE = "square";
	/**
	 * The constant for the marker symbol diamond
	 */
	public static final String MARKER_SYMBOL_DIAMOND = "diamond";
	/**
	 * TPE Output
	 */
	public static final String OUTPUT_TPE = "TPE";
	public static final String OUTPUT_CLIMATE = "CLIMATE";
	public static final String OUTPUT_SOIL = "SOIL";

	public static final String ENTITY_CLASS_OUTPUT = "Output";
	public static final String CATEGORY_DESCRIPTION_MAP = "Map";
	public static final String STATION_NAME = "stationName";
	public static final String STATION_NUMBER = "stationNumber";
	public static final String REGION_NAME = "regionName";
	public static final String COUNTRY_NAME = "countryName";
	public static final String SOIL_NAME = "soilName";
	public static final String PROPERTY_AUTHOR = "author";
	public static final String PROPERTY_YEAR = "year";
	public static final String PROPERTY_SOURCE = "source";
	public static final String STATION_COORDINATES = "stationCoordinates";
	/**
	 * The station property id constant
	 */
	public static final String STATION_ID = "stationId";
	/**
	 * The soil color property constant
	 */
	public static final String SOIL_COLOR = "soilColor";

	public static final String SOIL_PROPERTY_ID = "soilPropertyId";
	/**
	 * The property id of the Google Map feature or point
	 */
	public static final String FEATURE_ID = "id";
	/**
	 * The name property of the feature on the Google Map
	 */
	public static final String FEATURE_NAME = "name";
	/**
	 * The property color constant for the Google Map feature
	 */
	public static final String FEATURE_COLOR = "color";
	/**
	 * The property color value for the weather station
	 */
	public static final Object STATION_COLOR_GREEN = "station_green";
	public static final Object STATION_COLOR_BLACK = "station_black";
	public static final String PROPERTY_ID = "property";
	/**
	 * The constant for the property name
	 */
	public static final String PROPERTY_NAME = "propertyName";
	/**
	 * The constant for the property value
	 */
	public static final String PROPERTY_VALUE = "propertyValue";
	public static final String SOIL_PROPERTY_NAME = "soilPropertyName";
	public static final String SOIL_PROPERTY_VALUE = "soilPropertyValue";
	// private static final Object FEATURE_TYPE_SOIL = "SOIL";
	public static final String FEATURE_TYPE = "featureType";
	/**
	 * The constant for the map option for TPE
	 */
	public static final String JSON_MAP_TPE = "tpe";
	public static final String JSON_MAP_SOIL = "soil";
	public static final String JSON_MAP_CLIMATE = "climate";
	public static final String JSON_MAP_STABILITY = "stability";
	public static final String JSON_MAP_AREA= "area";
	public static final String JSON_REGION = "region";
	public static final String JSON_MUNICIPIOS = "municipios";
	public static final String JSON_BOUNDARY = "boundary";
	public static final String JSON_STATES = "states";
}
