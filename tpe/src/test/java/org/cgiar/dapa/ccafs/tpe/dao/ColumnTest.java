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

import java.util.LinkedList;
import java.util.List;

import org.cgiar.dapa.ccafs.tpe.chart.Chart;

/**
 * This class tests for the column chart data
 * 
 * @author NMATOVU
 * 
 */
public class ColumnTest extends BaseTest {
	/**
	 * REtrieves the chart column series (data and categories)
	 */
	public void testGetColumnSeries() {
		// The spline chart series
		List<Chart> series = new LinkedList<Chart>();
		Integer scenarioId = 1;
		Integer cultivarId = 1;
		Integer categoryId = 1;
		Integer subregionId = 1;
		String year = "2012";
		Integer swindow = 1;
		series = tpeService.getTPEColumnSeries(subregionId, categoryId,
				scenarioId, cultivarId, year, swindow);
		assertNotNull(series);
		assertEquals(0, series.size());

	}

}
