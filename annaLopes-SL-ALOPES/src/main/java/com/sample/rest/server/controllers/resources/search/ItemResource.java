package com.sample.rest.server.controllers.resources.search;

import com.sample.rest.server.core.domain.Price;

import java.math.BigDecimal;

public class ItemResource {

    private final Long code;
    private final BigDecimal price;

    public ItemResource(final Price price) {
        this.code = price.getCode();
        this.price = price.getPrice();
    }

    public Long getCode() {
        return code;
    }

    public BigDecimal getPrice() {
        return price;
    }
}