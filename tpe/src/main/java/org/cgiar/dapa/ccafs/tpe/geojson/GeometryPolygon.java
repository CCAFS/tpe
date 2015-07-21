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

import java.util.LinkedList;
import java.util.List;

/**
 * Represents the GeoJSON feature for the Google Map API
 * 
 * @author NMATOVU
 * 
 */
public class GeometryPolygon extends BaseGeo {

	private static final long serialVersionUID = -9211329183759333052L;
	/**
	 * The type of the geometry feature the Google Map API
	 */
	private String type = GEOMETRY_TYPE_POLYGON;
	/**
	 * The coordinates for the Geometry Feature of POLYGON type
	 * [[[lat,lng],[lat,lng],[lat,lng],[lat,lng]]]
	 */
	private List<List<List<Double>>> coordinates = new LinkedList<List<List<Double>>>();

	public GeometryPolygon() {
		super();
		coordinates = new LinkedList<List<List<Double>>>();
	}

	public GeometryPolygon(List<List<List<Double>>> coordinates) {
		super();
		this.coordinates = coordinates;
	}

	public GeometryPolygon(String type, List<List<List<Double>>> coordinates) {
		super();
		this.type = type;
		this.coordinates = coordinates;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<List<List<Double>>> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(List<List<List<Double>>> coordinates) {
		this.coordinates = coordinates;
	}

}
