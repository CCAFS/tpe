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
package org.cgiar.dapa.ccafs.tpe.dao;

import junit.framework.TestCase;

import org.cgair.dapa.ccafs.tpe.util.Constants;
import org.cgiar.dapa.ccafs.tpe.service.ITPEService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * This is the TPE base DAO test class
 * 
 * @author NMatovu
 * 
 */
public abstract class BaseTest extends TestCase implements Constants{

	private static final String BEAN_SERVICE = "tpeService";
	private ApplicationContext context;
	protected ITPEService tpeService;

	public BaseTest() {
		super();
		context = new ClassPathXmlApplicationContext("/applicationContext.xml");
		tpeService = (ITPEService) context.getBean(BEAN_SERVICE);
	}

}
