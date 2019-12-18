package com.github.smile.ryan.framework.demo1.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.web.client.RestTemplate;

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

  @Autowired
  private ObjectMapper objectMapper;

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) {
    resources
        .resourceId("demo1-service")
        .tokenServices(tokenServices());
  }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/**")
            .authorizeRequests()
            .antMatchers("/login", "/auth/code").permitAll()
            .anyRequest()
            .authenticated();
//            .access("@permissionService.hasPermission(request)");
    }

  @Bean
  public ResourceServerTokenServices tokenServices() {
    RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
    remoteTokenServices.setCheckTokenEndpointUrl("http://localhost:8081/auth/check_token");
    remoteTokenServices.setClientId("demo1-service");
    remoteTokenServices.setClientSecret("123456");
    remoteTokenServices.setRestTemplate(restTemplate());
    remoteTokenServices.setAccessTokenConverter(accessTokenConverter());
    return remoteTokenServices;
  }


  @LoadBalanced
  @Bean
  public RestTemplate restTemplate() {
    RestTemplate restTemplate = new RestTemplate();
    List<HttpMessageConverter<?>> converters = restTemplate.getMessageConverters();
    for (HttpMessageConverter<?> converter : converters) {
      if (converter instanceof MappingJackson2HttpMessageConverter) {
        MappingJackson2HttpMessageConverter jsonConverter = (MappingJackson2HttpMessageConverter) converter;
        jsonConverter.setObjectMapper(objectMapper);
        jsonConverter.setSupportedMediaTypes(ImmutableList
            .of(new MediaType("application", "json", MappingJackson2HttpMessageConverter.DEFAULT_CHARSET),
                new MediaType("text", "html", MappingJackson2HttpMessageConverter.DEFAULT_CHARSET)));
      }
    }
    return restTemplate;
  }

  @Bean
  public AccessTokenConverter accessTokenConverter() {
    return new DefaultAccessTokenConverter();
  }


}
