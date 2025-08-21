package org.ongpatinhas.service;

import org.ongpatinhas.dto.AdoptionInterestDTO;
import org.ongpatinhas.model.AdoptionInterest;
import org.ongpatinhas.repository.AdoptionInterestRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AdoptionInterestService {

    AdoptionInterestRepository adoptionInterestRepository;

    public AdoptionInterestService(AdoptionInterestRepository adoptionInterestRepository) {
        this.adoptionInterestRepository = adoptionInterestRepository;
    }

    public void createAdoptionInterest(AdoptionInterestDTO adoptionInterestDTO){

        AdoptionInterest adoptionInterest = new AdoptionInterest();
        adoptionInterest.setFullName(adoptionInterestDTO.fullName());
        adoptionInterest.setIsAdult(adoptionInterestDTO.isAdult());
        adoptionInterest.setPhone(adoptionInterestDTO.phone());
        adoptionInterest.setEmail(adoptionInterestDTO.email());
        adoptionInterest.setPetName(adoptionInterestDTO.petName());
        adoptionInterest.setHadPetsBefore(adoptionInterestDTO.hadPetsBefore());
        adoptionInterest.setCurrentlyHasPets(adoptionInterestDTO.currentlyHasPets());
        adoptionInterest.setAdoptionReason(adoptionInterestDTO.adoptionReason());
        adoptionInterest.setCreatedAt(LocalDateTime.now());
        adoptionInterestRepository.save(adoptionInterest);

    }

}
