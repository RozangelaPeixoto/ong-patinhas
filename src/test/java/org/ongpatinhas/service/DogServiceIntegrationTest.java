package org.ongpatinhas.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ongpatinhas.dto.DogDTO;
import org.ongpatinhas.model.Dog;
import org.ongpatinhas.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
@TestPropertySource(properties = {
        "spring.flyway.enabled=false",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
class DogServiceIntegrationTest {

    @Autowired
    private DogService dogService;

    @Autowired
    private DogRepository dogRepository;

    @BeforeEach
    void setUp() {
        dogRepository.deleteAll();
        Dog dog = new Dog();
        dog.setId("dog1");
        dog.setName("Rex");
        dog.setAge("3 anos");
        dogRepository.save(dog);
    }

    @Test
    void testFindInfoDogById() {
        DogDTO dto = dogService.findInfoDogById("dog1");
        assertNotNull(dto);
        assertEquals("Rex", dto.name());
    }

    @Test
    void testFindDogById() {
        Dog dog = dogService.findDogById("dog1");
        assertNotNull(dog);
        assertEquals("Rex", dog.getName());
    }

    @Test
    void testFindAllDogs() {
        List<DogDTO> dogs = dogService.findAllDogs();
        assertEquals(1, dogs.size());
        assertEquals("Rex", dogs.get(0).name());
    }
}
