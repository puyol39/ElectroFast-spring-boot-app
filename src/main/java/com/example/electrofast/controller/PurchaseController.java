package com.example.electrofast.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.electrofast.service.PurchaseService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/purchase")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;

    @PostMapping("/{productId}")
    public String purchase(@PathVariable Long productId, 
                           @AuthenticationPrincipal UserDetails userDetails,
                           RedirectAttributes redirectAttributes) {
        try {
            purchaseService.createOrder(userDetails.getUsername(), productId);
            redirectAttributes.addFlashAttribute("message", "購入が完了しました！");
            return "redirect:/purchase/complete";
        } catch (Exception e) {
            // サービスで投げたエラーメッセージを取得して、画面に渡す
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/products/" + productId; // 商品詳細画面に戻す
        }
    }
}