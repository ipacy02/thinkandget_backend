package com.api.tests.authentication;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.api.applicationApi.AuthApi;
import com.api.payloads.AuthPayloads;
import com.api.constants.HttpStatus;
import com.api.constants.ExpectedMessages;
import com.api.constants.TestData;
import java.util.Map;

public class PasswordResetTests {

    @Test(priority = 1)
    public void realWorldScenario_UserRequestsPasswordResetLink() {
        // Step 1: Use your already registered email account
        Map<String, Object> forgotPayload = AuthPayloads.getForgotPasswordPayload(TestData.STATIC_EMAIL);

        Response response = AuthApi.forgotPassword(forgotPayload);
        response.then().log().ifValidationFails();

        // Step 2: Assert the real-world success response code and message
        response.then().statusCode(HttpStatus.OK);
        Assert.assertEquals(response.jsonPath().getString(ExpectedMessages.KEY_MESSAGE), ExpectedMessages.FORGOT_PASSWORD_LINK_SENT);

        System.out.println(ExpectedMessages.LOG_FORGOT_PASSWORD_SENT);
    }

    @Test(priority = 2)
    public void realWorldScenario_ResetShouldFailWithInvalidOrExpiredToken() {
        // Step 1: Simulate using an incorrect/expired link token string
        String fakeOrExpiredToken = "expired_token_xyz_123";
        Map<String, Object> resetPayload = AuthPayloads.getResetPasswordPayload(TestData.NEW_PASSWORD);

        Response response = AuthApi.resetPassword(fakeOrExpiredToken, resetPayload);
        response.then().log().ifValidationFails();

        // Step 2: Real-world protection check - the server should reject this (400 Bad Request or 401 Unauthorized)
        // Adjust the expected status code below based on what your backend throws for a bad link!
        response.then().statusCode(400);

        Assert.assertFalse(response.jsonPath().getBoolean(ExpectedMessages.KEY_SUCCESS));
        System.out.println(">>> SUCCESS: System securely rejected password modification using an invalid link token.");
    }
}