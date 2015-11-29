package com.ansa.configuration.xauth;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by andrey on 29.11.2015.
 */
public class DevTest {

    @Test
    public void test(){
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
