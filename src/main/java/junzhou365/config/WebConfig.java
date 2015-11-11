package junzhou365.config;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{
	private final static Logger log = Logger.getLogger(WebConfig.class.getName());
	public final static String CATALOG_URL = "/catalog";
	public final static String imageFolerPath = "C:/Users/Julian/Codes/Projects/public_images/";
	public final static String imageResourcePath = "file:///C:/Users/Julian/Codes/Projects/public_images/";
	public final static String SHAREDRESOURCES = "C:/Users/Julian/Codes/Projects/shared-resources/";
	public final static String CATALOG_ANGULARJS_LOCATION = "file:///C:/Users/Julian/Codes/Projects/webapp_catalog/app/";
	public final static String LIANPAGE_ANGULARJS_LOCATION = "file:///C:/Users/Julian/Codes/Projects/lianpage/app/";

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler("/vendor/**").addResourceLocations(SHAREDRESOURCES);
		registry.addResourceHandler("/**").addResourceLocations(LIANPAGE_ANGULARJS_LOCATION);
		registry.addResourceHandler(CATALOG_URL + "/**").addResourceLocations(CATALOG_ANGULARJS_LOCATION);
		registry.addResourceHandler(CATALOG_URL + "/images/**").addResourceLocations(imageResourcePath);
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("forward:/index.html");
		registry.addViewController(CATALOG_URL + "/").setViewName("forward:" + CATALOG_URL + "/index.html");
	    registry.addRedirectViewController(CATALOG_URL, CATALOG_URL + "/");
	    
	}
}
