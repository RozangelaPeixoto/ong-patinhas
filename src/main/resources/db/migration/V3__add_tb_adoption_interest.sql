CREATE TABLE tb_adoption_interest (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    is_adult BOOLEAN NOT NULL,
    phone VARCHAR(20) NOT NULL,
    email VARCHAR(100) NOT NULL,
    had_pets_before BOOLEAN,
    currently_has_pets BOOLEAN,
    adoption_reason TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    dog_id VARCHAR(36) NOT NULL,
    CONSTRAINT fk_adoption_dog FOREIGN KEY (dog_id) REFERENCES tb_dogs(id)
);