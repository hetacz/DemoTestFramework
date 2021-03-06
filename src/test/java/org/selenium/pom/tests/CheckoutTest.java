package org.selenium.pom.tests;

import org.selenium.pom.api.actions.CartApi;
import org.selenium.pom.api.actions.SignUpApi;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.AccountPage;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.utils.FakerUtils;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class CheckoutTest extends BaseTest {

    private static final String THANK_YOU_MESSAGE = "Thank you. Your order has been received.";
    private static final int BLUE_SHOES_ID = 1215;

    @Test(description = "Checkout as guest using direct bank transfer method.")
    public void guestCheckoutUsingDirectBankTransfer() throws IOException {
        BillingAddress[] billingAddresses =
                JacksonUtils.deserializeJSON("billingAddress.json", BillingAddress[].class);
        BillingAddress billingAddress = billingAddresses[2];
        CheckoutPage checkoutPage = new CheckoutPage(getDriver());
        checkoutPage.load();
        CartApi cartApi = new CartApi();
        Product product = new Product(BLUE_SHOES_ID);
        cartApi.addToCart(product, 1);

        injectCookiesToBrowser(cartApi.getCookies());
        checkoutPage
                .load()
                .setBillingAddress(billingAddress)
                .selectDirectBankTransferRadioButton()
                .placeOrder();
        Assert.assertEquals(checkoutPage.getNotice(), THANK_YOU_MESSAGE);
    }

    @Test(description = "Login during checkout using cash on delivery payment method.")
    public void loginAndCheckoutUsingCashOnDelivery() throws IOException {
        BillingAddress[] billingAddresses =
                JacksonUtils.deserializeJSON("billingAddress.json", BillingAddress[].class);
        BillingAddress billingAddress = billingAddresses[2];
        final String username = "demoUser" + FakerUtils.generateRandomNumber();
        final String password = "qwe123";
        final String email = username + "@demo.test";
        User user = new User(username, password, email);

        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);
        CartApi cartApi = new CartApi(signUpApi.getCookies());
        Product product = new Product(BLUE_SHOES_ID);
        cartApi.addToCart(product, 1);

        CheckoutPage checkoutPage = new CheckoutPage(getDriver());
        checkoutPage.load();
        injectCookiesToBrowser(signUpApi.getCookies());
        checkoutPage
                .load()
                .setBillingAddress(billingAddress)
                .selectCashOnDeliveryRadioButton()
                .placeOrder();
        Assert.assertEquals(checkoutPage.getNotice(), THANK_YOU_MESSAGE);
    }

    @Test(description = "As a fresh user, make a purchase and then view transaction history.")
    public void userIsAbleToSeePreviousOrders() throws IOException {
        BillingAddress[] billingAddresses =
                JacksonUtils.deserializeJSON("billingAddress.json", BillingAddress[].class);
        BillingAddress billingAddress = billingAddresses[2];
        final String username = "demoUser" + FakerUtils.generateRandomNumber();
        final String password = "qwe123";
        final String email = username + "@demo.test";
        User user = new User(username, password, email);
        Product product = new Product(BLUE_SHOES_ID);

        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);
        CartApi cartApi = new CartApi(signUpApi.getCookies());
        cartApi.addToCart(product, 1);

        CheckoutPage checkoutPage = new CheckoutPage(getDriver());
        checkoutPage.load();
        injectCookiesToBrowser(signUpApi.getCookies());
        final int orderNumberThankYouPage = checkoutPage
                .load()
                .setBillingAddress(billingAddress)
                .selectCashOnDeliveryRadioButton()
                .placeOrder()
                .getOrderNumber();
        AccountPage accountPage = new AccountPage(getDriver());
        final int orderNumberAccountPage = accountPage
                .getOrdersPage()
                .load()
                .getOrderNumber();
        Assert.assertEquals(orderNumberThankYouPage, orderNumberAccountPage, "Last order should be visible in /account/orders/");
        final String ordersPageUrl = accountPage
                .getOrdersPage()
                .clickFirstViewOrderBtn()
                .getPageUrl();
        Assert.assertTrue(ordersPageUrl.contains(String.valueOf(orderNumberAccountPage)), "/account/view-order/ url contains order number");
    }
}
