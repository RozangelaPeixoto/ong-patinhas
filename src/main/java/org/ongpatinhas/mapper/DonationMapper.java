package org.ongpatinhas.mapper;

import org.ongpatinhas.dto.DonationDTO;
import org.ongpatinhas.model.Donation;

import java.time.LocalDateTime;

public class DonationMapper {

    public static Donation toEntity(DonationDTO dto, String uuid) {
        Donation donation = new Donation();
        donation.setId(uuid);
        donation.setName(dto.name());
        donation.setEmail(dto.email());
        donation.setAmount(dto.value());
        donation.setCreatedAt(LocalDateTime.now());
        return donation;
    }
}
