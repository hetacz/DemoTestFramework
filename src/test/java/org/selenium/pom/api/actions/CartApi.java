package org.selenium.pom.api.actions;

import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.SimpleLog;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.selenium.pom.api.ApiRequest;
import org.selenium.pom.constants.Endpoint;
import org.selenium.pom.objects.Product;

import java.util.HashMap;

public class CartApi extends ApiRequest {

    private static final Log LOG = new SimpleLog(CartApi.class.getPackageName() + " " + CartApi.class.getSimpleName());
    private Cookies cookies;
    public Cookie cookie;

    @Contract(pure = true)
    public CartApi() { }

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

    public Response addToCart(@NotNull Product product, int quantity) {
        Header header = new Header("content-type", "application/x-www-form-urlencoded");
        Headers headers = new Headers(header);

        HashMap<String, String> formData = new HashMap<>();
        formData.put("product_sku", "");
        formData.put("product_id", String.valueOf(product.getId()));
        formData.put("quantity", String.valueOf(quantity));

        if (cookies == null) {
            cookies = new Cookies();
        }

        Response response = post(Endpoint.ADD_TO_CART.url, cookies, headers, formData);

        if (response.getStatusCode() != 200) {
            throw new RuntimeException(
                    "Failed to add the product" + product.getId() + " to the cart, HTTP status code: " +
                            response.getStatusCode());
        }

        LOG.info("Product" + product.getName() + "added");
        this.cookies = response.getDetailedCookies();
        return response;
    }
}
