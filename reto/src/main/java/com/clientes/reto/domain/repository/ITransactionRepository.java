package com.clientes.reto.domain.repository;

import com.clientes.reto.domain.dto.TransactionDto;

import java.util.List;

public interface ITransactionRepository {
    List<TransactionDto> findByAccountId(Integer accountId);
    TransactionDto save(TransactionDto transactionDto);
}
