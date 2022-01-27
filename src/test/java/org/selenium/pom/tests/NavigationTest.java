package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.data.DataProvider;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.ProductPage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class NavigationTest extends BaseTest {

    @Test(description = "Visit store page using main menu link from home page.")
    public void navigateFromHomeToStoreUsingMainMenu() {

        HomePage homePage = new HomePage(getDriver());
        homePage.load();
        StorePage storePage = homePage
                .getPageHeader()
                .navigateToStoreUsingMenu();
        Assert.assertEquals(storePage.getTitle(), "Store");
    }

    @Test(description = "Visit product's page by clicking on a product.")
    public void navigateFromStoreToProductPage() throws IOException {

        Product product = new Product(1215);

        StorePage storePage = new StorePage(getDriver());
        storePage
                .load()
                .clickProductByName(product);
        ProductPage productPage = new ProductPage(getDriver());
        Assert.assertEquals(
                productPage.getProductName(),
                product.getName(),
                "Product page should contain product name written in the middle."
        );
    }

    @Test(description = "Visit featured products' page by clicking on featured icons.",
            dataProvider = "getFeaturedProducts", dataProviderClass = DataProvider.class)
    public void navigateFromHomeToFeaturedProductPage(Product product) {

        HomePage homePage = new HomePage(getDriver());
        homePage
                .load()
                .getProductThumbnail()
                .clickThumbnailImage(product);
        ProductPage productPage = new ProductPage(getDriver());
        Assert.assertEquals(productPage.getProductName(), product.getName());
    }
}
