package org.ongpatinhas.service;

import org.ongpatinhas.dto.AdoptionInterestDTO;
import org.ongpatinhas.mapper.AdoptionInterestMapper;
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
        AdoptionInterest adoptionInterest = AdoptionInterestMapper.toEntity(adoptionInterestDTO, dog);
        saveAdoptionInterest(adoptionInterest);

    }

    private Dog getDog(String id){
        return dogService.findDogById(id);
    }

    private void saveAdoptionInterest(AdoptionInterest adoptionInterest){
        adoptionInterestRepository.save(adoptionInterest);
    }

}
