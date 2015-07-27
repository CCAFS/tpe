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

package org.cgiar.dapa.ccafs.tpe.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.cgiar.dapa.ccafs.tpe.dao.IUserDao;
import org.cgiar.dapa.ccafs.tpe.entity.Role;
import org.cgiar.dapa.ccafs.tpe.exception.UserException;
import org.cgiar.dapa.ccafs.tpe.exception.UserNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * This class is used by spring action to authenticate and authorize user from
 * platform database with the defined user roles
 * 
 * @author nmatovu
 *
 */
public class PlatformUserService implements UserDetailsService {
	private IUserDao userDao;

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		org.cgiar.dapa.ccafs.tpe.entity.User user = userDao
				.findUserByUsername(username);

		try {
			if (user == null)
				// throw new UsernameNotFoundException("Oops!");
				throw new UserNotFoundException(username);

		} catch (UserException e) {
			throw new UserException(username, e);
		}

		return queryUser(user);
	}

	private User queryUser(org.cgiar.dapa.ccafs.tpe.entity.User userEntity) {
		// convert model user to spring security user
		String username = userEntity.getUsername();
		String password = userEntity.getPassword();
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		User springUser = new User(username, password, enabled,
				accountNonExpired, credentialsNonExpired, accountNonLocked,
				getGrantedAuthorities(userEntity.getRoles()));
		return springUser;
	}

	/**
	 * Wraps string roles to SimpleGrantedAuthority objects
	 * 
	 * @param roles
	 *            String of roles
	 * @return list of granted authorities
	 */
	public static List<GrantedAuthority> getGrantedAuthorities(List<Role> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return authorities;
	}

}
