package org.ongpatinhas.mapper;

import org.ongpatinhas.dto.AdoptionInterestDTO;
import org.ongpatinhas.model.AdoptionInterest;
import org.ongpatinhas.model.Dog;

import java.time.LocalDateTime;

public class AdoptionInterestMapper {

    public static AdoptionInterest toEntity(AdoptionInterestDTO dto, Dog dog) {

        AdoptionInterest adoptionInterest = new AdoptionInterest();
        adoptionInterest.setFullName(dto.fullName());
        adoptionInterest.setIsAdult(dto.isAdult());
        adoptionInterest.setPhone(dto.phone());
        adoptionInterest.setEmail(dto.email());
        adoptionInterest.setHadPetsBefore(dto.hadPetsBefore());
        adoptionInterest.setCurrentlyHasPets(dto.currentlyHasPets());
        adoptionInterest.setAdoptionReason(dto.adoptionReason());
        adoptionInterest.setCreatedAt(LocalDateTime.now());
        adoptionInterest.setDog(dog);
        return adoptionInterest;
    }


}
