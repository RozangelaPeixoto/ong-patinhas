CREATE TABLE adoption_interest (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    is_adult BOOLEAN NOT NULL,
    phone VARCHAR(20) NOT NULL,
    email VARCHAR(100) NOT NULL,
    pet_name VARCHAR(100) NOT NULL,
    had_pets_before BOOLEAN,
    currently_has_pets BOOLEAN,
    adoption_reason TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);