package org.ongpatinhas.service;

import org.ongpatinhas.dto.AdoptionInterestDTO;
import org.ongpatinhas.model.AdoptionInterest;
import org.ongpatinhas.model.Dog;
import org.ongpatinhas.repository.AdoptionInterestRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AdoptionInterestService {

    private final AdoptionInterestRepository adoptionInterestRepository;
    private final DogService dogService;

    public AdoptionInterestService(AdoptionInterestRepository adoptionInterestRepository, DogService dogService) {
        this.adoptionInterestRepository = adoptionInterestRepository;
        this.dogService = dogService;
    }

    public void createAdoptionInterest(AdoptionInterestDTO adoptionInterestDTO){

        Dog dog = getDog(adoptionInterestDTO.dogId());

        AdoptionInterest adoptionInterest = new AdoptionInterest();
        adoptionInterest.setFullName(adoptionInterestDTO.fullName());
        adoptionInterest.setIsAdult(adoptionInterestDTO.isAdult());
        adoptionInterest.setPhone(adoptionInterestDTO.phone());
        adoptionInterest.setEmail(adoptionInterestDTO.email());
        adoptionInterest.setHadPetsBefore(adoptionInterestDTO.hadPetsBefore());
        adoptionInterest.setCurrentlyHasPets(adoptionInterestDTO.currentlyHasPets());
        adoptionInterest.setAdoptionReason(adoptionInterestDTO.adoptionReason());
        adoptionInterest.setCreatedAt(LocalDateTime.now());
        adoptionInterest.setDog(dog);

    }

    private Dog getDog(String id){
        return dogService.findDogById(id);
    }

    private void setAdoptionInterest(AdoptionInterest adoptionInterest){
        adoptionInterestRepository.save(adoptionInterest);
    }

}
