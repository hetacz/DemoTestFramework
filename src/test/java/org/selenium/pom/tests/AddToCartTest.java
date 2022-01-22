package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.dataProviders.DataProvider;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddToCartTest extends BaseTest {

    @Test(description = "Add blue shoes to the cart from store page.")
    public void addToCartFromStorePage() throws IOException {
        final int BLUE_SHOES_ID = 1215;
        Product product = new Product(BLUE_SHOES_ID);
        StorePage storePage = new StorePage(getDriver());
        storePage
                .load()
                .getProductThumbnail()
                .clickAddToCartBtn(product);
        CartPage cartPage = storePage
                .getProductThumbnail()
                .clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), product.getName());
    }

    /*
    @Test
    public void addToCartFromFeatured() {
        final int index = 2; // from 0 to 4, 5 tiles
        HomePage homePage = new HomePage(getDriver());
        final String productName = homePage
                .load()
                .addToCartFeatured(index);
        CartPage cartPage = homePage.clickViewCart();
        Assert.assertEquals(productName, cartPage.getProductName());
    }

    @Test
    public void addToCartFromProductPage() {
        final int index = 2; // from 0 to 4, 5 tiles
        HomePage homePage = new HomePage(getDriver());
        final String productName = homePage
                .load()
                .clickFeatured(index);
        ProductPage productPage = new ProductPage(getDriver());
        Assert.assertEquals(productName, productPage.getProductName());
        CartPage cartPage = productPage
                .addToCart()
                .clickViewCart();
        Assert.assertEquals(productName, cartPage.getProductName());
    }
    */
    @Test(description = "Add featured products to the cart from home page.",
            dataProvider = "getFeaturedProducts", dataProviderClass = DataProvider.class)
    public void addToCartFeaturedProducts(Product product) {
        HomePage homePage = new HomePage(getDriver());
        CartPage cartPage = homePage
                .load()
                .getProductThumbnail()
                .clickAddToCartBtn(product)
                .clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), product.getName());
    }

    @Test(description = "Add bracelets products to cart from store page.",
            dataProvider = "getBraceletProducts", dataProviderClass = DataProvider.class)
    public void addToCartBraceletsFromStorePage(Product product) {
        StorePage storePage = new StorePage(getDriver());
        CartPage cartPage = storePage
                .load()
                .getProductThumbnail()
                .clickAddToCartBtn(product)
                .clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), product.getName());
    }
}
