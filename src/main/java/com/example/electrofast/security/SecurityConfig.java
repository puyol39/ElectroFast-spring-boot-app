package com.example.electrofast.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                // 1. 全員に許可するURL（/auth/ 以降のログイン・登録画面を含む）
                .requestMatchers(
                    "/", 
                    "/products/**", 
                    "/auth/**",     // これで /auth/login も /auth/register もOK
                    "/css/**", 
                    "/js/**",       // ★追加：Bootstrap等の動作に必要
                    "/images/**"
                    
                ).permitAll()
                
                // 2. それ以外（購入、注文履歴など）はログインが必要
                .anyRequest().authenticated()
            )
            .formLogin(login -> login
                .loginPage("/auth/login")         // ログイン画面の表示URL
                .loginProcessingUrl("/auth/login") // ログイン処理（POST）の送信先
                .defaultSuccessUrl("/", true)      // 成功時のリダイレクト先
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/")
                .permitAll()
            );
        
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}