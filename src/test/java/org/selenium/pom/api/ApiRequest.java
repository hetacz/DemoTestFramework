package org.selenium.pom.api;

import io.restassured.http.Cookies;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.selenium.pom.utils.ConfigLoader;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ApiRequest {

    protected static Response get(String endPoint, Cookies cookies) {
        return given()
                .baseUri(ConfigLoader.getInstance().getBaseUrl())
                .cookies(cookies)
                .when()
                .get(endPoint)
                .then()
                .extract()
                .response();
    }

    protected static Response post(
            String endPoint, Cookies cookies, Headers headers, HashMap<String, String> formParams
    ) {
        return given()
                .baseUri(ConfigLoader.getInstance().getBaseUrl())
                .headers(headers)
                .cookies(cookies)
                .formParams(formParams)
                .when()
                .post(endPoint)
                .then()
                .extract()
                .response();
    }
}
