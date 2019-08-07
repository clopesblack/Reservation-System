package com.sample.rest.server.endpoints;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.BeforeClass;

import static io.restassured.RestAssured.requestSpecification;
import static io.restassured.RestAssured.responseSpecification;
import static io.restassured.http.ContentType.JSON;

public abstract class AbstractControllerTest {

    @BeforeClass
    public static void setup() {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri("http://localhost:8080")
                .setBasePath("/api")
                .setContentType(JSON)
                .addFilter(new ErrorLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();
        responseSpecification = new ResponseSpecBuilder()
                .build();
    }
}