package com.ansa.usermanagement;

import com.ansa.usermanagement.USER_ROLE;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private USER_ROLE userRole;
    private String encodedPassword;

    public User(){
        this.username = "default";
    }

    public User(String username, String password, USER_ROLE userRole){
        this.username = username;
        this.userRole = userRole;
    }

    public void setEncodedPassword(String encodedPassword){
        this.encodedPassword = encodedPassword;

    }
    public String getEncodedPassword(){
        return encodedPassword;
    }
    public String getUsername(){
        return username;
    }

    public USER_ROLE getUserRole(){
        return userRole;
    }

    public void cleanPassword(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
