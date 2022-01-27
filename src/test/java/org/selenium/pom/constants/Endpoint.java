package org.selenium.pom.constants;

import org.jetbrains.annotations.Contract;

public enum Endpoint {

    HOME("/"),
    STORE("/store"),
    CART("/cart"),
    CHECKOUT("/checkout"),
    ACCOUNT("/account"),
    ADD_TO_CART("/?wc-ajax=add_to_cart"),
    WC_CHECKOUT("/?wc-ajax=checkout"),
    ORDER_RECEIVED("/checkout/order-received/"),
    ORDERS("/account/orders/"),
    VIEW_ORDER("/account/view-order/");

    public final String url;

    @Contract(pure = true)
    Endpoint(String url) {
        this.url = url;
    }
}
