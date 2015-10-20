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
//        final MySessionImpl factory = new MySessionImpl();
//        factory.setDataSource(dataSource());
//        factory.setHibernateProperties(getHibernateProperties());
//        return factory.getObject();
		System.out.println("\n");
		System.out.println("Create sessionFactory");
		SessionFactory factory = new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
		System.out.println("Factory exists: ");
		System.out.println(factory != null);
//		Session session = factory.getCurrentSession();
//        session.beginTransaction();
//        Category category = (Category) session.get(Category.class, (long)1);
//        session.getTransaction().commit();
//        System.out.println(category.getName());
//		System.out.println("\n");
		return factory;
    }
//	

//	@Bean
//	public DriverManagerDataSource dataSource() {
//	    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
//	    driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
//	    driverManagerDataSource.setUrl("jdbc:postgresql://localhost:5432/testdb");
//	    driverManagerDataSource.setUsername("Julian");
//	    driverManagerDataSource.setPassword("juls");
//	    return driverManagerDataSource;
//	}		
}