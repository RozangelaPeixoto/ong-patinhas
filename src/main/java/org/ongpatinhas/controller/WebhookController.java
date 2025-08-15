package org.ongpatinhas.controller;

import org.ongpatinhas.model.Donation;
import org.ongpatinhas.service.DonationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

    private final DonationService donationService;

    public WebhookController(DonationService donationService) {
        this.donationService = donationService;
    }

    @PostMapping
    public void receberWebhook(@RequestBody Map<String, Object> payload) {
        Map<String, Object> data = (Map<String, Object>) payload.get("data");
        if (data != null && data.get("id") != null) {
            Long paymentId = Long.valueOf(data.get("id").toString());
            Map<String, Object> pagamento = donationService.findPayment(paymentId);

            extractInfoFromPayment(pagamento, paymentId);
        }
    }

    private void extractInfoFromPayment(Map<String, Object> pagamento, Long paymentId) {
        String referencia = (String) pagamento.get("external_reference");
        String formaPagamento = (String) pagamento.get("payment_type_id");
        String status = (String) pagamento.get("status");
        updateDonation(referencia, formaPagamento, paymentId, status);
    }

    private void updateDonation(String referencia, String formaPagamento, Long paymentId, String status) {
        Donation donation = donationService.findPaymentById(referencia);
        donation.setPaymentType(formaPagamento);
        donation.setIdMercadoPago(paymentId);
        donation.setPaidAt(LocalDateTime.now());
        donation.setStatus(status);
        donationService.saveDonation(donation);
    }
}
