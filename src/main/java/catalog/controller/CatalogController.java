package catalog.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import catalog.domain.Category;
import catalog.domain.CategoryManager;

import org.apache.log4j.Logger;

@RestController
public class CatalogController {
	private CategoryManager cmng = new CategoryManager();
	final Logger log = Logger.getLogger(CatalogController.class.getName());
	
	@RequestMapping(value="/categories/{id}", method=RequestMethod.GET)
	public Category getCategory(@PathVariable Long id) {
		Category category = cmng.findCategory(id);
		return category;
	}
	
	@RequestMapping(value="/categories", method=RequestMethod.POST)
	public void createCategory(@RequestBody String name) {
		cmng.addCategory(name);
	}
}
