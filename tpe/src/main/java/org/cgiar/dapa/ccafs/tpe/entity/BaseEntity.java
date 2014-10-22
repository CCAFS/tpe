package org.cgiar.dapa.ccafs.tpe.entity;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

/**
 * This is the base class for all entity classes in the tpe project. All other
 * entity classes inherit this base class.
 * 
 * @author NMATOVU
 *
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4140206572083144774L;

}
