package com.sample.rest.server.core.domain;

import java.beans.ConstructorProperties;
import java.math.BigDecimal;

public class Price {

    private final Long code;
    private final BigDecimal price;
    private final String currency;

    @ConstructorProperties({"experience_id", "price", "currency"})
    public Price(final Long code, final BigDecimal price, final String currency) {
        this.code = code;
        this.price = price;
        this.currency = currency;
    }

    public Long getCode() {
        return code;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }
}
