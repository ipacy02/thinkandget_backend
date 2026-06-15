package com.api.applicationApi;

import io.restassured.response.Response;
import java.util.Map;
import utils.SpecBuilder;

import static io.restassured.RestAssured.given;

public class BaseApi {

    public static Response post(String endpoint, Map<String, Object> requestBody) {
        return given()
                .spec(SpecBuilder.getRequestSpec())
                .body(requestBody)
                .when()
                .post(endpoint)
                .then()
                .spec(SpecBuilder.getResponseSpec())
                .extract()
                .response();
    }

    public static Response get(String endpoint) {
        return given()
                .spec(SpecBuilder.getRequestSpec())
                .when()
                .get(endpoint)
                .then()
                .spec(SpecBuilder.getResponseSpec())
                .extract()
                .response();
    }

    public static Response put(String endpoint, Map<String, Object> requestBody) {
        return given()
                .spec(SpecBuilder.getRequestSpec())
                .body(requestBody)
                .when()
                .put(endpoint)
                .then()
                .spec(SpecBuilder.getResponseSpec())
                .extract()
                .response();
    }
}