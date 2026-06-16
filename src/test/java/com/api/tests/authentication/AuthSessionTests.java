package com.api.tests.authentication;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.api.applicationApi.AuthApi;
import com.api.payloads.AuthPayloads;
import com.api.constants.HttpStatus;
import com.api.constants.ExpectedMessages;
import com.api.constants.TestData;
import java.util.Map;

public class AuthSessionTests {

    private String loginAccessToken;
    private String loginRefreshToken;

    @BeforeClass
    public void prerequisite_loginUser() {
        Map<String, Object> loginPayload = AuthPayloads.getLoginPayLoad(
                TestData.STATIC_EMAIL,
                TestData.STATIC_PASSWORD
        );

        Response response = AuthApi.login(loginPayload);
        response.then().statusCode(HttpStatus.OK);

        // Extract both tokens from the response structure
        loginAccessToken = response.jsonPath().getString("data.token");
        loginRefreshToken = response.jsonPath().getString("data.refreshToken");

        Assert.assertNotNull(loginAccessToken, ExpectedMessages.ERR_LOGIN_TOKEN_NULL);
    }

    @Test(priority = 1)
    public void shouldRetrieveCurrentUserProfileSuccessfully() {
        // Run the GET endpoint checking credentials state
        Response response = AuthApi.getCurrentUser(loginAccessToken);
        response.then().log().ifValidationFails();

        // Expecting 200 OK
        response.then().statusCode(HttpStatus.OK);
        Assert.assertTrue(response.jsonPath().getBoolean(ExpectedMessages.KEY_SUCCESS));

        // Asserting specific key matches the account we authenticated with
        Assert.assertEquals(response.jsonPath().getString("data.user.email"), TestData.STATIC_EMAIL);
        System.out.println(ExpectedMessages.LOG_GET_ME_SUCCESS);
    }

    @Test(priority = 2)
    public void shouldRefreshAccessTokenSuccessfully() {
        // Assert we have a valid refresh token string before execution
        Assert.assertNotNull(loginRefreshToken, "Refresh token from initial authentication is missing!");

        // Build request payload map block
        Map<String, Object> refreshPayload = AuthPayloads.getRefreshPayload(loginRefreshToken);

        Response response = AuthApi.refreshAccessToken(refreshPayload);
        response.then().log().ifValidationFails();

        // Expecting 200 OK for a valid token generation swap
        response.then().statusCode(HttpStatus.OK);
        Assert.assertTrue(response.jsonPath().getBoolean(ExpectedMessages.KEY_SUCCESS));

        // Pull out your fresh dynamic system token
        String brandNewAccessToken = response.jsonPath().getString(ExpectedMessages.KEY_TOKEN);
        Assert.assertNotNull(brandNewAccessToken, "Swapped renewal access token was returned null!");

        System.out.println(ExpectedMessages.LOG_REFRESH_SUCCESS);
    }
}