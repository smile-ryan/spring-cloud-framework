package com.github.smile.ryan.framework.auth.common.config;

import com.github.smile.ryan.framework.auth.common.exception.AuthException;
import com.github.smile.ryan.framework.auth.common.properties.AuthSecurityProperties;
import java.util.Date;
import javax.sql.DataSource;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * <pre>
 * 名称：TokenStoreConfig
 * 描述：TokenStoreConfig.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@Configuration
public class TokenStoreConfig {

  private AuthSecurityProperties authSecurityProperties;

  private RedisConnectionFactory redisConnectionFactory;

  private DataSource dataSource;

  private ClientDetailsService clientDetailsService;

  @Autowired(required = false)
  public TokenStoreConfig(AuthSecurityProperties authSecurityProperties,
      RedisConnectionFactory redisConnectionFactory, DataSource dataSource,
      ClientDetailsService clientDetailsService) {
    this.authSecurityProperties = authSecurityProperties;
    this.redisConnectionFactory = redisConnectionFactory;
    this.dataSource = dataSource;
    this.clientDetailsService = clientDetailsService;
  }

  @Bean
  public TokenStore tokenStore() {
    TokenStore store;
    switch (authSecurityProperties.getTokenStoreType()) {
      case jwt:
        store = new JwtTokenStore(jwtAccessTokenConverter());
        break;
      case redis:
        if (redisConnectionFactory == null) {
          throw new BeanCreationException("配置Redis存储Token需要redisConnectionFactory bean，未找到");
        }
        store = new AuthRedisTokenStore(redisConnectionFactory);
        break;
      case jdbc:
        if (dataSource == null) {
          throw new BeanCreationException("配置jdbc存储Token需要dataSource bean，未找到");
        }
        store = new JdbcTokenStore(dataSource);
        break;
      default:
        store = new InMemoryTokenStore();
    }

    return store;
  }

  @Bean
  @Primary
  public JwtAccessTokenConverter jwtAccessTokenConverter() {
    JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
    converter.setSigningKey(authSecurityProperties.getTokenSigningKey());
    return converter;
  }

  @Bean
  public DefaultTokenServices defaultTokenServices() {
    DefaultTokenServices tokenServices = new DefaultTokenServices();
    tokenServices.setTokenStore(tokenStore());
    return tokenServices;
  }


  private final class AuthRedisTokenStore extends RedisTokenStore {

    public AuthRedisTokenStore(RedisConnectionFactory redisConnectionFactory) {
      super(redisConnectionFactory);
    }

    @Override
    public OAuth2Authentication readAuthentication(OAuth2AccessToken accessToken) {
      OAuth2Authentication authentication = super.readAuthentication(accessToken);
      resetAccessTokenValiditySeconds(accessToken, authentication);
      return authentication;
    }

    @Override
    public OAuth2AccessToken getAccessToken(OAuth2Authentication authentication) {
      OAuth2AccessToken accessToken = super.getAccessToken(authentication);
      resetAccessTokenValiditySeconds(accessToken, authentication);
      return accessToken;
    }

    private void resetAccessTokenValiditySeconds(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
      if (accessToken == null || authentication == null) {
        return;
      }
      DefaultOAuth2AccessToken oauth2AccessToken = (DefaultOAuth2AccessToken) accessToken;

      int validitySeconds = getAccessTokenValiditySeconds(authentication.getOAuth2Request());
      if (validitySeconds > 0) {
        oauth2AccessToken
            .setExpiration(new Date(System.currentTimeMillis() + (validitySeconds * 1000L)));
      }
      removeAccessToken(accessToken);
      storeAccessToken(accessToken, authentication);
    }

    private int getAccessTokenValiditySeconds(OAuth2Request clientAuth) {
      if (clientDetailsService != null) {
        ClientDetails client = clientDetailsService.loadClientByClientId(clientAuth.getClientId());
        Integer validity = client.getAccessTokenValiditySeconds();
        if (validity != null) {
          return validity;
        }
      }
      // get default accessToken validity time
      throw new AuthException("Cannot get accessToken validity time");
    }
  }
}
