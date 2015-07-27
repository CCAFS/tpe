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

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.cgiar.dapa.ccafs.tpe.dao.IGenericDao;
import org.cgiar.dapa.ccafs.tpe.exception.PlatformException;

/**
 * Defines a Base Generic DAO that provides JPA implementation. This class keeps
 * the reference to the <code>EntityManager</code> and provides a default
 * implementation for methods defined in the <code>IGenericDao</code> interface.
 * 
 * @author NMATOVU
 *
 * @param <T>
 * @param <K>
 */
@SuppressWarnings("unchecked")
public abstract class GenericDao<T, K extends Serializable> implements
		IGenericDao<T, K> {
	protected Class<T> entityClass;
	protected EntityManager entityManager;

	/**
	 * Constructor that takes in a class to see which type of entity to persist
	 * 
	 * @param persistentClass
	 *            the class type you'd like to persist
	 */
	public GenericDao(final Class<T> entityClass) {
		this.entityClass = entityClass;

	}

	/**
	 * Gets the <code>EntityManager</code> used by this DAO
	 * 
	 * @return the entityManager used for persistence
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * Sets the <code>EntityManager</code> used by this DAO
	 * 
	 * @param entityManager
	 *            the entityManager to be used for persistence
	 */
	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;

	}

	public Class<T> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public T getById(K id) {

		try {
			T instance = entityManager.find(entityClass, id);
			return instance;
		} catch (RuntimeException re) {

			throw re;
		}

	}

	public T addOrMerge(T entity) throws PlatformException {

		try {
			if (this.entityManager.contains(entity))
				this.entityManager.merge(entity);
			else
				this.entityManager.persist(entity);
		} catch (Exception e) {
			throw new PlatformException("DB exception", e);
		}

		return entity;

	}

	public void delete(K id) {
		this.entityManager.remove(this.entityManager.contains(entityManager
				.find(entityClass, id)) ? entityManager.find(entityClass, id)
				: entityManager.merge(entityManager.find(entityClass, id)));

	}

	public Integer getCount(T filter) {
		return ((Number) this.entityManager.createQuery(
				"SELECT COUNT (*) FROM " + entityClass.getName())
				.getSingleResult()).intValue();
	}

	public Boolean exists(K id) {
		if (this.entityManager.contains(entityManager.find(entityClass, id)))

			return true;

		else

			return false;
	}

	public List<T> getAll() {

		Query q = this.entityManager.createQuery("from "
				+ this.entityClass.getName());
		return q.getResultList();
	}

	public T create(T entity) {
		this.entityManager.persist(entity);
		return entity;
	}

	public T update(T entity) {
		return this.entityManager.merge(entity);

	}

}
