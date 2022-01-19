package org.selenium.pom.objects;

import org.jetbrains.annotations.Contract;

public class BillingAddress {

    private String firstName;
    private String lastName;
    private String countryName;
    private String address1;
    private String city;
    private String stateName;
    private String postalCode;
    private String email;

    @Contract(pure = true)
    public BillingAddress(){}

    @Contract(pure = true)
    public BillingAddress(
            String firstName,
            String lastName,
            String countryName,
            String address1,
            String city,
            String stateName,
            String postalCode,
            String email
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.countryName = countryName;
        this.address1 = address1;
        this.city = city;
        this.stateName = stateName;
        this.postalCode = postalCode;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public BillingAddress setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public BillingAddress setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getAddress1() {
        return address1;
    }

    public BillingAddress setAddress1(String address1) {
        this.address1 = address1;
        return this;
    }

    public String getCity() {
        return city;
    }

    public BillingAddress setCity(String city) {
        this.city = city;
        return this;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public BillingAddress setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public BillingAddress setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getCountryName() {
        return countryName;
    }

    public BillingAddress setCountryName(String countryName) {
        this.countryName = countryName;
        return this;
    }

    public String getStateName() {
        return stateName;
    }

    public BillingAddress setStateName(String stateName) {
        this.stateName = stateName;
        return this;
    }
}
