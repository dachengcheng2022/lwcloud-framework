//package com.mall.filter;
//
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
//import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
//import reactor.core.publisher.Mono;
//
///**
// * my name is istpos ,2019/8/26.
// * gateway过滤
// */
////RoutePredicateFactory
////@Configuration
////@Component
//public class TokenTestFilterFactory extends AbstractNameValueGatewayFilterFactory {
//
//
//    @Override
//    public GatewayFilter apply(NameValueConfig config) {
//        return (exchange, chain) -> {
//            exchange.getAttributes().put("", System.currentTimeMillis());
//            return chain.filter(exchange).then(
//                    Mono.fromRunnable(() -> {
//                        Long startTime = exchange.getAttribute("");
//                        if (startTime != null) {
//                            StringBuilder sb = new StringBuilder(exchange.getRequest().getURI().getRawPath())
//                                    .append(": ")
//                                    .append(System.currentTimeMillis() - startTime)
//                                    .append("ms");
////                            if (config.isWithParams()) {
////                                sb.append(" params:").append(exchange.getRequest().getQueryParams());
////                            }
//                        }
//                    })
//            );
//        };
//    }
//
//
//}
