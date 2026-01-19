package com.example.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.ecommerce.model.CartItem;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.CartRepository;
import com.example.ecommerce.repository.ProductRepository;

@Service
public class CartService {
    
    private final CartRepository cartRepo;
    private final ProductRepository productRepo;

    public CartService(CartRepository cartRepo, ProductRepository productRepo){
        this.cartRepo = cartRepo;
        this.productRepo = productRepo;
    }

    public CartItem addToCart(String userId, String productId, Integer quantity){
        Optional<Product> productOpt = productRepo.findById(productId);
        if (productOpt.isEmpty()){
            throw new RuntimeException("Product Not Found");
        }

        CartItem item = new CartItem();
        item.setUserId(userId);
        item.setProductId(productId);
        item.setQuantity(quantity);

        return cartRepo.save(item);
    }

    public List<CartItem> getCart(String userId){
        return cartRepo.findByUserId(userId);
    }

    public void clearCart(String userId){
        cartRepo.deleteByUserId(userId);
    }
}
