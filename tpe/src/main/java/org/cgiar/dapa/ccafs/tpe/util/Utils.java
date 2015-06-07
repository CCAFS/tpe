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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

	public static List<Integer> getClusters() {
		List<Integer> clusters = new LinkedList<Integer>(Arrays.asList(1, 2, 3));
		return clusters;
	}

	public static String getClusterColor(Integer cluster) {
		// LFE_1("#990000"), HFE_2("#009900"), FE_3("#ADD8E6");
		String color;
		switch (cluster) {
		case 1:
			color = "#990000";
			break;
		case 2:
			color = "#009900";
			break;
		case 3:
			// color = "#ADD8E6";
			color = "#000099";
			break;
		default:
			color = "#000000";
			break;
		}

		return color;
	}

	/**
	 * Loads the Geo JSON files from the server
	 * 
	 * @param fileName
	 *            the name of the Geo JSON file to load
	 * @return the Geo JSON file
	 */
	public static Object readJSON(String fileName) {
		// The geo JSON file to load.
		Object json = null;

		// If the file is not null
		if (fileName != null) {
			File brazilJSON = new File(fileName);
			JSONParser parser = new JSONParser();

			try {
				log.info(brazilJSON.getCanonicalPath());
				// Read the file from the specified path
				json = parser.parse(new FileReader(brazilJSON
						.getCanonicalPath()));
				//log.info(fileName);
				//log.info(json);
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
		}
		return json;
	}

	/**
	 * Loads the Geo JSON file for the specified region name and type.
	 * 
	 * @param region
	 *            the name of the geo json file to load
	 * @param type
	 *            the type of the region to load. This determines the path of
	 *            the file.
	 * @return GeoJSON object
	 */
	public static Object loadGeoJSON(String region, String type) {
		String path = "";
		if (region != null && type != null) {
			String file = region.toLowerCase();

			if (type.equals(JSON_REGION))
				path = "/CIAT 2015/resources/script/" + file + ".json";
			// brazilJSON = new File("/CIAT 2015/resources/script/" + file +
			// ".json");
			else if (type.equals(JSON_STATES))
				path = "/CIAT 2015/resources/script/" + region + "_states.json";
			else if (type.equals(JSON_MUNICIPIOS))
				path = "/CIAT 2015/resources/script/" + region
						+ "_municipios.json";
			else if (type.equals(JSON_BOUNDARY))
				path = "/CIAT 2015/resources/script/" + region
						+ "_boundary.json";
		}
		return readJSON(path);
	}

	/**
	 * Loads the TPE JSON based on the selected region and map result TPE
	 * 
	 * @param crop
	 *            the selected map option TPE
	 * @param region
	 *            the selected region (Latin America, Colombia or Brazil)
	 * @param cultivar
	 *            the selected crop cultivar
	 * @param map
	 *            the type of map (tpe, stability or area)
	 * @return JSON object
	 */
	public static Object readJSON(String crop, String region, String cultivar,
			String map) {
		String path = "";
		if (region != null && crop != null) {
			region = region.toLowerCase();
			crop = crop.toLowerCase();
			cultivar = cultivar.toLowerCase();
			path = "/CIAT 2015/resources/script/" + region + "_" + crop + "_"
					+ cultivar + "_" + map + ".json";
			log.info(path);
		}
		return readJSON(path);
	}

	/**
	 * Loads the JSON based on the selected region and map (Soil and climate)
	 * 
	 * @param map
	 *            the selected climate or soil map option
	 * @param region
	 *            the selected region (Latin America, Colombia or Brazil)
	 * @return JSON object
	 */
	public static Object readJSON(String map, String region) {
		String path = "";
		if (region != null && map != null) {
			region = region.toLowerCase();
			map = map.toLowerCase();
			path = "/CIAT 2015/resources/script/" + region + "_" + map
					+ ".json";
		}
		return readJSON(path);
	}
}
