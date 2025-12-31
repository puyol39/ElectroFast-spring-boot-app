package com.example.electrofast.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.electrofast.entity.Order;
import com.example.electrofast.entity.Product;
import com.example.electrofast.entity.Stock; // 追加
import com.example.electrofast.entity.User;
import com.example.electrofast.repository.OrderRepository;
import com.example.electrofast.repository.ProductRepository;
import com.example.electrofast.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public void createOrder(String email, Long productId) {

        // 1. ユーザー情報の取得
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("エラー：購入ユーザー情報が見つかりません。(" + email + ")");
        }
        
        // 2. 商品情報の取得
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("指定された商品が見つかりません。ID:" + productId));

        // --- 【修正箇所：在庫チェック】 ---
        // product.getStock() は Stockエンティティを返し、その中の quantity が実際の在庫数です
        Stock stock = product.getStock();
        if (stock == null || stock.getQuantity() <= 0) {
            throw new IllegalStateException("申し訳ありません。商品「" + product.getName() + "」は現在在庫切れです。");
        }

        // --- 【修正箇所：在庫を減らす処理】 ---
        // Stockエンティティの数量を更新します
        stock.setQuantity(stock.getQuantity() - 1);
        // -------------------------------

        // 3. 注文データの作成と保存
        Order order = new Order(user, product);
        orderRepository.save(order);
        
        // ※ @Transactional がついているため、メソッド終了時に 
        //    stock.setQuantity の変更も自動的にデータベースへ反映されます。
    }
}