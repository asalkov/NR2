package com.ansa.usermanagement;

public interface UserService {
    User registerUser(String username, String password);

    User getUserByToken(String token);

    User getUserByUserName(String username);

    User findUser(String userName);
}
