package com.marcelo_corrtes.api.exceptions;

public class NotFoundUserException extends RuntimeException {

    public NotFoundUserException(String message) {
        super(message);
    }

}
