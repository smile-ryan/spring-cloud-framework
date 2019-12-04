package com.github.smile.ryan.framework.auth.common.adapter;

import com.github.smile.ryan.framework.auth.common.filter.CaptchaRequestFilter;
import com.github.smile.ryan.framework.auth.common.handler.LoginFailureHandler;
import com.github.smile.ryan.framework.auth.common.properties.AuthSecurityProperties;
import com.github.smile.ryan.framework.auth.common.service.AuthUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * <pre>
 * 名称：AuthWebSecurityConfigAdapter
 * 描述：AuthWebSecurityConfigAdapter.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@Configuration
@Order(1)
public class AuthWebSecurityConfigAdapter extends WebSecurityConfigurerAdapter {

  private AuthUserDetailService authUserDetailService;

  private AuthSecurityProperties properties;

  private LoginFailureHandler loginFailureHandler;

  private CaptchaRequestFilter captchaRequestFilter;

  @Autowired
  public AuthWebSecurityConfigAdapter(AuthUserDetailService authUserDetailService,
      AuthSecurityProperties properties, LoginFailureHandler loginFailureHandler,
      CaptchaRequestFilter captchaRequestFilter) {
    this.authUserDetailService = authUserDetailService;
    this.properties = properties;
    this.loginFailureHandler = loginFailureHandler;
    this.captchaRequestFilter = captchaRequestFilter;
  }

  @Override
  public void configure(WebSecurity web) {
    web.ignoring().antMatchers
        ("/swagger-ui.html/**", "/webjars/**",
            "/swagger-resources/**", "/v2/api-docs/**",
            "/swagger-resources/configuration/ui/**",
            "/swagger-resources/configuration/security/**",
            "/static/**", "/images/**",
            "/captcha", "/error", "/lock/screen");
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(authUserDetailService);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .requestMatchers()
        .antMatchers("/auth/**", "/index", "/", properties.getLoginPage(),
            properties.getLoginProcessUrl())
        .and()
        .authorizeRequests()
        .antMatchers(properties.getLoginPage(), properties.getLoginProcessUrl())
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .sessionManagement().maximumSessions(1).expiredUrl("/lock/screen");

    http.formLogin()
        .failureHandler(loginFailureHandler)
        .loginPage(properties.getLoginPage())
        .loginProcessingUrl(properties.getLoginProcessUrl())
        .failureUrl("/error")
        .defaultSuccessUrl("/index");

    http.httpBasic().disable();

    // 用户密码验证之前校验验证码
    http.addFilterBefore(captchaRequestFilter, UsernamePasswordAuthenticationFilter.class);
  }


  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
