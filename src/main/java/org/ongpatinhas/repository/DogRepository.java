package org.ongpatinhas.repository;

import org.ongpatinhas.model.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogRepository extends JpaRepository<Dog, String> {
}
