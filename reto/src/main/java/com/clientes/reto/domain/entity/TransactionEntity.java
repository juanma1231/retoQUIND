package com.clientes.reto.domain.entity;

import com.clientes.reto.domain.enums.TransactionsEnum;
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
    @Enumerated(EnumType.STRING)
    private TransactionsEnum transactionType;

    @Column
    private String description;

    @Column
    private Double monto;

    @Column(name = "account_id", nullable = false, length = 10)
    private Integer accountId;

    @ManyToOne
    @JoinColumn(name = "product_number")
    private ProductEntity productEntity;

}
