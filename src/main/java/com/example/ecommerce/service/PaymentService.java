package com.example.ecommerce.service;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.Payment;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.repository.PaymentRepository;

@Service
public class PaymentService {
    
    private final PaymentRepository paymentRepo;
    private final OrderRepository orderRepo;

    public PaymentService(PaymentRepository paymentRepo, OrderRepository orderRepo) {
        this.paymentRepo = paymentRepo;
        this.orderRepo = orderRepo;
    }

    public Payment createPayment(String orderId){

        Order order = orderRepo.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));

        Payment payment = new Payment();
        payment.setOrderId(orderId);
        payment.setAmount(order.getTotalAmount());
        payment.setStatus("PENDING");
        payment.setCreatedAt(Instant.now());

        return paymentRepo.save(payment);
    }

    public Payment getPayment(String paymentId){
        return paymentRepo.findById(paymentId).orElseThrow(() -> new RuntimeException("Payment Not Found"));
    }

    public void handleWebhook(String paymentId, String status) {

        Payment payment = paymentRepo.findById(paymentId)
                .orElseThrow(() ->
                        new RuntimeException("Payment not found"));

        Order order = orderRepo.findById(payment.getOrderId())
                .orElseThrow(() ->
                        new RuntimeException("Order not found"));

        payment.setStatus(status);
        paymentRepo.save(payment);

        if ("SUCCESS".equals(status)) {
            order.setStatus("PAID");
        } else {
            order.setStatus("FAILED");
        }

        orderRepo.save(order);
    }
}
