package utils;

import com.api.constants.TestData;
import com.github.javafaker.Faker;

public class DataFaker {

    private static final Faker faker = new Faker();

    /**
     * Generates a unique category name by combining the base name and a random number
     */
    public static String getUniqueCategoryName() {
        return TestData.CATEGORY_NAME + " " + faker.random().nextInt(1000, 9999);
    }
}