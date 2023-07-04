package com.clientes.reto.persistence.repositoryimpl;

import com.clientes.reto.domain.dto.ProductDto;
import com.clientes.reto.domain.repository.IProdcutDtoRepository;
import com.clientes.reto.persistence.entity.ProductEntity;
import com.clientes.reto.persistence.mappers.ProductMapper;
import com.clientes.reto.persistence.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ProductRepositoryImplI implements IProdcutDtoRepository {

    @Autowired
    @Lazy
    ProductRepository productRepository;

    @Autowired
    ProductMapper mapper;
    @Override
    public ProductDto finById(Integer id) {
        return mapper.toProductDto(productRepository.finById(id));
    }

    @Override
    public ProductDto save(ProductDto productDto) {
        ProductEntity productEntity = mapper.toProductEntity(productDto);
        return mapper.toProductDto(productRepository.save(productEntity));
    }



    @Override
    public List<ProductDto> finByUser(String mail) {
        return mapper.toProducts(productRepository.finByUser(mail));
    }

    @Override
    public List<ProductDto> getAll() {
        return mapper.toProducts((List<ProductEntity>) productRepository.findAll());
    }
}
