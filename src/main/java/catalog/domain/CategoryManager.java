package catalog.domain;

import java.util.List;

import org.hibernate.Session;

import catalog.domain.Category;
import catalog.domain.HibernateUtil;

public class CategoryManager {
    public void addCategory(String name) {
    	Category category = new Category(name);
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(category);
        session.getTransaction().commit();
    }

    public Category findCategory(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Category category = (Category) session.get(Category.class, id);
        session.getTransaction().commit();
        return category;
    }
    
    public void deleteCategory(Long id) {
    	//remove all items in the category
    	// TODO: Add delete_itmes
    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    	session.beginTransaction();
    	Category category = (Category)session.get(Category.class, id);
    	if (category != null) {
    		
    		session.delete(category);
    		session.flush();
    		session.getTransaction().commit();
    		
    	}
    }

    @SuppressWarnings("rawtypes")
	private List listCategories() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List result = session.createQuery("from Category").list();
        session.getTransaction().commit();
        return result;
    }
    
    @SuppressWarnings("rawtypes")
	public void listAll() {
    	List categories = listCategories();
    	for (int i = 0; i < categories.size(); i++) {
    		Category c = (Category) categories.get(i);
    		System.out.println("Category: id:" + 
    				c.getId() + " " + c.getName() + " " + c.getDatetime());
    	}
    }
}