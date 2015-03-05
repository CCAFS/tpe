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

import org.cgiar.dapa.ccafs.tpe.dao.ITagDao;
import org.cgiar.dapa.ccafs.tpe.entity.Tag;

/**
 * This class implements the methods defined in the tag dao interface.
 * 
 * @author NMATOVU
 *
 */
// @SuppressWarnings("unchecked")
public class TagDao extends GenericDao<Tag, Integer> implements ITagDao {

	public TagDao() {
		super(Tag.class);
		// TODO Auto-generated constructor stub
	}

}