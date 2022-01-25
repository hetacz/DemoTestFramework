package org.selenium.pom.pages;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.constants.Coupon;
import org.selenium.pom.constants.Endpoint;
import org.selenium.pom.utils.StringUtils;
import org.testng.Assert;

public class CartPage extends BasePage {

    private final By overlay = By.cssSelector(".blockUI.blockOverlay");
    /*
    Without using PageFactory
    private final By productName = By.cssSelector("td[class='product-name'] a");
    private final By checkoutBtn = By.cssSelector(".checkout-button");
    */
    @FindBy(xpath = "(//table)[2]//td[@data-title='Subtotal']")
    private WebElement subtotalLabel;
    @FindBy(xpath = "(//table)[2]//td[contains(@data-title, 'Coupon')]")
    private WebElement couponLabel;
    @FindBy(xpath = "(//table)[2]//td[@data-title='Shipping']//bdi")
    private WebElement shippingLabel;
    @FindBy(xpath = "(//table)[2]//td[contains(@data-title, 'Tax')]")
    private WebElement taxLabel;
    @FindBy(xpath = "(//table)[2]//td[@data-title='Total']")
    private WebElement totalLabel;

    @FindBy(id = "coupon_code")
    @CacheLookup
    private WebElement couponInputFld;
    @FindBy(name = "apply_coupon")
    @CacheLookup
    private WebElement couponInputBtn;

    @FindBy(id = "shipping_method_0_free_shipping2")
    private WebElement freeShippingRadio;

    @FindBy(css = "td[class='product-name'] a")
    @CacheLookup
    private WebElement productName;
    @FindBy(css = ".checkout-button")
    @CacheLookup
    private WebElement checkoutBtn;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public CartPage load() {
        load(Endpoint.CART.url);
        wait.until(ExpectedConditions.titleContains("AskOmDch"));
        return this;
    }

    public String getProductName() {
        return getVisibleElement(productName).getText();
    }

    public CheckoutPage checkout() {
        getClickableElement(checkoutBtn);
        scrollIntoView(checkoutBtn).click();
        return new CheckoutPage(driver);
    }

    public float getShippingCost() { ;
        String s = getVisibleElement(shippingLabel).getText();
        return StringUtils.convertDollarsToFloat(s);
    }

    public String getCouponLabel() {
        return getVisibleElement(couponLabel).getText();
    }

    public CartPage applyCoupon(@NotNull Coupon coupon) {
        getVisibleElement(couponInputFld).clear();
        scrollIntoView(couponInputFld);
        getVisibleElement(couponInputFld).sendKeys(coupon.couponName);
        getClickableElement(couponInputBtn);
        scrollIntoView(couponInputBtn).click();
        waitForOverlaysToDisappear(overlay);
        return this;
    }

    public WebElement getFreeShippingRadio() {
        return getClickableElement(freeShippingRadio);
    }

    public CartPage checkIf25DiscountIsApplied() {
        final float subtotal = StringUtils.convertDollarsToFloat(getVisibleElement(subtotalLabel).getText());
        final float couponDiscount = StringUtils.convertDollarsToFloat(getVisibleElement(couponLabel).getText());
        final float discount = subtotal  / 4;

        Assert.assertEquals(couponDiscount * 100, Math.round(discount * 100)); // 2 digits precision
        return this;
    }
}
