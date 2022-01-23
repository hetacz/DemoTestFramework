package org.selenium.pom.constants;

import org.jetbrains.annotations.Contract;

public enum Payment {

    BANK_TRANSFER("bacs"),
    CASH_ON_DELIVERY("cod");

    public final String abbr;

    @Contract(pure = true)
    Payment(String abbr) {
        this.abbr = abbr;
    }
}
