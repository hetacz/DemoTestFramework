package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class MyFirstTestCase extends BaseTest {

    private static final int BLUE_SHOES_ID = 1215;
    private static final String SEARCH_TERM = "Blue";
    private static final String THANK_YOU_MSG = "Thank you. Your order has been received.";
    private static final String STORE_PAGE_TITLE = "Store";

    @Test
    public void guestCheckoutUsingDirectBankTransfer() throws IOException {
        BillingAddress billingAddress = JacksonUtils.deserializeJSON("myBillingAddress.json",BillingAddress.class);
        Product product = new Product(BLUE_SHOES_ID);
        HomePage homePage = new HomePage(getDriver());
        homePage.load();
        StorePage storePage = homePage.navigateToStoreUsingMenu();
        /*
        Builder pattern used much more extensively might turn out a bit more readable.
        Up to me, which one I choose :)

        StorePage storePage1 = new HomePage(driver)
                .load()
                .navigateToStoreUsingMenu();
        */
        Assert.assertEquals(storePage.getTitle(), STORE_PAGE_TITLE);
        storePage
                .search(SEARCH_TERM)
                .waitForNewSearchTitle(SEARCH_TERM)
                .clickAddToCartBtn(product.getName());
        CartPage cartPage = storePage.clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), product.getName());
        CheckoutPage checkoutPage = cartPage.checkout();
        checkoutPage
                .setBillingAddress(billingAddress)
                .selectDirectBankTransferRadioButton()
                .placeOrder();
        Assert.assertEquals(checkoutPage.getNotice(), THANK_YOU_MSG);
        getDriver().quit();
    }

    @Test
    public void loginAndCheckoutUsingDirectBankTransfer() throws IOException {
        BillingAddress billingAddress = JacksonUtils.deserializeJSON("myBillingAddress.json",BillingAddress.class);
        User user = JacksonUtils.deserializeJSON("user.json", User.class);
        Product product = new Product(BLUE_SHOES_ID);
        HomePage homePage = new HomePage(getDriver());
        homePage.load();
        StorePage storePage = homePage.navigateToStoreUsingMenu();;
        Assert.assertEquals(storePage.getTitle(), STORE_PAGE_TITLE);
        storePage
                .search(SEARCH_TERM)
                .waitForNewSearchTitle(SEARCH_TERM)
                .clickAddToCartBtn(product.getName());
        CartPage cartPage = storePage.clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), product.getName());
        CheckoutPage checkoutPage = cartPage.checkout();
        checkoutPage
                .clickToLogin()
                .login(user)
                .setBillingAddress(billingAddress)
                .selectDirectBankTransferRadioButton()
                .placeOrder();
        Assert.assertEquals(checkoutPage.getNotice(), THANK_YOU_MSG);
        getDriver().quit();
    }
}
