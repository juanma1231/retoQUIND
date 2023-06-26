package com.clientes.reto.service;

import com.clientes.reto.domain.entity.ProductEntity;
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
}
