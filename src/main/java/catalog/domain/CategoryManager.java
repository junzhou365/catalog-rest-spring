package catalog.domain;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import catalog.domain.Category;
import catalog.domain.HibernateUtil;

public class CategoryManager {
	
    public Category getCategory(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Category category = (Category) session.get(Category.class, id);
        session.getTransaction().commit();
        return category;
    }
    
    public Category updateCategory(Category category, boolean update) {
    	category.setDatetime(new Date());
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
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
    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    	session.beginTransaction();
    	Category category = (Category)session.get(Category.class, id);
    	if (category != null) {
    		session.delete(category);
    		session.flush();
    	}
    	session.getTransaction().commit();
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