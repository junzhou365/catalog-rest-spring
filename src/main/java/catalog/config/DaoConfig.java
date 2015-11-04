package catalog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import catalog.user.dao.UserDao;
import catalog.user.dao.UserDaoImpl;
import catalog.user.dao.UserRoleDao;
import catalog.user.dao.UserRoleDaoImpl;
import catalog.domain.dao.CategoryDao;
import catalog.domain.dao.CategoryDaoImpl;
import catalog.domain.dao.ImageDao;
import catalog.domain.dao.ImageDaoImpl;
import catalog.domain.dao.ItemDao;
import catalog.domain.dao.ItemDaoImpl;

@Configuration
public class DaoConfig {

	@Autowired
	private DatabaseConfig databaseConfig;
	
	@Autowired
	private SecurityConfig securityConfig;
	
	@Bean
	public UserDao userDao() {
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		userDaoImpl.setSessionFactory(databaseConfig.sessionFactory());
		userDaoImpl.setUserRoleDao(userRoleDao());
		userDaoImpl.setPasswordEncoder(securityConfig.passwordEncoder());
		return userDaoImpl;
		
	}
	
	@Bean
	public UserRoleDao userRoleDao() {
		UserRoleDaoImpl userRoleDaoImpl = new UserRoleDaoImpl();
		userRoleDaoImpl.setSessionFactory(databaseConfig.sessionFactory());
		return userRoleDaoImpl;
		
	}
	
	@Bean(initMethod="init", destroyMethod = "shutdown")
	public CategoryDao categoryDao() {
		CategoryDaoImpl categoryDaoImpl = new CategoryDaoImpl();
		categoryDaoImpl.setSessionFactory(databaseConfig.sessionFactory());
		categoryDaoImpl.setUserDao(userDao());
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
