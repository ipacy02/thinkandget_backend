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


    // =========================================================================
    // APPENDED FOR USERS MODULE (Leaves everything above completely untouched)
    // =========================================================================

    public static final String KEY_USER_FIRSTNAME = "data.firstName";
    public static final String KEY_USER_AVATAR = "data.avatar";
    public static final String KEY_ADDRESS_DATA = "data";

    public static final String KEY_CATEGORY_ID = "data.id";
    public static final String KEY_CATEGORY_NAME = "data.name"; // Added to support your TestNG name validation assertion

    public static final String LOG_PROFILE_UPDATE_SUCCESS = ">>> SUCCESS: Profile information updated smoothly without errors.";
    public static final String LOG_ADDRESS_WORKFLOW_SUCCESS = ">>> SUCCESS: Address entry added and list verified successfully.";
    public static final String LOG_AVATAR_UPLOAD_SUCCESS = ">>> SUCCESS: User avatar image uploaded to server database storage.";
    public static final String LOG_PASSWORD_CHANGE_SUCCESS = ">>> SUCCESS: Active account profile password updated securely.";
    public static final String ERR_PROFILE_UPDATE_FAILED = "Profile update failed: Expected names do not match!";
    public static final String ERR_ADDRESS_EMPTY = "Address collection is null or failed to return records!";

    public static final String LOG_CATEGORY_WORKFLOW_SUCCESS = ">>> SUCCESS: Category lifecycle workflow verified completely.";
    public static final String ERR_CATEGORY_ID_NULL = "Category Creation Failed: Returned ID is null!";
    public static final String ERR_CATEGORY_NAME_MISMATCH = "Category GET Assertion Failed: Names do not match!";
}