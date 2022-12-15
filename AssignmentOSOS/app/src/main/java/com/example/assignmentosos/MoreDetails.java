package com.example.assignmentosos;

public class MoreDetails {

    // nested json object
    String street , city , zipcode;

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public MoreDetails(String street, String city, String zipcode) {
        this.street = street;
        this.city = city;
        this.zipcode = zipcode;
    }
}
