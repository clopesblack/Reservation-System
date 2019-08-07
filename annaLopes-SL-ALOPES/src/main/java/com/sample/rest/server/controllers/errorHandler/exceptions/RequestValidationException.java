package com.sample.rest.server.controllers.errorHandler.exceptions;

import com.sample.rest.server.controllers.errorHandler.ErrorResponse;

import javax.ws.rs.core.Response.Status;

public class RequestValidationException extends ResponseException {

    public RequestValidationException(ErrorResponse errorResponse, Status status) {
        super(errorResponse, status);
    }
}
