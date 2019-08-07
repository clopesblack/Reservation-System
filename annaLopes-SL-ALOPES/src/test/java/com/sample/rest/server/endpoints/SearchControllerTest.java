package com.sample.rest.server.endpoints;

import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static java.time.ZoneOffset.UTC;
import static javax.ws.rs.core.Response.Status;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static org.hamcrest.Matchers.*;

public class SearchControllerTest extends AbstractControllerTest {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    @Test
    public void shouldReturnExperiencesWhenGetByLocationAndDateAndTravelers() {
        String date = LocalDate.now(UTC).plusDays(10).format(DATE_TIME_FORMATTER);
        given().when()
                .contentType(JSON)
                .get("/search?location=ROME&date=" + date + "&travelers=1")
                .then()
                .statusCode(Status.OK.getStatusCode())
                .contentType(JSON)
                .body("search.date", is(date))
                .body("search.location", is("ROME"))
                .body("search.travelers", is(1))
                .body("results.matching", is(5))
                .body("results.items", hasSize(5))
                .body("results.items[0].code", is(1085))
                .body("results.items[0].price", is((177.6f)))
                .body("results.items[1].code", is(1115))
                .body("results.items[1].price", is((192.0f)));
    }

    @Test
    public void shouldReturnExperiencesWithEmptyItemsWhenGetByLocationThatNotExists() {
        given().when()
                .contentType(JSON)
                .get("/search?location=BRAZIL&date=20190720&travelers=1")
                .then()
                .statusCode(Status.OK.getStatusCode())
                .contentType(JSON)
                .body("search.date", is("20190720"))
                .body("search.location", is("BRAZIL"))
                .body("search.travelers", is(1))
                .body("results.matching", is(0))
                .body("results.items", empty());
    }

    @Test
    public void shouldReturnErrorWhenGetExperiencesWithNullParameter() {
        given().when()
                .contentType(JSON)
                .get("/search?location=&date=20190720&travelers=1")
                .then()
                .statusCode(BAD_REQUEST.getStatusCode())
                .contentType(JSON)
                .body("code", is("0003"))
                .body("message", is("The [location] must not be null"));
    }

    @Test
    public void shouldReturnErrorWhenGetExperiencesWithWrongPatternDate() {
        given().when()
                .contentType(JSON)
                .get("/search?location=ROME&date=2019-07-20&travelers=1")
                .then()
                .statusCode(BAD_REQUEST.getStatusCode())
                .contentType(JSON)
                .body("code", is("0004"))
                .body("message", is("Date must be formatter as 'yyyyMMdd'"));
    }

    @Test
    public void shouldReturnErrorWhenGetExperiencesWithDateNotGreaterThanToday() {
        given().when()
                .contentType(JSON)
                .get("/search?location=ROME&date=20190711&travelers=1")
                .then()
                .statusCode(BAD_REQUEST.getStatusCode())
                .contentType(JSON)
                .body("code", is("0005"))
                .body("message", is("Date must be greater than today"));
    }

    @Test
    public void shouldReturnErrorWhenGetExperiencesWithInvalidTravelers() {
        given().when()
                .contentType(JSON)
                .get("/search?location=ROME&date=20190720&travelers=0")
                .then()
                .statusCode(BAD_REQUEST.getStatusCode())
                .contentType(JSON)
                .body("code", is("0006"))
                .body("message", is("The [travelers] must be greater than 0"));
    }
}
