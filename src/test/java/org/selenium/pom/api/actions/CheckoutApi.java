package org.selenium.pom.api.actions;

import org.selenium.pom.api.ApiRequest;

public class CheckoutApi extends ApiRequest {
/*
    private Cookies cookies;

    public CheckoutApi() { }

    public CheckoutApi(Cookies cookies) { this.cookies = cookies; }

    public Cookies getCookies() {
        return cookies;
    }

    public CheckoutApi setCookies(Cookies cookies) {
        this.cookies = cookies;
        return this;
    }

    private @NotNull Response getCheckout() { // fetch /checkout page in response (which contains nonce)

        if (cookies == null) {
            throw new RuntimeException("Trying to access checkout page with empty cookies (must add items in cart before).");
        }

        Response response = get(Endpoint.CHECKOUT.url, cookies);

        if(response.getStatusCode() != 200) {
            throw new RuntimeException("Failed to get to checkout page, response code: " + response.getStatusCode());
        }
        return response;
    }

    private @NotNull String fetchNonceValue() {
        Response response = getCheckout();
        Document document = Jsoup.parse(response.body().prettyPrint());
        Element element = document.selectFirst("#woocommerce-process-checkout-nonce");
        if (element == null) {
            throw new RuntimeException("Checkout form not found!");
        }
        return element.attr("value");
    }

    public Response proceedToCheckout() {
        return null;
    }

    public Response checkout(@NotNull BillingAddress billingAddress, @NotNull Payment payment) {
        // App codes countries and states by abbreviations (CA, US) by default when sending API calls.
        // I'll send data without abbreviating maybe it will still show up on previous orders.

        Header header = new Header("content-type", "application/x-www-form-urlencoded; charset=UTF-8");
        Headers headers = new Headers(header);

        HashMap<String, String> formData = new HashMap<>();
        formData.put("billing_first_name", billingAddress.getFirstName());
        formData.put("billing_last_name", billingAddress.getLastName());
        formData.put("billing_company", "");
        formData.put("billing_country", );
        formData.put("billing_address_1", billingAddress.getAddress1());
        formData.put("billing_address_2", "");
        formData.put("billing_city", billingAddress.getCity());
        formData.put("billing_state", );
        formData.put("billing_postcode", billingAddress.getPostalCode());
        formData.put("billing_phone", "");
        formData.put("billing_email", billingAddress.getEmail());
        formData.put("shipping_first_name", billingAddress.getFirstName());
        formData.put("shipping_last_name", billingAddress.getLastName());
        formData.put("shipping_company", "");
        formData.put("shipping_country", );
        formData.put("shipping_address_1", billingAddress.getAddress1());
        formData.put("shipping_address_2", "");
        formData.put("shipping_city", billingAddress.getCity());
        formData.put("shipping_state", );
        formData.put("shipping_postcode", billingAddress.getPostalCode());
        formData.put("order_comments", "");
        formData.put("shipping_method[0]", "flat_rate:1");
        formData.put("payment_method", payment.abbr);
        formData.put("woocommerce-process-checkout-nonce", fetchNonceValue());
        formData.put("_wp_http_referer", "/?wc-ajax=update_order_review");

        Response response = post(Endpoint.WC_CHECKOUT.url, cookies, headers, formData);
        if(response.getStatusCode() != 200) {
            throw new RuntimeException("Failed to checkout properly");
        }
        this.cookies = response.getDetailedCookies();
        return response;
    }

 */
}
