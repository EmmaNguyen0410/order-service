package com.emmang.order.exception;

public class UnauthorizedRoleException extends RuntimeException{
    public UnauthorizedRoleException(String message) {
        super(message);
    }
}