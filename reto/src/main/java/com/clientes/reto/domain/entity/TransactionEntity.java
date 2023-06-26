package com.clientes.reto.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "transaction")
@Getter
@Setter
@NoArgsConstructor
public class TransactionEntity {
    @Id
    @Column(name = "transaction_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String transactionType;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "product_number")
    private ProductEntity productEntity;

}
