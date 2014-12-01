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
package org.cgiar.dapa.ccafs.tpe.convexhull;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class HullPoint {
	/**
	 * The latitude coordinate
	 */
	private Double latitude;
	/**
	 * The longitude coordinate
	 */
	private Double longitude;

	public HullPoint(Double latitude, Double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "(" + getLatitude() + "," + getLongitude() + ")";
	}

	public List<Double> getCoordinates() {
		// The ordering of x and y are important, this means that when
		// representing latitude and longitiude the order is [longitude,
		// latitude].

		return new LinkedList<Double>(Arrays.asList(this.getLongitude(),
				this.getLatitude()));
	}
}
