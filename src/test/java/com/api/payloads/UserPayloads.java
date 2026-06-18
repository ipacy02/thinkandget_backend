package com.api.payloads;

import java.util.HashMap;
import java.util.Map;

public class UserPayloads {

    // Request payload for updating the user profile details
    public static Map<String, Object> getUpdateProfilePayload(String firstName, String lastName, String phone) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("firstName", firstName);
        payload.put("lastName", lastName);
        payload.put("phone", phone);
        return payload;
    }

    // Request payload for adding a new user address entry
    public static Map<String, Object> getAddAddressPayload(String street, String city, String country, String postalCode, String firstName, String lastName, String phone, String state) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("street", street);
        payload.put("city", city);
        payload.put("country", country);
        payload.put("postalCode", postalCode);
        payload.put("firstName", firstName);
        payload.put("lastName", lastName);
        payload.put("phone", phone);
        payload.put("state", state);
        return payload;
    }

    // Request payload for updating/changing the logged-in user's password
    public static Map<String, Object> getChangePasswordPayload(String oldPasswordKey, String oldPasswordVal, String newPasswordKey, String newPasswordVal) {
        Map<String, Object> payload = new HashMap<>();
        payload.put(oldPasswordKey, oldPasswordVal);
        payload.put(newPasswordKey, newPasswordVal);
        return payload;
    }
}