package com.autumn.config.security;

import com.autumn.RedisComponent;
import com.autumn.config.security.exception.AutumnAuthExceptionEntryPoint;
import com.autumn.config.security.exception.WalletAccessDeniedHandler;
import com.autumn.config.security.oauth2.AuthorityDetailsService;
import com.autumn.vo.security.TokenUserDetails;
import com.autumn.filter.IntegrationAuthenticationFilter;
import com.autumn.filter.TokenLoginFilter;
import com.autumn.filter.TokenVerifyFilter;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.jackson2.SecurityJackson2Modules;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ClientSettings;
import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;
import org.springframework.security.oauth2.server.authorization.jackson2.OAuth2AuthorizationServerJackson2Module;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.annotation.Resource;
import java.nio.file.attribute.UserPrincipal;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.List;
import java.util.UUID;

@Configuration
public class SecurityConfig {


    @Resource
    private WalletAccessDeniedHandler walletAccessDeniedHandler;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private IntegrationAuthenticationFilter integrationAuthenticationFilter;

    @Resource
    private AutumnAuthExceptionEntryPoint autumnAuthExceptionEntryPoint;

    @Resource
    private AuthorityDetailsService authorityDetailsService;

    @Resource
    private RedisComponent redisComponent;
    //
//    @Bean
//    @Order(1)
//    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http)
//            throws Exception {
//        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
//
//        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class);
//
//        http.exceptionHandling((exceptions) -> {
//                    exceptions.accessDeniedHandler(walletAccessDeniedHandler);
//                    exceptions.authenticationEntryPoint(autumnAuthExceptionEntryPoint);
////                    exceptions.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"));
//
//                })
//                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
//                .addFilterBefore(integrationAuthenticationFilter, CsrfFilter.class);
//
////        http.oauth2ResourceServer().accessDeniedHandler()
////        http.addFilterAt(integrationAuthenticationFilter,OAuth2AuthorizationEndpointFilter.class);
//
////        http.addFilterBefore(integrationAuthenticationFilter);
//        return http.build();
//    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, RSAKey rsaKey,
                                                   AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {

        return http.csrf()
                .disable()
                .authorizeHttpRequests()
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(new TokenLoginFilter(authenticationManagerBuilder.getObject(), rsaKey,redisComponent),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(new TokenVerifyFilter(rsaKey,redisComponent), BasicAuthenticationFilter.class)
                .logout()
                .logoutUrl("/oauth2/logout")
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .build();
    }


    //
//    @Bean
//    @Order(2)
//    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
//            throws Exception {
//
//        http.authorizeRequests()
//                .antMatchers("/css/**", "/js/**", "/fonts/**", "/font/**", "/plugins/**", "/img/**", "/webjars/**",
//                        "/**.htmls", "/oauth2/**", "/v2/api-docs", "**/swagger-resources/**", "/swagger-ui.html", "/api/common/**").permitAll()
//                .antMatchers("/api/common/**").permitAll()
//                .antMatchers("/api/**").authenticated()
//                .and()
//                .formLogin().disable()
////                .formLogin((formLoginCustomizer) -> {
////                    formLoginCustomizer.loginProcessingUrl("/api/common/v1/login");
////                    formLoginCustomizer.successHandler((req, resp, authentication) -> {
////                        Object principal = authentication.getPrincipal();
////                        resp.setContentType("application/json;charset=utf-8");
////                        PrintWriter out = resp.getWriter();
////                        out.write(new ObjectMapper().writeValueAsString(principal));
////                        out.flush();
////                        out.close();
////                    });
////                })
////                .httpBasic().disable()
//                .csrf().disable();
//        http.exceptionHandling((exceptions) -> {
//            exceptions.accessDeniedHandler(walletAccessDeniedHandler);
//            exceptions.authenticationEntryPoint(autumnAuthExceptionEntryPoint);
//        });
//
//        return http.build();
//    }

    @Bean
    public RegisteredClientRepository registeredClientRepository() {
        RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId("messaging-client")
//                .clientSecret("{noop}secret")
                .clientSecret(passwordEncoder.encode("111111"))
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .redirectUri("http://127.0.0.1:8080/login/oauth2/code/messaging-client-oidc")
                .redirectUri("http://www.baidu.com")
                .scope(OidcScopes.OPENID)
                .scope("message.read")
                .scope("message.write")
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                .build();

        return new InMemoryRegisteredClientRepository(registeredClient);
    }

    @Bean
    public OAuth2AuthorizationService authorizationService(JdbcTemplate jdbcTemplate, RegisteredClientRepository registeredClientRepository) {
        JdbcOAuth2AuthorizationService service = new JdbcOAuth2AuthorizationService(jdbcTemplate, registeredClientRepository);
        JdbcOAuth2AuthorizationService.OAuth2AuthorizationRowMapper rowMapper = new JdbcOAuth2AuthorizationService.OAuth2AuthorizationRowMapper(registeredClientRepository);

        ObjectMapper objectMapper = new ObjectMapper();
        ClassLoader classLoader = JdbcOAuth2AuthorizationService.class.getClassLoader();
        List<Module> securityModules = SecurityJackson2Modules.getModules(classLoader);
        objectMapper.registerModules(securityModules);
        objectMapper.registerModule(new OAuth2AuthorizationServerJackson2Module());
        // You will need to write the Mixin for your class so Jackson can marshall it.
        objectMapper.addMixIn(TokenUserDetails.class, UserPrincipal.class);
        rowMapper.setObjectMapper(objectMapper);
        service.setAuthorizationRowMapper(rowMapper);
        return service;

//        return new JdbcOAuth2AuthorizationService(jdbcTemplate, registeredClientRepository);
    }

//    @Bean
//    public RegisteredClientRepository registeredClientRepository() {
//        RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
//                .clientId("messaging-client")
//                .clientSecret("{noop}secret")
//                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
//                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
//                .redirectUri("http://127.0.0.1:8080/login/oauth2/code/messaging-client-oidc")
//                .redirectUri("http://127.0.0.1:8080/authorized")
//                .scope(OidcScopes.OPENID)
//                .scope("message.read")
//                .scope("message.write")
//                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
//                .build();
//
//        return new JdbcRegisteredClientRepository(dataSource);
////        return new InMemoryRegisteredClientRepository(registeredClient);
//    }

    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        KeyPair keyPair = generateRsaKey();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAKey rsaKey = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return new ImmutableJWKSet<>(jwkSet);
    }

    private static KeyPair generateRsaKey() {
        KeyPair keyPair;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();
        }
        catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
        return keyPair;
    }

    /**
     *  default https://docs.spring.io/spring-authorization-server/docs/current/reference/html/configuration-model.html#configuring-provider-settings
     * @return
     */
    @Bean
    public ProviderSettings providerSettings() {
        return ProviderSettings.builder().build();
    }


}