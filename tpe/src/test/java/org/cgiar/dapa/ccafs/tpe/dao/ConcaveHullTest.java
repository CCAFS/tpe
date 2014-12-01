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

package org.cgiar.dapa.ccafs.tpe.dao;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.cgiar.dapa.ccafs.tpe.convexhull.ConcaveHull;
import org.cgiar.dapa.ccafs.tpe.convexhull.HullPoint;
import org.cgiar.dapa.ccafs.tpe.convexhull.LngComparator;

public class ConcaveHullTest extends BaseTest {

	private Logger log = Logger.getLogger(this.getClass());

	/**
	 * Sorts the points.
	 */
	public void testSortPoints() {

		List<HullPoint> originalPoints = new LinkedList<HullPoint>(
				Arrays.asList(new HullPoint(20.0, 10.0), new HullPoint(15.0,
						9.0), new HullPoint(-20.0, 12.0), new HullPoint(10.0,
						-10.0), new HullPoint(1.0, -5.0), new HullPoint(-10.0,
						-10.0), new HullPoint(15.0, -11.0), new HullPoint(5.0,
						20.0)));
		log.info("Original List before sorting:");
		log.info(originalPoints);

		// Sort the collections
		Collections.sort(originalPoints, new LngComparator());
		log.info("Original List after ascending sorting:");
		log.info(originalPoints);

		// Sort the collections in descending order
		Collections.sort(originalPoints,
				Collections.reverseOrder(new LngComparator()));
		log.info("Original List after descending sorting:");
		log.info(originalPoints);

	}

	/**
	 * Concave Hull Points
	 */
	public void testGetConcaveHullPoints() {

		List<HullPoint> originalPoints = new LinkedList<HullPoint>(
				Arrays.asList(new HullPoint(20.0, 10.0), new HullPoint(15.0,
						9.0), new HullPoint(-20.0, 12.0), new HullPoint(10.0,
						-10.0), new HullPoint(1.0, -5.0), new HullPoint(-10.0,
						-10.0), new HullPoint(15.0, -11.0), new HullPoint(5.0,
						20.0), new HullPoint(-5.0, -5.0), new HullPoint(5.0,
						5.0), new HullPoint(5.0, -5.0),
						new HullPoint(5.0, 15.0)));
		log.info("Original Points before concave hull:");
		log.info(originalPoints);
		log.info("# of original points: " + originalPoints.size());
		List<HullPoint> concaveHullPoints = new LinkedList<HullPoint>();

		concaveHullPoints = ConcaveHull.getConcaveHull(originalPoints, 3);
		log.info("Original Points after concave hull:");

		log.info(concaveHullPoints);

		log.info("# of concave hull: " + concaveHullPoints.size());

	}
}
