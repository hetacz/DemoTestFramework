package org.selenium.pom.tests.old;

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

import java.io.IOException;

public class EndToEndTest extends BaseTest {

    private static final int BLUE_SHOES_ID = 1215;
    private static final String SEARCH_TERM = "Blue";
    private static final String THANK_YOU_MSG = "Thank you. Your order has been received.";
    private static final String STORE_PAGE_TITLE = "Store";

    //@Test(description = "Buy a product and checkout as a guest.")
    public void guestCheckoutUsingDirectBankTransfer() throws IOException {

        BillingAddress[] billingAddresses =
                JacksonUtils.deserializeJSON("billingAddress.json", BillingAddress[].class);
        BillingAddress billingAddress = billingAddresses[0];
        Product product = new Product(BLUE_SHOES_ID);

        HomePage homePage = new HomePage(getDriver());
        homePage.load();
        StorePage storePage = homePage
                .getPageHeader()
                .navigateToStoreUsingMenu();
        Assert.assertEquals(storePage.getTitle(), STORE_PAGE_TITLE);
        storePage
                .search(SEARCH_TERM)
                .waitForNewSearchTitle(SEARCH_TERM)
                .getProductThumbnail()
                .clickAddToCartBtn(product);
        CartPage cartPage = storePage
                .getProductThumbnail()
                .clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), product.getName());
        CheckoutPage checkoutPage = cartPage.checkout();
        checkoutPage
                .setBillingAddress(billingAddress)
                .selectDirectBankTransferRadioButton()
                .placeOrder();
        Assert.assertEquals(checkoutPage.getNotice(), THANK_YOU_MSG);
    }

    //@Test(description = "Buy a product and login during checkout.")
    public void loginAndCheckoutUsingDirectBankTransfer() throws IOException {

        BillingAddress[] billingAddresses =
                JacksonUtils.deserializeJSON("billingAddress.json", BillingAddress[].class);
        BillingAddress billingAddress = billingAddresses[0];
        User user = JacksonUtils.deserializeJSON("user.json", User.class);
        Product product = new Product(BLUE_SHOES_ID);

        HomePage homePage = new HomePage(getDriver());
        homePage.load();
        StorePage storePage = homePage
                .getPageHeader()
                .navigateToStoreUsingMenu();
        Assert.assertEquals(storePage.getTitle(), STORE_PAGE_TITLE);
        storePage
                .search(SEARCH_TERM)
                .waitForNewSearchTitle(SEARCH_TERM)
                .getProductThumbnail()
                .clickAddToCartBtn(product);
        CartPage cartPage = storePage
                .getProductThumbnail()
                .clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), product.getName());
        CheckoutPage checkoutPage = cartPage.checkout();
        checkoutPage
                .clickToLogin()
                .login(user)
                .setBillingAddress(billingAddress)
                .selectDirectBankTransferRadioButton()
                .placeOrder();
        Assert.assertEquals(checkoutPage.getNotice(), THANK_YOU_MSG);
    }
}
