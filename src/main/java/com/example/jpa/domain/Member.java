package com.example.jpa.domain;

import com.example.jpa.domain.model.Address;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @Column(name = "name")
    private String name;

    @Embedded
    private Address address;

    private Member(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public static Member of(String name, Address address) {
        return new Member(name, address);
    }

    public List<Order> getOrders() {
        return orders;
    }
}
