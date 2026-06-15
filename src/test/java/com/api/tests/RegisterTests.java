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

public class RegisterTests {

    @Test
    public void shouldHandleRegistrationBasedOnEmailStatus() {

        Map<String, Object> registerPayload = AuthPayloads.getRegisterPayload(
                TestData.STATIC_EMAIL,
                TestData.STATIC_PASSWORD,
                TestData.FIRST_NAME,
                TestData.LAST_NAME,
                TestData.PHONE_NUMBER
        );

        Response response = AuthApi.register(registerPayload);
        int statusCode = response.getStatusCode();

        if (statusCode == HttpStatus.CREATED) {

            Assert.assertTrue(response.jsonPath().getBoolean(ExpectedMessages.KEY_SUCCESS));
            Assert.assertEquals(response.jsonPath().getString(ExpectedMessages.KEY_MESSAGE), ExpectedMessages.REGISTRATION_SUCCESS);

            // Confirm a token path was cleanly generated
            String token = response.jsonPath().getString(ExpectedMessages.KEY_TOKEN);
            Assert.assertNotNull(token, ExpectedMessages.ERR_TOKEN_NULL);

        } else if (statusCode == HttpStatus.CONFLICT) {
            // The email was ALREADY REGISTERED
            System.out.println(ExpectedMessages.LOG_REGISTRATION_CONFLICT);

            Assert.assertFalse(response.jsonPath().getBoolean(ExpectedMessages.KEY_SUCCESS));
            Assert.assertEquals(response.jsonPath().getString(ExpectedMessages.KEY_MESSAGE), ExpectedMessages.EMAIL_ALREADY_EXISTS);

        } else {
            // for fallback failures such as 400 Bad Request or 500 Internal Error
            Assert.fail("Unexpected status code returned from server: " + statusCode +
                    " | Response Body: " + response.getBody().asString());

        }
    }
}