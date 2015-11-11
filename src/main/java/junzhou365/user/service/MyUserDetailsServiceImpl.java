package junzhou365.user.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import junzhou365.user.dao.UserDao;
import junzhou365.user.model.UserRole;


public class MyUserDetailsServiceImpl implements MyUserDetailsService {
	final Logger log = Logger.getLogger(MyUserDetailsServiceImpl.class.getName());
	
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Transactional(readOnly=true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		junzhou365.user.model.User user = userDao.findByUserName(username);
		System.out.println(user);
		List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());

		UserDetails userDetails =  buildUserForAuthentication(user, authorities);
		log.debug(userDetails.getPassword());
		return userDetails;
		
	}

	// Converts junzhou365.user.model.User user to
	// org.springframework.security.core.userdetails.User
	private User buildUserForAuthentication(junzhou365.user.model.User user, List<GrantedAuthority> authorities) {
		User authUser = new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
		log.debug(authUser.getUsername() + " " + authUser.getPassword());
		return authUser;
	}

	private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (UserRole userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

		return Result;
	}

	public junzhou365.user.model.User getCurrentUser() {
		// This will close session if it is used inside transaction
		SecurityContext sctx = SecurityContextHolder.getContext();
		if (sctx.getAuthentication() == null)
			return null;
		Object user = sctx.getAuthentication().getPrincipal();
		if (user instanceof UserDetails) {
			return userDao.findByUserName(((UserDetails) user).getUsername());
		}
		return userDao.findByUserName(user.toString());
	}

}