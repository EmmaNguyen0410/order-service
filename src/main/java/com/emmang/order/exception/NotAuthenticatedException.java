package com.emmang.order.exception;

public class NotAuthenticatedException extends RuntimeException{
    public NotAuthenticatedException(String message) {
        super(message);
    }
}