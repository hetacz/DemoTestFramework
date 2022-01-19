package org.selenium.pom.api.actions;

import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.jetbrains.annotations.Contract;
import org.selenium.pom.utils.ConfigLoader;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class CartApi {

    private Cookies cookies;

    @Contract(pure = true)
    public CartApi() {}

    @Contract(pure = true)
    public CartApi(Cookies cookies) {
        this.cookies = cookies;
    }

    public Cookies getCookies() {
        return cookies;
    }

    public CartApi setCookies(Cookies cookies) {
        this.cookies = cookies;
        return this;
    }

    public Response addToCart(int productId, int quantity) { // todo update to product object
        Header header = new Header("content-type", "application/x-www-form-urlencoded");
        Headers headers = new Headers(header);

        HashMap<String, Object> formData = new HashMap<>();
        formData.put("product_sku", "");
        formData.put("product_id", productId);
        formData.put("quantity", quantity);

        if(cookies == null) {
            cookies = new Cookies();
        }

        Response response = given()
                .baseUri(ConfigLoader.getInstance().getBaseUrl())
                .headers(headers)
                .cookies(cookies)
                .formParams(formData)
                //.log()
                //.all()
                .when()
                .post("/?wc-ajax=add_to_cart")
                .then()
                //.log()
                //.all()
                .extract()
                .response();

        if(response.getStatusCode() != 200) {
            throw new RuntimeException("Failed to add the product" + productId + " to the cart, HTTP status code: " + response.getStatusCode());
        }

        this.cookies = response.getDetailedCookies();

        return response;
    }
}
