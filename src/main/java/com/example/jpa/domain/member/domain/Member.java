package com.example.jpa.domain.member.domain;

import com.example.jpa.domain.order.domain.Order;
import com.example.jpa.model.Address;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
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

    public static Member empty() {
        return new Member("", null);
    }

    @Override public String toString() {
        return name;
    }

    @Override public boolean equals(Object obj) {
        if (!(obj instanceof Member))
            return false;
        Member m = (Member) obj;
        return id.equals(m.getId()) && name.equals(m.getName()) && address.equals(m.getAddress());
    }

    @Override public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + address.hashCode();
        return result;
    }
}
