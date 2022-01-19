package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddToCartTest extends BaseTest {

    @Test
    public void addToCartFromStorePage() throws IOException {

        final int BLUE_SHOES_ID = 1215;

        Product product = new Product(BLUE_SHOES_ID);
        StorePage storePage = new StorePage(getDriver());
        storePage
                .load()
                .clickAddToCartBtn(product.getName());
        CartPage cartPage = storePage.clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), product.getName());
    }
}
