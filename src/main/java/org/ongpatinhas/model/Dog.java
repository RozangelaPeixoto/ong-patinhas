package org.ongpatinhas.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tb_dogs")
public class Dog {

    @Id
    private String id;
    private String name;
    private String breed;
    private String age;
    private String skills;
    private String image;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "dog", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AdoptionInterest> adoptionInterests = new ArrayList<>();

}
