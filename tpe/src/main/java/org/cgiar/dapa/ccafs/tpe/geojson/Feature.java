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

/**
 * Represents the GeoJSON Feature Collection for the Google Map API
 * 
 * @author NMATOVU
 *
 */
public class Feature extends BaseGeo {

	private static final long serialVersionUID = -5505282253291793786L;
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
	private Property properties;

	// private Map<String, Property> properties;

	public Feature() {
		super();
		geometry = new Geometry();
		properties = new Property();
	}

	public Feature(String type, Geometry geometry, Property property) {
		super();
		this.type = type;
		this.geometry = geometry;
		this.properties = property;
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

	public Property getProperty() {
		return properties;
	}

	public void setProperty(Property property) {
		this.properties = property;
	}

}
