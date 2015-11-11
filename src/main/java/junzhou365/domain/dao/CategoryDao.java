package junzhou365.domain.dao;

import java.util.List;

import junzhou365.domain.model.Category;

public interface CategoryDao {
	public Category getCategory(Long id);
    
    public Category updateCategory(Category category, boolean update);
    
    public void deleteCategory(Long id);

	public List<Category> getAllCategories();
}
