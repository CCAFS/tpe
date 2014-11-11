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

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cgiar.dapa.ccafs.tpe.util.TestUtil;

import com.opensymphony.xwork2.ActionSupport;

/**
 * The action for generating GeoJson data
 * 
 * @author NMATOVU
 *
 */
public class GeoJsonAction extends BaseGeoJsonAction {

	private static final long serialVersionUID = -6473893732734090515L;
	private Log log = LogFactory.getLog(this.getClass());
	private Map<String, Object> collections;

	public String execute() {
		log.info("About to generate GeoJson data.");
		// For testing purposes only
		collections = TestUtil.getFeatures();
		// collections = TestUtil.getFeatureCollection();
		return ActionSupport.SUCCESS;
	}

	public Map<String, Object> getCollections() {
		return collections;
	}

	public void setCollections(Map<String, Object> collections) {
		this.collections = collections;
	}

}
