package junzhou365.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import junzhou365.user.dao.UserDao;
import junzhou365.user.dao.UserDaoImpl;
import junzhou365.user.dao.UserRoleDao;
import junzhou365.user.dao.UserRoleDaoImpl;
import junzhou365.domain.dao.CategoryDao;
import junzhou365.domain.dao.CategoryDaoImpl;
import junzhou365.domain.dao.ImageDao;
import junzhou365.domain.dao.ImageDaoImpl;
import junzhou365.domain.dao.ItemDao;
import junzhou365.domain.dao.ItemDaoImpl;


@Configuration
public class DaoConfig {

	@Autowired
	private DatabaseConfig databaseConfig;
	
	@Autowired
	private SecurityConfig securityConfig;

	@Autowired
	private ServiceConfig serviceConfig;
	
	@Bean
	public UserDao userDao() {
		System.out.println("USERDAO START");
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		userDaoImpl.setSessionFactory(databaseConfig.sessionFactory());
		userDaoImpl.setUserRoleDao(userRoleDao());
		userDaoImpl.setPasswordEncoder(securityConfig.passwordEncoder());
		System.out.println("USERDAO EMD");
		return userDaoImpl;
		
	}
	
	@Bean
	public UserRoleDao userRoleDao() {
		System.out.println("USEROLEDAO START");
		UserRoleDaoImpl userRoleDaoImpl = new UserRoleDaoImpl();
		userRoleDaoImpl.setSessionFactory(databaseConfig.sessionFactory());
		System.out.println("USEROLEDAO END");
		return userRoleDaoImpl;
		
	}
	
	@Bean
	public CategoryDao categoryDao() {
		CategoryDaoImpl categoryDaoImpl = new CategoryDaoImpl();
		categoryDaoImpl.setSessionFactory(databaseConfig.sessionFactory());
		categoryDaoImpl.setMyUserDetailsService(serviceConfig.myUserDetailsService());
		return categoryDaoImpl;
		
	}
	
	@Bean
	public ItemDao itemDao() {
		ItemDaoImpl itemDaoImpl = new ItemDaoImpl();
		itemDaoImpl.setSessionFactory(databaseConfig.sessionFactory());
		itemDaoImpl.setImageDao(imageDao());
		return itemDaoImpl;
		
	}
	
	@Bean
	public ImageDao imageDao() {
		ImageDaoImpl imageDaoImpl = new ImageDaoImpl();
		imageDaoImpl.setSessionFactory(databaseConfig.sessionFactory());
		return imageDaoImpl;
		
	}
}
