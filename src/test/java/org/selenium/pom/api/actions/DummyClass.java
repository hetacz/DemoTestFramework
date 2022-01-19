package org.selenium.pom.api.actions;

import io.restassured.http.Cookies;
import org.selenium.pom.objects.User;
import org.selenium.pom.utils.FakerUtils;

public class DummyClass {

    public static void main(String[] args){

        // register();
        // addToCartNotLogged();
        addToCartLogged();
    }

    private static Cookies register() {

        final String username = "demoUser" + FakerUtils.generateRandomNumber();
        final String password = "qwe123";
        final String email = username + "@demo.test";

        User user = new User(
                username,
                password,
                email
        );

        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);
        System.out.println("REGISTER COOKIES: " + signUpApi.getCookies());
        return signUpApi.getCookies();
    }

    private static void addToCartNotLogged() {

        CartApi cartApi = new CartApi();
        cartApi.addToCart(1215, 1);
        System.out.println("CART COOKIES: " + cartApi.getCookies());
    }

    private static void addToCartLogged() {

        CartApi cartApi = new CartApi(register());
        cartApi.addToCart(1215, 1);
        System.out.println("CART COOKIES: " + cartApi.getCookies());
    }
}
