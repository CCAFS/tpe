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

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import org.cgiar.dapa.ccafs.tpe.geojson.SFeature;
import org.cgiar.dapa.ccafs.tpe.geojson.Geometry;
import org.cgiar.dapa.ccafs.tpe.geojson.Property;
import org.cgiar.dapa.ccafs.tpe.geojson.Feature;

/**
 * This is a test utility class
 * 
 * @author NMATOVU
 *
 */
public class TestUtil implements Constants {

	static final Map<String, Object> props = new LinkedHashMap<String, Object>();
	static {
		final Map<String, Object> m = props;
		m.put("name", "Colombia");
		m.put("yield", 2000.44);
	};

	/**
	 * Generate sample feature collection
	 * 
	 * @return
	 */
	public static Map<String, Object> getFeatureCollection() {
		Geometry geom = new Geometry(new LinkedList<Double>(Arrays.asList(
				10.2255, 24.77)));

		// Property property = new Property("yield", 5433.29);

		Map<String, Object> properties = props;
		SFeature feature = new SFeature(GEOJSON_VALUE_FEATURE, geom, properties);
		// The feature collection
		Map<String, Object> featureCollection = new LinkedHashMap<String, Object>();
		// The feature
		featureCollection.put(GEOJSON_KEY_TYPE,
				GEOJSON_VALUE_FEATURE_COLLECTION);
		featureCollection.put(GEOJSON_KEY_FEATURES, new LinkedList<SFeature>(
				Arrays.asList(feature, feature, feature)));

		// Geometry geometry = new Geometry(new ArrayList<Double>(Arrays.asList(
		// 10.22, 24.77)));

		return featureCollection;
	}

	public static Map<String, Object> getFeatures() {
		Geometry geom = new Geometry(new LinkedList<Double>(Arrays.asList(
				10.2255, 24.77)));

		Property property = new Property("palmira", 22.33, "palmira", "Cauca",
				1, "2014");

		Feature feature = new Feature(GEOJSON_VALUE_FEATURE, geom, property);
		// The feature collection
		Map<String, Object> featureCollection = new LinkedHashMap<String, Object>();
		// The feature
		featureCollection.put(GEOJSON_KEY_TYPE,
				GEOJSON_VALUE_FEATURE_COLLECTION);
		featureCollection.put(GEOJSON_KEY_FEATURES, new LinkedList<Feature>(
				Arrays.asList(feature, feature, feature)));

		// Geometry geometry = new Geometry(new ArrayList<Double>(Arrays.asList(
		// 10.22, 24.77)));

		return featureCollection;
	}

}
