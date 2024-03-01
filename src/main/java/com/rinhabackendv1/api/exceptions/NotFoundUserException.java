package com.rinhabackendv1.api.exceptions;

public class NotFoundUserException extends RuntimeException {

    public NotFoundUserException(String message) {
        super(message);
    }

}
