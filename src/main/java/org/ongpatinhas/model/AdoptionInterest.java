package org.ongpatinhas.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "tb_adoption_interest")
public class AdoptionInterest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name",nullable = false, length = 100)
    private String fullName;

    @Column(name = "is_adult", nullable = false)
    private Boolean isAdult;

    @Column(nullable = false, length = 20)
    private String phone;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(name = "had_pets_before")
    private Boolean hadPetsBefore;

    @Column(name = "currently_has_pets")
    private Boolean currentlyHasPets;

    @Column(name = "adoption_reason", columnDefinition = "TEXT")
    private String adoptionReason;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "dog_id", nullable = false)
    private Dog dog;

}
