package org.selenium.pom.objects;

import org.jetbrains.annotations.Contract;

public class USStates {

    private String shortName;
    private String longName;
    private float tax;

    @Contract(pure = true)
    public USStates() {}

    @Contract(pure = true)
    public USStates(String shortName, String longName, float tax) {
        this.shortName = shortName;
        this.longName = longName;
        this.tax = tax;
    }

    public String getShortName() {
        return shortName;
    }

    public USStates setShortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    public String getLongName() {
        return longName;
    }

    public USStates setLongName(String longName) {
        this.longName = longName;
        return this;
    }

    public float getTax() {
        return tax;
    }

    public USStates setTax(float tax) {
        this.tax = tax;
        return this;
    }
}
