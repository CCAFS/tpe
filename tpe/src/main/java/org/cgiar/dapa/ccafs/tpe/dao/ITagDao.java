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

import java.util.List;

import org.cgiar.dapa.ccafs.tpe.entity.Tag;
import org.cgiar.dapa.ccafs.tpe.exception.PlatformException;

/**
 * This interface defines platform tag dao methods
 * 
 * @author NMATOVU
 *
 */
public interface ITagDao extends IGenericDao<Tag, Integer> {

	/**
	 * Retrieves all the tags from the database with the specified enabled
	 * value(true or false).;
	 * 
	 * @param enabled
	 *            the boolean value; true if the tag is enabled and false if
	 *            not.
	 * @return tags
	 */
	List<Tag> getTags(boolean enabled);

	/**
	 * Adds a new tag with its associated posts (links) or updates an existing
	 * tag or its post links.
	 * 
	 * @param name
	 *            the name of the tag to add or update
	 * @param url
	 *            the url link of the tag to add or update.
	 * @param weight
	 *            the weight of the tag to add or update (The weight will be
	 *            used in the html font size).
	 * @param enabled
	 *            boolean value, true for enabled and false for disabled one.
	 */
	void addTag(String name, String url, Integer weight, Boolean enabled)
			throws PlatformException;

	/**
	 * Retrieves the tag from the database by the specified name.
	 * 
	 * @param name
	 *            the name of the tag to look for
	 * @return Tag
	 */
	Tag findTagByName(String name);
}
