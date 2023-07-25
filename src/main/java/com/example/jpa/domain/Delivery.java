package com.example.jpa.domain;

import com.example.jpa.domain.Order;
import com.example.jpa.domain.model.Address;
import com.example.jpa.domain.model.DeliveryStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DELIVERY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    private Delivery(Address address, DeliveryStatus status) {
        this.address = address;
        this.status = status;
    }

    public static Delivery of(Address address, DeliveryStatus status) {
        return new Delivery(address, status);
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
