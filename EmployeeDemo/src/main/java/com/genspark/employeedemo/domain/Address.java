package com.genspark.employeedemo.domain;

import org.springframework.stereotype.Component;

@Component
public class Address {

    private String street, country;

    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public Address(){
        super();
    }
    public Address(String street, String country) {
        this.street = street;
        this.country = country;
    }

    @Override
    public String toString(){
        return "{Address: "+getStreet() +" Country: "+getCountry()+"}";
    }
}
