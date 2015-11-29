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

import java.io.File;
import java.sql.Date;
import java.time.LocalDateTime;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.opensymphony.xwork2.ActionSupport;

public class NewsAction extends BaseAction {

	private static final long serialVersionUID = -3837363832614922105L;
	private Log LOG = LogFactory.getLog(this.getClass());
	private String title;
	private String author;
	private Date postedOn;
	private String content;
	private String photoUrl;

	@Override
	public String execute() {
		if (content != null && title != null) {
			try {
				DocumentBuilderFactory docFactory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

				// root elements
				Document doc = docBuilder.newDocument();
				Element rootElement = doc.createElement("platform");
				doc.appendChild(rootElement);

				// news elements
				Element news = doc.createElement("news");
				rootElement.appendChild(news);

				// set attribute to news element
				// Attr attr = doc.createAttribute("id");
				// attr.setValue("1");
				// news.setAttributeNode(attr);
				news.setAttribute("id", "1");

				// title elements
				Element titleOfNews = doc.createElement("title");
				titleOfNews.appendChild(doc.createTextNode(getTitle()));
				news.appendChild(titleOfNews);

				// author elements
				Element authorOfNews = doc.createElement("author");
				authorOfNews.appendChild(doc.createTextNode(getAuthor()));
				news.appendChild(authorOfNews);

				// Date elements
				Element date = doc.createElement("date");
				date.appendChild(doc.createTextNode(LocalDateTime.now()
						.toString()));
				news.appendChild(date);

				// content elements
				Element cont = doc.createElement("content");
				cont.appendChild(doc.createTextNode(getContent()));
				news.appendChild(cont);

				// write the content into xml file
				TransformerFactory transformerFactory = TransformerFactory
						.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File("C:\\opt\resources\tpe.xml"));

				// Output to console for testing
				// StreamResult result = new StreamResult(System.out);

				transformer.transform(source, result);

				System.out.println("File saved!");

			} catch (ParserConfigurationException pce) {
				// e.printStackTrace();
				LOG.error(pce.getMessage());
			} catch (TransformerException tfe) {
				// tfe.printStackTrace();
				LOG.error(tfe.getMessage());
			}

			return ActionSupport.SUCCESS;
		} else
			addActionError("Content and title are required!");
		return ERROR;
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

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

}
