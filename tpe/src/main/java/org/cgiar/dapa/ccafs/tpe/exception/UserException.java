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

import org.cgiar.dapa.ccafs.tpe.entity.User;

/**
 * The user exception class
 * 
 * @author nmatovu
 *
 */
public class UserException extends RuntimeException {

	private static final long serialVersionUID = 2028031597519534931L;

	public UserException(User user) {
		this(user.getUsername());
	}

	public UserException(String username) {
		super("Could not find user with username [" + username + "]");
	}

	public UserException(User user, Throwable cause) {
		this(user == null ? "username" : user.getUsername(), cause);
	}

	public UserException(String username, Throwable cause) {
		super("Could not find user for username [" + username + "]", cause);
	}

}
