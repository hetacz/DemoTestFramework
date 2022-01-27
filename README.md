
# My demo Selenium testing Framework.

![example workflow](https://github.com/hetacz/DemoTestFramework/actions/workflows/maven.yml/badge.svg)  
Selenium framework for a sample e-commerce website, [askomdch.com](https://askomdch.com).

## 1. Using:

 - Java 17
 - Selenium 4
 - TestNG
 - Maven

## 2. Features:

 - APIs to generate state
 - Parametrized
 - Parallelized
 - Data Providers

## 3. Commands:

 - **mvn clean test** — default test command, runs headless tests on Chrome.  
 - **allure serve allure-results** - generate test report.

## 4. Available Configurations:

Accessible as suiteFile parameters (in /testng folder).  
 - testng.xml (default)
 - _singleMethod.xml
 - chrome.xml
 - edge.xml
 - firefox.xml
 - fullHeaders.xml
 - local.xml
 
Detailed README inside of folder.

## 5. Configurable parameters:

 - browser
    - CHROME (default)
    - FIREFOX
    - EDGE
 - headed
    - true
    - false (default)

## 6. Dependencies:

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
