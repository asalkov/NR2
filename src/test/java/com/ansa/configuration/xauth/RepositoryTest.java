package com.ansa.configuration.xauth;

import com.ansa.Application;
import com.ansa.usermanagement.storage.UserInfo;
import com.ansa.usermanagement.storage.UserInfoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest("server.port:0")
public class RepositoryTest {

    @Resource
    UserInfoRepository userInfoRepository;


    @Test
    public void test(){
        UserInfo userInfo = new UserInfo("a", "b");
        userInfo.setToken("t");

        UserInfo userInfo1 = new UserInfo("a1", "b1");
        userInfo1.setToken("t1");

        userInfoRepository.save(userInfo);
        userInfoRepository.save(userInfo1);

        UserInfo loaded = userInfoRepository.findByToken("t");

        System.out.println(loaded.getUsername());

        System.out.println(userInfoRepository.findByToken("t1").getUsername());

        System.out.println(userInfoRepository.findByUsername("a2"));
    }
}
