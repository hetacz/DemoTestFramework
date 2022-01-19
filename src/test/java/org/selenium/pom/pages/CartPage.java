package org.selenium.pom.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.selenium.pom.base.BasePage;

public class CartPage extends BasePage {

    /*
    Without using PageFactory
    private final By productName = By.cssSelector("td[class='product-name'] a");
    private final By checkoutBtn = By.cssSelector(".checkout-button");
    */
    @FindBy(css = "td[class='product-name'] a") @CacheLookup
    private WebElement productName;
    @FindBy(css = ".checkout-button") @CacheLookup
    private WebElement checkoutBtn;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName() {
        return getVisibleElement(productName).getText();
    }

    public CheckoutPage checkout() {
        getClickableElement(checkoutBtn).click();
        return new CheckoutPage(driver);
    }
}
