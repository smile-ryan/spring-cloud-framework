package com.github.smile.ryan.framework.auth.common.converter;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * 名称：CheckTokenAccessTokenConverter
 * 描述：CheckTokenAccessTokenConverter.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@Slf4j
@Component
public class CheckTokenAccessTokenConverter implements AccessTokenConverter {

  private final AccessTokenConverter accessTokenConverter;

  CheckTokenAccessTokenConverter() {
    this(new DefaultAccessTokenConverter());
  }

  CheckTokenAccessTokenConverter(AccessTokenConverter accessTokenConverter) {
    this.accessTokenConverter = accessTokenConverter;
  }

  @Override
  public Map<String, ?> convertAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
    Map<String, Object> claims = (Map<String, Object>) this.accessTokenConverter
        .convertAccessToken(token, authentication);

    // gh-1070
    claims.put("active", true);    // Always true if token exists and not expired

    return claims;
  }

  @Override
  public OAuth2AccessToken extractAccessToken(String value, Map<String, ?> map) {
    return this.accessTokenConverter.extractAccessToken(value, map);
  }

  @Override
  public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
    return this.accessTokenConverter.extractAuthentication(map);
  }
}