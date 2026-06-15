package com.api.applicationApi;

import io.restassured.response.Response;
import java.util.Map;

public class AuthApi extends BaseApi {

    public static Response register(Map<String, Object> requestBody) {
        return post("/auth/register", requestBody);
    }
    public static Response login(Map<String, Object> requestBody) {
        return post("/auth/login", requestBody);
    }
    public static Response verifyEmail(String token) {
        return get("/auth/verify-email/" + token);
    }
}