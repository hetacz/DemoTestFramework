package org.selenium.pom.tests;

import org.jetbrains.annotations.NotNull;
import org.selenium.pom.api.actions.CartApi;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.dataProviders.DataProvider;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.USStates;
import org.selenium.pom.pages.CartPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class TaxTest extends BaseTest {

    @Test(description = "Get to checkout as a guest and check US tax data for selected states.",
             dataProvider = "getUSStates", dataProviderClass = DataProvider.class)
    public void checkTaxRatesForSelectedUSStates(@NotNull USStates usStates) throws IOException {
        CartPage cartPage = new CartPage(getDriver());
        cartPage.load();
        CartApi cartApi = new CartApi();
        Product boho = new Product(1199);
        Product handbag = new Product(1193);
        // Two products worth $100
        cartApi.addToCart(boho, 1);
        injectCookiesToBrowser(cartApi.getCookies());

        cartApi.addToCart(handbag, 1);
        injectCookiesToBrowser(cartApi.getCookies());

        cartPage
                .load()
                .clickChangeAddress()
                .selectState(usStates.getLongName())
                .clickUpdateShippingBtn()
                .checkIfStateUpdated(usStates.getShortName());
        Assert.assertEquals(cartPage.getStateTax(), usStates.getTax(), "Checking tax of $100 cart.");
    }
}
