package com.bsfdv.remittance.exception;

import com.bsfdv.remittance.utils.ErrorBeanV2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class RemittanceExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleUnExpectedException(Exception ex) {
        if (ex instanceof CustomException) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorBeanV2.INTERNAL_SERVER.message());
    }
    @Override
    public final ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                     HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.badRequest().body(ErrorBeanV2.PARAM_INVALID.message());
    }
    @Override
    public final ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.status(BAD_REQUEST).body(ErrorBeanV2.PARAM_MISSING.message());
    }
    @Override
    public final ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
                                                                        HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(ErrorBeanV2.MEDIATYPE_NOT_SUPPORT.message());
    }
    /**
     * Handles the {@code MethodArgumentTypeMismatchException}
     *
     * @param ex the {@link MethodArgumentTypeMismatchException}
     * @return a {@link ResponseEntity}
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public final ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
                                                                         WebRequest request) {
        // String error = String.format("The parameter '%s' of value '%s' could not be converted to type '%s'",
        // ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName())
        return ResponseEntity.status(BAD_REQUEST).body(ErrorBeanV2.PARAM_TYPE_INCORRECT.message());
    }

}
