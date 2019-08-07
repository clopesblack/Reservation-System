package com.sample.rest.server.core.domain;

import java.beans.ConstructorProperties;

public class Availability {

    private final Long code;
    private final Integer tickets;

    @ConstructorProperties({"experience_id", "tickets"})
    public Availability(final Long code, final Integer tickets) {
        this.code = code;
        this.tickets = tickets;
    }

    public Long getCode() {
        return code;
    }

    public Integer getTickets() {
        return tickets;
    }
}
