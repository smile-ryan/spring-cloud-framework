package com.github.smile_ryan.spring.cloud.framework.auth.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

/**
 * <pre>
 * 名称：HttpResponse
 * 描述：HttpResponse.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@Data
@JacksonXmlRootElement(localName = "response")
@JsonPropertyOrder({"status", "message", "data", "timestamp"})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public final class HttpResponse<T> {

    private Integer status;

    private String message;

    private String error;

    private T data;

    private Date timestamp;

    public static <T> HttpResponse<T> ok() {
        return new HttpResponse<T>().status(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .timestamp(new Date());
    }

    public static <T> HttpResponse<T> ok(T data) {
        return new HttpResponse<T>().status(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .data(data)
                .timestamp(new Date());
    }

    public static <T> HttpResponse<T> error(Integer status, String message) {
        return new HttpResponse<T>().status(status)
                .message(message)
                .timestamp(new Date());
    }

    public static <T> HttpResponse<T> error(Integer status, String message, T data) {
        return new HttpResponse<T>().status(status)
                .message(message)
                .data(data)
                .timestamp(new Date());
    }

    public HttpResponse<T> status(Integer status) {
        this.status = status;
        return this;
    }

    public HttpResponse<T> error(String error) {
        this.error = error;
        return this;
    }

    public HttpResponse<T> message(String message) {
        this.message = message;
        return this;
    }

    public HttpResponse<T> timestamp(Date timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public HttpResponse<T> data(T data) {
        this.data = data;
        return this;
    }

}
