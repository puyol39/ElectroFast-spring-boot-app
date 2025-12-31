package com.example.electrofast.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // 購入時の価格を記録（価格改定対策）
    @Column(name = "price_at_purchase", nullable = false)
    private Integer priceAtPurchase;

    // 保証開始日となる注文日時
    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;
    
    public Order(User user, Product product) {
        this.user = user;
        this.product = product;
        this.priceAtPurchase = product.getPrice();
        this.orderDate = LocalDateTime.now();
        
    }
}