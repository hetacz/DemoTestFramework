package org.selenium.pom.data;

import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.USStates;

import java.io.IOException;
import java.util.Arrays;

import static org.selenium.pom.utils.JacksonUtils.deserializeJSON;

public class DataProvider {

    @SuppressWarnings("all")
    @org.testng.annotations.DataProvider(name = "getFeaturedProducts", parallel = true)
    public static Product[] getFeaturedProducts() throws IOException {
        Product[] products = deserializeJSON("products.json", Product[].class);
        products = Arrays.stream(products)
                .filter(Product::getFeatured) // featured: true
                .toArray(Product[]::new);
        return products;
    }
    @SuppressWarnings("all")
    @org.testng.annotations.DataProvider(name = "getBraceletProducts", parallel = true)
    public static Product[] getBraceletProducts() throws IOException {
        Product[] products = deserializeJSON("products.json", Product[].class);
        products = Arrays.stream(products)
                .filter(product -> {
            return product.getName().contains("Bracelet");
        }).toArray(Product[]::new);
        return products;
    }

    @SuppressWarnings("all")
    @org.testng.annotations.DataProvider(name = "getProducts", parallel = true)
    public static Product[] getProducts() throws IOException {
        return deserializeJSON("products.json", Product[].class);
    }

    @SuppressWarnings("all")
    @org.testng.annotations.DataProvider(name = "getBillingAddress", parallel = true)
    public static BillingAddress[] getBillingAddress() throws IOException {
        return deserializeJSON("billingAddress.json", BillingAddress[].class);
    }

    @SuppressWarnings("all")
    @org.testng.annotations.DataProvider(name = "getUSStates", parallel = true)
    public static USStates[] getUSStates() throws IOException {
        return deserializeJSON("usStates.json", USStates[].class);
    }
}
