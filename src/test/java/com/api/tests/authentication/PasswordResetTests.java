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
        Map<String, Object> forgotPayload = AuthPayloads.getForgotPasswordPayload(TestData.STATIC_EMAIL);

        Response response = AuthApi.forgotPassword(forgotPayload);
        response.then().log().ifValidationFails();

        response.then().statusCode(HttpStatus.OK);
        Assert.assertEquals(response.jsonPath().getString(ExpectedMessages.KEY_MESSAGE), ExpectedMessages.FORGOT_PASSWORD_LINK_SENT);

        System.out.println(ExpectedMessages.LOG_FORGOT_PASSWORD_SENT);
    }

    @Test(priority = 2)
    public void realWorldScenario_ResetShouldFailWithInvalidOrExpiredToken() {
        String fakeOrExpiredToken = "expired_token_xyz_123";
        Map<String, Object> resetPayload = AuthPayloads.getResetPasswordPayload(TestData.NEW_PASSWORD);

        Response response = AuthApi.resetPassword(fakeOrExpiredToken, resetPayload);
        response.then().log().ifValidationFails();

        response.then().statusCode(400);

        Assert.assertFalse(response.jsonPath().getBoolean(ExpectedMessages.KEY_SUCCESS));
    }
}