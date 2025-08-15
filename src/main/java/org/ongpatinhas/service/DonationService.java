package org.ongpatinhas.service;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.core.MPRequestOptions;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import org.ongpatinhas.dto.DonationDTO;
import org.ongpatinhas.model.Donation;
import org.ongpatinhas.repository.DonationRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class DonationService {

    private final DonationRepository donationRepository;

    public DonationService(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    public void createDonation(DonationDTO donationDTO, String uuid) {
        Donation donation = new Donation();
        donation.setId(uuid);
        donation.setName(donationDTO.name());
        donation.setEmail(donationDTO.email());
        donation.setValue(donationDTO.value());
        donation.setCreatedAt(LocalDateTime.now());
        saveDonation(donation);
    }

    private Donation findDonationById(String id){
        return donationRepository.findById(id).orElse(null);
    }

    public void saveDonation(Donation donation){
        donationRepository.save(donation);
    }

    public boolean updateDonation(String id, String formaPagamento, Long paymentId, String status) {
        Donation donation = findDonationById(id);
        if (donation == null) return false;
        donation.setPaymentType(formaPagamento);
        donation.setIdMercadoPago(paymentId);
        donation.setPaidAt(LocalDateTime.now());
        donation.setStatus(status);
        saveDonation(donation);
        return true;
    }

}
