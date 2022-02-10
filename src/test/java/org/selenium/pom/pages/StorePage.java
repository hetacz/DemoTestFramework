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
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.components.PageHeader;
import org.selenium.pom.pages.components.ProductThumbnail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StorePage extends BasePage {

    private static final Logger LOG = LoggerFactory.getLogger(StorePage.class.getPackageName() + " " + StorePage.class.getSimpleName());
    private final ProductThumbnail productThumbnail;
    private final PageHeader pageHeader;
    @FindBy(id = "woocommerce-product-search-field-0")
    @CacheLookup
    private WebElement searchFld;
    @FindBy(css = "button[value='Search']")
    @CacheLookup
    private WebElement searchBtn;
    @FindBy(css = ".woocommerce-products-header__title.page-title") // no cache lookup
    private WebElement title;
    @FindBy(css = ".woocommerce-info")
    private WebElement searchInfo;

    public StorePage(WebDriver driver) {
        super(driver);
        productThumbnail = new ProductThumbnail(driver);
        pageHeader = new PageHeader(driver);
    }

    public StorePage load() {
        load(Endpoint.STORE.url);
        wait.until(ExpectedConditions.titleContains("AskOmDch"));
        LOG.debug(Endpoint.STORE.url + " page loaded.");
        return this;
    }

    public StorePage search(String text) {
        enterTextInSearchFld(text);
        clickSearchBtn();
        LOG.debug(text + " string searched.");
        return this;
    }

    @Contract("_ -> this")
    private StorePage enterTextInSearchFld(String text) {
        WebElement element = getVisibleElement(searchFld);
        scrollIntoView(element);
        element.clear();
        element.sendKeys(text);
        return this;
    }

    @Contract(" -> this")
    private StorePage clickSearchBtn() {
        getClickableElement(searchBtn);
        scrollIntoView(searchBtn).click();
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
    private @NotNull By getProductLinkByPartialLinkText(@NotNull Product product) {
        return By.partialLinkText(product.getName());
    }

    public StorePage clickProductByName(Product product) {
        By productLink = getProductLinkByPartialLinkText(product);
        getClickableElement(productLink).click();
        LOG.debug(product.getName() + " clicked.");
        return this;
    }

    public String getSearchInfo() {
        return getVisibleElement(searchInfo).getText();
    }

    public ProductThumbnail getProductThumbnail() {
        return productThumbnail;
    }

    public PageHeader getPageHeader() {
        return pageHeader;
    }
}
