
# My demo Selenium testing Framework.

Selenium framework for a sample e-commerce website, [askomdch.com](https://askomdch.com).

## Using:

 - Java 17
 - Selenium 4
 - TestNG
 - Maven

## Features:

 - APIs to generate state
 - Parametrized
 - Parallelized
 - Data Providers

## Test Command:

**mvn clean test** — default test command, runs headless tests on Chrome.

## Available Configurations:

Accessible as suiteFile parameters (/testng folder)

 - testng.xml
 - testng_addToCart.xml
 - testng_chrome.xml
 - testng_edge.xml
 - testng_firefox.xml
 - testng_local.xml

### Configurable parameters:

 - browser
    - CHROME (default)
    - FIREFOX
    - EDGE
 - headed
    - true
    - false (default)

#### Dependencies:

 - Selenium 4
 - Maven
 - TestNG
 - REST Assured
 - Jackson
 - Jsoup
 - Faker
 - Webdrivermanager
 - Allure
 - Maven Surefire plugin
 - AspectJ Weaver
