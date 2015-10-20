package catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import catalog.config.DatabaseConfig;
import catalog.config.HibernateUtil;
import catalog.config.DaoConfig;
import catalog.config.SecurityConfig;
import catalog.config.ServiceConfig;
import catalog.domain.model.Category;
import catalog.user.model.User;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@Configuration
@EnableAutoConfiguration
@ComponentScan({ "catalog.*" })
public class App {
	private final static Logger log = Logger.getLogger(App.class.getName());
	
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(App.class);
        ApplicationContext ctx2 = app.run(args);
        
        System.out.println("----------------------Let's inspect the beans provided by Spring Boot:----------------------");
        String[] beanNames = ctx2.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }
}
