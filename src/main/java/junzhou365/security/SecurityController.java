package junzhou365.security;

import java.security.Principal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import junzhou365.user.dao.UserDao;
import junzhou365.user.model.User;

@RestController
@RequestMapping(value="/catalog")
public class SecurityController {
	final Logger log = Logger.getLogger(SecurityController.class.getName());
	
	private UserDao userDao;
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@RequestMapping("/user")
//	@ResponseBody
	public Principal user(Principal user) {
		return user;
	}
	
	@RequestMapping(value="/signup", method= RequestMethod.POST)
//	public void user(@RequestParam("username") String username, @RequestParam("password") String password) {
	public void user(@RequestBody User user) {
//		User user = new User(username, password, true);
		userDao.updateUser(user, false);
	}
}
