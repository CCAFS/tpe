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

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Represents the GeoJSON Feature Collection for the Google Map API
 * 
 * @author NMATOVU
 *
 */
public class SFeature extends BaseGeo {

	private static final long serialVersionUID = 8004559534042379064L;
	/**
	 * The type of the GeoJson feature
	 */
	private String type = GEOJSON_VALUE_FEATURE;
	/**
	 * The geometry of the GeoJson feature
	 */
	private Geometry geometry;
	/**
	 * The properties of the GeoJson feature
	 */
	private Map<String, Object> properties = new LinkedHashMap<String, Object>();

	// private Map<String, Property> properties;

	public SFeature() {
		super();
		geometry = new Geometry();
		properties = new LinkedHashMap<String, Object>();
	}

	public SFeature(String type, Geometry geometry,
			Map<String, Object> properties) {
		super();
		this.type = type;
		this.geometry = geometry;
		this.properties = properties;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

}
