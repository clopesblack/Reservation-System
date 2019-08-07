package com.sample.rest.server.controllers.errorHandler.exceptions;

import com.sample.rest.server.controllers.errorHandler.ErrorResponse;

import javax.ws.rs.core.Response.Status;

public abstract class ResponseException extends RuntimeException {

    private final ErrorResponse errorResponse;
    private final Status status;

    public ResponseException(final ErrorResponse errorResponse, final Status status) {
        this.errorResponse = errorResponse;
        this.status = status;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public Status getStatus() {
        return status;
    }
}
