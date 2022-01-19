package org.selenium.pom.base;

import io.restassured.http.Cookies;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.factory.DriverManager;
import org.selenium.pom.utils.CookieUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.List;

public class BaseTest {

    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    protected WebDriver getDriver() {
        return this.driver.get();
    }

    private void setDriver(WebDriver driver) {
        this.driver.set(driver);
    }

    @Parameters("browser")
    @BeforeMethod
    public void startDriver(@Optional String browser) {
        browser = System.getProperty("browser", browser);
        if (browser == null) {
            browser = "CHROME";
        }
        setDriver(new DriverManager().initializeDriver(browser));
        System.out.println("Current Thread: " + Thread.currentThread().getId());
        System.out.println("Driver: " + getDriver());
    }

    @AfterMethod
    public void stopDriver() {
        System.out.println("Current Thread: " + Thread.currentThread().getId());
        System.out.println("Driver: " + getDriver());
        getDriver().quit();
    }

    public void injectCookiesToBrowser(Cookies cookies) {
        List<Cookie> seleniumCookieList = CookieUtils.convertRestAssuredCookiesToSeleniumCookies(cookies);
        for(Cookie cookie: seleniumCookieList) {
            getDriver().manage().addCookie(cookie);
        }
    }
}
