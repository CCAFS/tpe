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

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * The class that implements the convex hull algorithm for the TPE polygons
 * 
 * @author NMATOVU
 *
 */
public class ConvexHull implements ITPEConvexHull {

	public ConvexHull() {
		super();
	}

	public List<ConvexHullPoint> getConvexHullPolygon(
			List<ConvexHullPoint> coordinates) {
		// The list sorted by the latitude coordinates
		List<ConvexHullPoint> latitudeSorted = new LinkedList<ConvexHullPoint>();
		if (!coordinates.isEmpty() && coordinates != null) {
			latitudeSorted = coordinates;
			Collections.sort(latitudeSorted, new LatComparator());

			// The total count of the sorted coordinates
			int n = latitudeSorted.size();

			// The upper coordinates of the convex hull polygon
			List<ConvexHullPoint> latUpper = new LinkedList<ConvexHullPoint>();
			// Add the first two minimum coordinates from the latitudeSorted
			// list to the latUpper list
			latUpper.add(latitudeSorted.get(0));
			latUpper.add(latitudeSorted.get(1));

			// int upSize = 2;
			// int upperCount = latUpper.size();

			for (int i = 2; i < n; i++) {
				// latUpper.add(i,latitudeSorted.get(i));
				latUpper.add(latitudeSorted.get(i));
				// upperCount++;
				while (latUpper.size() > 2
						&& !counterClockWiseTurn(
								latUpper.get(latUpper.size() - 3),
								latUpper.get(latUpper.size() - 2),
								latUpper.get(latUpper.size() - 1))) {
					// Remove the middle coordinate of the three last
					latUpper.remove(latUpper.size() - 2);
					// upperCount--;
				}
			}

			// The lower coordinates of the convex hull polygon
			/*
			 * List<ConvexHullPoint> latLower = new
			 * LinkedList<ConvexHullPoint>();
			 */

			// Add the first top two (max) coordinates from the latitudeSorted
			// list to the latLowerf list
			/*
			 * latLower.add(latitudeSorted.get(latitudeSorted.size() - 1));
			 * latLower.add(latitudeSorted.get(latitudeSorted.size() - 2));
			 */

			// int lowerCount = 2;

			/*
			 * for (int i = n - 3; i >= 0; i--) { //
			 * latLower.add(i,latitudeSorted.get(i));
			 * latLower.add(latitudeSorted.get(i)); // lowerCount++;
			 * 
			 * while (latLower.size() > 2 && counterClockWiseTurn(
			 * latLower.get(latLower.size() - 3), latLower.get(latLower.size() -
			 * 2), latLower.get(latLower.size() - 1))) {
			 * 
			 * // Remove the middle coordinate of the three last
			 * latLower.remove(latLower.size() - 2); // lowerCount--; } }
			 */

			List<ConvexHullPoint> convexHullPolygon = new LinkedList<ConvexHullPoint>();

			convexHullPolygon.addAll(latUpper);
			// Add the first coordinate to close the polygon
			convexHullPolygon.add(latUpper.get(0));

			// for (int i = 0; i < upperCount; i++) {
			// convexHullPolygon.add(latUpper.get(i));
			// }

			// convexHullPolygon.addAll(latLower);
			// for (int i = 1; i < lowerCount - 1; i++) {
			// convexHullPolygon.add(latLower.get(i));
			// }

			return convexHullPolygon;
		}

		return new ArrayList<ConvexHullPoint>();
	}

	public List<ConvexHullPoint> getRectilinearConvexHullPolygon(
			List<ConvexHullPoint> coordinates) {
		// The list sorted by the latitude coordinates
		List<ConvexHullPoint> convexHullPolygon = new LinkedList<ConvexHullPoint>();
		List<ConvexHullPoint> rectilinearPolygon = new LinkedList<ConvexHullPoint>();
		if (!coordinates.isEmpty() && coordinates != null) {
			// Obtain the convex hull coordinates
			convexHullPolygon = this.getConvexHullPolygon(coordinates);

			if (convexHullPolygon.size() == 1) {
				// In case there is only one coordinate
				// TODO Determine two anonymous coordinates at an approximated
				// distance from this coordinate, and then apply the same
				// approach as for two coordinates below.
			} else if (convexHullPolygon.size() == 2) {

				List<ConvexHullPoint> coords = new LinkedList<ConvexHullPoint>();
				rectilinearPolygon = new LinkedList<ConvexHullPoint>();
				Double center;
				// TODO If the latitude of the first coordinate and latitude
				// of the second coordinate are equal, Or if the longitude
				// of 1st and 2nd coordinates are equal, then determine a
				// rectangle coordinates to form a polygon.

				if (new LatComparator().compare(convexHullPolygon.get(0),
						convexHullPolygon.get(1)) == 0) {
					// If the two latitudes for the two coordinates are equal or
					// are the same
					// If the two coordinates are both on the same vertical line

					// TODO Determine the coordinates of the rectangle.

					center = Math.abs(convexHullPolygon.get(0).getLatitude()
							- convexHullPolygon.get(1).getLatitude()) / 2;

					// The original two coordinates are excluded but they could
					// be included as well. However, if they are added they will
					// not change or alter the shape of polygon.
					coords.add(new ConvexHullPoint(convexHullPolygon.get(0)
							.getLatitude() + center, convexHullPolygon.get(0)
							.getLongitude()));
					coords.add(new ConvexHullPoint(convexHullPolygon.get(0)
							.getLatitude() - center, convexHullPolygon.get(0)
							.getLongitude()));
					coords.add(new ConvexHullPoint(convexHullPolygon.get(1)
							.getLatitude() - center, convexHullPolygon.get(1)
							.getLongitude()));

					coords.add(new ConvexHullPoint(convexHullPolygon.get(1)
							.getLatitude() + center, convexHullPolygon.get(1)
							.getLongitude()));
					// TODO To close the polygon, we add the first coordinate
					// once again.

					coords.add(new ConvexHullPoint(convexHullPolygon.get(0)
							.getLatitude() + center, convexHullPolygon.get(0)
							.getLongitude()));

					return rectilinearPolygon;

				} else if (new LngComparator().compare(
						convexHullPolygon.get(0), convexHullPolygon.get(1)) == 0) {
					// If the two longitudes for the two coordinates are equal
					// or are the same
					// If the two coordinates are both on the same horizontal
					// line

					// TODO Determine the coordinates of the rectangle.

					center = Math.abs(convexHullPolygon.get(0).getLongitude()
							- convexHullPolygon.get(1).getLongitude()) / 2;

					// The original two coordinates are excluded but they could
					// be included as well. However, if they are added they will
					// not change or alter the shape of polygon.
					coords.add(new ConvexHullPoint(convexHullPolygon.get(0)
							.getLatitude(), convexHullPolygon.get(0)
							.getLongitude() + center));
					coords.add(new ConvexHullPoint(convexHullPolygon.get(0)
							.getLatitude(), convexHullPolygon.get(0)
							.getLongitude() - center));
					coords.add(new ConvexHullPoint(convexHullPolygon.get(1)
							.getLatitude(), convexHullPolygon.get(1)
							.getLongitude() - center));

					coords.add(new ConvexHullPoint(convexHullPolygon.get(1)
							.getLatitude(), convexHullPolygon.get(1)
							.getLongitude() + center));
					// TODO To close the polygon, we add the first coordinate
					// once again.

					coords.add(new ConvexHullPoint(convexHullPolygon.get(0)
							.getLatitude(), convexHullPolygon.get(0)
							.getLongitude() + center));

					return rectilinearPolygon;
				} else {
					// For only two coordinates that are not on the same
					// vertical or horizontal line
					// Determine the diagonal coordinates so as to complete the
					// rectangle polygon

					rectilinearPolygon = new LinkedList<ConvexHullPoint>();
					// Assumed that the coordinates in convexHull list were
					// already sorted by latitude.
					ConvexHullPoint firstCoord = convexHullPolygon.get(0);
					ConvexHullPoint secondCoord = convexHullPolygon.get(1);

					// Then add the first coordinate to the rectilinear list
					rectilinearPolygon.add(firstCoord);
					// Add the second coordinate from the diagonal line
					rectilinearPolygon.add(new ConvexHullPoint(firstCoord
							.getLatitude(), secondCoord.getLongitude()));
					// Add the second coordinate as the third coordinate
					rectilinearPolygon.add(secondCoord);
					// Then add the second diagonal coordinate
					rectilinearPolygon.add(new ConvexHullPoint(secondCoord
							.getLatitude(), firstCoord.getLongitude()));

					// TODO Add the firstCoordinate to close the polygon
					// rectilinearPolygon.add(firstCoord);

					return rectilinearPolygon;
				}

			} else {
				// For more than two coordinates
				rectilinearPolygon = new LinkedList<ConvexHullPoint>();
				rectilinearPolygon.add(convexHullPolygon.get(0));
				for (int i = 1; i < convexHullPolygon.size(); i++) {
					// Add the perpendicular coordinate
					rectilinearPolygon.add(getPerpendicularCoordinate(
							convexHullPolygon.get(i - 1),
							convexHullPolygon.get(i)));
					// Then add the next coordinate as well
					rectilinearPolygon.add(convexHullPolygon.get(i));
				}
				// TODO Close the polygon
				// Finally add the first coordinate once again to close the
				// polygon
				rectilinearPolygon.add(convexHullPolygon.get(0));

				return rectilinearPolygon;
			}

		} else
			return new LinkedList<ConvexHullPoint>();

		return new ArrayList<ConvexHullPoint>();
	}

	public List<ConvexHullPoint> getOrthogonalConvexHullPolygon(
			List<ConvexHullPoint> coordinates) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean counterClockWiseTurn(ConvexHullPoint topSecondOnStack,
			ConvexHullPoint topFirstOnStack, ConvexHullPoint nextCoordinate) {
		return (topFirstOnStack.getLatitude() - topSecondOnStack.getLatitude())
				* (nextCoordinate.getLongitude() - topSecondOnStack
						.getLongitude())
				- (topFirstOnStack.getLongitude() - topSecondOnStack
						.getLongitude())
				* (nextCoordinate.getLatitude() - topSecondOnStack
						.getLatitude()) > 0;
	}

	public ConvexHullPoint getPerpendicularCoordinate(
			ConvexHullPoint prevCoordinate, ConvexHullPoint nextCoordinate,
			Boolean nextLatLngMax) {
		// TODO Implement a method to determine the nextLatLngMax
		if (nextLatLngMax)
			return new ConvexHullPoint(nextCoordinate.getLatitude(),
					prevCoordinate.getLongitude());

		return new ConvexHullPoint(prevCoordinate.getLatitude(),
				nextCoordinate.getLongitude());
	}

	public ConvexHullPoint getPerpendicularCoordinate(
			ConvexHullPoint prevCoordinate, ConvexHullPoint nextCoordinate) {
		if (counterClockWiseTurn(prevCoordinate, nextCoordinate,
				new ConvexHullPoint(prevCoordinate.getLatitude(),
						nextCoordinate.getLongitude())))
			return new ConvexHullPoint(nextCoordinate.getLatitude(),
					prevCoordinate.getLongitude());

		return new ConvexHullPoint(prevCoordinate.getLatitude(),
				nextCoordinate.getLongitude());
	}

	public Map<Integer, List<ConvexHullPoint>> getConvexHullByStation(
			Map<Integer, List<ConvexHullPoint>> coordinatesMap) {

		Map<Integer, List<ConvexHullPoint>> convexHullMap = new LinkedHashMap<Integer, List<ConvexHullPoint>>();
		// If the coordinates list is not empty or null
		if (!coordinatesMap.isEmpty() && coordinatesMap != null)
			// For each list of coordinates associated with a station id
			for (Integer station : coordinatesMap.keySet())
				convexHullMap.put(station,
						this.getConvexHullPolygon(coordinatesMap.get(station)));

		return convexHullMap;
	}

	public Map<Integer, List<ConvexHullPoint>> getRectilinearConvexHullByStation(
			Map<Integer, List<ConvexHullPoint>> coordinatesMap) {
		Map<Integer, List<ConvexHullPoint>> rectilinearConvexHullMap = new LinkedHashMap<Integer, List<ConvexHullPoint>>();
		// If the coordinates list is not empty or null
		if (!coordinatesMap.isEmpty() && coordinatesMap != null)
			// For each list of coordinates associated with a station id
			for (Integer station : coordinatesMap.keySet())
				rectilinearConvexHullMap.put(station, this
						.getRectilinearConvexHullPolygon(coordinatesMap
								.get(station)));

		return rectilinearConvexHullMap;
	}
}
