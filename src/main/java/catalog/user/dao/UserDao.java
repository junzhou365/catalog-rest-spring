package catalog.user.dao;

import catalog.user.model.User;

public interface UserDao {
	
	User findByUserName(String username);
	
	User updateUser(User user, boolean update);

}