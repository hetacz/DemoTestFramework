package org.selenium.pom.pages.components;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.CartPage;

import java.util.List;

public class ProductThumbnail extends BasePage {

    @FindBy(css = "ul.products.columns-5 > li")
    @CacheLookup
    private List<WebElement> productsList;
    @FindBy(css = "ul.products.columns-5 > li a.woocommerce-loop-product__link")
    @CacheLookup
    private List<WebElement> productsImageList;
    @FindBy(css = "ul.products.columns-5 > li h2.woocommerce-loop-product__title")
    @CacheLookup
    private List<WebElement> productsNameList;
    @FindBy(css = "ul.products.columns-5 > li a.ajax_add_to_cart")
    @CacheLookup
    private List<WebElement> productsAddBtnList;

    @FindBy(css = "a[title='View cart']")
    @CacheLookup
    private WebElement viewCartLink;

    public ProductThumbnail(WebDriver driver) {
        super(driver);
    }

    @Contract("_ -> new")
    private @NotNull By getClickAddToCartBtn(@NotNull Product product) {
        return By.cssSelector("a[aria-label='Add “" + product.getName() + "” to your cart']");
    }

    public ProductThumbnail clickAddToCartBtn(Product product) {
        By addToCartBtn = getClickAddToCartBtn(product);
        getClickableElement(addToCartBtn).click();
        return this;
    }

    public CartPage clickViewCart() {
        getClickableElement(viewCartLink);
        scrollIntoView(viewCartLink).click();
        return new CartPage(driver);
    }

    public String clickFeatured(int index) {
        getVisibleElement(productsList.get(index));
        final String s = getVisibleElement(productsNameList.get(index)).getText().trim();
        getClickableElement(productsImageList.get(index)).click();
        return s;
    }

    public String addToCartFeatured(int index) {
        getVisibleElement(productsList.get(index));
        final String s = getVisibleElement(productsNameList.get(index)).getText().trim();
        getClickableElement(productsAddBtnList.get(index)).click();
        return s;
    }
}
