package com.ansa.configuration.xauth;

import com.ansa.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.jdbc.support.incrementer.SybaseAnywhereMaxValueIncrementer;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest("server.port:0")
public class UserXAuthTokenControllerTest {


    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void test() throws Exception {
        mockMvc.perform(post("/auth/register").param("username", "username").param("password", "password")).
                andExpect(status().isOk());

//        mockMvc.perform(post("/auth/register").param("username", "username").param("password", "password")).
//                andExpect(status().isBadRequest());


        String token = "{\"token\":\"user:1448824793239:4eef76246e7b9a18fa2695e9b3c20c4e\"}";

        mockMvc.perform(post("/auth/authenticate").param("username", "username").param("password", "password"))
                .andExpect(status().isOk());


        mockMvc.perform(post("/auth/authenticate").param("username", "username").param("password", "pass"))
                .andExpect(status().isBadRequest());
    }
}
