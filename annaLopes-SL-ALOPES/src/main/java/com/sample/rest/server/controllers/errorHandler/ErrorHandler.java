package com.sample.rest.server.controllers.errorHandler;

import com.sample.rest.server.controllers.errorHandler.exceptions.ResponseException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static com.sample.rest.server.controllers.errorHandler.ErrorType.UNEXPECTED_ERROR;
import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;

@Provider
public class ErrorHandler implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(final Throwable exception) {
        if (exception instanceof ResponseException) {
            ResponseException responseException = (ResponseException) exception;
            return Response.status(responseException.getStatus()).entity(responseException.getErrorResponse()).build();
        }

        return Response.status(INTERNAL_SERVER_ERROR)
                .entity(new ErrorResponse(UNEXPECTED_ERROR)).build();
    }
}