package org.demo.application.userservice.controller.exception;

import com.google.common.collect.Sets;

import org.demo.application.userservice.controller.model.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GeneralExceptionHandler {

    private static final String INTERNAL_SEVER_ERROR_MESSAGE = "Internal Server error. Please contact with we-wont-help-you@gmail.com";

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleMissingRequestBody(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(RestResponse.builder()
                        .exceptionMessages(Sets.newHashSet(ex.getMessage()))
                        .build());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity requestHandlingNoHandlerFound(final NoHandlerFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(RestResponse.builder()
                        .exceptionMessages(Sets.newHashSet(ex.getMessage()))
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException validationException) {
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(RestResponse.builder()
                        .exceptionMessages(Sets.newHashSet(validationException.getBindingResult().getFieldError().getDefaultMessage()))
                        .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity exception() {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(RestResponse.builder()
                        .exceptionMessages(Sets.newHashSet(INTERNAL_SEVER_ERROR_MESSAGE))
                        .build());
    }
}
