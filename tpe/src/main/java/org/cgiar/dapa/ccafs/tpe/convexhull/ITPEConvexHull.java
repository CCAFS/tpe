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

import java.util.List;
import java.util.Map;

/**
 * The interface that defines the TPE convex hull algorithm for the map polygons
 * 
 * @author NMATOVU
 *
 */
public interface ITPEConvexHull {
	/**
	 * Returns the convex hull polygon coordinates using the specified list of
	 * coordinates
	 * 
	 * @param coordinates
	 *            the original coordinates
	 * @return convex hull coordinates
	 */
	List<ConvexHullPoint> getConvexHullPolygon(List<ConvexHullPoint> coordinates);

	/**
	 * Returns the rectilinear convex hull coordinates from the specified
	 * coordinates
	 * 
	 * @param coordinates
	 *            the original coordinates
	 * @return rectilinear convex hull
	 */
	List<ConvexHullPoint> getRectilinearConvexHullPolygon(
			List<ConvexHullPoint> coordinates);

	/**
	 * Returns the orthogonal convex hull from the specified list of coordinates
	 * 
	 * @param coordinates
	 *            the original coordinates
	 * @return Orthogonal convex hull
	 */
	List<ConvexHullPoint> getOrthogonalConvexHullPolygon(
			List<ConvexHullPoint> coordinates);

	/**
	 * Returns a boolean value if the next coordinate specified lies above the
	 * line joining topSecondOnStack coordinate to topFirstOnStack coordinate
	 * 
	 * @param topSecondOnStack
	 *            the top second coordinate on the convex hull stack
	 * @param topFirstOnStack
	 *            the top first coordinate on the convex hull stack
	 * @param nextCoordinate
	 *            the new coordinate to add to the convex hull stack
	 * @return true or false
	 */
	Boolean counterClockWiseTurn(ConvexHullPoint topSecondOnStack,
			ConvexHullPoint topFirstOnStack, ConvexHullPoint nextCoordinate);

	/**
	 * Returns the perpendicular coordinate for the two specified coordinates.
	 * 
	 * @param prevCoordinate
	 *            the previous coordinate on the convex hull stack
	 * @param nextCoordinate
	 *            the next coordinate on the convex hull stack
	 * @param upperConvexHull
	 *            true if the both the latitude and longitude of the
	 *            nextCoordinate are maximum compared to those of prevCoordinate
	 * @return perpendicular coordinate
	 */
	ConvexHullPoint getPerpendicularCoordinate(ConvexHullPoint prevCoordinate,
			ConvexHullPoint nextCoordinate, Boolean nextLatLngMax);

	/**
	 * Returns the perpendicular coordinate for the two specified coordinates.
	 * 
	 * @param prevCoordinate
	 *            the previous coordinate on the convex hull stack
	 * @param nextCoordinate
	 *            the next coordinate on the convex hull stack
	 * 
	 * @return perpendicular coordinate
	 */
	ConvexHullPoint getPerpendicularCoordinate(ConvexHullPoint prevCoordinate,
			ConvexHullPoint nextCoordinate);

	/**
	 * Creates and returns a convex hull map coordinates for each station being
	 * specified. This method definition accepts a map of stations, each station
	 * with its list of coordinates that relates to it.
	 * 
	 * @param coordinatesMap
	 *            the map of station id's and their corresponding coordinates
	 *            associated to them.
	 * @return rectilinear convex hull
	 */
	Map<Integer, List<ConvexHullPoint>> getConvexHullByStation(
			Map<Integer, List<ConvexHullPoint>> coordinatesMap);

	/**
	 * Creates and returns a rectilinear convex hull map coordinates for each
	 * station being specified. This method definition accepts a map of
	 * stations, each station with its list of coordinates that relates to it.
	 * 
	 * @param coordinatesMap
	 *            the map of station id's and their corresponding coordinates
	 *            associated to them.
	 * @return rectilinear convex hull
	 */
	Map<Integer, List<ConvexHullPoint>> getRectilinearConvexHullByStation(
			Map<Integer, List<ConvexHullPoint>> coordinatesMap);
}
