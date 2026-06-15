package com.api.payloads;

import java.util.HashMap;
import java.util.Map;

public class AuthPayloads {


    public static Map<String, Object> getRegisterPayload(String email, String password, String firstName, String lastName, String phone) {

        Map<String, Object> payload = new HashMap<>();
        payload.put("email", email);
        payload.put("password", password);
        payload.put("firstName", firstName);
        payload.put("lastName", lastName);
        payload.put("phone", phone);
        return payload;
    }
    public static Map<String, Object> getLoginPayLoad(String email, String password) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("email", email);
        payload.put("password", password);
        return payload;
    }

}