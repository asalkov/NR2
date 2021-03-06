package com.ansa.usermanagement.storage;

import com.ansa.usermanagement.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class UserInfo {

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private String token;
    private Date lastLogin;

    public UserInfo(){

    }

    public UserInfo(User user){
        this.username = user.getUsername();
        this.password = user.getEncodedPassword();
    }

    public UserInfo(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public User getUser() {
        User user = new User();
        user.setUsername(username);
        user.setEncodedPassword(password);
        return user;
    }
}
