package com.sample.rest.server.core.services.validation;

import com.sample.rest.server.controllers.resources.search.SearchResource;
import com.sample.rest.server.core.utils.ValidatorHelper;

import javax.inject.Inject;

public class SearchValidator {

    private final ValidatorHelper helper;

    @Inject
    public SearchValidator(final ValidatorHelper helper) {
        this.helper = helper;
    }

    public void validate(final SearchResource search) {
        helper.notNullOrEmpty(search.getLocation(), "location");
        helper.notNullOrEmpty(search.getDate(), "date");
        helper.datePattern(search.getDate());
        helper.futureDate(search.getDate());
        helper.notNullOrEmpty(search.getTravelers(), "travelers");
        helper.minValue(search.getTravelers(), "travelers");
    }
}