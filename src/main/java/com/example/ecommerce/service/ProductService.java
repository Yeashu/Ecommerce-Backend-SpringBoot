package com.example.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository repo;

    public ProductService(ProductRepository repo){
        this.repo = repo;
    }

    public Product create(Product product){
        return repo.save(product);
    }

    public List<Product> listAll(){
        return repo.findAll();
    }
}
