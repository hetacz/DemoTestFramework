package org.selenium.pom.tests;

import org.selenium.pom.api.actions.CartApi;
import org.selenium.pom.api.actions.SignUpApi;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.dataProviders.DataProvider;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.utils.FakerUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class CheckoutTest extends BaseTest {

    private static final String THANK_YOU_MESSAGE = "Thank you. Your order has been received.";

    @Test(description = "Checkout as guest using direct bank transfer payment method.",
            dataProvider = "getBillingAddress", dataProviderClass = DataProvider.class)
    public void guestCheckoutUsingDirectBankTransfer(BillingAddress billingAddress) throws IOException {
        CheckoutPage checkoutPage = new CheckoutPage(getDriver());
        checkoutPage.load();
        CartApi cartApi = new CartApi();
        Product product = new Product(1215);
        cartApi.addToCart(product, 1);

        injectCookiesToBrowser(cartApi.getCookies());
        checkoutPage
                .load()
                .setBillingAddress(billingAddress)
                .selectDirectBankTransferRadioButton()
                .placeOrder();
        Assert.assertEquals(checkoutPage.getNotice(), THANK_YOU_MESSAGE);
    }

    @Test(description = "Login during checkout using direct bank transfer payment method.",
            dataProvider = "getBillingAddress", dataProviderClass = DataProvider.class)
    public void loginAndCheckoutUsingDirectBankTransfer(BillingAddress billingAddress) throws IOException {
        final String username = "demoUser" + FakerUtils.generateRandomNumber();
        final String password = "qwe123";
        final String email = username + "@demo.test";
        User user = new User(username, password, email);

        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);
        CartApi cartApi = new CartApi(signUpApi.getCookies());
        Product product = new Product(1215);
        cartApi.addToCart(product, 1);

        CheckoutPage checkoutPage = new CheckoutPage(getDriver());
        checkoutPage.load();
        injectCookiesToBrowser(signUpApi.getCookies());
        checkoutPage
                .load()
                .setBillingAddress(billingAddress)
                .selectDirectBankTransferRadioButton()
                .placeOrder();
        Assert.assertEquals(checkoutPage.getNotice(), THANK_YOU_MESSAGE);
    }
    /*
    @Test(description = "As a fresh user, make a purchase and then view transaction history.")
    public void userIsAbleToSeePreviousOrders() throws IOException, InterruptedException {
        BillingAddress[] billingAddresses =
                JacksonUtils.deserializeJSON("billingAddress.json", BillingAddress[].class);
        BillingAddress billingAddress = billingAddresses[1];
        final String username = "demoUser" + FakerUtils.generateRandomNumber();
        final String password = "qwe123";
        final String email = username + "@demo.test";
        User user = new User(username, password, email);
        Product product = new Product(1193);

        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);
        signUpApi.login(user);
        CartApi cartApi = new CartApi(signUpApi.getCookies());
        cartApi.addToCart(product, 1);

    }*/
    /*
    @Test(description = "Get to checkout as a guest and check US tax data for selected states.",
            dataProvider = "getUSStates", dataProviderClass = DataProvider.class)
    public void checkTaxRatesForSelectedUSStates(@NotNull USStates usStates) throws IOException, InterruptedException {
        CheckoutPage checkoutPage = new CheckoutPage(getDriver());
        checkoutPage.load();
        CartApi cartApi = new CartApi();
        Product product1 = new Product(1202);
        Product product2 = new Product(1193);
        cartApi.addToCart(product1, 1);
        cartApi.addToCart(product2, 1);
        injectCookiesToBrowser(cartApi.getCookies());
        checkoutPage
                .load()
                .selectCountry("United States (US)")
                .selectState(usStates.getLongName())
                .assertThatTaxIsSetUpCorrectly(usStates);
    }*/
}
