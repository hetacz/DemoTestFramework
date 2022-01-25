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
import org.selenium.pom.constants.Endpoint;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.User;

public class CheckoutPage extends BasePage {

    private final By overlay = By.cssSelector(".blockUI.blockOverlay");
    @FindBy(id = "billing_first_name")
    @CacheLookup
    private WebElement firstNameFld;
    @FindBy(id = "billing_last_name")
    @CacheLookup
    private WebElement lastNameFld;
    @FindBy(id = "billing_address_1")
    @CacheLookup
    private WebElement billingAddress1FLd;
    @FindBy(id = "billing_city")
    @CacheLookup
    private WebElement billingCityFld;
    @FindBy(id = "billing_postcode")
    @CacheLookup
    private WebElement billingPostcodeFld;
    @FindBy(id = "billing_email")
    @CacheLookup
    private WebElement billingEmailFld;
    @FindBy(id = "place_order")
    @CacheLookup
    private WebElement placeOrderBtn;
    @FindBy(css = ".woocommerce-notice")
    @CacheLookup
    private WebElement successNotice;
    @FindBy(css = ".showlogin")
    @CacheLookup
    private WebElement showLoginBtn;
    @FindBy(id = "username")
    @CacheLookup
    private WebElement usernameFld;
    @FindBy(id = "password")
    @CacheLookup
    private WebElement passwordFld;
    @FindBy(name = "login")
    @CacheLookup
    private WebElement loginBtn;
    @FindBy(id = "billing_country")
    private WebElement countryDropdown;
    @FindBy(id = "billing_state")
    private WebElement stateDropdown;
    @FindBy(id = "payment_method_bacs")
    @CacheLookup
    private WebElement directBankTransferRadioBtn;
    @FindBy(id = "payment_method_cod")
    @CacheLookup
    private WebElement cashOnDeliveryRadioBtn;
    @FindBy(id = "select2-billing_state-container")
    private WebElement alternateStateDropdown;
    @FindBy(id = "select2-billing_country-container")
    private WebElement alternateCountryDropdown;
    @FindBy(css = "td[class='product-name']")
    @CacheLookup
    private WebElement productName;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage login(@NotNull User user) {
        enterUsername(user.getUsername());
        enterPassword(user.getPassword());
        clickLogin();
        waitForLoginBtnToDisappear();
        return this;
    }

    public CheckoutPage load() {
        load(Endpoint.CHECKOUT.url);
        wait.until(ExpectedConditions.titleContains("AskOmDch"));
        return this;
    }

    private WebElement getCountryName(String countryName) {
        return getClickableElement(driver.findElement(By.xpath("//li[text()='" + countryName + "']")));
    }

    private WebElement getStateName(String stateName) {
        return getClickableElement(driver.findElement(By.xpath("//li[text()='" + stateName + "']")));
    }

    @Contract("_ -> this")
    private CheckoutPage enterUsername(String username) {
        WebElement element = getVisibleElement(usernameFld);
        scrollIntoView(element);
        element.clear();
        element.sendKeys(username);
        return this;
    }

    @Contract("_ -> this")
    private CheckoutPage enterPassword(String password) {
        WebElement element = getVisibleElement(passwordFld);
        scrollIntoView(element);
        element.clear();
        element.sendKeys(password);
        return this;
    }

    @Contract(" -> this")
    private CheckoutPage clickLogin() {
        getClickableElement(loginBtn);
        scrollIntoView(loginBtn).click();
        return this;
    }

    @Contract(" -> this")
    private CheckoutPage waitForLoginBtnToDisappear() {
        getInvisibleElement(loginBtn);
        return this;
    }

    public CheckoutPage clickToLogin() {
        getClickableElement(showLoginBtn);
        scrollIntoView(showLoginBtn).click();
        return this;
    }

    @Contract("_ -> this")
    private CheckoutPage enterFirstName(String firstName) {
        WebElement element = getVisibleElement(firstNameFld);
        scrollIntoView(element);
        element.clear();
        element.sendKeys(firstName);
        return this;
    }

    @Contract("_ -> this")
    private CheckoutPage enterLastName(String lastName) {
        WebElement element = getVisibleElement(lastNameFld);
        scrollIntoView(element);
        element.clear();
        element.sendKeys(lastName);
        return this;
    }

    @Contract("_ -> this")
    public CheckoutPage selectCountry(String countryName) {
        /*
         firefox browser ITSELF has a bug (4 yo) that prevents using Select class
         Select select = new Select(getClickableElement(countryDropdown));
         select.selectByVisibleText(countryName);
        */
        getClickableElement(alternateCountryDropdown);
        scrollIntoView(alternateCountryDropdown).click();
        WebElement country = getCountryName(countryName);
        scrollIntoView(country).click();
        return this;
    }

    @Contract("_ -> this")
    private CheckoutPage enterAddress1(String address1) {
        WebElement element = getVisibleElement(billingAddress1FLd);
        scrollIntoView(element);
        element.clear();
        element.sendKeys(address1);
        return this;
    }

    @Contract("_ -> this")
    private CheckoutPage enterCity(String city) {
        WebElement element = getVisibleElement(billingCityFld);
        scrollIntoView(element);
        element.clear();
        element.sendKeys(city);
        return this;
    }

    @Contract("_ -> this")
    public CheckoutPage selectState(@NotNull String stateName) {
        /*
         firefox browser ITSELF has a bug (4 yo) that prevents using Select class
        Select select = new Select(getVisibleElement(stateDropdown));
        select.selectByVisibleText(stateName);
         */
        if (!stateName.equals("")) {
            getClickableElement(alternateStateDropdown);
            scrollIntoView(alternateStateDropdown).click();
            WebElement state = getStateName(stateName);
            scrollIntoView(state).click();
        }
        return this;
    }

    @Contract("_ -> this")
    private CheckoutPage enterPostCode(String postCode) {
        WebElement element = getVisibleElement(billingPostcodeFld);
        element.clear();
        element.sendKeys(postCode);
        return this;
    }

    @Contract("_ -> this")
    private CheckoutPage enterEmail(String email) {
        WebElement element = getVisibleElement(billingEmailFld);
        element.clear();
        element.sendKeys(email);
        return this;
    }

    public CheckoutPage setBillingAddress(@NotNull BillingAddress billingAddress) {
        enterFirstName(billingAddress.getFirstName());
        enterLastName(billingAddress.getLastName());
        selectCountry(billingAddress.getCountryName());
        enterAddress1(billingAddress.getAddress1());
        enterCity(billingAddress.getCity());
        selectState(billingAddress.getStateName());
        enterPostCode(billingAddress.getPostalCode());
        enterEmail(billingAddress.getEmail());
        return this;
    }

    public CheckoutPage placeOrder() {
        waitForOverlaysToDisappear(overlay);
        getClickableElement(placeOrderBtn);
        scrollIntoView(placeOrderBtn).click();
        waitForOverlaysToDisappear(overlay);
        return this;
    }

    public String getNotice() {
        return getVisibleElement(successNotice).getText();
    }

    public CheckoutPage selectDirectBankTransferRadioButton() {
        WebElement element = getClickableElement(directBankTransferRadioBtn);
        if (!element.isSelected()) {
            scrollIntoView(element);
            element.click();
        }
        return this;
    }

    public CheckoutPage selectCashOnDeliveryRadioButton() {
        WebElement element = getClickableElement(cashOnDeliveryRadioBtn);
        if (!element.isSelected()) {
            scrollIntoView(element);
            element.click();
        }
        return this;
    }

    public String getProductName() {
        return getVisibleElement(productName).getText();
    }
}
