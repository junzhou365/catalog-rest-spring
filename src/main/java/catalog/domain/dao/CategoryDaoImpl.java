package catalog.domain.dao;

import java.util.Date;
import java.util.List;

import catalog.user.dao.UserDao;
import catalog.user.model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import catalog.domain.model.Category;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class CategoryDaoImpl implements CategoryDao {	
	private final static Logger log = Logger.getLogger(CategoryDaoImpl.class.getName());
	
	private SessionFactory sessionFactory;
	private UserDao userDao;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
    public Category getCategory(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Category category = (Category) session.get(Category.class, id);
        session.getTransaction().commit();
        return category;
    }

    public Category updateCategory(Category category, boolean update) {
		User currUser = getCurrentUser();

		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		if (update && isTheAuthor(category)) {
			category.setDatetime(new Date());
			session.update(category);
		}
		// create
		else if(!update) {
			category.setDatetime(new Date());
			category.setUser(currUser);
//			Hibernate.initialize(category.getUser());
//			Hibernate.initialize(category.getUser().getUserRole());
			session.save(category);
		}
		session.getTransaction().commit();

        return category;
    }

    public void deleteCategory(Long id) {
    	// remove all items in the category using cascade
    	Session session = sessionFactory.getCurrentSession();
    	session.beginTransaction();
    	Category category = (Category)session.get(Category.class, id);
    	if (category != null && isTheAuthor(category)) {
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
	}
	
	public void shutdown() {
	}

	private boolean isTheAuthor(Category category) {
		Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user instanceof UserDetails
				&&  category.getUser() != null
				&& ((UserDetails) user).getUsername().equals(category.getUser().getUsername());
	}

	private User getCurrentUser() {
		// This will close session if it is used inside transaction
		Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (user instanceof UserDetails) {
			return userDao.findByUserName(((UserDetails) user).getUsername());
		}
		return userDao.findByUserName(user.toString());
	}
}