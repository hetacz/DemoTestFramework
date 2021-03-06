package org.selenium.pom.pages.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.StorePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageHeader extends BasePage {

    private static final Logger LOG = LoggerFactory.getLogger(PageHeader.class.getPackageName() + " " + PageHeader.class.getSimpleName());
    @FindBy(css = "#menu-item-1227 > a")
    @CacheLookup
    private WebElement storeMenuLink;

    public PageHeader(WebDriver driver) {
        super(driver);
    }

    public StorePage navigateToStoreUsingMenu() {
        getClickableElement(storeMenuLink);
        scrollIntoView(storeMenuLink).click(); // return object of another page!
        LOG.debug("Page header store link clicked.");
        return new StorePage(driver); // One can return Base Page and make test class responsible for object management.
    }
}
