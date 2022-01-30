package org.selenium.pom.pages.account;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.SimpleLog;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.constants.Endpoint;

public class OrdersPage extends BasePage {

    private static final Log LOG = new SimpleLog(OrdersPage.class.getPackageName() + " " + OrdersPage.class.getSimpleName());
    @FindBy(xpath = "(//tbody/tr/td)[1]/a")
    @CacheLookup
    private WebElement orderNumberLink1;
    @FindBy(xpath = "(//a[@class='woocommerce-button button view'])[1]")
    @CacheLookup
    private WebElement viewOrderBtn1;

    public OrdersPage(WebDriver driver) {
        super(driver);
    }

    public int getOrderNumber() {
        return Integer.parseInt(orderNumberLink1.getText().trim().substring(1));
    }

    public OrdersPage load() {
        load(Endpoint.ORDERS.url);
        wait.until(ExpectedConditions.titleContains("AskOmDch"));
        LOG.debug(Endpoint.ORDERS.url + " page loaded.");
        return this;
    }

    public OrdersPage clickFirstViewOrderBtn() {
        getClickableElement(viewOrderBtn1);
        scrollIntoView(viewOrderBtn1).click();
        wait.until(ExpectedConditions.urlContains(Endpoint.VIEW_ORDER.url));
        LOG.debug("First view order button clicked.");
        return this;
    }

    public String getPageUrl() {
        return driver.getCurrentUrl();
    }
}
