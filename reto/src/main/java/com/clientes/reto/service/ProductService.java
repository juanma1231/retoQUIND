package com.clientes.reto.service;

import com.clientes.reto.domain.entity.ProductEntity;
import com.clientes.reto.domain.enums.State;
import com.clientes.reto.repository.ProductRepository;
import com.clientes.reto.utils.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public ProductEntity finById(Integer id) {
        return productRepository.findById(id).orElseThrow(()-> new CustomException("Producto no encontrado"));
    }

    public ProductEntity save(ProductEntity product){

        return productRepository.save(product);
    }
    public ProductEntity create(ProductEntity product){
        product.setDeaudas(false);
        product.setState(State.ACTIVO);
        product.setCreationDate(LocalDateTime.now());
        product.setUpdateDate(LocalDateTime.now());
        return  productRepository.save(product);

    }
    public  List<ProductEntity> finByUser(String email){
        return productRepository.finByUser(email);
    }
    public void inactive(Integer accountId){
        ProductEntity product = productRepository.finById(accountId);
        product.setState(State.INACTIVO);
        product.setUpdateDate(LocalDateTime.now());
        productRepository.save(product);
    }
    public void cancelar(Integer accountId){
        ProductEntity product = productRepository.finById(accountId);
        if (product.getBalance() < 1 && Boolean.TRUE.equals(!product.getDeaudas())){
            product.setState(State.CANCELADA);
            product.setUpdateDate(LocalDateTime.now());
        }else throw new CustomException("No se puede cancelar una cuenta con un saldo superior a 1");

    }

}
