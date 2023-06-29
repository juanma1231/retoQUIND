package com.clientes.reto.service;

import com.clientes.reto.domain.entity.ProductEntity;
import com.clientes.reto.domain.entity.TransactionEntity;
import com.clientes.reto.domain.enums.AccountType;

import com.clientes.reto.repository.TransactionRepository;
import com.clientes.reto.utils.CustomException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    ProductService productService;

    @Autowired
    TransactionRepository transactionRepository;

    public TransactionEntity retirar(TransactionEntity transactionEntity) {
        double monto = transactionEntity.getMonto();
        Integer accountNumber = transactionEntity.getAccountId();
        ProductEntity productEntity = productService.finById(accountNumber);
        double montofavor = monto - productEntity.getAvailableBalance();
        if(productEntity!=null){
            if (productEntity.getAccountType().equals(AccountType.CORRIENTE) && montofavor<3000000 || productEntity.getAccountType().equals(AccountType.AHORROS)){
                if (productEntity.getBalance()>=monto && productEntity.getAvailableBalance()>= monto || productEntity.getAccountType().equals(AccountType.CORRIENTE)){
                    productEntity.setBalance(productEntity.getBalance() - monto);
                    productEntity.setAvailableBalance(productEntity.getAvailableBalance() - monto);
                    if (productEntity.getAvailableBalance() - monto < 0){
                        productEntity.setDeaudas(true);
                    }
                    Date currentDate = new Date();
                    productEntity.setUpdateDate(currentDate);
                    productService.save(productEntity);
                    return transactionRepository.save(transactionEntity);
                }else throw new CustomException("No puede sobregirar la cuenta a más de 3 millones");

            } else throw new CustomException("el monto que desea retirar, excede su saldo disponible");
        }else throw new CustomException("la cuenta no existe");

    }
    public TransactionEntity consignar(TransactionEntity transactionEntity){
        double monto = transactionEntity.getMonto();
        Integer accountNumber = transactionEntity.getAccountId();
        ProductEntity product= productService.finById(accountNumber);
        if(product!=null){
            product.setBalance(product.getBalance() + monto);
            product.setAvailableBalance(product.getAvailableBalance() + monto);
            if(product.getAccountType().equals(AccountType.CORRIENTE) && product.getBalance()>=0){
                product.setDeaudas(false);
            }
            List<TransactionEntity> transactionEntities = product.getTransactions();
            transactionEntities.add(transactionEntity);
            product.setTransactions(transactionEntities);
            System.out.println("Consignando");
            System.out.println(product.getTransactions());
            Date currentDate = new Date();
            product.setUpdateDate(currentDate);
            productService.save(product);
            return transactionRepository.save(transactionEntity);
        }else throw new CustomException("La cuenta no existe");

    }
    public TransactionEntity doATransference(Integer receptor, TransactionEntity transactionEntity){
        Integer emisot = transactionEntity.getAccountId();
        double monto = transactionEntity.getMonto();
        ProductEntity product = productService.finById(receptor);
        ProductEntity product1 = productService.finById(emisot);
        double montofavor = monto - product1.getAvailableBalance();
        if(product!=null && product1!=null){
            if (product1.getAccountType().equals(AccountType.CORRIENTE) && montofavor<3000000 || product1.getAccountType().equals(AccountType.AHORROS)){
                if(product1.getBalance()>= monto && product1.getAvailableBalance()>=monto || product1.getAccountType().equals(AccountType.CORRIENTE)){
                    product.setBalance(product.getBalance() + monto);
                    product.setAvailableBalance(product.getAvailableBalance() + monto);
                    product1.setAvailableBalance(product1.getAvailableBalance() - monto);
                    product1.setBalance(product1.getBalance() - monto);
                    if (product1.getBalance()<0){
                        product1.setDeaudas(true);
                    } else if (product.getAccountType().equals(AccountType.CORRIENTE) && product.getBalance()>=0) {
                        product.setDeaudas(false);
                    }
                    Date currentDate = new Date();
                    product.setUpdateDate(currentDate);
                    product1.setUpdateDate(currentDate);
                    productService.save(product);
                    productService.save(product1);
                    return transactionRepository.save(transactionEntity);
                }else throw new CustomException("El emsisor no tiene saldo disponible");
            }else throw new CustomException("No puede sobregirar la cuenta màs de 3 millones");
        }else throw new CustomException("Alguna de las dos cuentas no existen en el sistema");

    }

    public List<TransactionEntity> findByAccountId(Integer accountId){
        return transactionRepository.findByAccountId(accountId);
    }
}
