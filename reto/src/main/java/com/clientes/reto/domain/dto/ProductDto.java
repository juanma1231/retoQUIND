package com.clientes.reto.domain.dto;

import com.clientes.reto.domain.enums.AccountType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

public class ProductDto {
    private AccountType accountType;
    private Integer productNumber;
    private String state;
    private double balance;
    private boolean deudas;
    private double availableBalance;
    private boolean excentaGMF;
    private Date creationDate;
    private Date updateDate;
    private  String idClient;
    private List<TransactionDto> transactions;
    private PersonDto client;
    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Integer getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(Integer productNumber) {
        this.productNumber = productNumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isDeudas() {
        return deudas;
    }

    public void setDeudas(boolean deudas) {
        this.deudas = deudas;
    }

    public double getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(double availableBalance) {
        this.availableBalance = availableBalance;
    }

    public boolean isExcentaGMF() {
        return excentaGMF;
    }

    public void setExcentaGMF(boolean excentaGMF) {
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

    public List<TransactionDto> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDto> transactions) {
        this.transactions = transactions;
    }

    public PersonDto getClient() {
        return client;
    }

    public void setClient(PersonDto client) {
        this.client = client;
    }
}