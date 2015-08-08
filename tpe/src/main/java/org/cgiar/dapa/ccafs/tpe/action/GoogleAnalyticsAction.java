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
package org.cgiar.dapa.ccafs.tpe.action;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.Action;

public class GoogleAnalyticsAction {
	private static final Log LOG = LogFactory
			.getLog(GoogleAnalyticsAction.class);
	private ArrayList<String> trackers;

	/**
	 * Can use a comma separated list of ids
	 * 
	 * @param googleIds
	 */
	public void setSiteId(String googleIds) {
		this.trackers = new ArrayList<String>();
		if (googleIds == null || googleIds.trim().length() == 0) {
			LOG.warn("Google site ID not specified. Not enabling.");
			return;
		}
		LOG.info("Google site IDs: " + googleIds);
		String[] split = googleIds.split(",");
		for (String trackerId : split) {
			if (trackerId == null || trackerId.trim().length() == 0) {
				continue;
			}
			LOG.info("Registering " + trackerId);
			this.trackers.add(trackerId);
		}
	}

	/**
	 * @return the trackers
	 */
	public ArrayList<String> getTrackers() {
		return this.trackers;
	}

	public String execute() {
		return Action.SUCCESS;
	}
}
