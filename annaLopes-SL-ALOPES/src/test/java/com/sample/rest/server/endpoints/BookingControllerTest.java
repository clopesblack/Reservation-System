package com.sample.rest.server.endpoints;

import com.sample.rest.server.controllers.resources.book.PurchaseResource;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.sample.rest.server.controllers.resources.book.PurchaseStatus.*;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static java.time.ZoneOffset.UTC;
import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.OK;
import static org.hamcrest.Matchers.is;

public class BookingControllerTest extends AbstractControllerTest {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    @Test
    public void shouldReturnExperiencesWhenGetByLocationAndDateAndTravelers() {
        String date = LocalDate.now(UTC).plusDays(10).format(DATE_TIME_FORMATTER);
        PurchaseResource purchaseResource = new PurchaseResource();
        purchaseResource.setCode(1115L);
        purchaseResource.setDate(date);
        purchaseResource.setTravelers(2);

        given().when()
                .contentType(JSON)
                .body(purchaseResource)
                .post("/booking")
                .then()
                .statusCode(CREATED.getStatusCode())
                .contentType(JSON)
                .body("code", is(1115))
                .body("date", is(date))
                .body("message", is(SUCCESS.getMessage()))
                .body("status", is(SUCCESS.getStatus()))
                .body("price", is(384.0f))
                .body("travelers", is(2));
    }

    @Test
    public void shouldReturnFailureSoldOutExperiencesWhenGetByLocationAndDateAndTravelers() {
        String date = LocalDate.now(UTC).plusDays(10).format(DATE_TIME_FORMATTER);
        PurchaseResource purchaseResource = new PurchaseResource();
        purchaseResource.setCode(1678L);
        purchaseResource.setDate(date);
        purchaseResource.setTravelers(10);

        given().when()
                .contentType(JSON)
                .body(purchaseResource)
                .post("/booking")
                .then()
                .statusCode(OK.getStatusCode())
                .contentType(JSON)
                .body("code", is(1678))
                .body("date", is(date))
                .body("message", is(SOLD_OUT.getMessage()))
                .body("status", is(SOLD_OUT.getStatus()))
                .body("price", is(3576.0f))
                .body("travelers", is(10));
    }
}
