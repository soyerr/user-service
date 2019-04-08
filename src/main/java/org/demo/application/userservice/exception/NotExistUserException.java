package org.demo.application.userservice.exception;

public class NotExistUserException extends RuntimeException {

    public NotExistUserException(String message){
        super(message);
    }
}
