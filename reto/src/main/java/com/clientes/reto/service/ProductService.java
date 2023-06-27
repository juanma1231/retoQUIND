package com.clientes.reto.service;

import com.clientes.reto.domain.entity.ProductEntity;
import com.clientes.reto.domain.enums.State;
import com.clientes.reto.repository.ProductRepository;
import com.clientes.reto.response.CustomException;
import com.clientes.reto.response.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

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
        return  productRepository.save(product);
    }
    public  List<ProductEntity> finByUser(String email){
        return productRepository.finByUser(email);
    }
    public void inactive(Integer accountId){
        ProductEntity product = productRepository.finById(accountId);
        product.setState(State.INACTIVO);
        productRepository.save(product);
    }
    public void cancelar(Integer accountId){
        ProductEntity product = productRepository.finById(accountId);
        if (product.getBalance() < 1){
            product.setState(State.CANCELADA);
        }else throw new CustomException("No se puede cancelar una cuenta con un saldo superior a 1");

    }

}
