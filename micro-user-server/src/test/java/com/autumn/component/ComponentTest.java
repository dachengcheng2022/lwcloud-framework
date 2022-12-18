package com.autumn.component;

import com.autumn.utils.security.JwtUtils;
import com.autumn.vo.security.TokenUserDetails;
import com.nimbusds.jose.jwk.RSAKey;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class ComponentTest {

    @Autowired
    private RSAKey rsaKey;

    @Test
    public void testJWT(){
        TokenUserDetails tokenUserDetails = new TokenUserDetails();
        tokenUserDetails.setUserId(1);
        tokenUserDetails.setLoginName("dacheng");
        String s = JwtUtils.generateToken(tokenUserDetails, rsaKey);
        System.err.println(s);
    }
}
