package org.selenium.pom.utils;

import io.restassured.http.Cookies;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.Cookie;

import java.util.ArrayList;
import java.util.List;

public class CookieUtils {

    public static List<Cookie> convertRestAssuredCookiesToSeleniumCookies(@NotNull Cookies cookies) {
        List<io.restassured.http.Cookie> restAssuredCookieList = cookies.asList();
        List<Cookie> seleniumCookieList = new ArrayList<>();
        for(io.restassured.http.Cookie cookie: restAssuredCookieList) {
            seleniumCookieList.add(new Cookie(
                    cookie.getName(),
                    cookie.getValue(),
                    cookie.getDomain(),
                    cookie.getPath(),
                    cookie.getExpiryDate(),
                    cookie.isSecured(),
                    cookie.isHttpOnly(),
                    cookie.getSameSite()
            ));
        }
        return seleniumCookieList;
    }
}
