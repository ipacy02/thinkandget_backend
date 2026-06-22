package com.api.payloads;

import java.util.HashMap;
import java.util.Map;

public class CategoryPayloads {

    public static Map<String, Object> getCreateCategoryPayload(String name, String description) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("name", name);
        payload.put("description", description);
        return payload;
    }
}
