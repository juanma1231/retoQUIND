package com.clientes.reto.domain.dto;

import com.clientes.reto.domain.enums.AccountType;

import javax.xml.crypto.Data;
import java.util.List;

public class ProductDto {
    private Integer id;
    private AccountType accountType;
    private Integer productNumber;
    private String state;
    private double balance;
    private boolean deudas;
    private double availableBalance;
    private boolean excentaGMF;
    private Data creationDate;
    private Data updateDate;
    private  String idClient;
    private List<TransactionDto> transactions;
    private PersonDto client;
}
