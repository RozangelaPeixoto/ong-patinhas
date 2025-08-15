package org.ongpatinhas.controller;

import org.ongpatinhas.model.Donation;
import org.ongpatinhas.service.DonationService;
import org.ongpatinhas.service.MercadoPagoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

    private final MercadoPagoService mercadoPagoService;

    public WebhookController(MercadoPagoService mercadoPagoService) {
        this.mercadoPagoService = mercadoPagoService;
    }

    @PostMapping
    public void receberWebhook(@RequestBody Map<String, Object> payload) {
        Map<String, Object> data = (Map<String, Object>) payload.get("data");
        if (data != null && data.get("id") != null) {
            Long paymentId = Long.valueOf(data.get("id").toString());
            mercadoPagoService.processPayment(paymentId);
        }
    }

}
