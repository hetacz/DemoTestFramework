package org.selenium.pom.utils;

import org.jetbrains.annotations.NotNull;
import org.selenium.pom.constants.EnvType;

import java.io.File;
import java.util.Properties;

public class ConfigLoader {

    private static ConfigLoader configLoader;
    private final Properties properties;
    private static final String PROPERTY_ERR_MSG = " property is not specified in a config.properties file";
    private static final String STAGE_PATH = "src" + File.separator + "test" + File.separator + "resources" + File.separator + "config.properties";
    private static final String PRODUCTION_PATH = "src" + File.separator + "test" + File.separator + "resources" + File.separator + "prod_config.properties";

    private ConfigLoader() {
        String env = System.getProperty("env", String.valueOf(EnvType.STAGE));
        switch (EnvType.valueOf(env)) {
            case STAGE -> properties = PropertyUtils.propertyLoader(STAGE_PATH);
            case PRODUCTION -> properties = PropertyUtils.propertyLoader(PRODUCTION_PATH);
            default -> throw new IllegalStateException("Invalid env type: " + env);
        }
    }

    public static ConfigLoader getInstance() {
        if (configLoader == null) {
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public @NotNull String getBaseUrl() {
        String s = properties.getProperty("baseUrl");
        if (s != null) {
            return s;
        } else {
            throw new RuntimeException("baseUrl" + PROPERTY_ERR_MSG);
        }
    }

    public @NotNull String getUsername() {
        String s = properties.getProperty("username");
        if (s != null) {
            return s;
        } else {
            throw new RuntimeException("username" + PROPERTY_ERR_MSG);
        }
    }

    public @NotNull String getPassword() {
        String s = properties.getProperty("password");
        if (s != null) {
            return s;
        } else {
            throw new RuntimeException("password" + PROPERTY_ERR_MSG);
        }
    }
}
