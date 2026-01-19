package com.example.ecommerce.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ecommerce.model.CartItem;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.OrderItem;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.CartRepository;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.repository.ProductRepository;

@Service
public class OrderService {
    private final OrderRepository orderRepo;
    private final CartRepository cartRepo;
    private final ProductRepository productRepo;

    public OrderService(OrderRepository orderRepo, CartRepository cartRepo, ProductRepository productRepo) {
        this.orderRepo = orderRepo;
        this.cartRepo = cartRepo;
        this.productRepo = productRepo;
    }

    public Order createOrder(String userId){

        List<CartItem> items = cartRepo.findByUserId(userId);
        if(items.isEmpty()){
            throw new RuntimeException("Cart is Empty");
        }

        double total = 0;
        List<OrderItem> orderItems = new ArrayList<>();

        for(CartItem item: items){
            Product product = productRepo.findById(item.getProductId()).orElseThrow(()->new RuntimeException("Product Not Found"));

            OrderItem oItem = new OrderItem();
            oItem.setProductId(product.getId());
            oItem.setPrice(product.getPrice());
            oItem.setQuantity(item.getQuantity());
            
            orderItems.add(oItem);

            total += oItem.getPrice() * oItem.getQuantity();
        }

        Order order = new Order();
        order.setUserId(userId);
        order.setItems(orderItems);
        order.setTotalAmount(total);
        order.setStatus("CREATED");
        order.setCreatedAt(Instant.now());

        Order savedOrder = orderRepo.save(order);

        cartRepo.deleteByUserId(userId);

        return savedOrder;
    }

    public Order getOrder(String orderId){
        return orderRepo.findById(orderId).orElseThrow(() -> new RuntimeException("Order Not Found"));
    }
}
