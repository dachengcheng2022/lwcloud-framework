//package com.autumn.filter;
//
//
//import com.autumn.RedisComponent;
//import com.autumn.common.SystemConstant;
//import com.autumn.constant.ErrorStatus;
//import com.autumn.constant.RedisConstant;
//import com.autumn.exception.custom.CommonException;
//import com.autumn.utils.security.JwtUtils;
//import com.autumn.vo.security.PayloadUser;
//import com.autumn.vo.security.TokenUserDetails;
//import com.nimbusds.jose.jwk.RSAKey;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.http.HttpHeaders;
//import org.springframework.lang.NonNull;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.time.LocalDateTime;
//
///**
// * <p>
// * TokenVerifyFilter
// * </p>
// *
// * @author livk
// */
//@RequiredArgsConstructor
//@Slf4j
//public class TokenVerifyFilter extends OncePerRequestFilter {
//
//    private final RSAKey rsaKey;
//
//    private final RedisComponent redisComponent;
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain chain)
//            throws IOException, ServletException {
//        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
//        if (authorization != null && authorization.startsWith("Bearer ")) {
//            String token = authorization.replaceFirst("Bearer ", "");
//            PayloadUser payloadUser = JwtUtils.parse(token, rsaKey);
//            TokenUserDetails user = payloadUser.getTokenUserDetails();
//            String tokenCache = redisComponent.opsForHashGet(RedisConstant.USER_TOKEN_CACHE, user.getUserId().toString(), "");
//            if(!StringUtils.equalsIgnoreCase(tokenCache,token)){
//                log.error("token has not match redis {}",token);
//                request.setAttribute("auth.error", new CommonException(ErrorStatus.USER_NEED_LOGIN_ERROR));
//                request.getRequestDispatcher(SystemConstant.ERROR_CONTROLLER_PATH).forward(request, response);
//                return;
//            }
//
//            if(payloadUser.getExpirTime().isAfter(LocalDateTime.now())){
//                log.error("token has been expir {}",token);
//                request.setAttribute("auth.error", new CommonException(ErrorStatus.USER_NEED_LOGIN_ERROR));
//                request.getRequestDispatcher(SystemConstant.ERROR_CONTROLLER_PATH).forward(request, response);
//                return;
//            }
//            if (user != null) {
//                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user,
//                        user.getPassword(), user.getAuthorities());
//                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//                chain.doFilter(request, response);
//            } else {
//                request.setAttribute("auth.error", new CommonException(ErrorStatus.USER_NEED_LOGIN_ERROR));
//                request.getRequestDispatcher(SystemConstant.ERROR_CONTROLLER_PATH).forward(request, response);
//
//            }
//        } else {
//            request.setAttribute("auth.error", new CommonException(ErrorStatus.USER_NEED_LOGIN_ERROR));
//            request.getRequestDispatcher(SystemConstant.ERROR_CONTROLLER_PATH).forward(request, response);
//        }
//    }
//
//}
