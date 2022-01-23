package org.selenium.pom.utils;

public class StringUtils {

    public static float convertDollarsToFloat(String s) {
        s = s.trim();
        if(s.startsWith("-")) {
            s = s.substring(2);
        }
        if(s.startsWith("$")) {
            s = s.substring(1);
        }
        if(s.contains("[")) {
            final int i = s.indexOf("[");
            s = s
                    .substring(0, i)
                    .trim();
        }
        return Float.parseFloat(s);
    }
}
