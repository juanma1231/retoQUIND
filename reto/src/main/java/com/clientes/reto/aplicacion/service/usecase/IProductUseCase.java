package com.clientes.reto.aplicacion.service.usecase;

import com.clientes.reto.domain.dto.ProductDto;

import java.util.List;

public interface IProductUseCase {
    ProductDto create(ProductDto productDto);
    ProductDto save(ProductDto productDto);
    ProductDto finById(String id);
    List<ProductDto> finByUser(String email);
    ProductDto inactive(String accountId);
    ProductDto cancelar(String accountId);
    List<ProductDto> getALl();

}
