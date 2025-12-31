package com.example.electrofast.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.electrofast.entity.Product;
import com.example.electrofast.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    /**
     * すべての商品を取得します。
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * IDを指定して商品を取得します。
     * @throws IllegalArgumentException 商品が見つからない場合
     */
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("指定された商品が見つかりません。ID:" + id));
    }

    /**
     * 在庫数を安全に取得するメソッド
     * ProductとStockが1対1で分かれているため、Stockの存在確認を行っています。
     */
    public Integer getStock(Long id) {
        Product product = getProductById(id);
        // 【Nullガード】Stockテーブルにデータがない場合の例外（NullPointerException）を防ぐ
        if (product.getStock() == null) {
            return 0; 
        }
        // Stockエンティティから数量を取得して返す
        return product.getStock().getQuantity();
    }
}