package com.clientes.reto.repository;

import com.clientes.reto.domain.entity.TransactionEntity;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository  extends CrudRepository<TransactionEntity, Integer> {
}
