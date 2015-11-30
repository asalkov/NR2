package com.ansa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/*
http://jasonwatmore.com/post/2015/03/10/AngularJS-User-Registration-and-Login-Example.aspx
 */
@ComponentScan
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


}
