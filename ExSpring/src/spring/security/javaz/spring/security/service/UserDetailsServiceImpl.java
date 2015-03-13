package javaz.spring.security.service;

import java.util.ArrayList;
import java.util.List;

import javaz.common.logger.CustomLogger;
import javaz.hibernate.dao.UserDAO;
import javaz.hibernate.entity.Permission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author SUCCESS\tungo
 * @date 2014-07-31
 */
@Component(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	private CustomLogger log = CustomLogger.getLogger(this.getClass());

	@Autowired
	private UserDAO userDAO;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		javaz.hibernate.entity.User user = userDAO.findByUsername(username);
		
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		
		for (Permission perm : user.getGroup().getPermissions()) {
			final String authority = perm.getCode();
			
			grantList.add(new GrantedAuthority() {
				private static final long serialVersionUID = 1L;
				@Override
				public String getAuthority() {
					return authority;
				}
			});
		}
		
		UserDetails userDetails = new User(username, user.getPassword(), user.getActive(), true, true, true, grantList);
		return userDetails;
	}

}
