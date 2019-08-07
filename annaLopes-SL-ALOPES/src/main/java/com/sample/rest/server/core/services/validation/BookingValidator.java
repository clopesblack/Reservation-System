package com.sample.rest.server.core.services.validation;

import com.sample.rest.server.controllers.resources.book.PurchaseResource;
import com.sample.rest.server.core.utils.ValidatorHelper;

import javax.inject.Inject;

public class BookingValidator {

    private final ValidatorHelper helper;

    @Inject
    public BookingValidator(final ValidatorHelper helper) {
        this.helper = helper;
    }

    public void validate(final PurchaseResource resource) {
        helper.notNullOrEmpty(resource.getCode(), "location");
        helper.notNullOrEmpty(resource.getTravelers(), "travelers");
        helper.minValue(resource.getTravelers(), "travelers");
        helper.notNullOrEmpty(resource.getDate(), "date");
        helper.datePattern(resource.getDate());
        helper.futureDate(resource.getDate());
    }
}