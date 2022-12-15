package com.autumn.filter;


import com.alibaba.fastjson2.JSONObject;
import com.autumn.common.RetBiz;
import com.autumn.domain.user.MallUser;
import com.autumn.domain.user.MallUserDetails;
import com.autumn.form.account.TokenForms;
import com.autumn.utils.security.JwtUtils;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

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

//    private final RsaKeyProperties properties;

    private final RSAKey rsaKey;

    public TokenLoginFilter(AuthenticationManager authenticationManager, RSAKey rsaKey) {
        super(DEFAULT_ANT_PATH_REQUEST_MATCHER);
        this.authenticationManager = authenticationManager;
        this.rsaKey = rsaKey;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
//        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

//            MallUser user = JacksonUtils.toBean(request.getInputStream(), MallUser.class);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password,
                    null);
            return authenticationManager.authenticate(authenticationToken);
//        } catch (IOException e) {
////            Map<String, Object> map = Map.of("code", HttpServletResponse.SC_UNAUTHORIZED, "msg", "用户名或者密码错误！");
////            WebUtils.out(response, map);
//
//            throw new UsernameNotFoundException("用户名或者密码错误");
//        }
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) {
        MallUser mallUser = new MallUser();
        MallUserDetails principal = (MallUserDetails) authResult.getPrincipal();
        mallUser.setId(principal.getId());
        String token = JwtUtils.generateToken(mallUser, rsaKey);
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
