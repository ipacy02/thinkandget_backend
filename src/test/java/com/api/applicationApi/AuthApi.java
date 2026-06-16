package com.api.applicationApi;

import io.restassured.response.Response;
import java.util.HashMap;
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

    public static Response forgotPassword(Map<String, Object> requestBody) {
        return post("/auth/forgot-password", requestBody);
    }

    public static Response resetPassword(String token, Map<String, Object> requestBody) {
        return post("/auth/reset-password/" + token, requestBody);
    }

    // UPDATED METHOD: Leverages BaseApi logic safely
    public static Response getCurrentUser(String accessToken) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + accessToken);

        return get("/auth/me", headers);
    }

    public static Response refreshAccessToken(Map<String, Object> requestBody) {
        return post("/auth/refresh", requestBody);
    }
}