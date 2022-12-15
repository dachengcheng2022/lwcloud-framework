package com.autumn.filter;


import com.autumn.domain.user.MallUser;
import com.autumn.utils.WebUtils;
import com.autumn.utils.security.JwtUtils;
import com.autumn.vo.security.PayloadUser;
import com.nimbusds.jose.jwk.RSAKey;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * <p>
 * TokenVerifyFilter
 * </p>
 *
 * @author livk
 */
@RequiredArgsConstructor
public class TokenVerifyFilter extends OncePerRequestFilter {

    private final RSAKey rsaKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain chain)
            throws IOException, ServletException {
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorization != null && authorization.startsWith("Bearer ")) {
            String token = authorization.replaceFirst("Bearer ", "");
            PayloadUser payloadUser = JwtUtils.parse(token, rsaKey);
            MallUser user = payloadUser.getMallUser();
            if (user != null) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(),
                        user.getPassword(), null);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                chain.doFilter(request, response);
            } else {
//                Map<String, Object> map = Map.of("code", HttpServletResponse.SC_FORBIDDEN, "msg", "缺少用户信息");
                HashMap<Object, Object> data = new HashMap<>();
                data.put("test","test");
                WebUtils.out(response, data);
            }
        } else {
            HashMap<Object, Object> data = new HashMap<>();
            data.put("test2","test2");
            WebUtils.out(response, data);
        }
    }

}
