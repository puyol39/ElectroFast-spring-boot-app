package com.example.electrofast.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.electrofast.entity.User;
import com.example.electrofast.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService; 

    // ログイン画面表示
    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

 // 会員登録画面の表示
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        // Thymeleafの th:object="${user}" と紐付けるために、空のUserオブジェクトを渡す
        model.addAttribute ("user", new User()); 
        return "auth/register";
    }

    // 会員登録処理
    @PostMapping("/register")
    public String register(@Validated @ModelAttribute User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "auth/register";
        }
        
        try {
            userService.register(user);
        } catch (Exception e) {
            model.addAttribute("error", "会員登録処理において、エラーが発生しました");
            return "auth/register";
        }

        return "redirect:/auth/login?registered";
    }
}