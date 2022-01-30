package org.selenium.pom.pages;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.SimpleLog;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.constants.Endpoint;
import org.selenium.pom.pages.components.PageHeader;
import org.selenium.pom.pages.components.ProductThumbnail;

public class HomePage extends BasePage {

    private static final Log LOG = new SimpleLog(HomePage.class.getPackageName() + " " + HomePage.class.getSimpleName());
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
        LOG.info(Endpoint.HOME.url + " page loaded.");
        return this;
    }

    public PageHeader getPageHeader() {
        return pageHeader;
    }

    public ProductThumbnail getProductThumbnail() {
        return productThumbnail;
    }
}
