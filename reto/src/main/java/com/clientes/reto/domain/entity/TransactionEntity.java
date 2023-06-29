package com.clientes.reto.domain.entity;

import com.clientes.reto.domain.enums.TransactionsEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "transaction")
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

    public TransactionEntity(Integer id, TransactionsEnum transactionType, String description, Double monto, Integer accountId, ProductEntity productEntity) {
        this.id = id;
        this.transactionType = transactionType;
        this.description = description;
        this.monto = monto;
        this.accountId = accountId;
        this.productEntity = productEntity;
    }

    public TransactionEntity() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TransactionsEnum getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionsEnum transactionType) {
        this.transactionType = transactionType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }
}
