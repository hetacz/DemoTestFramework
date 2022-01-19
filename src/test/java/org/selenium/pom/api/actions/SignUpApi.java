package org.selenium.pom.api.actions;

import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.selenium.pom.objects.User;
import org.selenium.pom.utils.ConfigLoader;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class SignUpApi {
    private Cookies cookies;

    public Cookies getCookies() {
        return cookies;
    }

    public SignUpApi setCookies(Cookies cookies) {
        this.cookies = cookies;
        return this;
    }

    /*    public String fetchRegisterNonceValueUsingGroovy() {
        Response response = getAccount();
        return response.htmlPath().getString("**.findAll{ it.@name == 'woocommerce-register-nonce' }.@value");
    }*/

    public String fetchRegisterNonceValue(){
        Response response = getAccount();
        Document document = Jsoup.parse(response.body().prettyPrint());
        Element element = document.selectFirst("#woocommerce-register-nonce");
        if(element == null) {
            throw new RuntimeException("Register form not found!");
        }
        return element.attr("value");
    }

    private Response getAccount() {
        Cookies cookies = new Cookies();
        Response response = given()
                .baseUri(ConfigLoader.getInstance().getBaseUrl())
                .cookies(cookies)
                //.log()
                //.all()
                .when()
                .get("/account")
                .then()
                //.log()
                //.all()
                .extract()
                .response();

        if(response.getStatusCode() != 200) {
            throw new RuntimeException("Failed to fetch the account, HTTP status code: " + response.getStatusCode());
        }

        return response;
    }

    public Response register(User user) {
        Cookies cookies = new Cookies();
        Header header = new Header("content-type", "application/x-www-form-urlencoded");
        Headers headers = new Headers(header);

        HashMap<String, String> formData = new HashMap<>();
        formData.put("username", user.getUsername());
        formData.put("email", user.getEmail());
        formData.put("password", user.getPassword());
        formData.put("woocommerce-register-nonce", fetchRegisterNonceValue());
        formData.put("register", "Register");

        Response response = given()
                .baseUri(ConfigLoader.getInstance().getBaseUrl())
                .headers(headers)
                .cookies(cookies)
                .formParams(formData)
                //.log()
                //.all()
                .when()
                .post("/account")
                .then()
                //.log()
                //.all()
                .extract()
                .response();

        if(response.getStatusCode() != 302) {
            throw new RuntimeException("Failed to register the account, HTTP status code: " + response.getStatusCode());
        }

        this.cookies = response.getDetailedCookies();

        return response;
    }
}
