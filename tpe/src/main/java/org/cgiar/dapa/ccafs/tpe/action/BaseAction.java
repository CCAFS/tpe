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

import org.apache.log4j.Logger;
import org.cgiar.dapa.ccafs.tpe.service.ITPEService;

import com.opensymphony.xwork2.ActionSupport;

/**
 * This is the base action that provides the core methods that will be utilized
 * by other action classes in the system
 * 
 * @author NMATOVU
 *
 */
public abstract class BaseAction extends ActionSupport {

	private static final long serialVersionUID = 3706037816101380217L;
	@SuppressWarnings("unused")
	private Logger log = Logger.getLogger(this.getClass());
	/**
	 * The TPE Service
	 */
	protected ITPEService tpeService;

	public ITPEService getTpeService() {
		return tpeService;
	}

	public void setTpeService(ITPEService tpeService) {
		this.tpeService = tpeService;
	}

}
