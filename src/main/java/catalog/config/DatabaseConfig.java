package catalog.config;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
//import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import catalog.domain.model.Category;


@Configuration
public class DatabaseConfig {

	@Bean
    public SessionFactory sessionFactory() {
		System.out.println("\n");
		System.out.println("Create sessionFactory");
		SessionFactory factory = new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
		System.out.println("Factory exists: ");
		System.out.println(factory != null);
		return factory;
    }
//	

	@Bean
	public DriverManagerDataSource dataSource() {
	    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
	    driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
	    driverManagerDataSource.setUrl("jdbc:postgresql://localhost:5432/catalog2");
	    driverManagerDataSource.setUsername("julian");
	    driverManagerDataSource.setPassword("juls");
	    return driverManagerDataSource;
	}		
}