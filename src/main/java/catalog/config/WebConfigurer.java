package catalog.config;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfigurer extends WebMvcConfigurerAdapter{
	private final static Logger log = Logger.getLogger(WebConfigurer.class.getName());
	public final static String imageFolerPath = "C:/Users/Julian/Codes/Projects/public_images/";
	public final static String imageResourcePath = "file:///C:/Users/Julian/Codes/Projects/public_images/";
	public final static String ANGULARJS_LOCATION = "file:///C:/Users/Julian/Codes/Projects/webapp_catalog/app/";
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		
		registry.addResourceHandler("/**").addResourceLocations(ANGULARJS_LOCATION);
		registry.addResourceHandler("/images/**").addResourceLocations(imageResourcePath);
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
	    registry.addViewController("/").setViewName("forward:/index.html");
	}
}
