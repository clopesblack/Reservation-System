package com.sample.rest.server.controllers.resources.book;

public enum PurchaseStatus {

    SUCCESS("SUCCESS", "Your booking is confirmed."),
    FAILURE("FAILURE", "Your booking wasn't able to be confirmed."),
    SOLD_OUT("FAILURE", "The experience is sold out.");

    private final String status;
    private final String message;

    PurchaseStatus(final String status, final String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

}
