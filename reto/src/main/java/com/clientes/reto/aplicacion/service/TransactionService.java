package com.clientes.reto.aplicacion.service;

import com.clientes.reto.domain.dto.ProductDto;
import com.clientes.reto.domain.dto.TransactionDto;
import com.clientes.reto.domain.adapter.ITransactionRepository;
import com.clientes.reto.aplicacion.usecase.IProductUseCase;
import com.clientes.reto.aplicacion.usecase.ITransactionUseCase;
import com.clientes.reto.infrastructura.enums.AccountType;

import com.clientes.reto.utils.CustomException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;

@Service
public class TransactionService implements ITransactionUseCase {
    @Autowired
    @Lazy
    IProductUseCase iProductUseCase;

    @Autowired
    @Lazy
    ITransactionRepository transactionRepository;

    public TransactionDto retirar(TransactionDto transactionDto) {
        double monto = transactionDto.getMonto();
        String accountNumber = transactionDto.getAccountId();
        ProductDto productEntity = iProductUseCase.finById(accountNumber);
        double montofavor = monto - productEntity.getAvailableBalance();
        if(productEntity!=null){
            if (productEntity.getAccountType().equals(AccountType.CORRIENTE) && montofavor<3000000 || productEntity.getAccountType().equals(AccountType.AHORROS)){
                if (productEntity.getBalance()>=monto && productEntity.getAvailableBalance()>= monto || productEntity.getAccountType().equals(AccountType.CORRIENTE)){
                    productEntity.setBalance(productEntity.getBalance() - monto);
                    productEntity.setAvailableBalance(productEntity.getAvailableBalance() - monto);
                    if (productEntity.getAvailableBalance() - monto < 0){
                        productEntity.setDeudas(true);
                    }
                    Date currentDate = new Date();
                    productEntity.setUpdateDate(currentDate);
                    iProductUseCase.save(productEntity);
                    return transactionRepository.save(transactionDto);
                } else throw new CustomException("el monto que desea retirar, excede su saldo disponible");

            } else throw new CustomException("No puede sobregirar la cuenta a más de 3 millones");
        }else throw new CustomException("la cuenta no existe");

    }
    public TransactionDto consignar(TransactionDto transactionEntity){
        double monto = transactionEntity.getMonto();
        String accountNumber = transactionEntity.getAccountId();
        ProductDto product= iProductUseCase.finById(accountNumber);
        if(product!=null){
            product.setBalance(product.getBalance() + monto);
            product.setAvailableBalance(product.getAvailableBalance() + monto);
            if(product.getAccountType().equals(AccountType.CORRIENTE) && product.getBalance()>=0){
                product.setDeudas(false);
            }
            Date currentDate = new Date();
            product.setUpdateDate(currentDate);
            iProductUseCase.save(product);
            return transactionRepository.save(transactionEntity);
        }else throw new CustomException("La cuenta no existe");

    }
    public TransactionDto doATransference(String receptor, TransactionDto transactionEntity){
        String emisot = transactionEntity.getAccountId();
        double monto = transactionEntity.getMonto();
        ProductDto product = iProductUseCase.finById(receptor);
        ProductDto product1 = iProductUseCase.finById(emisot);
        double montofavor = monto - product1.getAvailableBalance();
        if(product!=null && product1!=null){
            if (product1.getAccountType().equals(AccountType.CORRIENTE) && montofavor<3000000 || product1.getAccountType().equals(AccountType.AHORROS)){
                if(product1.getBalance()>= monto && product1.getAvailableBalance()>=monto || product1.getAccountType().equals(AccountType.CORRIENTE)){
                    product.setBalance(product.getBalance() + monto);
                    product.setAvailableBalance(product.getAvailableBalance() + monto);
                    product1.setAvailableBalance(product1.getAvailableBalance() - monto);
                    product1.setBalance(product1.getBalance() - monto);
                    if (product1.getBalance()<0){
                        product1.setDeudas(true);
                    } else if (product.getAccountType().equals(AccountType.CORRIENTE) && product.getBalance()>=0) {
                        product.setDeudas(false);
                    }
                    Date currentDate = new Date();
                    product.setUpdateDate(currentDate);
                    product1.setUpdateDate(currentDate);
                    iProductUseCase.save(product);
                    iProductUseCase.save(product1);
                    return transactionRepository.save(transactionEntity);
                }else throw new CustomException("El emsisor no tiene saldo disponible");
            }else throw new CustomException("No puede sobregirar la cuenta màs de 3 millones");
        }else throw new CustomException("Alguna de las dos cuentas no existen en el sistema");

    }

    public List<TransactionDto> findByAccountId(String accountId){
        return transactionRepository.findByAccountId(accountId);
    }
}
