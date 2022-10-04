package com.example.propertyviewer.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Property {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name;
    private String street;
    private int number;
    private String postalcode;
    private String city;
    private String country;
    private String desc;


    public Property() {}

    public Property(String name, String street, int number, String postalcode, String city, String country, String desc) {
        super();
        this.name = name;
        this.street = street;
        this.number = number;
        this.postalcode = postalcode;
        this.city = city;
        this.country = country;
        this.desc = desc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getStreet() {
        return street;
    }


    public void setStreet(String street) {
        this.street = street;
    }


    public int getNumber() {
        return number;
    }


    public void setNumber(int number) {
        this.number = number;
    }


    public String getPostalcode() {
        return postalcode;
    }


    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }


    public String getCity() {
        return city;
    }


    public void setCity(String city) {
        this.city = city;
    }


    public String getCountry() {
        return country;
    }


    public void setCountry(String country) {
        this.country = country;
    }


    public String getDesc() {
        return desc;
    }


    public void setDesc(String desc) {
        this.desc = desc;
    }

@Override
public String toString() {
    return "Property [name=" + name + ", street=" + street + ", number=" + number + ", postalcode=" + postalcode
            + ", city=" + city + ", country=" + country + ", desc=" + desc + "]";
}

    
}
