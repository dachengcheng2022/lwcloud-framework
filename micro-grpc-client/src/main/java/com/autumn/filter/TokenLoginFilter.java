package com.autumn.filter;


import com.alibaba.fastjson2.JSONObject;
import com.autumn.RedisComponent;
import com.autumn.common.RetBiz;
import com.autumn.constant.RedisConstant;
import com.autumn.vo.security.TokenUserDetails;
import com.autumn.form.account.TokenForms;
import com.autumn.utils.security.JwtUtils;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <p>
 * TokenLoginFilter
 * </p>
 *
 * @author livk
 */
public class TokenLoginFilter extends AbstractAuthenticationProcessingFilter {

    private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher("/login",
            "POST");

    private final AuthenticationManager authenticationManager;

    private final RSAKey rsaKey;

    private RedisComponent redisComponent;

    public TokenLoginFilter(AuthenticationManager authenticationManager,RSAKey rsaKey,
                            RedisComponent redisComponent) {
        super(DEFAULT_ANT_PATH_REQUEST_MATCHER);
        this.authenticationManager = authenticationManager;
        this.rsaKey = rsaKey;
        this.redisComponent = redisComponent;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password,
                    null);
            return authenticationManager.authenticate(authenticationToken);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) {
        TokenUserDetails principal = (TokenUserDetails) authResult.getPrincipal();
        String token = JwtUtils.generateToken(principal, rsaKey);
//        将token放入redis缓存当中
        redisComponent.opsForHashPut(RedisConstant.USER_TOKEN_CACHE,principal.getUserId().toString(),token);
        response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer ".concat(token));
        try {
            PrintWriter out = response.getWriter();
            RetBiz retBiz = new RetBiz();
            TokenForms tokenForms = new TokenForms();
            tokenForms.setAccessToken(token);
            retBiz.setResult(tokenForms);
            out.print(JSONObject.toJSONString(retBiz));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
