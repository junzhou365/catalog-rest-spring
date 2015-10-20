package catalog.user.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import catalog.user.model.UserRole;

public class UserRoleDaoImpl implements UserRoleDao {
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public UserRole updateRole(UserRole userRole, boolean update) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
        if (update)
        	session.update(userRole);
        else
        	session.save(userRole);
        session.getTransaction().commit();
        return userRole;
		
	}

}
