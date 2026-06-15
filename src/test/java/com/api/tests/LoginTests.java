package com.api.tests;

import com.api.constants.ExpectedMessages;
import com.api.constants.HttpStatus;
import com.api.constants.TestData;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.api.applicationApi.AuthApi;
import com.api.payloads.AuthPayloads;

import java.util.Map;

public class LoginTests {

    private String loginAccessToken;

    @Test
    public void testLoginSuccessfullyWithValidCredentials() {
        Map<String, Object> loginPayload = AuthPayloads.getLoginPayLoad(
                TestData.STATIC_EMAIL,
                TestData.STATIC_PASSWORD
        );

        Response response = AuthApi.login(loginPayload);
        response.then().log().ifValidationFails();

        response.then().statusCode(HttpStatus.OK);

        loginAccessToken = response.jsonPath().getString(ExpectedMessages.KEY_TOKEN);
        Assert.assertNotNull(loginAccessToken, ExpectedMessages.ERR_TOKEN_NULL);

        System.out.println(ExpectedMessages.LOG_LOGIN_SUCCESS + loginAccessToken);
    }
}