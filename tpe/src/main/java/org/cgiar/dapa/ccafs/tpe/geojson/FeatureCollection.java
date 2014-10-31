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
 * Represents the GeoJSON Geometry for the Google Map API
 * 
 * @author NMATOVU
 *
 */
public class FeatureCollection extends BaseGeo {

	private static final long serialVersionUID = 5627854772070853600L;

	private String type = GEOJSON_VALUE_FEATURE_COLLECTION;
	/**
	 * The list of features in the FeatureCollection
	 */
	private List<SFeature> features = new LinkedList<SFeature>();

	public FeatureCollection() {
		super();
		features = new LinkedList<SFeature>();
	}

	public FeatureCollection(String type, List<SFeature> features) {
		super();
		this.type = type;
		this.features = features;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<SFeature> getFeatures() {
		return features;
	}

	public void setFeatures(List<SFeature> features) {
		this.features = features;
	}

}
