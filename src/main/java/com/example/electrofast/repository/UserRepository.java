package com.example.electrofast.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.electrofast.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}