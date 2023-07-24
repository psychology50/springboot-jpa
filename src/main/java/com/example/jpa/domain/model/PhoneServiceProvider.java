package com.example.jpa.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class PhoneServiceProvider {
    @Id String name;
}
