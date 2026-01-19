package com.example.ecommerce.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.dto.AddToCartRequest;
import com.example.ecommerce.model.CartItem;
import com.example.ecommerce.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartControlller {
    private final CartService service;

    public CartControlller(CartService service){
        this.service = service;
    }

    @PostMapping("/add")
    public CartItem addToCart(@RequestBody AddToCartRequest req){
        return service.addToCart(
            req.getUserId(),
            req.getProductId(),
            req.getQuantity()
        );
    }

    @GetMapping("/{userId}")
    public List<CartItem> getCart(@PathVariable String userId){
        return service.getCart(userId);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> cleatCart(@PathVariable String userId){
        service.clearCart(userId);
        return ResponseEntity.ok("Cart Cleared Successfully");
    }
}
