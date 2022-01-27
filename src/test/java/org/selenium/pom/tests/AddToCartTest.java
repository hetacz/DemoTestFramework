package org.selenium.pom.tests;

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
import java.util.Arrays;

public class AddToCartTest extends BaseTest {

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

    // @Test(description = "Add a single item to cart using API.")
    public void addToCartApi() throws IOException {
        Product product = new Product(BLUE_SHOES_ID);
        CartApi cartApi = new CartApi();
        cartApi.addToCart(product, 1);
        System.out.println(cartApi.getCookies().asList().size());
        cartApi.getCookies().asList().forEach(System.out::println);
        CartPage cartPage = new CartPage(getDriver());
        cartPage.load();
        injectCookiesToBrowser(cartApi.getCookies());
        final int uniqueItemsInCart = cartPage
                .load()
                .countUniqueItems();
        Assert.assertEquals(uniqueItemsInCart, 1);
    }

    // @Test(description = "Add all products to cart.")
    public void addAllProductsToCart() throws IOException, InterruptedException {
        Product[] products = JacksonUtils.deserializeJSON("products.json", Product[].class);
        CartApi cartApi = new CartApi();
        HomePage homePage = new HomePage(getDriver());
        homePage.load();
        Arrays.stream(products).forEach(product -> {
            cartApi.addToCart(product, 1);
            injectCookiesToBrowser(cartApi.getCookies());
        });
        CartPage cartPage = new CartPage(getDriver());
        cartPage.load();
        final int uniqueItemsInCart = cartPage
                .load()
                .countUniqueItems();
        Thread.sleep(10000);
        Assert.assertEquals(uniqueItemsInCart, products.length);
    }
}
