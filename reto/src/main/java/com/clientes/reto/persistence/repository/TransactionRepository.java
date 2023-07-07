package com.clientes.reto.persistence.repository;

import com.clientes.reto.persistence.entity.TransactionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository  extends CrudRepository<TransactionEntity, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM transaction t WHERE t.account_id = ?1")
    List<TransactionEntity> findByAccountId(String accountId);
}
