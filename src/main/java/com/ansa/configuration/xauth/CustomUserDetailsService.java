package com.ansa.configuration.xauth;

import com.ansa.usermanagement.USER_ROLE;
import com.ansa.usermanagement.User;
import com.ansa.usermanagement.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@Service
public class CustomUserDetailsService extends SpringBeanAutowiringSupport implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUser(username);
        if (user == null)
            throw new UsernameNotFoundException(username);
        UserDetails details = new SimpleUserDetails(user.getUsername(), user.getEncodedPassword(), USER_ROLE.GENERAL.name());

        return details;
    }
}
