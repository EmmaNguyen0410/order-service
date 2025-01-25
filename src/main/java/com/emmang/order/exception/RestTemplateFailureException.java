package com.emmang.order.exception;

public class RestTemplateFailureException extends RuntimeException{
    public RestTemplateFailureException(String message) {
        super(message);
    }
}