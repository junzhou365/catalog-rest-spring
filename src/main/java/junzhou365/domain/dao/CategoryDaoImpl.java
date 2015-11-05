package junzhou365.domain.dao;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import junzhou365.domain.model.Category;

public class CategoryDaoImpl implements CategoryDao {	
	private final static Logger log = Logger.getLogger(CategoryDaoImpl.class.getName());
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
    public Category getCategory(Long id) {
    	log.debug(sessionFactory);
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Category category = (Category) session.get(Category.class, id);
        session.getTransaction().commit();
        return category;
    }
    
    public Category updateCategory(Category category, boolean update) {
    	category.setDatetime(new Date());
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        if (update)
        	session.update(category);
        else
        	session.save(category);
        session.getTransaction().commit();
        return category;
    }
    
    public void deleteCategory(Long id) {
    	//remove all items in the category using cascade
    	Session session = sessionFactory.getCurrentSession();
    	session.beginTransaction();
    	Category category = (Category)session.get(Category.class, id);
    	if (category != null) {
    		session.delete(category);
    		session.flush();
    	}
    	session.getTransaction().commit();
    }

	public List<Category> getAllCategories() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        @SuppressWarnings("unchecked")
		List<Category> result = session.createQuery("from Category").list();
        session.getTransaction().commit();
        return result;
    }
	
	public void init() {
		System.out.println("\n");
		System.out.println("*************** CategoryDaoImpl is created! ***************");
		System.out.println("\n");
		
		System.out.println("CategoryDao Factory exists: ");
		System.out.println(sessionFactory.getCurrentSession() != null);
		System.out.println("\n");
	}
	
	public void shutdown() {
		System.out.println("\n");
		System.out.println("*************** CategoryDaoImpl is shutting down! ***************");
		System.out.println("\n");
	}
}