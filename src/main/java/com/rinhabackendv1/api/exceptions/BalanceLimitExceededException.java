package com.rinhabackendv1.api.exceptions;

public class BalanceLimitExceededException extends RuntimeException{
    
        public BalanceLimitExceededException(String message) {
            super(message);
        }
     
}
