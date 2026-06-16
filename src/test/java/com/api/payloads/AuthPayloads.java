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

    // Request payload for forgot password endpoint
    public static Map<String, Object> getForgotPasswordPayload(String email) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("email", email);
        return payload;
    }

    // Request payload for updating the password with token matching path keys
    public static Map<String, Object> getResetPasswordPayload(String newPassword) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("password", newPassword);
        return payload;
    }

    // Add this method to your existing com.api.payloads.AuthPayloads class
    public static Map<String, Object> getRefreshPayload(String refreshToken) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("refreshToken", refreshToken);
        return payload;
    }
}