//package com.mall.filter;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
//import org.springframework.core.io.buffer.DataBuffer;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import reactor.core.publisher.Mono;
//
//public class TokenValidateGatewayFilterFactory extends AbstractGatewayFilterFactory<TokenValidateGatewayFilterFactory.Config> {
//
//    private static Logger logger = LoggerFactory.getLogger(TokenValidateGatewayFilterFactory.class);
//
//    @Override
//    public GatewayFilter apply(Config config) {
//        return (exchange, chain) -> {
//            logger.info("TokenValidateGatewayFilterFactory...");
//            String token = exchange.getRequest().getHeaders().getFirst("Authorization");
//            //校验token的合法性
//            boolean tokenValidated = true;
//            if (tokenValidated) {
//                // 令牌合法，继续访问，可以进行一些处理，如：添加头信息，参数等
//                ServerHttpRequest.Builder builder = exchange.getRequest().mutate();
//                //比如，根据令牌获得用户信息，userId
//                builder.header("ch-userId", "用户Id");
//                return chain.filter(exchange.mutate().request(builder.build()).build());
//            }
//            //令牌不合法
//            ServerHttpResponse response = exchange.getResponse();
//            //设置headers
//            HttpHeaders httpHeaders = response.getHeaders();
//            httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
//            httpHeaders.add("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
//            //过滤器中跨域需要自己处理
//            //设置body
//            DataBuffer bodyDataBuffer = response.bufferFactory().wrap("123456".getBytes());
//            return response.writeWith(Mono.just(bodyDataBuffer));
//        };
//    }
//
//    public static class Config {
//
//    }
//}