package org.ongpatinhas.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


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

}
