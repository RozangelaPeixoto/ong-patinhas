package org.ongpatinhas.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AdoptionInterestDTO(
        @NotBlank(message = "O nome é obrigatório")
        String fullName,

        @AssertTrue(message = "Você precisa ter mais de 18 anos")
        Boolean isAdult,

        @NotBlank(message = "O telefone é obrigatório")
        @Pattern(
                regexp = "\\d{11}",
                message = "Telefone inválido, precisa ter 11 dígitos"
        )
        String phone,

        @Email(message = "E-mail inválido")
        @NotBlank(message = "O e-mail é obrigatório")
        String email,

        @NotBlank(message = "O nome do animal é obrigatório")
        String petName,

        Boolean hadPetsBefore,
        Boolean currentlyHasPets,

        @NotBlank(message = "Por favor, insira um motivo para querer adotar")
        String adoptionReason){

    public AdoptionInterestDTO() {
        this("",false , "", "", "", false, false, "");

    }
}
