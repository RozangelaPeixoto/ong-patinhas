package org.ongpatinhas.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DogDTO(String id, String name, String breed, String age, String skills, String image) {
}
