package com.clientes.reto.domain.entity;

import com.clientes.reto.domain.enums.AccountType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
public class ProductEntity {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "account_type")
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Column(name = "product_number", nullable = false, length = 10)
    private Integer producNumber;

    @Column(nullable = false)
    private  String state;

    @Column(nullable = false)
    private  Double balance;

    @Column(nullable = false)
    private Boolean deaudas;

    @Column(name = "available_balance", nullable = false)
    private  Double availableBalance;

    @Column(name = "excenta_gmf", nullable = false)
    private Boolean excentaGMF;

    @Column(name = "cration_date", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Data creationDate;

    @Column(name = "update_date", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Data updateDate;

    @Column(name = "id_client", nullable = false)
    private  String idClient;



    @OneToMany(mappedBy = "productEntity")
    private List<TransactionEntity> transactions;

    @ManyToOne
    @JoinColumn(name = "id_client", insertable = false, updatable = false)
    private  PersonEntity client;

}
