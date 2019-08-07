package com.sample.rest.server.core.utils;

import com.sample.rest.server.controllers.errorHandler.ErrorResponse;
import com.sample.rest.server.controllers.errorHandler.exceptions.RequestValidationException;

import java.time.format.DateTimeFormatter;

import static com.sample.rest.server.controllers.errorHandler.ErrorType.*;
import static java.time.LocalDate.now;
import static java.time.LocalDate.parse;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

public class ValidatorHelper {

    private static final String DATE_PATTERN_REGEX = "([12]\\d{3}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01]))";

    public void notNullOrEmpty(final Object o, final String field) {
        if (o == null || (o instanceof String && ((String) o).isEmpty())) {
            throw new RequestValidationException(
                    new ErrorResponse(FIELD_MUST_NOT_BE_NULL, field), BAD_REQUEST);
        }
    }

    public void datePattern(final String date) {
        if (!date.matches(DATE_PATTERN_REGEX)) {
            throw new RequestValidationException(
                    new ErrorResponse(FIELD_DATE_MUST_BE_ON_PATTERN), BAD_REQUEST);
        }
    }

    public void futureDate(final String date) {
        if (!parse(date, DateTimeFormatter.ofPattern("yyyyMMdd")).isAfter(now())) {
            throw new RequestValidationException(
                    new ErrorResponse(FIELD_DATE_MUST_BE_GREATER_THAN_TODAY), BAD_REQUEST);
        }
    }

    public void minValue(final Number number, final String field) {
        if (number.longValue() <= 0) {
            throw new RequestValidationException(
                    new ErrorResponse(FIELD_NUMBER_MUST_BE_GREATER_THAN_ZERO, field), BAD_REQUEST);
        }
    }
}