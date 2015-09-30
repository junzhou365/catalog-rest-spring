package catalog.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import catalog.domain.Category;
import catalog.domain.CategoryManager;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

@RestController
public class CatalogController {
	private CategoryManager cmng = new CategoryManager();
	final Logger log = Logger.getLogger(CatalogController.class.getName());
	
	@RequestMapping(value="/categories/{id}", method=RequestMethod.GET)
	public Category getCategory(@PathVariable Long id) {
		Category category = cmng.findCategory(id);
		log.debug(category.getDatetime());
		return category;
	}
	
	@RequestMapping(value="/categories", method=RequestMethod.GET)
	public List<Category> getAllCategories() {
		List<Category> categories = cmng.getAllCategories();
		return categories;
	}
	
	@RequestMapping(value="/categories", method=RequestMethod.POST)
	public Category createCategory(@RequestBody Category category) {
		Category newCategory = cmng.createCategory(category.getName());
		return newCategory;
	}
	
	@RequestMapping(value="/categories/{id}", method=RequestMethod.PUT)
	public Category updateCategory(@RequestBody Category category) {
		category.setDatetime(new Date());
		Category updatedCategory = cmng.updateCategory(category);
		return updatedCategory;
	}
	
	@RequestMapping(value="/categories/{id}", method=RequestMethod.DELETE)
	public void deleteCategory(@PathVariable Long id) {
		cmng.deleteCategory(id);
	}
}
