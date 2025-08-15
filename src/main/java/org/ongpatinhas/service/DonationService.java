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
    private final RestTemplate restTemplate = new RestTemplate();

    public DonationService(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    public String createPreference(DonationDTO donationDTO){

        PreferenceClient client = new PreferenceClient();
        String uuid = UUID.randomUUID().toString();
        PreferenceItemRequest item = getPreferenceItemRequest(donationDTO.value());

        PreferenceBackUrlsRequest backUrls = getBackUrls();

        PreferenceRequest request = getRequest(item, backUrls, uuid);

        Map<String, String> headers =  new HashMap<>();
        headers.put("X-Idempotency-Key", uuid);

        MPRequestOptions requestOptions = MPRequestOptions.builder()
                .customHeaders(headers)
                .build();
        try {
            Preference preference = client.create(request, requestOptions);
            createDonation(donationDTO, uuid);
            return preference.getInitPoint();
        } catch (MPApiException e) {
            return e.getApiResponse().getContent();
        } catch (MPException e) {
            return e.getMessage();
        }
    }

    private void createDonation(DonationDTO donationDTO, String uuid) {
        Donation donation = new Donation();
        donation.setId(uuid);
        donation.setName(donationDTO.name());
        donation.setEmail(donationDTO.email());
        donation.setValue(donationDTO.value());
        donation.setCreatedAt(LocalDateTime.now());
        saveDonation(donation);
    }

    private PreferenceRequest getRequest(PreferenceItemRequest item, PreferenceBackUrlsRequest backUrls, String uuid) {
        return PreferenceRequest.builder()
                .items(List.of(item))
                .backUrls(backUrls)
                .autoReturn("all")
                .externalReference(uuid)
                .build();
    }

    private PreferenceBackUrlsRequest getBackUrls() {
        return PreferenceBackUrlsRequest.builder()
                .success("https://portfolio-azure-nine-95.vercel.app/success")
                .failure("https://portfolio-azure-nine-95.vercel.app/failure")
                .pending("https://portfolio-azure-nine-95.vercel.app/pending")
                .build();
    }

    private PreferenceItemRequest getPreferenceItemRequest(BigDecimal value) {
        return PreferenceItemRequest.builder()
                .title("Doação Ong Patinhas")
                .quantity(1)
                .unitPrice(value)
                .build();
    }

    public Map<String, Object> findPayment(Long paymentId) {
        String url = "https://api.mercadopago.com/v1/payments/" + paymentId;
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(MercadoPagoConfig.getAccessToken());
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
        return response.getBody();
    }

    public Donation findPaymentById(String id){
        return donationRepository.findById(id).orElse(null);
    }

    public void saveDonation(Donation doantion){
        donationRepository.save(doantion);
    }
}
