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

import java.io.Serializable;
import java.util.List;

import org.cgiar.dapa.ccafs.tpe.util.Constants;

/**
 * Defines a generic data access object that is extended by all the DAOs in the
 * system.
 * 
 * @author NMATOVU
 *
 * @param <T>
 * @param <K>
 */
public interface IGenericDao<T, K extends Serializable> extends Constants{

	/**
	 * Gets the object or record using specified primary key
	 * 
	 * @param id
	 *            the primary key (id) of the object or record to retrieve from
	 *            the database
	 * @return object or record corresponding to the specified primary key (id)
	 */
	T getById(K id);

	/**
	 * Saves or adds the specified object or record to the database. Handles
	 * both insert as well as update
	 * 
	 * @param entity
	 *            the object,entity or record to save into the database
	 * @return managed copy of the original object or record.
	 */
	T addOrMerge(T entity)  ;

	/**
	 * Creates a new entity
	 * 
	 * @param entity
	 * @return the newly created object or record
	 */
	T create(T entity);

	/**
	 * Updates the specified record or entity
	 * 
	 * @param entity
	 * @return the updated record
	 */
	T update(T entity);

	/**
	 * Deletes the object or record that corresponds to the specified primary
	 * key (id)
	 * 
	 * @param id
	 *            the primary key of the object to delete
	 */
	void delete(K id);

	/**
	 * Gets the record count of the records that corresponds to the specified
	 * filter.
	 * 
	 * @param filter
	 *            the record that acts as a filter
	 * @return the record count
	 */
	Integer getCount(T filter);

	/**
	 * Returns the boolean value that indicates whether the record(s) that
	 * corresponds to the specified primary key (id) exists in the database.
	 * 
	 * @param id
	 *            the primary key of the record
	 * @return true or false
	 */
	Boolean exists(K id);

	/**
	 * Gets the list of all objects or records of this type from the database
	 * 
	 * @return list of this objects or records; the list can be empty
	 */
	List<T> getAll();

}
