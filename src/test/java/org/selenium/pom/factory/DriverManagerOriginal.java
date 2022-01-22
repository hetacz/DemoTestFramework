package org.selenium.pom.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.selenium.pom.constants.BrowserType;

public class DriverManagerOriginal {

    public WebDriver initializeDriver(String browser) {
        // if not using WebDriverManager remove dependency too
        WebDriver driver;
        switch (BrowserType.valueOf(browser)) {
            case CHROME -> {
                WebDriverManager.chromedriver().cachePath("drivers").setup();
                driver = new ChromeDriver();
            }
            case FIREFOX -> {
                WebDriverManager.firefoxdriver().cachePath("drivers").setup();
                driver = new FirefoxDriver();
            }
            case EDGE -> {
                WebDriverManager.edgedriver().cachePath("drivers").setup();
                driver = new EdgeDriver();
            }
            default -> throw new IllegalStateException("Invalid browser name: " + browser);
        }
        driver.manage().window().maximize();
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        return driver;
    }
}
