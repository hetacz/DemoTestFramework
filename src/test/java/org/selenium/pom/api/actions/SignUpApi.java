package org.selenium.pom.api.actions;

import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.jetbrains.annotations.NotNull;
import org.selenium.pom.api.ApiRequest;
import org.selenium.pom.constants.Endpoint;
import org.selenium.pom.objects.User;

import java.util.HashMap;

public class SignUpApi extends ApiRequest {

    private Cookies cookies;

    public Cookies getCookies() {
        return cookies;
    }

    public SignUpApi setCookies(Cookies cookies) {
        this.cookies = cookies;
        return this;
    }

    private String fetchRegisterNonceValue() {
        Response response = getAccount();
        return response.htmlPath().getString("**.findAll{ it.@name == 'woocommerce-register-nonce' }.@value");
    }

    private String fetchLoginNonceValue() {
        Response response = getAccount();
        return response.htmlPath().getString("**.findAll{ it.@name == 'woocommerce-login-nonce' }.@value");
    }

    /*
    private @NotNull String fetchRegisterNonceValue() {
        Response response = getAccount();
        Document document = Jsoup.parse(response.body().prettyPrint());
        Element element = document.selectFirst("#woocommerce-register-nonce");
        if (element == null) {
            throw new RuntimeException("Register form not found!");
        }
        return element.attr("value");
    }

    private @NotNull String fetchLoginNonceValue() {
        Response response = getAccount();
        Document document = Jsoup.parse(response.body().prettyPrint());
        Element element = document.selectFirst("#woocommerce-login-nonce");
        if (element == null) {
            throw new RuntimeException("Login form not found!");
        }
        return element.attr("value");
    }
     */

    private @NotNull Response getAccount() {
        Cookies cookies = new Cookies();
        Response response = get(Endpoint.ACCOUNT.url, cookies);
        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Failed to fetch the account, HTTP status code: " + response.getStatusCode());
        }

        return response;
    }

    public Response register(@NotNull User user) {
        Cookies cookies = new Cookies();
        Header header = new Header("content-type", "application/x-www-form-urlencoded");
        Headers headers = new Headers(header);

        HashMap<String, String> formData = new HashMap<>();
        formData.put("username", user.getUsername());
        formData.put("email", user.getEmail());
        formData.put("password", user.getPassword());
        formData.put("woocommerce-register-nonce", fetchRegisterNonceValue());
        formData.put("register", "Register");

        Response response = post(Endpoint.ACCOUNT.url, cookies, headers, formData);
        if (response.getStatusCode() != 302) {
            throw new RuntimeException("Failed to register the account, HTTP status code: " + response.getStatusCode());
        }

        this.cookies = response.getDetailedCookies();
        return response;
    }

    public Response login(@NotNull User user) {
        Cookies cookies = new Cookies();
        Header header = new Header("content-type", "application/x-www-form-urlencoded");
        Headers headers = new Headers(header);

        HashMap<String, String> formData = new HashMap<>();
        formData.put("username", user.getUsername());
        formData.put("password", user.getPassword());
        formData.put("woocommerce-login-nonce", fetchLoginNonceValue());
        formData.put("login", "Log in");

        Response response = post(Endpoint.ACCOUNT.url, cookies, headers, formData);
        if (response.getStatusCode() != 302) {
            throw new RuntimeException("Failed to log in, HTTP status code: " + response.getStatusCode());
        }
        if (response.getStatusCode() == 200) {
            throw new RuntimeException(
                    "Failed to log in: " + response.getStatusCode() + "status code appears even on failed calls.");
        }

        this.cookies = response.getDetailedCookies();
        return response;
    }
}
