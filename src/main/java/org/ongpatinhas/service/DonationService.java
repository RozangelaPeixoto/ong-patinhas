package org.ongpatinhas.service;

import org.ongpatinhas.dto.DonationDTO;
import org.ongpatinhas.model.Donation;
import org.ongpatinhas.repository.DonationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DonationService {

    private final DonationRepository donationRepository;

    public DonationService(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    public Donation createDonation(DonationDTO donationDTO, String uuid) {
        Donation donation = new Donation();
        donation.setId(uuid);
        donation.setName(donationDTO.name());
        donation.setEmail(donationDTO.email());
        donation.setAmount(donationDTO.value());
        donation.setCreatedAt(LocalDateTime.now());
        saveDonation(donation);
        return donation;
    }

    Donation findDonationById(String id){
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
