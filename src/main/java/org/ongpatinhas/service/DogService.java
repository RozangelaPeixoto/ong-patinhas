package org.ongpatinhas.service;

import org.ongpatinhas.dto.DogDTO;
import org.ongpatinhas.model.Dog;
import org.ongpatinhas.repository.DogRepository;
import org.springframework.stereotype.Service;

@Service
public class DogService {

    private final DogRepository dogRepository;

    public DogService(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public DogDTO findInfoDogById(String id){
        Dog dog = dogRepository.findById(id).orElse(null);

        if(dog == null) return null;
        DogDTO dogDTO = new DogDTO(dog.getId(), dog.getName(), dog.getBreed(), dog.getAge(), dog.getSkills(), dog.getImage());

        return dogDTO;
    }

    public Dog findDogById(String id){
        return dogRepository.findById(id).orElse(null);
    }

}
