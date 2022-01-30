package org.selenium.pom.base;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.SimpleLog;
import org.jetbrains.annotations.Contract;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenium.pom.utils.ConfigLoader;

import java.time.Duration;
import java.util.List;

public class BasePage {

    private static final Log LOG = new SimpleLog(BasePage.class.getPackageName() + " " + BasePage.class.getSimpleName());
    private static final int FLUENT_WAIT = 20;
    protected final WebDriver driver;
    protected final WebDriverWait wait;

    @Contract(pure = true)
    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(FLUENT_WAIT));
        PageFactory.initElements(driver, this);
    }

    protected void load(String endPoint) {
        driver.get(ConfigLoader.getInstance().getBaseUrl() + endPoint);
    }

    protected void waitForOverlaysToDisappear(By overlay) {
        List<WebElement> overlays = driver.findElements(overlay);
        LOG.info("Overlays size: " + overlays.size());
        if (!overlays.isEmpty()) {
            wait.until(ExpectedConditions.invisibilityOfAllElements(overlays));
            LOG.info("Overlays are invisible: " + overlays.size());
        } else {
            LOG.info("Overlays not found.");
        }
    }

    protected WebElement scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()",element);
        return element;
    }

    protected WebElement getVisibleElement(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected WebElement getClickableElement(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected WebElement getClickableElement(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected Boolean getInvisibleElement(WebElement element) {
        return wait.until(ExpectedConditions.invisibilityOf(element));
    }
}
