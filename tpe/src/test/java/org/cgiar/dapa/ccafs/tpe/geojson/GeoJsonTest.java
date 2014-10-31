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
package org.cgiar.dapa.ccafs.tpe.geojson;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import org.apache.log4j.Logger;
import org.cgiar.dapa.ccafs.tpe.dao.BaseTest;
import org.json.simple.JSONValue;

/**
 * Tests for GeoJson methods
 * 
 * @author NMATOVU
 *
 */
public class GeoJsonTest extends BaseTest {

	private Logger log = Logger.getLogger(this.getClass());
	static final Map<String, Object> props = new LinkedHashMap<String, Object>();
	static {
		final Map<String, Object> m = props;
		m.put("name", "Colombia");
		m.put("yield", 2000.44);
	};

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void testCreatGeoJson() {
//		JSONObject object = new JSONObject();
//		JSONParser parser = new JSONParser();
		Map obj = new LinkedHashMap();
		obj.put("name", "abia");
		obj.put("point", new Integer(100));
		obj.put("yield", new Double(1000.21));

		String jsonText = JSONValue.toJSONString(obj);
		log.info(jsonText);
	}

	public void testGetGeoJson() {
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

		String geoJSON = JSONValue.toJSONString(featureCollection);
		log.info(geoJSON);
	}
}
