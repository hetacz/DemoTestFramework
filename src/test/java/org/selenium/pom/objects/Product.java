package org.selenium.pom.objects;

import org.jetbrains.annotations.Contract;
import org.selenium.pom.utils.JacksonUtils;

import java.io.IOException;

public class Product {

    private int id;
    private String name;
    private Boolean featured;

    @Contract(pure = true)
    public Product() { }

    @Contract(pure = true)
    public Product(int id) throws IOException {
        Product[] products = JacksonUtils.deserializeJSON("products.json", Product[].class); // read json array
        for (Product product : products) {
            if (product.getId() ==
                    id) { // if it finds product with ID given in a constructor it means it is a valid ID, and it creates a product.
                this.id = product.getId();
                this.name = product.getName();
                this.featured = product.getFeatured();
            }
        }
    }

    public int getId() {
        return id;
    }

    public Product setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public Boolean getFeatured() {
        return featured;
    }

    public Product setFeatured(Boolean featured) {
        this.featured = featured;
        return this;
    }
}
