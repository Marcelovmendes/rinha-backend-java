package com.marcelo_corrtes.api.exceptions;

public class TransactionErrorException extends RuntimeException {

    public TransactionErrorException(String message) {

        super(message);
    }
}
