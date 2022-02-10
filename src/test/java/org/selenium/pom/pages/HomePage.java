package org.selenium.pom.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.constants.Endpoint;
import org.selenium.pom.pages.components.PageHeader;
import org.selenium.pom.pages.components.ProductThumbnail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomePage extends BasePage {

    private static final Logger LOG = LoggerFactory.getLogger(HomePage.class.getPackageName() + " " + HomePage.class.getSimpleName());
    private final PageHeader pageHeader;
    private final ProductThumbnail productThumbnail;

    public HomePage(WebDriver driver) {
        super(driver);
        pageHeader = new PageHeader(driver);
        productThumbnail = new ProductThumbnail(driver);
    }
    
    public HomePage load() {
        load(Endpoint.HOME.url);
        wait.until(ExpectedConditions.titleContains("AskOmDch"));
        LOG.debug(Endpoint.HOME.url + " page loaded.");
        return this;
    }

    public PageHeader getPageHeader() {
        return pageHeader;
    }

    public ProductThumbnail getProductThumbnail() {
        return productThumbnail;
    }
}
