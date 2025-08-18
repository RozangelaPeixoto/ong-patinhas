package org.ongpatinhas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name="tb_donations")
public class Donation {

    @Id
    private String id;
    private String name;
    private String email;
    private BigDecimal amount;
    @Column(name="payment_type")
    private String paymentType;
    private String status;
    @Column(name="created_at")
    private LocalDateTime createdAt;
    @Column(name="id_mercado_pago")
    private Long idMercadoPago;
    @Column(name="paid_at")
    private LocalDateTime paidAt;

}
