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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The tag class represents the platform tag post
 * 
 * @author nmatovu
 *
 */
@Entity
@Table(name = "tag")
@AttributeOverride(name = "id", column = @Column(name = "tag_id"))
public class Tag extends BaseEntity {

	private static final long serialVersionUID = -6027450661067580161L;

	/**
	 * The name of the category
	 */
	private String tagName;

	/**
	 * The url link associated with the platform tag
	 */
	private String tagUrl;
	/**
	 * The tag count. Set the default weight to 16px;
	 */
	private Integer tagWeight = 16;
	/**
	 * The number of tag clicks. Set the default clicks to zero.
	 */
	private Integer tagClicks = 0;
	/**
	 * The date on which the tag was created
	 */
	private Date createdOn = new Date();

	/**
	 * The boolean value that indicates whether the tag will be shown or not.
	 * By default the tag is enabled.
	 */
	private Boolean enabled = true;
	/**
	 * The list of url links for the particular tag
	 */
	private List<TagLink> links;

	public Tag() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tag(String tagName, String tagUrl, Integer tagWeight, Boolean enabled) {
		super();
		this.tagName = tagName;
		this.tagUrl = tagUrl;
		this.tagWeight = tagWeight;
		this.enabled = enabled;
		this.links = new ArrayList<TagLink>();
	}

	public Tag(String tagName, String tagUrl, Integer tagWeight,
			Boolean enabled, List<TagLink> links) {
		super();
		this.tagName = tagName;
		this.tagUrl = tagUrl;
		this.tagWeight = tagWeight;
		this.enabled = enabled;
		this.links = links;
	}

	@Column(name = "tag_name")
	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	@Column(name = "tag_url")
	public String getTagUrl() {
		return tagUrl;
	}

	public void setTagUrl(String tagUrl) {
		this.tagUrl = tagUrl;
	}

	@Column(name = "tag_weight")
	public Integer getTagWeight() {
		return tagWeight;
	}

	public void setTagWeight(Integer tagWeight) {
		this.tagWeight = tagWeight;
	}

	@Column(name = "tag_clicks")
	public Integer getTagClicks() {
		return tagClicks;
	}

	public void setTagClicks(Integer tagClicks) {
		this.tagClicks = tagClicks;
	}

	@Column(name = "created_on")
	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tag", cascade = CascadeType.ALL)
	public List<TagLink> getLinks() {
		return links;
	}

	public void setLinks(List<TagLink> links) {
		this.links = links;
	}

	@Column(name = "enabled")
	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

}
