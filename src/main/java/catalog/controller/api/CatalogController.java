package catalog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import catalog.domain.dao.CategoryDao;
import catalog.domain.dao.CategoryDaoImpl;
import catalog.domain.model.Category;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

@RestController
@RequestMapping(value="/catalog/api")
public class CatalogController {
	final Logger log = Logger.getLogger(CatalogController.class.getName());
	
	private CategoryDao categoryDao;
	@Autowired
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	
	@RequestMapping(value="/categories/{id}", method=RequestMethod.GET)
	public Category getCategory(@PathVariable Long id) {
		Category category = categoryDao.getCategory(id);
		log.debug(category.getDatetime());
		return category;
	}
	
	@RequestMapping(value="/categories", method=RequestMethod.GET)
	public List<Category> getAllCategories() {
		List<Category> categories = categoryDao.getAllCategories();
		return categories;
	}
	
	@RequestMapping(value="/categories", method=RequestMethod.POST)
	public Category createCategory(@RequestBody Category category) {
		return categoryDao.updateCategory(category, false);
	}
	
	@RequestMapping(value="/categories/{id}", method=RequestMethod.PUT)
	public Category updateCategory(@RequestBody Category category) {
		return categoryDao.updateCategory(category, true);
	}
	
	@RequestMapping(value="/categories/{id}", method=RequestMethod.DELETE)
	public void deleteCategory(@PathVariable Long id) {
		categoryDao.deleteCategory(id);
	}
}
