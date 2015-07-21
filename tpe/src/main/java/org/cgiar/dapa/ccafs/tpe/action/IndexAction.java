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

import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends BaseAction {

	private static final long serialVersionUID = 1886907782804805164L;

	public String execute() {

		return ActionSupport.SUCCESS;
	}

	public String documentation() {

		// Retrieve all the platform tags
		// tags = tpeService.getAllTags();

		return ActionSupport.SUCCESS;
	}

	public String resources() {

		return ActionSupport.SUCCESS;
	}

	public String workshops() {

		return ActionSupport.SUCCESS;
	}

	public String project() {
		return ActionSupport.SUCCESS;
	}

	public String tutorials() {
		return ActionSupport.SUCCESS;
	}

	public String scientific() {
		return ActionSupport.SUCCESS;
	}

}
