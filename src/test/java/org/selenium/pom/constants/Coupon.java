package org.selenium.pom.constants;

import org.jetbrains.annotations.Contract;

public enum Coupon {

    OFF15("off15"),
    BTOFF20("btoff20"),
    FREESHIP("freeship"),
    OFFCART5("offcart5"),
    OFF25("off25");

    public final String couponName;

    @Contract(pure = true)
    Coupon(String couponName) {
        this.couponName = couponName;
    }
}
