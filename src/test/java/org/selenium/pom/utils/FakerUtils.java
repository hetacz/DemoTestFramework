package org.selenium.pom.utils;

import com.github.javafaker.Faker;

public class FakerUtils {

    public static long generateRandomNumber() {
        Faker faker = new Faker();
        return faker.number().randomNumber(8, true);
    }
}
