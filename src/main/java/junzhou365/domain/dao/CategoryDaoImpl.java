package junzhou365.domain.dao;

import java.util.Date;
import java.util.List;

import junzhou365.user.model.User;
import junzhou365.user.service.MyUserDetailsService;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import junzhou365.domain.model.Category;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;


public class CategoryDaoImpl implements CategoryDao {	
	private final static Logger log = Logger.getLogger(CategoryDaoImpl.class.getName());
	
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private MyUserDetailsService myUserDetailsService;

	public void setMyUserDetailsService(MyUserDetailsService myUserDetailsService) {
		this.myUserDetailsService = myUserDetailsService;
	}

	public Category getCategory(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Category category = (Category) session.get(Category.class, id);
        session.getTransaction().commit();
        return category;
    }

    public Category updateCategory(Category category, boolean update) {
		User currUser = myUserDetailsService.getCurrentUser();

		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		if (update && isTheAuthor(category)) {
			category.setDatetime(new Date());
			session.update(category);
		}
		// create
		else if(!update && currUser != null) {
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

	private boolean isTheAuthor(Category category) {
		Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user instanceof UserDetails
				&&  category.getUser() != null
				&& ((UserDetails) user).getUsername().equals(category.getUser().getUsername());
	}
}