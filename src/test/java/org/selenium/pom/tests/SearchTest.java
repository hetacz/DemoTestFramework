package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.ProductPage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

    @Test(description = "Get search results using a partial match.")
    public void searchWithPartialMatch() {

        final String searchQuery = "Blue";

        StorePage storePage = new StorePage(getDriver());
        storePage
                .load()
                .search(searchQuery)
                .waitForNewSearchTitle(searchQuery);
        Assert.assertEquals(storePage.getTitle(), "Search results: “" + searchQuery + "”");
    }

    @Test(description = "Get search results using a exact match.")
    public void searchWithExactMatch() {

        final String searchQuery = "Blue Shoes";

        StorePage storePage = new StorePage(getDriver());
        storePage
                .load()
                .search(searchQuery);
        ProductPage productPage = new ProductPage(getDriver());
        Assert.assertEquals(productPage.getProductName(), searchQuery);
    }

    @Test(description = "Provide not existing query to search.")
    public void searchNotExistingProduct() {

        final String searchQuery = "qxv";

        StorePage storePage = new StorePage(getDriver());
        storePage
                .load()
                .search(searchQuery)
                .waitForNewSearchTitle(searchQuery);
        Assert.assertEquals(storePage.getTitle(), "Search results: “" + searchQuery + "”");
        Assert.assertEquals(storePage.getSearchInfo(), "No products were found matching your selection.");
    }
}
