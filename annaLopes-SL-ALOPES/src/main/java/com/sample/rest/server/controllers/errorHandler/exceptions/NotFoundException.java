package com.sample.rest.server.controllers.errorHandler.exceptions;

import com.sample.rest.server.controllers.errorHandler.ErrorResponse;
import com.sample.rest.server.controllers.errorHandler.ErrorType;

import javax.ws.rs.core.Response.Status;

public class NotFoundException extends ResponseException {

    public NotFoundException() {
        super(new ErrorResponse(ErrorType.NOT_FOUND), Status.NOT_FOUND);
    }
}