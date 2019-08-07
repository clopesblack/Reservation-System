package com.sample.rest.server.controllers;

import com.sample.rest.server.controllers.resources.book.PurchaseOrderResource;
import com.sample.rest.server.controllers.resources.book.PurchaseResource;
import com.sample.rest.server.core.services.BookingService;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import static com.sample.rest.server.controllers.resources.book.PurchaseStatus.FAILURE;
import static com.sample.rest.server.controllers.resources.book.PurchaseStatus.SOLD_OUT;
import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.OK;

@Path("/booking")
@Produces(MediaType.APPLICATION_JSON)
public class BookingController {

    private BookingService service;

    @Inject
    public BookingController(final BookingService service) {
        this.service = service;
    }

    @POST
    public Response book(final PurchaseResource resource) {
        PurchaseOrderResource book = service.book(resource);
        return Response.status(getStatus(book)).entity(book).build();
    }

    private Status getStatus(final PurchaseOrderResource book) {
        if (FAILURE.getStatus().equals(book.getStatus()) || SOLD_OUT.getStatus().equals(book.getStatus())) {
            return OK;
        }
        return CREATED;
    }
}