package com.api.tests.user;

import com.api.applicationApi.AuthApi;
import com.api.applicationApi.UserApi;
import com.api.constants.ExpectedMessages;
import com.api.constants.HttpStatus;
import com.api.constants.TestData;
import com.api.payloads.AuthPayloads;
import com.api.payloads.UserPayloads;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.File;
import java.util.Map;

public class UserManagementTests {

    private String loginAccessToken;

    @BeforeClass
    public void setupSessionToken() {
        // Leverages the clean factory build setup from AuthPayloads
        Map<String, Object> loginPayload = AuthPayloads.getLoginPayLoad(TestData.STATIC_EMAIL, TestData.STATIC_PASSWORD);

        Response response = AuthApi.login(loginPayload);
        response.then().statusCode(HttpStatus.OK);

        loginAccessToken = response.jsonPath().getString(ExpectedMessages.KEY_TOKEN);
        Assert.assertNotNull(loginAccessToken, ExpectedMessages.ERR_LOGIN_TOKEN_NULL);
    }

    @Test(priority = 1)
    public void shouldUpdateUserProfileSuccessfully() {
        Map<String, Object> updatePayload = UserPayloads.getUpdateProfilePayload(
                TestData.UPDATED_FIRST_NAME,
                TestData.UPDATED_LAST_NAME,
                TestData.PHONE_NUMBER
        );

        Response response = UserApi.updateProfile(loginAccessToken, updatePayload);
        response.then().statusCode(HttpStatus.OK);

        Assert.assertEquals(response.jsonPath().getString(ExpectedMessages.KEY_USER_FIRSTNAME),
                TestData.UPDATED_FIRST_NAME, ExpectedMessages.ERR_PROFILE_UPDATE_FAILED);
        System.out.println(ExpectedMessages.LOG_PROFILE_UPDATE_SUCCESS);
    }

    @Test(priority = 2)
    public void shouldAddAndRetrieveAddressesSuccessfully() {
        Map<String, Object> addressPayload = UserPayloads.getAddAddressPayload(
                TestData.ADDR_STREET,
                TestData.ADDR_CITY,
                TestData.ADDR_COUNTRY,
                TestData.ADDR_POSTAL_CODE,
                TestData.FIRST_NAME,
                TestData.LAST_NAME,
                TestData.PHONE_NUMBER,
                TestData.ADDR_STATE
        );

        Response addResponse = UserApi.addAddress(loginAccessToken, addressPayload);
        int currentStatus = addResponse.getStatusCode();
        Assert.assertTrue(currentStatus == HttpStatus.OK || currentStatus == HttpStatus.CREATED);

        Response getResponse = UserApi.getAddresses(loginAccessToken);
        getResponse.then().statusCode(HttpStatus.OK);

        Assert.assertNotNull(getResponse.jsonPath().getList(ExpectedMessages.KEY_ADDRESS_DATA),
                ExpectedMessages.ERR_ADDRESS_EMPTY);
        System.out.println(ExpectedMessages.LOG_ADDRESS_WORKFLOW_SUCCESS);
    }

    @Test(priority = 3)
    public void shouldUploadAvatarSuccessfully() {
        File avatarFile = new File(TestData.PATH_MOCK_AVATAR);

        if (avatarFile.exists()) {
            Response response = UserApi.uploadAvatar(loginAccessToken, avatarFile);
            response.then().statusCode(HttpStatus.OK);

            Assert.assertNotNull(response.jsonPath().getString(ExpectedMessages.KEY_USER_AVATAR));
            System.out.println(ExpectedMessages.LOG_AVATAR_UPLOAD_SUCCESS);
        } else {
            System.out.println(">>> SKIPPED: Place an image at '" + TestData.PATH_MOCK_AVATAR + "' to run avatar tests.");
        }
    }

    @Test(priority = 4)
    public void shouldChangeUserPasswordSuccessfully() {
        Map<String, Object> passwordPayload = UserPayloads.getChangePasswordPayload(
                TestData.KEY_OLD_PASSWORD, TestData.STATIC_PASSWORD,
                TestData.KEY_NEW_PASSWORD, TestData.NEW_PASSWORD
        );

        Response response = UserApi.changePassword(loginAccessToken, passwordPayload);
        response.then().statusCode(HttpStatus.OK);
        System.out.println(ExpectedMessages.LOG_PASSWORD_CHANGE_SUCCESS);

        Map<String, Object> revertPayload = UserPayloads.getChangePasswordPayload(
                TestData.KEY_OLD_PASSWORD, TestData.NEW_PASSWORD,
                TestData.KEY_NEW_PASSWORD, TestData.STATIC_PASSWORD
        );
        UserApi.changePassword(loginAccessToken, revertPayload).then().statusCode(HttpStatus.OK);
    }
}