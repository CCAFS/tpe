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
package org.cgiar.dapa.ccafs.tpe.exception;

/**
 * The TPE platform exception
 * 
 * @author NMATOVU
 *
 */
public class TPEPlatformException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2073986648025832654L;

	public TPEPlatformException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TPEPlatformException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public TPEPlatformException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public TPEPlatformException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public TPEPlatformException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
