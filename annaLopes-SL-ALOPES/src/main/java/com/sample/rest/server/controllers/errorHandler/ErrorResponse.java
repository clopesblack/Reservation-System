package com.sample.rest.server.controllers.errorHandler;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

public class ErrorResponse {

    private final ErrorType errorType;
    private final List<String> parameters;

    public ErrorResponse(final ErrorType errorType, final String parameters) {
        this.errorType = errorType;
        this.parameters = singletonList(parameters);
    }

    public ErrorResponse(final ErrorType errorType) {
        this.errorType = errorType;
        this.parameters = emptyList();
    }

    public String getCode() {
        return this.errorType.getCode();
    }

    public String getMessage() {
        return String.format(errorType.getMessage(), parameters);
    }
}