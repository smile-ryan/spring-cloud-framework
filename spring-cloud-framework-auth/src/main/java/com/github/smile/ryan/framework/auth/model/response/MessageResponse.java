package com.github.smile.ryan.framework.auth.model.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.Date;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * <pre>
 * 名称：MessageResponse
 * 描述：MessageResponse.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@Data
@JacksonXmlRootElement(localName = "response")
@JsonPropertyOrder({"status", "message", "data", "timestamp"})
public final class MessageResponse<T> {

    private Integer status;

    private String message;

    private String error;

    private T data;

    private Date timestamp;

    public static <T> MessageResponse<T> ok() {
        return new MessageResponse<T>().status(HttpStatus.OK.value())
            .message(HttpStatus.OK.getReasonPhrase())
            .timestamp(new Date());
    }

    public static <T> MessageResponse<T> ok(T data) {
        return new MessageResponse<T>().status(HttpStatus.OK.value())
            .message(HttpStatus.OK.getReasonPhrase())
            .data(data)
            .timestamp(new Date());
    }

    public static <T> MessageResponse<T> error(Integer status, String message) {
        return new MessageResponse<T>().status(status)
            .message(message)
            .timestamp(new Date());
    }

    public static <T> MessageResponse<T> error(Integer status, String message, T data) {
        return new MessageResponse<T>().status(status)
            .message(message)
            .data(data)
            .timestamp(new Date());
    }

    public MessageResponse<T> status(Integer status) {
        this.status = status;
        return this;
    }

    public MessageResponse<T> error(String error) {
        this.error = error;
        return this;
    }

    public MessageResponse<T> message(String message) {
        this.message = message;
        return this;
    }

    public MessageResponse<T> timestamp(Date timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public MessageResponse<T> data(T data) {
        this.data = data;
        return this;
    }

}
