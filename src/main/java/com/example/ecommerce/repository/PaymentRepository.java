package com.example.ecommerce.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.ecommerce.model.Payment;

public interface PaymentRepository extends MongoRepository<Payment, String> {
    
}
