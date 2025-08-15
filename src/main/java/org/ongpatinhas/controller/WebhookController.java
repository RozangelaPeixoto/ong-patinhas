package org.ongpatinhas.controller;

import org.ongpatinhas.service.MercadoPagoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        Object dataObj = payload.get("data");
        if (dataObj instanceof Map<?, ?> dataMap) {
            Object idObj = dataMap.get("id");
            if (idObj != null) {
                Long paymentId = Long.valueOf(idObj.toString());
                mercadoPagoService.processPayment(paymentId);
            }
        }
    }

}
