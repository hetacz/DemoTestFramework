package org.selenium.pom.pages;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

public class StorePage extends BasePage {

    @FindBy(id = "woocommerce-product-search-field-0") @CacheLookup
    private WebElement searchFld;
    @FindBy(css = "button[value='Search']") @CacheLookup
    private WebElement searchBtn;
    @FindBy(css = "a[title='View cart']") @CacheLookup
    private WebElement viewCartLink;
    @FindBy(css = ".woocommerce-products-header__title.page-title") // no cache lookup
    private WebElement title;

    public StorePage(WebDriver driver) {
        super(driver);
    }

    public StorePage load() {
        load("/store");
        return this;
    }

    public StorePage search(String text) {
        enterTextInSearchFld(text);
        clickSearchBtn();
        return this;
    }

    @Contract("_ -> this")
    private StorePage enterTextInSearchFld(String text) {
        WebElement element = getVisibleElement(searchFld);
        element.clear();
        element.sendKeys(text);
        return this;
    }

    @Contract(" -> this")
    private StorePage clickSearchBtn() {
        getClickableElement(searchBtn).click();
        return this;
    }

    public String getTitle() {
        return getVisibleElement(title).getText();
    }

    public StorePage waitForNewSearchTitle(String titleText) {
        wait.until(ExpectedConditions.textToBePresentInElement(title, "Search results: “" + titleText + "”"));
        return this;
    }

    @Contract("_ -> new")
    private @NotNull By getClickAddToCartBtn(String productName) {
        return By.cssSelector("a[aria-label='Add “" + productName + "” to your cart']");
    }

    public StorePage clickAddToCartBtn(String productName) {
        By addToCartBtn = getClickAddToCartBtn(productName);
        getClickableElement(addToCartBtn).click();
        return this;
    }

    public CartPage clickViewCart() {
        getClickableElement(viewCartLink).click();
        return new CartPage(driver);
    }
}
