package com.sample.rest.server.core.domain;

import java.util.List;

import static java.util.Collections.singletonList;

public class PriceSearch {

    private final List<Experience> experiences;
    private final String date;
    private final Integer travelers;

    public PriceSearch(final List<Experience> experiences, final String date, final Integer travelers) {
        this.experiences = experiences;
        this.date = date;
        this.travelers = travelers;
    }

    public PriceSearch(final Experience experience, final String date, final Integer travelers) {
        this.experiences = singletonList(experience);
        this.date = date;
        this.travelers = travelers;
    }

    public List<Experience> getExperiences() {
        return experiences;
    }

    public String getDate() {
        return date;
    }

    public Integer getTravelers() {
        return travelers;
    }
}