package com.ansa.configuration.xauth;

import com.ansa.Application;
import com.ansa.usermanagement.User;
import com.ansa.usermanagement.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest("server.port:0")
public class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    public void test(){
        User newUser = userService.registerUser("test", "default");

        User foundUser = userService.findUser("test");

        Assert.assertEquals(newUser.getUsername(), foundUser.getUsername());
       // Assert.assertEquals(newUser.getEncodedPassword(), foundUser.getEncodedPassword());

        String test = "test";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encoded1 = encoder.encode(test);
        System.out.println(encoded1);
        String encoded2 = encoder.encode(test);
        System.out.println(encoded2);

        System.out.println(encoder.matches("test", encoded1));
        System.out.println(encoder.matches("test", encoded2));

    }

}
