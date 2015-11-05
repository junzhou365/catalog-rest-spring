package junzhou365.config;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfigurer extends WebMvcConfigurerAdapter{
	private final static Logger log = Logger.getLogger(WebConfigurer.class.getName());

	public final static String imageFolerPath = "/home/vagrant/public_images/";
	public final static String imageResourcePath = "file:///home/vagrant/public_images/";
	public final static String SHAREDRESOURCES = "file:///home/vagrant/shared_resources/";
	public final static String CATALOG_ANGULARJS_LOCATION = "file:///home/vagrant/webapp_catalog/app/";
	public final static String LIANPAGE_ANGULARJS_LOCATION = "file:///C:/Users/Julian/Codes/Projects/lianpage/app/";
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler("/vendor/**").addResourceLocations(SHAREDRESOURCES);
		registry.addResourceHandler("/**").addResourceLocations(LIANPAGE_ANGULARJS_LOCATION);
		registry.addResourceHandler("/catalog/**").addResourceLocations(CATALOG_ANGULARJS_LOCATION);
		registry.addResourceHandler("/catalog/images/**").addResourceLocations(imageResourcePath);
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("forward:/index.html");
		registry.addViewController("/catalog/").setViewName("forward:/catalog/index.html");
	    registry.addRedirectViewController("/catalog", "/catalog/");
	    
	}
}
