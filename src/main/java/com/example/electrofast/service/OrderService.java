package com.example.electrofast.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.electrofast.entity.Order;
import com.example.electrofast.entity.User;
import com.example.electrofast.repository.OrderRepository;
import com.example.electrofast.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    /**
     * メールアドレスを元に、そのユーザーの注文履歴をすべて取得する
     */
    public List<Order> getOrderHistory(String email) {

        User user = userRepository.findByEmail(email);

        return orderRepository.findByUserOrderByOrderDateDesc(user);
    }
}