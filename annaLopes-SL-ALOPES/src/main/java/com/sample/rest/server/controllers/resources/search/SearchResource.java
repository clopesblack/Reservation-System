package com.sample.rest.server.controllers.resources.search;

import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.ws.rs.QueryParam;

@JsonbPropertyOrder({"location, date, travelers"})
public class SearchResource {

    private String location;
    private String date;
    private Integer travelers;

    public String getLocation() {
        return location;
    }

    @QueryParam("location")
    public void setLocation(final String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    @QueryParam("date")
    public void setDate(final String date) {
        this.date = date;
    }

    public Integer getTravelers() {
        return travelers;
    }

    @QueryParam("travelers")
    public void setTravelers(final Integer travelers) {
        this.travelers = travelers;
    }
}