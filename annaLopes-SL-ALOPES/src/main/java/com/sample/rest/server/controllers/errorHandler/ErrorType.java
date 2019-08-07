package com.sample.rest.server.controllers.errorHandler;

public enum ErrorType {

    UNEXPECTED_ERROR("0001", "Unexpected error has occurred"),
    NOT_FOUND("0002", "Resource not found"),
    FIELD_MUST_NOT_BE_NULL("0003", "The %s must not be null"),
    FIELD_DATE_MUST_BE_ON_PATTERN("0004", "Date must be formatter as 'yyyyMMdd'"),
    FIELD_DATE_MUST_BE_GREATER_THAN_TODAY("0005", "Date must be greater than today"),
    FIELD_NUMBER_MUST_BE_GREATER_THAN_ZERO("0006", "The %s must be greater than 0");

    private final String code;
    private final String message;

    ErrorType(final String code, final String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}