package com.example.jpa.domain;

import com.example.jpa.domain.model.OrderStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private Order(Member member, Delivery delivery, LocalDateTime orderDate, OrderStatus status) {
        this.member = member;
        this.delivery = delivery;
        this.orderDate = orderDate;
        this.status = status;
    }

    public static Order of(Member member, Delivery delivery, LocalDateTime orderDate, OrderStatus status) {
        return new Order(member, delivery, orderDate, status);
    }

    public void setMember(Member member) {
        if (this.member != null) {
            this.member.getOrders().remove(this);
        }

        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
}
