package org.ongpatinhas.mapper;

import org.ongpatinhas.dto.DogDTO;
import org.ongpatinhas.model.Dog;

public class DogMapper {

    public static DogDTO toDto(Dog dog) {

        return new DogDTO(dog.getId(), dog.getName(), dog.getBreed(), dog.getAge(), dog.getSkills(), dog.getImage());

    }

}
