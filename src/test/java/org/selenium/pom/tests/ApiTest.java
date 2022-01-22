package org.selenium.pom.tests;

import io.restassured.http.Cookies;
import org.selenium.pom.api.actions.CartApi;
import org.selenium.pom.api.actions.SignUpApi;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.utils.FakerUtils;

import java.io.IOException;

public class ApiTest {
    /*
    @Test
    public void apiTest() throws IOException {

        // register();
        // addToCartNotLogged();
        addToCartLogged();
    }
    */
    private static Cookies register() {

        final String username = "demoUser" + FakerUtils.generateRandomNumber();
        final String password = "qwe123";
        final String email = username + "@demo.test";

        User user = new User(username, password, email);

        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);
        //System.out.println("REGISTER COOKIES: " + signUpApi.getCookies());
        return signUpApi.getCookies();
    }

    private static void addToCartNotLogged() throws IOException {

        Product product = new Product(1215); // blue shoes

        CartApi cartApi = new CartApi();
        cartApi.addToCart(product, 1);
        //System.out.println("CART COOKIES: " + cartApi.getCookies());
    }

    private static void addToCartLogged() throws IOException {

        Product product = new Product(1215); // blue shoes

        CartApi cartApi = new CartApi(register());
        cartApi.addToCart(product, 1);
        //System.out.println("CART COOKIES: " + cartApi.getCookies());
    }
}
