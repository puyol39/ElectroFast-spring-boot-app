package com.example.electrofast.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.electrofast.entity.User;
import com.example.electrofast.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // SecurityConfigで定義したものが注入されます

    /**
     * 新規会員登録処理
     * パスワードを暗号化してからデータベースに保存します
     */
    @Transactional
    public void register(User user) {
        // 1. メールアドレスの重複チェック（簡易版）
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("このメールアドレスは既に登録されています。");
        }

        // 2. パスワードの暗号化
        // 画面で入力された "password123" を "$2a$10$..." のような暗号文に変換
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        // 3. 権限（Role）の設定（今回は一般ユーザー "GENERAL" 固定）
        // ※Userエンティティにroleフィールドがある場合のみ。なければ省略可
        user.setRole("GENERAL"); 

        // 4. 保存
        userRepository.save(user);
    }
}