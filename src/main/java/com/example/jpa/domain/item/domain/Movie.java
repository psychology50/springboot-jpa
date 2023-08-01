package com.example.jpa.domain.item.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("M")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Movie extends Item {
    private String director;
    private String actor;

    private Movie(String name, int price, int stockQuantity, String director, String actor) {
        super(name, price, stockQuantity);
        this.director = director;
        this.actor = actor;
    }

    public static Movie of(String name, int price, int stockQuantity, String director, String actor) {
        return new Movie(name, price, stockQuantity, director, actor);
    }
}
