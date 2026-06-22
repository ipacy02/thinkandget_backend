package com.api.applicationApi;

import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

public class CategoryApi extends BaseApi {

    private static Map<String, String> getAuthHeader(String accessToken) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + accessToken);
        return headers;
    }

    public static Response getCategories() {
        return get("/categories");
    }

    public static Response createCategory(String accessToken, Map<String, Object> requestBody){
        return post("/categories", getAuthHeader(accessToken), requestBody);
    }

    public static Response getSingleCategory(String categoryId) {
        return get("/categories/" + categoryId);
    }
}