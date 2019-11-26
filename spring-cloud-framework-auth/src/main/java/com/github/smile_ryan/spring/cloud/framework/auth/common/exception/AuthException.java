package com.github.smile_ryan.spring.cloud.framework.auth.common.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.smile_ryan.spring.cloud.framework.auth.common.serializer.AuthExceptionJacksonSerializer;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * <pre>
 * 名称：AuthException
 * 描述：AuthException.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@JsonSerialize(using = AuthExceptionJacksonSerializer.class)
public class AuthException extends OAuth2Exception {

    public AuthException(String msg, Throwable t) {
        super(msg, t);

    }

    public AuthException(String msg) {
        super(msg);

    }


}
