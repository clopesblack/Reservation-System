package com.sample.rest.server.core.domain;

import com.sample.rest.server.controllers.resources.book.PurchaseResource;

import java.math.BigDecimal;

public class Booking {

    private final Long code;
    private final String date;
    private final Integer travelers;
    private final BigDecimal price;
    private final String currency;

    public Booking(final PurchaseResource purchase, final Price price) {
        this.code = purchase.getCode();
        this.date = purchase.getDate();
        this.travelers = purchase.getTravelers();
        this.price = price.getPrice();
        this.currency = price.getCurrency();
    }

    public Long getCode() {
        return code;
    }

    public String getDate() {
        return date;
    }

    public Integer getTravelers() {
        return travelers;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }
}
