package com.api.applicationApi;

import io.restassured.response.Response;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class UserApi extends BaseApi {

    private static Map<String, String> getAuthHeader(String accessToken) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + accessToken);
        return headers;
    }

    public static Response updateProfile(String accessToken, Map<String, Object> requestBody) {
        return put("/users/profile", getAuthHeader(accessToken), requestBody);
    }

    public static Response uploadAvatar(String accessToken, File avatarFile) {
        return post("/users/avatar", getAuthHeader(accessToken), "avatar", avatarFile);
    }

    public static Response changePassword(String accessToken, Map<String, Object> requestBody) {
        return put("/users/change-password", getAuthHeader(accessToken), requestBody);
    }

    public static Response getAddresses(String accessToken) {
        return get("/users/addresses", getAuthHeader(accessToken));
    }

    public static Response addAddress(String accessToken, Map<String, Object> requestBody) {
        return post("/users/addresses", getAuthHeader(accessToken), requestBody);
    }
}