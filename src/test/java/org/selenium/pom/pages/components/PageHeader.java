package org.selenium.pom.pages.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.StorePage;

public class PageHeader extends BasePage {

    @FindBy(css = "#menu-item-1227 > a")
    @CacheLookup
    private WebElement storeMenuLink;

    public PageHeader(WebDriver driver) {
        super(driver);
    }

    public StorePage navigateToStoreUsingMenu() {
        getClickableElement(storeMenuLink).click(); // return object of another page!
        return new StorePage(driver); // One can return Base Page and make test class responsible for object management.
    }
}
