package com.ansa.usermanagement;

import com.ansa.usermanagement.storage.UserInfo;
import com.ansa.usermanagement.storage.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    private static List<User> users = new ArrayList<User>();

    private static Map<String, User> usernameToUserMap = new HashMap<String, User>();
    static {
        User user1 = new User("user", "user", USER_ROLE.GENERAL);
        User user2 = new User("admin", "admin", USER_ROLE.ADMIN);

        users.add(user1);
        users.add(user2);

        usernameToUserMap.put(user1.getUsername(), user1);
        usernameToUserMap.put(user2.getUsername(), user2);


    }

    private Map<String, User> activeUserMap = new HashMap<String, User>();

    @Resource
    private UserInfoRepository userInfoRepository;


    @Override
    public User registerUser(String username, String password) {
        User user = new User(username, password, USER_ROLE.GENERAL);

        String encodedPassword = new BCryptPasswordEncoder().encode(password);
        user.setEncodedPassword(encodedPassword);

        //UserInfo userInfo = new UserInfo(username, encodedPassword);

        //userInfoRepository.save(userInfo);

        usernameToUserMap.put(user.getUsername(), user);
        return user;
    }


    @Override
    public User getUserByToken(String token) {
        return activeUserMap.get(token);
    }

    @Override
    public User getUserByUserName(String username){
        return usernameToUserMap.get(username);
    }

    @Override
    public User findUser(String userName) {
        return usernameToUserMap.get(userName);
    }
}
