package org.ongpatinhas.service;

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

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class DonationService {

    private final DonationRepository donationRepository;

    public DonationService(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    public String createPreference(DonationDTO donationDTO){

        PreferenceClient client = new PreferenceClient();
        UUID uuid = UUID.randomUUID();
        PreferenceItemRequest item = getPreferenceItemRequest(donationDTO.value());

        PreferenceBackUrlsRequest backUrls = getBackUrls();

        PreferenceRequest request = getRequest(item, backUrls, uuid);

        Map<String, String> headers =  new HashMap<>();
        headers.put("X-Idempotency-Key", uuid.toString());

        MPRequestOptions requestOptions = MPRequestOptions.builder()
                .customHeaders(headers)
                .build();
        try {
            Preference preference = client.create(request, requestOptions);
            saveDonation(donationDTO, uuid);
            return preference.getInitPoint();
        } catch (MPApiException e) {
            return e.getApiResponse().getContent();
        } catch (MPException e) {
            return e.getMessage();
        }
    }

    private void saveDonation(DonationDTO donationDTO, UUID uuid) {
        Donation donation = new Donation();
        donation.setId(uuid);
        donation.setName(donationDTO.name());
        donation.setEmail(donationDTO.email());
        donation.setValue(donationDTO.value());
        donation.setCreatedAt(LocalDateTime.now());
        donationRepository.save(donation);
    }

    private PreferenceRequest getRequest(PreferenceItemRequest item, PreferenceBackUrlsRequest backUrls, UUID uuid) {
        return PreferenceRequest.builder()
                .items(List.of(item))
                .backUrls(backUrls)
                .autoReturn("all")
                .externalReference(uuid.toString())
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


}
