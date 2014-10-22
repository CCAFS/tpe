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
package org.cgiar.dapa.ccafs.tpe.service.impl;

import java.util.List;

import org.cgiar.dapa.ccafs.tpe.dao.ICropDao;
import org.cgiar.dapa.ccafs.tpe.entity.Crop;
import org.cgiar.dapa.ccafs.tpe.service.ITPEService;

/**
 * This class implements the methods defined in the ITPEService interface
 * 
 * @author NMATOVU
 *
 */
public class TPEService implements ITPEService {
	private ICropDao cropDao;

	public void setCropDao(ICropDao cropDao) {
		this.cropDao = cropDao;
	}

	@Override
	public List<Crop> getAllCrops() {

		return cropDao.getAll();
	}
}
