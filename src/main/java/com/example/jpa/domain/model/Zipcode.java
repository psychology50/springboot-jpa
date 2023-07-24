package com.example.jpa.domain.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Zipcode {
    String zip;
    String plusFour;
}
