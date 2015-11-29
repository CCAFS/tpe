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
import javax.persistence.Transient;

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
	private String name;

	/**
	 * The url link associated with the platform tag
	 */
	private String url;
	/**
	 * The tag count. Set the default weight to 16px;
	 */
	private Integer weight = 16;
	/**
	 * The number of tag clicks. Set the default clicks to zero.
	 */
	private Integer clicks = 0;
	/**
	 * The date on which the tag was created
	 */
	private Date createdOn = new Date();

	/**
	 * The boolean value that indicates whether the tag will be shown or not. By
	 * default the tag is enabled.
	 */
	private Boolean enabled = true;

	/**
	 * The list of url links for the particular tag
	 */
	private List<Post> posts = new ArrayList<Post>();

	public Tag() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tag(String tagName, String tagUrl, Integer tagWeight, Boolean enabled) {
		super();
		this.name = tagName;
		this.url = tagUrl;
		this.weight = tagWeight;
		this.enabled = enabled;
		this.posts = new ArrayList<Post>();
	}

	public Tag(String tagName, String tagUrl, Integer tagWeight,
			Boolean enabled, List<Post> posts) {
		super();
		this.name = tagName;
		this.url = tagUrl;
		this.weight = tagWeight;
		this.enabled = enabled;
		if (this.posts != null)
			this.posts.addAll(posts);
		else
			this.posts = posts;
	}

	@Column(name = "tag_name")
	public String getName() {
		return name;
	}

	public void setName(String tagName) {
		this.name = tagName;
	}

	@Column(name = "tag_url")
	public String getUrl() {
		return url;
	}

	public void setUrl(String tagUrl) {
		this.url = tagUrl;
	}

	@Column(name = "tag_weight")
	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer tagWeight) {
		this.weight = tagWeight;
	}

	@Column(name = "tag_clicks")
	public Integer getTagClicks() {
		return clicks;
	}

	public void setTagClicks(Integer tagClicks) {
		this.clicks = tagClicks;
	}

	@Column(name = "created_on")
	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "tag", cascade = CascadeType.ALL)
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	@Column(name = "enabled")
	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Transient
	public Integer getPostCount() {
		if (posts != null && !posts.isEmpty())
			return posts.size();
		return 0;
	}

	@Transient
	public String getStatus() {
		if (enabled != null)
			if (enabled)
				return "Yes";
		return "No";
	}

	@Override
	public String toString() {

		return name + " (" + getPostCount() + ")";
	}

}
