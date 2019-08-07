package com.sample.rest.server.controllers.resources.book;

import com.sample.rest.server.core.domain.Booking;

import java.math.BigDecimal;

public class PurchaseOrderResource {

    private final String status;
    private final String message;
    private final Long code;
    private final String date;
    private final Integer travelers;
    private final BigDecimal price;

    public PurchaseOrderResource(final PurchaseStatus purchaseStatus, final Booking booking) {
        this.status = purchaseStatus.getStatus();
        this.message = purchaseStatus.getMessage();
        this.code = booking.getCode();
        this.date = booking.getDate();
        this.travelers = booking.getTravelers();
        this.price = booking.getPrice();
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
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
}
