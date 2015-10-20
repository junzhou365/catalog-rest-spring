package catalog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

import catalog.user.service.UserDetailsServiceImpl;

@Configuration
public class ServiceConfig {
	@Autowired
	private DaoConfig daoConfig;
	
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetailsServiceImpl userDetailsServiceImpl = new UserDetailsServiceImpl();
		userDetailsServiceImpl.setUserDao(daoConfig.userDao());
		return userDetailsServiceImpl;
	}
}
