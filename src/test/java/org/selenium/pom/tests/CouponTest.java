package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.constants.Coupon;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.selenium.pom.utils.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class CouponTest extends BaseTest {

    private static final int PRODUCT_ID = 1193;

    @Test(description = "Validate coupon 'freeship', for free shipment")
    public void validateCouponFreeship() throws IOException {
        Product product = new Product(PRODUCT_ID);
        HomePage homePage = new HomePage(getDriver());
        StorePage storePage = new StorePage(getDriver());
        CartPage cartPage = new CartPage(getDriver());
        homePage
                .load()
                .getPageHeader()
                .navigateToStoreUsingMenu();
        storePage
                .getProductThumbnail()
                .clickAddToCartBtn(product)
                .clickViewCart();
        Assert.assertTrue(cartPage.getShippingCost() > 0, "Shipping cost is greater than 0.");
        cartPage.applyCoupon(Coupon.FREESHIP);
        Assert.assertTrue(cartPage.getCouponLabel().contains("Free shipping coupon"), "Free shipping coupon is activated.");
        Assert.assertTrue(cartPage.getFreeShippingRadio().isSelected(), "Free shipping button should be selected.");
    }

    @Test(description = "Validate coupon 'offcart5', for flat $5 discount")
    public void validateCouponOffcart5() throws IOException {
        Product product = new Product(PRODUCT_ID);
        HomePage homePage = new HomePage(getDriver());
        StorePage storePage = new StorePage(getDriver());
        CartPage cartPage = new CartPage(getDriver());
        homePage
                .load()
                .getPageHeader()
                .navigateToStoreUsingMenu();
        storePage
                .getProductThumbnail()
                .clickAddToCartBtn(product)
                .clickViewCart();
        cartPage.applyCoupon(Coupon.OFFCART5);
        Assert.assertEquals(StringUtils.convertDollarsToFloat(cartPage.getCouponLabel()), 5, "Flat $5 off the cart.");
    }

    @Test(description = "Validate coupon 'off25', for 25% discount on entire cart")
    public void validateCouponOff25() throws IOException {
        Product product = new Product(PRODUCT_ID);
        HomePage homePage = new HomePage(getDriver());
        StorePage storePage = new StorePage(getDriver());
        CartPage cartPage = new CartPage(getDriver());
        homePage
                .load()
                .getPageHeader()
                .navigateToStoreUsingMenu();
        storePage
                .getProductThumbnail()
                .clickAddToCartBtn(product)
                .clickViewCart();
        cartPage
                .applyCoupon(Coupon.OFF25)
                .checkIf25DiscountIsApplied();
    }
}
