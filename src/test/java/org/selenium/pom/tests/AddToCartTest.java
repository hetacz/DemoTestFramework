package org.selenium.pom.tests;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.SimpleLog;
import org.selenium.pom.api.actions.CartApi;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.data.DataProvider;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddToCartTest extends BaseTest {
    private static final Log LOG = new SimpleLog(BaseTest.class.getPackageName() + " " + BaseTest.class.getSimpleName());
    private static final int BLUE_SHOES_ID = 1215;

    @Test(description = "Add blue shoes to the cart from store page.")
    public void addToCartFromStorePage() throws IOException {
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

    @Test(description = "Add a single item to cart using API.")
    public void addToCartApi() throws IOException {
        Product product = new Product(BLUE_SHOES_ID);
        CartApi cartApi = new CartApi();
        cartApi.addToCart(product, 1);
        cartApi.getCookies().asList().forEach(LOG::info);
        CartPage cartPage = new CartPage(getDriver());
        cartPage.load();
        injectCookiesToBrowser(cartApi.getCookies());
        final int uniqueItemsInCart = cartPage
                .load()
                .countUniqueItems();
        Assert.assertEquals(uniqueItemsInCart, 1, "There should be one, unique item in the cart.");
    }

    @Test(description = "Add all products to cart.")
    public void addAllProductsToCart() throws IOException {
        Product[] products = JacksonUtils.deserializeJSON("products.json", Product[].class);
        CartApi cartApi = new CartApi();
        CartPage cartPage = new CartPage(getDriver());

        cartPage.load();
        /*Arrays.stream(products).forEach(product -> {
            int p = product.getId();
            System.out.println(p);
            cartApi.addToCart(product, 1);
            injectCookiesToBrowser(cartApi.getCookies());
            cartPage.load();
        });*/
        final int uniqueItemsInCart = cartPage
                .load()
                .countUniqueItems();
        //Assert.assertEquals(uniqueItemsInCart, products.length);
    }
}
