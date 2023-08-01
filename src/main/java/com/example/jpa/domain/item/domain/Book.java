package com.example.jpa.domain.item.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("B")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book extends Item {
    private String author;
    private String isbn;

    private Book(String name, int price, int stockQuantity, String author, String isbn) {
        super(name, price, stockQuantity);
        this.author = author;
        this.isbn = isbn;
    }

    public static Book of(String name, int price, int stockQuantity, String author, String isbn) {
        return new Book(name, price, stockQuantity, author, isbn);
    }
}
