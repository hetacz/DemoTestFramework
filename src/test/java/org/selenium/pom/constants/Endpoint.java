package org.selenium.pom.constants;

import org.jetbrains.annotations.Contract;

public enum Endpoint {

    HOME("/"),
    STORE("/store"),
    CART("/cart"),
    CHECKOUT("/checkout"),
    ACCOUNT("/account"),
    ADD_TO_CART("/?wc-ajax=add_to_cart"),
    WC_CHECKOUT("/?wc-ajax=checkout");

    public final String url;

    @Contract(pure = true)
    Endpoint(String url) {
        this.url = url;
    }
}
