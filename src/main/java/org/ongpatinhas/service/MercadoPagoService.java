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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class MercadoPagoService {

    private final DonationService donationService;
    private final RestTemplate restTemplate = new RestTemplate();

    public MercadoPagoService(DonationService donationService) {
        this.donationService = donationService;
    }

    @Value("${spring.mercadopago.success.url}")
    private String successUrl;
    @Value("${spring.mercadopago.failure.url}")
    private String failureUrl;
    @Value("${spring.mercadopago.pending.url}")
    private String pendingUrl;

    public String createDonationPreference(DonationDTO donationDTO){

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
            donationService.createDonation(donationDTO, uuid);
            return preference.getInitPoint();
        } catch (MPApiException e) {
            return e.getApiResponse().getContent();
        } catch (MPException e) {
            return e.getMessage();
        }
    }

    private PreferenceItemRequest getPreferenceItemRequest(BigDecimal value) {
        return PreferenceItemRequest.builder()
                .title("Doação Ong Patinhas")
                .quantity(1)
                .unitPrice(value)
                .build();
    }

    private PreferenceBackUrlsRequest getBackUrls() {
        return PreferenceBackUrlsRequest.builder()
                .success(successUrl)
                .failure(failureUrl)
                .pending(pendingUrl)
                .build();
    }

    private PreferenceRequest getRequest(PreferenceItemRequest item, PreferenceBackUrlsRequest backUrls, String uuid) {
        return PreferenceRequest.builder()
                .items(List.of(item))
                .backUrls(backUrls)
                .autoReturn("all")
                .externalReference(uuid)
                .build();
    }

    public void processPayment(Long paymentId){
        Map<String, Object> pagamento = findPayment(paymentId);
        extractInfoFromPayment(pagamento, paymentId);
    }

    private Map<String, Object> findPayment(Long paymentId) {
        String url = "https://api.mercadopago.com/v1/payments/" + paymentId;
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(MercadoPagoConfig.getAccessToken());
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<>() {
                }
        );

        return response.getBody();
    }

    private void extractInfoFromPayment(Map<String, Object> pagamento, Long paymentId) {
        String referencia = (String) pagamento.get("external_reference");
        String formaPagamento = (String) pagamento.get("payment_type_id");
        String status = (String) pagamento.get("status");
        donationService.updateDonation(referencia, formaPagamento, paymentId, status);
    }

}
