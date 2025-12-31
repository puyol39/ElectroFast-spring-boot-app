package com.example.electrofast.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.ToString; // 追加

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    // ★修正点：toString()の無限ループを防ぐため Exclude を追加
    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    @ToString.Exclude 
    private Stock stock;

    @Column(name = "model_number", nullable = false)
    private String modelNumber;

    @Column(nullable = false)
    private String manufacturer;

    @Column(nullable = false)
    private Integer price;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "image_url")
    private String imageUrl;
}