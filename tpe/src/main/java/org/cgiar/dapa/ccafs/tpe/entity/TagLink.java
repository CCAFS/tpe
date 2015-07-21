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

package org.cgiar.dapa.ccafs.tpe.entity;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The tag class represents the link associated with the particular tag
 * 
 * @author nmatovu
 *
 */
@Entity
@Table(name = "link")
@AttributeOverride(name = "id", column = @Column(name = "link_id"))
public class TagLink extends BaseEntity {

	private static final long serialVersionUID = 8231154063724326254L;

	/**
	 * The name of the category
	 */
	private String title;

	/**
	 * The url of the link
	 */
	private String url;

	/**
	 * The number of url clicks.
	 */
	private Integer clicks;
	/**
	 * The date on which the tag was created
	 */
	private Date createdOn;
	/**
	 * The tag associated with this link
	 */
	private Tag tag;

	@Column(name = "created_on")
	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Column(name = "title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "url")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "clicks")
	public Integer getClicks() {
		return clicks;
	}

	public void setClicks(Integer clicks) {
		this.clicks = clicks;
	}

	@ManyToOne(targetEntity = Tag.class)
	@JoinColumn(name = "tag_id", referencedColumnName = "tag_id")
	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

}
