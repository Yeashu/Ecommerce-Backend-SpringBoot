package com.example.ecommerce.webhook;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.dto.PaymentWebhookRequest;
import com.example.ecommerce.service.PaymentService;

@RestController
@RequestMapping("/api/webhooks")
public class PaymentWebhookController {
    private final PaymentService service;

    public PaymentWebhookController(PaymentService service) {
        this.service = service;
    }

    @PostMapping("/payment")
    public ResponseEntity<Map<String, String>> handleWebhook(
            @RequestBody PaymentWebhookRequest req) {

        service.handleWebhook(
                req.getPaymentId(),
                req.getStatus()
        );

        return ResponseEntity.ok(
                Map.of("message", "Webhook processed")
        );
    }
}
