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
public class Point extends BaseGeo {

	private static final long serialVersionUID = 4162618112995464167L;
	/**
	 * The type of the GeoJson feature
	 */
	private String type = FEATURES_TYPE;
	/**
	 * The geometry of the GeoJson feature
	 */
	private GeometryPoint geometry;

	/**
	 * The properties of the GeoJson feature
	 */
	private FeatureProperty properties;

	// private Map<String, Property> properties;
	// private Map<String, Object> properties;

	public Point() {
		super();
		geometry = new GeometryPoint();
		setProperties(new FeatureProperty());
	}

	public Point(String type, GeometryPoint geometry, FeatureProperty properties) {
		super();
		this.type = type;
		this.geometry = geometry;
		this.setProperties(properties);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public GeometryPoint getGeometry() {
		return geometry;
	}

	public void setGeometry(GeometryPoint geometry) {
		this.geometry = geometry;
	}

	public FeatureProperty getProperties() {
		return properties;
	}

	public void setProperties(FeatureProperty properties) {
		this.properties = properties;
	}

}
