package com.sample.rest.server.controllers;

import com.sample.rest.server.controllers.resources.search.SearchResource;
import com.sample.rest.server.core.services.SearchService;

import javax.inject.Inject;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.OK;

@Path("/search")
@Produces(MediaType.APPLICATION_JSON)
public class SearchController {

    private final SearchService service;

    @Inject
    public SearchController(final SearchService service) {
        this.service = service;
    }

    @GET
    public Response getExperiences(@BeanParam final SearchResource search) {
        return Response.status(OK).entity(service.getExperiences(search)).build();
    }
}