package catalog.domain.dao;

import java.util.List;

import catalog.domain.model.Category;

public interface CategoryDao {
	public Category getCategory(Long id);
    
    public Category updateCategory(Category category, boolean update);
    
    public void deleteCategory(Long id);

	public List<Category> getAllCategories();
	
	public void init();
	
	public void shutdown();
}
