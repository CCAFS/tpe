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

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.cgiar.dapa.ccafs.tpe.entity.Region;
import org.cgiar.dapa.ccafs.tpe.service.ITPEService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

/**
 * This is the base action that provides the core methods that will be utilized
 * by other action classes in the system
 * 
 * @author NMATOVU
 *
 */
public abstract class BaseAction extends ActionSupport implements Preparable,
		ServletContextAware {

	private static final long serialVersionUID = 3706037816101380217L;
	@SuppressWarnings("unused")
	private Log log = LogFactory.getLog(this.getClass());
	protected static final String TPE = "tpe";
	protected static final String SOIL = "soil";
	protected static final String CLIMATE = "climate";
	protected static final String CULTIVARS = "cultivars";
	protected static final String SUBREGIONS = "subregions";
	protected static final String YEARS = "years";
	private String path;
	protected Region region;
	/**
	 * The TPE Service
	 */
	protected ITPEService tpeService;
	protected Integer regionId;

	public BaseAction() {
		super();
	}

	@Override
	public void setServletContext(ServletContext arg0) {
		this.path = arg0.getRealPath("/");
	}

	@Override
	public void prepare() {

	}

	@Override
	public String execute() {
		return Action.SUCCESS;
	}

	public String getVirtualDirectory() {
		ActionContext ac = ActionContext.getContext().getActionInvocation()
				.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) ac
				.get(ServletActionContext.HTTP_REQUEST);
		String contextPath = request.getContextPath();
		return request == null ? null : (contextPath.equals("/") ? null
				: contextPath);
	}

	public String getHostname() {
		ActionContext ac = ActionContext.getContext().getActionInvocation()
				.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) ac
				.get(ServletActionContext.HTTP_REQUEST);
		String host = request.getHeader("Host");
		return host;
	}

	public ITPEService getTpeService() {
		return tpeService;
	}

	public void setTpeService(ITPEService tpeService) {
		this.tpeService = tpeService;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Region getRegion() {
		return this.region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

}
