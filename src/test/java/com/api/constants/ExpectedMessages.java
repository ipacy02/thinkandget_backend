package com.api.constants;

public class ExpectedMessages {

    public static final String KEY_SUCCESS = "success";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_TOKEN = "data.token";


    public static final String REGISTRATION_SUCCESS = "Registration successful";
    public static final String EMAIL_ALREADY_EXISTS = "Email already registered";

    public static final String LOG_REGISTRATION_CONFLICT = ">>> CONFLICT: Email is ALREADY REGISTERED in the database.";
    public static final String LOG_LOGIN_SUCCESS = ">>> Successfully logged in! Extracted Session Token: ";

    public static final String ERR_TOKEN_NULL = "Login token extraction from data.token failed!";
}