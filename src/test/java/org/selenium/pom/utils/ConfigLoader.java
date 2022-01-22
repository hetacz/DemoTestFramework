package org.selenium.pom.utils;

import org.selenium.pom.constants.EnvType;

import java.util.Properties;

public class ConfigLoader {

    private static ConfigLoader configLoader;
    private final Properties properties;

    private ConfigLoader() {
        String env = System.getProperty("env", String.valueOf(EnvType.STAGE));
        switch (EnvType.valueOf(env)) {
            case STAGE -> properties = PropertyUtils.propertyLoader("src/test/resources/config.properties");
            case PRODUCTION -> properties = PropertyUtils.propertyLoader("src/test/resources/prod_config.properties");
            default -> throw new IllegalStateException("Invalid env type: " + env);
        }
    }

    public static ConfigLoader getInstance() {
        if (configLoader == null) {
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public String getBaseUrl() {
        String s = properties.getProperty("baseUrl");
        if (s != null) {
            return s;
        } else {
            throw new RuntimeException("Property baseUrl is not specified in a config.properties file");
        }
    }

    public String getUsername() {
        String s = properties.getProperty("username");
        if (s != null) {
            return s;
        } else {
            throw new RuntimeException("Property username is not specified in a config.properties file");
        }
    }

    public String getPassword() {
        String s = properties.getProperty("password");
        if (s != null) {
            return s;
        } else {
            throw new RuntimeException("Property password is not specified in a config.properties file");
        }
    }
}
