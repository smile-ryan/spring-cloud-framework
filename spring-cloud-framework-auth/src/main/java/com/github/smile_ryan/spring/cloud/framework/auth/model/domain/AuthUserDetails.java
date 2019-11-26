package com.github.smile_ryan.spring.cloud.framework.auth.model.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * <pre>
 * 名称：AuthUserDetails
 * 描述：AuthUserDetails.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@Data
public class AuthUserDetails implements UserDetails {

    private String username;

    private String email;

    private Boolean isEnable;

    private Boolean isExpired;

    private Boolean isLocked;

    private String password;

    private String gender;

    @TableField(exist = false)
    private List<GrantedAuthority> authorities;

    @Override
    public List<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnable;
    }
}