package com.clientes.reto.service;

import com.clientes.reto.domain.entity.ProductEntity;
import com.clientes.reto.domain.enums.AccountType;
import com.clientes.reto.repository.ProductRepository;
import com.clientes.reto.response.CustomException;
import com.clientes.reto.response.CustomResponse;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class TransactionService {
    @Autowired
    ProductService productService;

    public ProductEntity retirar(Integer accountNumber, double monto) {
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
                    return productService.save(productEntity);
                }else throw new CustomException("No puede sobregirar la cuenta a más de 3 millones");

            } else throw new CustomException("el monto que desea retirar, excede su saldo disponible");
        }else throw new CustomException("la cuenta no existe");

    }
    public ProductEntity consignar(Integer accountNumber, double monto){
        ProductEntity product= productService.finById(accountNumber);
        if(product!=null){
            product.setBalance(product.getBalance() + monto);
            product.setAvailableBalance(product.getAvailableBalance() + monto);
            if(product.getAccountType().equals(AccountType.CORRIENTE) && product.getBalance()>=0){
                product.setDeaudas(false);
            }
            return  productService.save(product);
        }else throw new CustomException("La cuenta no existe");

    }
    public ProductEntity hacerTransferencia(Integer receptor, Integer emisot, double monto){
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
                    productService.save(product);
                    return productService.save(product1);
                }else throw new CustomException("El emsisor no tiene saldo disponible");
            }else throw new CustomException("No puede sobregirar la cuenta màs de 3 millones");
        }else throw new CustomException("Alguna de las dos cuentas no existen en el sistema");

    }
}
