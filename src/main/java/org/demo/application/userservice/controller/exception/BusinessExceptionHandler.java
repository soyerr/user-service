package org.demo.application.userservice.controller.exception;


import com.google.common.collect.Sets;
import org.demo.application.userservice.controller.model.RestResponse;
import org.demo.application.userservice.exception.NotExistUserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BusinessExceptionHandler {

    @ExceptionHandler(NotExistUserException.class)
    public ResponseEntity notExistingUser(NotExistUserException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(RestResponse.builder()
                        .exceptionMessages(Sets.newHashSet(ex.getMessage()))
                        .build());
    }
}
