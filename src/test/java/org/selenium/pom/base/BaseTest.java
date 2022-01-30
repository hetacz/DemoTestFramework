package org.selenium.pom.base;

import io.restassured.http.Cookies;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.SimpleLog;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.constants.BrowserType;
import org.selenium.pom.factory.DriverManagerFactory;
import org.selenium.pom.utils.CookieUtils;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class BaseTest {
    private static final Log LOG = new SimpleLog(BaseTest.class.getPackageName() + " " + BaseTest.class.getSimpleName());
    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    protected WebDriver getDriver() {
        return this.driver.get();
    }

    private void setDriver(WebDriver driver) {
        this.driver.set(driver);
    }

    @Parameters({"browser", "headed"})
    @BeforeMethod
    public void startDriver(@Optional String browser, @Optional Boolean headed) {
        browser = System.getProperty("browser", browser);
        if (browser == null) {
            LOG.warn("No browser configuration, defaulting to Chrome");
            browser = "CHROME";
        }
        if (headed == null) {
            headed = false;
        }
        //        setDriver(new DriverManagerOriginal().initializeDriver(browser));
        setDriver(
                headed ? DriverManagerFactory
                        .getManager(BrowserType.valueOf(browser))
                        .createDriver() : DriverManagerFactory
                        .getManager(BrowserType.valueOf(browser))
                        .createDriverHeadless()
        );
        LOG.debug("Current Thread: " + Thread
                .currentThread()
                .getId());
        LOG.debug("Driver: " + getDriver());
    }

    @Parameters("browser")
    @AfterMethod
    public void stopDriver(@NotNull ITestResult result, @Optional String browser) throws IOException {
        // Thread.sleep(300);
        LOG.debug("Current Thread: " + Thread
                .currentThread()
                .getId());
        LOG.debug("Driver: " + getDriver());
        if (result.getStatus() == ITestResult.FAILURE) {
            File destFile = new File("screenshots" + File.separator + browser + File.separator + result
                    .getTestClass()
                    .getRealClass()
                    .getSimpleName() + File.separator + result
                    .getMethod()
                    .getMethodName() + ".png");
            takeScreenshot(destFile);
        }
        getDriver().quit();
    }

    protected void injectCookiesToBrowser(Cookies cookies) {
        List<Cookie> seleniumCookieList = CookieUtils.convertRestAssuredCookiesToSeleniumCookies(cookies);
        for (Cookie cookie : seleniumCookieList) {
            getDriver()
                    .manage()
                    .addCookie(cookie);
        }
    }

    private void takeScreenshot(File destFile) throws IOException {
        TakesScreenshot takesScreenshot = (TakesScreenshot) getDriver();
        File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, destFile);
    }
}
