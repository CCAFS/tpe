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
package org.cgiar.dapa.ccafs.tpe.jqgrid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ColNames {

	// private static final String ID = "Id";
	private static final String COUNTRY = "Country";
	private static final String STATE = "State";
	private static final String MUNICIPALITY = "Municipality";
	private static final String STATION = "Station";
	private static final String JAN = "Jan";
	private static final String FEB = "Feb";
	private static final String MAR = "Mar";
	private static final String APR = "Apr";
	private static final String MAY = "May";
	private static final String JUN = "Jun";
	private static final String JUL = "Jul";
	private static final String AUG = "Aug";
	private static final String SEP = "Sep";
	private static final String OCT = "Oct";
	private static final String NOV = "Nov";
	private static final String DEC = "Dec";

	private static final String PH = "PH";
	private static final String CLAY = "Clay";
	private static final String SAND = "Sand";
	private static final String SILT = "Silt";
	private static final String BULKY_DENSITY = "Bulky Density";
	private static final String ASM = "Available Soil Moisture";
	private static final String ASM_WP = "Available Soil Moisture at Wilting Point";
	private static final String ASM_FC = "Available Soil Moisture at Field Capacity";
	private static final String ORG_CARBON = "Organic Carbon";
	private static final String DEPTH = "Depth";
	private static final String TAXONOMY = "Taxonomy";
	private static final String CATION_EXCHANGE = "Cation Exchange";
	private static final String LON = "Longitude";
	private static final String LAT = "Latitude";

	public static List<String> climateNames() {
		return new ArrayList<String>(Arrays.asList(COUNTRY, STATE,
				MUNICIPALITY, STATION, JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG,
				SEP, OCT, NOV, DEC, LON, LAT));
	}

	public static List<String> soilNames() {
		return new ArrayList<String>(Arrays.asList(COUNTRY, STATE,
				MUNICIPALITY, STATION, PH, CLAY, SAND, SILT, BULKY_DENSITY,
				ASM, ASM_WP, ASM_FC, ORG_CARBON, DEPTH, TAXONOMY,
				CATION_EXCHANGE, LON, LAT));
	}

	// public static List<String> regionNames() {
	// return new ArrayList<String>(Arrays.asList(ID, NAME, TYPE, ISO));
	// }

}
