package junzhou365.user.dao;

import junzhou365.user.model.User;

public interface UserDao {
	
	User findByUserName(String username);
	
	User updateUser(User user, boolean update);

}