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

import java.sql.Date;

public class News {
	private String id;
	private String title;
	private String author;
	private Date postedOn;
	private String content;
	private String image;
	private String linkurl;
	private boolean external;

	public News() {
		super();
		// TODO Auto-generated constructor stub
	}

	public News(String title, String author, Date postedOn, String content,
			 String image, String linkurl) {
		super();
		this.title = title;
		this.author = author;
		this.postedOn = postedOn;
		this.content = content;
		this.image = image;
		this.linkurl = linkurl;
	}

	public News(String id, String title, String author, Date postedOn,
			String content, String image, String linkurl) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.postedOn = postedOn;
		this.content = content;
		this.image = image;
		this.linkurl = linkurl;
	}

	public News(String id, String title, String author, Date postedOn,
			String content, String image, String linkurl, boolean external) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.postedOn = postedOn;
		this.content = content;
		this.image = image;
		this.linkurl = linkurl;
		this.external = external;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getPostedOn() {
		return postedOn;
	}

	public void setPostedOn(Date postedOn) {
		this.postedOn = postedOn;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isExternal() {
		return external;
	}

	public void setExternal(boolean external) {
		this.external = external;
	}

	public String getLinkurl() {
		return linkurl;
	}

	public void setLinkurl(String linkurl) {
		this.linkurl = linkurl;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
