package com.sample.rest.server.controllers.resources.search;

import javax.json.bind.annotation.JsonbPropertyOrder;
import java.util.List;

@JsonbPropertyOrder({"matching, items"})
public class ResultResource {

    private final Integer matching;
    private final List<ItemResource> items;

    public ResultResource(final List<ItemResource> items) {
        this.matching = items.size();
        this.items = items;
    }

    public Integer getMatching() {
        return matching;
    }

    public List<ItemResource> getItems() {
        return items;
    }
}