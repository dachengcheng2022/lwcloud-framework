package com.autumn.config.security;

import com.autumn.config.security.exception.AutumnAuthExceptionEntryPoint;
import com.autumn.config.security.exception.WalletAccessDeniedHandler;
import com.autumn.config.security.integration.authenticator.MallUserDetails;
import com.autumn.config.security.oauth2.AuthorityDetailsService;
import com.autumn.filter.IntegrationAuthenticationFilter;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.authorization.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
import org.springframework.security.oauth2.server.authorization.web.OAuth2AuthorizationEndpointFilter;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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

    //
    @Bean
    @Order(1)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http)
            throws Exception {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);

        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class);

        http.exceptionHandling((exceptions) -> {
                    exceptions.accessDeniedHandler(walletAccessDeniedHandler);
                    exceptions.authenticationEntryPoint(autumnAuthExceptionEntryPoint);
//                    exceptions.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"));

                })
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .addFilterBefore(integrationAuthenticationFilter, CsrfFilter.class);

//        http.oauth2ResourceServer().accessDeniedHandler()
//        http.addFilterAt(integrationAuthenticationFilter,OAuth2AuthorizationEndpointFilter.class);

//        http.addFilterBefore(integrationAuthenticationFilter);
        return http.build();
    }

    //
    @Bean
    @Order(2)
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
            throws Exception {

        http.authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/fonts/**", "/font/**", "/plugins/**", "/img/**", "/webjars/**",
                        "/**.htmls", "/oauth2/**", "/v2/api-docs", "**/swagger-resources/**", "/swagger-ui.html", "/api/common/**").permitAll()
                .antMatchers("/api/common/**").permitAll()
                .antMatchers("/api/**").authenticated()
                .and()
                .formLogin().disable()
//                .formLogin((formLoginCustomizer) -> {
//                    formLoginCustomizer.loginProcessingUrl("/api/common/v1/login");
//                    formLoginCustomizer.successHandler((req, resp, authentication) -> {
//                        Object principal = authentication.getPrincipal();
//                        resp.setContentType("application/json;charset=utf-8");
//                        PrintWriter out = resp.getWriter();
//                        out.write(new ObjectMapper().writeValueAsString(principal));
//                        out.flush();
//                        out.close();
//                    });
//                })
//                .httpBasic().disable()
                .csrf().disable();
        http.exceptionHandling((exceptions) -> {
            exceptions.accessDeniedHandler(walletAccessDeniedHandler);
            exceptions.authenticationEntryPoint(autumnAuthExceptionEntryPoint);
//            exceptions.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"));
        });
//
//
////        http
////                // Redirect to the login page when not authenticated from the
////                // authorization endpoint
////                .exceptionHandling((exceptions) -> exceptions
////                        .authenticationEntryPoint(
////                                new LoginUrlAuthenticationEntryPoint("/login"))
////                )
////                // Accept access tokens for User Info and/or Client Registration
////                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
//
        return http.build();
    }



//    @Bean
//    @Order(1)
//    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http)
//            throws Exception {
//        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
//        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
//                .oidc(Customizer.withDefaults());	// Enable OpenID Connect 1.0
//        http
//                // Redirect to the login page when not authenticated from the
//                // authorization endpoint
//                .exceptionHandling((exceptions) -> exceptions
//                        .authenticationEntryPoint(
//                                new LoginUrlAuthenticationEntryPoint("/login"))
//                )
//                // Accept access tokens for User Info and/or Client Registration
//                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
//
//        return http.build();
//    }

//    @Bean
//    @Order(2)
//    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
//            throws Exception {
//        http
//                .authorizeHttpRequests((authorize) -> authorize
//                        .anyRequest().authenticated()
//                )
//                // Form login handles the redirect to the login page from the
//                // authorization server filter chain
//                .formLogin((formLoginCustomizer)->{
//                    formLoginCustomizer.loginProcessingUrl("/api/common/v1/login");
//                    formLoginCustomizer.successHandler((req, resp, authentication)->{
//                        Object principal = authentication.getPrincipal();
//                        resp.setContentType("application/json;charset=utf-8");
//                        PrintWriter out = resp.getWriter();
//                        out.write(new ObjectMapper().writeValueAsString(principal));
//                        out.flush();
//                        out.close();
//                    });
//                })
//                .csrf().disable();
//        return http.build();
//    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        //这里用固定的用户，后续改成从数据库查询
//        UserDetails userDetails = User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("111111")
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(userDetails);
//    }


//    @Bean
//    public RegisteredClientRepository registeredClientRepository(JdbcTemplate jdbcTemplate) {
//        RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
//                .clientId("mobile-gateway-client")
//                .clientSecret("{noop}123456")
//                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
//                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
//                .redirectUri("http://127.0.0.1:9100/login/oauth2/code/mobile-gateway-client-oidc")
//                .redirectUri("http://127.0.0.1:9100/authorized")
//                .scope(OidcScopes.OPENID)
//                .scope("message.read")
//                .scope("message.write")
//                .build();
//
//        JdbcRegisteredClientRepository registeredClientRepository = new JdbcRegisteredClientRepository(jdbcTemplate);
//        registeredClientRepository.save(registeredClient);
//
//        return registeredClientRepository;
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
        objectMapper.addMixIn(MallUserDetails.class, UserPrincipal.class);
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