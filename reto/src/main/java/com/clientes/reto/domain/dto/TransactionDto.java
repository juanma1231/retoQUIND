package com.clientes.reto.domain.dto;

import com.clientes.reto.persistence.enums.State;
import com.clientes.reto.persistence.enums.TransactionsEnum;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class TransactionDto {

    private Integer id;
    private TransactionsEnum transaccionType;

    private String description;

    private double monto;

    private Integer accountId;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TransactionsEnum getTransaccionType() {
        return transaccionType;
    }

    public void setTransaccionType(TransactionsEnum transaccionType) {
        this.transaccionType = transaccionType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

}
