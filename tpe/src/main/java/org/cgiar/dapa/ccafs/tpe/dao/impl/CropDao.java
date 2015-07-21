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
package org.cgiar.dapa.ccafs.tpe.dao.impl;

import org.cgiar.dapa.ccafs.tpe.dao.ICropDao;
import org.cgiar.dapa.ccafs.tpe.entity.Crop;

/**
 * This class implements the methods that are defined in the ICropDao interface.
 * 
 * @author NMATOVU
 *
 */
public class CropDao extends GenericDao<Crop, Integer> implements ICropDao {
	// private Logger log = Logger.getLogger(this.getClass());

	public CropDao() {
		super(Crop.class);

	}

}
