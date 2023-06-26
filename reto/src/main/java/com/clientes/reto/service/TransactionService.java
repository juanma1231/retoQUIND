package com.clientes.reto.service;

import com.clientes.reto.domain.entity.ProductEntity;
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

    public ProductEntity retiro(Integer accountNumber, double monto) {
        ProductEntity productEntity = productService.finById(accountNumber);
        if (productEntity.getBalance()>=monto && productEntity.getAvailableBalance()>= monto){
            productEntity.setBalance(productEntity.getBalance() - monto);
            productEntity.setAvailableBalance(productEntity.getAvailableBalance() - monto);
            return productService.save(productEntity);
        }
        else throw new CustomException("el monto que desea retirar, excede su saldo disponible");
    }
    public ProductEntity consignacion(Integer accountNumber, double monto){
        ProductEntity product= productService.finById(accountNumber);
        product.setBalance(product.getBalance() + monto);
        product.setAvailableBalance(product.getAvailableBalance() + monto);
        return  productService.save(product);
    }
    public ProductEntity transferencia(Integer receptor, Integer emisot, double monto){
        ProductEntity product = productService.finById(receptor);
        ProductEntity product1 = productService.finById(emisot);
        if(product1.getBalance()>= monto && product1.getAvailableBalance()>=monto){
            product.setBalance(product.getBalance() + monto);
            product.setAvailableBalance(product.getAvailableBalance() + monto);
            product1.setAvailableBalance(product1.getAvailableBalance() - monto);
            product1.setBalance(product1.getBalance() - monto);
            productService.save(product);
            return productService.save(product1);
        }
        else throw new CustomException("el monto del emisor, excede el saldo disponible");
    }
}
