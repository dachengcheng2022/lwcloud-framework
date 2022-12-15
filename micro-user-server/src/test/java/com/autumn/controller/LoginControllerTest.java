package com.autumn.controller;

import com.autumn.common.RetBiz;
import com.autumn.utils.JacksonUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * <p>
 * HelloControllerTest
 * </p>
 *
 * @author livk
 */
@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {
    @Autowired
    MockMvc mockMvc;

    String token;

//    @BeforeEach
    public void init() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.set("username", "livk");
        params.set("password", "123456");
        MockHttpServletResponse response = mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .params(params))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("code", "200").exists())
                .andReturn().getResponse();
        token = JacksonUtils.toMap(response.getContentAsString(), String.class, String.class).get("data");
    }

    @Test
    void testLogin() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.set("username", "admin");
        params.set("password", "111111");
        MockHttpServletResponse response = mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .params(params))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("status", "1").exists())
                .andReturn().getResponse();
        RetBiz retBiz = JacksonUtils.toBean(response.getContentAsString(), RetBiz.class);
        System.err.println(retBiz.getResult());
    }

//    @Test
//    void testHello() throws Exception {
//        mockMvc.perform(get("/hello")
//                        .header(HttpHeaders.AUTHORIZATION, token))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(content().string("hello"));
//    }

//    @Test
//    void testIndex() throws Exception {
//        mockMvc.perform(get("/index")
//                        .header(HttpHeaders.AUTHORIZATION, token))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(content().string("index"));
//    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
