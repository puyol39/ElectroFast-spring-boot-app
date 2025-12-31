package com.example.electrofast.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.electrofast.entity.Order;
import com.example.electrofast.entity.User;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // 自分の注文履歴を新しい順に取得
    List<Order> findByUserOrderByOrderDateDesc(User user);
}