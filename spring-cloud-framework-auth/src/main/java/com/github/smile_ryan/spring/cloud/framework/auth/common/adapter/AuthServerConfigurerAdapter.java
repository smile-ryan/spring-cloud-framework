package com.github.smile_ryan.spring.cloud.framework.auth.common.adapter;

import com.github.smile_ryan.spring.cloud.framework.auth.common.filter.BasicAuthenticationFilter;
import com.github.smile_ryan.spring.cloud.framework.auth.common.service.AuthClientDetailsService;
import com.github.smile_ryan.spring.cloud.framework.auth.common.service.AuthUserDetailService;
import com.github.smile_ryan.spring.cloud.framework.auth.common.translator.AuthWebResponseExceptionTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * <pre>
 * 名称：AuthServerConfigurerAdapter
 * 描述：AuthServerConfigurerAdapter.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@Configuration
@EnableAuthorizationServer
public class AuthServerConfigurerAdapter extends AuthorizationServerConfigurerAdapter {

    private AuthenticationManager authenticationManager;

    private AuthClientDetailsService authClientDetailsService;

    private TokenStore tokenStore;

    private JwtAccessTokenConverter jwtAccessTokenConverter;

    private AuthenticationEntryPoint authenticationEntryPoint;

    private AuthWebResponseExceptionTranslator authWebResponseExceptionTranslator;

    private BasicAuthenticationFilter basicAuthenticationFilter;

    private AuthUserDetailService authUserDetailService;

    @Autowired(required = false)
    public AuthServerConfigurerAdapter(AuthenticationManager authenticationManager,
                                       AuthClientDetailsService authClientDetailsService,
                                       TokenStore tokenStore, JwtAccessTokenConverter jwtAccessTokenConverter,
                                       AuthenticationEntryPoint authenticationEntryPoint,
                                       AuthWebResponseExceptionTranslator authWebResponseExceptionTranslator,
                                       BasicAuthenticationFilter basicAuthenticationFilter, AuthUserDetailService authUserDetailService) {
        this.authenticationManager = authenticationManager;
        this.authClientDetailsService = authClientDetailsService;
        this.tokenStore = tokenStore;
        this.jwtAccessTokenConverter = jwtAccessTokenConverter;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.authWebResponseExceptionTranslator = authWebResponseExceptionTranslator;
        this.basicAuthenticationFilter = basicAuthenticationFilter;
        this.authUserDetailService = authUserDetailService;
    }

    public AuthServerConfigurerAdapter() {
        super();
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.allowFormAuthenticationForClients()
                .authenticationEntryPoint(authenticationEntryPoint)
                .tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()")
                .addTokenEndpointAuthenticationFilter(basicAuthenticationFilter);
        basicAuthenticationFilter.setClientDetailsService(authClientDetailsService);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer client) throws Exception {
        client.withClientDetails(authClientDetailsService);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .userDetailsService(authUserDetailService)
                .allowedTokenEndpointRequestMethods(HttpMethod.POST, HttpMethod.GET);

        if (!(tokenStore instanceof RedisTokenStore) && this.jwtAccessTokenConverter != null) {
            endpoints.accessTokenConverter(jwtAccessTokenConverter);
        }
        endpoints.exceptionTranslator(authWebResponseExceptionTranslator);
        endpoints.pathMapping("/oauth/authorize", "/auth/authorize");
        endpoints.pathMapping("/oauth/token", "/auth/token");
        endpoints.pathMapping("/oauth/check_token", "/auth/check_token");
        endpoints.pathMapping("/oauth/grant", "/auth/grant");
        endpoints.pathMapping("/oauth/error", "/auth/error");
    }

}
