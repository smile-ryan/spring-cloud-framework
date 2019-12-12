package com.github.smile.ryan.framework.auth.model.domain;

import com.github.smile.ryan.framework.auth.common.util.CommonUtils;
import com.github.smile.ryan.framework.auth.model.entity.AuthClientEntity;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.ClientDetails;

/**
 * <pre>
 * 名称：SmsCaptchaService
 * 描述：SmsCaptchaService.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@Data
@SuppressWarnings("unchecked")
public final class AuthClientDetails implements ClientDetails {

    private AuthClientEntity authClientEntity;
    private Set<String> scope;

    public AuthClientDetails(AuthClientEntity authClientEntity) {
        this.authClientEntity = authClientEntity;
    }

    public AuthClientDetails() {
    }

    @Override
    public String getClientId() {
        return authClientEntity.getClientId();
    }

    @Override
    public Set<String> getResourceIds() {
        return authClientEntity.getResourceIds() != null
            ? CommonUtils.transformStringToSet(authClientEntity.getResourceIds(), String.class) : null;
    }

    @Override
    public boolean isSecretRequired() {
        return authClientEntity.getIsSecretRequired();
    }

    @Override
    public String getClientSecret() {
        return authClientEntity.getClientSecret();
    }

    @Override
    public boolean isScoped() {
        return authClientEntity.getIsScoped();
    }

    @Override
    public Set<String> getScope() {
        this.scope = authClientEntity.getScope() != null
            ? CommonUtils.transformStringToSet(authClientEntity.getScope(), String.class) : null;
        return this.scope;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return authClientEntity.getAuthorizedGrantTypes() != null
            ? CommonUtils.transformStringToSet(authClientEntity.getAuthorizedGrantTypes(), String.class)
            : null;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return authClientEntity.getRegisteredRedirectUri() != null
            ? CommonUtils
            .transformStringToSet(authClientEntity.getRegisteredRedirectUri(), String.class) : null;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return (authClientEntity.getAuthorities() != null
            && authClientEntity.getAuthorities().trim().length() > 0)
            ? AuthorityUtils.commaSeparatedStringToAuthorityList(authClientEntity.getAuthorities())
            : null;
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return authClientEntity.getAccessTokenValiditySeconds();
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return authClientEntity.getRefreshTokenValiditySeconds();
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return this.authClientEntity.getIsAutoApprove() == null ? false : this
            .authClientEntity.getIsAutoApprove();
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }
}
