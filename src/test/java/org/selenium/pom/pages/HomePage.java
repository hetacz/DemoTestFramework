package org.selenium.pom.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

public class HomePage extends BasePage {

    @FindBy(css="#menu-item-1227 > a") @CacheLookup
    private WebElement storeMenuLink;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public StorePage navigateToStoreUsingMenu() {
        getClickableElement(storeMenuLink).click(); // return object of another page!
        return new StorePage(driver); // One can return Base Page and make test class responsible for object management.
    }
    /*
        Method without using fluent interface. Up to me whichever suits me the most.

    public HomePage clickStoreMenuLink() {
        driver.findElement(storeMenuLink).click();
        return this;
    }
*/
    public HomePage load() {
        load("/");
        wait.until(ExpectedConditions.titleContains("AskOmDch"));
        return this;
    }
}
