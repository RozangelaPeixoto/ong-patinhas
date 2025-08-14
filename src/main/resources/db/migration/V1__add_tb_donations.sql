CREATE TABLE tb_donations (
   id BINARY(16) PRIMARY KEY,
   name VARCHAR(100) NOT NULL,
   email VARCHAR(100) NOT NULL,
   value DECIMAL(10,2) NOT NULL,
   payment_type VARCHAR(50),
   status VARCHAR(50),
   created_at DATETIME,
   id_mercado_pago BIGINT,
   paid_at DATETIME
);