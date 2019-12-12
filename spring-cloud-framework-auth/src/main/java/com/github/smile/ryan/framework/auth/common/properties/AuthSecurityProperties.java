package com.github.smile.ryan.framework.auth.common.properties;

import com.github.smile.ryan.framework.auth.common.enums.TokenStoreType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;

/**
 * <pre>
 * 名称：AuthSecurityProperties
 * 描述：AuthSecurityProperties.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "app.auth")
@Getter
@Setter
public class AuthSecurityProperties {

    private String loginPage = "/login";

    private String loginProcessUrl = "/authorize";

    private TokenStoreType tokenStoreType = TokenStoreType.memory;

    @NestedConfigurationProperty
    private SmsCaptchaProperties sms = new SmsCaptchaProperties();

    private String tokenSigningKey;

    private String captchaParameterName = "captcha";

}
