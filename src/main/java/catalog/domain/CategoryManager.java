package catalog.domain;

import java.util.List;

import org.hibernate.Session;

import catalog.domain.Category;
import catalog.domain.HibernateUtil;

public class CategoryManager {
	
    public Category findCategory(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Category category = (Category) session.get(Category.class, id);
        session.getTransaction().commit();
        return category;
    }
    
    public Category createCategory(String name) {
    	Category category = new Category(name);
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(category);
        session.getTransaction().commit();
        return category;
    }

    public Category updateCategory(Category category) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.update(category);
        session.getTransaction().commit();
        return category;
    }
    
    public void deleteCategory(Long id) {
    	//remove all items in the category using cascade
    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    	session.beginTransaction();
    	Category category = (Category)session.get(Category.class, id);
    	if (category != null) {
    		session.delete(category);
    		session.flush();
    		session.getTransaction().commit();
    	}
    }

	public List<Category> getAllCategories() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        @SuppressWarnings("unchecked")
		List<Category> result = session.createQuery("from Category").list();
        session.getTransaction().commit();
        return result;
    }
}