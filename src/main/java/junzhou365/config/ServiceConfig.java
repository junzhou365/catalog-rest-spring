package junzhou365.config;

import junzhou365.user.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

import junzhou365.user.service.MyUserDetailsServiceImpl;

@Configuration
public class ServiceConfig {
	@Autowired
	private DaoConfig daoConfig;
	
	@Bean
	public MyUserDetailsService myUserDetailsService() {
		MyUserDetailsServiceImpl myUserDetailsServiceImpl = new MyUserDetailsServiceImpl();
		myUserDetailsServiceImpl.setUserDao(daoConfig.userDao());
		return myUserDetailsServiceImpl;
	}
}
