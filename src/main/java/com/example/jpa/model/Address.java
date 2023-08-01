package com.example.jpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {
    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "zipcode")
    private String zipcode;

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    public static Address of(String city, String street, String zipcode) {
        return new Address(city, street, zipcode);
    }

    @Override public boolean equals(Object obj) {
        if (!(obj instanceof Address))
            return false;
        Address address = (Address) obj;
        return this.city.equals(address.city) && this.street.equals(address.street) && this.zipcode.equals(address.zipcode);
    }

    @Override public int hashCode() {
        int result = city.hashCode();
        result = 31 * result + street.hashCode();
        result = 31 * result + zipcode.hashCode();
        return result;
    }
}

