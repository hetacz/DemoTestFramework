package org.selenium.pom.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.selenium.pom.base.BasePage;

public class ProductPage extends BasePage {

    @FindBy(name = "add-to-cart")
    @CacheLookup
    private WebElement addToCartBtn;
    @FindBy(css = "div[role='alert'] a[class='button wc-forward']")
    @CacheLookup
    private WebElement viewCartBtn;
    @FindBy(css = "h1.product_title.entry-title")
    @CacheLookup
    private WebElement productHeader;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName() {
        return getVisibleElement(productHeader).getText();
    }

    public ProductPage addToCart() {
        getClickableElement(addToCartBtn);
        scrollIntoView(addToCartBtn).click();
        return this;
    }

    public CartPage clickViewCart() { // btn rather than link elsewhere on page
        getClickableElement(viewCartBtn);
        scrollIntoView(viewCartBtn).click();
        return new CartPage(driver);
    }
}
