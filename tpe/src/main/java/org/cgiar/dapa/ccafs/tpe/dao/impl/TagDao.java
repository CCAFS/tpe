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

import java.util.List;

import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cgiar.dapa.ccafs.tpe.dao.ITagDao;
import org.cgiar.dapa.ccafs.tpe.entity.Post;
import org.cgiar.dapa.ccafs.tpe.entity.Tag;
import org.cgiar.dapa.ccafs.tpe.exception.PlatformException;

/**
 * This class implements the methods defined in the tag dao interface.
 * 
 * @author NMATOVU
 *
 */
@SuppressWarnings("unchecked")
public class TagDao extends GenericDao<Tag, Integer> implements ITagDao {

	public TagDao() {
		super(Tag.class);

	}

	private Log log = LogFactory.getLog(this.getClass());

	@Override
	public List<Tag> getTags(boolean enabled) {
		StringBuffer q = new StringBuffer("from " + entityClass.getName())
				.append(" r where r.enabled =:enabled");
		Query query = entityManager.createQuery(q.toString());
		query.setParameter("enabled", enabled);

		return query.getResultList();
	}

	@Override
	public void addTag(String name, String url, Integer weight, Boolean enabled)
			throws PlatformException {
		Tag tag;
		try {
			// First make sure if the tag with the specified name does not
			// exists in the database.
			tag = this.findTagByName(name);
			if (tag == null)
				// Persist the new tag
				this.entityManager.persist(new Tag(name, url, weight, enabled));

			// Update its posts.
			tag.getPosts().add(new Post(name, url, tag));
			this.entityManager.merge(tag);

		} catch (Exception e) {
			log.error(e.getMessage());
			throw new PlatformException("Could not persist or update the tag: "
					+ name + " into the database.", e);
		}
	}

	@Override
	public Tag findTagByName(String name) {

		StringBuffer q = new StringBuffer("from " + entityClass.getName())
				.append(" r where r.name =:name");
		Query query = entityManager.createQuery(q.toString());
		query.setParameter("name", name);

		return (Tag) query.getSingleResult();
	}

	@Override
	public List<Post> getTagPosts(String tag) {

		StringBuffer q = new StringBuffer("from " + Post.class.getName())
				.append(" r where r.tag.name =:tag");
		Query query = entityManager.createQuery(q.toString());
		query.setParameter("tag", tag);

		return query.getResultList();
	}
}
