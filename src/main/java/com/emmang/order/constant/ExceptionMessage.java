package com.emmang.order.constant;

public final class ExceptionMessage {
    public static final String UNAUTHORIZED_ROLE = "Your role is not authorized to access this resource";
    public static final String HEADER_NOT_FOUND = "You are not authenticated";

    private ExceptionMessage() {
        // Prevent instantiation
    }
}