package org.selenium.pom.tests;

import org.selenium.pom.api.actions.CartApi;
import org.selenium.pom.api.actions.SignUpApi;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.utils.FakerUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends BaseTest {

    @Test
    public void loginDuringCheckout() throws IOException {

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
        CartApi cartApi = new CartApi();
        Product product = new Product(1215);
        cartApi.addToCart(product.getId(), 1);

        CheckoutPage checkoutPage = new CheckoutPage(getDriver());
        checkoutPage.load();
        // one can only inject cookies when on a proper website, it won't work, when you are not visiting any specific website.
        injectCookiesToBrowser(cartApi.getCookies());
        checkoutPage
                .load()
                .clickToLogin()
                .login(user);
        Assert.assertTrue(checkoutPage.getProductName().contains(product.getName()), "Selected product name visible is as intended.");
    }
}
