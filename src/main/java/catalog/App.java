package catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import catalog.domain.Category;
import catalog.domain.CategoryManager;

import org.apache.log4j.Logger;


@SpringBootApplication
public class App {
	private final static Logger log = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
    	CategoryManager cm = new CategoryManager();
    	Category c = cm.findCategory((long)1);
    	if (c == null)
    		cm.addCategory("Test");
        SpringApplication.run(App.class, args);
    }
}
