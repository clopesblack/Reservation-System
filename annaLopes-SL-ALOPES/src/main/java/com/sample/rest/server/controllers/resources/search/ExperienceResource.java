package com.sample.rest.server.controllers.resources.search;

import javax.json.bind.annotation.JsonbPropertyOrder;

@JsonbPropertyOrder({"search, results"})
public class ExperienceResource {

    private final SearchResource search;
    private final ResultResource results;

    public ExperienceResource(final SearchResource search, final ResultResource results) {
        this.search = search;
        this.results = results;
    }

    public SearchResource getSearch() {
        return search;
    }

    public ResultResource getResults() {
        return results;
    }
}