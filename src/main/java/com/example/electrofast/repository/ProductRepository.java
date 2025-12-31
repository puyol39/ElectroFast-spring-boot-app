package com.example.electrofast.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.electrofast.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // 型番や商品名でのあいまい検索用
    List<Product> findByNameContainingOrModelNumberContaining(String name, String modelNumber);
    
    // メーカーでの絞り込み用
    List<Product> findByManufacturer(String manufacturer);
}