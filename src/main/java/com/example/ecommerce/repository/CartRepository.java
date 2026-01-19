package com.example.ecommerce.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.ecommerce.model.CartItem;

public interface CartRepository extends MongoRepository<CartItem, String> {

    List<CartItem> findByUserId(String userId);

    void deleteByUserId(String userId);
}
