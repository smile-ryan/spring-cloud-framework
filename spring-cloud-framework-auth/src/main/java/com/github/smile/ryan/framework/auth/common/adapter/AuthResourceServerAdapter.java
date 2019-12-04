package com.github.smile.ryan.framework.auth.common.adapter;

import com.github.smile.ryan.framework.auth.common.handler.ForbiddenExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * <pre>
 * 名称：OAuth2ResourceServerConfig
 * 描述：OAuth2ResourceServerConfig.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@Configuration
@EnableResourceServer
public class AuthResourceServerAdapter extends ResourceServerConfigurerAdapter {

  private final AuthenticationEntryPoint authenticationEntryPoint;

  private final ForbiddenExceptionHandler forbiddenExceptionHandler;

  private final TokenStore tokenStore;

  @Autowired
  public AuthResourceServerAdapter(AuthenticationEntryPoint authenticationEntryPoint,
      ForbiddenExceptionHandler forbiddenExceptionHandler, TokenStore tokenStore) {
    this.authenticationEntryPoint = authenticationEntryPoint;
    this.forbiddenExceptionHandler = forbiddenExceptionHandler;
    this.tokenStore = tokenStore;
  }

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) {
    resources.tokenStore(tokenStore)
        .resourceId("auth-server");
    resources.authenticationEntryPoint(authenticationEntryPoint)
        .accessDeniedHandler(forbiddenExceptionHandler);

  }

//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/favicon.ico", "/auth/**").permitAll()
//                .anyRequest()
//                .access("#oauth2.hasAnyScope('all','select')");
//    }


}
