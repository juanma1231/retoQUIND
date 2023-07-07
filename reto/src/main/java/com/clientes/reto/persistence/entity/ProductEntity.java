package com.clientes.reto.persistence.entity;
import com.clientes.reto.persistence.enums.AccountType;
import com.clientes.reto.persistence.enums.State;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "product")
public class ProductEntity {

    @Column(name = "account_type")
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Id
    @Column(name = "product_number")
    private String producNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private State state;

    @Column(nullable = false)
    private  Double balance;

    @Column(nullable = false)
    private Boolean deaudas;

    @Column(name = "available_balance", nullable = false)
    private  Double availableBalance;

    @Column(name = "excenta_gmf")
    private Boolean excentaGMF;

    @Column(name = "cration_date", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date creationDate;

    @Column(name = "update_date", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateDate;

    @Column(name = "id_client", nullable = false)
    private  String idClient;



    @OneToMany(mappedBy = "productEntity")
    private List<TransactionEntity> transactions;

    @ManyToOne
    @JoinColumn(name = "client", insertable = false, updatable = false)
    private  PersonEntity client;

    public ProductEntity(AccountType accountType, String producNumber, State state, Double balance, Boolean deaudas, Double availableBalance, Boolean excentaGMF, Date creationDate, Date updateDate, String idClient, List<TransactionEntity> transactions, PersonEntity client) {
        this.accountType = accountType;
        this.producNumber = producNumber;
        this.state = state;
        this.balance = balance;
        this.deaudas = deaudas;
        this.availableBalance = availableBalance;
        this.excentaGMF = excentaGMF;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.idClient = idClient;
        this.transactions = transactions;
        this.client = client;
    }

    public ProductEntity() {
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getProducNumber() {
        return producNumber;
    }

    public void setProducNumber(String producNumber) {
        this.producNumber = producNumber;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Boolean getDeaudas() {
        return deaudas;
    }

    public void setDeaudas(Boolean deaudas) {
        this.deaudas = deaudas;
    }

    public Double getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(Double availableBalance) {
        this.availableBalance = availableBalance;
    }

    public Boolean getExcentaGMF() {
        return excentaGMF;
    }

    public void setExcentaGMF(Boolean excentaGMF) {
        this.excentaGMF = excentaGMF;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public List<TransactionEntity> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionEntity> transactions) {
        this.transactions = transactions;
    }

    public PersonEntity getClient() {
        return client;
    }

    public void setClient(PersonEntity client) {
        this.client = client;
    }
}
