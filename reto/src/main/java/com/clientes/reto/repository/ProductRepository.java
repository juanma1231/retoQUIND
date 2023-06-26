package com.clientes.reto.repository;

import com.clientes.reto.domain.entity.PersonEntity;
import com.clientes.reto.domain.entity.ProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM product p WHERE p.id_client = ?1")
    List<ProductEntity> finByUser(String email);

    @Query(nativeQuery = true, value = "SELEC * FROM product p WHERE p.product_number = ?1")
    ProductEntity finById(Integer id);
}
