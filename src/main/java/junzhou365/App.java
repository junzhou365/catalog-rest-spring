package junzhou365;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.FilterType;

@Configuration
//@ComponentScan
@EnableAutoConfiguration
@ComponentScan(basePackages ="junzhou365", includeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*Config") )
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
//        SpringApplication.run(App.class, args);
    }
}
