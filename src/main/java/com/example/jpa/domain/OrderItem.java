package com.example.jpa.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ORDER_ITEM")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "order_price")
    private int orderPrice;

    @Column(name = "count")
    private int count;

    private OrderItem(Order order, Item item, int orderPrice, int count) {
        this.order = order;
        this.item = item;
        this.orderPrice = orderPrice;
        this.count = count;
    }

    public static OrderItem of(Order order, Item item, int orderPrice, int count) {
        return new OrderItem(order, item, orderPrice, count);
    }

    public void setOrder(Order order) {
        if (this.order != null) {
            this.order.getOrderItems().remove(this);
        }

        this.order = order;
        order.getOrderItems().add(this);
    }
}
