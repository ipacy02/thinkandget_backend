package com.api.constants;

public class ExpectedMessages {

    // JSON Key Path Evaluators
    public static final String KEY_SUCCESS = "success";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_TOKEN = "data.token";
    public static final String KEY_REFRESH_TOKEN = "refreshToken"; // Key for refresh payload

    // Expected Text Contents for Responses
    public static final String REGISTRATION_SUCCESS = "Registration successful. Check your email to verify.";
    public static final String EMAIL_ALREADY_EXISTS = "Email already registered";
    public static final String RESET_EMAIL_SENT = "Reset email sent (always returns 200 for security)";
    public static final String FORGOT_PASSWORD_LINK_SENT = "If an account with that email exists, a reset link has been sent.";

    // Framework System Console Logs
    public static final String LOG_REGISTRATION_SUCCESS = ">>> SUCCESS: New user registered successfully!";
    public static final String LOG_REGISTRATION_CONFLICT = ">>> CONFLICT: Email is ALREADY REGISTERED in the database.";
    public static final String LOG_LOGIN_SUCCESS = ">>> Successfully logged in! Extracted Session Token: ";
    public static final String LOG_FORGOT_PASSWORD_SENT = ">>> SUCCESS: Forgot password email request triggered successfully.";
    public static final String LOG_PASSWORD_RESET_COMPLETE = ">>> SUCCESS: Account password has been updated with the token payload.";
    public static final String LOG_GET_ME_SUCCESS = ">>> SUCCESS: Retrieved authenticated user profile details successfully.";
    public static final String LOG_REFRESH_SUCCESS = ">>> SUCCESS: Swapped refresh token for a brand new Access Token.";

    // Assertion Error Messages
    public static final String ERR_TOKEN_NULL = "Reset token extraction from response body payload failed!";
    public static final String ERR_LOGIN_TOKEN_NULL = "Login token extraction from data.token failed!";
    public static final String ERR_RESET_TOKEN_NULL = "Reset token extraction from response body payload failed!";
}