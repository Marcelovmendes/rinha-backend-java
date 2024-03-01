package com.rinhabackendv1.api.exceptions;

public class TransactionErrorException extends RuntimeException {

    public TransactionErrorException(String message) {

        super(message);
    }
}
