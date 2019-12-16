package com.github.smile.ryan.framework.auth.common.handler;

import com.github.smile.ryan.framework.auth.common.exception.ArgumentsFailureException;
import com.github.smile.ryan.framework.auth.common.exception.AuthFailureException;
import com.github.smile.ryan.framework.auth.common.exception.NotAuthException;
import com.github.smile.ryan.framework.auth.common.exception.NotAuthorityException;
import com.github.smile.ryan.framework.auth.model.response.MessageResponse;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * <pre>
 * 名称：GlobalExceptionHandler
 * 描述：GlobalExceptionHandler.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@Slf4j
@RestControllerAdvice
public final class GlobalExceptionHandler {

    @ExceptionHandler(value = {HttpMessageNotReadableException.class,
        ClientRegistrationException.class,
        TypeMismatchException.class,
        MissingServletRequestParameterException.class,
        ConstraintViolationException.class,
        BindException.class,
        InvalidTokenException.class,
        ArgumentsFailureException.class,
        MethodArgumentNotValidException.class,
        UsernameNotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MessageResponse badRequestExceptionHandle(Exception e) {
        log.warn(e.getMessage());
        if (e instanceof BindException) {
            // Spring Validation Exception 处理@Valid失败后异常
            String message = ((BindException) e).getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", "));
            return MessageResponse.error(HttpStatus.BAD_REQUEST.value(), message);
        } else if (e instanceof ConstraintViolationException) {
            // Spring Validation Exception 处理@RequestParam失败后异常
            String message = ((ConstraintViolationException) e).getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage).collect(Collectors.joining(", "));
            return MessageResponse.error(HttpStatus.BAD_REQUEST.value(), message);
        } else if (e instanceof MethodArgumentNotValidException) {
            // Spring Validation Exception 处理@RequestBody失败后异常
            String message;
            FieldError fieldError = ((MethodArgumentNotValidException) e).getBindingResult()
                .getFieldError();
            if (fieldError == null) {
                message = ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage).findFirst().orElse("No message available");

            } else {
                message = String.format("%s: %s, rejectedValue: %s", fieldError.getField(),
                    fieldError.getDefaultMessage(), fieldError.getRejectedValue());
            }
            return MessageResponse.error(HttpStatus.BAD_REQUEST.value(), message);
        }
        return MessageResponse.error(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler(value = {NotAuthException.class, AuthFailureException.class,
        InvalidGrantException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public MessageResponse unauthorizedExceptionHandle(NotAuthException e) {
        log.warn(e.getMessage());
        return MessageResponse.error(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
    }

    @ExceptionHandler(NotAuthorityException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public MessageResponse forbiddenExceptionHandle(NotAuthorityException e) {
        log.warn(e.getMessage());
        return MessageResponse.error(HttpStatus.FORBIDDEN.value(), e.getMessage());
    }

    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MessageResponse notFoundExceptionHandle(Exception e) {
        log.warn(e.getMessage());
        return MessageResponse.error(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public MessageResponse methodNotAllowedExceptionHandle(Exception e) {
        log.warn(e.getMessage());
        return MessageResponse.error(HttpStatus.METHOD_NOT_ALLOWED.value(), e.getMessage());
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public MessageResponse notAcceptableExceptionHandle(Exception e) {
        log.warn(e.getMessage());
        return MessageResponse.error(HttpStatus.NOT_ACCEPTABLE.value(), e.getMessage());
    }

    @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public MessageResponse unsupportedMediaTypeExceptionHandle(Exception e) {
        log.warn(e.getMessage());
        return MessageResponse.error(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public MessageResponse internalServerExceptionHandle(Exception e) {
        log.error(e.getMessage(), e);
        return MessageResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

}
