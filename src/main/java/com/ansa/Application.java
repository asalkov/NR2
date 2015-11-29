package com.ansa;

import com.ansa.usermanagement.USER_ROLE;
import com.ansa.usermanagement.User;
import com.ansa.usermanagement.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;

@ComponentScan
@EnableAutoConfiguration
public class Application {

    @Autowired
    UserRepository userRepository;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void init(){
        User user = new User("username", "password", USER_ROLE.GENERAL);
        userRepository.save(user);
        Iterable<User> userList = userRepository.findAll();

         userList.forEach(user1 -> System.out.println(user1.getId()));
    }
}
