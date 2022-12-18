package org.vdoloka.controller.ViewController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@SpringBootTest()
@AutoConfigureMockMvc
class MainControllerTest {
    @Autowired
    protected WebApplicationContext context;

    protected MockMvc mvc;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }
    @Test

//    @WithMockUser(username="admin",roles={"USER"})
     void givenHomePageURI_ShouldBeTrue() throws Exception {

        MvcResult mvcResult = mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("mainpage"))
                .andReturn();

        String actualResult = mvcResult.getResponse().getContentAsString();

        assertTrue(actualResult.contains("bg.jpg"));
    }
}