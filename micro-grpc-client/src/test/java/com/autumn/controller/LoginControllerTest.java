package com.autumn.controller;

import com.alibaba.fastjson2.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * <p>
 * https://github.com/alibaba/druid/issues/2050
 * </p>
 *
 * @author livk
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class LoginControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Resource
    private PasswordEncoder passwordEncoder;
    String token;

    @BeforeEach
    public void init() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.set("username", "1029842247@qq.com");
        params.set("password", "123456");
        MockHttpServletResponse response = mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .params(params))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("status", "1").exists())
                .andReturn().getResponse();
        token ="Bearer " + JSONObject.parseObject(response.getContentAsString()).getJSONObject("result").getString("access_token");
    }

    @Test
    void testHello() throws Exception {
        mockMvc.perform(get("/api/test/v1/test")
                        .header(HttpHeaders.AUTHORIZATION, token))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string("hello"));
    }

    @Test
    void testIndex() throws Exception {
        mockMvc.perform(get("/index")
                        .header(HttpHeaders.AUTHORIZATION, token))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string("index"));
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
