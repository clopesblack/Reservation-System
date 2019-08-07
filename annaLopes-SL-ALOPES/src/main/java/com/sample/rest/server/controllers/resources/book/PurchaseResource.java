package com.sample.rest.server.controllers.resources.book;

public class PurchaseResource {

    private Long code;
    private String date;
    private Integer travelers;

    public Long getCode() {
        return code;
    }

    public void setCode(final Long code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(final String date) {
        this.date = date;
    }

    public Integer getTravelers() {
        return travelers;
    }

    public void setTravelers(final Integer travelers) {
        this.travelers = travelers;
    }
}
