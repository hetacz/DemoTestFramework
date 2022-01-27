package org.selenium.pom.factory;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.selenium.pom.constants.BrowserType;
import org.selenium.pom.factory.driver.ChromeDriverManager;
import org.selenium.pom.factory.driver.EdgeDriverManager;
import org.selenium.pom.factory.driver.FirefoxDriverManager;

public class DriverManagerFactory {

    @Contract("_ -> new")
    public static @NotNull DriverManager getManager(@NotNull BrowserType browserType) {
        switch (browserType) {
            case CHROME -> {
                return new ChromeDriverManager();
            }
            case FIREFOX -> {
                return new FirefoxDriverManager();
            }
            case EDGE -> {
                return new EdgeDriverManager();
            }
            default -> throw new IllegalStateException("Unexpected value: " + browserType);
        }
    }
}
