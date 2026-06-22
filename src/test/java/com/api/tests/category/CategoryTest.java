package com.api.tests.category;

import com.api.applicationApi.AuthApi;
import com.api.applicationApi.CategoryApi;
import com.api.constants.ExpectedMessages;
import com.api.constants.HttpStatus;
import com.api.constants.TestData;
import com.api.payloads.AuthPayloads;
import com.api.payloads.CategoryPayloads;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.DataFaker;

import java.util.Map;

public class CategoryTest {

    private String loginAccessToken;

    @BeforeClass
    public void setupSessionToken() {
        Map<String, Object> loginPayload = AuthPayloads.getLoginPayLoad(TestData.STATIC_EMAIL, TestData.STATIC_PASSWORD);

        Response response = AuthApi.login(loginPayload);
        response.then().statusCode(HttpStatus.OK);

        loginAccessToken = response.jsonPath().getString(ExpectedMessages.KEY_TOKEN);
        Assert.assertNotNull(loginAccessToken, ExpectedMessages.ERR_LOGIN_TOKEN_NULL);
    }

    @Test
    public void createCategories() {
        String dynamicCategoryName = DataFaker.getUniqueCategoryName();

        Map<String, Object> catePayloads = CategoryPayloads.getCreateCategoryPayload(
                dynamicCategoryName,
                TestData.CATEGORY_DESCRIPTION
        );

        Response addCategories = CategoryApi.createCategory(loginAccessToken ,catePayloads);
        addCategories.then().statusCode(HttpStatus.CREATED);

        String categoryId = addCategories.jsonPath().getString(ExpectedMessages.KEY_CATEGORY_ID);
        Assert.assertNotNull(categoryId, ExpectedMessages.ERR_CATEGORY_ID_NULL);

        String categorySlug = addCategories.jsonPath().getString("data.slug");

        Response getCategories = CategoryApi.getCategories();
        getCategories.then().statusCode(HttpStatus.OK);

        Response getSingleCategories = CategoryApi.getSingleCategory(categorySlug);
        getSingleCategories.then().statusCode(HttpStatus.OK);

        Assert.assertEquals(getSingleCategories.jsonPath().getString(ExpectedMessages.KEY_CATEGORY_NAME),
                dynamicCategoryName, ExpectedMessages.ERR_CATEGORY_NAME_MISMATCH);

        System.out.println(ExpectedMessages.LOG_CATEGORY_WORKFLOW_SUCCESS + " Verified Slug: " + categorySlug);
    }
}