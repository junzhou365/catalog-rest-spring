package catalog.config;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfigurer extends WebMvcConfigurerAdapter{
	private final static Logger log = Logger.getLogger(WebConfigurer.class.getName());
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		String ANGULARJS_LOCATION = "classpath:/webapp/";
		registry.addResourceHandler("/**").addResourceLocations(ANGULARJS_LOCATION);
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
	    registry.addViewController("/").setViewName("forward:/index.html");
	}
}
