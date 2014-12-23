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
package org.cgiar.dapa.ccafs.tpe.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cgiar.dapa.ccafs.tpe.convexhull.HullPoint;
import org.cgiar.dapa.ccafs.tpe.entity.Region;
import org.cgiar.dapa.ccafs.tpe.entity.Soil;
import org.cgiar.dapa.ccafs.tpe.entity.Station;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * This is the tpe utils class that contains the utility methods that are being
 * used by various classes and methods in the application
 * 
 * @author NMATOVU
 *
 */
public class Utils implements Constants {
	/**
	 * The size of the tile
	 */
	private static final Double TILE_SIZE_64 = 20.0;
	@SuppressWarnings("unused")
	private static Log log = LogFactory.getLog(Utils.class.getClass());

	/**
	 * Creates the marker symbol map for the highcharts spline chart
	 * 
	 * @return marker
	 */
	public static Map<String, String> initializeSplineMarker() {
		Map<String, String> marker = new HashMap<String, String>();
		marker.put(MARKER_SYMBOL, MARKER_SYMBOL_SQUARE);
		return marker;
	}

	/**
	 * Provides the list of region ids
	 * 
	 * @param regions
	 * @return region ids
	 */
	public static List<Integer> getRegionIds(List<Region> regions) {
		List<Integer> ids = new LinkedList<Integer>();
		for (Region region : regions)
			ids.add(region.getId());
		return ids;
	}

	/**
	 * Returns a list of region ids
	 * 
	 * @param stations
	 * @return id list
	 */
	public static List<Integer> getStationIds(List<Station> stations) {
		List<Integer> ids = new LinkedList<Integer>();
		for (Station station : stations)
			ids.add(station.getId());
		return ids;
	}

	public static Object loadJSONFile(String fileName) {

		File resource = null;
		try {
			resource = new File(Thread.currentThread().getContextClassLoader()
					.getResource(fileName).toURI());
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JSONParser parser = new JSONParser();
		Object json = null;
		try {
			json = parser.parse(new FileReader(resource));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return json;

	}

	public static Object loadJSON(String fileName) {
		//
		// File file = new File(fileName);
		// String absolutePath = file.getAbsolutePath();

		JSONParser parser = new JSONParser();
		Object json = null;
		try {
			json = parser.parse(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return json;

	}

	/**
	 * This method create the default TPE scenarios (RAINFED, IRRIGATED and
	 * POTENTIAL)
	 * 
	 * @return
	 */
	public static List<String> getScenarios() {
		List<String> scenarios = new LinkedList<String>();
		for (Scenario sc : Scenario.values()) {
			scenarios.add(sc.name().toString());
		}
		return scenarios;
	}

	public static List<List<List<Double>>> convertFromConvexHull(
			List<HullPoint> convexHullCoordinates) {
		List<List<Double>> coordinates = new LinkedList<List<Double>>();

		for (Iterator<HullPoint> iteratorPoint = convexHullCoordinates
				.iterator(); iteratorPoint.hasNext();) {

			coordinates.add(iteratorPoint.next().getCoordinates());

			System.out.println(coordinates);
		}

		return new LinkedList<List<List<Double>>>(Arrays.asList(coordinates));

	}

	/**
	 * Calculates the distance between two coordinates on the Google Map
	 * 
	 * @param firstCooordinate
	 *            the first LatLng coordinate
	 * @param secondCoordinate
	 *            the second LatLng coordinate
	 * @return distance
	 */
	public static Double distnceBtnLatLng(HullPoint firstCooordinate,
			HullPoint secondCoordinate) {
		// Radius of the Earth in Km (6371)
		Double radiusEarth = new Double(6371);

		Double dLat = degToRad(secondCoordinate.getLatitude(),
				firstCooordinate.getLatitude());

		Double dLng = degToRad(secondCoordinate.getLongitude(),
				firstCooordinate.getLongitude());

		Double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
				+ Math.cos(degToRad(firstCooordinate.getLatitude()))
				* Math.cos(degToRad(secondCoordinate.getLatitude()))
				* Math.sin(dLng / 2) * Math.sin(dLng / 2);

		Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		Double d = radiusEarth * c;

		// if (d > 1)
		// return new Double(Math.round(d));
		//
		// else if (d <= 1)
		//
		// return new Double(Math.round(d * 1000));

		return d;

	}

	public static Double degToRad(Double secondCoordinate,
			Double firstCoordinate) {

		return (secondCoordinate - firstCoordinate) * Math.PI / 180;
	}

	public static Double degToRad(Double coordinate) {

		return coordinate * Math.PI / 180;
	}

	public static double radTodeg(double rad) {
		return (rad * 180.0 / Math.PI);
	}

	public static Integer numberOfTiles(Double distance, Double tileSize) {
		Double tiles = 0.0;

		if (distance != null && distance != 0) {
			tiles = distance / TILE_SIZE_64;

			if (tiles > 1.0)
				return tiles.intValue();
		} else
			return 0;

		return tiles.intValue();
	}

	public static Integer numberOfTiles(HullPoint firstCooordinate,
			HullPoint secondCoordinate) {

		Double distance = distnceBtnLatLng(firstCooordinate, secondCoordinate);
		Double tiles = 0.0;

		if (distance != null && distance != 0) {
			tiles = distance / TILE_SIZE_64;

			if (tiles > 1.0)
				return tiles.intValue();
		} else
			return 0;

		return tiles.intValue();
	}

	// Map<Lat, Lng>
	public static Map<Double, Double> pixelStep(HullPoint firstCooordinate,
			HullPoint secondCoordinate) {
		// Map<Lat, Lng>
		Map<Double, Double> pixelSteps = new HashMap<Double, Double>();

		Integer numberOfTiles = numberOfTiles(firstCooordinate,
				secondCoordinate);

		Double stepLat = (firstCooordinate.getLatitude() - secondCoordinate
				.getLatitude()) / numberOfTiles;

		Double stepLng = (firstCooordinate.getLongitude() - secondCoordinate
				.getLongitude()) / numberOfTiles;

		pixelSteps.put(stepLat, stepLng);

		return pixelSteps;

	}

	public static List<HullPoint> getPixelCoordinates(
			HullPoint firstCooordinate, HullPoint secondCoordinate,
			boolean lowerHull) {
		// The Convex Hull Points from the first coordinate to the second
		// coordinate
		List<HullPoint> pixelPoints = new LinkedList<HullPoint>();
		// The points from point A to point B
		List<HullPoint> pointsAB = new LinkedList<HullPoint>();

		Integer numberOfTiles = numberOfTiles(firstCooordinate,
				secondCoordinate);

		Double stepLat = (firstCooordinate.getLatitude() - secondCoordinate
				.getLatitude()) / numberOfTiles;

		Double stepLng = (firstCooordinate.getLongitude() - secondCoordinate
				.getLongitude()) / numberOfTiles;

		// Get the points on line AB
		// Initialize the list with the coordinates of the first point A
		pointsAB.add(firstCooordinate);
		for (int i = 0; i < numberOfTiles; i++) {
			pointsAB.add(new HullPoint(
					firstCooordinate.getLatitude() + stepLat, firstCooordinate
							.getLongitude() + stepLng));
		}
		// Then add the last coordinates from the second point B
		pointsAB.add(secondCoordinate);

		if (!lowerHull) {
			// For lower hull, start with (FirstLat,PixelLng)
			for (int i = 0; i < pointsAB.size(); i++) {
				pixelPoints.add(pointsAB.get(i));
				// Add the perpendicular coordinate if is not the last point
				if (i != pointsAB.size() - 1)
					pixelPoints.add(new HullPoint(
							pointsAB.get(i).getLatitude(), pointsAB.get(i + 1)
									.getLongitude()));
			}
		} else {
			// TODO Consider or check the magnitude of the coordinates
			// For lower hull, start with (FirstLat)
			for (int i = 0; i < pointsAB.size(); i++) {
				pixelPoints.add(pointsAB.get(i));
				// Add the perpendicular coordinate if is not the last point
				if (i != pointsAB.size() - 1)
					pixelPoints.add(new HullPoint(pointsAB.get(i + 1)
							.getLatitude(), pointsAB.get(i).getLongitude()));
			}
		}

		return pixelPoints;

	}

	public static List<Integer> getTextureId(List<Soil> textures) {
		List<Integer> ids = new LinkedList<Integer>();
		for (Soil soil : textures)
			ids.add(soil.getId());
		return ids;
	}

	/**
	 * The method for loading a JSON file data
	 * 
	 * @param fileName
	 * @return
	 */
	public static Object loadJSONData(String fileName) {

		JSONParser parser = new JSONParser();
		Object json = null;
		try {
			json = parser.parse(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return json;
	}
}
