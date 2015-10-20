package catalog.user.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

import catalog.user.model.User;
import catalog.user.model.UserRole;

public class UserDaoImpl implements UserDao {
	
	private SessionFactory sessionFactory;
	
	private UserRoleDao userRoleDao;
	
	private PasswordEncoder passwordEncoder;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void setUserRoleDao(UserRoleDao userRoleDao) {
		this.userRoleDao = userRoleDao;
	}
	
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@SuppressWarnings("unchecked")
	public User findByUserName(String username) {
		User user = null;
		List<User> users = new ArrayList<User>();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		users = session.createQuery("from User where username=?").setParameter(0, username)
				.list();
		if (users.size() > 0) {
			user = users.get(0);
			Hibernate.initialize(user.getUserRole());
		}
		session.getTransaction().commit();
		return user;
	}
	
	public User updateUser(User user, boolean update) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
        if (update)
        	session.update(user);
        else
        	session.save(user);
        session.getTransaction().commit();
        UserRole userRole = new UserRole(user, "USER");
        userRoleDao.updateRole(userRole, update);
        return user;
	}

}