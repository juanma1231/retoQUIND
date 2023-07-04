package com.clientes.reto.domain.repository;

import com.clientes.reto.domain.dto.ProductDto;

import java.util.List;

public interface IProdcutDtoRepository {
    ProductDto finById(Integer id);
    ProductDto save(ProductDto productDto);
    List<ProductDto> finByUser(String mail);


    List<ProductDto> getAll();
}
