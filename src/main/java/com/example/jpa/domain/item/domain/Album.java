package com.example.jpa.domain.item.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("A")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Album extends Item {
    private String artist;
    private String etc;

    private Album(String name, int price, int stockQuantity, String artist, String etc) {
        super(name, price, stockQuantity);
        this.artist = artist;
        this.etc = etc;
    }

    public static Album of(String name, int price, int stockQuantity, String artist, String etc) {
        return new Album(name, price, stockQuantity, artist, etc);
    }
}
