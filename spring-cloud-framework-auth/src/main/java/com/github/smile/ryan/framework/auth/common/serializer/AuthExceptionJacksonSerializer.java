package com.github.smile.ryan.framework.auth.common.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.github.smile.ryan.framework.auth.common.exception.AuthException;
import java.io.IOException;
import java.util.Map;
import org.springframework.web.util.HtmlUtils;

/**
 * <pre>
 * 名称：AuthExceptionJacksonSerializer
 * 描述：AuthExceptionJacksonSerializer.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
public class AuthExceptionJacksonSerializer extends StdSerializer<AuthException> {

    protected AuthExceptionJacksonSerializer() {
        super(AuthException.class);
    }

    @Override
    public void serialize(AuthException value, JsonGenerator jgen,
        SerializerProvider serializerProvider) throws IOException {
        jgen.writeStartObject();
        jgen.writeObjectField("status", value.getHttpErrorCode());
        String errorMessage = value.getOAuth2ErrorCode();
        if (errorMessage != null) {
            errorMessage = HtmlUtils.htmlEscape(errorMessage);
        }
        jgen.writeStringField("message", errorMessage);
        if (value.getAdditionalInformation() != null) {
            for (Map.Entry<String, String> entry : value.getAdditionalInformation().entrySet()) {
                String key = entry.getKey();
                String add = entry.getValue();
                jgen.writeStringField(key, add);
            }
        }
        jgen.writeEndObject();
    }
}
