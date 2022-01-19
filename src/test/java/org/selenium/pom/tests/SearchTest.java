package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

    @Test
    public void searchWithPartialMatch() {

        final String searchQuery = "Blue";

        StorePage storePage = new StorePage(getDriver());
        storePage
                .load()
                .search(searchQuery)
                .waitForNewSearchTitle(searchQuery);
        Assert.assertEquals(storePage.getTitle(), "Search results: “" + searchQuery + "”");
    }
}
