package com.clientes.reto.domain.repository;

import com.clientes.reto.domain.dto.ProductDto;

import java.util.List;

public interface ProdcutDtoRepository {
    ProductDto finById(Integer id);
    ProductDto save(ProductDto productDto);
    ProductDto create(ProductDto productDto);
    List<ProductDto> finByUser(String mail);
    ProductDto inactive(Integer accountId);
    ProductDto cancel(Integer accountId);
    Iterable<ProductDto> getAll();
}
