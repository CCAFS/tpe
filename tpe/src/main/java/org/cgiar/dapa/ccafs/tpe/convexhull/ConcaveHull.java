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

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

public class ConcaveHull {
	
	private Logger log = Logger.getLogger(this.getClass());
	/**
	 * Create the concave hull coordinates that forms a polygon
	 * 
	 * @param coordinates
	 *            the original list of coordinates used to create the hull
	 *            coordinates
	 * @param k
	 *            the number of nearest points to the current point
	 * @return concave hull coordinates
	 */
	public static List<HullPoint> getConcaveHull(List<HullPoint> coordinates,
			int k) {

		 
		List<HullPoint> hullPoints = new LinkedList<HullPoint>();
		List<HullPoint> nearestPoints = new LinkedList<HullPoint>();
		List<HullPoint> originalPoints = new LinkedList<HullPoint>();
		HullPoint firstPoint, currentPoint;

		// Return empty list
		if ((k < 3) || (coordinates.size() < 3) || (coordinates == null)
				|| coordinates.isEmpty())
			return hullPoints;

		// Clean the original list, remove the duplicate points
		originalPoints = cleanCoordinates(coordinates);

		// If the ppoints are less than 3 after cleaning, hust return an empty
		// list
		if (originalPoints.size() < 3)
			return hullPoints;

		// If there are atleast 3 points in the cleaned list

		// Sort the original list by longitude (y-minimum)
		Collections.sort(originalPoints, new LngComparator());

		if (originalPoints.size() == 3)
			// If there are only 3 coordinates, return that as the
			// concave hull
			return originalPoints;

		// For more than 3 coordinates in the list

		// Set the first point in the list as the first point
		firstPoint = originalPoints.get(0);

		// Set the current point
		currentPoint = firstPoint;
		// Add the first point to the hull list
		hullPoints.add(firstPoint);

		// Remove the first point from the original list=
		// originalPoints.remove(firstPoint);
		originalPoints.remove(0);

		// Double previousAngle = new Double(0);
		// A boolean value that determines if the line segment
		// intersects any of the hulls segments
		boolean intersects = true;

		// The last point
		boolean lastPoint = false;

		// The steps
		int step = 2;

		while (!lastPoint && originalPoints.size() > 0) {
			// Add the first point to the original list if it is
			// step 5
			if (step == 5)
				originalPoints.add(firstPoint);
			// Get the nearest points to the current point
			nearestPoints = getNearestPoints(originalPoints, currentPoint, k);

			// Sort the nearest points by angle (right turn)
			/*
			 * Collections.sort(nearestPoints, new
			 * AngleComparator(currentPoint));
			 */
			// Sort the points in desceding order
			Collections
					.sort(nearestPoints, Collections
							.reverseOrder(new AngleComparator(currentPoint)));

			// Set it to true
			intersects = true;

			// Iterate through the nearest points and determine
			// if at least one of the nearest points
			// does not intersect any of the hulls segments
			// If all do intersect, then increment the value of
			// k and repeat.
			// TODO Comparator with descending order

			for (int i = 0; i < nearestPoints.size(); i++) {
				intersects = intersectsPolygon(hullPoints, currentPoint,
						nearestPoints.get(i));
				if (!intersects) {
					// Get the nearest point with the high right turn
					// angle
					currentPoint = nearestPoints.get(i);
					// Add the current point to the hull list
					hullPoints.add(currentPoint);
					// Remove the current point from the original list
					// of points.
					originalPoints.remove(currentPoint);

					// Increment the steps
					step++;

					break;
				}
			}

			// If all the line segments for the nearest points
			// intersect the segments of the polygon, then
			// increment k and then repeat
			if (intersects)
				getConcaveHull(coordinates, k + 1);

			// If the current point is the same as the first point, then
			// return the hull list
			if (currentPoint == firstPoint) {
				boolean allInside = true;
				for (HullPoint point : coordinates) {
					allInside = pointInsidePolygon(hullPoints, point);

					if (!allInside)
						return getConcaveHull(coordinates, k + 1);
				}
				lastPoint = true;
				return hullPoints;
			}
		}

		return hullPoints;
	}

	private static boolean pointInsidePolygon(List<HullPoint> originalPoints,
			HullPoint point) {
		boolean inside = false;

		for (int i = 0, j = originalPoints.size() - 1; i < originalPoints
				.size(); j = i++) {
			double xi = originalPoints.get(i).getLatitude(), yi = originalPoints
					.get(i).getLongitude(), xj = originalPoints.get(j)
					.getLatitude(), yj = originalPoints.get(j).getLongitude();

			boolean intersect = ((yi > point.getLongitude()) != (yj > point
					.getLongitude()))
					&& (point.getLatitude() < (xj - xi)
							* (point.getLongitude() - yi) / (yj - yi) + xi);

			if (intersect)
				inside = !inside;
		}

		return inside;

	}

	private static boolean intersectsPolygon(List<HullPoint> hullPoints,
			HullPoint currentPoint, HullPoint nextPoint) {
		// boolean intersect = false;

		for (int i = 1, l = hullPoints.size() - 1; i < l; i++)
			if (intersects(hullPoints.get(i - 1), hullPoints.get(i),
					hullPoints.get(l), nextPoint))
				return true;
		return false;
	}

	/**
	 * Returns a boolean value if the two segments intersects
	 * 
	 * @param p1
	 *            the first point of the first line segment
	 * @param p2
	 *            the second point of the first line segment
	 * @param q1
	 *            the first point of the second line segment
	 * @param q2
	 *            the second point of the second line segment
	 * @return true or false
	 */
	private static boolean intersects(HullPoint p1, HullPoint p2, HullPoint q1,
			HullPoint q2) {
		if ((p1.getLatitude() == q1.getLatitude()
				&& p1.getLongitude() == q1.getLongitude() || p2.getLatitude() == q2
				.getLatitude() && p2.getLongitude() == q2.getLongitude())
				|| (p1.getLatitude() == q2.getLatitude()
						&& p1.getLongitude() == q2.getLongitude() || p2
						.getLatitude() == q1.getLatitude()
						&& p2.getLongitude() == q1.getLongitude()))
			return false;

		return (counterClockWise(p1, p2, q1) * counterClockWise(p1, p2, q2) <= 0)
				&& (counterClockWise(q1, q2, p1) * counterClockWise(q1, q2, p2) <= 0);

	}

	/**
	 * Gets the closest or nearest points to the current point. The number of
	 * nearest points will be k;
	 * 
	 * @param originalPoints
	 *            the original list of coordinates to get the nearest points
	 *            from
	 * @param currentPoint
	 *            the current point
	 * @param k
	 *            the number of nearest points
	 * @return the nearest points
	 */
	private static List<HullPoint> getNearestPoints(
			List<HullPoint> originalPoints, HullPoint currentPoint, int k) {
		List<HullPoint> nearestPoints = new LinkedList<HullPoint>();
		List<HullPoint> coordinates = new LinkedList<HullPoint>();

		if (originalPoints != null && !originalPoints.isEmpty()) {
			coordinates = originalPoints;
			// Sort the coordinates by the distance from the current point
			Collections.sort(coordinates, new DistanceComparator(currentPoint));
			// The list of nearest points

			for (int i = 0; i < k; i++)
				// Get the nearest points
				nearestPoints.add(coordinates.get(i));

		}

		return nearestPoints;
	}

	/**
	 * Removes the duplicate coordinates from the original list of coordinates
	 * 
	 * @param coordinates
	 *            the list of coordinates to clean
	 * @return the cleaned coordinates
	 */
	private static List<HullPoint> cleanCoordinates(List<HullPoint> coordinates) {
		List<HullPoint> cleanPoints = new LinkedList<HullPoint>();
		// If the list of coordinates is not empty or null
		if (!coordinates.isEmpty() && coordinates != null)
			// For each coordinate in the list add it to the clean list of does
			// not exist there yet
			for (HullPoint point : coordinates)
				if (!cleanPoints.contains(point))
					cleanPoints.add(point);
		return cleanPoints;
	}

	public static Double getDistanceTo(HullPoint otherPoint,
			HullPoint currentPoint) {
		return Math.sqrt(Math.pow(
				(currentPoint.getLatitude() - otherPoint.getLatitude()), 2)
				+ Math.pow((currentPoint.getLongitude() - otherPoint
						.getLongitude()), 2));
	}

	/**
	 * Gets the angle between two coordinates
	 * 
	 * @param currentPoint
	 *            the current coordinate
	 * @param nextPoint
	 *            the next point
	 * @return the angle
	 */
	public static Double getAngle(HullPoint currentPoint, HullPoint nextPoint) {

		return Math.atan2(
				nextPoint.getLongitude() - currentPoint.getLongitude(),
				nextPoint.getLatitude() - currentPoint.getLatitude());
	}

	public static int counterClockWise(HullPoint firstPoint,
			HullPoint secondPoint, HullPoint thirdPoint) {

		double value = (secondPoint.getLatitude() - firstPoint.getLatitude())
				* (thirdPoint.getLongitude() - firstPoint.getLongitude())
				- (secondPoint.getLongitude() - firstPoint.getLongitude())
				* (thirdPoint.getLatitude() - firstPoint.getLatitude());
		if (value > 0)
			return 1;
		if (value < 0)
			return -1;
		return 0;
	}
}
