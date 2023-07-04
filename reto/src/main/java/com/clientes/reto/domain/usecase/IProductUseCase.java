package com.clientes.reto.domain.usecase;

import com.clientes.reto.domain.dto.ProductDto;

import java.util.List;

public interface IProductUseCase {
    ProductDto create(ProductDto productDto);
    ProductDto save(ProductDto productDto);
    ProductDto finById(Integer id);
    List<ProductDto> finByUser(String email);
    ProductDto inactive(Integer accountId);
    ProductDto cancelar(Integer accountId);
    List<ProductDto> getALl();

}
