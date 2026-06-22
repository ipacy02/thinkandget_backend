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

    public static Response post(String endpoint, Map<String, String> headers, Map<String, Object> requestBody) {
        return given()
                .spec(SpecBuilder.getRequestSpec())
                .headers(headers)
                .body(requestBody)
                .when()
                .post(endpoint)
                .then()
                .spec(SpecBuilder.getResponseSpec())
                .extract()
                .response();
    }

    public static Response post(String endpoint, Map<String, String> headers, String controlName, java.io.File file) {
        return given()
                .spec(SpecBuilder.getMultipartRequestSpec())
                .headers(headers)
                .contentType("multipart/form-data")
                .multiPart(controlName, file)
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

    public static Response get(String endpoint, Map<String, String> headers) {
        return given()
                .spec(SpecBuilder.getRequestSpec())
                .headers(headers)
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

    public static Response put(String endpoint, Map<String, String> headers, Map<String, Object> requestBody) {
        return given()
                .spec(SpecBuilder.getRequestSpec())
                .headers(headers)
                .body(requestBody)
                .when()
                .put(endpoint)
                .then()
                .spec(SpecBuilder.getResponseSpec())
                .extract()
                .response();
    }
}