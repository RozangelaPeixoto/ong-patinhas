package org.ongpatinhas.dto;

public record AdoptionInterestDTO(
        String fullName,
        Boolean isAdult,
        String phone,
        String email,
        String petName,
        Boolean hadPetsBefore,
        Boolean currentlyHasPets,
        String adoptionReason) {
}
