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
package org.cgiar.dapa.ccafs.tpe.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cgiar.dapa.ccafs.tpe.entity.Tag;
import org.cgiar.dapa.ccafs.tpe.entity.TagLink;
import org.cgiar.dapa.ccafs.tpe.exception.TPEException;

import com.opensymphony.xwork2.ActionSupport;

public class TagsAction extends BaseAction {

	private static final long serialVersionUID = -3025365582429269300L;

	@SuppressWarnings("unused")
	private Log log = LogFactory.getLog(this.getClass());

	/**
	 * The list of platform tagsF
	 */
	private List<Tag> tags;
	/**
	 * The map of tags
	 */
	private Map<String, Tag> tagMap;
	/**
	 * The id of the tag used when editing the tag.
	 */
	private Integer id;
	/**
	 * The name of the tag to save into the database
	 */
	private String name;
	/**
	 * The url of the tag
	 */
	private String url;
	/**
	 * The font weight of the tag
	 */
	private Integer weight;
	/**
	 * Boolean value if the tag is enabled or not
	 */
	private Boolean enabled;
	/**
	 * The list of tags
	 */
	private List<TagLink> links = new ArrayList<TagLink>();

	public String execute() {

		// Retrieve all the platform tags
		// TODO Retrieve only the enabled tags
		tags = tpeService.getAllTags();

		return ActionSupport.SUCCESS;
	}

	/**
	 * Adds or saves a new tag into the database
	 * 
	 * @return
	 */
	public String add() {

		// Create a new tag instance

		if (name != null)
			try {
				tpeService.addTag(new Tag(getName(), getUrl(), getWeight(),
						getEnabled()));
			} catch (TPEException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else
			return ActionSupport.INPUT;

		return ActionSupport.SUCCESS;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public Map<String, Tag> getTagMap() {
		return tagMap;
	}

	public void setTagMap(Map<String, Tag> tagMap) {
		this.tagMap = tagMap;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<TagLink> getLinks() {
		return links;
	}

	public void setLinks(List<TagLink> links) {
		this.links = links;
	}

}
