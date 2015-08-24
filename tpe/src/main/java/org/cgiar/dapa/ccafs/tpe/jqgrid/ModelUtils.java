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

@SuppressWarnings("unused")
public class ModelUtils {

	// private static final String ID = "id";
	private static final String CENTER = "center";
	private static final boolean FALSE = false;
	private static final boolean TRUE = true;
	private static final String COUNTRY = "country";
	private static final String STATE = "state";
	private static final String MUNICIPALITY = "municipality";
	private static final String STATION = "station";
	private static final String JAN = "jan";
	private static final String FEB = "feb";
	private static final String MAR = "mar";
	private static final String APR = "apr";
	private static final String MAY = "may";
	private static final String JUN = "jun";
	private static final String JUL = "jul";
	private static final String AUG = "aug";
	private static final String SEP = "sep";
	private static final String OCT = "oct";
	private static final String NOV = "nov";
	private static final String DEC = "dec";
	private static final String LEFT = "left";
	private static final String RIGHT = "right";
	private static final String PH = "ph";
	private static final String CLAY = "clay";
	private static final String SAND = "sand";
	private static final String SILT = "silt";
	private static final String BULKY_DENSITY = "bulkyDensity";
	private static final String ASM = "asm";
	private static final String ASM_WP = "asmWiltPoint";
	private static final String ASM_FC = "asmFieldCapacity";
	private static final String ORG_CARBON = "organicCarbon";
	private static final String DEPTH = "depth";
	private static final String TAXONOMY = "taxonomy";
	private static final String CATION_EXCHANGE = "cationExchange";
	private static final String LON = "longitude";
	private static final String LAT = "latitude";

	public static List<ColModel> climateModel() {
		return new ArrayList<ColModel>(Arrays.asList(new ColModel(COUNTRY,
				COUNTRY, 120, LEFT, TRUE, TRUE, TRUE, TRUE), new ColModel(
				STATE, STATE, 120, LEFT, TRUE, TRUE, TRUE, TRUE), new ColModel(
				MUNICIPALITY, MUNICIPALITY, 120, LEFT, TRUE, TRUE, TRUE, TRUE),
				new ColModel(STATION, STATION, 120, LEFT, TRUE, TRUE, TRUE,
						TRUE), new ColModel(JAN, JAN, 80, CENTER, TRUE, TRUE,
						TRUE, TRUE), new ColModel(FEB, FEB, 80, CENTER, TRUE,
						TRUE, TRUE, TRUE), new ColModel(MAR, MAR, 80, CENTER,
						TRUE, TRUE, TRUE, TRUE), new ColModel(APR, APR, 80,
						CENTER, TRUE, TRUE, TRUE, TRUE), new ColModel(MAY, MAY,
						80, CENTER, TRUE, TRUE, TRUE, TRUE), new ColModel(JUN,
						JUN, 80, CENTER, TRUE, TRUE, TRUE, TRUE), new ColModel(
						JUL, JUL, 80, CENTER, TRUE, TRUE, TRUE, TRUE),
				new ColModel(AUG, AUG, 80, CENTER, TRUE, TRUE, TRUE, TRUE),
				new ColModel(SEP, SEP, 80, CENTER, TRUE, TRUE, TRUE, TRUE),
				new ColModel(OCT, OCT, 80, CENTER, TRUE, TRUE, TRUE, TRUE),
				new ColModel(NOV, NOV, 80, CENTER, TRUE, TRUE, TRUE, TRUE),
				new ColModel(DEC, DEC, 80, CENTER, TRUE, TRUE, TRUE, TRUE),
				new ColModel(LON, LON, 80, CENTER, TRUE, TRUE, TRUE, TRUE),
				new ColModel(LAT, LAT, 80, CENTER, TRUE, TRUE, TRUE, TRUE)

		));
	}

	public static List<ColModel> soilModel() {
		return new ArrayList<ColModel>(Arrays.asList(new ColModel(COUNTRY,
				COUNTRY, 120, LEFT, TRUE, TRUE, TRUE, TRUE), new ColModel(
				STATE, STATE, 120, LEFT, TRUE, TRUE, TRUE, TRUE), new ColModel(
				MUNICIPALITY, MUNICIPALITY, 120, LEFT, TRUE, TRUE, TRUE, TRUE),
				new ColModel(STATION, STATION, 120, LEFT, TRUE, TRUE, TRUE,
						TRUE), new ColModel(PH, PH, 80, CENTER, TRUE, TRUE,
						TRUE, TRUE), new ColModel(CLAY, CLAY, 80, CENTER, TRUE,
						TRUE, TRUE, TRUE), new ColModel(SAND, SAND, 80, CENTER,
						TRUE, TRUE, TRUE, TRUE), new ColModel(SILT, SILT, 80,
						CENTER, TRUE, TRUE, TRUE, TRUE), new ColModel(
						BULKY_DENSITY, BULKY_DENSITY, 80, CENTER, TRUE, TRUE,
						TRUE, TRUE), new ColModel(ASM, ASM, 80, CENTER, TRUE,
						TRUE, TRUE, TRUE), new ColModel(ASM_WP, ASM_WP, 80,
						CENTER, TRUE, TRUE, TRUE, TRUE), new ColModel(ASM_FC,
						ASM_FC, 80, CENTER, TRUE, TRUE, TRUE, TRUE),
				new ColModel(ORG_CARBON, ORG_CARBON, 80, CENTER, TRUE, TRUE,
						TRUE, TRUE), new ColModel(DEPTH, DEPTH, 80, CENTER,
						TRUE, TRUE, TRUE, TRUE), new ColModel(TAXONOMY,
						TAXONOMY, 80, CENTER, TRUE, TRUE, TRUE, TRUE),
				new ColModel(CATION_EXCHANGE, CATION_EXCHANGE, 80, CENTER,
						TRUE, TRUE, TRUE, TRUE), new ColModel(LON, LON, 80,
						CENTER, TRUE, TRUE, TRUE, TRUE), new ColModel(LAT, LAT,
						80, CENTER, TRUE, TRUE, TRUE, TRUE)));
	}
	// public static List<ColModel> regionModel() {
	// return new ArrayList<ColModel>(Arrays.asList(new ColModel(ID, ID, 80,
	// CENTER, TRUE, TRUE, TRUE, TRUE, TRUE), new ColModel(NAME, NAME,
	// 200, LEFT, TRUE, TRUE, TRUE, TRUE), new ColModel(TYPE, TYPE,
	// 120, LEFT, TRUE, TRUE, TRUE, TRUE), new ColModel(ISO, ISO, 120,
	// CENTER, TRUE, TRUE, TRUE, TRUE)));
	// }

}
