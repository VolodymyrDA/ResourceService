package org.vdoloka.controller;

import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class MainControllerTest extends Test {

    @org.junit.jupiter.api.Test

    @WithMockUser(username="admin",roles={"USER"})
     void givenHomePageURI_ShouldBeTrue() throws Exception {

        MvcResult mvcResult = mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("registration"))
                .andReturn();

        String actualResult = mvcResult.getResponse().getContentAsString();

        assertTrue(actualResult.contains("registration"));
    }
}