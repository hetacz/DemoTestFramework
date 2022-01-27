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

public class ProductThumbnail extends BasePage {

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

    @Contract("_ -> new")
    private @NotNull By getThumbnailImg(@NotNull Product product) {
        return By.cssSelector("li.post-" + product.getId() + " > div.astra-shop-thumbnail-wrap > a");
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

    public ProductThumbnail clickThumbnailImage(@NotNull Product product) {
        By img = getThumbnailImg(product);;
        getClickableElement(img).click();
        return this;
    }
}
