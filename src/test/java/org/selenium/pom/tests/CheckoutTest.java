package org.selenium.pom.tests;

import org.selenium.pom.api.actions.CartApi;
import org.selenium.pom.api.actions.SignUpApi;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.utils.FakerUtils;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class CheckoutTest extends BaseTest {

    @Test
    public void guestCheckoutUsingDirectBankTransfer() throws IOException {

        BillingAddress billingAddress = JacksonUtils.deserializeJSON("myBillingAddress.json",BillingAddress.class);

        CheckoutPage checkoutPage = new CheckoutPage(getDriver());
        checkoutPage.load();
        CartApi cartApi = new CartApi();
        cartApi.addToCart(1215, 1);
        injectCookiesToBrowser(cartApi.getCookies());
        checkoutPage
                .load()
                .setBillingAddress(billingAddress)
                .selectDirectBankTransferRadioButton()
                .placeOrder();
        Assert.assertEquals(checkoutPage.getNotice(), "Thank you. Your order has been received.");
    }

    @Test
    public void loginAndCheckoutUsingDirectBankTransfer() throws IOException {

        BillingAddress billingAddress = JacksonUtils.deserializeJSON("myBillingAddress.json",BillingAddress.class);

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
        CartApi cartApi = new CartApi(signUpApi.getCookies());
        Product product = new Product(1215);
        cartApi.addToCart(product.getId(), 1);

        CheckoutPage checkoutPage = new CheckoutPage(getDriver());
        checkoutPage.load();
        injectCookiesToBrowser(signUpApi.getCookies());
        checkoutPage
                .load()
                .setBillingAddress(billingAddress)
                .selectDirectBankTransferRadioButton()
                .placeOrder();
    }
}
