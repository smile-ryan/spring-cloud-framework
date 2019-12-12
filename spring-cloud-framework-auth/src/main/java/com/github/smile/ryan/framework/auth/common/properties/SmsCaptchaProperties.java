package com.github.smile.ryan.framework.auth.common.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 名称：SmsCaptchaProperties
 * 描述：SmsCaptchaProperties.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@Getter
@Setter
public class SmsCaptchaProperties {

    private Integer expirationTime = 240;
    private Integer length = 4;
    private String mobileParameter = "mobile";

}
