package org.selenium.pom.pages;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.constants.Endpoint;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.account.OrdersPage;
import org.testng.Assert;

public class AccountPage extends BasePage {

    private final OrdersPage ordersPage;

    @FindBy(css = "button[value='Log in']")
    @CacheLookup
    private WebElement loginButton;
    @FindBy(css = "button[value='Register']")
    @CacheLookup
    private WebElement registerButton;
    @FindBy(css = ".woocommerce-MyAccount-content")
    @CacheLookup
    private WebElement welcomeMessage;

    public AccountPage(WebDriver driver) {
        super(driver);
        ordersPage = new OrdersPage(driver);
    }

    public AccountPage load() {
        load(Endpoint.ACCOUNT.url);
        wait.until(ExpectedConditions.titleContains("AskOmDch"));
        return this;
    }

    public AccountPage getAccountPageNotLoggedState() {
        Assert.assertTrue(getVisibleElement(loginButton).isEnabled(), "Login button is enabled");
        Assert.assertTrue(getVisibleElement(registerButton).isEnabled(), "Register button is enabled");
        return this;
    }

    public AccountPage getAccountPageLoggedState(@NotNull User user) {
        Assert.assertTrue(
                getVisibleElement(welcomeMessage).getText().contains(user.getUsername()),
                "Welcome message should contain username"
        );
        return this;
    }

    public OrdersPage getOrdersPage() {
        return ordersPage;
    }
}
