package junzhou365.user.service;

import junzhou365.user.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by Julian on 11/11/2015.
 */
public interface MyUserDetailsService extends UserDetailsService {
    public User getCurrentUser();
}
